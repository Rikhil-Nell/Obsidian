
## 📌 1. **One-Sample t-Test**

**Wording clues:**

- “Is the average X equal to Y?”
    
- “Do students score **around 75 marks on average**?”
    
- “Is the **mean life of bulbs** equal to 1000 hrs?”
    

**Test:** `t.test(x, mu = value, alternative = ..., conf.level = ...)`  
**Use when:**

- Population variance is unknown
    
- Small to moderate sample size
    
- Data is approx. normal
    

---

## 📌 2. **One-Sample z-Test**

**Wording clues:**

- “Is the **known population mean** µ = X?” and you’re given:
    
    - σ (population std deviation)
        
    - Large sample (n > 30)
        

**Test:** Use z-test manually or via `BSDA::z.test()`  
**Use when:**

- Population std dev **σ is known**
    
- Normal distribution or large n
    

---

## 📌 3. **Two-Sample t-Test (Independent)**

**Wording clues:**

- “Compare two groups: A and B”
    
- “Do **Variety A and B** yield the same?”
    
- “Men vs women income...”
    
- “Two brands, two classrooms…”
    

**Test:** `t.test(x, y, var.equal = TRUE/FALSE, ...)`  
**Use when:**

- Two **independent** samples
    
- Assume `var.equal = TRUE` if equal variances given, else FALSE
    

---

## 📌 4. **Paired t-Test**

**Wording clues:**

- “Before and after”
    
- “Same students tested twice”
    
- “Pre-test and post-test scores”
    
- “Same subjects under two treatments”
    

**Test:** `t.test(x, y, paired = TRUE, ...)`  
**Use when:**

- Data points are naturally paired
    
- The difference is assumed normally distributed
    

---

## 📌 5. **F-Test for Equality of Variances**

**Wording clues:**

- “Is the variability same between groups?”
    
- “Compare the **variance/spread/consistency** between A and B”
    
- “Do the two brands have similar consistency?”
    

**Test:** `var.test(x, y)`  
**Use when:**

- Comparing **variances** of two independent samples
    
- Assumes normality
    

---

## 📌 6. **Two-Proportion z-Test**

**Wording clues:**

- “Is the **proportion** of A different from B?”
    
- “Do 40% of men vs 50% of women prefer X?”
    
- “Compare success rates, pass rates...”
    

**Test:** `prop.test(x = c(success1, success2), n = c(n1, n2))`  
**Use when:**

- Comparing proportions
    
- Use `alternative = "greater" / "less" / "two.sided"`
    

---

## 📌 7. **ANOVA / F-test for Means (More than 2 groups)**

**Wording clues:**

- “Compare the **means** of 3 or more groups”
    
- “Is there a significant difference in yield among 3 fertilizers?”
    

**Test:** `aov()` or `oneway.test()`  
**Use when:**

- More than 2 groups
    
- Testing for **overall difference**, not pairwise
    

---

## ⚠️ Alternative Hypothesis Mapping

|Wording|`alternative`|
|---|---|
|"Is it **different** from"|`"two.sided"`|
|"Is it **greater** than"|`"greater"`|
|"Is it **less** than"|`"less"`|

---

## 🧠 TL;DR - Decision Tree Style:

| Sample Situation      | Known σ?          | Paired? | # of Groups | Type of Test      |
| --------------------- | ----------------- | ------- | ----------- | ----------------- |
| 1 group vs µ          | No                | -       | 1           | One-sample t-test |
| 1 group vs µ          | Yes               | -       | 1           | One-sample z-test |
| 2 groups              | No                | No      | 2           | Two-sample t-test |
| 2 groups              | No                | Yes     | 2           | Paired t-test     |
| 2 groups              | Compare variances | No      | 2           | F-test            |
| ≥3 groups             | -                 | -       | 3+          | ANOVA (F-test)    |
| Comparing proportions | -                 | -       | 2           | Proportion z-test |

---

Want this in PDF or image form to carry to your exam? I can make one.