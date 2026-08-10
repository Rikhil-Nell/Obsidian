---
skill: Payments (Stripe)
source: Self-identified gap + operational tooling list + pairs with Backend Primitives #02
priority: High
status: Not Started
project: Stripe webhooks on ledger backend (after #02 job queue)
---

# Payments (Stripe)

## Why It Matters

Appeared In:

- Self-assessment (2026-06-22): payments not done well yet
- [[Learn/Backend Primitives]] — idempotency from #02 job queue applies directly to Stripe webhooks
- [[Companies/DualEntry]] — finance/ERP domain; ledger primitive mirrors their world

Most student projects skip payments. Every serious startup needs them within months. Stripe webhooks alone teach real-world backend: idempotency, retries, signature verification, out-of-order delivery.

**Build after:** primitive #02 (job queue + DLQ) is shipped. Payment webhook handler becomes another idempotent consumer.

---

## Specific Concepts

- [ ] Checkout / PaymentIntent flow
- [ ] Webhook signature verification
- [ ] Idempotent webhook handling (same event_id twice = one effect)
- [ ] Out-of-order events (paid before created)
- [ ] Subscription lifecycle (create, renew, cancel, failed payment)
- [ ] Refunds and partial refunds
- [ ] Test mode vs live mode
- [ ] Razorpay (optional, India-facing products)

---

## Suggested Build (on ledger backend)

Wire Stripe to the ledger core:

```
Stripe webhook → verify signature → enqueue job (#02)
  → idempotent handler → ledger entry (#03) for payment captured
```

Proves payments + events + idempotency in one system.

---

## Progress

- [ ] Not Started
- [ ] Stripe test mode + one successful payment
- [ ] Webhook handler idempotent
- [ ] Subscription or refund path handled
- [ ] Interview Ready

---

## Related

- Learn: [[Learn/Backend Primitives]], [[Learn/Event Systems]], [[Learn/Distributed Systems]]
- Companies: [[Companies/DualEntry]]
