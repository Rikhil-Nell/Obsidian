## ✅ **Lab Exercise 1**

### **Q1: Calculate mean, median, and standard deviation from a grouped frequency table (class intervals and frequencies).**

```r
# Calculates descriptive statistics (mean, SD, median) from grouped frequency data
# Variables:
# - LB, UB: lower and upper bounds of each class interval
# - freq: number of observations per class
# - mid_pts: midpoints of class intervals
# - mean: weighted average
# - SD: square root of weighted variance
# - median: calculated using the median formula for grouped data

LB = seq(10,70,10)
UB = seq(20,80,10)
freq = c(4,6,10,20,10,6,4)
mid_pts = (LB + UB)/2
mean = sum(freq * mid_pts)/sum(freq)
Dev_sq = (mid_pts - mean)^2
variance = sum(freq * Dev_sq)/sum(freq)
SD = sqrt(variance)

Total_freq = sum(freq)
L = 40
Nby2 = Total_freq / 2
M = 20
F = 20
C = 10
median = L + ((Nby2 - M) / F) * C

cat("Mean:", mean, "\n")
cat("SD:", SD, "\n")
cat("Median:", median, "\n")
```

---

### **Q2: Binomial distribution - File corruption probabilities with 20 trials and p = 0.2**

```r
# Evaluates various binomial probabilities for file corruption scenario:
# - n: number of files opened
# - p: probability of a file being corrupted (4 out of 20)
# Uses dbinom and sum for cumulative probabilities

n = 20
p = 4 / 20

pb1 = sum(dbinom(10:20, n, p))
pb2 = dbinom(3, n, p)
pb3 = dbinom(20, n, p)
pb4 = dbinom(0, n, p)
pb5 = sum(dbinom(3:5, n, p))

cat("i.) At least 10 files corrupted:", pb1, "\n")
cat("ii.) Exactly 3 files corrupted:", pb2, "\n")
cat("iii.) All files corrupted:", pb3, "\n")
cat("iv.) All files safe:", pb4, "\n")
cat("v.) 3 to 5 files corrupted:", pb5, "\n")
```

---

### **Q3: Poisson Distribution - Misprints in a newspaper**

```r
# Uses Poisson distribution to model misprints
# - Lmb: lambda = average errors per page
# - Calculates probabilities using dpois and ppois for various page conditions

Lmb = 1.2
pg4 = dpois(2, Lmb)
pg3lessthan3 = ppois(2, Lmb)
first10pgs = dpois(5, Lmb * 10)
atleast3pgsto40 = 1 - ppois(2, Lmb * 40)

cat("(a) On page 4, P(X=2):", pg4, "\n")
cat("(b) On page 3, P(X<3):", pg3lessthan3, "\n")
cat("(c) First 10 pages, P(X=5):", first10pgs, "\n")
cat("(d) At least 3 errors on 40 pages:", atleast3pgsto40, "\n")
```

---

## ✅ **Lab Exercise 2**

### **Normal Distribution Examples (i to iii)**

```r
# Calculates probabilities using normal distribution
# - mu, sd: mean and standard deviation
# - pnorm used to compute probabilities

mu = 50
sd = sqrt(40)

x = pnorm(60, mu, sd)
y = pnorm(100, mu, sd, lower.tail = FALSE)
cat("P(X ≤ 60):", x, "\n")
cat("P(X ≥ 100):", y, "\n")
```

```r
mu = 14
sd = 2.5
x = pnorm(15, mu, sd)
y = pnorm(12, mu, sd)
ans = (x - y) * 1000
cat("i.) Between 12 and 15:", ans, "\n")

z = pnorm(18, mu, sd)
ans2 = (1 - z) * 1000
cat("ii.) Score above 18:", round(ans2), "\n")

p = z * 1000
cat("iii.) Score below 18:", round(p), "\n")
```

---

### **Q4: Correlation, Covariance, and Regression for Sales and Expenses**

```r
# Performs correlation and regression analysis
# - lm() is used to fit linear regression model
# - cor() for Pearson correlation
# - coef() extracts regression coefficients

x = c(46, 42, 44, 40, 43, 41, 45)
y = c(40, 38, 36, 35, 39, 37, 41)

model = lm(y ~ x)
coeff = coef(model)
cat("Regression line: Y =", coeff[2], "* x +", coeff[1], "\n")

xv = 37
predicted = coeff[1] + xv * coeff[2]
cat("If x=37, predicted y:", predicted, "\n")
```

---

## ✅ **Lab Exercise 3**

### **Q1: Correlation using covariance and standard deviation**

```r
# Fertilizer vs productivity correlation analysis
# Computes:
# - Pearson correlation
# - Covariance
# - Individual standard deviations
# - Plots the line graph

flz = c(15,18,20,24,30,35,40,50)
prd = c(85,93,95,105,120,130,150,160)

cor_coef = cor(flz, prd)
covariance = cov(flz, prd)
std_dev1 = sd(flz)
std_dev2 = sd(prd)

cat("Correlation coefficient:", cor_coef, "\n")
cat("Covariance:", covariance, "\n")
cat("Standard Deviation (Fertilizer):", std_dev1, "\n")
cat("Standard Deviation (Productivity):", std_dev2, "\n")

plot(flz, prd, type = "o", col = "red", lwd = 2, xlab = "Fertilizer", ylab = "Productivity")
```

---

### **Q2: Rank correlation (Spearman) between judges P, Q, R**

```r
# Computes Spearman and Pearson correlation between judge scores
# - Handles missing data in Judge Q
# - Shows correlation for each judge pair and interpretation

P = c(15,14,16,11,18,14,12,11,15,13)
Q = c(14,11,11,10,13,12,16,13,NA,14)
R = c(17,9,12,13,17,13,16,14,13,17)

Q = na.omit(Q)
P = P[1:length(Q)]
R = R[1:length(Q)]

P_Q = cor(P, Q)
P_R = cor(P, R)
Q_R = cor(Q, R)

PQ = cor(P, Q, method = "pearson")
PR = cor(P, R, method = "pearson")
QR = cor(Q, R, method = "pearson")

cat("Spearman: P&Q =", P_Q, ", P&R =", P_R, ", Q&R =", Q_R, "\n")
cat("Pearson:  P&Q =", PQ, ", P&R =", PR, ", Q&R =", QR, "\n")
cat("Highest correlation is Q&R. Judgment agreement best between them.\n")
```

---

### **Q3: Regression between father and son heights**

```r
# Calculates regression lines between father's and son's height
# Predicts:
# - Son's height for a given father's height
# - Father's height for a given son's height

father = c(170.2,180,175.5,162.4,172.6,167.8,158.5,177.1)
son = c(165.3,171.4,169.3,171.2,169.5,170.5,149,169.4)

son_father = lm(son ~ father)
father_son = lm(father ~ son)

son_estimate = predict(son_father, data.frame(father = 157.5))
father_estimate = predict(father_son, data.frame(son = 169.4))

cat("Estimated son height if father is 157.5 cm:", son_estimate, "\n")
cat("Estimated father height if son is 169.4 cm:", father_estimate, "\n")
```

---

### ✅ **Custom Q1: Bar plot of average rice yield across states**

```r
# Generates a bar plot of rice yield across various Indian states
# - x: vector of state names
# - y: corresponding average rice yield values
# - barplot() creates a labeled colored bar chart

x = c("Andhra", "Kerala", "Telangana", "Tamil Nadu", "Orissa", "Karnataka", "Maharastra")
y = c(943, 620, 920, 1160, 800, 1150, 1100)

barplot(y, names.arg = x, col = heat.colors(7), main = "Yield of the Rice",
        xlab = "States", ylab = "Average Yield")
```

---

### ✅ **Custom Q2: Multiple bar plot for faculty-wise student distribution**

```r
# Draws a multiple bar diagram to compare student counts across faculties and colleges
# - A/B/C/D: vectors for Arts, Science, Commerce counts for each college
# - barplot() plots faculty distributions per college with legends

clg = c("A", "B", "C", "D")
A = c(1200, 600, 500)
B = c(100, 800, 650)
C = c(1400, 700, 850)
D = c(750, 900, 300)

d = data.frame(A, B, C, D)
d1 = as.matrix(d)

barplot(d1, beside = TRUE, names.arg = clg,
        col = c('lightblue', 'lightpink', 'lightgreen'),
        legend = c("Arts", "Science", "Commerce"),
        xlab = "College Names", ylab = "No. of Students")
```

---

### ✅ **Custom Q3: Pie chart showing expenditure distribution**

```r
# Plots a pie chart of expenditure categories
# - item: expenditure categories
# - A: amount spent in each category
# - pie() visualizes proportions using color-coded slices

item = c("Food", "Clothing", "Electric", "Movie", "Rent", "Miscellaneous")
A = c(700, 1000, 240, 260, 700, 350)

pie(A, main = "Expenditure", labels = item, radius = 1, col = rainbow(length(A)))
```

---

### ✅ **Custom Q4: One-sample t-tests to verify population mean**

```r
# Performs three one-sample t-tests on student marks data
# - x: sample marks
# - mu: hypothesized population mean
# - alternative: nature of test (≠, >, <)
# - Outputs whether H0 (null) is accepted/rejected

x = c(89,88,78,76,78,78,86,83,82,76,72,77,92)
sig_lvl = 0.05
y = t.test(x, mu = 80, alternative = 'two.sided')
z = y$p.value

if (z > sig_lvl) {
  cat("i.) Accept Null Hypothesis: Mean marks ≈ 80\n")
} else {
  cat("i.) Reject Null Hypothesis: Mean ≠ 80\n")
}

sig_lvl1 = 0.01
y1 = t.test(x, mu = 75, alternative = 'greater')
z1 = y1$p.value

if (z1 > sig_lvl1) {
  cat("ii.) Accept Null Hypothesis: Mean marks ≤ 75\n")
} else {
  cat("ii.) Reject Null Hypothesis: Mean > 75\n")
}

sig_lvl2 = 0.1
y2 = t.test(x, mu = 85, alternative = 'less')
z2 = y2$p.value

if (z2 > sig_lvl2) {
  cat("iii.) Accept Null Hypothesis: Mean marks ≥ 85\n")
} else {
  cat("iii.) Reject Null Hypothesis: Mean < 85\n")
}
```

---

### ✅ **Custom Q5: Independent two-sample t-tests between two crop varieties**

```r
# Compares yields of two crop varieties A and B using independent t-tests
# - sig_lvl: significance levels for multiple hypotheses
# - mu: expected mean difference (0, 2, 0.5)
# - var.equal=TRUE assumes equal variances

A = c(22,24,26,23,26,30,32,34)
B = c(28,25,26,30,33,28,30,35)
sig_lvl = 0.02

y = t.test(A, B, var.equal = TRUE, mu = 0)
z = y$p.value
cat(ifelse(z > sig_lvl, "i.) Accept Null: Yields are equal\n", "i.) Reject Null: Yields differ\n"))

sig_lvl1 = 0.05
y1 = t.test(A, B, var.equal = TRUE, mu = 2, alternative = 'less')
z1 = y1$p.value
cat(ifelse(z1 > sig_lvl1, "ii.) Accept Null: A ≤ B by 2 units\n", "ii.) Reject Null: A < B\n"))

sig_lvl2 = 0.1
y2 = t.test(A, B, var.equal = TRUE, mu = 0.5, alternative = 'greater')
z2 = y2$p.value
cat(ifelse(z2 > sig_lvl2, "iii.) Accept Null: A not greater than B by 0.5\n", "iii.) Reject Null: A > B\n"))

sig_lvl3 = 0.05
y3 = t.test(A, B, var.equal = TRUE, mu = 0, alternative = 'greater')
z3 = y3$p.value
cat(ifelse(z3 > sig_lvl3, "iv.) Accept Null: A is not significantly greater\n", "iv.) Reject Null: A is greater\n"))
```

---

### ✅ **Custom Q6: Paired t-test for before-and-after comparison**

```r
# Performs a paired t-test on two related samples (e.g., pre/post-treatment)
# - X, Y: paired data samples
# - Test whether the mean difference is zero

X = c(6.6,6.85,6.75,7.2,6.75,6.65,6.7,7.3,6.9,6.6)
Y = c(6.9,7.3,7,7.6,6.85,7.3,6.7,7.45,7.3,6.5)
slv = 0.05

y = t.test(X, Y, paired = TRUE)
z = y$p.value

cat(ifelse(z > slv, "Accept Null Hypothesis: No significant difference\n",
                  "Reject Null Hypothesis: Significant difference found\n"))
```

---

### ✅ **Custom Q7: One-sample z-test to compare against known population mean**

```r
# Performs z-test on a single sample with known population standard deviation
# - x: sample data
# - mu: hypothesized mean
# - sigma.x: population SD
# - z.test() from BSDA library

library(BSDA)

x = c(88,92,94,94,96,97,97,97,99,99,105,109,109,109,110,
      112,112,113,114,115,93,95,106,108,113,91,98,99,102,107)
slv = 0.05

y = z.test(x, mu = 100, sigma.x = 15, alternative = 'two.sided')
z = y$p.value

cat(ifelse(z > slv, "Accept Null: Mean = 100\n", "Reject Null: Mean ≠ 100\n"))
```

---

### ✅ **Custom Q8: Two-sample z-test for sales comparison between beverages**

```r
# Compares mean sales of two beverages using two-sample z-test
# - A/B: sales figures for each product
# - sigma.x/y: population SDs
# - Test whether product A outsold B

A = c(56,65,37,47,66,76,75,31,80,45,34,42,42,23,67,47,45,50,45,42,
      59,34,50,48,65,41,53,41,36,39,51,69,30,52,42)
B = c(51,47,53,40,70,49,63,71,47,62,65,62,56,74,49,33,80,60,46,65,
      48,61,54,67,65,48,46,66,52,65,62,59,63,44,50)
slv = 0.05

y = z.test(A, B, mu = 0, sigma.x = 15, sigma.y = 12, alternative = 'less')
pv = y$p.value

cat(ifelse(pv > slv, "Accept Null: No sales difference\n",
                 "Reject Null: Mojito outsold lime juice\n"))
```

---

### ✅ **Custom Q9: F-test for variance comparison between groups**

```r
# Performs F-test (variance ratio test) between two groups
# - A, B: samples with assumed normality
# - alternative: 'two.sided', 'greater', or 'less'
# - Tests whether variance differs significantly

A = c(22,24,26,23,26,30,32,34)
B = c(28,25,26,30,32,30,33,28,30,35)
slv = 0.05

y = var.test(A, B, alternative = 'two.sided')
z = y$p.value
cat(ifelse(z > slv, "i.) Accept Null: Variances are equal\n",
                  "i.) Reject Null: Variances differ\n"))

slv1 = 0.01
y1 = var.test(A, B, alternative = 'greater')
z1 = y1$p.value
cat(ifelse(z1 > slv1, "ii.) Accept Null: A's variance ≤ B\n",
                   "ii.) Reject Null: A's variance > B\n"))

slv2 = 0.01
y2 = var.test(A, B, alternative = 'less')
z2 = y2$p.value
cat(ifelse(z2 > slv2, "iii.) Accept Null: A's variance ≥ B\n",
                   "iii.) Reject Null: A's variance < B\n"))
```


## ✅ **Lab Exercise 4**

---

### **Q1: Represent faculty-wise student data by a multiple bar diagram**

```r
# Creates a multiple bar chart from student counts across faculties and colleges
# - Each column (A, B, C, D) represents a college
# - Rows represent the count of students in Arts, Science, and Commerce
# - Data is transformed into matrix form and passed to barplot() with colors and legend

clg = c("A", "B", "C", "D")
A = c(1200, 600, 500)
B = c(1000, 800, 650)
C = c(1400, 700, 850)
D = c(750, 900, 300)

d = data.frame(A, B, C, D)
d1 = as.matrix(d)

barplot(d1, beside = TRUE, names.arg = clg,
        col = c('lightblue', 'lightpink', 'lightgreen'),
        legend = c("Arts", "Science", "Commerce"),
        xlab = "College Names", ylab = "No. of Students")
```

---

### **Q2: Represent expenditure data using a pie chart**

```r
# Plots a pie chart of household expenditures
# - item: categories of expenses
# - A: expense values in rupees
# - pie() visualizes expenditure proportions in a color-coded pie format

item = c("Food", "Clothing", "Electric", "Movie", "Rent", "Miscellaneous")
A = c(87, 24, 11, 13, 25, 20)

pie(A, main = "Expenditure", labels = item, radius = 1, col = rainbow(length(A)))
```

Perfect. Here’s the mapped and cleaned-up code for **Lab Exercises 5 & 6** from the PDF, fully aligned with the `.txt` code. Like before, each code block starts with a clear explanation so you can understand the logic without digging through inline comments.

---

## ✅ **Lab Exercise 5 & 6**

---

### **Q1: Compare whether Horse A is running faster than Horse B using t-test (5% level)**

```r
# Compares race times of two horses using a two-sample t-test
# - horse_A and horse_B: race times (in seconds)
# - alternative = "greater": test if Horse A is faster (i.e., lower time)
# - var.equal = TRUE assumes equal variances between groups

horse_A = c(28, 30, 32, 33, 33, 29, 34)
horse_B = c(29, 30, 30, 24, 27, 29)
slv = 0.05

y = t.test(horse_A, horse_B, alternative = "greater", var.equal = TRUE)
z = y$p.value

if (z > slv) {
  cat("Accept Null Hypothesis: Horse A is not faster than Horse B\n")
} else {
  cat("Accept Alternative Hypothesis: Horse A is faster than Horse B\n")
}
```

---

### **Q2: Test if variance increased in new machine's cereal packing**

```r
# Tests for increase in variance between two packaging machines using F-test
# - old/new: sample masses from each machine
# - var.test() checks if the variance of the new machine > old machine
# - Hypothesis is one-sided at 5% significance level

old = c(301.0, 292.4, 293.6, 298.7, 291.1, 285.1, 305.1, 290.0, 297, 302.2)
new = c(295.3, 289.4, 288.5, 299.8, 293.6, 308.9, 320.4, 312.2, 292.9, 300.2, 276.3, 280.3)
slv = 0.05

y = var.test(old, new, alternative = "greater")
z = y$p.value

if (z > slv) {
  cat("Accept Null Hypothesis: Variance has not increased with the new machine\n")
} else {
  cat("Accept Alternative Hypothesis: Variance has increased with the new machine\n")
}
```

---

### **Q3: Determine if night shift produces more units than day shift (5% level)**

```r
# Uses two-sample z-test to compare means of units produced in two shifts
# - day/night: number of units produced by sampled workers
# - Population SDs known: sigma.x = 21 (day), sigma.y = 28 (night)
# - One-sided test: Is night shift more productive?

library(BSDA)

day = c(374,346,345,351,336,377,340,366,325,344,353,370,351,355,306,323,349,344,
        348,339,354,347,417,350,350,299,289,320,326,344,384,326,396)

night = c(343,340,349,403,374,323,302,314,339,386,331,366,372,379,363,361,349,
          357,305,376,354,387,370,338,355,291,333,362,389,316,370,324,321,339,336)

slv = 0.05

y = BSDA::z.test(day, night, sigma.x = 21, sigma.y = 28, alternative = "less")
z = y$p.value

if (z > slv) {
  cat("Accept Null Hypothesis: Day shift is at least as productive as night shift\n")
} else {
  cat("Accept Alternative Hypothesis: Night shift is more productive than day shift\n")
}
```

---
