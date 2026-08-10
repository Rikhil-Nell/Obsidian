# Demo Video Script

**Target length:** 8–12 minutes  
**Goal:** Show all assignment parts + standout extras (agent RAG, persistence, workflow JSON, auto-layout).

**Before recording:**
- Backend running: `cd backend && uvicorn main:app --reload`
- Frontend running: `cd frontend && npm start`
- `OPENAI_API_KEY` set in `backend/.env` (for live RAG demo)
- Optional: clear run history for a clean slate

---

## 1. Intro (30 sec)

> “This is my VectorShift pipeline builder — a visual workflow editor like n8n, with a FastAPI backend that validates graphs and runs pipelines using Pydantic AI agents.”

Show full UI: left library, canvas, right run history, header import/export.

---

## 2. Part 1 & 2 — Nodes and UI (1 min)

> “Nodes are config-driven from a central registry — handles, fields, and icons in one place. Input, Text, and Knowledge Base have custom UIs where needed.”

- Drag **Input**, **LLM**, **Output** onto canvas
- Point out dark theme, consistent node width, connection handles

---

## 3. Part 3 — Text node (1 min)

> “The Text node auto-resizes and creates handles when you type double-brace variables.”

- Drag **Text** between Input and LLM
- Wire Input → Text (show `{{input_1}}` auto-inserted)
- Type a short template with a variable
- Show wired variable chip when connected

---

## 4. Agent RAG — Knowledge Base (2 min)

> “RAG here is agent-driven, not a fixed retrieve step. The user talks to the LLM; the agent chooses when to search uploaded docs.”

- Sidebar → **Examples** → click **RAG agent** (or build manually)
- Point out: KB **above** LLM (top connector), Input on the left
- Open KB node — show uploaded doc / replace file
- **Auto-arrange** button (bottom-left grid icon) if layout is tight
- Click **Run pipeline**

**While run executes, narrate run history:**
- Step: KB indexed N chunks
- Step: LLM used knowledge tool
- Final answer grounded in document

> “System instructions and the search tool live in the backend — the LLM node only has model and optional additional context.”

---

## 5. Part 4 & 5 — Backend + Pydantic AI (1 min)

> “The backend topologically sorts the graph, runs each node, and builds a Pydantic AI agent per LLM node.”

- Open `http://localhost:8000/docs` briefly — show `POST /pipelines/run`, `GET /runs`
- Terminal: `cd backend && pytest` — show tests passing (quick cut)

Optional one-liner:
> “Without an API key, TestModel returns deterministic mock output; tools need a real key.”

---

## 6. Utility nodes (1 min)

> “Utility nodes support branching and combining flows.”

- Load **Parallel LLMs + merge** example from sidebar
- Auto-arrange
- Run — show two LLM outputs merged
- Mention: condition, split, transform, delay also implemented

---

## 7. Workflow JSON import/export (1 min)

> “Workflows serialize to vectorshift.workflow.v1 — portable like n8n.”

- Header → **Export** — show JSON, copy or download
- Clear canvas or refresh
- **Import** — paste JSON — workflow restores
- Auto-layout + fit view on load

---

## 8. Run history persistence (1 min)

> “Every run is stored in SQLite under backend/data — survives browser reload.”

- Reload browser
- Right panel → run history still listed
- Expand a run — show steps; click again to **collapse** (demo toggle)
- Select run — workflow restores on canvas
- Mention: include `backend/data/` in submission zip

---

## 9. Wrap-up (30 sec)

> “Summary: config-driven nodes, Text node variables, FastAPI DAG execution, Pydantic AI agents with optional knowledge search, persisted runs, workflow JSON, and dagre auto-layout. Thanks for watching.”

**Checklist for reviewer:**
- [ ] All 5 parts demonstrated
- [ ] RAG agent tool (not pipeline retrieve)
- [ ] pytest green
- [ ] Export/import shown
- [ ] Run history after reload

---

## B-roll / optional shots

- `docs/architecture-and-design.md` — quick scroll for depth
- `nodeRegistry.js` — registry pattern
- `agent_factory.py` — `search_knowledge_base` tool
- Mini-map and zoom controls

---

## Troubleshooting on camera

| Issue | What to say / do |
|-------|------------------|
| LLM asks for clarification | Re-run; ensure API key set; KB wired to top port |
| Mock message about API key | Show `.env` has key; restart uvicorn |
| Nodes overlap | Click auto-arrange |
| Old workflow has RAG node | Import error — use Examples or remove `rag` type |
