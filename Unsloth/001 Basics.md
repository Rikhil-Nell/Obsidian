---
tags:
  - LLM
  - Finetuning
  - Unsloth
---
# 1. Understanding Finetuning

Finetuning an LLM customizes its behavior, enhances + injects knowledge and optimizes performance for domains/specific tasks.

>[!example] 
>**GPT-4** serves as a base model; however, OpenAI fine-tuned it to better comprehend instructions and prompts, leading to the creation of ChatGPT-4 which everyone uses today.

By fine-tuning a pre-trained model (e.g. Llama-3.1-8B) on a specialized dataset, you can:

- **Update + Learn New Knowledge**: Inject and learn new domain-specific information.
- **Customize Behavior**: Adjust the model’s tone, personality, or response style.
- **Optimize for Tasks**: Improve accuracy and relevance for specific use cases.

**Example usecases**:

- Train LLM to predict if a headline impacts a company positively or negatively.
- Use historical customer interactions for more accurate and custom responses.
- Fine-tune LLM on legal texts for contract analysis, case law research, and compliance.

# 2. Choose the Right Model + Method

As a beginner it is best to start with a small instruct model like Llama 3.1 (8B) and experiment from there. You'll also need to decide between QLoRA and LoRA training:

- **LoRA:** Fine-tunes small, trainable matrices in 16-bit without updating all model weights.
- **QLoRA:** Combines LoRA with 4-bit quantization to handle very large models with minimal resources.

It is recommended to start with **Instruct models**, as they allow direct fine-tuning using conversational chat templates (ChatML, ShareGPT etc.) and require less data compared to **Base models** (which uses Alpaca, Vicuna etc). Learn more about the differences between instruct and base models here: [[What Model to Use]]?
