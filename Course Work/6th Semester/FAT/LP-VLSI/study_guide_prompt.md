# Study Guide Generator Prompt Template

Use this prompt when asking an AI agent to create comprehensive study materials from course content.

---

## THE PROMPT

```
I have an exam coming up for Linear Integrated Circuit Applications. I am attaching my course material as [PDF/PPT/DOCX/images].

Create a comprehensive, exam-focused study guide following this structured approach:

---

## PHASE 0: Setup (Mandatory)

1. Create an implementation plan (implementation_plan.md) detailing:
   - Source materials to process
   - Topics identified from initial scan
   - Proposed file structure
   - Verification methodology

2. Request my approval before proceeding to Phase 1

---

## PHASE 1: Extract and Understand (IF EXTRACTED SCRIPT EXISTS DON'T EXECUTE THIS)

1. Use the provided extraction script (or enhanced version) to exhaustively extract:
   - All text content with page/slide references
   - Section headers and hierarchy
   - All equations and formulas (preserve LaTeX where possible)
   - All worked examples/problems with their solutions
   - All figures/diagrams (capture descriptions and referenced values)
   - All tables with their data
   - Footnotes, captions, and margin notes

2. Save extracted content as:
   - `extracted_content/raw_text.txt` - Plain text for reference
   - `extracted_content/structured.md` - Formatted with headers
   - `extracted_content/equations.json` - All detected equations
   - `extracted_content/problems.json` - All worked examples
   - `extracted_content/extraction_summary.md` - Coverage report

3. Analyze for:
   - Main concepts and their dependencies
- Formula derivation chains
   - Problem types and solution patterns
   - Sign conventions and edge cases

---

## PHASE 2: Create Modular Study Files

Create the following files in a `study_guide/` directory:

### 2.1 Roadmap File
**File**: `00_roadmap.md`
- Learning path with concept dependencies (use mermaid diagram)
- Topic priority matrix (High/Medium/Low based on complexity and likely exam weight)
- Suggested study order with time estimates
- Concept quick-reference table linking topics to files

### 2.2 Topic Files
**Files**: `01_[topic].md`, `02_[topic].md`, etc.

Each topic file must include:
1. **Learning Objectives** - What you will understand after this section
2. **Ground-Up Explanation** - Assume zero prior knowledge on first mention
3. **Analogies** - Relatable comparisons to aid memorization
4. **Key Formulas** - With ALL variables defined, units specified, boxed critical equations
5. **Derivations** - Show how formulas connect (where applicable)
6. **Worked Examples** - Match every problem type from source material
7. **Common Mistakes** - Gotchas and edge cases
8. **Self-Check Questions** - With hidden answers
9. **Concept Links** - References to related topics and formula sheet sections

### 2.3 Worked Problems File
**File**: `[N-1]_worked_problems.md`
- Solve EVERY problem from the source material
- Group by topic/problem type
- Each problem must have:
  - **Concepts Used**: Backlinks to relevant topic files (e.g., `[Topic Name](./02_topic.md#section)`)
  - **Formulas Used**: Backlinks to formula sheet (e.g., `[Formula Name](./[N]_formula_sheet.md#section)`)
  - **Step-by-step solution** with unit tracking
  - **Answer boxed** at the end
  - **Verification** where applicable

### 2.4 Formula Sheet
**File**: `[N]_formula_sheet_ultimate.md`
- Organized by topic with anchor links
- Sections:
  - Physical constants with values and units
  - Unit conversion reference table
  - Formulas grouped by topic (use boxed notation for critical ones)
  - Quick reference tables for commonly computed values
  - Sign conventions summary
  - Pre-submission checklist

---

## PHASE 3: Quality Verification

After creating all files, verify:
1. **Page Coverage**: Every page/slide of source is represented
2. **Problem Coverage**: Every worked example has a matching solution
3. **Formula Coverage**: Every equation is in the formula sheet
4. **Link Integrity**: All backlinks work correctly
5. **Consistency**: Notation is uniform across all files

Create a verification checklist in `walkthrough.md`.

---

## FORMAT REQUIREMENTS

- Use proper LaTeX: `$$formula$$` for display, `$inline$` for inline
- Use tables for organized data
- Use `$$\boxed{formula}$$` for critical equations
- Use mermaid diagrams for concept flows
- NO emojis in any files
- Use markdown links for cross-references: `[text](./file.md#anchor)`

---

## CONTENT REQUIREMENTS

- **First Mention**: Elaborate explanation with context and motivation
- **Subsequent Mentions**: Can use concise bullet points as reinforcement
- **Repetition**: Intentionally repeat key concepts across relevant sections
- **Practical Focus**: Everything should help answer exam questions
- **Edge Cases**: Always note boundary conditions and special cases

---

## MY KNOWLEDGE LEVEL

[Choose one or customize]
- Assume I know nothing - explain from absolute basics
- Assume basic familiarity - focus on application and nuance
- Assume strong foundation - focus on advanced topics and tricks

---
```

---

## NOTES FOR DIFFERENT SOURCE TYPES

### PDFs (Textbooks/Notes)
- Use PyMuPDF (fitz) for text extraction
- Preserve page numbers for reference
- Handle multi-column layouts
- Extract embedded images and their captions

### PowerPoint Files
- Use python-pptx library
- Extract text from slides and notes sections
- Preserve slide numbers
- Handle SmartArt and shapes

### Images (Handwritten Notes, Diagrams)
- Option A: Use OCR (pytesseract) for text extraction
- Option B: Use vision models (if available) for description
- Always include image path in output for manual review
- For graphs: describe axes, trends, key data points

### Word Documents
- Use python-docx library
- Preserve heading hierarchy
- Extract tables and embedded equations

---

## EXTRACTION SCRIPT USAGE

Before starting, run:
```
python extract_course_material.py --input [path] --output extracted_content/
```

The script handles: PDF, PPTX, DOCX, and images

---

## OBSIDIAN INTEGRATION

I use Obsidian for my notes. Please follow these conventions:

### Image Folder Structure
- Create an `Images/` folder in the study guide directory
- Save all images (extracted, generated, or screenshot) to this folder
- Use descriptive snake_case names: `mos_structure_cross_section.png`

### Image Reference Syntax
Use Obsidian wiki-link format for all image references:
```
![[image_name.png]]
```

NOT standard markdown format:
```
![alt](./Images/image_name.png)  <!-- DON'T USE THIS -->
```

### When to Create/Capture Images

1. **Complex Circuit Diagrams**: If ASCII art cannot adequately represent a circuit, take a screenshot or generate an image
2. **Graphs with Multiple Curves**: Screenshot from source or generate
3. **Cross-Section Diagrams**: Generate or extract from PDF
4. **Flowcharts**: Use mermaid if simple, generate image if complex
5. **Comparison Tables with Visual Elements**: Generate as image if markdown table is insufficient

### Image Capture Workflow

For the AI agent:
1. Extract images from source PDFs to `Images/` folder
2. For diagrams you cannot draw in ASCII:
   - Use `generate_image` tool to create the diagram
   - Save to `Images/` folder with descriptive name
   - Reference in notes as `![[descriptive_name.png]]`
3. For screenshots from browser:
   - Capture the relevant po
   - Reference appropriately

### Naming Convention

| Content Type | Naming Pattern | Example |
|--------------|----------------|---------|
| Circuit diagram | `[topic]_circuit.png` | `![[cs_stage_resistive_load_circuit.png]]` |
| Cross-section | `[device]_cross_section.png` | `![[nmos_cross_section.png]]` |
| Graph/plot | `[relationship]_graph.png` | `![[id_vs_vds_characteristics.png]]` |
| Comparison | `[items]_comparison.png` | `![[nmos_pmos_comparison.png]]` |
| Extracted figure | `[source]_fig[num].png` | `![[razavi_fig_2_3.png]]` |

---

## HANDLING IMAGES AND GRAPHS: FEASIBILITY NOTES

### What Works Well

| Source Type | Extraction Method | Quality |
|-------------|-------------------|---------|
| Embedded PDF images | PyMuPDF extracts as PNG | Excellent |
| PPT images | python-pptx exports blobs | Excellent |
| Scanned text | Tesseract OCR | Good (depends on quality) |
| Screenshots | Direct copy | Excellent |
| Circuit diagrams | Extract + manual description | Moderate |

### What Is Challenging

| Source Type | Challenge | Workaround |
|-------------|-----------|------------|
| Hand-drawn graphs | OCR cannot interpret | Describe manually or use vision models |
| Complex equations in images | LaTeX conversion difficult | Type them manually from extracted image |
| Multi-part figures | Caption association | Add cross-references manually |
| Low-resolution scans | OCR accuracy drops | Request better source material |

### Recommended Approach for Graphs

1. **Extract the image** to `figures/` directory
2. **Create a description** including:
   - Axes labels and units
   - Key data points or curves
   - Trends or relationships shown
   - Any equations derived from the graph
3. **Reference in notes** with path: `![Description](./figures/filename.png)`

### Vision Model Integration (Future Enhancement)

If vision models become available:
```python
# Pseudo-code for vision-based graph analysis
def analyze_graph(image_path):
    prompt = """
    Analyze this graph and extract:
    1. X-axis label and range
    2. Y-axis label and range
    3. All labeled curves/lines
    4. Key data points
    5. Any equations or relationships
    """
    return vision_model.analyze(image_path, prompt)
```

### Current Best Practice

For now, the script:
1. Extracts all images to `figures/` folder
2. Logs image locations in `extraction_summary.md`
3. You should manually review images and add descriptions where critical

---

## QUICK START

1. Place this prompt template in your course folder
2. Copy the extraction script to the same location
3. Run: `python extract_course_material.py --input "course_materials/" --output "extracted/"`
4. Attach:
   - Your course materials (PDF, PPT, DOCX)
   - The extraction output folder
   - This prompt (customized for your subject)
5. Let the AI create your study guide

---

## CHECKLIST BEFORE STARTING

- [ ] All source materials are digitized (not physical)
- [ ] PDFs are not password-protected
- [ ] Scanned documents have reasonable quality
- [ ] You have identified any question papers to include
- [ ] You know your exam date (for time estimation)
- [ ] You have specified your knowledge level in the prompt
