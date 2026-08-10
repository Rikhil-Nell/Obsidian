# 4. Deployment Guide — Going to Production

This doc covers what changes when you move from `localhost` to `api.ex.com` + `ex.com`.

---

## Scenario: Separate Frontend and Backend

```
ex.com          → Vercel (frontend — React, Next.js, or static HTML)
api.ex.com      → Your server (FastAPI in Docker)
```

Both share the root domain `ex.com`, which means **cookies can be shared** across subdomains. This is the simplest production setup.

---

## 1. Google Cloud Console Changes

Go to **APIs & Services → Credentials → your OAuth Client** and update:

| Setting | Local | Production |
|---------|-------|------------|
| **Authorized redirect URIs** | `http://localhost:8000/auth/google/callback` | `https://api.ex.com/auth/google/callback` |
| **Authorized JavaScript origins** | — | `https://ex.com` |

> **Tip:** You can keep both — GCP allows multiple redirect URIs, so localhost still works for dev.

---

## 2. CORS Middleware

With separate domains, the browser enforces **Cross-Origin Resource Sharing** (CORS). You need to explicitly allow your frontend origin:

```python
# app/main.py
from fastapi.middleware.cors import CORSMiddleware

app.add_middleware(
    CORSMiddleware,
    allow_origins=["https://ex.com"],  # NOT "*" — must be explicit for credentials
    allow_credentials=True,            # allows cookies cross-origin
    allow_methods=["*"],
    allow_headers=["*"],
)
```

### Why not `allow_origins=["*"]`?

Browsers refuse to send cookies with `credentials: "include"` if the server responds with `Access-Control-Allow-Origin: *`. You must list the exact origin.

### What if I have multiple environments?

```python
# config.py
ALLOWED_ORIGINS: list[str] = ["http://localhost:3000"]  # override in production

# main.py
app.add_middleware(
    CORSMiddleware,
    allow_origins=settings.ALLOWED_ORIGINS,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
```

Set `ALLOWED_ORIGINS=["https://ex.com"]` in your production `.env`.

---

## 3. Cookie Flags

Cookies must be updated for production:

```python
# auth_controller.py — update _set_auth_cookies
response.set_cookie(
    key="access_token",
    value=access_token,
    httponly=True,
    secure=True,         # ← MUST be True (requires HTTPS)
    samesite="lax",
    max_age=...,
    path="/",
    domain=".ex.com",    # ← leading dot = shared across subdomains
)
```

| Flag | Local Dev | Production | Why |
|------|-----------|------------|-----|
| `secure` | `False` | **`True`** | Browsers won't send `secure` cookies over HTTP |
| `samesite` | `"lax"` | `"lax"` | Works for same-root-domain |
| `domain` | not set | `".ex.com"` | Share cookies between `ex.com` and `api.ex.com` |

### Why `.ex.com` with a leading dot?

A cookie with `domain=.ex.com` is sent to:
- `ex.com` ✅
- `api.ex.com` ✅
- `www.ex.com` ✅
- `anything.ex.com` ✅

Without the domain flag, a cookie set by `api.ex.com` is only sent to `api.ex.com`.

---

## 4. OAuth Redirect After Login

After the Google callback, the backend currently redirects to `/chat.html` (a static file served by FastAPI). In production, redirect to the **Vercel frontend**:

```python
# config.py
FRONTEND_URL: str = "http://localhost:8000"  # override in production

# routers/auth.py — google_callback
redirect = RedirectResponse(url=f"{settings.FRONTEND_URL}/chat", status_code=302)
```

Set `FRONTEND_URL=https://ex.com` in your production `.env`.

---

## 5. Frontend `fetch()` Calls

Since the frontend is on a different origin, every `fetch` needs `credentials: "include"` (instead of the default `"same-origin"`):

```javascript
// Local dev
fetch('/api/conversations')

// Production (cross-origin)
fetch('https://api.ex.com/api/conversations', {
    credentials: 'include'  // ← sends cookies cross-origin
})
```

Best practice — use a config variable:

```javascript
const API_BASE = window.location.hostname === 'localhost'
    ? ''                          // same origin in dev
    : 'https://api.ex.com';      // cross-origin in prod

authFetch(`${API_BASE}/api/conversations`);
```

---

## 6. HTTPS

Cookies with `secure=True` require HTTPS. For production:

- Use a **reverse proxy** (nginx, Caddy, Traefik) that terminates TLS
- Or deploy behind a load balancer (AWS ALB, Cloudflare) that handles HTTPS
- Caddy is the easiest — it auto-provisions Let's Encrypt certificates:

```
# Caddyfile
api.ex.com {
    reverse_proxy localhost:8000
}
```

---

## 7. JWT Secret

The default `JWT_SECRET_KEY` is `"change-me-in-production"`. In production, generate a strong secret:

```bash
openssl rand -hex 32
```

Set it as an environment variable on your server. **Never commit it to git.**

---

## 8. What If the Domains Don't Share a Root?

If your setup is `myapi.com` and `myfrontend.com` (completely different domains), cookies **cannot** be shared. You'd need to:

1. Pass the access token via **URL fragment** after OAuth callback:
   ```python
   redirect = RedirectResponse(f"https://myfrontend.com/auth/callback#access_token={access_token}")
   ```
2. Frontend extracts the token from the URL hash and stores it **in memory** (not localStorage)
3. Frontend sends it as a `Bearer` header instead of relying on cookies
4. Refresh token can still use a cookie scoped to `myapi.com`

This is "Approach B" from common OAuth patterns. It's more complex but works for any domain setup.

---

## Summary — Everything That Changes for Production

| Area | Change |
|------|--------|
| **GCP Console** | Add production redirect URI + JS origin |
| **`main.py`** | Add `CORSMiddleware` with explicit origins |
| **`auth_controller.py`** | `secure=True`, `domain=".ex.com"` |
| **`routers/auth.py`** | Redirect to `FRONTEND_URL` after OAuth callback |
| **`config.py`** | Add `FRONTEND_URL`, `ALLOWED_ORIGINS` settings |
| **Frontend JS** | Use `credentials: "include"`, absolute API URLs |
| **`.env`** | `FRONTEND_URL`, `ALLOWED_ORIGINS`, real `JWT_SECRET_KEY` |
| **Infrastructure** | HTTPS via reverse proxy (Caddy/nginx) |
