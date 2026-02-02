## Raw JSON Body

```json
{
  "data_source": "http://localhost:8000/Customer_KPIs_KnownPhonesOnly.csv",
  "analysis_timestamp": "2025-06-27T19:46:56.782846",
  "analysis_config": {
    "profit_margin": 0.25,
    "dormancy_threshold_days": 60,
    "new_customer_threshold_days": 30
  },
  "financial_summary": {
    "total_revenue": 1942688,
    "estimated_total_profit": 485672,
    "overall_aov": 305.02,
    "overall_avg_clv": 363.87
  },
  "customer_segments": {
    "total_customers": 5339,
    "new_customers": {
      "count": 216,
      "percentage": 4.05,
      "avg_first_order_value": 303.57
    },
    "active_customers": {
      "count": 254,
      "percentage": 4.76,
      "avg_clv": 540.65,
      "avg_orders": 1.85
    },
    "dormant_customers": {
      "count": 4869,
      "percentage": 91.2,
      "avg_clv_before_dormancy": 356.87
    }
  },
  "coupon_strategy_insights": {
    "stamp_card": {
      "target_customer_count": 98,
      "order_frequency_distribution": {
        "mean": 3.2,
        "std": 3.18,
        "min": 2,
        "25%": 2.0,
        "50%": 2.0,
        "75%": 3.0,
        "max": 29
      },
      "suggestion": "Most active customers place between 2 and 3 orders. A 5 or 7 stamp card could be optimal."
    },
    "miss_you": {
      "target_customer_count": 4869,
      "dormancy_trigger_point_days": 60,
      "avg_spend_of_dormant_customers": 304.52,
      "last_order_recency_distribution": {
        "mean": 184.84,
        "std": 80.13,
        "min": 0,
        "25%": 137.0,
        "50%": 189.0,
        "75%": 251.0,
        "max": 314
      },
      "suggestion": "The average dormant customer used to spend around $304.52 per order. A win-back offer should be compelling relative to this amount."
    },
    "joining_bonus": {
      "target_customer_count": 216,
      "avg_first_order_value": 303.57,
      "suggestion": "The average new customer spends $303.57 on their first order. A joining bonus should provide value but protect margins on this initial amount."
    }
  },
  "additional_insights": {
    "high_value_customers": {
      "count": 931,
      "threshold": 480.0,
      "avg_clv": 816.47
    },
    "order_frequency_insights": {
      "single_order_customers": 4645,
      "repeat_customers": 694,
      "high_frequency_customers": 32
    }
  }
}
```
