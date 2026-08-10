# 1. Project Overview

A FastAPI + PostgreSQL template with **Google OAuth**, **JWT authentication**, and a **per-user chat app** (OpenAI-powered). Everything runs in Docker Compose.

## What's in the box

- 🔐 **Google OAuth 2.0** with OpenID Connect — sign in with Google, get a JWT
- 🍪 **httpOnly cookie auth** — tokens stored securely, immune to XSS
- 🔄 **Refresh token rotation** with **theft detection** — revoked tokens trigger full session revocation
- 🔗 **Account linking** — multiple providers (Google, GitHub, etc.) → one user account
- 💬 **Chat app** — per-user conversations with OpenAI (`gpt-4o-mini`)
- 🐳 **Docker Compose** — one command to run everything
- 📦 **Alembic migrations** — version-controlled database schema

## Quick start

```bash
# 1. Copy env file and fill in your secrets
cp .env.example .env
# Edit .env with your GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET, OPENAI_API_KEY

# 2. Build and start
docker compose up --build -d

# 3. Run migrations
docker compose exec app alembic revision --autogenerate -m "initial"
docker compose exec app alembic upgrade head

# 4. Open http://localhost:8000
```

## Project structure

```
auth-test/
├── app/
│   ├── core/
│   │   ├── config.py          ← Pydantic Settings (env vars)
│   │   ├── database.py        ← async engine, session, get_db
│   │   ├── security.py        ← JWT creation/validation, token hashing
│   │   └── oauth.py           ← Google OAuth client (authlib)
│   ├── models/
│   │   ├── base.py            ← DeclarativeBase (id, timestamps)
│   │   ├── user.py            ← User model
│   │   ├── oauth_account.py   ← OAuth provider links
│   │   ├── chat.py            ← Conversation + Message
│   │   └── refresh_token.py   ← Hashed refresh tokens
│   ├── schemas/
│   │   ├── user.py            ← User request/response schemas
│   │   ├── auth.py            ← UserProfile schema
│   │   └── chat.py            ← Conversation/Message schemas
│   ├── controllers/
│   │   ├── auth_controller.py ← OAuth callback, token issuance, refresh, logout
│   │   └── chat_controller.py ← Conversation CRUD, OpenAI integration
│   ├── routers/
│   │   ├── users.py           ← User CRUD endpoints
│   │   ├── auth.py            ← OAuth login/callback/refresh/logout
│   │   └── chat.py            ← Conversation/message endpoints
│   └── main.py                ← FastAPI app, middleware, router includes
├── static/
│   ├── index.html             ← Landing page (Google sign-in)
│   ├── chat.html              ← Chat UI
│   ├── css/style.css          ← Dark theme styling
│   └── js/
│       ├── auth.js            ← Token refresh wrapper
│       └── chat.js            ← Chat client logic
├── alembic/                   ← Migration scaffolding
├── docs/                      ← You are here
├── Dockerfile                 ← Multi-stage build with uv
├── docker-compose.yml         ← app + postgres
├── pyproject.toml             ← Python dependencies
└── .env.example               ← Template env file
```

## Environment variables

| Variable | Required | Description |
|----------|----------|-------------|
| `GOOGLE_CLIENT_ID` | ✅ | From Google Cloud Console |
| `GOOGLE_CLIENT_SECRET` | ✅ | From Google Cloud Console |
| `JWT_SECRET_KEY` | ✅ | `openssl rand -hex 32` |
| `OPENAI_API_KEY` | ✅ | From OpenAI dashboard |
| `POSTGRES_USER` | ✅ | Default: `postgres` |
| `POSTGRES_PASSWORD` | ✅ | Default: `postgres` |
| `POSTGRES_DB` | ✅ | Default: `app_db` |

## Google Cloud Console setup

1. Go to [console.cloud.google.com](https://console.cloud.google.com/)
2. Create a project (or use an existing one)
3. Go to **APIs & Services → Credentials**
4. Click **Create Credentials → OAuth Client ID**
5. Application type: **Web application**
6. Authorized redirect URIs: `http://localhost:8000/auth/google/callback`
7. Copy the Client ID and Client Secret into your `.env`

## API endpoints

### Auth
| Method | Path | Description | Auth? |
|--------|------|-------------|-------|
| `GET` | `/auth/google/login` | Redirect to Google OAuth | No |
| `GET` | `/auth/google/callback` | Google callback handler | No |
| `GET` | `/auth/me` | Get current user profile | ✅ |
| `POST` | `/auth/refresh` | Refresh access token | Cookie |
| `POST` | `/auth/logout` | Revoke tokens, clear cookies | Cookie |

### Chat
| Method | Path | Description | Auth? |
|--------|------|-------------|-------|
| `GET` | `/api/conversations` | List user's conversations | ✅ |
| `POST` | `/api/conversations` | Create a conversation | ✅ |
| `GET` | `/api/conversations/{id}/messages` | Get messages | ✅ |
| `POST` | `/api/conversations/{id}/messages` | Send message (+ AI response) | ✅ |
| `DELETE` | `/api/conversations/{id}` | Delete a conversation | ✅ |

### Utility
| Method | Path | Description |
|--------|------|-------------|
| `GET` | `/health` | Health check |
| `GET` | `/` | Landing page |
| `GET` | `/docs` | Swagger UI |
