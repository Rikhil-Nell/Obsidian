# Authentication & OAuth — How It All Actually Works

This doc is a deep-dive reference. It explains the concepts, flows, and patterns you'll encounter when adding Google/GitHub/etc. auth to your app. Read this before touching code.

---

## Table of Contents

1. [The Big Picture — What Are We Even Doing?](#the-big-picture)
2. [OAuth 2.0 — The Authorization Code Flow](#oauth-20-the-flow)
3. [OpenID Connect (OIDC) — OAuth + Identity](#openid-connect)
4. [Tokens — JWTs, Access Tokens, Refresh Tokens](#tokens)
5. [Cookies vs Headers — How Tokens Reach Your Backend](#cookies-vs-headers)
6. [What the Frontend Does vs What the Backend Does](#frontend-vs-backend)
7. [Account Linking — One User, Many Login Methods](#account-linking)
8. [Token Expiration & Refresh — The Lifecycle](#token-lifecycle)
9. [Session Strategies — Stateful vs Stateless](#session-strategies)
10. [Security Gotchas — CSRF, XSS, and Cookie Flags](#security)

---

<a id="the-big-picture"></a>
## 1. The Big Picture — What Are We Even Doing?

When a user clicks "Sign in with Google", here's what's conceptually happening:

1. Your app asks Google: *"Can you verify who this person is and tell me their email?"*
2. Google asks the user: *"Hey, this app wants to know your email. Cool?"*
3. User says yes, Google tells your app: *"This person is rikhil@gmail.com"*
4. Your app creates (or finds) a user record and gives the browser a token/cookie so it can make authenticated requests going forward.

That's it. OAuth is fundamentally about **delegating identity verification to someone you trust** (Google, GitHub, etc.) instead of managing passwords yourself.

### Why not just passwords?

You *can* do passwords. But:

- You have to store hashed passwords securely (bcrypt/argon2)
- You have to build forgot-password, reset-password, email verification flows
- Users have to remember yet another password
- You're liable if your password database leaks

With OAuth, Google handles all of that. You just get an email and a unique ID.

---

<a id="oauth-20-the-flow"></a>
## 2. OAuth 2.0 — The Authorization Code Flow

This is the flow used by Google, GitHub, and every major provider. It's called the **Authorization Code** flow because your app receives a short-lived *code* that it exchanges for tokens server-side.

### The sequence (step by step)

```
┌──────────┐     ┌──────────┐     ┌──────────────┐
│ Browser  │     │ Your API │     │ Google/GitHub │
└────┬─────┘     └────┬─────┘     └──────┬───────┘
     │                │                   │
     │  1. Click      │                   │
     │  "Sign in      │                   │
     │   with Google" │                   │
     │───────────────>│                   │
     │                │                   │
     │  2. Redirect   │                   │
     │  to Google's   │                   │
     │  auth page     │                   │
     │<───────────────│                   │
     │                │                   │
     │  3. User sees Google's            │
     │  consent screen, clicks "Allow"   │
     │──────────────────────────────────>│
     │                │                   │
     │  4. Google redirects back to      │
     │  YOUR callback URL with a CODE    │
     │<──────────────────────────────────│
     │                │                   │
     │  5. Browser    │                   │
     │  hits your     │                   │
     │  /callback     │                   │
     │───────────────>│                   │
     │                │  6. Your server   │
     │                │  exchanges the    │
     │                │  CODE for TOKENS  │
     │                │  (server-to-server│
     │                │   — user never    │
     │                │   sees this)      │
     │                │──────────────────>│
     │                │                   │
     │                │  7. Google returns│
     │                │  access_token +   │
     │                │  id_token (JWT)   │
     │                │<──────────────────│
     │                │                   │
     │  8. Your API   │                   │
     │  creates a     │                   │
     │  session/JWT,  │                   │
     │  sets cookie   │                   │
     │<───────────────│                   │
     │                │                   │
     │  9. Subsequent │                   │
     │  requests      │                   │
     │  include the   │                   │
     │  cookie        │                   │
     │───────────────>│                   │
```

### Why the "code exchange"? Why not send the token directly?

Security. The authorization code is passed through the browser (via URL redirect), which is visible in browser history, logs, etc. But the code alone is useless — it can only be exchanged for tokens by your **server**, which also sends your **client secret** (which the browser never sees). This is why it's safe even if someone intercepts the code.

### Key terms

| Term | What it is |
|---|---|
| **Client ID** | Your app's public identifier (safe to expose) |
| **Client Secret** | Your app's private key (NEVER expose to frontend) |
| **Authorization Code** | One-time code from the redirect, exchanged for tokens |
| **Access Token** | Token that lets you call Google's APIs (e.g., get user profile) |
| **ID Token** | A JWT containing the user's identity claims (email, name, etc.) |
| **Redirect URI / Callback URL** | Where Google sends the user after they approve |
| **Scope** | What you're asking permission for (e.g., `openid email profile`) |
| **State** | A random string you generate to prevent CSRF attacks on the callback |

---

<a id="openid-connect"></a>
## 3. OpenID Connect (OIDC) — OAuth + Identity

OAuth 2.0 by itself is an **authorization** protocol — it answers "what can this app access?" not "who is this person?". **OpenID Connect (OIDC)** is a thin layer on top of OAuth that adds **identity**.

The practical difference: when you request the `openid` scope, the provider returns an **ID Token** (a JWT) in addition to the access token. The ID token contains:

```json
{
  "iss": "https://accounts.google.com",     // who issued it
  "sub": "110248495921238986420",            // unique user ID at Google
  "email": "rikhil@gmail.com",
  "email_verified": true,
  "name": "Rikhil Nellimarla",
  "picture": "https://lh3.googleusercontent.com/...",
  "iat": 1708790400,                        // issued at
  "exp": 1708794000                         // expires at
}
```

**You use the `sub` (subject) field as the unique identifier** — not the email. Emails can change; `sub` is permanent.

### Google and GitHub both support OIDC?

Google: yes, full OIDC support with ID tokens.
GitHub: no — GitHub uses plain OAuth 2.0. You get an access token, then use it to call `GET https://api.github.com/user` to get the user's identity. Same end result, one extra API call.

---

<a id="tokens"></a>
## 4. Tokens — JWTs, Access Tokens, Refresh Tokens

### What is a JWT?

A **JSON Web Token** is a base64-encoded JSON object with a signature. It looks like:

```
eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiYWJjMTIzIiwiZXhwIjoxNzA4fQ.K7h3mE_signature
```

Three parts separated by dots:
1. **Header** — algorithm used (`HS256`, `RS256`)
2. **Payload** — the claims (user ID, expiry, etc.)
3. **Signature** — proves it hasn't been tampered with

Anyone can *read* a JWT (it's just base64). The signature proves it was issued by you and hasn't been modified. You **never** put secrets in a JWT payload.

### The three tokens you'll encounter

| Token | Issued by | Lifetime | Purpose |
|---|---|---|---|
| **Google's Access Token** | Google | ~1 hour | Call Google APIs (get profile, etc.) |
| **Google's ID Token** | Google | ~1 hour | Contains user identity (email, sub) |
| **YOUR app's JWT** | Your server | You decide (15min–7 days) | Authenticate subsequent requests to YOUR API |

The flow is:
1. User logs in via Google → you get Google's access_token + id_token
2. You read the id_token to get the user's email/sub
3. You create/find the user in YOUR database
4. You issue YOUR OWN JWT (with your user's ID) and give it to the browser
5. Google's tokens are **done** — you don't need them again (unless you're calling Google APIs on the user's behalf)

### Refresh tokens

A **refresh token** is a long-lived token (days/weeks) used to get a new access token when the old one expires — **without making the user log in again**.

Two separate refresh token concepts:
- **Google's refresh token**: lets your server get a new Google access token. Only relevant if you need ongoing access to Google APIs (like reading their calendar). For simple login, you don't need this.
- **YOUR app's refresh token**: lets the browser get a new JWT from your server when the old one expires.

For your chat app, the pattern will be:

```
Access JWT:  short-lived (15–30 minutes)
Refresh token: long-lived (7–30 days), stored in httpOnly cookie

When the access JWT expires:
  Browser → POST /auth/refresh (sends refresh cookie)
  Server → validates refresh token, issues new access JWT
  No Google interaction needed
```

---

<a id="cookies-vs-headers"></a>
## 5. Cookies vs Headers — How Tokens Reach Your Backend

There are two ways the browser can send your token with every request:

### Option A: `Authorization` header (common in SPAs)

```javascript
// Frontend stores token in memory/localStorage
fetch('/api/chat', {
  headers: { 'Authorization': 'Bearer eyJhbGci...' }
})
```

**Pros:** Explicit, works well with APIs, no CSRF issues.
**Cons:** If you store the token in `localStorage`, it's vulnerable to XSS (any injected script can steal it). Storing in memory is safer but loses the token on page refresh.

### Option B: httpOnly cookie (recommended for your setup)

```python
# Backend sets the cookie after login
response.set_cookie(
    key="access_token",
    value="eyJhbGci...",
    httponly=True,    # JavaScript CANNOT read this cookie
    secure=True,      # Only sent over HTTPS
    samesite="lax",   # CSRF protection
    max_age=1800      # 30 minutes
)
```

**Pros:** JavaScript can never read or steal the token (immune to XSS). Automatically sent with every request.
**Cons:** Vulnerable to CSRF (mitigated by `SameSite` flag). Slightly more complex setup.

### What we'll use

**httpOnly cookies** for both access and refresh tokens. This is the most secure option for a web app where the frontend and backend are on the same domain.

```
Browser makes request → cookie is automatically included
  ↓
Backend reads cookie → validates JWT → allows/denies
```

The frontend code doesn't even need to know about the token. It just calls `fetch('/api/chat')` and the cookie goes along automatically.

### The cookie flags explained

| Flag | What it does | Why you need it |
|---|---|---|
| `httponly` | JavaScript cannot access the cookie | Prevents XSS from stealing tokens |
| `secure` | Cookie only sent over HTTPS | Prevents interception on HTTP |
| `samesite=lax` | Cookie only sent for same-site requests + top-level navigations | Prevents CSRF attacks |
| `max_age` | Cookie expires after N seconds | Auto-cleanup |
| `path=/` | Cookie sent for all paths | Needed for API routes |

---

<a id="frontend-vs-backend"></a>
## 6. What the Frontend Does vs What the Backend Does

### Frontend responsibilities

| Step | What happens |
|---|---|
| **Initiate login** | User clicks "Sign in with Google" → frontend redirects to `GET /auth/google/login` |
| **Handle redirect** | After Google callback, backend redirects to frontend with cookies already set |
| **Make authenticated requests** | `fetch('/api/...')` — cookies are sent automatically |
| **Handle 401 responses** | If the server returns 401, try refreshing: `POST /auth/refresh`. If that also fails → redirect to login |
| **Logout** | `POST /auth/logout` → backend clears cookies |

### Backend responsibilities

| Step | What happens |
|---|---|
| **`GET /auth/google/login`** | Generate a random `state`, store it, redirect to Google's auth URL |
| **`GET /auth/google/callback`** | Receive the code, verify `state`, exchange code for tokens with Google, read user info from ID token |
| **Create/find user** | Look up user by `provider + provider_user_id`. Create if new. |
| **Issue YOUR tokens** | Create a JWT + refresh token, set them as httpOnly cookies |
| **`GET /auth/me`** | Read the JWT from cookie, return current user info |
| **`POST /auth/refresh`** | Read refresh token from cookie, validate it, issue new JWT |
| **`POST /auth/logout`** | Clear both cookies |
| **Protect endpoints** | Middleware/dependency that reads the JWT cookie and injects the current user |

### The key insight

The **backend is the only thing that talks to Google**. The frontend never sees Google's tokens, your client secret, or any sensitive data. It just:
1. Redirects the user to a URL your backend gives it
2. Gets cookies set by your backend
3. Makes normal API calls with those cookies automatically attached

---

<a id="account-linking"></a>
## 7. Account Linking — One User, Many Login Methods

This is the design pattern for: *"A user signs up with Google, then later tries to log in with GitHub (same email). Should they get the same account?"*

### The database schema

```
users
├── id (UUID)
├── email
├── username
├── hashed_password (nullable — Google-only users don't have one)
├── is_active
├── created_at
└── updated_at

oauth_accounts
├── id (UUID)
├── user_id (FK → users)
├── provider ("google" | "github" | ...)
├── provider_user_id (the `sub` from Google, `id` from GitHub)
├── provider_email
├── created_at
└── UNIQUE(provider, provider_user_id)  ← one entry per provider per user
```

### The linking logic (on OAuth callback)

```python
def handle_oauth_callback(provider, provider_user_id, email):
    # 1. Check if this exact OAuth account exists
    oauth = find_oauth(provider, provider_user_id)
    if oauth:
        return oauth.user  # existing user, done

    # 2. Check if a user with this email already exists
    user = find_user_by_email(email)
    if user:
        # Link this OAuth provider to the existing account
        create_oauth_account(user, provider, provider_user_id, email)
        return user

    # 3. Brand new user — create everything
    user = create_user(email=email, username=derive_from_email(email))
    create_oauth_account(user, provider, provider_user_id, email)
    return user
```

### What this gives you

- **Sign up with Google, later sign in with GitHub (same email)** → same account, two `oauth_accounts` rows
- **Sign up with email/password, later link Google** → same account, one `oauth_accounts` row + password hash
- **Sign up with Google (email A), sign in with GitHub (email B)** → two different accounts (emails don't match)

### The "email trust" question

> *Should you auto-link accounts based on email?*

**Only if the provider confirms the email is verified.** Google always verifies emails. GitHub... sometimes doesn't. Check the `email_verified` field. If unverified, don't auto-link — ask the user to verify manually.

---

<a id="token-lifecycle"></a>
## 8. Token Expiration & Refresh — The Lifecycle

Here's exactly what happens over time:

```
T = 0 min    User logs in via Google
             → Backend issues ACCESS JWT (expires in 30 min)
             → Backend issues REFRESH TOKEN (expires in 7 days)
             → Both stored as httpOnly cookies

T = 5 min    User sends chat message
             → Browser sends cookies automatically
             → Backend reads JWT, checks exp: still valid ✓
             → Request proceeds

T = 35 min   User sends another message
             → Backend reads JWT, checks exp: EXPIRED ✗
             → Returns 401 Unauthorized

             → Frontend sees 401, calls POST /auth/refresh
             → Backend reads refresh cookie: still valid ✓
             → Backend issues NEW access JWT (expires T+65 min)
             → Sets new cookie

             → Frontend retries the original request
             → Now it works ✓

T = 7 days   Refresh token also expires
             → POST /auth/refresh returns 401
             → Frontend redirects to login page
             → User must sign in with Google again
```

### Why two tokens?

- **Short-lived access tokens** limit the damage if one is stolen (it expires in 30 min)
- **Long-lived refresh tokens** keep the user logged in without making them re-authenticate constantly
- Refresh tokens are **only sent to one endpoint** (`/auth/refresh`), reducing exposure

### Refresh token rotation (extra security)

Each time you use a refresh token, **invalidate the old one and issue a new one**. If an attacker steals a refresh token and uses it, the real user's next refresh attempt will fail (the token was already rotated), alerting you to the breach.

```python
@router.post("/auth/refresh")
async def refresh(refresh_token: str = Cookie()):
    # Validate the refresh token
    token_record = await db.get(RefreshToken, refresh_token)
    if not token_record or token_record.is_revoked or token_record.expired:
        raise HTTPException(401)

    # Rotate: revoke old, issue new
    token_record.is_revoked = True
    new_refresh = create_refresh_token(token_record.user_id)
    new_access = create_access_jwt(token_record.user_id)

    response.set_cookie("access_token", new_access, ...)
    response.set_cookie("refresh_token", new_refresh, ...)
```

---

<a id="session-strategies"></a>
## 9. Session Strategies — Stateful vs Stateless

### Stateless (JWT-based) — what we'll use

The server doesn't store session data. Everything is in the JWT:

```json
{
  "sub": "user-uuid-here",
  "exp": 1708794000,
  "iat": 1708790400
}
```

**Pros:** No database lookup on every request, scales horizontally (any server can validate).
**Cons:** Can't revoke a JWT before it expires (you issued it, it's valid until `exp`). Mitigation: keep access JWTs short-lived (15–30 min).

### Stateful (database sessions)

The server stores a session ID in the database and gives the browser a session ID cookie. Every request, the server looks up the session.

**Pros:** Can revoke immediately (delete the row). Full control.
**Cons:** Database lookup on every request. More complex to scale.

### Hybrid (what we're doing)

```
Access: Stateless JWT (15–30 min, no DB lookup)
Refresh: Stateful (stored in DB, allows revocation + rotation)
```

This gives you fast request validation (no DB hit for the JWT) with the ability to revoke sessions (delete the refresh token from DB).

---

<a id="security"></a>
## 10. Security Gotchas

### CSRF (Cross-Site Request Forgery)

An attacker's site tricks your browser into making a request to your API (since cookies are sent automatically). Mitigated by:
- `SameSite=Lax` cookies (browser won't send cookies for cross-origin POST requests)
- CSRF tokens (if you need `SameSite=None` for cross-domain setups)
- The OAuth `state` parameter (prevents CSRF on the callback URL)

### XSS (Cross-Site Scripting)

Injected JavaScript on your page tries to steal tokens. Mitigated by:
- `httpOnly` cookies (JS can't read them)
- Never storing tokens in `localStorage` or `sessionStorage`
- Content Security Policy headers
- Sanitizing user input

### The OAuth `state` parameter

You generate a random string, store it in a cookie/session, and include it in the Google auth URL. When Google redirects back, you verify the `state` matches. This prevents an attacker from crafting a malicious callback URL.

```python
# Before redirect
state = secrets.token_urlsafe(32)
response.set_cookie("oauth_state", state, httponly=True, max_age=600)
redirect_url = f"https://accounts.google.com/o/oauth2/auth?state={state}&..."

# On callback
if request.cookies["oauth_state"] != request.query_params["state"]:
    raise HTTPException(400, "Invalid state")
```

---

## Summary — The Mental Model

```
Google/GitHub = "Who is this person?" (identity provider)
Your backend  = "What can this person do?" (your app's logic)
JWT cookie    = "Proof that this person already logged in" (session)
Refresh token = "Let them stay logged in without re-asking Google"
```

The flow that will repeat regardless of provider (Google, GitHub, etc.):

1. **Frontend:** redirect to your backend's login URL
2. **Backend:** redirect to provider's auth page
3. **Provider:** redirect back to your callback with a code
4. **Backend:** exchange code for tokens → find/create user → issue YOUR JWT → set cookies
5. **Frontend:** just makes normal API calls, cookies handle the rest
