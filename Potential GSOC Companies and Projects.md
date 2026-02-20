# 1. Internet Archive

Project 3: Tapestry AI Enhancement

- Size: 350 hours
    
- Difficulty: Medium
    
- Description*: The Tapestries project ([https://tapestries.archive.org](https://tapestries.archive.org), [GitHub](https://github.com/internetarchive/tapestry-project)) currently allows users to connect their Google Gemini account to use a chat window to construct or analyze content in Tapestries. We’d like to enhance this capability: allowing the use of other services (including examining the possibility for self-hosted services) and deepening the reach into included documents of different types as well as different sources of data, particularly in the Internet Archive. Other ideas involving Tapestries are more than welcome!
    
- Outcome: AI is more accessible and useful to Tapestries users allowing semi-automated creation of more interesting documents.
    
- Skills: Node, TypeScript, some experience with MCP helpful
    

  
[Click here to express interest in the Wayback Machine](https://docs.google.com/forms/d/1ug1dAbUN0tQsQoOeDPQ8RQNro_DBsbDXNg-9vztJIxc/prefill)

# 2. The HoneyNet

**Mentor:** Matteo Lodi, backups to be defined  
**Project type:** Improving an existing tool  
**URL:** [https://github.com/intelowlproject/IntelOwl](https://github.com/intelowlproject/IntelOwl)  
**Expected Project hours:** 175 - 350 based on received proposal  
  

### Project Overview

The goal of this project is to revolutionize how analysts interact with IntelOwl by integrating a cutting-edge, self-deployed LLM-based chatbot. This tool will transform static threat intelligence into a conversational experience, allowing users to query complex data using natural language.

By leveraging modern AI frameworks, the project aims to make threat investigation more intuitive, efficient, and accessible.

### Key Objectives

- Conversational Interface Development: Utilize Python, LangChain, and ChainLit (or whatever tool is better to be used) to build a seamless chat interface capable of handling complex natural language queries (e.g., “Which campaigns are associated with this IOC?”).
    
- IntelOwl Module Integration: Deeply interface the chatbot with IntelOwl’s enrichment modules, allowing the AI to trigger deeper investigations and pull real-time data when required.
    
- Self-Deployed Architecture: Ensure the LLM infrastructure is self-hosted to maintain data privacy and security—a critical requirement for threat intelligence environments.
    
- Adaptive Investigative Workflows: Design the system to adapt to evolving user needs, streamlining the communication between the analyst and the platform’s underlying data.
    

### Contributor Profile & Required Soft Skills

The LLM and AI landscape is moving at an incredible pace. To succeed in this project, we are looking for a contributor who demonstrates:

- Proactivity: you propose creative solutions and architectural improvements to overcome technical hurdles.
    
- Technical Autonomy: You are comfortable diving into dense documentation and managing your development cycles with minimal supervision.
    
- High Adaptability: You stay updated with the latest open-source AI frameworks and are ready to pivot or adapt your approach as new, more efficient models or tools emerge.

# 3. Rocket Chat
### 💡 AI Rocket.Chat Apps Generator

[](https://github.com/RocketChat/google-summer-of-code/blob/main/google-summer-of-code-2026.md#-ai-rocketchat-apps-generator)

👥 **Mentor(s):** Dnouv  
📢 **Communication Channel:** [team channel](https://open.rocket.chat/channel/idea-AI-Rocket-Chat-Apps-Generator)

💬 **Description:**  
This is a set of extension (or a fork) of open source `gemini-cli` that will facilitate anyone to create/generate their own Rocket.Chat app with ease.

The tool must have built-in internal knowledge of the architecture of a Rocket.Chat App, how to build and test an App, and how to generate ALL the elements that an App can use to interface with the Apps Engine/server (bridged APIs, web hooks, persistence, per-user state management and so on).   

The tool should also be able to generate and maintain tests for the created App.

💪 **Desired Skills:**

- Experience with modern code generation cli (Claude Code, OpenCode, OpenAI Codex, `gemini-cli` and so on )
- A passion for creating tooling for AI coding  
- Familiarity with Rocket.Chat App creation and Apps Engine operation
- TypeScript development 
- `gemini-cli` architecture and extension mechanisms
- Prompt engineering

🎯 **Goals/Deliverables:**  
A very easy to use and understand CLI tool that anyone can use to create, test, and deploy their own custom Rocket.Chat apps.

⏳ **Project Duration:**  
175 hours

📈 **Difficulty:**  
Medium

### 💡 Rocket.Chat Code Analyzer: agentic inference context reduction mechanics 

[](https://github.com/RocketChat/google-summer-of-code/blob/main/google-summer-of-code-2026.md#-rocketchat-code-analyzer-agentic-inference-context-reduction-mechanics)

👥 **Mentor(s):** William Liu  
📢 **Communication Channel:** [team channel](https://open.rocket.chat/channel/idea-Rocket-Chat-Code-Analyzer)

💬 **Description:**  
Most production codebases are stored in huge revision control repositories (similar to Rocket.Chat) and are often monorepos that combines the source code of a large number of related subprojects.  

When AI agentic tooling is unleashed on these huge code repositories, it quickly reveals the primitive and wasteful (unoptimized) nature of early tools. Because LLM inferences are being performed inside a loop where the context of the queries are being built; and the context is constantly increasing in size, query after query.   

This means that repositories as large as Rocket.Chat is often out of reach (of the token/AI-inference budget) for many open source developers. Even though some AI service providers offer per-session caching and compression (llmlinqua and so on), these are O(n) optimizations that have only nominal impact on the overall project/session cost when large repositories are involved.  

This project explores and implements a class of “domain specific context reduction mechanisms” that can have exponential impact when working with large code repositories. These scoping mechanisms are specific to (works only with) the domain of “code analysis/generation”.  

The project’s code will be an extension or fork of `gemini-cli`, with the context reduction mechanism added. It will enable users of `gemini-cli` to work with (analyze, or generate code based on ) the (known to be huge) Rocket.Chat’s monorepo, all within the budget of the “free tier” inference currently offered by Google.  
Ideally, the mechanics should be implemented in a re-usable manner, extending its utility to other large codebases and the contributor can contribute it back upstream to `gemini-cli`. 

💪 **Desired Skills:**  

- A passion for innovations on open source tooling for the age of open source AI (vibe) coding
- Experience with modern code generation cli - Claude Code, OpenCode, OpenAI Codex, gemini-cli and so on
- Intimate understanding of how gemini-cli works  — Familiarity with Rocket.Chat’s codebase in our monorepo
- TypeScript development 
- Coding with `gemini-cli` extension mechanisms
- Prompt engineering 
- Theoretical understanding of agentic systems and LLM inference

🎯 **Goals/Deliverables:**   Tooling that enables open source AI developers to work with huge production code repositories, within industry provider’s free-tier limits; opening access of these great tools to an exponentially larger population of users.

⏳ **Project Duration:**  
175 hours

📈 **Difficulty:**  
Medium
# 4. Gemini CLI
https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0

# Gemini CLI Summer Of Code 2026 - Ideas List

[Bryan Morgan](mailto:bryanmorgan@google.com)

Feb 4, 2026

  

A partial list of ideas for Google Summer of Code 2026 participants contributing to the Gemini CLI project is provided here. Summary information for these ideas is as follows:                                                                                                                                                  

- Mix of difficulties: 2 Hard (350hr), 7 Medium (175hr), 1 Easy-Medium (90hr)                                                                                                
    
- Aligned with existing roadmap items: Extensions gallery (#18246), Hooks (#18253), Windows quality (#18251), Evals (#18257), Skills (#18255)                                
    
- Mentors provided are only suggestions and are subject to change
    

## 2026 Gemini CLI Ideas List 

  

[1 - Network Traffic Proxy and Domain Filtering](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

[2 - Behavioral Evaluation Test Framework](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

[3 - Windows Developer Experience Improvements](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

[4 - Multi-IDE Integration Enhancement](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

[5 - Performance Monitoring and Optimization Dashboard](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

[6 - Interactive Progress Visualization & Task Stepping](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

[7 - Terminal-Integrated Debugging Companion](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

[8 - Native Windows Sandbox using AppContainer](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

[9 - Interactive Security Policy and Sandbox Wizard](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

[10 - Unified Sandbox Driver Architecture](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

[11 - Hands-Free Multimodal Voice Mode](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

[12 - Generative Architecture & UI Visualization](https://docs.google.com/document/d/1iaMZliqwUn-ACyZAbgzdXmDiQZ7l5gp8UQIIY2BnPO8/edit?tab=t.0#heading=)

### 1 - Network Traffic Proxy and Domain Filtering

Difficulty: Medium | Size: 175 hours | Area: Security

#### Description

Implement a network proxy layer for sandboxed command execution that routes HTTP/HTTPS and other TCP traffic through a controlled gateway. This enables domain allowlisting/denylisting, traffic logging for audit purposes, and permission prompts for new network destinations. The feature would provide defense-in-depth protection even against potential sandbox escapes

Expected Outcomes

- HTTP/HTTPS proxy implementation with domain filtering capabilities
    
- SOCKS5 proxy support for non-HTTP traffic (SSH, database connections)
    
- Configurable allowlist/denylist with wildcard pattern support
    
- Permission prompt UI for new/unknown domains
    
- Traffic logging for security auditing (opt-in)
    
- Integration with existing Seatbelt and Docker sandbox modes
    

#### Skills Required/Preferred

- Required: TypeScript/Node.js, networking fundamentals (TCP/IP, HTTP, proxies)
    
- Preferred: Experience with proxy servers (mitmproxy, squid), TLS/SSL, network security
    

#### Possible Mentors

- [Gaurav Ghosh](mailto:gaghosh@google.com)
    

  

### 2 - Behavioral Evaluation Test Framework

Difficulty: Medium | Size: 175 hours | Area: Quality/Testing

#### Description

Develop a comprehensive behavioral evaluation framework for testing Gemini CLI's agent capabilities against real-world coding scenarios. This includes creating a benchmark suite of coding tasks, measuring success rates across different task categories, and providing regression detection for agent behavior changes. The framework will help maintain quality as the agent evolves and enable contributors to validate their changes.

#### Expected Outcomes

- Evaluation framework with standardized test harness
    
- Benchmark suite covering 50+ coding scenarios across categories (debugging, refactoring, new features, code review)
    
- Automated scoring and success rate metrics
    
- Regression detection system integrated with CI/CD
    
- Dashboard or report generation for evaluation results
    
- Documentation for adding new evaluation scenarios
    
- Baseline metrics for current Gemini CLI version
    

#### Skills Required/Preferred

- Required: TypeScript/Node.js, testing methodologies, CI/CD pipelines
    
- Preferred: Experience with LLM evaluation, benchmarking, statistical analysis
    

#### Possible Mentors

- [Christian Gunderman](mailto:gundermanc@google.com)
    

### 3 - Windows Developer Experience Improvements

Difficulty: Medium | Size: 175 hours | Area: Platform/Quality

#### Description

Improve the Gemini CLI experience for Windows developers by addressing platform-specific issues, optimizing performance, and ensuring feature parity with macOS/Linux. This includes fixing terminal rendering issues, improving PATH handling, enhancing PowerShell/CMD integration, and creating comprehensive Windows-specific documentation and tooling.

#### Expected Outcomes

- Resolution of top 20 Windows-specific issues from the issue tracker
    
- Native Windows terminal rendering improvements (fixing black backgrounds, theme issues)
    
- PowerShell and CMD integration enhancements
    
- Windows-specific installation and update improvements
    
- Performance optimizations for Windows file system operations
    
- Comprehensive Windows troubleshooting documentation
    
- Automated Windows-specific CI testing pipeline
    

#### Skills Required/Preferred

- Required: TypeScript/Node.js, Windows development, PowerShell
    
- Preferred: Windows terminal APIs, cross-platform development experience
    

#### Possible Mentors

- [Tommaso Sciortino](mailto:sciortino@google.com)
    

### 4 - Multi-IDE Integration Enhancement

Difficulty: Medium | Size: 175 hours | Area: IDE/Tooling

#### Description

Expand Gemini CLI's IDE integration beyond VS Code to provide first-class support for additional popular editors including JetBrains IDEs (IntelliJ, PyCharm, WebStorm), Neovim, and Zed. This includes implementing editor-specific plugins/extensions, establishing a common IDE context protocol, and enabling features like synchronized file state, cursor position awareness, and real-time collaboration between the CLI and editor.

#### Expected Outcomes

- JetBrains IDE plugin with core functionality (open file, apply diff, get context)
    
- Enhanced Neovim integration with bidirectional communication
    
- Zed editor full integration completion
    
- Common IDE Context Protocol specification
    
- Shared context synchronization across CLI and IDE
    
- IDE detection improvements for automatic integration
    
- Documentation and setup guides for each supported IDE
    

#### Skills Required/Preferred

- Required: TypeScript/Node.js, at least one IDE plugin development experience
    
- Preferred: JetBrains plugin SDK, Neovim Lua, Zed extension API
    

#### Possible Mentors

- [Shreya Keshive](mailto:shreyakeshive@google.com)
    

### 5 - Performance Monitoring and Optimization Dashboard

Difficulty: Easy-Medium | Size: 90 hours | Area: Observability/DX

#### Description

Build an in-CLI performance monitoring dashboard that provides developers and contributors with visibility into Gemini CLI's runtime behavior. This includes startup time analysis, memory usage tracking, tool execution profiling, and model latency metrics. The dashboard helps identify performance regressions and optimization opportunities while providing users with session statistics.

#### Expected Outcomes

- In-CLI performance dashboard accessible via /stats or /perf command
    
- Startup time breakdown and optimization suggestions
    
- Memory usage monitoring with warnings for high consumption
    
- Tool execution timing and frequency statistics
    
- Model API latency tracking
    
- Session statistics (tokens used, tools called, files modified)
    
- Performance regression detection for CI integration
    
- Export capability for performance reports
    

#### Skills Required/Preferred

- Required: TypeScript/Node.js, CLI/TUI development, performance profiling
    
- Preferred: React/Ink, data visualization, OpenTelemetry
    

#### Possible Mentors

- [Sehoon Shon](mailto:sshon@google.com)
    

### 6 - Interactive Progress Visualization & Task Stepping

Difficulty: Medium | Size: 175 hours | Area: UX/UI

#### Description

Enhance the Ink-based terminal UI to provide deep visibility into the agent's internal reasoning and tool execution chains. Currently, complex multi-step tasks can be a "black box" for users. This project involves building an interactive visualization that shows the hierarchy of tasks, tool calls, and model decisions in real-time, allowing users to "step through" execution like a debugger.

#### Expected Outcomes

- Real-time task tree visualization in the Ink TUI
    
- "Step-through" mode where users must approve individual tool calls or agent decisions
    
- Rich-text rendering of tool inputs/outputs with collapsible sections
    
- Improved error state visualization for nested agent failures
    
- User-configurable verbosity levels for different task categories
    

#### Skills Required/Preferred

- Required: React (Ink), TypeScript, State Management
    
- Preferred: CLI UX design, experience with asynchronous UI updates, terminal rendering optimizations
    

#### Possible Mentors

- [Jacob Richman](mailto:jacobr@google.com)
    

  

### 7 - Terminal-Integrated Debugging Companion

Difficulty: Hard | Size: 175 hours | Area: Integration/DX

#### Description

Integrate Gemini CLI with standard debugging protocols (DAP) to provide an interactive debugging companion. The agent should be able to attach to running processes (Node.js, Python, Go, etc.), set breakpoints, inspect stack traces, and query variable states to assist in root-cause analysis of bugs directly within the terminal interface.

#### Expected Outcomes

- Debugger toolset for the agent using Debug Adapter Protocol (DAP)
    
- Automated stack trace analysis and variable inspection tools
    
- Interactive "debug mode" where the agent can pause execution and ask for clarification
    
- Seamless integration with existing run_shell_command for launching debug sessions
    
- Automated fix suggestions based on runtime state inspection
    

#### Skills Required/Preferred

- Required: Node.js, Debugger Internals (DAP), TypeScript
    
- Preferred: Experience with VS Code debugger extensions or LLM-driven debugging
    

#### Possible Mentors

- [Keith Schaab](mailto:keithsc@google.com)
    

  

### 8 - Native Windows Sandbox using AppContainer

Difficulty: Hard | Size: 350 hours | Area: Security

#### Description

Implement a native Windows sandboxing driver using Windows AppContainer or the Windows Sandbox API. Currently, Gemini CLI on Windows relies on Docker Desktop for isolation. A native driver would provide a lighter, faster, and dependency-free experience for Windows users, utilizing OS-level security primitives to isolate file system and network access effectively.

#### Expected Outcomes

- Native Windows sandbox driver implementation using AppContainer
    
- Parity with macOS Seatbelt for file system and registry restrictions
    
- Network isolation using Windows Filtering Platform (WFP)
    
- Automated setup and validation tool for Windows security features
    
- Performance benchmarks showing improvement over Docker-based isolation on Windows
    
- Detailed security audit of the native implementation
    

#### Skills Required/Preferred

- Required: C#, Windows APIs (AppContainer, Win32), Node-API/TypeScript
    
- Preferred: Security-focused Windows development, experience with Windows Sandbox
    

#### Possible Mentors

- [Gaurav Ghosh](mailto:gaghosh@google.com)
    

### 9 - Interactive Security Policy and Sandbox Wizard

Difficulty: Easy | Size: 90 hours | Area: Security/UX

#### Description

Build a guided configuration "Wizard" to help users set up and manage security policies for different projects. The wizard will simplify the creation of Seatbelt profiles and Docker mount configurations, providing a user-friendly way to define "safe zones" and network permissions for the agent, ensuring that security is accessible even to non-experts.

#### Expected Outcomes

- Interactive gemini sandbox-setup command with a guided UI
    
- Visual dashboard for reviewing and auditing active sandbox permissions
    
- Per-project policy file management (.gemini/policy.json)
    
- Template-based security profiles for common project types (Web, CLI, API)
    
- Real-time policy validation and conflict detection
    

#### Skills Required/Preferred

- Required: React (Ink), TypeScript, Security concepts
    
- Preferred: CLI design, experience with security configuration or policy-as-code
    

#### Possible Mentors

- [Gal Zahavi](mailto:galzahavi@google.com)
    

### 10 - Unified Sandbox Driver Architecture

Difficulty: Medium | Size: 175 hours | Area: Architecture/Security

#### Description

Refactor the Gemini CLI's sandboxing logic into a modular, plugin-based driver architecture. Currently, sandboxing logic is split across the CLI and Core packages with many platform-specific conditional checks. This project involves creating a clean SandboxDriver interface that abstracts away the platform-specific details, making it easy to support macOS Seatbelt, Linux Bubblewrap, Windows AppContainer, and Docker through a unified API. This architecture will serve as the foundation for all future native sandboxing implementations.

#### Expected Outcomes

- A well-defined SandboxDriver TypeScript interface and lifecycle manager in @google/gemini-cli-core.
    
- Refactored macOS Seatbelt and Docker/Podman implementations using the new interface.
    
- A "No-Op" driver for unsandboxed environments that provides consistent logging and warnings.
    
- Support for dynamic driver discovery and selection based on host OS and user configuration.
    
- Improved error handling and diagnostic tools for sandbox initialization failures.
    
- Comprehensive unit and integration tests for the new driver lifecycle.
    

#### Skills Required/Preferred

- Required: TypeScript, Software Architecture (SOLID principles), Node.js.
    
- Preferred: Experience with plugin systems or abstraction layers, cross-platform development, systems programming concepts.
    

#### Possible Mentors

- [Taylor Mullen](mailto:ntaylormullen@google.com)
    

  

### 11 - Hands-Free Multimodal Voice Mode 

Difficulty: Hard | Size: 350 hours | Area: Innovation/Multimodal

#### Description

Transform Gemini CLI into a hands-free, conversational coding partner—your personal J.A.R.V.I.S. for software development. This project builds a real-time, bidirectional voice interface where developers can speak naturally to the agent and hear responses read aloud. Unlike basic speech-to-text wrappers, this leverages Gemini's native multimodal audio capabilities for a fluid, continuous conversation with minimal latency.

#### Expected Outcomes

- Real-time voice input/output using Gemini's native audio streaming API
    
- Multiple activation modes: Voice Activity Detection (VAD), Push-to-Talk (hotkey), Wake Word ("Hey Gemini")
    
- Visual feedback: animated waveform visualizer showing listening/speaking/processing states
    
- Interruption support: speak to stop the agent's current response
    
- Voice-optimized response formatting (concise, spoken-friendly output)
    
- Audio feedback and text-to-speech for agent responses with configurable voices
    
- Noise cancellation and multi-accent robustness
    

#### Skills Required/Preferred

- Required: TypeScript/Node.js, Audio processing (PCM, WAV, WebAudio), Real-time streaming
    
- Preferred: Gemini Multimodal API, Voice Activity Detection, Whisper integration, accessibility engineering
    

#### Possible Mentors

- [Bryan Morgan](mailto:bryanmorgan@google.com)
    

  

### 12 - Generative Architecture & UI Visualization

Difficulty: Medium | Size: 175 hours | Area: Innovation/UX

#### Description

Break the "text-only" constraint of the terminal by teaching Gemini CLI to draw. This project enables the agent to generate and render visual artifacts directly in the terminal console—architecture diagrams, dependency graphs, data flow visualizations, and even live previews of generated UI components. Using Mermaid.js for diagrams and terminal image protocols (Sixel, iTerm2, Kitty) for rich graphics, developers can finally see what they're building without context-switching to a browser or IDE.

  

Imagine asking "explain the authentication flow" and seeing a beautifully rendered sequence diagram appear inline. Or generating a React component and instantly seeing a pixel-perfect preview in your terminal. This transforms the CLI from a text interface into a visual development environment.

#### Expected Outcomes

- Inline rendering of architecture diagrams (Sequence, Class, ERD, Flowcharts) generated from codebase analysis
    
- Live preview of generated frontend components (HTML/CSS/React) rendered as terminal images
    
- Support for multiple terminal image protocols: Sixel, iTerm2 inline images, Kitty graphics protocol
    
- Intelligent ASCII/ANSI box-drawing fallback for unsupported terminals
    
- New tool: visualize for on-demand diagram generation
    
- Integration with explain command ("Explain this architecture" → renders diagram)
    
- Dependency graph visualization for package.json/requirements.txt
    
- Git history visualization (branch graphs, contributor timelines)
    
- Caching layer for rendered images to avoid regeneration
    

#### Why This Matters

- Paradigm shift: No CLI coding tool renders rich graphics—this is genuinely new
    
- Frontend developer appeal: Instant visual feedback without leaving the terminal
    
- Viral demo potential: Terminal diagrams are visually striking and shareable
    

#### Skills Required/Preferred

- Required: TypeScript/Node.js, Image processing (Sharp, node-canvas), streaming I/O
    
- Preferred: Terminal graphics protocols (Sixel, iTerm2), Mermaid.js/Graphviz, Puppeteer for HTML rendering
    

#### Possible Mentors

- [Dmitry Lyalin](mailto:dmitrylyalin@google.com)

# 5. Cern HSF

All: https://hepsoftwarefoundation.org/gsoc/2026/summary.html
# AI Assistance for CMS Computing Operations

# Description

Archi (AI Augmented Research Chat Intelligence) is an open-source, end-to-end framework for building AI agents to automate research and operational workflows. Various groups have already applied the system to their use case; the most advanced is the Computing Operations (CompOps) team at the Compact Muon Solenoid (CMS) experiment at CERN. CompOps has a private, constantly evolving, and scattered knowledge base, with scarce personnel on short term contracts. Archi puts together state-of-the-art, open-source tools like LangChain, knowledge graphs, and Model Context Protocol, and combines documentation, code, tickets, and live diagnostics to accurately retrieve relevant information, assisting operators in daily tasks, improving operator efficiency, and lessening the load on experts. Other groups at CMS deploying Archi for their use case include the Data Quality Monitoring (DQM) team and a group focusing on retrieval of the vast analysis code and documentation across the CMS landscape.

The goal of this GSoC project is to work on the development of autonomous agents to perform non-trivial computing operations at CMS, a task which integrates large language models with highly accurate retrieval, expert domain knowledge, heteregenous data sources, and agentic tools. The student will get familiarity with state-of-the-art and in-demand agentic tools like LangChain and MCP. like LangChain and MCP.

## Task idea

- Identify operational tasks apt for automation
- Work with experts to define the specs of the agent: input data sources, actions it can take, desired outcomes
- Define sandbox limitations of the agent, policy of human review for first phase of roll-out
- Define metrics of success and data collection
- Implement, with the Archi framework, a prototype of the agent
- Deploy agent to operations and demonstrate impact

## Expected results and milestones

- Design report on the specs of agent
- Develop a prototype of the agent
- Integrate the agent into operations with domain experts
- Evaluate the performance of the agent based on desired outcomes

## Requirements

- Python programming skills
- Familiarity with Git and GitHub-based collaboration (PRs, code review)
- Basic Linux command-line skills
- Ability to communicate clearly and work iteratively with domain experts
- Familiarity with LLM tooling and agent frameworks (e.g., LangChain) and/or Model Context Protocol (MCP)
- Exposure to retrieval systems (vector search/embeddings), knowledge bases, or knowledge graphs
- Experience with containers (Docker/Podman) and/or CI testing
- Interest in (or prior exposure to) HEP/CMS computing operations workflows

## AI Policy

AI assistance is allowed for this contribution. The applicant takes full responsibility for all code and results, disclosing AI use for non-routine tasks (algorithm design, architecture, complex problem-solving). Routine tasks (grammar, formatting, style) do not require disclosure.

## How to apply

Once CERN/HSF is accepted as a GSoC org, please write an email with a short introduction to your interests and background to the mentors with the string “gsoc26” in the subject. There will be a small evaluation task that we will mail to you then.

## Links

- [https://github.com/archi-physics/archi](https://github.com/archi-physics/archi)

## Mentors

- **[Hasan Ozturk](mailto:h.ozturk@cern.ch)** - CERN
- [Pietro Lugato](mailto:pmlugato@mit.edu) - MIT
- [Luca Lavezzo](mailto:lavezzo@mit.edu) - MIT

## Additional Information

- Difficulty level (low / medium / high): medium
- Duration: 350 hours
- Mentor availability: June-October

## Corresponding Project

- [CMS](https://hepsoftwarefoundation.org/gsoc/projects/2026/project_CMS.html)

## Participating Organizations

- [CERN](https://hepsoftwarefoundation.org/gsoc/organizations/2026/cern.html)
- [MIT](https://hepsoftwarefoundation.org/gsoc/organizations/2026/mit.html)

# Honorable Mentions

https://github.com/STEllAR-GROUP/hpx/wiki/Google-Summer-of-Code-(GSoC)-2026#2026-hpx-project-ideas