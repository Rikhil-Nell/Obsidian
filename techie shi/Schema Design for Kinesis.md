Users: 
id
email
google_refresh_token (encrypted)
timezone

Contacts:
id
full_name
email
phone
created_at

Meetings
id
user_id (fk of users)
contact_id (fk of contacts)
google_event_id
title
description
start_time
end_time
status

Agents
id
name
model_name
system_prompt
livekit_config
allowed_tools
user_id (fk of users)
is_active
created_at

AgentState
session id (livekit room id)
agent_id (fk dd agent)
contact_id (fk to contacts)
history json

AgentActions
id
agent_id(fk to agents)
action_type: tool use
payload: json
status: bool

# CRM Database Schema Plan for Voice Agent Platform

## Overview
Database schema for a **Lead-centric CRM** component of a voice agent platform with telephony integration (LiveKit, FreePBX, Twilio), post-call analysis with RAG, and automation triggers.

**Core Principle**: Leads are the central entity. All customer interactions (calls, meetings, communications) tie back to a Lead.

**Lead Detail Page Features**:
1. Communications (email, SMS, chat history)
2. Calendar (meetings & appointments)
3. RAG Chatbot (AI-powered insights from call transcripts)

---

## 1. Database Architecture

### Tech Stack
- **Database**: PostgreSQL (Supabase)
- **ORM**: SQLModel (SQLAlchemy + Pydantic)
- **Auth**: Supabase Auth (auth.users)
- **Vectors**: pgvector for RAG embeddings
- **Migrations**: Alembic

### Key Design Decisions
- All models inherit from `BaseUUIDModel` (UUID pk, created_at, updated_at)
- **Leads are the primary CRM entity** - all interactions reference leads
- Multi-tenancy via `user_id` foreign keys + Row Level Security (RLS)
- Soft deletes with `deleted_at` timestamps where appropriate
- JSONB for flexible configuration fields

---

## 2. Entity Relationship Diagram

### 2.1 High-Level Overview

```
┌──────────────────────────────────────────────────────────────────────────────────────┐
│                                    AUTHENTICATION                                     │
│  ┌─────────────────┐                                                                 │
│  │   auth.users    │ (Supabase managed)                                              │
│  │─────────────────│                                                                 │
│  │ PK id           │                                                                 │
│  │    email        │                                                                 │
│  └────────┬────────┘                                                                 │
│           │ 1:1                                                                      │
│           ▼                                                                          │
│  ┌─────────────────┐                                                                 │
│  │  user_profiles  │                                                                 │
│  │─────────────────│                                                                 │
│  │ PK id           │──────────────────┬──────────────────┬──────────────────┐       │
│  │ FK auth_user_id │                  │                  │                  │       │
│  │    email        │                  │                  │                  │       │
│  │    role         │                  │                  │                  │       │
│  └─────────────────┘                  │                  │                  │       │
└───────────────────────────────────────┼──────────────────┼──────────────────┼───────┘
                                        │ 1:N             │ 1:N             │ 1:N
    ┌───────────────────────────────────┼──────────────────┼──────────────────┤
    │                                   │                  │                  │
    ▼                                   ▼                  ▼                  ▼
┌─────────────────┐            ┌─────────────────┐  ┌─────────────┐  ┌─────────────────┐
│     agents      │            │     triggers    │  │    tags     │  │   audit_logs    │
│─────────────────│            │─────────────────│  │─────────────│  │─────────────────│
│ PK id           │            │ PK id           │  │ PK id       │  │ PK id           │
│ FK user_id      │            │ FK user_id      │  │ FK user_id  │  │ FK user_id      │
│    name         │            │ FK agent_id     │  │    name     │  │    table_name   │
│    system_prompt│            │    conditions   │  │    color    │  │    action       │
│    voice_id     │            │    actions      │  └──────┬──────┘  └─────────────────┘
└────────┬────────┘            └────────┬────────┘         │
         │                              │                  │ M:N (via lead_tags)
         │ 1:N                          │ 1:N              │
         │                              ▼                  │
         │                     ┌─────────────────┐         │
         │                     │trigger_executions         │
         │                     │─────────────────│         │
         │                     │ PK id           │         │
         │                     │ FK trigger_id   │         │
         │                     │    success      │         │
         │                     └─────────────────┘         │
         │                                                 │
         │                                                 │
┌────────┴─────────────────────────────────────────────────┴─────────────────────────────┐
│                              LEAD MANAGEMENT (Central Hub)                              │
│                                                                                         │
│  ┌═══════════════════════════════════════════════════════════════════════════════════┐ │
│  ║                                                                                   ║ │
│  ║  ┌─────────────────────────────────────────────────────────────────────────────┐  ║ │
│  ║  │                              leads (CENTRAL ENTITY)                         │  ║ │
│  ║  │─────────────────────────────────────────────────────────────────────────────│  ║ │
│  ║  │ PK id                    │ stage (enum)           │ lead_score              │  ║ │
│  ║  │ FK user_id               │ previous_stage         │ temperature (enum)      │  ║ │
│  ║  │ FK assigned_to           │ source (enum)          │ first_contacted_at      │  ║ │
│  ║  │ FK converted_to_contact  │ source_detail          │ last_contacted_at       │  ║ │
│  ║  │    first_name            │ stage_changed_at       │ last_activity_at        │  ║ │
│  ║  │    last_name             │ interests (JSONB)      │ custom_fields (JSONB)   │  ║ │
│  ║  │    email                 │ external_id            │ do_not_call             │  ║ │
│  ║  │    phone / phone_norm    │ converted_at           │ do_not_email            │  ║ │
│  ║  │    company / job_title   │ lost_reason            │ is_active / deleted_at  │  ║ │
│  ║  └─────────────────────────────────────────────────────────────────────────────┘  ║ │
│  ║                                         │                                         ║ │
│  ╚═════════════════════════════════════════╪═════════════════════════════════════════╝ │
│                                            │                                           │
│     ┌──────────────┬──────────────┬────────┴────────┬──────────────┐                  │
│     │ 1:N          │ 1:N          │ 1:N             │ 1:N          │                  │
│     ▼              ▼              ▼                 ▼              │                  │
│  ┌────────────┐ ┌────────────┐ ┌────────────┐ ┌────────────┐      │                  │
│  │lead_stage_ │ │lead_commu- │ │  calls     │ │ meetings   │      │                  │
│  │history     │ │nications   │ │────────────│ │────────────│      │                  │
│  │────────────│ │────────────│ │PK id       │ │PK id       │      │                  │
│  │PK id       │ │PK id       │ │FK lead_id  │ │FK lead_id  │      │                  │
│  │FK lead_id  │ │FK lead_id  │ │FK user_id  │ │FK user_id  │      │                  │
│  │   from_stg │ │FK user_id  │ │FK agent_id │ │FK contact  │      │                  │
│  │   to_stage │ │   type     │ │   direction│ │   title    │      │                  │
│  │   changed  │ │   subject  │ │   status   │ │   start    │      │                  │
│  │   duration │ │   body     │ │   provider │ │   end      │      │                  │
│  └────────────┘ │   status   │ │   from/to  │ │   status   │      │                  │
│                 │   thread_id│ │   duration │ │   google_  │      │                  │
│                 └────────────┘ │   recording│ │   event_id │      │                  │
│                                └─────┬──────┘ └────────────┘      │                  │
│                                      │                            │                  │
│                                      │ 1:1                        │                  │
│                                      ▼                            │                  │
│                                ┌────────────┐                     │                  │
│                                │call_       │                     │                  │
│                                │transcripts │                     │                  │
│                                │────────────│                     │                  │
│                                │PK id       │                     │                  │
│                                │FK call_id  │◄────────────────────┘                  │
│                                │   segments │                                        │
│                                │   full_text│                                        │
│                                │   language │                                        │
│                                └─────┬──────┘                                        │
│                                      │ 1:N                                           │
│                                      ▼                                               │
│                                ┌────────────┐                                        │
│                                │call_trans- │                                        │
│                                │cript_embed │                                        │
│                                │────────────│                                        │
│                                │PK id       │                                        │
│                                │FK trans_id │                                        │
│                                │   chunk_idx│                                        │
│                                │   chunk_txt│                                        │
│                                │   embedding│◄── pgvector(1536)                      │
│                                └────────────┘                                        │
│                                                                                      │
│                                ┌────────────┐    ┌────────────────────────┐          │
│                                │call_       │    │     lead_views         │          │
│                                │outcomes    │    │────────────────────────│          │
│                                │────────────│    │PK id                   │          │
│                                │PK id       │    │FK user_id              │          │
│                                │FK call_id  │    │   name                 │          │
│                                │   summary  │    │   filters (JSONB)      │          │
│                                │   sentiment│    │   visible_columns      │          │
│                                │   classif  │    │   is_default           │          │
│                                │   ai_next  │    └────────────────────────┘          │
│                                └────────────┘                                        │
│                                                                                      │
│                                ┌────────────────────────────┐                        │
│                                │       follow_ups           │                        │
│                                │────────────────────────────│                        │
│                                │PK id                       │                        │
│                                │FK lead_id                  │                        │
│                                │FK user_id / contact_id     │                        │
│                                │FK call_id / agent_id       │                        │
│                                │   type (email/sms/callback)│                        │
│                                │   status                   │                        │
│                                │   scheduled_at             │                        │
│                                │   content                  │                        │
│                                └────────────────────────────┘                        │
└──────────────────────────────────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────────────────────────────────┐
│                                VOICE AGENT SYSTEM                                    │
│                                                                                      │
│  agents ◄───────────────────────────────────────────────────────────────────────┐    │
│     │                                                                           │    │
│     │ 1:N                                                                       │    │
│     ▼                                                                           │    │
│  ┌─────────────────┐         ┌─────────────────┐                               │    │
│  │ agent_sessions  │         │  agent_actions  │                               │    │
│  │─────────────────│         │─────────────────│                               │    │
│  │ PK id           │────────►│ PK id           │                               │    │
│  │ FK agent_id     │   1:N   │ FK session_id   │                               │    │
│  │ FK call_id      │         │ FK agent_id     │                               │    │
│  │ FK lead_id      │         │    action_type  │                               │    │
│  │    livekit_room │         │    status       │                               │    │
│  │    status       │         │    payload      │                               │    │
│  │    history      │         │    result       │                               │    │
│  │    context      │         │    duration_ms  │                               │    │
│  └─────────────────┘         └─────────────────┘                               │    │
│                                                                                 │    │
└─────────────────────────────────────────────────────────────────────────────────┘    │

┌──────────────────────────────────────────────────────────────────────────────────────┐
│                                CONVERTED CONTACTS                                    │
│                                                                                      │
│  leads.converted_to_contact_id ────────────────────────────────────────────┐        │
│                                                                            │        │
│                                                                            ▼        │
│                                                                   ┌─────────────────┐
│                                                                   │    contacts     │
│                                                                   │─────────────────│
│                                                                   │ PK id           │
│                                                                   │ FK user_id      │
│                                                                   │ FK converted_   │
│                                                                   │    from_lead_id │
│                                                                   │    first_name   │
│                                                                   │    last_name    │
│                                                                   │    email/phone  │
│                                                                   │    company      │
│                                                                   └─────────────────┘
│                                                                                      │
└──────────────────────────────────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────────────────────────────────┐
│                               JUNCTION TABLES (M:N)                                  │
│                                                                                      │
│   ┌─────────────┐              ┌─────────────┐                                       │
│   │  lead_tags  │              │  call_tags  │                                       │
│   │─────────────│              │─────────────│                                       │
│   │ PK,FK lead  │◄── leads     │ PK,FK call  │◄── calls                              │
│   │ PK,FK tag   │◄── tags      │ PK,FK tag   │◄── tags                               │
│   │    created  │              │    created  │                                       │
│   └─────────────┘              └─────────────┘                                       │
│                                                                                      │
└──────────────────────────────────────────────────────────────────────────────────────┘
```

### 2.2 Relationship Cardinality Summary

| Parent | Child | Cardinality | FK Column |
|--------|-------|-------------|-----------|
| auth.users | user_profiles | 1:1 | auth_user_id |
| user_profiles | leads | 1:N | user_id |
| user_profiles | leads (assigned) | 1:N | assigned_to |
| user_profiles | agents | 1:N | user_id |
| user_profiles | triggers | 1:N | user_id |
| user_profiles | tags | 1:N | user_id |
| user_profiles | lead_views | 1:N | user_id |
| user_profiles | contacts | 1:N | user_id |
| user_profiles | audit_logs | 1:N | user_id |
| leads | lead_stage_history | 1:N | lead_id |
| leads | lead_communications | 1:N | lead_id |
| leads | calls | 1:N | lead_id |
| leads | meetings | 1:N | lead_id |
| leads | follow_ups | 1:N | lead_id |
| leads | contacts | 1:1 | converted_to_contact_id |
| leads | lead_tags | 1:N | lead_id |
| agents | agent_sessions | 1:N | agent_id |
| agents | calls | 1:N | agent_id |
| agent_sessions | agent_actions | 1:N | session_id |
| calls | call_transcripts | 1:1 | call_id |
| calls | call_outcomes | 1:1 | call_id |
| calls | call_tags | 1:N | call_id |
| call_transcripts | call_transcript_embeddings | 1:N | transcript_id |
| triggers | trigger_executions | 1:N | trigger_id |
| tags | lead_tags | 1:N | tag_id |
| tags | call_tags | 1:N | tag_id |

### 2.3 Lead Lifecycle Flow

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│    NEW      │────►│  CONTACTED  │────►│  QUALIFIED  │────►│  PROPOSAL   │
└─────────────┘     └─────────────┘     └─────────────┘     └─────────────┘
                                                                   │
                    ┌─────────────┐     ┌─────────────┐            │
                    │    LOST     │◄────│ NEGOTIATION │◄───────────┘
                    └─────────────┘     └──────┬──────┘
                           │                   │
                           ▼                   ▼
                    ┌─────────────┐     ┌─────────────┐     ┌─────────────┐
                    │  ARCHIVED   │     │     WON     │────►│  CONTACT    │
                    └─────────────┘     └─────────────┘     │ (converted) │
                                                           └─────────────┘

Each transition recorded in: lead_stage_history
```

---

## 3. Tables Overview (21 tables)

### Core Tables
| Table | Purpose | Owner FK |
|-------|---------|----------|
| `user_profiles` | Links to auth.users, stores user settings | auth.users |
| `leads` | **Central CRM entity** - all interactions reference this | user_profiles |
| `lead_stage_history` | Track stage transitions with timestamps | leads |
| `contacts` | Converted leads or standalone contacts | user_profiles |

### Lead Interaction Tables
| Table | Purpose | Owner FK |
|-------|---------|----------|
| `lead_communications` | Email, SMS, chat history | leads |

### Voice Agent Tables
| Table | Purpose | Owner FK |
|-------|---------|----------|
| `agents` | Voice agent configurations | user_profiles |
| `calls` | Call records (inbound/outbound) | leads |
| `call_transcripts` | Full transcripts for RAG | calls |
| `call_transcript_embeddings` | Vector chunks for similarity search | call_transcripts |
| `call_outcomes` | AI analysis: sentiment, classification, summary | calls |
| `agent_sessions` | Active LiveKit sessions | agents |
| `agent_actions` | Tool execution audit log | agent_sessions |

### Calendar & Follow-up Tables
| Table | Purpose | Owner FK |
|-------|---------|----------|
| `meetings` | Calendar appointments | leads |
| `follow_ups` | Scheduled emails/SMS/callbacks | leads |

### Automation Tables
| Table | Purpose | Owner FK |
|-------|---------|----------|
| `triggers` | Automation rules | user_profiles |
| `trigger_executions` | Trigger audit log | triggers |

### Organization & Views
| Table | Purpose | Owner FK |
|-------|---------|----------|
| `tags` | Labels for leads/calls | user_profiles |
| `lead_tags` | Junction: lead <-> tag | - |
| `call_tags` | Junction: call <-> tag | - |
| `lead_views` | Saved filter configurations | user_profiles |
| `audit_logs` | Full audit trail for compliance | user_profiles |

---

## 4. Enum Types

```python
# backend/app/models/enums.py
from enum import Enum

# ========== LEAD ENUMS ==========

class LeadSource(str, Enum):
    FORM = "form"
    REFERRAL = "referral"
    EVENT = "event"
    IMPORT = "import"
    API = "api"
    MANUAL = "manual"
    ADVERTISEMENT = "advertisement"
    WEBSITE = "website"
    SOCIAL_MEDIA = "social_media"
    COLD_CALL = "cold_call"
    PARTNER = "partner"

class LeadStage(str, Enum):
    NEW = "new"
    CONTACTED = "contacted"
    QUALIFIED = "qualified"
    PROPOSAL = "proposal"
    NEGOTIATION = "negotiation"
    WON = "won"
    LOST = "lost"
    ARCHIVED = "archived"

class LeadTemperature(str, Enum):
    HOT = "hot"
    WARM = "warm"
    COLD = "cold"

class CommunicationType(str, Enum):
    EMAIL_INBOUND = "email_inbound"
    EMAIL_OUTBOUND = "email_outbound"
    SMS_INBOUND = "sms_inbound"
    SMS_OUTBOUND = "sms_outbound"
    CHAT = "chat"
    WHATSAPP_INBOUND = "whatsapp_inbound"
    WHATSAPP_OUTBOUND = "whatsapp_outbound"

# ========== CALL ENUMS ==========

class CallDirection(str, Enum):
    INBOUND = "inbound"
    OUTBOUND = "outbound"

class CallStatus(str, Enum):
    INITIATED = "initiated"
    RINGING = "ringing"
    IN_PROGRESS = "in_progress"
    COMPLETED = "completed"
    FAILED = "failed"
    NO_ANSWER = "no_answer"
    BUSY = "busy"
    CANCELLED = "cancelled"

class TelephonyProvider(str, Enum):
    TWILIO = "twilio"
    FREEPBX = "freepbx"
    LIVEKIT = "livekit"

# ========== MEETING ENUMS ==========

class MeetingStatus(str, Enum):
    SCHEDULED = "scheduled"
    CONFIRMED = "confirmed"
    IN_PROGRESS = "in_progress"
    COMPLETED = "completed"
    CANCELLED = "cancelled"
    NO_SHOW = "no_show"

# ========== AGENT ENUMS ==========

class AgentSessionStatus(str, Enum):
    ACTIVE = "active"
    PAUSED = "paused"
    ENDED = "ended"
    ERROR = "error"

class ActionStatus(str, Enum):
    PENDING = "pending"
    IN_PROGRESS = "in_progress"
    COMPLETED = "completed"
    FAILED = "failed"

class ActionType(str, Enum):
    CHECK_AVAILABILITY = "check_availability"
    BOOK_MEETING = "book_meeting"
    CANCEL_MEETING = "cancel_meeting"
    RESCHEDULE_MEETING = "reschedule_meeting"
    SEND_EMAIL = "send_email"
    SEND_SMS = "send_sms"
    LOOKUP_LEAD = "lookup_lead"
    UPDATE_LEAD = "update_lead"
    TRANSFER_CALL = "transfer_call"
    EMERGENCY_ESCALATION = "emergency_escalation"

# ========== FOLLOW-UP ENUMS ==========

class FollowUpType(str, Enum):
    EMAIL = "email"
    SMS = "sms"
    CALLBACK = "callback"

class FollowUpStatus(str, Enum):
    SCHEDULED = "scheduled"
    IN_PROGRESS = "in_progress"
    SENT = "sent"
    DELIVERED = "delivered"
    FAILED = "failed"
    CANCELLED = "cancelled"

# ========== ANALYSIS ENUMS ==========

class SentimentClassification(str, Enum):
    VERY_POSITIVE = "very_positive"
    POSITIVE = "positive"
    NEUTRAL = "neutral"
    NEGATIVE = "negative"
    VERY_NEGATIVE = "very_negative"

class CallClassification(str, Enum):
    BOOKING_REQUEST = "booking_request"
    SUPPORT_REQUEST = "support_request"
    SALES_INQUIRY = "sales_inquiry"
    COMPLAINT = "complaint"
    GENERAL_INQUIRY = "general_inquiry"
    CANCELLATION = "cancellation"
    EMERGENCY = "emergency"
    SPAM = "spam"
    OTHER = "other"

# ========== TRIGGER ENUMS ==========

class TriggerType(str, Enum):
    SCHEDULED = "scheduled"
    MANUAL = "manual"
    REAL_TIME = "real_time"
    EVENT_BASED = "event_based"
```

---

## 5. Core Table Schemas

### 5.1 UserProfile (links to Supabase auth.users)
```python
class UserProfile(BaseUUIDModel, table=True):
    __tablename__ = "user_profiles"

    auth_user_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("auth.users(id)", ondelete="CASCADE"),
                        unique=True, nullable=False, index=True)
    )
    email: str = Field(max_length=255, index=True)
    full_name: str | None = Field(default=None, max_length=255)
    phone: str | None = Field(default=None, max_length=50)
    timezone: str = Field(default="UTC", max_length=50)
    role: str = Field(default="user", max_length=50)  # admin, manager, user

    # Google OAuth for calendar
    google_refresh_token_encrypted: str | None = Field(default=None)
    google_calendar_id: str | None = Field(default=None, max_length=255)

    # Assignment tracking
    max_leads: int | None = Field(default=None)  # For load balancing
    current_lead_count: int = Field(default=0)

    settings: dict | None = Field(default=None, sa_column=Column(JSONB))
    is_active: bool = Field(default=True)
    deleted_at: datetime | None = Field(default=None)
```

---

### 5.2 Lead (Central CRM Entity)
```python
class Lead(BaseUUIDModel, table=True):
    """
    Central CRM entity. All interactions reference this table.
    """
    __tablename__ = "leads"

    user_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )

    # Basic Info
    first_name: str = Field(max_length=100)
    last_name: str = Field(max_length=100)
    email: str | None = Field(default=None, max_length=255, index=True)
    phone: str | None = Field(default=None, max_length=50, index=True)
    phone_normalized: str | None = Field(default=None, max_length=20, index=True)  # E.164
    company: str | None = Field(default=None, max_length=255)
    job_title: str | None = Field(default=None, max_length=255)
    website: str | None = Field(default=None, max_length=500)

    # Location
    address: str | None = Field(default=None, max_length=500)
    city: str | None = Field(default=None, max_length=100)
    state: str | None = Field(default=None, max_length=100)
    postal_code: str | None = Field(default=None, max_length=20)
    country: str | None = Field(default=None, max_length=100)
    timezone: str | None = Field(default=None, max_length=50)

    # Lead Source & Stage
    source: LeadSource = Field(sa_column=Column(SQLEnum(LeadSource), index=True))
    source_detail: str | None = Field(default=None, max_length=255)  # e.g., campaign name
    stage: LeadStage = Field(default=LeadStage.NEW, sa_column=Column(SQLEnum(LeadStage), index=True))
    previous_stage: LeadStage | None = Field(default=None, sa_column=Column(SQLEnum(LeadStage)))
    stage_changed_at: datetime | None = Field(default=None)

    # Scoring & Temperature
    lead_score: int = Field(default=0, index=True)
    temperature: LeadTemperature = Field(
        default=LeadTemperature.WARM,
        sa_column=Column(SQLEnum(LeadTemperature), index=True)
    )

    # Assignment
    assigned_to: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="SET NULL"), index=True)
    )
    assigned_at: datetime | None = Field(default=None)
    assigned_by: UUID | None = Field(default=None)

    # Program/Product Interest (flexible)
    interests: list[str] | None = Field(default=None, sa_column=Column(JSONB))

    # External References
    external_id: str | None = Field(default=None, max_length=255, index=True)
    external_source: str | None = Field(default=None, max_length=100)

    # Conversion Tracking
    converted_at: datetime | None = Field(default=None)
    converted_to_contact_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("contacts.id", ondelete="SET NULL"))
    )
    lost_reason: str | None = Field(default=None, max_length=500)

    # Communication Preferences
    preferred_contact_method: str | None = Field(default=None, max_length=20)
    do_not_call: bool = Field(default=False)
    do_not_email: bool = Field(default=False)
    do_not_sms: bool = Field(default=False)

    # Timestamps
    first_contacted_at: datetime | None = Field(default=None)
    last_contacted_at: datetime | None = Field(default=None)
    last_activity_at: datetime | None = Field(default=None)

    # Custom Fields
    custom_fields: dict | None = Field(default=None, sa_column=Column(JSONB))
    metadata: dict | None = Field(default=None, sa_column=Column(JSONB))

    # Soft Delete
    is_active: bool = Field(default=True)
    deleted_at: datetime | None = Field(default=None)

    __table_args__ = (
        Index("ix_leads_user_stage", "user_id", "stage"),
        Index("ix_leads_assigned_stage", "assigned_to", "stage"),
        Index("ix_leads_user_score", "user_id", "lead_score"),
        Index("ix_leads_full_name", "first_name", "last_name"),
    )

    @property
    def full_name(self) -> str:
        return f"{self.first_name} {self.last_name}".strip()
```

---

### 5.3 LeadStageHistory
```python
class LeadStageHistory(BaseUUIDModel, table=True):
    """
    Track all stage transitions for analytics and audit.
    """
    __tablename__ = "lead_stage_history"

    lead_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("leads.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )
    from_stage: LeadStage | None = Field(default=None, sa_column=Column(SQLEnum(LeadStage)))
    to_stage: LeadStage = Field(sa_column=Column(SQLEnum(LeadStage)))
    changed_by: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="SET NULL"))
    )
    reason: str | None = Field(default=None, max_length=500)
    duration_in_previous_stage_seconds: int | None = Field(default=None)

    __table_args__ = (
        Index("ix_lead_stage_history_lead_created", "lead_id", "created_at"),
    )
```

---

### 5.4 LeadCommunication
```python
class LeadCommunication(BaseUUIDModel, table=True):
    """
    Email, SMS, chat history for a lead.
    """
    __tablename__ = "lead_communications"

    lead_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("leads.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )
    user_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )

    communication_type: CommunicationType = Field(
        sa_column=Column(SQLEnum(CommunicationType), index=True)
    )

    # For email
    subject: str | None = Field(default=None, max_length=500)
    from_address: str | None = Field(default=None, max_length=255)
    to_address: str | None = Field(default=None, max_length=255)

    # For SMS
    from_phone: str | None = Field(default=None, max_length=50)
    to_phone: str | None = Field(default=None, max_length=50)

    # Content
    body: str = Field(sa_column=Column(Text))
    body_html: str | None = Field(default=None, sa_column=Column(Text))

    # Delivery tracking
    provider_message_id: str | None = Field(default=None, max_length=255)
    status: str = Field(default="sent", max_length=50)  # sent, delivered, read, failed, bounced
    delivered_at: datetime | None = Field(default=None)
    read_at: datetime | None = Field(default=None)
    error_message: str | None = Field(default=None)

    # Threading
    thread_id: str | None = Field(default=None, max_length=255, index=True)
    in_reply_to_id: UUID | None = Field(default=None)

    metadata: dict | None = Field(default=None, sa_column=Column(JSONB))

    __table_args__ = (
        Index("ix_lead_communications_lead_created", "lead_id", "created_at"),
    )
```

---

### 5.5 LeadView (Saved Filters)
```python
class LeadView(BaseUUIDModel, table=True):
    """
    Saved filter configurations per user.
    """
    __tablename__ = "lead_views"

    user_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )

    name: str = Field(max_length=100)
    description: str | None = Field(default=None, max_length=255)

    # Filter configuration
    filters: dict = Field(sa_column=Column(JSONB))
    # Example: {"stage": ["new", "contacted"], "temperature": "hot", "assigned_to": "me"}

    # Column visibility
    visible_columns: list[str] = Field(sa_column=Column(JSONB))
    # Example: ["full_name", "email", "phone", "stage", "score", "last_activity"]

    # Sort configuration
    sort_field: str = Field(default="created_at", max_length=50)
    sort_direction: str = Field(default="desc", max_length=4)  # asc, desc

    # View settings
    is_default: bool = Field(default=False)
    is_shared: bool = Field(default=False)  # Shared with team
    color: str | None = Field(default=None, max_length=7)  # Hex color
    icon: str | None = Field(default=None, max_length=50)

    __table_args__ = (
        UniqueConstraint("user_id", "name", name="uq_lead_view_user_name"),
    )
```

---

### 5.6 Contact (Converted Leads or Standalone)
```python
class Contact(BaseUUIDModel, table=True):
    """
    Converted leads become contacts. Can also be standalone.
    """
    __tablename__ = "contacts"

    user_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )

    # Link to original lead
    converted_from_lead_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("leads.id", ondelete="SET NULL"), index=True)
    )

    first_name: str = Field(max_length=100)
    last_name: str = Field(max_length=100)
    email: str | None = Field(default=None, max_length=255, index=True)
    phone: str | None = Field(default=None, max_length=50, index=True)
    phone_normalized: str | None = Field(default=None, max_length=20, index=True)
    company: str | None = Field(default=None, max_length=255)
    job_title: str | None = Field(default=None, max_length=255)
    timezone: str | None = Field(default=None, max_length=50)

    external_id: str | None = Field(default=None, max_length=255)
    external_source: str | None = Field(default=None, max_length=100)

    notes: str | None = Field(default=None)
    metadata: dict | None = Field(default=None, sa_column=Column(JSONB))
    is_active: bool = Field(default=True)
    deleted_at: datetime | None = Field(default=None)

    @property
    def full_name(self) -> str:
        return f"{self.first_name} {self.last_name}".strip()
```

---

### 5.7 Tag + Junction Tables
```python
class Tag(BaseUUIDModel, table=True):
    __tablename__ = "tags"

    user_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )
    name: str = Field(max_length=100, index=True)
    color: str | None = Field(default=None, max_length=7)
    description: str | None = Field(default=None, max_length=255)

    __table_args__ = (UniqueConstraint("user_id", "name", name="uq_tag_user_name"),)


class LeadTag(SQLModel, table=True):
    __tablename__ = "lead_tags"
    lead_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("leads.id", ondelete="CASCADE"), primary_key=True))
    tag_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("tags.id", ondelete="CASCADE"), primary_key=True))
    created_at: datetime = Field(default_factory=datetime.utcnow)


class CallTag(SQLModel, table=True):
    __tablename__ = "call_tags"
    call_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="CASCADE"), primary_key=True))
    tag_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("tags.id", ondelete="CASCADE"), primary_key=True))
    created_at: datetime = Field(default_factory=datetime.utcnow)
```

---

### 5.8 Agent
```python
class Agent(BaseUUIDModel, table=True):
    __tablename__ = "agents"

    user_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )

    name: str = Field(max_length=255, index=True)
    description: str | None = Field(default=None)

    model_name: str = Field(max_length=100)
    model_provider: str = Field(default="openai", max_length=50)

    system_prompt: str = Field(sa_column=Column(Text))
    voice_id: str | None = Field(default=None, max_length=100)
    language: str = Field(default="en-US", max_length=10)

    livekit_config: dict | None = Field(default=None, sa_column=Column(JSONB))
    allowed_tools: list[str] = Field(default=[], sa_column=Column(JSONB))

    max_call_duration_seconds: int = Field(default=1800)
    max_concurrent_calls: int = Field(default=10)

    settings: dict | None = Field(default=None, sa_column=Column(JSONB))
    is_active: bool = Field(default=True)
    deleted_at: datetime | None = Field(default=None)
```

---

### 5.9 Call (References Lead)
```python
class Call(BaseUUIDModel, table=True):
    """
    Call records - tied to Leads as the primary reference.
    """
    __tablename__ = "calls"

    user_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )

    # Primary reference is to Lead
    lead_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("leads.id", ondelete="SET NULL"), index=True)
    )

    # Secondary reference to Contact (for converted leads or standalone contacts)
    contact_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("contacts.id", ondelete="SET NULL"), index=True)
    )

    agent_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("agents.id", ondelete="SET NULL"), index=True)
    )

    direction: CallDirection = Field(sa_column=Column(SQLEnum(CallDirection)))
    status: CallStatus = Field(default=CallStatus.INITIATED, sa_column=Column(SQLEnum(CallStatus), index=True))
    telephony_provider: TelephonyProvider = Field(sa_column=Column(SQLEnum(TelephonyProvider)))

    provider_call_id: str | None = Field(default=None, max_length=255, index=True)
    from_number: str = Field(max_length=50, index=True)
    to_number: str = Field(max_length=50, index=True)

    initiated_at: datetime = Field(default_factory=datetime.utcnow, index=True)
    answered_at: datetime | None = Field(default=None)
    ended_at: datetime | None = Field(default=None)
    duration_seconds: int | None = Field(default=None)

    recording_url: str | None = Field(default=None)
    recording_duration_seconds: int | None = Field(default=None)
    recording_storage_path: str | None = Field(default=None)

    livekit_room_name: str | None = Field(default=None, max_length=255, index=True)
    livekit_room_id: str | None = Field(default=None, max_length=255)

    # Call disposition
    disposition: str | None = Field(default=None, max_length=100)
    notes: str | None = Field(default=None, sa_column=Column(Text))

    metadata: dict | None = Field(default=None, sa_column=Column(JSONB))
    cost_cents: int | None = Field(default=None)

    __table_args__ = (
        Index("ix_calls_lead_initiated", "lead_id", "initiated_at"),
        Index("ix_calls_user_initiated", "user_id", "initiated_at"),
    )
```

---

### 5.10 CallTranscript
```python
class CallTranscript(BaseUUIDModel, table=True):
    __tablename__ = "call_transcripts"

    call_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="CASCADE"),
                        nullable=False, unique=True, index=True)
    )

    segments: list[dict] = Field(sa_column=Column(JSONB))
    full_text: str = Field(sa_column=Column(Text))
    language: str | None = Field(default=None, max_length=10)

    embedding_model: str | None = Field(default=None, max_length=100)
    embedding_status: str = Field(default="pending", max_length=20)
    last_embedded_at: datetime | None = Field(default=None)

    transcription_provider: str | None = Field(default=None, max_length=50)
    transcription_confidence: float | None = Field(default=None)
    word_count: int | None = Field(default=None)
    speaker_count: int | None = Field(default=None)
```

---

### 5.11 CallTranscriptEmbedding (pgvector)
```python
class CallTranscriptEmbedding(BaseUUIDModel, table=True):
    __tablename__ = "call_transcript_embeddings"

    transcript_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("call_transcripts.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )

    chunk_index: int = Field(nullable=False)
    chunk_text: str = Field(sa_column=Column(Text))
    chunk_start_ms: int | None = Field(default=None)
    chunk_end_ms: int | None = Field(default=None)

    embedding: list[float] = Field(sa_column=Column(Vector(1536)))

    metadata: dict | None = Field(default=None, sa_column=Column(JSONB))

    __table_args__ = (
        Index("ix_transcript_embeddings_vector", "embedding",
              postgresql_using="ivfflat",
              postgresql_with={"lists": 100},
              postgresql_ops={"embedding": "vector_cosine_ops"}),
    )
```

---

### 5.12 CallOutcome
```python
class CallOutcome(BaseUUIDModel, table=True):
    __tablename__ = "call_outcomes"

    call_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="CASCADE"),
                        nullable=False, unique=True, index=True)
    )

    summary: str | None = Field(default=None, sa_column=Column(Text))
    summary_model: str | None = Field(default=None, max_length=100)

    sentiment: SentimentClassification | None = Field(default=None, sa_column=Column(SQLEnum(SentimentClassification)))
    sentiment_score: float | None = Field(default=None)
    sentiment_confidence: float | None = Field(default=None)

    classification: CallClassification | None = Field(default=None, sa_column=Column(SQLEnum(CallClassification), index=True))
    classification_confidence: float | None = Field(default=None)

    topics: list[str] | None = Field(default=None, sa_column=Column(JSONB))
    entities: dict | None = Field(default=None, sa_column=Column(JSONB))
    action_items: list[dict] | None = Field(default=None, sa_column=Column(JSONB))

    # AI-suggested next steps
    ai_next_steps: list[str] | None = Field(default=None, sa_column=Column(JSONB))
    ai_recommended_stage: LeadStage | None = Field(default=None, sa_column=Column(SQLEnum(LeadStage)))

    talk_ratio: float | None = Field(default=None)
    interruption_count: int | None = Field(default=None)
    silence_percentage: float | None = Field(default=None)

    was_resolved: bool | None = Field(default=None)
    resolution_notes: str | None = Field(default=None)

    custom_fields: dict | None = Field(default=None, sa_column=Column(JSONB))
    analyzed_at: datetime | None = Field(default=None)
    analysis_version: str | None = Field(default=None, max_length=20)
```

---

### 5.13 AgentSession
```python
class AgentSession(BaseUUIDModel, table=True):
    __tablename__ = "agent_sessions"

    agent_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("agents.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )
    call_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="SET NULL"), index=True)
    )
    lead_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("leads.id", ondelete="SET NULL"), index=True)
    )

    livekit_room_name: str = Field(max_length=255, unique=True, index=True)
    livekit_room_id: str | None = Field(default=None, max_length=255)
    livekit_participant_id: str | None = Field(default=None, max_length=255)

    status: AgentSessionStatus = Field(default=AgentSessionStatus.ACTIVE, sa_column=Column(SQLEnum(AgentSessionStatus), index=True))

    history: list[dict] = Field(default=[], sa_column=Column(JSONB))
    context: dict | None = Field(default=None, sa_column=Column(JSONB))

    started_at: datetime = Field(default_factory=datetime.utcnow)
    ended_at: datetime | None = Field(default=None)

    message_count: int = Field(default=0)
    tool_call_count: int = Field(default=0)
```

---

### 5.14 AgentAction
```python
class AgentAction(BaseUUIDModel, table=True):
    __tablename__ = "agent_actions"

    session_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("agent_sessions.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )
    agent_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("agents.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )

    action_type: ActionType = Field(sa_column=Column(SQLEnum(ActionType), index=True))
    status: ActionStatus = Field(default=ActionStatus.PENDING, sa_column=Column(SQLEnum(ActionStatus), index=True))

    payload: dict = Field(sa_column=Column(JSONB))
    result: dict | None = Field(default=None, sa_column=Column(JSONB))
    error_message: str | None = Field(default=None)

    initiated_at: datetime = Field(default_factory=datetime.utcnow)
    completed_at: datetime | None = Field(default=None)
    duration_ms: int | None = Field(default=None)

    triggered_by: str | None = Field(default=None, max_length=50)
```

---

### 5.15 Meeting (References Lead)
```python
class Meeting(BaseUUIDModel, table=True):
    __tablename__ = "meetings"

    user_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )

    # Primary reference to Lead
    lead_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("leads.id", ondelete="SET NULL"), index=True)
    )

    contact_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("contacts.id", ondelete="SET NULL"), index=True)
    )

    call_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="SET NULL"), index=True)
    )

    google_event_id: str | None = Field(default=None, max_length=255, index=True)
    google_calendar_id: str | None = Field(default=None, max_length=255)

    title: str = Field(max_length=255)
    description: str | None = Field(default=None, sa_column=Column(Text))
    location: str | None = Field(default=None, max_length=500)
    meeting_link: str | None = Field(default=None, max_length=500)
    meeting_provider: str | None = Field(default=None, max_length=50)

    start_time: datetime = Field(index=True)
    end_time: datetime
    timezone: str = Field(default="UTC", max_length=50)
    is_all_day: bool = Field(default=False)

    status: MeetingStatus = Field(default=MeetingStatus.SCHEDULED, sa_column=Column(SQLEnum(MeetingStatus), index=True))

    reminders: list[dict] | None = Field(default=None, sa_column=Column(JSONB))
    attendees: list[dict] | None = Field(default=None, sa_column=Column(JSONB))
    recurrence_rule: str | None = Field(default=None, max_length=255)

    notes: str | None = Field(default=None, sa_column=Column(Text))
    outcome: str | None = Field(default=None, sa_column=Column(Text))

    cancelled_at: datetime | None = Field(default=None)
    cancellation_reason: str | None = Field(default=None, max_length=255)

    __table_args__ = (
        Index("ix_meetings_lead_time", "lead_id", "start_time"),
        Index("ix_meetings_user_time", "user_id", "start_time"),
    )
```

---

### 5.16 FollowUp (References Lead)
```python
class FollowUp(BaseUUIDModel, table=True):
    __tablename__ = "follow_ups"

    user_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )

    # Primary reference to Lead
    lead_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("leads.id", ondelete="SET NULL"), index=True)
    )

    contact_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("contacts.id", ondelete="SET NULL"), index=True)
    )

    call_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="SET NULL"), index=True)
    )

    follow_up_type: FollowUpType = Field(sa_column=Column(SQLEnum(FollowUpType), index=True))
    status: FollowUpStatus = Field(default=FollowUpStatus.SCHEDULED, sa_column=Column(SQLEnum(FollowUpStatus), index=True))

    scheduled_at: datetime = Field(index=True)
    executed_at: datetime | None = Field(default=None)

    subject: str | None = Field(default=None, max_length=255)
    content: str = Field(sa_column=Column(Text))
    content_template_id: str | None = Field(default=None, max_length=100)

    recipient_email: str | None = Field(default=None, max_length=255)
    recipient_phone: str | None = Field(default=None, max_length=50)
    callback_agent_id: UUID | None = Field(default=None, sa_column=Column(PG_UUID, ForeignKey("agents.id", ondelete="SET NULL")))

    max_retries: int = Field(default=3)
    retry_count: int = Field(default=0)
    last_error: str | None = Field(default=None)

    trigger_type: TriggerType = Field(default=TriggerType.SCHEDULED, sa_column=Column(SQLEnum(TriggerType)))
    triggered_by_action_id: UUID | None = Field(default=None)

    delivery_id: str | None = Field(default=None, max_length=255)
    delivery_status: str | None = Field(default=None, max_length=50)

    metadata: dict | None = Field(default=None, sa_column=Column(JSONB))

    __table_args__ = (
        Index("ix_follow_ups_lead_scheduled", "lead_id", "scheduled_at"),
        Index("ix_follow_ups_status_scheduled", "status", "scheduled_at"),
    )
```

---

### 5.17 Trigger + TriggerExecution
```python
class Trigger(BaseUUIDModel, table=True):
    __tablename__ = "triggers"

    user_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )
    agent_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("agents.id", ondelete="SET NULL"), index=True)
    )

    name: str = Field(max_length=255)
    description: str | None = Field(default=None)

    trigger_type: TriggerType = Field(sa_column=Column(SQLEnum(TriggerType), index=True))

    conditions: dict = Field(sa_column=Column(JSONB))
    actions: list[dict] = Field(sa_column=Column(JSONB))

    is_active: bool = Field(default=True)
    last_triggered_at: datetime | None = Field(default=None)
    trigger_count: int = Field(default=0)
    next_run_at: datetime | None = Field(default=None, index=True)


class TriggerExecution(BaseUUIDModel, table=True):
    __tablename__ = "trigger_executions"

    trigger_id: UUID = Field(
        sa_column=Column(PG_UUID, ForeignKey("triggers.id", ondelete="CASCADE"),
                        nullable=False, index=True)
    )

    triggered_by_event: str | None = Field(default=None, max_length=100)
    triggered_by_entity_type: str | None = Field(default=None, max_length=50)
    triggered_by_entity_id: UUID | None = Field(default=None)

    executed_at: datetime = Field(default_factory=datetime.utcnow, index=True)
    success: bool = Field(default=True)

    actions_executed: list[dict] = Field(sa_column=Column(JSONB))
    errors: list[str] | None = Field(default=None, sa_column=Column(JSONB))
    duration_ms: int | None = Field(default=None)
```

---

### 5.18 AuditLog
```python
class AuditLog(BaseUUIDModel, table=True):
    __tablename__ = "audit_logs"

    user_id: UUID | None = Field(
        default=None,
        sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="SET NULL"))
    )

    table_name: str = Field(max_length=100, index=True)
    record_id: UUID = Field(index=True)
    action: str = Field(max_length=20)

    old_values: dict | None = Field(default=None, sa_column=Column(JSONB))
    new_values: dict | None = Field(default=None, sa_column=Column(JSONB))
    changed_fields: list[str] | None = Field(default=None, sa_column=Column(JSONB))

    ip_address: str | None = Field(default=None, max_length=45)
    user_agent: str | None = Field(default=None, max_length=500)
    metadata: dict | None = Field(default=None, sa_column=Column(JSONB))

    __table_args__ = (
        Index("ix_audit_logs_table_record", "table_name", "record_id"),
        Index("ix_audit_logs_created", "created_at"),
    )
```

---

## 6. Migration Order

```
Phase 1: Foundation
├── 1.1 Enable extensions (uuid-ossp, pgvector)
├── 1.2 Create all ENUM types

Phase 2: Core User
├── 2.1 user_profiles

Phase 3: Central Entity (Leads)
├── 3.1 leads
├── 3.2 lead_stage_history
├── 3.3 lead_views

Phase 4: Supporting Entities
├── 4.1 contacts
├── 4.2 agents
├── 4.3 tags
├── 4.4 triggers
├── 4.5 audit_logs

Phase 5: Lead Interaction Tables
├── 5.1 lead_communications

Phase 6: Voice & Calendar
├── 6.1 calls
├── 6.2 meetings
├── 6.3 agent_sessions
├── 6.4 follow_ups

Phase 7: Analysis & Embeddings
├── 7.1 call_transcripts
├── 7.2 call_outcomes
├── 7.3 agent_actions
├── 7.4 trigger_executions

Phase 8: Child Tables
├── 8.1 call_transcript_embeddings
├── 8.2 lead_tags
├── 8.3 call_tags

Phase 9: Database Features
├── 9.1 Create audit triggers
├── 9.2 Create RLS policies
├── 9.3 Create indexes
├── 9.4 Create FTS indexes
```

---

## 7. RLS Policies (Multi-tenancy)

```sql
-- Enable RLS on all tables
ALTER TABLE leads ENABLE ROW LEVEL SECURITY;
ALTER TABLE lead_communications ENABLE ROW LEVEL SECURITY;
-- ... all other tables

-- Helper function
CREATE OR REPLACE FUNCTION get_my_profile_id()
RETURNS UUID AS $$
  SELECT id FROM user_profiles WHERE auth_user_id = auth.uid()
$$ LANGUAGE SQL SECURITY DEFINER;

-- Lead policies
CREATE POLICY leads_select ON leads
  FOR SELECT USING (user_id = get_my_profile_id() OR assigned_to = get_my_profile_id());

CREATE POLICY leads_insert ON leads
  FOR INSERT WITH CHECK (user_id = get_my_profile_id());

CREATE POLICY leads_update ON leads
  FOR UPDATE USING (user_id = get_my_profile_id() OR assigned_to = get_my_profile_id());

CREATE POLICY leads_delete ON leads
  FOR DELETE USING (user_id = get_my_profile_id());

-- Child table policies (via lead join)
CREATE POLICY lead_communications_all ON lead_communications
  FOR ALL USING (
    EXISTS (SELECT 1 FROM leads WHERE leads.id = lead_id
            AND (leads.user_id = get_my_profile_id() OR leads.assigned_to = get_my_profile_id()))
  );

-- Apply similar patterns to all lead-related tables
```

---

## 8. Stage Change Trigger

```sql
-- Track stage changes in history
CREATE OR REPLACE FUNCTION track_lead_stage_change()
RETURNS TRIGGER AS $$
BEGIN
  IF OLD.stage IS DISTINCT FROM NEW.stage THEN
    -- Record in stage history
    INSERT INTO lead_stage_history (id, lead_id, from_stage, to_stage, changed_by, duration_in_previous_stage_seconds, created_at)
    VALUES (
      gen_random_uuid(),
      NEW.id,
      OLD.stage,
      NEW.stage,
      current_setting('app.current_user_id', true)::uuid,
      EXTRACT(EPOCH FROM (now() - COALESCE(OLD.stage_changed_at, OLD.created_at)))::int,
      now()
    );
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER leads_stage_change_trigger AFTER UPDATE ON leads
  FOR EACH ROW EXECUTE FUNCTION track_lead_stage_change();
```

---

## 9. Files to Create

### Models
```
backend/app/models/
├── __init__.py
├── enums.py
├── user_profile.py
├── lead.py                    # Lead, LeadStageHistory, LeadView
├── lead_communication.py
├── contact.py
├── tag.py                     # Tag, LeadTag, CallTag
├── agent.py                   # Agent, AgentSession, AgentAction
├── call.py                    # Call, CallTranscript, CallOutcome
├── embedding.py               # CallTranscriptEmbedding
├── meeting.py
├── follow_up.py
├── trigger.py                 # Trigger, TriggerExecution
└── audit_log.py
```

### Schemas
```
backend/app/schemas/
├── __init__.py
├── user_profile.py
├── lead.py
├── lead_communication.py
├── contact.py
├── agent.py
├── call.py
├── meeting.py
├── follow_up.py
└── trigger.py
```

### CRUD
```
backend/app/crud/
├── __init__.py
├── user_profile.py
├── lead.py
├── lead_communication.py
├── contact.py
├── agent.py
├── call.py
├── meeting.py
├── follow_up.py
└── trigger.py
```

### Routers
```
backend/app/api/v1/routers/
├── __init__.py
├── leads.py
├── lead_communications.py
├── contacts.py
├── agents.py
├── calls.py
├── meetings.py
├── follow_ups.py
└── triggers.py
```

---

## 10. Lead Detail Page Data Model

When viewing a Lead detail page, the frontend queries:

```python
# Lead Summary
GET /api/v1/leads/{lead_id}
# Returns: Lead with properties, score, stage, assigned_to

# Communication History (emails, SMS, chat)
GET /api/v1/leads/{lead_id}/communications
# Returns: Paginated emails, SMS, chat messages

# Call History (voice interactions)
GET /api/v1/leads/{lead_id}/calls
# Returns: Paginated calls with outcomes, transcripts available

# Scheduled Meetings (calendar)
GET /api/v1/leads/{lead_id}/meetings
# Returns: Past and upcoming meetings

# AI Chatbot / RAG Query
POST /api/v1/leads/{lead_id}/chat
# Body: {"query": "What did we discuss about pricing?"}
# Returns: AI response with context from call transcripts

# AI Insights (computed)
GET /api/v1/leads/{lead_id}/insights
# Returns: AI summary, recommended next steps, sentiment trends
```

---

## 11. RAG Queries (Lead-Scoped)

```python
# Search transcripts for a specific lead
async def search_lead_transcripts(lead_id: UUID, query: str, limit: int = 5):
    embedding = await get_embedding(query)

    results = await db.execute("""
        SELECT cte.chunk_text, cte.chunk_index, c.id as call_id
        FROM call_transcript_embeddings cte
        JOIN call_transcripts ct ON ct.id = cte.transcript_id
        JOIN calls c ON c.id = ct.call_id
        WHERE c.lead_id = :lead_id
        ORDER BY cte.embedding <=> :embedding
        LIMIT :limit
    """, {"lead_id": lead_id, "embedding": embedding, "limit": limit})

    return results


# AI Chat endpoint for lead
async def lead_chat(lead_id: UUID, query: str):
    # 1. Get relevant transcript chunks via RAG
    chunks = await search_lead_transcripts(lead_id, query)

    # 2. Get lead context
    lead = await get_lead(lead_id)

    # 3. Build prompt with context
    context = f"""
    Lead: {lead.full_name} ({lead.company})
    Stage: {lead.stage}
    Recent calls: {len(chunks)} relevant segments found

    Relevant conversation excerpts:
    {format_chunks(chunks)}
    """

    # 4. Call LLM with context
    response = await llm.chat(
        system="You are an AI assistant helping with CRM insights.",
        messages=[{"role": "user", "content": f"{context}\n\nQuestion: {query}"}]
    )

    return response
```

---

## 12. Design Decisions Summary

| Decision | Choice | Rationale |
|----------|--------|-----------|
| Central Entity | Leads | All CRM interactions reference leads |
| Vector DB | pgvector | Built into Supabase |
| Embedding dim | 1536 | OpenAI ada-002 compatible |
| Audit log | Yes | Compliance requirement |
| Telephony | All 3 | Twilio, FreePBX, LiveKit |
| Soft delete | Yes | Leads, contacts, agents |
| Multi-tenancy | RLS | Supabase-native |
| Stage tracking | Full history | Analytics on conversion funnel |
| Lead Page Focus | Communications, Calendar, RAG Chatbot | Streamlined UX |