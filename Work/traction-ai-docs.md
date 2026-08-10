# Nebular Blazar — Project Documentation

> A platform that gives founders a **web presence for their ideas** before they build anything — shareable, discoverable, and credible.

---

## Table of Contents

- [The Idea](#the-idea)
- [Core Value Proposition](#core-value-proposition)
- [User Flows](#user-flows)
- [Visitor Flows](#visitor-flows)
- [Route Map & Access Control](#route-map--access-control)
- [Architecture](#architecture)
- [Data Model](#data-model)
- [AI Generation Pipeline](#ai-generation-pipeline)
- [The Deck: How It Works](#the-deck-how-it-works)
- [Summary & llm.txt: How They Work](#summary--llmtxt-how-they-work)
- [Authentication](#authentication)
- [Editor UI: Toggle Between Chat & Preview](#editor-ui-toggle-between-chat--preview)
- [Tech Stack](#tech-stack)
- [URL Structure](#url-structure)
- [MVP Scope](#mvp-scope)

---

## The Idea

High-velocity founders often pitch 5-6 different ideas every financial year. For each, they need pitch decks, go-to-market strategies, PPTs, PDFs — a cumbersome process. When sharing with a VC, they send 7 scattered docs with nothing catchy about them.

**Nebular Blazar** solves this by giving founders a platform where they can:

1. **Ideate** — dump a messy idea, get it structured with AI assistance, receive honest readiness feedback
2. **Build** — AI generates a stunning, unique pitch deck as a live web page (not boring slides)
3. **Share** — one link, one page, everything an investor needs

The platform also serves as a **discoverable landing page before the product exists**. Through `llm.txt` (machine-readable startup spec), AI agents can discover and evaluate startups — enabling cold inbound traction without the founder having to share first.

### What Makes This Different

| Traditional | Nebular Blazar |
|-------------|---------------|
| Google Slides / Notion / PDF | Live web page with animations, video, interactivity |
| Pick a template → fill text (everyone looks the same) | AI generates **unique** HTML/CSS per project |
| Share 7 attachments | Share one link |
| Not discoverable | Discoverable via `llm.txt`, SEO, meta tags |
| No intelligence layer | AI-generated investor summary, readiness flags |
| Static document | Living page — update anytime, no re-sending |

---

## Core Value Proposition

**For Founders:**
> "I look legit even before I've built anything. One link, everything about my startup, and it looks like a $5K Framer site."

**For Investors/Visitors:**
> "I can understand this startup in 2 minutes — deck, summary, risks, all on one page."

**For AI Agents:**
> "I can discover and parse startup data via `llm.txt` without human intervention."

---

## User Flows

### Flow 1: New Founder Onboarding

```
1. Founder lands on nebularblazar.com
2. Signs up via Google Auth
3. Lands on dashboard — "Create your first project"
4. Enters project name + slug (e.g., "Kinesys" / "kinesys")
5. Redirected to /@username/project/ideate (chat mode)
```

### Flow 2: Ideation (Chat Mode)

```
1. Founder is at /@rikhil/kinesys/ideate
2. Chat interface — they dump their messy idea:
   "I'm building an AI platform for SMBs..."
3. AI responds with:
   - Structured breakdown (problem, solution, market, ICP, etc.)
   - Readiness flags (honest gaps):
     ⚠ "No distribution strategy identified"
     ⚠ "Competitive space — what's your wedge?"
4. Founder refines through conversation:
   "Actually our wedge is the Shopify integration..."
5. AI updates structured data with each iteration
6. When satisfied → clicks "Generate my deck →"
```

### Flow 3: Deck Generation

```
1. Founder optionally sets style preferences:
   - Theme: dark / light
   - Aesthetic: glassmorphism / minimal / bold
   - Vibe: "premium and confident"
2. AI generates a COMPLETE, UNIQUE HTML/CSS/JS pitch deck
   - Not a template fill — entirely bespoke layout, animations, colors
   - Stored as a single HTML string in the database
3. Founder previews the deck (toggle to Preview mode)
4. Can regenerate: "make it more minimal" / "add data visualizations"
5. Can edit structured data and regenerate
```

### Flow 4: Publish

```
1. Founder reviews:
   - Deck preview
   - AI-generated investor summary (auto-generated from structured data)
   - llm.txt preview (auto-generated)
2. Clicks "Publish"
3. Project status → published
4. Shareable link is live: nebularblazar.com/@rikhil/kinesys
5. llm.txt is live: nebularblazar.com/@rikhil/kinesys/llm.txt
```

### Flow 5: Editing After Publish

```
1. Founder visits their project URL (logged in)
2. Sees editor view (not public view)
3. Toggle between Chat (ideate) and Preview modes
4. Can update idea, regenerate deck, regenerate summary
5. Changes go live immediately (no redeploy)
```

### Flow 6: Multiple Projects

```
1. Founder can create multiple projects from dashboard
2. Each project has its own:
   - Slug (/@rikhil/kinesys, /@rikhil/datavault, etc.)
   - Ideation history
   - Generated deck
   - Summary & llm.txt
3. Dashboard shows all projects with status (draft/published)
```

---

## Visitor Flows

### Flow 1: Investor Opens Shared Link

```
1. Investor receives link: nebularblazar.com/@rikhil/kinesys
2. Page loads with TWO sections:

   SECTION 1 (above the fold — full screen):
   ┌──────────────────────────────────────┐
   │  PITCH DECK (iframe)                 │
   │  Unique AI-generated slides          │
   │  Full screen, keyboard navigation    │
   │  Arrow keys, dots, fullscreen btn    │
   │  Last slide has CTA: "Read more ↓"  │
   └──────────────────────────────────────┘

   ── user scrolls past last slide ──

   SECTION 2 (below the fold):
   ┌──────────────────────────────────────┐
   │  INVESTOR SUMMARY                    │
   │  Verdict: [Interesting]              │
   │  One-liner summary                   │
   │  Strengths (bullet list)             │
   │  Concerns (bullet list)              │
   │  Key question for the founder        │
   │                                      │
   │  FULL BREAKDOWN                      │
   │  Problem | Solution | Market |       │
   │  Business Model | Traction           │
   │  (expandable sections)               │
   │                                      │
   │  [📄 View llm.txt]                  │
   │  Footer: "Powered by Nebular Blazar" │
   └──────────────────────────────────────┘

3. No login required. No navigation. One page, full story.
```

### Flow 2: AI Agent Discovers Startup

```
1. AI agent crawls nebularblazar.com/@rikhil/kinesys/llm.txt
2. Receives structured plain text:

   # Kinesys
   ## Overview
   AI-powered analytics for Shopify store owners.
   ## Problem
   Small businesses can't afford data teams.
   ## Solution
   ChatGPT-like interface for querying business data.
   ## Market Size
   $12B SMB analytics market
   ## Risks
   - Crowded space
   - $49/mo may be high for SMBs
   ...

3. Agent can parse, evaluate, compare with other startups
4. If interested → contact info is in the spec
```

### Flow 3: Unauthenticated Visit to Draft Project

```
1. Visitor goes to /@rikhil/kinesys
2. Project status = "draft"
3. → 404 page (unpublished projects are not visible)
```

### Flow 4: Unauthenticated Visit to /ideate

```
1. Visitor goes to /@rikhil/kinesys/ideate
2. → Redirect to /@rikhil/kinesys (or 404)
3. Ideation workspace is owner-only
```

---

## Route Map & Access Control

| Route | Owner (logged in) | Visitor (anyone else) |
|-------|-------------------|-----------------------|
| `/@user/project` | **Editor view** — toolbar, deck preview, structured data editor, summary preview, publish controls | **Public view** — deck (iframe) + summary below (only if published) |
| `/@user/project/ideate` | **Chat workspace** — ideation chat with AI, readiness flags | **403/404** — owner only |
| `/@user/project/deck` | Raw HTML (iframe source) | Raw HTML (iframe source) |
| `/@user/project/llm.txt` | Plain text preview | Plain text (for AI agents) |
| `/dashboard` | List of all projects, create new | **Redirect to login** |
| `/` | Landing page | Landing page |

### How We Distinguish Owner vs Visitor

```python
@router.get("/@{username}/{slug}")
async def project_page(username, slug, request):
    project = db.get_project(username, slug)
    current_user = get_optional_user(request)  # None if not logged in

    is_owner = current_user and current_user.id == project.user_id

    if is_owner:
        return render("editor.html", project)      # editor with toolbar
    else:
        if project.status != "published":
            raise HTTPException(404)                # hide drafts
        return render("public_view.html", project)  # deck + summary
```

- `get_optional_user()` checks for a valid JWT in cookies/headers
- Returns `None` if not logged in (no 401 — just shows public view)
- If logged in AND owns the project → editor view
- If logged in but doesn't own it → public view (same as visitor)

---

## Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                        FASTAPI SERVER                       │
│                                                             │
│  ┌─── Public Routes ────┐  ┌─── API Routes ──────────────┐ │
│  │                       │  │                              │ │
│  │  /@user/project       │  │  POST /api/projects          │ │
│  │  /@user/project/deck  │  │  POST /api/projects/:id/     │ │
│  │  /@user/project/      │  │       ideate                 │ │
│  │       llm.txt         │  │  POST /api/projects/:id/     │ │
│  │                       │  │       generate-deck          │ │
│  │  / (landing page)     │  │  POST /api/projects/:id/     │ │
│  │                       │  │       publish                │ │
│  └───────────────────────┘  │  GET  /api/auth/google       │ │
│                              │  GET  /api/auth/callback     │ │
│                              └──────────────────────────────┘ │
│                                                             │
│  ┌─── Templates (Jinja2) ───┐  ┌─── Services ────────────┐ │
│  │                           │  │                          │ │
│  │  public_view.html         │  │  ai_service.py           │ │
│  │  editor.html              │  │  (idea structuring)      │ │
│  │  ideate.html              │  │                          │ │
│  │  landing.html             │  │  deck_generator.py       │ │
│  │  dashboard.html           │  │  (HTML generation)       │ │
│  │                           │  │                          │ │
│  └───────────────────────────┘  │  summary_generator.py    │ │
│                                  │  (investor summary +     │ │
│                                  │   llm.txt generation)    │ │
│                                  └──────────────────────────┘ │
│                                                             │
│  ┌─── Database (PostgreSQL) ────────────────────────────┐   │
│  │  users | projects                                     │   │
│  └───────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

### Key Architectural Decisions

1. **Monolith** — everything is one FastAPI app (API + page serving + templates). No separate frontend deployment.
2. **Server-side rendering** — Jinja2 templates are rendered on the server with data from the DB. No API calls on page load for public views.
3. **AI-generated decks served via iframe** — the unique HTML is isolated from the main app for security and styling independence.
4. **No React/Next.js** — keeping it simple. Vanilla HTML/CSS/JS for the frontend, Jinja2 for templating.

---

## Data Model

```sql
-- Users table
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    google_id VARCHAR UNIQUE NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    username VARCHAR UNIQUE NOT NULL,
    display_name VARCHAR,
    avatar_url VARCHAR,
    created_at TIMESTAMP DEFAULT NOW()
);

-- Projects table
CREATE TABLE projects (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    slug VARCHAR NOT NULL,
    name VARCHAR NOT NULL,

    -- Stage 1: Ideation
    idea_raw TEXT,                    -- messy founder dump
    structured_data JSONB,           -- AI-parsed: problem, solution, market, etc.
    readiness_flags JSONB,           -- list of honest gap flags
    chat_history JSONB,              -- ideation conversation log

    -- Stage 2: Generated deck
    deck_html TEXT,                  -- THE BIG ONE: full HTML/CSS/JS string
    deck_style_config JSONB,         -- style preferences used for generation

    -- Stage 3: Summary & discoverability
    investor_summary JSONB,          -- verdict, strengths, concerns, etc.
    llm_txt TEXT,                    -- machine-readable startup spec

    -- Meta
    status VARCHAR DEFAULT 'draft',  -- draft | published
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),

    UNIQUE(user_id, slug)
);
```

### What Each Column Stores

| Column | Type | Example | Purpose |
|--------|------|---------|---------|
| `idea_raw` | TEXT | "I'm building an AI platform that..." | Raw founder input, preserved for reference |
| `structured_data` | JSONB | `{"problem": "...", "solution": "...", ...}` | AI-parsed idea, used for deck gen + summary |
| `readiness_flags` | JSONB | `["No distribution strategy", ...]` | Honest gaps shown to founder during ideation |
| `chat_history` | JSONB | `[{"role": "user", "content": "..."}, ...]` | Full ideation conversation for context |
| `deck_html` | TEXT | `"<!DOCTYPE html><html>..."` (10-20KB) | Complete self-contained HTML page for the deck |
| `deck_style_config` | JSONB | `{"theme": "dark", "aesthetic": "glass"}` | Preferences used, for regeneration |
| `investor_summary` | JSONB | `{"verdict": "Interesting", "risks": [...]}` | Structured summary shown to visitors |
| `llm_txt` | TEXT | `"# Kinesys\n## Overview\n..."` | Plain text spec for AI agents |

---

## AI Generation Pipeline

### Step 1: Idea Structuring

```
Input:  Raw text from founder (messy, unstructured)
Model:  GPT-4o (or Claude)
Output: Structured JSON + readiness flags
Temp:   0.3 (want accuracy, not creativity)

Prompt focus:
- Extract: problem, solution, target user, market, business model, traction, competitors
- Generate: honest readiness flags (specific, not generic)
- Be direct: "No distribution channel" not "Consider your go-to-market"
```

### Step 2: Deck HTML Generation

```
Input:  Structured data + style preferences
Model:  GPT-4o (or Claude)
Output: Single self-contained HTML string (inline CSS + inline JS)
Temp:   0.9 (want creativity and uniqueness)

Prompt focus:
- 5-7 full-screen slides with content from structured data
- Keyboard navigation (arrows, F for fullscreen)
- Smooth transitions, animations, responsive clamp() sizing
- UNIQUE design every time — vary layouts, colors, animations
- Pure HTML/CSS/JS — no React, no frameworks, no external deps (except Google Fonts)
```

### Step 3: Summary Generation

```
Input:  Structured data
Model:  GPT-4o (or Claude)
Output: JSON with verdict, strengths, concerns, key question
Temp:   0.4 (honest, not creative)

Prompt focus:
- Be honest, not promotional
- Include specific concerns, not generic ones
- Verdict: Too Early | Interesting | Worth a Call | Strong
```

### Step 4: llm.txt Generation

```
Input:  Structured data + summary
Output: Plain text in a standardized format
Method: Template-based (no AI needed — just string formatting)
```

---

## The Deck: How It Works

### Why One HTML String?

Every founder's deck must look **unique** — different layouts, animations, color palettes, typography. If we used templates, everyone would look the same (glorified Google Slides).

Instead, the AI generates a **complete, self-contained HTML file** with:
- All CSS in `<style>` tags (inline)
- All JS in `<script>` tags (inline)
- No external dependencies except Google Fonts and video URLs
- Full slide navigation, transitions, responsive design

This HTML string is stored in the `deck_html` TEXT column in PostgreSQL.

### How It's Served

The deck HTML is loaded inside an **iframe** on the main project page:

```html
<!-- In your public_view.html template -->
<div class="deck-container">
    <iframe
        src="/@rikhil/kinesys/deck"
        sandbox="allow-scripts"
        style="width: 100%; height: 100vh; border: none;">
    </iframe>
</div>
```

The `/@user/project/deck` endpoint returns the raw HTML:

```python
@router.get("/@{username}/{slug}/deck")
async def serve_deck(username, slug):
    project = db.get_project(username, slug)
    return HTMLResponse(content=project.deck_html)
```

### Security

- `sandbox="allow-scripts"` — allows JS (for navigation) but prevents:
  - Accessing parent page cookies/storage
  - Making API calls on behalf of the user
  - Navigating the parent page
- Same approach as CodePen, JSFiddle, Replit

### No Redeployment

The HTML is data, not code. New project → new row in DB → new HTML string. The deployed app doesn't change. It just serves whatever HTML it finds in the database for that project.

---

## Summary & llm.txt: How They Work

### Summary Section

The investor summary is **NOT** part of the AI-generated deck HTML. It's a separate section rendered by Jinja2.

```
Page structure:
┌──────────────────────────────────┐
│  YOUR APP'S TEMPLATE             │ ← deployed once (Jinja2)
│                                  │
│  ┌──────────────────────────┐   │
│  │  IFRAME (deck HTML)      │   │ ← unique per project (from DB)
│  │  AI-generated, unique    │   │
│  └──────────────────────────┘   │
│                                  │
│  ── scroll ──                    │
│                                  │
│  SUMMARY (Jinja2 template)      │ ← consistent styling (your branding)
│  {{ project.investor_summary }} │ ← data from DB (different per project)
│                                  │
│  [View llm.txt] link            │
└──────────────────────────────────┘
```

- The **template** (layout, CSS, structure) is your app code → deployed once
- The **data** (verdict, risks, summary text) comes from the database → changes per project
- Jinja2 fills in the data **on the server** before sending HTML to the browser
- **No API call on scroll** — the data is already baked into the HTML when the page loads

### llm.txt

`llm.txt` is **not a file on disk**. It's a route that returns plain text from the database:

```python
@router.get("/@{username}/{slug}/llm.txt")
async def serve_llm_txt(username, slug):
    project = db.get_project(username, slug)
    return PlainTextResponse(content=project.llm_txt)
```

The URL looks like a file (`/llm.txt`) but it's generated dynamically. Same pattern as `robots.txt` or `sitemap.xml` on most websites.

---

## Authentication

### Google OAuth 2.0 (Primary Auth Method)

```
1. User clicks "Sign in with Google"
2. Redirected to Google's OAuth consent screen
3. Google redirects back with auth code
4. Backend exchanges code for Google user info (email, name, avatar)
5. Create user in DB (if new) or find existing
6. Issue JWT access token + refresh token
7. Set as HTTP-only cookies
```

### Auth Flow (Endpoints)

```
GET  /api/auth/google        → redirect to Google OAuth
GET  /api/auth/callback      → handle Google redirect, issue tokens
POST /api/auth/refresh       → refresh access token
POST /api/auth/logout        → clear tokens
```

### How Auth Affects Page Rendering

```python
def get_optional_user(request):
    """Returns user if valid JWT exists, None otherwise. Never raises."""
    token = request.cookies.get("access_token")
    if not token:
        return None
    try:
        payload = jwt.decode(token, SECRET_KEY)
        return db.get_user(payload["user_id"])
    except:
        return None
```

- Public routes use `get_optional_user()` — returns None for visitors, user object for logged-in users
- Protected routes (ideate, dashboard, API) use `require_auth()` — returns 401 if not logged in
- The same URL (`/@user/project`) renders differently based on whether the viewer is the owner

---

## Editor UI: Toggle Between Chat & Preview

When the **owner** visits their project, they see an editor view with a toggle switch at the top:

```
┌────────────────────────────────────────────────────────┐
│  ┌─ TOP BAR ──────────────────────────────────────┐   │
│  │                                                 │   │
│  │  Project: Kinesys          Status: Draft        │   │
│  │                                                 │   │
│  │  ┌─────────────────────────────┐                │   │
│  │  │  💬 Chat  │  👁 Preview    │  ← TOGGLE      │   │
│  │  └─────────────────────────────┘                │   │
│  │                                                 │   │
│  │  [🎨 Regenerate Deck]  [🚀 Publish]            │   │
│  │                                                 │   │
│  └─────────────────────────────────────────────────┘   │
│                                                        │
│  ┌─────────── Content Area ────────────────────────┐   │
│  │                                                  │   │
│  │  WHEN TOGGLE = CHAT:                             │   │
│  │  Shows /@user/project/ideate content             │   │
│  │  - Chat interface with AI                        │   │
│  │  - Structured data sidebar                       │   │
│  │  - Readiness flags                               │   │
│  │                                                  │   │
│  │  WHEN TOGGLE = PREVIEW:                          │   │
│  │  Shows what visitors will see                    │   │
│  │  - Deck preview (iframe)                         │   │
│  │  - Summary preview below                         │   │
│  │  - llm.txt preview                               │   │
│  │                                                  │   │
│  └──────────────────────────────────────────────────┘   │
└────────────────────────────────────────────────────────┘
```

### Implementation

The toggle is client-side JavaScript — no page reload needed:

```javascript
const chatView = document.getElementById('chat-view');
const previewView = document.getElementById('preview-view');
const toggleBtns = document.querySelectorAll('.toggle-btn');

function switchMode(mode) {
    if (mode === 'chat') {
        chatView.style.display = 'block';
        previewView.style.display = 'none';
    } else {
        chatView.style.display = 'none';
        previewView.style.display = 'block';
    }
}
```

Both views are loaded in the same `editor.html` template — the toggle just shows/hides them.

### Route Behavior

| URL visited | What happens |
|-------------|-------------|
| `/@rikhil/kinesys` (owner) | Editor view loads with toggle defaulting to **Preview** |
| `/@rikhil/kinesys/ideate` (owner) | Editor view loads with toggle defaulting to **Chat** |
| `/@rikhil/kinesys` (visitor) | Public view (no toggle, no editor) |
| `/@rikhil/kinesys/ideate` (visitor) | Redirect to `/@rikhil/kinesys` or 404 |

Both `/@user/project` and `/@user/project/ideate` serve the same `editor.html` template for the owner. The route just determines which toggle state is active on load.

---

## Tech Stack

| Layer | Technology | Justification |
|-------|-----------|---------------|
| **Backend** | FastAPI (Python) | Already proficient, handles API + page serving |
| **Database** | PostgreSQL | Stores users, projects, AI-generated content |
| **ORM** | SQLAlchemy + Alembic | Models + migrations |
| **Templates** | Jinja2 | Server-side rendering for all pages |
| **Frontend** | Vanilla HTML/CSS/JS | No React needed, keep it simple |
| **AI** | OpenAI GPT-4o / Claude API | Idea structuring, deck generation, summaries |
| **Auth** | Google OAuth 2.0 + JWT | Simple, trusted, no password management |
| **Hosting** | VPS (Railway / Fly.io / DigitalOcean) | One container, everything included |
| **SSL** | Let's Encrypt + Certbot | Free, supports wildcards if needed later |
| **Domain** | Any registrar | ~$10-15/year |

### Why Not React / Next.js?

- Adds build complexity, separate deployment
- Jinja2 + vanilla JS handles everything needed
- Faster development for a backend-focused developer
- Can always migrate to Next.js later if needed

---

## URL Structure

```
nebularblazar.com/                                → Landing page
nebularblazar.com/dashboard                       → User's project list (auth required)
nebularblazar.com/@rikhil                          → User profile (future)
nebularblazar.com/@rikhil/kinesys                  → Project page (editor or public)
nebularblazar.com/@rikhil/kinesys/ideate           → Ideation chat (owner only)
nebularblazar.com/@rikhil/kinesys/deck             → Raw deck HTML (iframe source)
nebularblazar.com/@rikhil/kinesys/llm.txt          → Machine-readable spec
nebularblazar.com/api/auth/google                  → Google OAuth redirect
nebularblazar.com/api/auth/callback                → OAuth callback
nebularblazar.com/api/projects                     → CRUD projects (auth required)
nebularblazar.com/api/projects/:id/ideate          → Ideation API
nebularblazar.com/api/projects/:id/generate-deck   → Deck generation API
nebularblazar.com/api/projects/:id/publish         → Publish project
```

### Path-Based (MVP) → Subdomain (Future)

**Now:** `nebularblazar.com/@rikhil/kinesys`
**Later:** `kinesys.nebularblazar.com` (wildcard DNS + Let's Encrypt wildcard cert)

---

## MVP Scope

### Must Have (Hackathon / v1)

- [ ] Google Auth (sign up / login)
- [ ] Create project (name + slug)
- [ ] Ideation chat (dump idea → AI structures it → readiness flags)
- [ ] Deck generation (AI generates unique HTML from structured data)
- [ ] Public project page (deck iframe + summary section)
- [ ] llm.txt endpoint
- [ ] Publish toggle (draft → published)
- [ ] Editor with Chat/Preview toggle

### Nice to Have (v2)

- [ ] Multiple deck regenerations with different styles
- [ ] User profile page (`/@username`)
- [ ] Custom domain / subdomain support
- [ ] Export to PDF
- [ ] Analytics (who visited your page)
- [ ] Deck versioning (history of generations)

### Cut (Not MVP)

- ~~RAG chatbot for visitors~~ (redundant — summary does the job)
- ~~Full drag-and-drop editor~~ (AI is the editor)
- ~~Investor-side special mode~~ (v2+)
- ~~Stage-detection readiness agent~~ (simple flags are enough)
- ~~Separate docs/GTM generator~~ (the deck + summary covers this)
