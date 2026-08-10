---
tags:
  - "#Map"
---

**These are my notes for Machine Learning**

```dataview  
TABLE WITHOUT ID file.link AS "Note Title", file.cday AS "Day Created", tags AS "Tags", file.mday AS "Date Modified"
FROM "Machine Learning"
WHERE !contains(tags, "#Map")
SORT file.link ASC

```
