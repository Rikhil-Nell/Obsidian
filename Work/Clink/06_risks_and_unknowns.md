# Clink — Risks and Unknowns

## Overview

Every recommendation system faces a common set of failure modes. Clink's dish-level taste graph introduces additional risks unique to the food domain. This document catalogs known risks, assesses their severity, and proposes mitigations.

---

## 1. Data Sparsity

### The problem

The rating matrix $R$ is extremely sparse. With 10,000 users and 50,000 dishes:

- Matrix size: 500 million cells
- Typical user rates 20–50 dishes
- **Density: < 0.1%**

Matrix factorization struggles when the matrix is too sparse — there isn't enough signal to learn meaningful latent factors.

### Severity: **High**

This is the most fundamental technical risk. If users don't rate enough dishes, the collaborative filtering model will not converge to useful recommendations.

### Mitigation

| Strategy | Detail |
|---|---|
| Aggressive onboarding | 10–15 dishes rated during onboarding provides a baseline |
| Rating prompts | Push notifications after restaurant visits: "How was the Biryani?" |
| Implicit signals | Track dish views, coupon saves, and time spent on dish pages as weak positive signals |
| Hybrid scoring | Blend cold-start heuristics with collaborative filtering; don't rely on CF alone until ratings are dense enough |
| Geographic clustering | Factorize per-city matrices instead of a single global matrix to increase density |

---

## 2. Low User Engagement with Ratings

### The problem

Users are notoriously reluctant to leave reviews. On most platforms:

- < 5% of users write text reviews
- < 20% of users leave any rating at all
- Most ratings come from extreme experiences (very good or very bad)

### Severity: **High**

Without sufficient rating volume, the recommendation engine has no fuel.

### Mitigation

| Strategy | Detail |
|---|---|
| Frictionless rating UX | Single-tap reaction (love/like/neutral/dislike) — not a 5-star scale, not text-first |
| Taste score gamification | Show users their "taste profile completeness" and encourage filling it |
| Social incentive | "Rate 10 more dishes to unlock your Taste Twin" |
| Coupon gating | "Rate your last visit to unlock your next coupon" (use carefully to avoid resentment) |
| Skip text reviews initially | Don't require text — focus on reaction capture |

---

## 3. Cold Start Chicken-and-Egg

### The problem

The system needs users to have good recommendations. But users need good recommendations to stay engaged. This is a classic two-sided cold start:

- **User cold start:** new users have no ratings → poor recommendations
- **Item cold start:** new dishes have no ratings → never get recommended
- **System cold start:** new system has no data at all

### Severity: **High** (at launch)

### Mitigation

| Cold start type | Strategy |
|---|---|
| User | Onboarding taste quiz produces an immediate taste vector |
| Item | New dishes are boosted via popularity priors and restaurant-level signals |
| System | Initial recommendations are coupon-driven — value proposition is discount, not accuracy |

The key insight: **at launch, the coupon is the product, not the recommendation.** Users come for discounts. Over time, the recommendation quality becomes the retention driver.

---

## 4. Biased Recommendations (Filter Bubbles)

### The problem

Collaborative filtering naturally reinforces existing preferences. If a user likes biryani, the system recommends more biryani. The user never discovers that they might enjoy ramen.

This creates a **filter bubble** — recommendations become repetitive and stale.

### Severity: **Medium**

### Mitigation

| Strategy | Detail |
|---|---|
| Diversity injection | Reserve 20–30% of recommendation slots for "exploration" — dishes outside the user's typical profile |
| Cuisine diversity constraint | Ensure top-N recommendations span at least 3 cuisine types |
| Serendipity scoring | Boost dishes that are popular among taste twins but outside the user's rated cuisines |
| Monitor coverage metric | Track what fraction of dishes ever get recommended — if it drops below 40%, increase exploration |

---

## 5. Fraudulent and Manipulated Reviews

### The problem

Restaurants have strong financial incentives to:

- Generate fake positive reviews for their own dishes
- Generate fake negative reviews for competitors
- Incentivize customers to leave inflated ratings ("5-star review = free dessert")

### Severity: **Medium–High**

A single restaurant flooding the system with fake ratings can distort recommendations for an entire neighborhood.

### Mitigation

| Strategy | Detail |
|---|---|
| Rate limiting | Max 5 ratings per user per day; flag accounts that hit the limit regularly |
| Velocity detection | Flag sudden spikes in ratings for a single dish or restaurant |
| Device fingerprinting | Detect multiple accounts from the same device |
| Review source tracking | Distinguish onboarding ratings, organic ratings, and prompted ratings |
| Statistical outlier detection | If a dish's rating distribution is abnormally concentrated at +2, flag for review |
| Human review queue | Flagged ratings go to a moderator before affecting the model |

---

## 6. Geographic Limitations

### The problem

Clink's value depends on having sufficient restaurant and dish coverage in the user's area. In early stages:

- Coverage will be patchy
- Some neighborhoods will have 50 restaurants; others will have 2
- Users in low-coverage areas will get poor recommendations

### Severity: **Medium**

### Mitigation

| Strategy | Detail |
|---|---|
| Hyper-local launch | Launch in a single well-covered area (e.g., Banjara Hills, Jubilee Hills) before expanding |
| Transparency | If coverage is low, tell the user: "We're expanding in your area — here are popular dishes nearby" |
| Restaurant acquisition focus | Prioritize onboarding restaurants in areas with the most users |
| Adaptive radius | Increase the search radius dynamically if the local area has low density |

---

## 7. Recommendation Fatigue

### The problem

Users have been trained by years of mediocre recommendations on other platforms to **ignore algorithmic suggestions**. The reaction is often:

> "I don't need an app to tell me where to eat."

### Severity: **Medium**

### Mitigation

| Strategy | Detail |
|---|---|
| Social proof framing | Frame recommendations as "someone like you loved this" rather than "the algorithm says" |
| Taste Twin UI | The social hook (Food Twin Score) makes it feel human, not robotic |
| Coupon-first framing | "Here's a deal on something you'd love" — the deal is the hook, the recommendation is the glue |
| High accuracy threshold | Only surface dish recommendations with score > 0.7 — better to show fewer, better recommendations |

---

## 8. Scaling Costs

### The problem

As the system grows, several cost drivers emerge:

| Component | Cost driver |
|---|---|
| Model training | Compute scales with ratings volume |
| FAISS index | Memory scales with user count × K dimensions |
| PostgreSQL | Storage scales with ratings; query load scales with users |
| Infrastructure | More users → more API servers, higher bandwidth |

### Severity: **Low–Medium** (manageable with good architecture)

### Mitigation

| Strategy | Detail |
|---|---|
| Incremental training | Don't retrain from scratch — fold in new ratings incrementally |
| Index quantization | Use PQ (product quantization) in FAISS to reduce memory footprint |
| Table partitioning | Partition ratings by time; archive old partitions |
| Caching | Cache recommendations for returning users (invalidate on new rating or model update) |
| Right-size infrastructure | Don't over-provision; scale reactively with monitoring |

---

## 9. Privacy and Data Sensitivity

### The problem

The system collects:

- User location (GPS coordinates)
- Taste preferences (what they eat, where, how often)
- Social connections (taste twins)

This is sensitive personal data. Mishandling it can cause:

- Regulatory compliance issues
- User trust erosion
- Reputational damage

### Severity: **Medium–High**

### Mitigation

| Strategy | Detail |
|---|---|
| Data minimization | Only collect what the system needs; don't store raw GPS history |
| Anonymized analytics | Restaurant-facing analytics use aggregated, anonymized data |
| Taste twin privacy | Users can opt out of being visible as taste twins |
| Consent management | Clear onboarding consent for data usage |
| Encryption | Encrypt PII at rest and in transit |
| Compliance | Align with India's Digital Personal Data Protection Act (DPDPA) |

---

## 10. Technical Debt and Complexity

### The problem

ML-powered systems are inherently complex. Potential debt areas:

- Feature engineering logic scattered across services
- Model versioning and rollback not implemented
- Training pipeline reliability (what happens when training fails?)
- Stale index serving bad recommendations

### Severity: **Medium**

### Mitigation

| Strategy | Detail |
|---|---|
| Model versioning | Every training run produces a versioned artifact; serve a specific version |
| Fallback path | If FAISS is unavailable, fall back to cold-start heuristics gracefully |
| Monitoring | Alert on training failures, index staleness, and recommendation quality metrics |
| Keep it simple | Resist the temptation to add complexity before the data justifies it |

---

## Risk Summary Matrix

| Risk | Severity | Likelihood | Priority |
|---|---|---|---|
| Data sparsity | High | High | **P0** |
| Low engagement | High | High | **P0** |
| Cold start | High | Certain | **P0** |
| Fraud reviews | Medium–High | Medium | **P1** |
| Privacy compliance | Medium–High | Medium | **P1** |
| Filter bubbles | Medium | Medium | **P1** |
| Geographic limits | Medium | High | **P1** |
| Recommendation fatigue | Medium | Medium | **P2** |
| Scaling costs | Low–Medium | Low | **P2** |
| Technical debt | Medium | Medium | **P2** |
