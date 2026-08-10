# Part 5 — Pydantic AI executor

Pipeline **run** (`POST /pipelines/run`) executes nodes in topological order. LLM nodes are powered by **[Pydantic AI](https://ai.pydantic.dev/)** — one agent per LLM invocation, optional multimodal images, and an optional **knowledge search tool** when a Knowledge Base is wired.

For the full system picture (RAG design, persistence, frontend registry), see [architecture-and-design.md](architecture-and-design.md).

## Architecture

```
pipeline_executor.py
    ├── customInput / text / knowledgeBase / utilities / output
    └── llm_runner.py
            └── agent_factory.py  →  pydantic_ai.Agent (+ search_knowledge_base tool)
```

| Module | Role |
|--------|------|
| `execution_value.py` | Typed values between nodes (text, base64 image, media type) |
| `agent_factory.py` | Builds `Agent` with backend `AGENT_SYSTEM_PROMPT`; registers KB tool |
| `llm_runner.py` | Assembles user prompt parts, runs agent, returns step metadata |
| `rag_runner.py` | Chunking + lexical retrieval used inside the knowledge tool |
| `run_store.py` | Persists indexed chunks in SQLite (`rag_chunks` table) |
| `pipeline_executor.py` | Topological walk; resolves handles; calls runners per node type |

## LLM node — frontend vs backend

The LLM node UI exposes only **pipeline author** fields. **Agent behavior** (system instructions, tool wiring) lives in the backend.

### Frontend (`node.data`)

| Field | Default | Purpose |
|-------|---------|---------|
| `model` | `gpt-4o-mini` | Passed as `openai:{model}` unless already `provider:model` |
| `additionalInfo` | `""` | Optional notes **appended to the user message** (not system prompt) |

### Backend (`config.py` / `.env`)

| Setting | Default | Purpose |
|---------|---------|---------|
| `AGENT_SYSTEM_PROMPT` | See `app/core/config.py` | Neutral agent instructions (all LLM runs) |
| `DEFAULT_KNOWLEDGE_TOP_K` | `4` | Chunks returned per `search_knowledge_base` call |
| `OPENAI_API_KEY` | unset | Real model + tool calls; without it → `TestModel` |

Override agent instructions via `AGENT_SYSTEM_PROMPT` in `backend/.env` if needed. Copy `backend/.env.example` → `backend/.env`.

## Agent factory

```python
# Simplified flow (agent_factory.py + llm_runner.py)
has_kb = bool(knowledge_bases_wired_to_llm)
config = llm_config_from_node_data(node_data, has_knowledge_tool=has_kb)
agent = create_llm_agent(config, use_mock=not settings.OPENAI_API_KEY, knowledge=...)
result = await agent.run(prompt_parts)  # user text + optional BinaryContent images
```

`build_agent_instructions()` starts from `settings.AGENT_SYSTEM_PROMPT`. When a Knowledge Base is connected to the LLM **knowledge** port, extra text is appended requiring `search_knowledge_base` before answering document questions.

The tool is registered only when KB nodes are wired — no UI toggle.

### `search_knowledge_base` tool

- Loads chunks from SQLite for each connected KB `node_id` (indexed earlier in the same run).
- Runs lexical retrieval (`rag_runner.retrieve_chunks`).
- Falls back to top chunks if scores are zero (helps broad questions like “what is this document about?”).
- Returns bullet-list passages to the agent; the agent writes the final answer.

This is **agent-driven RAG**: the user message goes to the agent first; the agent chooses when and how to query — not a fixed `retrieve → LLM` pipeline step.

## Knowledge Base node (indexing)

On run, `pipeline_executor._execute_knowledge_base`:

1. Reads `sourceText` / uploaded file from node data (and optional wired document text).
2. Chunks via `rag_runner.chunk_text`.
3. Stores in `rag_chunks` keyed by `node_id` (`run_store.replace_rag_chunks`).

`dag_validator` ensures KB nodes tend to run **before** LLM nodes in the same execution.

## Mock vs real LLM

| `OPENAI_API_KEY` | Model | Knowledge tool |
|------------------|-------|----------------|
| Not set | `TestModel()` | Returns explicit message — tools cannot run in mock mode |
| Set | `openai:{model}` | Full agent + optional tool calls |

| Mode | Typical LLM output |
|------|-------------------|
| Mock, no KB | `"success (no tool calls)"` — deterministic, no network |
| Mock, KB wired | Message explaining API key required for tool use |
| Real, KB wired | Agent may call `search_knowledge_base`, then respond |

## Multimodal images (`BinaryContent`)

Image inputs flow as `ExecutionValue` with base64 payload + `image_media_type`.

### Frontend — Input node

- Type **Image** → file upload (stored as data URL in `inputValue`)
- `inputMediaType` from file MIME type (e.g. `image/png`)

### LLM node handles (current)

| Handle | Position | Accepts |
|--------|----------|---------|
| `input` | Left | Text prompt from Input / Text / upstream nodes; images forwarded as binary |
| `knowledge` | Top | **Knowledge Base only** (`knowledgeBase` → `connector`) |
| `response` | Right | Output to downstream nodes |

Legacy handle names (`prompt`, `system`, `images`) are still resolved in `_resolve_llm_inputs` if present in old saved workflows.

### Backend — prompt assembly

```python
prompt_parts = [
    "Describe this logo",
    BinaryContent(data=png_bytes, media_type="image/png"),
]
await agent.run(prompt_parts)
```

`build_prompt_parts()` mixes text and `BinaryContent`. Optional `additionalInfo` from the LLM node is appended to the user message in `pipeline_executor` before `run_llm_node`.

## Run response

```json
{
  "steps": [
    {
      "node_id": "llm-1",
      "node_type": "llm",
      "output": "...",
      "model": "gpt-4o-mini",
      "used_image": false,
      "used_knowledge_tool": true
    }
  ],
  "output": "final text from Output node",
  "used_mock_llm": false,
  "warnings": []
}
```

Execution order follows **data flow** (n8n-style priorities): Input / KB → Text → utilities → LLM → Output — not canvas drop order.

The run history panel shows per-step output; `used_knowledge_tool` is set when a KB was wired (tool was available for that run).

## Example pipelines

### Text → LLM → Output

1. Input value: `"Explain vector databases in one sentence"`
2. Wire Input → LLM **input** (left)
3. LLM **response** → Output
4. **Run pipeline**

### RAG agent (Knowledge Base + LLM)

1. Upload doc on **Knowledge Base** node
2. Wire KB **bottom** → LLM **top** (knowledge port)
3. Wire Input → LLM **left** (user question)
4. LLM **response** → Output
5. **Run pipeline** — KB step indexes chunks; LLM agent may call `search_knowledge_base`

Load the **RAG agent** example from the sidebar to pre-build this graph.

### Image → LLM → Output

1. Input type: **Image**, upload a PNG
2. Connect Input → LLM **input**
3. Optional: set **Additional context** on LLM (e.g. `"Describe the image briefly."`)
4. **Run pipeline** — backend sends `BinaryContent` to the agent

### Parallel LLMs + merge

Two LLM nodes share one Input; use **Additional context** on each LLM for style hints (e.g. paragraph vs bullets). Backend system prompt stays the same; only the appended user context differs.

## Tests

```bash
cd backend
pytest app/tests/test_agent_factory.py
pytest app/tests/test_llm_runner.py
pytest app/tests/test_run_persistence.py
pytest app/tests/test_pipeline_run.py
```

| Test area | What it covers |
|-----------|----------------|
| `test_agent_factory` | Model normalization, `TestModel`, KB instruction suffix |
| `test_llm_runner` | `BinaryContent` in prompt parts, mock output tuple |
| `test_run_persistence` | KB indexes + LLM `used_knowledge_tool` in API run |
| `test_pipeline_run` | Linear flows, image → LLM, mock LLM |

Full suite: `pytest` (35 tests).

## Why Pydantic AI?

- **Agents + tools** — `search_knowledge_base` is a first-class tool; the model decides when to call it
- **`BinaryContent`** — idiomatic multimodal prompts without hand-rolled base64 chat JSON
- **`TestModel`** — reliable CI without API keys
- **Provider prefix** (`openai:gpt-4o-mini`) — easy swap to `anthropic:...` etc.
- **Separation of concerns** — backend owns agent instructions; canvas owns model + optional user context

## Related docs

- [architecture-and-design.md](architecture-and-design.md) — RAG trade-offs, SQLite, auto-layout
- [video-script.md](video-script.md) — demo recording outline
