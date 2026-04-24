# Glossary

> [!tip] Keep this file open as a reference tab while reading any other study guide file.

This glossary defines every domain-specific term used in this study guide and the F5-TTS codebase. Terms are ordered alphabetically. Each entry includes: a plain-English definition, why it matters for F5-TTS, and where it appears in the code.

---

### Accelerate (HuggingFace Accelerate)
A library that wraps PyTorch training for multi-GPU, mixed-precision, and distributed training with minimal code changes. Instead of writing raw `torch.distributed` boilerplate, you call `accelerator.prepare()` and it handles everything.
- **Where:** `trainer.py:L57-68` — the `Accelerator` is created here
- **Why it matters:** F5-TTS uses Accelerate for all training. You need `accelerate config` set up before training.

### AdaLN (Adaptive Layer Normalization)
A layer normalization variant where the scale and shift parameters are **dynamically generated** from a conditioning input (in this case, the timestep embedding). This is how the transformer knows "how noisy is the input right now?"
- **Where:** `modules.py:L312-326`
- **Why it matters:** AdaLN is the mechanism that makes DiT a *diffusion* transformer. Without it, the model can't condition on $t$.
- **See:** [[05-model-architecture#4. Transformer Blocks — The main computation]]

### Attention (Multi-Head Self-Attention)
The core operation in transformers. Each position in a sequence computes a weighted sum of all other positions, where the weights are determined by learned query-key dot products. "Multi-head" means this is done in parallel with different learned projections.
$$\text{Attention}(Q, K, V) = \text{softmax}\left(\frac{QK^T}{\sqrt{d_k}}\right)V$$
- **Where:** `modules.py:L371-556`
- **Why it matters:** This is how the model relates text to audio and learns alignment without explicit duration prediction.

### Autoregressive (AR)
A generation strategy where outputs are produced one step at a time, left-to-right, with each step conditioned on all previous steps. GPT and VALL-E use this approach.
- **Why it matters:** F5-TTS is **non-autoregressive** — it generates the entire output simultaneously, which is fundamentally faster.
- **See:** [[01-what-is-this-repo#Why Flow Matching Instead of Autoregressive or Diffusion?]]

### Batch Size (frame-based)
In F5-TTS, batch size is measured in **total mel spectrogram frames** across all samples in the batch (not number of samples). Default: 38,400 frames. This ensures consistent GPU memory usage despite variable-length audio.
- **Where:** `configs/F5TTS_v1_Base.yaml:L7` — `batch_size_per_gpu: 38400`
- **See:** [[06-data-pipeline#Dynamic Batch Sampling]]

### BigVGAN
A GAN-based neural vocoder (mel spectrogram → waveform) from NVIDIA. Slightly higher quality than Vocos but slower. Requires FP32 computation.
- **Where:** `infer/utils_infer.py:L130-144`
- **Why it matters:** Alternative vocoder backend. Loaded from `third_party/BigVGAN/`.

### CFG (Classifier-Free Guidance)
An inference technique that amplifies the effect of conditioning by computing both a conditioned and unconditioned prediction, then extrapolating:
$$v_\text{guided} = v_\text{uncond} + (1 + w)(v_\text{cond} - v_\text{uncond})$$
- **Where:** `cfm.py:L180-191`
- **Default strength:** 2.0 (`utils_infer.py:L62`)
- **See:** [[04-flow-matching-intuition#Classifier-Free Guidance (CFG)]]

### CFM (Conditional Flow Matching)
The generative modeling framework used by F5-TTS. Learns a velocity field that transports noise to data along straight-line paths. The model class in code is literally called `CFM`.
- **Where:** `cfm.py:L22` — the `CFM` class
- **See:** [[04-flow-matching-intuition]]

### Checkpoint
A saved snapshot of model weights, optimizer state, EMA weights, and scheduler state. Used for resuming training and for inference.
- **Where:** `trainer.py:L150-183` (saving), `trainer.py:L185-263` (loading)
- **Formats:** `.pt` (PyTorch pickle), `.safetensors` (safe serialization)

### Codebook (in RVQ)
A lookup table of prototype vectors used in Vector Quantization. Each input vector is mapped to its nearest codebook entry, producing a discrete token. F5-TTS does NOT use codebooks — this is relevant context for understanding alternative TTS approaches.
- **See:** [[03-audio-and-codec-primitives#What Is RVQ (Residual Vector Quantization)?]]

### ConvNeXt V2
A modern CNN block design from Facebook Research. In F5-TTS, 4 ConvNeXt V2 blocks refine the text character embeddings, giving them local context (each character can "see" its 6 neighbors through a kernel of size 7).
- **Where:** `modules.py:L252-280`
- **Why it matters:** This is the key architectural innovation of F5-TTS over E2-TTS. It dramatically speeds up training convergence.
- **See:** [[05-model-architecture#2. TextEmbedding — ConvNeXt V2 text encoder]]

### ConvPositionEmbedding
A convolutional positional encoding that replaces standard sinusoidal position embeddings after the input fusion stage. Uses a depth-wise 1D convolution to inject position information.
- **Where:** `modules.py:L175-228`

### Cross-Fade
An audio stitching technique used when generating long text in chunks. The overlapping tails of adjacent generated chunks are blended using linear fade-out/fade-in to avoid audible discontinuities.
- **Where:** `infer/utils_infer.py:L549-585`
- **Default duration:** 0.15 seconds (3,600 samples at 24kHz)

### DAC (Descript Audio Codec)
A neural audio codec (similar to EnCodec) that compresses audio into discrete tokens. Not used by F5-TTS, but relevant context.
- **See:** [[03-audio-and-codec-primitives]]

### DDPM (Denoising Diffusion Probabilistic Model)
A generative modeling framework that adds noise gradually over many steps and learns to reverse the process. Flow matching (used by F5-TTS) is a simpler and faster alternative.
- **See:** [[04-flow-matching-intuition#How Flow Matching Differs from Diffusion]]

### DiT (Diffusion Transformer)
A transformer architecture adapted for diffusion/flow matching by adding timestep conditioning via AdaLN at every layer. F5-TTS's primary backbone.
- **Where:** `backbones/dit.py:L170-371`
- **See:** [[05-model-architecture]]

### DiTBlock
A single transformer block within the DiT. Contains: AdaLN → Multi-Head Attention → Gated Residual → FFN → Gated Residual.
- **Where:** `modules.py:L711-757`

### DynamicBatchSampler
A custom PyTorch `Sampler` that groups training samples by audio length to minimize padding waste. Batches are formed by greedily packing samples until a frame threshold is reached.
- **Where:** `dataset.py:L170-241`
- **See:** [[06-data-pipeline#Dynamic Batch Sampling]]

### EMA (Exponential Moving Average)
A technique that maintains a smoothed copy of model weights: $\theta_\text{EMA}^{(t)} = \beta \theta_\text{EMA}^{(t-1)} + (1-\beta)\theta_\text{train}^{(t)}$. The EMA weights are more stable and generalize better than raw training weights.
- **Where:** `trainer.py:L107-108` (init), `trainer.py:L387-388` (update)
- **Key detail:** Inference uses EMA weights by default. Early fine-tuning checkpoints may benefit from `use_ema=False`.
- **See:** [[07-training-loop#The EMA Model]]

### EnCodec
Meta's neural audio codec that compresses audio into discrete tokens using RVQ. Used by models like VALL-E but not by F5-TTS.
- **See:** [[03-audio-and-codec-primitives#What Are Neural Audio Codecs?]]

### EPSS (Empirically Pruned Step Sampling)
Hand-tuned non-uniform timestep positions for the ODE solver. These schedules concentrate more steps in the noisy regime ($t \approx 0$) where the velocity field changes most rapidly.
- **Where:** `utils.py:L205-218`
- **See:** [[04-flow-matching-intuition#EPSS: Empirically Pruned Step Sampling]]

### FFN (FeedForward Network)
The MLP block in each transformer layer. In F5-TTS: Linear(1024→2048) → GELU → Linear(2048→1024).
- **Where:** `modules.py:L353-369`

### Flash Attention
An optimized attention implementation that reduces memory usage from $O(n^2)$ to $O(n)$ by tiling and never materializing the full attention matrix. F5-TTS supports it via `attn_backend: flash_attn` in config.
- **Where:** `modules.py:L451-556` — `AttnProcessor` selects between torch SDPA and Flash Attention

### Flow Matching
See **CFM**.

### Forced Alignment
A classical TTS preprocessing step that aligns text phonemes to audio frames. F5-TTS eliminates the need for this entirely through its filler-token padding approach.
- **See:** [[01-what-is-this-repo#How Classical TTS Pipelines Work]]

### G2P (Grapheme-to-Phoneme)
Converting written text characters (graphemes) to pronunciation symbols (phonemes). F5-TTS does NOT use G2P — it works directly on characters or bytes.

### GRN (Global Response Normalization)
A normalization technique from the ConvNeXt V2 paper. Applied inside ConvNeXt blocks after the GELU activation.
- **Where:** `modules.py:L236-250`

### Hop Length
The number of raw audio samples between adjacent mel spectrogram frames. With `hop_length=256` at 24kHz, each frame represents 10.67ms. A 10s clip ≈ 938 frames.
- **Where:** `utils_infer.py:L54` — `hop_length = 256`
- **See:** [[03-audio-and-codec-primitives#Step 1: Short-Time Fourier Transform (STFT)]]

### Hydra
A configuration framework from Facebook. The `train.py` script uses Hydra to load YAML configs:
```python
@hydra.main(config_path="configs", config_name="F5TTS_v1_Base")
def main(model_cfg): ...
```
- **Where:** `train/train.py:L17`

### Infilling
Generating content that fills a gap within existing context. In F5-TTS, the model generates the missing audio between (or after) reference audio segments. Training uses random span masking to teach this ability.
- **See:** [[11-non-obvious-decisions#2. Random Span Masking During Training]]

### Mel Filterbank
A matrix of triangular filters that maps linear-frequency spectral bins to mel-scale frequency bins, matching human pitch perception: $M(f) = 2595 \cdot \log_{10}(1 + f/700)$.
- **See:** [[03-audio-and-codec-primitives#Step 2: Mel Filterbank]]

### Mel Spectrogram
A 2D time-frequency representation of audio where the frequency axis is scaled to match human hearing (mel scale). The primary data format in F5-TTS: all model inputs and outputs are mel spectrograms.
- **Shape:** `[100, T]` where 100 = mel channels, T = time frames
- **Where:** `modules.py:L112-151` — the `MelSpec` class
- **See:** [[03-audio-and-codec-primitives#What Is a Mel Spectrogram]]

### MMDiT (Multi-Modal DiT)
An architecture variant (from Stable Diffusion 3) where text and audio are processed as separate embedding streams with joint attention. Available in F5-TTS but not used in pretrained models.
- **Where:** `backbones/mmdit.py`

### MOS (Mean Opinion Score)
A subjective quality metric where human listeners rate audio naturalness from 1 (bad) to 5 (excellent). UTMOS is an automated predictor of MOS.
- **See:** [[10-production-considerations#Automated metrics]]

### NFE (Number of Function Evaluations)
The total number of neural network forward passes during inference. With 32 Euler steps and CFG, NFE = 64 (32 steps × 2 for conditioned + unconditioned).
- **Where:** `utils_infer.py:L61` — `nfe_step = 32`
- **See:** [[08-inference-pipeline#ODE Solver Choices and NFE]]

### ODE (Ordinary Differential Equation)
The mathematical framework used for inference in flow matching. Starting from noise $\mathbf{x}(0)$, we solve $d\mathbf{x}/dt = v_\theta(\mathbf{x}, t, c)$ to get clean audio at $\mathbf{x}(1)$.
- **Where:** `cfm.py:L218` — `torchdiffeq.odeint(fn, y0, t, ...)`
- **See:** [[04-flow-matching-intuition#The ODE That the Model Learns to Solve]]

### Pinyin
The romanization system for Chinese characters. "你好" → "ni3 hao3". F5-TTS uses pinyin tokenization for Chinese to provide explicit pronunciation information.
- **Where:** `utils.py:L148-189` — `convert_char_to_pinyin()`
- **Vocabulary size:** ~2543 tokens (with polyphoneme support)

### RMS Normalization
Normalization by root mean square (without centering): $y = x / \text{RMS}(x) \cdot \gamma$. Simpler and faster than Layer Norm. Used throughout the DiT backbone.
- **Where:** `modules.py:L286-309`

### RoPE (Rotary Position Embedding)
A position encoding method that encodes position information into the query and key vectors through rotation. Provides relative position awareness that generalizes to sequence lengths longer than seen during training.
- **Where:** `dit.py:L201` — `self.rotary_embed = RotaryEmbedding(dim_head)`
- **Applied in:** `modules.py:L498-509`

### RTF (Real-Time Factor)
`generation_time / audio_duration`. An RTF of 0.15 means generating 10 seconds of audio takes 1.5 seconds. Values < 1.0 mean faster-than-real-time generation.
- **F5-TTS RTF:** ~0.15 on A100 GPU
- **See:** [[08-inference-pipeline#RTF vs Latency]]

### RVQ (Residual Vector Quantization)
A multi-stage quantization technique where each stage quantizes the residual (error) from the previous stage. Used in neural audio codecs (EnCodec, DAC) but NOT in F5-TTS.
- **See:** [[03-audio-and-codec-primitives#What Is RVQ]]

### Sample Rate
Number of audio samples per second. F5-TTS uses 24,000 Hz (24kHz).
- **Where:** `utils_infer.py:L52` — `target_sample_rate = 24000`
- **See:** [[03-audio-and-codec-primitives#What Is Sample Rate and Why 24kHz?]]

### Safetensors
A safe, fast file format for storing model tensors. Unlike `.pt` (pickle-based), safetensors files cannot execute arbitrary code on load.
- **Where:** `utils_infer.py:L202-205` — safetensors loading branch

### SIM (Speaker Similarity)
A metric that measures how similar a generated voice sounds to the reference speaker. Computed using WavLM embeddings + cosine similarity. Higher = more faithful cloning.
- **See:** [[10-production-considerations#Automated metrics]]

### STFT (Short-Time Fourier Transform)
A Fourier transform applied to overlapping windows of audio, producing a time-frequency representation. The first step in computing a mel spectrogram.
- **See:** [[03-audio-and-codec-primitives#Step 1: Short-Time Fourier Transform (STFT)]]

### Sway Sampling
An inference-time timestep scheduling technique that concentrates ODE solver steps near $t=0$ and $t=1$ (where the velocity field changes most). Requires no retraining.
$$t' = t + s \cdot (\cos(\frac{\pi}{2}t) - 1 + t)$$
- **Where:** `cfm.py:L215-216`
- **Default coefficient:** $s = -1.0$
- **See:** [[04-flow-matching-intuition#Sway Sampling: Better Quality Without Retraining]]

### TTS (Text-to-Speech)
The task of generating spoken audio from written text. F5-TTS specifically handles **zero-shot voice cloning TTS**: generating speech in a target speaker's voice from a short reference clip.

### UNetT (Flat U-Net Transformer)
The E2-TTS backbone architecture. A transformer with U-Net-style skip connections: the first half of layers saves activations, the second half receives them as skip connections.
- **Where:** `backbones/unett.py:L108-308`

### UTMOS
An automated MOS (Mean Opinion Score) predictor. A neural network trained to estimate human quality ratings of speech. Scores from 1 to 5.
- **Where:** `eval/eval_utmos.py`

### Velocity Field
In flow matching, the function $v_\theta(\mathbf{x}, t, c)$ that the model learns. It tells the ODE solver "which direction to move" at each point in space and time. The true velocity during training is simply $\mathbf{x}_1 - \mathbf{x}_0$ (data minus noise).
- **Where:** `cfm.py:L280` — `flow = x1 - x0`

### Vocoder
A neural network that converts mel spectrograms back into audio waveforms. The mel-to-waveform conversion is lossy (the mel spectrogram discards phase information), so the vocoder must generate plausible phase information.
- **Options in F5-TTS:** Vocos (default, fast) or BigVGAN (slower, slightly higher quality)
- **Where:** `infer/utils_infer.py:L106-145`
- **See:** [[03-audio-and-codec-primitives#What Is a Vocoder?]]

### Vocos
A Fourier-based neural vocoder from Charactr. The default vocoder in F5-TTS. Fast and compact (~35MB).
- **Where:** `infer/utils_infer.py:L107-129`

### WER (Word Error Rate)
A metric for speech recognition accuracy: `(substitutions + insertions + deletions) / total_words`. In TTS evaluation, it measures intelligibility — lower WER means the TTS output contains fewer mispronounced or missing words.
- **See:** [[10-production-considerations#Automated metrics]]

### Zero-Shot Voice Cloning
Generating speech in a target speaker's voice using only a short reference audio clip, without any speaker-specific training. This is F5-TTS's primary capability.
- **Mechanism:** The reference audio serves as conditioning — the model's attention learns to match the voice characteristics it observes.
- **See:** [[08-inference-pipeline#How Voice Cloning Works Mechanically]]

---

> [!note] Back to start
> Return to the reading order: [[00-index]]
