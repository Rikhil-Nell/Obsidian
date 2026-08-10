# Part 6 — Extras (brownie points)

Features beyond the four required assessment parts.

## Canvas interaction polish

React Flow classes on node internals:

| Class | Effect |
|-------|--------|
| `nodrag` | Dragging inside the control does not move the node |
| `nowheel` | Scroll wheel does not zoom the canvas |
| `nopan` | Pointer does not pan the canvas |

Applied in `fields.js`, `BaseNode` `CardContent`, and the Input image upload block. Drag nodes by the **header** (`cursor-grab`).

## Deletable edges

`frontend/src/edges/DeletableEdge.js`:

- Invisible wide stroke along the wire for hover hit-testing
- **✕** button at the edge midpoint (via `EdgeLabelRenderer`)
- Click removes the edge from Zustand (`removeEdge`)
- New connections use `type: 'deletable'`

## Submit / Run UX

`frontend/src/submit.js`:

- **Submit pipeline** → `POST /pipelines/parse` → shadcn Dialog (nodes, edges, DAG badge)
- **Run pipeline** → `POST /pipelines/run` → execution trace dialog
- Loading spinners, disabled when canvas is empty
- Separate error state per dialog

## Field persistence

Node inputs call `updateNodeField(id, field, value)` so `nodes[].data` is populated when submitting or running. Required for the executor to read Input values, Text templates, and LLM config.

## File map

| Area | Path |
|------|------|
| Deletable edge | `frontend/src/edges/DeletableEdge.js` |
| Store | `frontend/src/store.js` |
| Submit / Run bar | `frontend/src/submit.js` |
| Dialog UI | `frontend/src/components/ui/dialog.js` |
| Pydantic AI executor | `backend/app/domains/pipelines/service/` |

See [Part 5](part-5-pydantic-ai-executor.md) for the LLM execution details.
