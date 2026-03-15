
```
# API Request & Response Schemas

> Derived from the Next.js frontend implementation. Hybrid approach: REST endpoints per resource + composite endpoints for heavy pages.

---

## Table of Contents

- [Conventions](#conventions)
- [Data Models](#data-models)
- [Authentication](#1-authentication)
- [Projects](#2-projects)
- [Documents](#3-documents)
- [Chat Messages](#4-chat-messages)
- [Deck Generation](#5-deck-generation)
- [Share Links](#6-share-links)
- [Composite (Page) Endpoints](#7-composite-page-endpoints)
- [Error Responses](#error-responses)

---

## Conventions

- All requests/responses use JSON (`Content-Type: application/json`)
- Authenticated endpoints require `Authorization: Bearer <token>` header
- Timestamps are ISO 8601 strings (e.g. `"2025-09-15T08:00:00Z"`)
- IDs are strings (UUIDs recommended)
- Pagination uses `?page=1&limit=20` where applicable

---

## Data Models

These match the existing TypeScript interfaces in `src/types/index.ts`.

### User

```json
{
  "id": "string",
  "name": "string",
  "email": "string",
  "avatarUrl": "string | null",
  "createdAt": "string (ISO 8601)"
}
```

### Project

```json
{
  "id": "string",
  "userId": "string",
  "name": "string",
  "prompt": "string",
  "status": "generating" | "draft" | "shared",
  "slidesHtml": ["string"],
  "fullHtml": "string | null",
  "thumbnailUrl": "string | null",
  "createdAt": "string (ISO 8601)",
  "updatedAt": "string (ISO 8601)"
}
```

### ProjectDocument

```json
{
  "id": "string",
  "projectId": "string",
  "type": "product-description" | "timeline" | "swot-analysis" | "market-research" | "financial-projections" | "funding-requirements" | "product-forecast" | "competitive-analysis" | "executive-summary",
  "title": "string",
  "content": "string",
  "status": "generating" | "ready" | "error" | "pending",
  "createdAt": "string (ISO 8601)",
  "updatedAt": "string (ISO 8601)"
}
```

### ChatMessage

```json
{
  "id": "string",
  "projectId": "string",
  "role": "user" | "assistant",
  "content": "string",
  "createdAt": "string (ISO 8601)"
}
```

### ShareLink

```json
{
  "id": "string",
  "projectId": "string",
  "slug": "string",
  "isPasswordProtected": "boolean",
  "expiresAt": "string (ISO 8601) | null",
  "viewCount": "number",
  "createdAt": "string (ISO 8601)"
}
```

---

## 1. Authentication

### POST /auth/signup

Create a new user account.

**Used by:** `/signup` page

**Request:**

```json
{
  "name": "string (required, min 1 char)",
  "email": "string (required, valid email)",
  "password": "string (required, min 8 chars, 1 uppercase, 1 number)"
}
```

**Response (201):**

```json
{
  "user": User,
  "token": "string (JWT)"
}
```

---

### POST /auth/login

Authenticate an existing user.

**Used by:** `/login` page

**Request:**

```json
{
  "email": "string (required)",
  "password": "string (required)"
}
```

**Response (200):**

```json
{
  "user": User,
  "token": "string (JWT)"
}
```

---

### GET /auth/me

Get the currently authenticated user.

**Used by:** App layout (sidebar user display, header avatar)

**Headers:** `Authorization: Bearer <token>`

**Response (200):**

```json
{
  "user": User
}
```

---

### POST /auth/logout

Invalidate the current session.

**Response (200):**

```json
{
  "success": true
}
```

---

## 2. Projects

### GET /projects

List all projects for the authenticated user.

**Used by:** `/projects` page (ProjectsGallery component)

**Headers:** `Authorization: Bearer <token>`

**Query params:**

| Param    | Type   | Default    | Description                                      |
|----------|--------|------------|--------------------------------------------------|
| `status` | string | `"all"`    | Filter: `all`, `generating`, `draft`, `shared` |
| `sort`   | string | `"recent"` | Sort: `recent`, `name`, `status`                 |

**Response (200):**

```json
{
  "projects": [Project]
}
```

---

### POST /projects

Create a new project. Triggered when the user starts a new pitch from the dashboard chat.

**Used by:** Dashboard chat flow (on first message / deck generation)

**Headers:** `Authorization: Bearer <token>`

**Request:**

```json
{
  "name": "string (required)",
  "prompt": "string (required, the initial idea/message)"
}
```

**Response (201):**

```json
{
  "project": Project
}
```

Note: The returned project will have `status: "generating"`, empty `slidesHtml: []`, and `fullHtml: null`.

---

### GET /projects/:projectId

Get a single project by ID.

**Used by:** Workspace page, Documents pages

**Headers:** `Authorization: Bearer <token>`

**Response (200):**

```json
{
  "project": Project
}
```

---

### PATCH /projects/:projectId

Update project fields (name, status).

**Used by:** Workspace "Share" flow (changes status)

**Headers:** `Authorization: Bearer <token>`

**Request:**

```json
{
  "name": "string (optional)",
  "status": "draft" | "shared" (optional)
}
```

**Response (200):**

```json
{
  "project": Project
}
```

---

### DELETE /projects/:projectId

Delete a project and all associated data.

**Headers:** `Authorization: Bearer <token>`

**Response (204):** No content

---

## 3. Documents

### GET /projects/:projectId/documents

List all documents for a project.

**Used by:** Workspace (document checklist), Documents page, Share page

**Headers:** `Authorization: Bearer <token>` (not required for share view)

**Response (200):**

```json
{
  "documents": [ProjectDocument]
}
```

---

### GET /documents/:documentId

Get a single document by ID.

**Used by:** `/projects/:projectId/documents/:documentId` page

**Headers:** `Authorization: Bearer <token>`

**Response (200):**

```json
{
  "document": ProjectDocument
}
```

---

## 4. Chat Messages

### GET /projects/:projectId/messages

Get all chat messages for a project.

**Used by:** Workspace chat panel

**Headers:** `Authorization: Bearer <token>`

**Response (200):**

```json
{
  "messages": [ChatMessage]
}
```

---

### POST /projects/:projectId/messages

Send a user message and receive an AI response. This is the core interaction endpoint.

**Used by:** Workspace ChatPanel, Dashboard ChatPanel

**Headers:** `Authorization: Bearer <token>`

**Request:**

```json
{
  "content": "string (required, the user's message)"
}
```

**Response (200):**

```json
{
  "userMessage": ChatMessage,
  "assistantMessage": ChatMessage,
  "documentsUpdated": [
    {
      "id": "string (document ID)",
      "status": "ready" | "generating" | "error"
    }
  ]
}
```

**Why `documentsUpdated`?**

In the workspace chat, sending a message causes the AI to mark 2-3 pending documents as "ready" (see `chat-panel.tsx:53-62`). The backend should return which documents changed status so the frontend can update the checklist in real-time.

---

### POST /projects/:projectId/messages/stream (alternative)

If streaming AI responses is desired, use SSE (Server-Sent Events).

**Headers:** `Authorization: Bearer <token>`

**Request:**

```json
{
  "content": "string (required)"
}
```

**Response:** `Content-Type: text/event-stream`

```
event: user_message
data: { ChatMessage }

event: token
data: { "content": "partial text..." }

event: assistant_message
data: { ChatMessage }

event: documents_updated
data: [{ "id": "doc-1", "status": "ready" }]

event: done
data: {}
```

---

## 5. Deck Generation

### POST /projects/:projectId/generate-deck

Generate (or regenerate) the pitch deck from the project's conversation/structured data.

**Used by:** Dashboard chat flow (after conversation completes), Workspace ("Regenerate" action)

**Headers:** `Authorization: Bearer <token>`

**Request:**

```json
{
  "style": {
    "theme": "dark" | "light" (optional, default "light"),
    "aesthetic": "minimal" | "bold" | "glassmorphism" (optional),
    "vibe": "string (optional, freeform e.g. 'premium and confident')"
  }
}
```

**Response (200):**

```json
{
  "project": Project
}
```

The returned `Project` will have populated `slidesHtml` (array of HTML strings, one per slide) and/or `fullHtml` (single self-contained HTML page), and `status` updated to `"draft"`.

---

### POST /projects/:projectId/generate-documents

Trigger generation of all 9 document types for a project.

**Used by:** Implicitly after project creation / deck generation

**Headers:** `Authorization: Bearer <token>`

**Request:**

```json
{
  "types": ["product-description", "timeline", "swot-analysis", "market-research", "financial-projections", "funding-requirements", "product-forecast", "competitive-analysis", "executive-summary"]
}
```

If `types` is omitted, all 9 types are generated.

**Response (202):** Accepted — generation happens asynchronously

```json
{
  "documents": [ProjectDocument]
}
```

All returned documents will have `status: "generating"` or `status: "pending"`. The frontend polls or listens for updates via the chat message flow (documents become "ready" through chat interaction).

---

## 6. Share Links

### POST /projects/:projectId/share

Create or get the share link for a project. Also sets the project status to `"shared"`.

**Used by:** Workspace "Share" button

**Headers:** `Authorization: Bearer <token>`

**Request:**

```json
{
  "isPasswordProtected": "boolean (optional, default false)",
  "password": "string (optional, required if isPasswordProtected is true)",
  "expiresAt": "string (ISO 8601) | null (optional)"
}
```

**Response (201):**

```json
{
  "shareLink": ShareLink,
  "project": Project
}
```

---

### GET /share/:projectId (public)

Get a shared project for the public share view. **No auth required.**

**Used by:** `/share/[projectId]` page

**Query params:**

| Param      | Type   | Description                              |
|------------|--------|------------------------------------------|
| `password` | string | Required if share link is password-protected |

**Response (200):**

```json
{
  "project": {
    "id": "string",
    "name": "string",
    "slidesHtml": ["string"],
    "fullHtml": "string | null"
  },
  "documents": [
    {
      "id": "string",
      "type": "DocumentType",
      "title": "string",
      "content": "string",
      "status": "string"
    }
  ],
  "viewCount": "number"
}
```

Note: The public response intentionally omits sensitive fields like `userId`, `prompt`, and `thumbnailUrl`.

**Response (401) — if password required:**

```json
{
  "error": "password_required",
  "message": "This share link is password-protected."
}
```

---

## 7. Composite (Page) Endpoints

These bundle multiple resources into a single response, optimized for specific pages that need several data types at once.

---

### GET /pages/workspace/:projectId

Everything the workspace page needs in one call.

**Used by:** `/projects/[projectId]` page (server component)

**Headers:** `Authorization: Bearer <token>`

**Response (200):**

```json
{
  "project": Project,
  "documents": [ProjectDocument],
  "messages": [ChatMessage]
}
```

**Frontend mapping:**
- `project` → `WorkspaceClient.project`
- `documents` → `WorkspaceClient.documents`
- `messages` → `WorkspaceClient.initialMessages`

---

### GET /pages/share/:projectId (public)

Same as `GET /share/:projectId` above. Listed here for completeness — the share endpoint already acts as a composite.

---

### GET /pages/documents/:projectId

Documents listing page data.

**Used by:** `/projects/[projectId]/documents` page (server component)

**Headers:** `Authorization: Bearer <token>`

**Response (200):**

```json
{
  "project": Project,
  "documents": [ProjectDocument]
}
```

---

## Error Responses

All error responses follow a consistent shape:

```json
{
  "error": "string (machine-readable error code)",
  "message": "string (human-readable description)"
}
```

### Standard Error Codes

| HTTP Status | Error Code           | When                                              |
|-------------|----------------------|---------------------------------------------------|
| 400         | `validation_error`   | Missing/invalid fields in request body             |
| 401         | `unauthorized`       | Missing or invalid auth token                      |
| 403         | `forbidden`          | User doesn't own the requested resource            |
| 404         | `not_found`          | Resource doesn't exist                             |
| 409         | `conflict`           | Duplicate resource (e.g. email already registered) |
| 422         | `unprocessable`      | Valid request but can't process (e.g. deck gen failed) |
| 429         | `rate_limited`       | Too many requests                                  |
| 500         | `internal_error`     | Server error                                       |

### Validation Error (400) — detailed format

```json
{
  "error": "validation_error",
  "message": "Request validation failed.",
  "fields": {
    "email": "Please enter a valid email address.",
    "password": "Password must be at least 8 characters."
  }
}
```

This `fields` map matches the frontend's `errors` state objects in the login/signup forms, enabling direct mapping to form field errors.

---

## Endpoint Summary

| Method | Endpoint                                | Auth | Description                          |
|--------|-----------------------------------------|------|--------------------------------------|
| POST   | `/auth/signup`                          | No   | Register new user                    |
| POST   | `/auth/login`                           | No   | Login                                |
| GET    | `/auth/me`                              | Yes  | Get current user                     |
| POST   | `/auth/logout`                          | Yes  | Logout                               |
| GET    | `/projects`                             | Yes  | List user's projects                 |
| POST   | `/projects`                             | Yes  | Create project                       |
| GET    | `/projects/:projectId`                  | Yes  | Get single project                   |
| PATCH  | `/projects/:projectId`                  | Yes  | Update project                       |
| DELETE | `/projects/:projectId`                  | Yes  | Delete project                       |
| GET    | `/projects/:projectId/documents`        | Yes  | List project documents               |
| GET    | `/documents/:documentId`                | Yes  | Get single document                  |
| GET    | `/projects/:projectId/messages`         | Yes  | List chat messages                   |
| POST   | `/projects/:projectId/messages`         | Yes  | Send message + get AI response       |
| POST   | `/projects/:projectId/generate-deck`    | Yes  | Generate/regenerate pitch deck       |
| POST   | `/projects/:projectId/generate-documents` | Yes | Trigger document generation         |
| POST   | `/projects/:projectId/share`            | Yes  | Create share link                    |
| GET    | `/share/:projectId`                     | No   | Public share view                    |
| GET    | `/pages/workspace/:projectId`           | Yes  | Composite: workspace page data       |
| GET    | `/pages/documents/:projectId`           | Yes  | Composite: documents page data       |

```