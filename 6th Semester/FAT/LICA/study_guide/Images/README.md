# Images Folder

This folder is reserved for extracted, generated, or manually added images used by the study guide.

## Required Obsidian Syntax

Use wiki-link syntax:

```text
![[image_name.png]]
```

Do not use standard markdown image syntax for study guide notes.

## Current Asset Status

The extraction output contains 267 image references in `extracted_content/obsidian_references.md`. The corresponding image files were found in `extracted_content/Images/` and copied into this folder so the study guide can resolve the existing `![[image_name.png]]` and `![[image_name.jpg]]` links.

## Expected Naming Pattern

The extracted references use names such as:

- `WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s11_img1.png`
- `WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s25_img1.png`

## Manual Review Checklist

- Confirm Obsidian is configured to resolve attachments from this folder.
- Open each topic file and confirm the visible circuit diagrams or plots render.
- Prioritize filter circuit diagrams, response plots, DAC circuits, and ADC block diagrams.
- If an image is missing or unclear, refer to the original PPTX slide listed in `extracted_content/obsidian_references.md`.
