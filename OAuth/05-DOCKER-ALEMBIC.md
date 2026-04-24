# FastAPI + PostgreSQL + Docker Compose — Guide

## How This Project Is Structured

```
auth-test/
├── app/
│   ├── core/
│   │   ├── config.py      ← Pydantic Settings — all config from env vars
│   │   └── database.py    ← async engine, session factory, get_db dependency
│   ├── models/
│   │   ├── base.py        ← DeclarativeBase with id, created_at, updated_at
│   │   └── user.py        ← example ORM model
│   ├── schemas/
│   │   └── user.py        ← Pydantic request/response models
│   ├── routers/
│   │   └── users.py       ← CRUD endpoints
│   └── main.py            ← FastAPI app, lifespan, router includes
├── alembic/               ← migration scaffolding
├── Dockerfile             ← multi-stage build with uv
├── docker-compose.yml     ← app + postgres orchestration
└── .env.example           ← copy to .env and customise
```

### Why this layout?

- **`core/`** holds things every part of the app needs — config and db engine. Nothing in `core/` imports from `models/`, `schemas/`, or `routers/`, so there are zero circular imports.
- **`models/`** is your source of truth for the DB schema. Alembic reads these to auto-generate migrations.
- **`schemas/`** defines what the API accepts and returns. Keeping these separate from ORM models means you can change one without breaking the other.
- **`routers/`** holds the actual endpoints. Each file is a `APIRouter` that gets `include_router()`'d in `main.py`.

### The async pattern

Every DB call goes through `AsyncSession`. The `get_db()` dependency in `database.py` handles the session lifecycle:

```python
async def get_db() -> AsyncGenerator[AsyncSession, None]:
    async with async_session() as session:
        try:
            yield session        # route uses it
            await session.commit()   # auto-commit if no exception
        except Exception:
            await session.rollback()
            raise
```

Your route functions just declare `db: AsyncSession = Depends(get_db)` and use it — no manual commit/rollback needed.

---

## Docker Compose — Commands You Should Know

### Starting up

```bash
# Build images + start everything (foreground — see logs live)
docker compose up --build

# Same but detached (background)
docker compose up --build -d
```

> `--build` forces a rebuild of the app image. Without it, Docker uses the cached image, which means your code changes won't be reflected.

### The rebuild gotcha

Docker caches layers. If you only changed Python code (not `pyproject.toml`), the dependency layer is cached and the build is fast. But if you changed dependencies, Docker rebuilds from that layer down.

```bash
# Force a full rebuild ignoring ALL cached layers
docker compose build --no-cache

# Then start
docker compose up -d
```

### Viewing logs

```bash
# Follow logs from all services
docker compose logs -f

# Follow logs from just the app
docker compose logs -f app

# Show last 100 lines only
docker compose logs --tail=100 app
```

### Exec into a running container

```bash
# Get a shell inside the app container
docker compose exec app bash

# Or run a one-off command (e.g., run Alembic migration)
docker compose exec app alembic upgrade head

# Open a psql session inside the DB container
docker compose exec db psql -U postgres -d app_db
```

### Stopping & cleaning up

```bash
# Stop containers but keep volumes (DB data persists)
docker compose down

# Stop AND delete volumes (wipes the DB)
docker compose down -v

# Nuclear option — remove everything (containers, images, volumes, networks)
docker compose down -v --rmi all
```

### Inspecting the DB from your host machine

Since we expose port 5432, you can connect from your host with any Postgres client:

```bash
# Using psql on your host
psql -h localhost -U postgres -d app_db

# Or use a GUI like pgAdmin, DBeaver, TablePlus — connect to localhost:5432
```

---

## Alembic — Migration Patterns

### The workflow

Alembic is your version control for the database schema. The basic loop is:

1. **Change a model** (add a column, create a table, etc.)
2. **Generate a migration** — Alembic diffs your models against the DB and writes a migration script
3. **Apply the migration** — runs the SQL against the DB

### Commands

```bash
# Generate a migration by auto-detecting model changes
docker compose exec app alembic revision --autogenerate -m "add users table"

# Apply all pending migrations
docker compose exec app alembic upgrade head

# Rollback the last migration
docker compose exec app alembic downgrade -1

# Rollback ALL migrations (back to empty DB)
docker compose exec app alembic downgrade base

# Show current migration state
docker compose exec app alembic current

# Show migration history
docker compose exec app alembic history --verbose
```

### First-time setup

After `docker compose up --build`, the DB exists but has no tables. You need to:

```bash
# Generate the initial migration
docker compose exec app alembic revision --autogenerate -m "initial"

# Apply it
docker compose exec app alembic upgrade head
```

### Dealing with migration conflicts

If you and a teammate both generate migrations from the same base, Alembic will complain about "multiple heads". Fix it:

```bash
# See all heads
docker compose exec app alembic heads

# Merge them into a single migration
docker compose exec app alembic merge heads -m "merge migrations"

# Then apply
docker compose exec app alembic upgrade head
```

### When autogenerate misses things

Alembic's autogenerate doesn't detect everything. It **will not** detect:
- Table or column renames (sees them as drop + add)
- Changes to `server_default` values
- Changes to `CheckConstraint`, `UniqueConstraint` text

For these, write the migration manually:

```bash
docker compose exec app alembic revision -m "rename column foo to bar"
# Then edit the generated file in alembic/versions/
```

---

## Patterns Worth Knowing

### Environment variable precedence

Pydantic Settings loads config in this order (first wins):

1. **Environment variables** (set by Docker Compose `environment:`)
2. **`.env` file** (for local dev without Docker)
3. **Defaults in the `Settings` class**

This means Docker Compose's `environment:` block always wins over `.env`, which is exactly what you want.

### Adding a new model

1. Create `app/models/thing.py` with your SQLAlchemy model
2. Import it in `alembic/env.py` (so autogenerate sees it)
3. Generate + apply migration

```python
# app/models/thing.py
from sqlalchemy import String
from sqlalchemy.orm import Mapped, mapped_column
from app.models.base import Base

class Thing(Base):
    __tablename__ = "things"
    name: Mapped[str] = mapped_column(String(100))
```

```python
# alembic/env.py — add the import
from app.models.thing import Thing  # noqa: F401
```

```bash
docker compose exec app alembic revision --autogenerate -m "add things table"
docker compose exec app alembic upgrade head
```

### Adding a new router

1. Create `app/routers/things.py` with an `APIRouter`
2. Include it in `app/main.py`

```python
# app/main.py
from app.routers import things
app.include_router(things.router)
```

### Hot-reloading during development

The default `CMD` in the Dockerfile doesn't have `--reload`. For development, override it in `docker-compose.yml`:

```yaml
services:
  app:
    # ... existing config ...
    command: uvicorn app.main:app --host 0.0.0.0 --port 8000 --reload
    volumes:
      - ./app:/app/app  # mount your code so reload picks up changes
```

This way, changes to your Python files are reflected instantly without rebuilding the image.

### Docker networking — how the containers talk

- Inside Docker Compose, containers reference each other by **service name**. The app connects to `db:5432`, not `localhost:5432`.
- The `ports:` mapping (`"5432:5432"`) is for **your host machine** to reach the DB. Containers don't use it.
- If the app can't connect, check that `POSTGRES_HOST=db` (the service name), not `localhost`.

### Common pitfall: "connection refused" on startup

Even with `depends_on`, if you don't have a healthcheck, Docker only waits for the container to _start_, not for Postgres to be _ready_. This template uses:

```yaml
healthcheck:
  test: ["CMD-SHELL", "pg_isready -U postgres -d app_db"]
  interval: 5s
  timeout: 5s
  retries: 5
```

Combined with `condition: service_healthy`, the app container won't start until `pg_isready` succeeds.

### Inspecting what's inside your Docker image

```bash
# List all files in the image
docker compose run --rm app find /app -type f

# Check installed Python packages
docker compose run --rm app pip list

# Check the image size
docker images auth-test-app
```

### Volume persistence

The `pgdata` volume persists your database data across `docker compose down` / `docker compose up` cycles. The data is only deleted when you explicitly pass `-v`:

```bash
# Keeps data
docker compose down

# Deletes data
docker compose down -v
```

---

## Quick Reference

| What                          | Command                                                    |
|-------------------------------|------------------------------------------------------------|
| Start everything              | `docker compose up --build -d`                             |
| View app logs                 | `docker compose logs -f app`                               |
| Run Alembic migration         | `docker compose exec app alembic upgrade head`             |
| Generate migration            | `docker compose exec app alembic revision --autogenerate -m "msg"` |
| Open DB shell                 | `docker compose exec db psql -U postgres -d app_db`        |
| Shell into app container      | `docker compose exec app bash`                             |
| Stop (keep data)              | `docker compose down`                                      |
| Stop (wipe data)              | `docker compose down -v`                                   |
| Swagger UI                    | `http://localhost:8000/docs`                                |
| Health check                  | `http://localhost:8000/health`                              |
