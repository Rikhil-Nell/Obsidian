# CRM Database Schema Plan for Voice Agent Platform

## Overview
Database schema for a CRM component of a voice agent platform with telephony integration (LiveKit, FreePBX, Twilio), post-call analysis with RAG, and automation triggers.

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
- Multi-tenancy via `user_id` foreign keys + Row Level Security (RLS)
- Soft deletes with `deleted_at` timestamps where appropriate
- JSONB for flexible configuration fields

---

## 2. Tables Overview (17 tables)

| Table | Purpose | Owner FK |
|-------|---------|----------|
| `user_profiles` | Links to auth.users, stores user settings | auth.users |
| `contacts` | CRM contacts | user_profiles |
| `tags` | Labels for contacts/calls | user_profiles |
| `contact_tags` | Junction: contact <-> tag | - |
| `agents` | Voice agent configurations | user_profiles |
| `calls` | Call records (inbound/outbound) | user_profiles |
| `call_tags` | Junction: call <-> tag | - |
| `call_transcripts` | Full transcripts for RAG | calls |
| `call_transcript_embeddings` | Vector chunks for similarity search | call_transcripts |
| `call_outcomes` | AI analysis: sentiment, classification, summary | calls |
| `agent_sessions` | Active LiveKit sessions | agents |
| `agent_actions` | Tool execution audit log | agent_sessions |
| `meetings` | Calendar appointments | user_profiles |
| `follow_ups` | Scheduled emails/SMS/callbacks | user_profiles |
| `triggers` | Automation rules | user_profiles |
| `trigger_executions` | Trigger audit log | triggers |
| `audit_logs` | Full audit trail for compliance | user_profiles |

---

## 3. Entity Relationship Diagram

```
                                    ┌─────────────────────┐
                                    │   auth.users        │
                                    │   (Supabase)        │
                                    └─────────┬───────────┘
                                              │ 1:1
                                              ▼
                                   ┌─────────────────────┐
              ┌────────────────────┤   UserProfile       ├────────────────────┐
              │                    └─────────┬───────────┘                    │
              │                              │                                │
    ┌─────────▼─────────┐         ┌─────────▼─────────┐         ┌────────────▼────────┐
    │     Contacts      │         │      Agents       │         │      Triggers       │
    └─────────┬─────────┘         └─────────┬─────────┘         └─────────┬───────────┘
              │                             │                             │
              │                             ▼                             ▼
              │                   ┌─────────────────────┐       ┌─────────────────────┐
              │                   │   AgentSessions     │       │ TriggerExecutions   │
              │                   └─────────┬───────────┘       └─────────────────────┘
              │                             │
              │                             ▼
              │                   ┌─────────────────────┐
              │                   │   AgentActions      │
              │                   └─────────────────────┘
              │
              ▼
    ┌─────────────────────┐
    │       Calls         │───────────────────────────────────────┐
    └─────────┬───────────┘                                       │
              │                                                   │
    ┌─────────┼─────────────────────┐                            │
    │         │                     │                            │
    ▼         ▼                     ▼                            ▼
┌─────────┐ ┌─────────────┐ ┌─────────────────┐         ┌─────────────────┐
│Transcript│ │ CallOutcome │ │   FollowUps     │         │    Meetings     │
└────┬────┘ └─────────────┘ └─────────────────┘         └─────────────────┘
     │
     ▼
┌─────────────────────┐
│ TranscriptEmbeddings│
│    (pgvector)       │
└─────────────────────┘
```

---

## 4. Enum Types

```python
# backend/app/models/enums.py
from enum import Enum

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

class MeetingStatus(str, Enum):
    SCHEDULED = "scheduled"
    CONFIRMED = "confirmed"
    IN_PROGRESS = "in_progress"
    COMPLETED = "completed"
    CANCELLED = "cancelled"
    NO_SHOW = "no_show"

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
    LOOKUP_CONTACT = "lookup_contact"
    CREATE_CONTACT = "create_contact"
    UPDATE_CONTACT = "update_contact"
    TRANSFER_CALL = "transfer_call"
    EMERGENCY_ESCALATION = "emergency_escalation"

class FollowUpType(str, Enum):
    EMAIL = "email"
    SMS = "sms"
    CALLBACK = "callback"
    TASK = "task"

class FollowUpStatus(str, Enum):
    SCHEDULED = "scheduled"
    IN_PROGRESS = "in_progress"
    SENT = "sent"
    DELIVERED = "delivered"
    FAILED = "failed"
    CANCELLED = "cancelled"

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

    # Google OAuth for calendar
    google_refresh_token_encrypted: str | None = Field(default=None)
    google_calendar_id: str | None = Field(default=None, max_length=255)

    settings: dict | None = Field(default=None, sa_column=Column(JSONB))
    is_active: bool = Field(default=True)
    deleted_at: datetime | None = Field(default=None)
```

### 5.2 Contact
```python
class Contact(BaseUUIDModel, table=True):
    __tablename__ = "contacts"

    user_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                                           nullable=False, index=True))
    full_name: str = Field(max_length=255, index=True)
    email: str | None = Field(default=None, max_length=255, index=True)
    phone: str | None = Field(default=None, max_length=50, index=True)
    phone_normalized: str | None = Field(default=None, max_length=20, index=True)  # E.164
    company: str | None = Field(default=None, max_length=255)
    job_title: str | None = Field(default=None, max_length=255)
    timezone: str | None = Field(default=None, max_length=50)
    preferred_contact_method: str | None = Field(default=None, max_length=20)

    # External CRM integration
    external_id: str | None = Field(default=None, max_length=255)
    external_source: str | None = Field(default=None, max_length=100)

    notes: str | None = Field(default=None)
    metadata: dict | None = Field(default=None, sa_column=Column(JSONB))
    is_active: bool = Field(default=True)
    deleted_at: datetime | None = Field(default=None)
```

### 5.3 Tag + Junction Tables
```python
class Tag(BaseUUIDModel, table=True):
    __tablename__ = "tags"

    user_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                                           nullable=False, index=True))
    name: str = Field(max_length=100, index=True)
    color: str | None = Field(default=None, max_length=7)  # Hex
    description: str | None = Field(default=None, max_length=255)

    __table_args__ = (UniqueConstraint("user_id", "name", name="uq_tag_user_name"),)

class ContactTag(SQLModel, table=True):
    __tablename__ = "contact_tags"
    contact_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("contacts.id", ondelete="CASCADE"), primary_key=True))
    tag_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("tags.id", ondelete="CASCADE"), primary_key=True))
    created_at: datetime = Field(default_factory=datetime.utcnow)

class CallTag(SQLModel, table=True):
    __tablename__ = "call_tags"
    call_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="CASCADE"), primary_key=True))
    tag_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("tags.id", ondelete="CASCADE"), primary_key=True))
    created_at: datetime = Field(default_factory=datetime.utcnow)
```

### 5.4 Agent
```python
class Agent(BaseUUIDModel, table=True):
    __tablename__ = "agents"

    user_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                                           nullable=False, index=True))
    name: str = Field(max_length=255, index=True)
    description: str | None = Field(default=None)

    # Model configuration
    model_name: str = Field(max_length=100)  # gpt-4, claude-3, etc.
    model_provider: str = Field(default="openai", max_length=50)

    # Behavior
    system_prompt: str = Field(sa_column=Column(Text))
    voice_id: str | None = Field(default=None, max_length=100)
    language: str = Field(default="en-US", max_length=10)

    # LiveKit config
    livekit_config: dict | None = Field(default=None, sa_column=Column(JSONB))

    # Capabilities
    allowed_tools: list[str] = Field(default=[], sa_column=Column(JSONB))

    # Limits
    max_call_duration_seconds: int = Field(default=1800)  # 30 min
    max_concurrent_calls: int = Field(default=10)

    settings: dict | None = Field(default=None, sa_column=Column(JSONB))
    is_active: bool = Field(default=True)
    deleted_at: datetime | None = Field(default=None)
```

### 5.5 Call
```python
class Call(BaseUUIDModel, table=True):
    __tablename__ = "calls"

    user_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                                           nullable=False, index=True))
    agent_id: UUID | None = Field(default=None, sa_column=Column(PG_UUID, ForeignKey("agents.id", ondelete="SET NULL"), index=True))
    contact_id: UUID | None = Field(default=None, sa_column=Column(PG_UUID, ForeignKey("contacts.id", ondelete="SET NULL"), index=True))

    direction: CallDirection = Field(sa_column=Column(SQLEnum(CallDirection)))
    status: CallStatus = Field(default=CallStatus.INITIATED, sa_column=Column(SQLEnum(CallStatus), index=True))
    telephony_provider: TelephonyProvider = Field(sa_column=Column(SQLEnum(TelephonyProvider)))

    provider_call_id: str | None = Field(default=None, max_length=255, index=True)  # Twilio SID
    from_number: str = Field(max_length=50, index=True)
    to_number: str = Field(max_length=50, index=True)

    # Timing
    initiated_at: datetime = Field(default_factory=datetime.utcnow, index=True)
    answered_at: datetime | None = Field(default=None)
    ended_at: datetime | None = Field(default=None)
    duration_seconds: int | None = Field(default=None)

    # Recording
    recording_url: str | None = Field(default=None)
    recording_duration_seconds: int | None = Field(default=None)
    recording_storage_path: str | None = Field(default=None)

    # LiveKit
    livekit_room_name: str | None = Field(default=None, max_length=255, index=True)
    livekit_room_id: str | None = Field(default=None, max_length=255)

    metadata: dict | None = Field(default=None, sa_column=Column(JSONB))
    cost_cents: int | None = Field(default=None)

    __table_args__ = (
        Index("ix_calls_user_initiated", "user_id", "initiated_at"),
        Index("ix_calls_contact_initiated", "contact_id", "initiated_at"),
    )
```

### 5.6 CallTranscript
```python
class CallTranscript(BaseUUIDModel, table=True):
    __tablename__ = "call_transcripts"

    call_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="CASCADE"),
                                           nullable=False, unique=True, index=True))

    # Structured transcript
    segments: list[dict] = Field(sa_column=Column(JSONB))
    # Format: [{"role": "agent"|"caller", "content": "...", "timestamp_ms": 1234, "confidence": 0.95}]

    full_text: str = Field(sa_column=Column(Text))  # For FTS
    language: str | None = Field(default=None, max_length=10)

    # RAG status
    embedding_model: str | None = Field(default=None, max_length=100)
    embedding_status: str = Field(default="pending", max_length=20)
    last_embedded_at: datetime | None = Field(default=None)

    # Transcription metadata
    transcription_provider: str | None = Field(default=None, max_length=50)
    transcription_confidence: float | None = Field(default=None)
    word_count: int | None = Field(default=None)
    speaker_count: int | None = Field(default=None)
```

### 5.7 CallTranscriptEmbedding (pgvector)
```python
class CallTranscriptEmbedding(BaseUUIDModel, table=True):
    __tablename__ = "call_transcript_embeddings"

    transcript_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("call_transcripts.id", ondelete="CASCADE"),
                                                 nullable=False, index=True))
    chunk_index: int = Field(nullable=False)
    chunk_text: str = Field(sa_column=Column(Text))
    chunk_start_ms: int | None = Field(default=None)
    chunk_end_ms: int | None = Field(default=None)

    # Vector embedding (1536 for OpenAI ada-002)
    embedding: list[float] = Field(sa_column=Column(Vector(1536)))

    metadata: dict | None = Field(default=None, sa_column=Column(JSONB))

    __table_args__ = (
        Index("ix_transcript_embeddings_vector", "embedding",
              postgresql_using="ivfflat",
              postgresql_with={"lists": 100},
              postgresql_ops={"embedding": "vector_cosine_ops"}),
    )
```

### 5.8 CallOutcome
```python
class CallOutcome(BaseUUIDModel, table=True):
    __tablename__ = "call_outcomes"

    call_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="CASCADE"),
                                           nullable=False, unique=True, index=True))

    # AI summary
    summary: str | None = Field(default=None, sa_column=Column(Text))
    summary_model: str | None = Field(default=None, max_length=100)

    # Sentiment
    sentiment: SentimentClassification | None = Field(default=None, sa_column=Column(SQLEnum(SentimentClassification)))
    sentiment_score: float | None = Field(default=None)  # -1.0 to 1.0
    sentiment_confidence: float | None = Field(default=None)

    # Classification
    classification: CallClassification | None = Field(default=None, sa_column=Column(SQLEnum(CallClassification), index=True))
    classification_confidence: float | None = Field(default=None)

    # Extracted data
    topics: list[str] | None = Field(default=None, sa_column=Column(JSONB))
    entities: dict | None = Field(default=None, sa_column=Column(JSONB))
    action_items: list[dict] | None = Field(default=None, sa_column=Column(JSONB))

    # Quality metrics
    talk_ratio: float | None = Field(default=None)
    interruption_count: int | None = Field(default=None)
    silence_percentage: float | None = Field(default=None)

    # Resolution
    was_resolved: bool | None = Field(default=None)
    resolution_notes: str | None = Field(default=None)

    custom_fields: dict | None = Field(default=None, sa_column=Column(JSONB))
    analyzed_at: datetime | None = Field(default=None)
    analysis_version: str | None = Field(default=None, max_length=20)
```

### 5.9 AgentSession
```python
class AgentSession(BaseUUIDModel, table=True):
    __tablename__ = "agent_sessions"

    agent_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("agents.id", ondelete="CASCADE"),
                                            nullable=False, index=True))
    call_id: UUID | None = Field(default=None, sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="SET NULL"), index=True))
    contact_id: UUID | None = Field(default=None, sa_column=Column(PG_UUID, ForeignKey("contacts.id", ondelete="SET NULL"), index=True))

    livekit_room_name: str = Field(max_length=255, unique=True, index=True)
    livekit_room_id: str | None = Field(default=None, max_length=255)
    livekit_participant_id: str | None = Field(default=None, max_length=255)

    status: AgentSessionStatus = Field(default=AgentSessionStatus.ACTIVE, sa_column=Column(SQLEnum(AgentSessionStatus), index=True))

    # Conversation state
    history: list[dict] = Field(default=[], sa_column=Column(JSONB))
    context: dict | None = Field(default=None, sa_column=Column(JSONB))

    started_at: datetime = Field(default_factory=datetime.utcnow)
    ended_at: datetime | None = Field(default=None)

    message_count: int = Field(default=0)
    tool_call_count: int = Field(default=0)
```

### 5.10 AgentAction
```python
class AgentAction(BaseUUIDModel, table=True):
    __tablename__ = "agent_actions"

    session_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("agent_sessions.id", ondelete="CASCADE"),
                                              nullable=False, index=True))
    agent_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("agents.id", ondelete="CASCADE"),
                                            nullable=False, index=True))

    action_type: ActionType = Field(sa_column=Column(SQLEnum(ActionType), index=True))
    status: ActionStatus = Field(default=ActionStatus.PENDING, sa_column=Column(SQLEnum(ActionStatus), index=True))

    payload: dict = Field(sa_column=Column(JSONB))
    result: dict | None = Field(default=None, sa_column=Column(JSONB))
    error_message: str | None = Field(default=None)

    initiated_at: datetime = Field(default_factory=datetime.utcnow)
    completed_at: datetime | None = Field(default=None)
    duration_ms: int | None = Field(default=None)

    triggered_by: str | None = Field(default=None, max_length=50)  # user_request, auto, system
```

### 5.11 Meeting
```python
class Meeting(BaseUUIDModel, table=True):
    __tablename__ = "meetings"

    user_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                                           nullable=False, index=True))
    contact_id: UUID | None = Field(default=None, sa_column=Column(PG_UUID, ForeignKey("contacts.id", ondelete="SET NULL"), index=True))
    call_id: UUID | None = Field(default=None, sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="SET NULL"), index=True))

    # Google Calendar
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
    cancelled_at: datetime | None = Field(default=None)
    cancellation_reason: str | None = Field(default=None, max_length=255)

    __table_args__ = (Index("ix_meetings_user_time", "user_id", "start_time"),)
```

### 5.12 FollowUp
```python
class FollowUp(BaseUUIDModel, table=True):
    __tablename__ = "follow_ups"

    user_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                                           nullable=False, index=True))
    call_id: UUID | None = Field(default=None, sa_column=Column(PG_UUID, ForeignKey("calls.id", ondelete="SET NULL"), index=True))
    contact_id: UUID | None = Field(default=None, sa_column=Column(PG_UUID, ForeignKey("contacts.id", ondelete="SET NULL"), index=True))

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

    __table_args__ = (Index("ix_follow_ups_scheduled", "status", "scheduled_at"),)
```

### 5.13 Trigger
```python
class Trigger(BaseUUIDModel, table=True):
    __tablename__ = "triggers"

    user_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="CASCADE"),
                                           nullable=False, index=True))
    agent_id: UUID | None = Field(default=None, sa_column=Column(PG_UUID, ForeignKey("agents.id", ondelete="SET NULL"), index=True))

    name: str = Field(max_length=255)
    description: str | None = Field(default=None)

    trigger_type: TriggerType = Field(sa_column=Column(SQLEnum(TriggerType), index=True))

    conditions: dict = Field(sa_column=Column(JSONB))
    # Examples:
    # Scheduled: {"cron": "0 9 * * MON", "timezone": "America/New_York"}
    # Event: {"event": "call.completed", "filters": {"sentiment": "negative"}}
    # Real-time: {"keyword_detected": ["cancel", "refund"]}

    actions: list[dict] = Field(sa_column=Column(JSONB))
    # Example: [{"type": "send_email", "template": "follow_up_negative"}]

    is_active: bool = Field(default=True)
    last_triggered_at: datetime | None = Field(default=None)
    trigger_count: int = Field(default=0)
    next_run_at: datetime | None = Field(default=None, index=True)


class TriggerExecution(BaseUUIDModel, table=True):
    __tablename__ = "trigger_executions"

    trigger_id: UUID = Field(sa_column=Column(PG_UUID, ForeignKey("triggers.id", ondelete="CASCADE"),
                                              nullable=False, index=True))

    triggered_by_event: str | None = Field(default=None, max_length=100)
    triggered_by_entity_type: str | None = Field(default=None, max_length=50)
    triggered_by_entity_id: UUID | None = Field(default=None)

    executed_at: datetime = Field(default_factory=datetime.utcnow, index=True)
    success: bool = Field(default=True)

    actions_executed: list[dict] = Field(sa_column=Column(JSONB))
    errors: list[str] | None = Field(default=None, sa_column=Column(JSONB))
    duration_ms: int | None = Field(default=None)
```

### 5.14 AuditLog
```python
class AuditLog(BaseUUIDModel, table=True):
    __tablename__ = "audit_logs"

    user_id: UUID | None = Field(default=None, sa_column=Column(PG_UUID, ForeignKey("user_profiles.id", ondelete="SET NULL")))

    table_name: str = Field(max_length=100, index=True)
    record_id: UUID = Field(index=True)
    action: str = Field(max_length=20)  # CREATE, UPDATE, DELETE

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

Due to foreign key dependencies:

```
1. Enable extensions
   - uuid-ossp
   - pgvector

2. Create ENUM types
   - All enums defined in Section 4

3. user_profiles (depends on auth.users)

4. audit_logs (needed for triggers on subsequent tables)

5. First-tier user-owned tables (parallel)
   - contacts
   - agents
   - tags
   - triggers

6. Second-tier tables (depend on first tier)
   - calls
   - meetings
   - agent_sessions

7. Third-tier tables
   - call_transcripts
   - call_outcomes
   - agent_actions
   - follow_ups
   - trigger_executions

8. Junction and child tables
   - contact_tags
   - call_tags
   - call_transcript_embeddings

9. Database triggers for audit logging

10. RLS policies

11. Indexes (additional beyond inline definitions)
```

---

## 7. RLS Policies (Multi-tenancy)

```sql
-- Enable RLS on all tenant-scoped tables
ALTER TABLE user_profiles ENABLE ROW LEVEL SECURITY;
ALTER TABLE contacts ENABLE ROW LEVEL SECURITY;
ALTER TABLE agents ENABLE ROW LEVEL SECURITY;
ALTER TABLE calls ENABLE ROW LEVEL SECURITY;
-- ... all other tables

-- Helper function to get current user's profile ID
CREATE OR REPLACE FUNCTION get_my_profile_id()
RETURNS UUID AS $$
  SELECT id FROM user_profiles WHERE auth_user_id = auth.uid()
$$ LANGUAGE SQL SECURITY DEFINER;

-- User profiles: access own profile only
CREATE POLICY user_profiles_policy ON user_profiles
  FOR ALL USING (auth.uid() = auth_user_id);

-- Direct user-owned tables (contacts, agents, calls, etc.)
CREATE POLICY contacts_policy ON contacts
  FOR ALL USING (user_id = get_my_profile_id());

CREATE POLICY agents_policy ON agents
  FOR ALL USING (user_id = get_my_profile_id());

CREATE POLICY calls_policy ON calls
  FOR ALL USING (user_id = get_my_profile_id());

CREATE POLICY meetings_policy ON meetings
  FOR ALL USING (user_id = get_my_profile_id());

CREATE POLICY tags_policy ON tags
  FOR ALL USING (user_id = get_my_profile_id());

CREATE POLICY follow_ups_policy ON follow_ups
  FOR ALL USING (user_id = get_my_profile_id());

CREATE POLICY triggers_policy ON triggers
  FOR ALL USING (user_id = get_my_profile_id());

-- Child tables (access via parent join)
CREATE POLICY call_transcripts_policy ON call_transcripts
  FOR ALL USING (
    EXISTS (SELECT 1 FROM calls WHERE calls.id = call_id
            AND calls.user_id = get_my_profile_id())
  );

CREATE POLICY call_outcomes_policy ON call_outcomes
  FOR ALL USING (
    EXISTS (SELECT 1 FROM calls WHERE calls.id = call_id
            AND calls.user_id = get_my_profile_id())
  );

CREATE POLICY agent_sessions_policy ON agent_sessions
  FOR ALL USING (
    EXISTS (SELECT 1 FROM agents WHERE agents.id = agent_id
            AND agents.user_id = get_my_profile_id())
  );

CREATE POLICY agent_actions_policy ON agent_actions
  FOR ALL USING (
    EXISTS (SELECT 1 FROM agents WHERE agents.id = agent_id
            AND agents.user_id = get_my_profile_id())
  );

CREATE POLICY contact_tags_policy ON contact_tags
  FOR ALL USING (
    EXISTS (SELECT 1 FROM contacts WHERE contacts.id = contact_id
            AND contacts.user_id = get_my_profile_id())
  );

CREATE POLICY call_tags_policy ON call_tags
  FOR ALL USING (
    EXISTS (SELECT 1 FROM calls WHERE calls.id = call_id
            AND calls.user_id = get_my_profile_id())
  );

CREATE POLICY trigger_executions_policy ON trigger_executions
  FOR ALL USING (
    EXISTS (SELECT 1 FROM triggers WHERE triggers.id = trigger_id
            AND triggers.user_id = get_my_profile_id())
  );

CREATE POLICY call_transcript_embeddings_policy ON call_transcript_embeddings
  FOR ALL USING (
    EXISTS (
      SELECT 1 FROM call_transcripts ct
      JOIN calls c ON c.id = ct.call_id
      WHERE ct.id = transcript_id AND c.user_id = get_my_profile_id()
    )
  );

-- Audit logs: users can view their own audit entries
CREATE POLICY audit_logs_select ON audit_logs
  FOR SELECT USING (user_id = get_my_profile_id() OR user_id IS NULL);

-- Service role bypass (for backend operations)
CREATE POLICY service_role_all ON contacts
  FOR ALL TO service_role USING (true);
-- Repeat for all tables that need backend access
```

---

## 8. Audit Logging

### Audit Trigger Function
```sql
CREATE OR REPLACE FUNCTION audit_trigger_func()
RETURNS TRIGGER AS $$
DECLARE
  current_user_id UUID;
BEGIN
  -- Get current user from session variable (set by backend)
  BEGIN
    current_user_id := current_setting('app.current_user_id', true)::uuid;
  EXCEPTION WHEN OTHERS THEN
    current_user_id := NULL;
  END;

  IF TG_OP = 'INSERT' THEN
    INSERT INTO audit_logs (id, table_name, record_id, action, new_values, user_id, created_at)
    VALUES (gen_random_uuid(), TG_TABLE_NAME, NEW.id, 'CREATE', to_jsonb(NEW), current_user_id, now());
    RETURN NEW;
  ELSIF TG_OP = 'UPDATE' THEN
    INSERT INTO audit_logs (id, table_name, record_id, action, old_values, new_values, user_id, created_at)
    VALUES (gen_random_uuid(), TG_TABLE_NAME, NEW.id, 'UPDATE', to_jsonb(OLD), to_jsonb(NEW), current_user_id, now());
    RETURN NEW;
  ELSIF TG_OP = 'DELETE' THEN
    INSERT INTO audit_logs (id, table_name, record_id, action, old_values, user_id, created_at)
    VALUES (gen_random_uuid(), TG_TABLE_NAME, OLD.id, 'DELETE', to_jsonb(OLD), current_user_id, now());
    RETURN OLD;
  END IF;
END;
$$ LANGUAGE plpgsql;

-- Apply triggers to tables that need auditing
CREATE TRIGGER contacts_audit_trigger
  AFTER INSERT OR UPDATE OR DELETE ON contacts
  FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER agents_audit_trigger
  AFTER INSERT OR UPDATE OR DELETE ON agents
  FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER calls_audit_trigger
  AFTER INSERT OR UPDATE OR DELETE ON calls
  FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER meetings_audit_trigger
  AFTER INSERT OR UPDATE OR DELETE ON meetings
  FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER follow_ups_audit_trigger
  AFTER INSERT OR UPDATE OR DELETE ON follow_ups
  FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();
```

### Setting User Context from Backend
```python
# In your FastAPI middleware or dependency
async def set_user_context(db: AsyncSession, user_id: UUID):
    await db.execute(text(f"SET LOCAL app.current_user_id = '{user_id}'"))
```

---

## 9. Full-Text Search Setup

```sql
-- Add tsvector column for transcript search
ALTER TABLE call_transcripts ADD COLUMN full_text_vector tsvector;

-- Create GIN index
CREATE INDEX ix_call_transcripts_fts ON call_transcripts USING gin(full_text_vector);

-- Auto-update trigger
CREATE OR REPLACE FUNCTION update_transcript_fts()
RETURNS TRIGGER AS $$
BEGIN
  NEW.full_text_vector := to_tsvector('english', COALESCE(NEW.full_text, ''));
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER transcript_fts_trigger
  BEFORE INSERT OR UPDATE ON call_transcripts
  FOR EACH ROW EXECUTE FUNCTION update_transcript_fts();

-- Search query example
SELECT * FROM call_transcripts
WHERE full_text_vector @@ to_tsquery('english', 'cancel & appointment');
```

---

## 10. Files to Create

### Models
```
backend/app/models/
├── __init__.py          # Export all models
├── enums.py             # All enum definitions
├── user_profile.py      # UserProfile
├── contact.py           # Contact, Tag, ContactTag
├── agent.py             # Agent, AgentSession, AgentAction
├── call.py              # Call, CallTranscript, CallOutcome, CallTag
├── embedding.py         # CallTranscriptEmbedding
├── meeting.py           # Meeting
├── follow_up.py         # FollowUp
├── trigger.py           # Trigger, TriggerExecution
└── audit_log.py         # AuditLog
```

### Schemas (Pydantic)
```
backend/app/schemas/
├── __init__.py
├── user_profile.py      # UserProfileCreate, UserProfileRead, UserProfileUpdate
├── contact.py           # ContactCreate, ContactRead, ContactUpdate
├── agent.py             # AgentCreate, AgentRead, AgentUpdate
├── call.py              # CallCreate, CallRead, CallUpdate
├── meeting.py           # MeetingCreate, MeetingRead, MeetingUpdate
├── follow_up.py         # FollowUpCreate, FollowUpRead
└── trigger.py           # TriggerCreate, TriggerRead
```

### CRUD
```
backend/app/crud/
├── __init__.py
├── user_profile.py
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
├── contacts.py
├── agents.py
├── calls.py
├── meetings.py
├── follow_ups.py
└── triggers.py
```

### Migrations
```
backend/alembic/versions/
├── 001_enable_extensions.py
├── 002_create_enums.py
├── 003_create_user_profiles.py
├── 004_create_audit_logs.py
├── 005_create_contacts_tags.py
├── 006_create_agents.py
├── 007_create_calls.py
├── 008_create_meetings.py
├── 009_create_transcripts_outcomes.py
├── 010_create_sessions_actions.py
├── 011_create_follow_ups.py
├── 012_create_triggers.py
├── 013_create_embeddings.py
├── 014_create_junction_tables.py
├── 015_create_audit_triggers.py
├── 016_create_rls_policies.py
└── 017_create_fts_indexes.py
```

### Dependencies to Add
```toml
# pyproject.toml
[project]
dependencies = [
    # ... existing
    "pgvector>=0.2.0",
]
```

---

## 11. Verification Checklist

1. **Run migrations**: `cd backend && alembic upgrade head`
2. **Verify tables**: Check Supabase dashboard for all 17 tables
3. **Test RLS**:
   - Create two users
   - Insert contacts for each
   - Verify user A cannot see user B's contacts
4. **Test audit logging**:
   - Create/update/delete a contact
   - Verify audit_logs has entries
5. **Test pgvector**:
   - Insert sample embedding
   - Run similarity query
6. **Test FTS**:
   - Insert transcript with text
   - Run full-text search query
7. **API smoke test**:
   - POST /api/v1/contacts
   - GET /api/v1/contacts
   - PATCH /api/v1/contacts/{id}
   - DELETE /api/v1/contacts/{id}

---

## 12. Design Decisions Summary

| Decision | Choice | Rationale |
|----------|--------|-----------|
| Vector DB | pgvector | Built into Supabase, simpler ops |
| Embedding dim | 1536 | OpenAI ada-002 compatible |
| Audit log | Yes | Compliance requirement |
| Telephony | All 3 | Twilio, FreePBX, LiveKit |
| Soft delete | Yes | Contacts, agents via deleted_at |
| Multi-tenancy | RLS | Supabase-native, secure |
| Auth | Supabase Auth | Already integrated |
| Primary keys | UUID | Distributed-friendly |
| Timestamps | UTC | Consistent across timezones |
