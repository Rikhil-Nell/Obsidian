# VectorShift Assessment — Documentation

Study guides and reference for the technical assessment.

## Start here

| Doc | What it covers |
|-----|----------------|
| **[Architecture & design](architecture-and-design.md)** | How the whole codebase works, data flow, design trade-offs |
| **[Video script](video-script.md)** | Step-by-step demo recording outline |

## Part-by-part guides

| Part | Topic | Status |
|------|--------|--------|
| [Part 1](part-1-node-abstraction.md) | Node abstraction (BaseNode + registry) | Done |
| [Part 2](part-2-styling.md) | Styling (Tailwind + shadcn) | Done |
| [Part 3](part-3-text-node-logic.md) | Text node auto-resize + `{{variable}}` handles | Done |
| [Part 4](part-4-backend-integration.md) | Backend integration (FastAPI + DAG) | Done |
| [Part 5](part-5-pydantic-ai-executor.md) | Pydantic AI executor (see architecture doc for latest LLM/KB behavior) | Done |
| [Part 6](part-6-extras.md) | Edge delete, dialogs, nodrag, persistence | Done |

**Note:** Parts 1–6 were written during incremental work. For the **current** RAG model (Knowledge Base + agent tool), backend-owned system prompt, auto-layout, and example gallery, prefer **[architecture-and-design.md](architecture-and-design.md)**.

## Suggested reading order

1. [Architecture & design](architecture-and-design.md) — big picture
2. [Part 1](part-1-node-abstraction.md) — node patterns
3. [Part 5](part-5-pydantic-ai-executor.md) + architecture §4.4 — execution & RAG
4. [Video script](video-script.md) — before recording
