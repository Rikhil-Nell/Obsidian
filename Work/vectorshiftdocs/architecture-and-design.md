# Architecture & Design Choices

This document explains how the VectorShift pipeline builder is structured, why key decisions were made, and how data flows from the canvas to the backend and back.

---

## 1. High-level picture

```
┌─────────────────────────────────────────────────────────────────┐
│  Frontend (React + React Flow + Zustand)                        │
│  • Canvas: nodes, edges, drag/drop, auto-layout                 │
│  • nodeRegistry: single source of truth for node types            │
│  • workflowSchema: import/export JSON (vectorshift.workflow.v1) │
└────────────────────────────┬────────────────────────────────────┘
                             │ POST /pipelines/run
                             │ GET/DELETE /runs
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│  Backend (FastAPI + Pydantic AI)                                │
│  • dag_validator: topological order + cycle detection           │
│  • pipeline_executor: run each node type in order               │
│  • agent_factory + llm_runner: Pydantic AI agents per LLM node  │
│  • run_store + SQLite: persist runs + RAG chunk indexes         │
└─────────────────────────────────────────────────────────────────┘
```

**Design principle:** The frontend owns *authoring* (layout, wiring, node config). The backend owns *execution* (ordering, LLM agents, knowledge indexing, persistence). Shared contract is the workflow JSON shape (`nodes` + `edges`).

---

## 2. Repository layout

| Path | Role |
|------|------|
| `frontend/` | React app — canvas, sidebar, run history, workflow I/O |
| `backend/` | FastAPI app — parse, validate, run, runs API |
| `docs/` | Study guides + this architecture doc + video script |
| `backend/data/pipeline.db` | SQLite — run history + indexed knowledge chunks (portable) |

`frontend/` and `backend/` are **separate git repos** (as in the starter). `docs/` lives at the zip root for submission.

---

## 3. Frontend architecture

### 3.1 Config-driven nodes (`nodeRegistry.js`)

**Choice:** One registry defines every standard node type (handles, fields, icons, initial data). `ConfigDrivenNode` renders fields from that config; only **Input**, **Text**, and **Knowledge Base** are custom components.

**Why:**
- Adding a node type = one registry entry + (optional) custom component.
- Handles stay consistent (`{nodeId}-{suffix}`).
- Import/export round-trips without a parallel schema per node.

**Custom nodes when UI is special:**
- **Input** — variable rename propagates to Text templates; image upload.
- **Text** — `{{variable}}` parsing, dynamic left handles, autocomplete.
- **Knowledge Base** — file upload, preview, bottom connector only.

### 3.2 State (`store.js`)

Zustand store holds:
- `nodes`, `edges`, `workflowName`
- `runHistory`, `activeRunId`, `activeRunDetail`
- `layoutTick` / `layoutRequested` for auto-layout

**`onConnect` rules** encode product behavior (not just React Flow defaults):
- Input → Text: auto-append `{{var}}` to template, assign handle.
- Input → LLM: force `input` handle; one prompt wire per source.
- Knowledge Base → LLM: force `knowledge` top port + `connector` source.
- Merge: auto-assign `inputA` / `inputB`.
- Knowledge port: **only** `knowledgeBase` sources allowed (`isValidConnection` in `ui.js`).

### 3.3 Workflow JSON (`workflowSchema.js`)

Format: `vectorshift.workflow.v1` with `nodes`, `edges`, `name`, `exportedAt`.

- Import validates node types (rejects legacy `rag` node with a helpful error).
- `syncNodeIdCounters` keeps `getNodeID()` from colliding after import.

### 3.4 Auto-layout (`autoLayout.js` + `LayoutOrchestrator.js`)

**Problem:** Imported examples and restored runs stacked nodes at overlapping positions.

**Solution (industry pattern from React Flow docs):**
1. **dagre** (`@dagrejs/dagre`) — layered layout, `rankdir: LR` (left-to-right pipeline flow).
2. Per-type **size estimates** for first paint.
3. **`useNodesInitialized`** — second pass with measured DOM sizes after render.
4. Knowledge edges excluded from dagre; KB nodes placed **above** their LLM (center-aligned).

**Auto-arrange button** (bottom-left grid icon) re-runs the same pipeline.

### 3.5 Example gallery (`workflows/exampleGallery.js`)

Embedded demo workflows loaded via sidebar — no extra fetch. Three demos:
- RAG agent (KB + Input + LLM + Output)
- Parallel LLMs + merge
- Simple prompt chain

### 3.6 UI polish choices

- Fixed **228px** node width — consistent canvas density.
- Scrollable textareas (`overflow-y: auto`, `max-height`).
- Number inputs in nodes: **no spinner arrows** (CSS in `index.css`).
- No instructional paragraph text inside nodes — labels + fields only.
- LLM node: **Model** + optional **Additional context** (user notes appended to message, not system prompt).

---

## 4. Backend architecture

### 4.1 Layering

```
router (pipeline_router, runs_router)
  → controller
    → service (pipeline_executor, run_store, dag_validator)
      → schemas (Pydantic models)
```

Keeps HTTP thin; execution logic is testable without FastAPI.

### 4.2 Execution order (`dag_validator.py`)

Topological sort with **type priorities** (sources before sinks when multiple nodes are ready):

`customInput` → `knowledgeBase` / `text` → utilities → `llm` → `customOutput`

Ensures knowledge bases **index before** the LLM reads chunks in the same run.

### 4.3 Node execution (`pipeline_executor.py`)

| Node type | Behavior |
|-----------|----------|
| `customInput` | Text or image `ExecutionValue` |
| `text` | Template render `{{var}}` from wired inputs |
| `knowledgeBase` | Chunk `sourceText` / file → SQLite `rag_chunks` |
| `llm` | Pydantic AI agent; optional KB tool |
| `customOutput` | Pass-through display |
| `merge`, `condition`, `split`, `transform`, `delay` | Utility transforms / routing |

**Branch nodes** (`condition`, `split`) attach outputs to named handles (`true`/`false`, `branchA`/`branchB`). Executor uses `sourceHandle` when reading upstream values.

### 4.4 Agent-driven RAG (design choice)

**Rejected:** `Input → RAG retrieve → LLM` (fixed retrieval before the model).

**Implemented:** `Input → LLM` with optional **Knowledge Base** on the LLM **top** port.

1. On run, KB node chunks text and stores in `rag_chunks` (keyed by `node_id`).
2. If KB is wired, the agent gets a `search_knowledge_base(query)` tool automatically.
3. The agent decides **when** and **how** to query (crafts focused queries, not raw user typos).
4. Backend owns **system instructions** (`AGENT_SYSTEM_PROMPT` in `config.py`) — not exposed in the LLM node UI.
5. **Additional context** on the LLM node is appended to the **user message** only.

**Retrieval:** Lexical overlap scoring in `rag_runner.py` (simple, no embedding API dependency). Fallback returns top chunks when scores are zero (helps “what is this document about?”).

### 4.5 Pydantic AI (`agent_factory.py`, `llm_runner.py`)

- One `Agent` per LLM node invocation.
- `TestModel` when `OPENAI_API_KEY` is unset (deterministic mock; tools cannot run).
- Images: `BinaryContent` in multimodal prompt parts.
- Knowledge tool registered with `RunContext` for Pydantic AI 1.x compatibility.

### 4.6 Persistence (`run_store.py`, `database.py`)

SQLite tables:
- **runs** — status, workflow snapshot JSON, result JSON, timestamps
- **rag_chunks** — `(node_id, chunk_index, content)` for knowledge bases

Including `backend/data/` in the submission zip preserves history and indexes.

---

## 5. Key design trade-offs

| Decision | Alternative | Why we chose this |
|----------|-------------|-------------------|
| Config-driven registry | One file per node class | Faster to add nodes; matches n8n-style mental model |
| Agent tool RAG | Pipeline RAG node | Better queries from agent; user message not pre-retrieved |
| Backend system prompt | LLM node textarea | Consistent agent behavior; UI stays simple |
| dagre layout | Hand-tuned positions only | Examples/history restore reliably |
| SQLite persistence | In-memory only | Run history survives reload; portable zip |
| Lexical retrieval | Vector embeddings | No extra API/cost; good enough for demo docs |
| Zustand | Redux | Small store; less boilerplate for canvas state |

---

## 6. Extension points

- **Embeddings:** Swap `retrieve_chunks` implementation; keep `search_knowledge_base` tool API.
- **New node type:** Add to `nodeRegistry.js`, executor branch, optional `dag_validator` priority.
- **New agent tools:** Register in `agent_factory.py` when wired similarly to KB.
- **Horizontal vs vertical layout:** `autoLayout.js` `direction` parameter (`LR` / `TB`).

---

## 7. Testing

```bash
cd backend && pytest
```

35 tests cover: DAG validation, pipeline run, LLM mock runner, agent factory, RAG chunking, run persistence, workflow validate API.

---

## 8. Environment variables

| Variable | Purpose |
|----------|---------|
| `OPENAI_API_KEY` | Real LLM + tool calls |
| `OPENAI_MODEL` | Default model name |
| `AGENT_SYSTEM_PROMPT` | Override backend agent instructions (optional) |
| `DEFAULT_KNOWLEDGE_TOP_K` | Chunks returned per tool call (default 4) |

Copy `backend/.env.example` → `backend/.env`. Never commit `.env`.

---

## 9. Assignment parts mapping

| Part | Where it lives |
|------|----------------|
| 1 — Node abstraction | `nodeRegistry`, `BaseNode`, `ConfigDrivenNode`, custom nodes |
| 2 — Styling | Tailwind, shadcn, `index.css`, sidebars |
| 3 — Text node | `textNode.js`, `VariableTextarea`, `textNodeConnections.js` |
| 4 — Backend integration | `pipeline_router`, parse/run endpoints, DAG check |
| 5 — Pydantic AI | `agent_factory`, `llm_runner`, `execution_value` |
| Extras | Workflow JSON, run history, utilities, KB RAG, gallery, auto-layout |

See [video-script.md](video-script.md) for a recorded walkthrough outline.
