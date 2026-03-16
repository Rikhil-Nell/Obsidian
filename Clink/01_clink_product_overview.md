# Clink — Product Overview

## 1. The Market Problem

India's restaurant industry operates under a structural imbalance created by delivery aggregators — primarily **Swiggy** and **Zomato**.

These platforms dominate the customer acquisition funnel for restaurants, but the relationship is extractive rather than symbiotic.

### What delivery platforms create

| Problem | Impact |
|---|---|
| High commission fees (25–35%) | Restaurant margins erode to near-zero on delivery orders |
| Discount-driven discovery | Restaurants compete on price, not quality |
| Platform-owned customer data | Restaurants cannot build direct relationships with diners |
| Delivery-first behaviour | Dine-in footfall declines, reducing the highest-margin revenue channel |

The result is a market where restaurants **pay to acquire customers they never truly own**.

A restaurant on Swiggy does not know who ordered from them last Tuesday. It cannot message that customer. It cannot offer a personal incentive. The platform sits between the restaurant and the diner, extracting rent from both sides.

### Why this matters at scale

India has an estimated **7.5 million restaurants**. The vast majority are small and medium-sized businesses with thin margins. For these operators, delivery platform commissions are not a line item — they are an existential pressure.

Restaurants need a way to:

- **Acquire dine-in customers** at lower cost
- **Understand customer preferences** directly
- **Build loyalty** without paying a middleman

---

## 2. What Clink Originally Built

Clink began as a **B2B platform for restaurants**.

The initial product suite included:

- **Targeted coupon campaigns** — restaurants could issue coupons to specific customer segments
- **In-store acquisition tracking** — measurement of how many new customers a campaign brought in
- **Loyalty programs** — automated returning-customer incentives
- **Cold lead revival** — re-engagement flows for lapsed visitors
- **Customer analytics** — visit patterns, spend patterns, and frequency analysis
- **AI-driven promotion suggestions** — the system recommended optimal discount levels and timing

The product was designed to give restaurants the customer intelligence tools that delivery platforms keep for themselves.

### The core thesis

> Bring customers back to the table. Let the restaurant own the relationship.

---

## 3. The Investor Feedback

When Clink pitched investors, the feedback was consistent:

> "The product is too B2B. Growth will be slow if it depends entirely on restaurant sales cycles. There is no consumer network effect."

The specific concerns:

- **Addressable market appeared limited** — selling SaaS to Indian restaurants is a slow, fragmented sales motion
- **No consumer pull** — restaurants have to be convinced one by one; there is no viral loop
- **No defensible data asset** — the data collected was useful but not unique enough to create a moat

The feedback pointed toward a single insight:

> **Clink needs a consumer-facing layer.**

---

## 4. The Consumer Pivot — A Taste Discovery Network

Inspired by platforms like **Beli** (US-based food discovery app), the team explored a concept that fundamentally changes the value proposition.

### The core idea

Build a **taste profile** for every user. Instead of recommending restaurants based on star ratings or proximity, recommend **specific dishes** based on **taste similarity between users**.

### Why dish-level data matters

Most restaurant platforms operate at the **restaurant level**:

> "Try Restaurant X — rated 4.3 stars."

Clink intends to operate at the **dish level**:

> "People with your taste loved the Nalli Gosht Biryani at Pakwaan Grand."

This is a fundamentally richer signal. Two users might both rate a restaurant 4 stars, but for entirely different reasons — one loved the biryani, the other loved the kebabs. Dish-level data exposes the **actual preference structure**.

### Example: taste matching in practice

Consider two users in Hyderabad:

| Signal | User A | User B |
|---|---|---|
| Biryani Times biryani | Disliked | Disliked |
| Minerva breakfast | Neutral | Neutral |
| Pista House desserts | Mediocre | Mediocre |

Their taste profiles align with high confidence. If User A loved a specific dish at another restaurant, that becomes a **high-confidence recommendation** for User B.

This is not generic collaborative filtering — it is **dish-level taste twinning**.

### The "Food Twin" concept

The system surfaces a social signal:

> "You are 82% taste compatible with Arjun."
> "Arjun loved this dish → try it."

This makes the recommendation engine feel **human and social** rather than algorithmic. Users are not being told "the algorithm thinks you'll like this." They are being told "someone who eats like you loved this."

---

## 5. How the Consumer Layer Strengthens the Business

The consumer-facing taste network creates three strategic advantages that the B2B product alone could not provide.

### 5.1 Network effects

Every new user who rates dishes increases the accuracy of recommendations for all other users. The system improves with usage — a property that pure-B2B tools do not have.

### 5.2 Data moat

Over time, the platform accumulates a dataset of the form:

```
(user, dish, restaurant, location, reaction)
```

This dataset is a **taste graph** linking diners to dishes to restaurants to geographies. No other platform in India currently builds this at the dish level.

> The dataset — not the algorithm — becomes the primary defensible advantage.

### 5.3 Two-sided flywheel

```
More users → Better recommendations → More engagement
      ↑                                         ↓
More restaurants ← Higher dine-in traffic ← More coupon redemptions
```

The consumer layer creates **pull** for the restaurant side. Restaurants join because users are already discovering their dishes. Users join because recommendations are accurate and social.

---

## 6. Positioning

### What Clink is not

- Not a delivery platform
- Not a restaurant review site
- Not a generic coupon app

### What Clink is

> A taste-based food discovery network that connects diners to dishes and restaurants to customers — bypassing delivery platforms entirely.

### The investor pitch

> "We build dish-level taste embeddings of diners and connect them with restaurants using a collaborative taste graph. Over time, this creates the largest dataset of Indian dining preferences — a dataset that becomes exponentially more valuable with every user."

---

## 7. Target Market

### Phase 1 — Hyderabad

- Dense restaurant ecosystem
- Strong food culture
- High delivery platform penetration (large problem to solve)
- Well-defined cuisine clusters (biryani, Andhra, South Indian, street food)

### Phase 2 — Expansion

- Bangalore, Mumbai, Delhi
- Cities with high food spend and delivery dependency

### User demographics

- Age 20–35
- Urban, food-curious, socially active
- Already using Swiggy/Zomato but open to discovery
- Motivated by both deals (coupons) and social proof (taste matches)

---

## 8. Summary

| Dimension | Detail |
|---|---|
| **Problem** | Restaurants lose money and customer relationships to delivery platforms |
| **Original solution** | B2B tools for restaurant marketing and customer analytics |
| **Pivot** | Add a consumer-facing taste discovery network |
| **Core mechanic** | Dish-level taste profiling + collaborative filtering + social proof |
| **Moat** | Accumulating taste graph: `(user, dish, restaurant, location, reaction)` |
| **Key insight** | The dataset is the asset. The algorithm is replaceable, the data is not. |
