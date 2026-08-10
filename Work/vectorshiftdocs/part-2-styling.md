# Part 2: Styling

This document explains Part 2 of the VectorShift assessment: restyling the pipeline builder with a unified dark theme, inspired by your Stitch mockup but implemented with Tailwind CSS and shadcn/ui.

Written for someone with **no frontend background**.

---

## What the assignment asked for

From the assessment PDF:

> Style the various components into an appealing, unified design. You can use VectorShift's existing styles as inspiration or create your own. You can use whatever React packages/libraries you would like.

Part 2 is **visual only** — all behavior from Parts 1, 3, and 4 must keep working.

---

## What we took from your Stitch mockup

| Kept from Stitch | Skipped from Stitch |
|------------------|---------------------|
| Left **Node Library** sidebar | Neomorphism (`neo-raised` shadows) |
| Dark navy background `#0b1326` | Inter font |
| Lavender accent `#c0c1ff` | Animated glowing connection lines |
| Dot-grid canvas | Docs / Logout / Settings / New Node |
| Node cards with header + body | "Silk AI Pipeline" branding |

We used **ui-design-brain** and **component.gallery** to avoid generic AI aesthetics: border + subtle shadow instead of dual shadows, verb-first button label, Card pattern for nodes.

---

## Tech stack added

| Tool | What it is (backend analogy) |
|------|-------------------------------|
| **Tailwind CSS** | Utility CSS classes (`p-4`, `bg-card`) — like inline styles but standardized |
| **shadcn/ui** | Copy-paste React components (Button, Card, Input) — like your `BaseNode` pattern but for forms |
| **CRACO** | Config overlay for Create React App — enables Tailwind + `@/` imports without ejecting |
| **lucide-react** | Icon library for sidebar node types |
| **Radix UI** | Accessible primitives under shadcn Select, Label, ScrollArea |

---

## New layout

```
┌─────────────────────────────────────────────────────┐
│ Pipeline Builder                                     │  PipelineHeader
├──────────┬──────────────────────────────────────────┤
│ Node     │                                          │
│ Library  │     React Flow canvas (dot grid)         │
│ (9 items)│                                          │
├──────────┴──────────────────────────────────────────┤
│              [ Submit pipeline ]                     │  SubmitButton
└─────────────────────────────────────────────────────┘
```

**Before:** horizontal toolbar at top, plain black-bordered nodes.

**After:** Stitch-style sidebar + full-height canvas + styled submit bar.

---

## Color tokens

Defined in [`frontend/src/index.css`](../frontend/src/index.css) as CSS variables (Stitch "Silk Dark" simplified):

| Token | Role |
|-------|------|
| `--background` | App + canvas base (dark navy) |
| `--card` | Sidebar, nodes, submit bar |
| `--foreground` | Primary text |
| `--muted-foreground` | Labels, sidebar hint text |
| `--primary` | Lavender accent — handles, icons, button |
| `--border` | Dividers and node borders |

**Fonts:** Manrope (headings) + DM Sans (body) via Google Fonts in `public/index.html`.

---

## Key files

| File | Role |
|------|------|
| `craco.config.js` | `@/` → `src/` alias |
| `tailwind.config.js` | Maps CSS variables to Tailwind classes |
| `src/index.css` | Theme + React Flow handle/edge overrides |
| `src/components/PipelineHeader.js` | App title bar |
| `src/components/NodeLibrarySidebar.js` | Vertical draggable node list |
| `src/draggableNode.js` | Sidebar item with lucide icon |
| `src/components/ui/*` | shadcn Button, Card, Input, Select, etc. |
| `src/nodes/BaseNode.js` | Now uses shadcn `Card` |
| `src/nodes/fields.js` | shadcn form controls |
| `src/ui.js` | Full-height canvas with dot grid |
| `src/submit.js` | shadcn `Button` — logic unchanged |

---

## BaseNode redesign

Part 1's `BaseNode` now renders:

```
Card (rounded, border, shadow-sm)
  CardHeader → title
  CardContent → fields
  Handles → styled via global CSS (.react-flow__handle)
```

Part 3 Text node still passes dynamic `style` width/height — the Card grows with content.

---

## React Flow theming

In `index.css`:

- **Handles:** 10px lavender circles with card-colored border
- **Edges:** lavender stroke, 2px
- **Controls / MiniMap:** dark card background, border

Canvas background uses `.canvas-dot-grid` (radial dots every 24px).

---

## How to run

```bash
cd frontend
npm install
npm start
```

Scripts now use **CRACO** (`craco start` / `craco build`) instead of raw `react-scripts`.

Backend for Submit (Part 4):

```bash
cd backend
uvicorn main:app --reload
```

---

## Verify Part 2 did not break other parts

1. Drag all 9 nodes from sidebar onto canvas
2. Connect nodes (handles visible on lavender dots)
3. Text node: type `{{input}}` → left handle appears; node resizes (Part 3)
4. Submit → alert with node/edge/DAG counts (Part 4)
5. `npm run build` succeeds

---

## Changing the look later

**One color:** edit `--primary` in `src/index.css`.

**Sidebar width:** change `w-64` in `NodeLibrarySidebar.js`.

**Node min width:** change `min-w-[220px]` in `BaseNode.js`.

If you get a refined design from Stitch again, apply tokens in `index.css` first — most of the app follows automatically.

---

## Screen recording talking points (~1–2 min)

1. **Layout:** Sidebar node library + canvas — scales to 9 node types.
2. **Design system:** Stitch colors + shadcn components (not raw HTML).
3. **Restraint:** No neomorphism; border + shadow; one accent color.
4. **Integration:** Parts 1–4 still work on styled UI.
5. **Stack:** Tailwind + shadcn on CRA via CRACO.

---

## Related docs

- [Part 1: Node Abstraction](part-1-node-abstraction.md)
- [Part 3: Text Node Logic](part-3-text-node-logic.md)
- [Part 4: Backend Integration](part-4-backend-integration.md)
- [Docs index](README.md)
