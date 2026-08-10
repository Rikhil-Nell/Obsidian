# Job Dashboard

## Stats

```dataview
TABLE WITHOUT ID
  length(rows) as Companies
FROM "Jobs/Companies"
GROUP BY true
```

```dataview
TABLE WITHOUT ID
  length(rows) as Interviews
FROM "Jobs/Interviews"
GROUP BY true
```

```dataview
TABLE WITHOUT ID
  length(rows) as Rejections
FROM "Jobs/Rejections"
GROUP BY true
```

```dataview
TABLE WITHOUT ID
  length(rows) as "Skills Tracked"
FROM "Jobs/Learn"
GROUP BY true
```

---

## CRM Views

![[Jobs CRM.base]]
