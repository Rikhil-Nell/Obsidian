| Function     | Arguments                                           | When to Use                                                                 | Example |
|--------------|-----------------------------------------------------|------------------------------------------------------------------------------|---------|
| `mean()`     | `x`                                                 | To calculate average of a numeric vector                                   | `mean(c(1,2,3))` |
| `sd()`       | `x`                                                 | To calculate standard deviation                                            | `sd(c(1,2,3))` |
| `var()`      | `x`                                                 | To calculate variance                                                      | `var(c(1,2,3))` |
| `plot()`     | `x`, `y`, `type`, `main`, `xlab`, `ylab`, `col`     | To create scatter plots, line graphs, etc.                                 | `plot(x, y, type="l", col="blue")` |
| `dbinom()`   | `x`, `size`, `prob`                                 | Discrete probability (P(X=x)) in binomial distribution                     | `dbinom(3, 10, 0.5)` |
| `pbinom()`   | `q`, `size`, `prob`, `lower.tail`                   | Cumulative binomial probability (P(X ≤ x))                                 | `pbinom(3, 10, 0.5)` |
| `dpois()`    | `x`, `lambda`                                       | P(X = x) for Poisson distribution                                          | `dpois(2, 1.5)` |
| `ppois()`    | `q`, `lambda`, `lower.tail`                         | Cumulative Poisson (P(X ≤ x))                                              | `ppois(3, 1.5)` |
| `pnorm()`    | `q`, `mean`, `sd`, `lower.tail`                     | Cumulative Normal probability                                              | `pnorm(60, mean=50, sd=6.3)` |
| `qnorm()`    | `p`, `mean`, `sd`                                   | Get Z-score or value for given probability                                | `qnorm(0.95, 50, 6.3)` |
| `cor()`      | `x`, `y`, `method`                                  | Get correlation (Pearson/Spearman/Kendall)                                | `cor(x, y, method="spearman")` |
| `cov()`      | `x`, `y`                                            | Get covariance between two variables                                      | `cov(x, y)` |
| `lm()`       | `formula`, `data`                                   | Fit linear regression model                                                | `lm(y ~ x)` |
| `predict()`  | `model`, `newdata`                                  | Predict using linear model                                                 | `predict(model, newdata=data.frame(x=37))` |
| `t.test()`   | `x`, `y`, `paired`, `var.equal`, `alternative`      | Compare means; single or two-sample t-test                                | `t.test(x, y, paired=TRUE)` |
| `var.test()` | `x`, `y`, `alternative`                             | Compare variances of two samples                                           | `var.test(x, y)` |
| `z.test()`   | `x`, `mu`, `sigma.x`, `alternative`                 | One-sample z-test (from `BSDA` package)                                   | `z.test(x, mu=50, sigma.x=5)` |
| `$p.value`   | Accessed from test result object                    | To extract p-value from any test (t, z, var, etc.)                         | `result$p.value` |


#### `mean(x)`

- **x**: Numeric vector.
    
- **Purpose**: Computes the arithmetic average.
    
- **When to use**: To get a central tendency of data.
    

---

#### `sd(x)`

- **x**: Numeric vector.
    
- **Purpose**: Measures the spread of data from the mean.
    
- **Formula**: √(∑(xᵢ - mean)² / (n-1))
    

---

#### `var(x)`

- **x**: Numeric vector.
    
- **Purpose**: Measures spread; variance is SD squared.
    
- **Use**: Good for mathematical modeling or variance comparison.
    

---

#### `plot(x, y, type, main, xlab, ylab, col)`

- **x, y**: Data vectors.
    
- **type**: `"p"` for points, `"l"` for lines.
    
- **main**: Title.
    
- **xlab/ylab**: Axis labels.
    
- **col**: Color.
    
- **Use**: Basic plotting to visualize data.
    

---

#### `dbinom(x, size, prob)`

- **x**: Number of successes.
    
- **size (n)**: Number of trials.
    
- **prob (p)**: Probability of success.
    
- **Use**: Gives exact P(X = x) in binomial distribution.
    
- **Theory**: P(X = x) = C(n, x) * p^x * (1-p)^(n-x)
    

---

#### `pbinom(q, size, prob, lower.tail)`

- **q**: Quantile (X ≤ q).
    
- **size**: Number of trials.
    
- **prob**: Success probability.
    
- **lower.tail**: `TRUE` = P(X ≤ q), `FALSE` = P(X > q).
    
- **Use**: Cumulative probability in binomial distribution.
    

---

#### `dpois(x, lambda)`

- **x**: Event count.
    
- **lambda (λ)**: Average rate of occurrence.
    
- **Use**: Exact P(X = x) for rare events.
    
- **Theory**: P(X = x) = (λ^x * e^-λ) / x!
    

---

#### `ppois(q, lambda, lower.tail)`

- **q**: Number of events.
    
- **lambda**: Mean rate.
    
- **lower.tail**: `TRUE` = P(X ≤ q), `FALSE` = P(X > q).
    
- **Use**: Cumulative Poisson distribution.
    

---

#### `pnorm(q, mean, sd, lower.tail)`

- **q**: Quantile.
    
- **mean**: μ (center of distribution).
    
- **sd**: σ (spread).
    
- **lower.tail**: `TRUE` = P(X ≤ q), `FALSE` = P(X > q).
    
- **Use**: For continuous data, P(X ≤ x).
    

---

#### `qnorm(p, mean, sd)`

- **p**: Probability.
    
- **mean, sd**: For normal distribution.
    
- **Use**: Find value `x` such that P(X ≤ x) = p.
    

---

#### `cor(x, y, method)`

- **x, y**: Numeric vectors.
    
- **method**: `"pearson"` (linear), `"spearman"` (rank), `"kendall"`.
    
- **Use**: Check strength and direction of linear/non-linear relation.
    
- **Output**: Between -1 and +1.
    

---

#### `cov(x, y)`

- **x, y**: Numeric vectors.
    
- **Use**: Tells whether two variables move together. Positive = same direction.
    

---

#### `lm(formula, data)`

- **formula**: `y ~ x`.
    
- **data**: Optional data frame.
    
- **Use**: Fits a linear model (regression).
    
- **Output**: Model object.
    

---

#### `predict(model, newdata)`

- **model**: Output from `lm()`.
    
- **newdata**: Data frame with `x` values.
    
- **Use**: Predicts `y` using fitted model.
    

---

#### `t.test(x, y, paired, var.equal, alternative)`

- **x, y**: Numeric vectors.
    
- **paired**: `TRUE` for dependent samples.
    
- **var.equal**: Assume equal variances or not.
    
- **alternative**: `"two.sided"`, `"less"`, or `"greater"`.
    
- **Use**: Hypothesis testing for means.
    

---

#### `var.test(x, y, alternative)`

- **x, y**: Numeric vectors.
    
- **alternative**: `"two.sided"`, `"less"`, or `"greater"`.
    
- **Use**: Tests if variances of two populations differ.
    

---

#### `z.test(x, mu, sigma.x, alternative)` _(from `BSDA` package)_

- **x**: Sample vector.
    
- **mu**: Population mean.
    
- **sigma.x**: Known population SD.
    
- **alternative**: Type of test (`"two.sided"` etc.).
    
- **Use**: Z-test when σ is known, sample size large.
    

---

#### `$p.value`

- **Use**: Extract p-value from test result (`t.test()`, `z.test()`, etc.).
    
- **Interpretation**: Small p-value (< 0.05) ⇒ statistically significant result.
    

---
