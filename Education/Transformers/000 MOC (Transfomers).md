---
tags:
  - "#Map"
---

**These are my notes for Transformers**

```dataview  
TABLE WITHOUT ID file.link AS "Note Title", file.cday AS "Day Created", tags AS "Tags", file.mday AS "Date Modified"
FROM "Transformers"
WHERE !contains(tags, "#Map")
SORT file.link ASC

```
