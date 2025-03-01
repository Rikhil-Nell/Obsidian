---
tags:
  - "#MachineLearning"
  - "#Transformers"
---
To understand what attention is trying to achieve, let us take an example of the 3 following sentences:

**Physical Activity**: "I decided to go for a run in the park to clear my mind."
**Manage/Operate**: "She runs a successful business in the tech industry.
**Computer Program**: "Before deploying the software, I need to run a few tests to check for bugs."

We as humans understand the word **run** with context, so it is easy for us to distinguish that the word **run** means different things in each of the above sentences.

However, for a Transformer model, it only has the **Embedding Matrix** to use as a lookup table to parse a token (word) of the input sentence into a vector. Hence, if you were to pass the above three sentences through the model, the **Embedding Vector** for the word **run** would be the same in all cases, as it doesn't hold any contextual data at this point.

It is in the **Attention** step where the surrounding embeddings interact with each other and form the context for the sentence.

To imagine this: consider **run** as a generic embedding vector in some high-dimensional space, and its neighboring embedding vectors are its different meanings. As the model refines itself, it pushes the embedding vector of **run** from its generic position toward one of its specific meanings, depending on context imparted by the communication with surrounding embedding vectors.

>[!info]  Clarification of Attention:
 Attention is sometimes misunderstood as refining the embedding vector of a token directly. However, it’s more accurate to think of it as a mechanism that **moves information between embeddings**, facilitating context formation.

The computation performed to predict the next token is entirely a function of the last vector of the sequence, this means that the **Embedding Vector** of the final token which was initialized only by the **Embedding Matrix** must hold the contextual data of every single word before it which is relevant in the prediction of the next word.

To further expand upon this, let us consider a phrase:

=="a fluffy blue creature roamed the verdant forest"== 

The model is trying to **encode every adjective to its corresponding noun**. The **Embedding** in the first step encodes the raw, non-contextual data of each word and its position relative to others (thanks to positional encoding).

It is helpful to imagine the noun calling out to all the words before it and asking "if any of them is an adjective?", and for this we want the word "fluffy" and "blue" respond, and like with all things in DL we wish to derive a vector for this "query" called **Query Vector**. 

$$
\underbrace{
\begin{bmatrix}
 \text{a}\\ \text{fluffy}\\ \text{blue} \\ \text{creature}\\ \text{roamed}\\ \text{the}\\ \text{verdant}\\ \text{forest}
\end{bmatrix}}_{\text{Words}}
\rightarrow
\underbrace{  
\begin{bmatrix}
 E_{1} \\ E_{2} \\ E_{3} \\ E_{4} \\ E_{5} \\ E_{6} \\ E_{7} \\ E_{8}
\end{bmatrix}}_{\text{Embeddings}}
\cdot
\underbrace{
\begin{bmatrix}
 W_{q}
\end{bmatrix}}_{\text{Query Weight Matrix}}
=
\underbrace{
\begin{bmatrix}
Q_{1} \\ Q_{2} \\ Q_{3} \\ Q_{4} \\ Q_{5} \\ Q_{6} \\ Q_{7} \\ Q_{8}
\end{bmatrix}}_{\text{Queries}}
$$ 
Now similarly for the keys, we once again repeat the process but this time with a different matrix to obtain **Key Vector**

$$
\underbrace{
\begin{bmatrix}
 \text{a}\\ \text{fluffy}\\ \text{blue} \\ \text{creature}\\ \text{roamed}\\ \text{the}\\ \text{verdant}\\ \text{forest}
\end{bmatrix}}_{\text{Words}}
\rightarrow
\underbrace{  
\begin{bmatrix}
 E_{1} \\ E_{2} \\ E_{3} \\ E_{4} \\ E_{5} \\ E_{6} \\ E_{7} \\ E_{8}
\end{bmatrix}}_{\text{Embeddings}}
\cdot
\underbrace{
\begin{bmatrix}
 W_{k}
\end{bmatrix}}_{\text{Key Weight Matrix}}
=
\underbrace{
\begin{bmatrix}
K_{1} \\ K_{2} \\ K_{3} \\ K_{4} \\ K_{5} \\ K_{6} \\ K_{7} \\ K_{8}
\end{bmatrix}}_{\text{Keys}}
$$

**Query Vectors (Q)** and **Key Vectors (K)** are both **128 dimensions** (in GPT-3) and exist in separate vector spaces.

We can visualize that the accuracy of the **key** increases as its vector gets closer to the **query** vector, and hence we find the dot product of the two vectors to calculate **Attention Scores** and then arranged as shown to give us something called **Attention Pattern**.

![[Attention.excalidraw|center]]

We normalize the attention scores along each column using **Softmax** to ensure they sum to 1.

Another way to represent the **Attention Pattern** is:
$$Attention(Q,K,V) = softmax\left(\frac{QK^T}{\sqrt{d_{k}}}\right)V$$
- **Q (Query)**: A vector representing the current token for which we are trying to find relevant information from other tokens in the sequence.
- **K (Key)**: A vector representing each token in the sequence, used to compare against the query and determine how much attention should be given to each token.
- **V (Value)**: A vector holding the actual information or representation of each token, which is weighted by the attention scores.
- **$\sqrt{d_{k} }$​**: The dimensionality of the key vectors, which is used to scale the dot product between $Q$ and $K$. The scaling factor $\sqrt{d_k}$ helps avoid extremely large values in the dot product, which could lead to very small gradients when passed through the **SoftMax** function.​

It turns out that it makes the training process of the model a lot more efficient if you simultaneously have it predict every possible next token following each initial subsequence of the tokens in a passage, so this indirectly hints to us, that since the model trains better if we allow it to guess every token this means we shouldn't let it take a look at the next token given to it in the input string and so to do this, we use a technique called **Masking**.

![[Drawing Attention2.excalidraw|center]]

To achieve what is shown above, we set all the entries in the shaded region to $- \infty$ , this way when we put the columns through **Soft Max** those entries turn to 0 and our **Attention pattern** remains normalized.

>[!info] Context Size 
>The size of the **attention pattern** is the $(\text{Context Size})^2$, this is why the context size can be a huge bottle neck for LLMs

Computing the **Attention Pattern** helps the model deduce which words are relevant to which other words.

Now to actually change the **Embedding** of the word we use the **Value Matrix**:
You take the **Value Matrix** multiply with the embedding vector before the target words, then you get a new vector called **Value Vector**, which is then taken to the attention pattern.

In the attention pattern you multiply the **Value Vector** to each of the corresponding value in the column obtained in the earlier step by the dot product of **Query Vectors** and **Key Vectors**, so for example if in the attention pattern it was decided that the word creature should devote significant portion of its attention to the words "fluffy" and "blue", then the ideally, the attention and the $\triangle E$ should be zero for the vectors of all other tokens other than those two 

You then sum take all these $\triangle E$ values and add them to the corresponding embedding values to update the said embeddings.

This whole process was just a **Single Head of Attention**, and the intuition we used so far was to supplement our understanding of such.

But in actuality the transformer model has multiple heads of attention, and the **embedding vector** and the **value matrix** are both broken down and distributed as such to increase the parallel computation efficiency.

![[Attention_Head_Steps.excalidraw|center|1000]]

>[!note] LoRA (Low-Rank Approximation)
> 
> Imagine the two matrices in LoRA as performing a kind of "zooming in and out" on the data.
> 
> - **The first matrix** takes the original information (which is very large and complex) and compresses it into a more compact, simplified version. This is like reducing the size of a high-resolution image — you're shrinking it down to make it easier and faster to work with. Even though you're compressing the data, you're keeping the most important and relevant parts of it.
> 
> - **The second matrix** then acts like zooming back in, restoring the compressed data to its original size or complexity. It "expands" the compact version, bringing back the richness and details that were temporarily set aside during the compression step.
> 
> By doing this, you get the benefits of working with smaller, more manageable data during the computationally intensive steps, while still preserving the depth and meaning of the original data in the final output.

>[!caution] Clarification
>In the context of the attention mechanism, it’s important to clarify the role of the **Output Matrix** in relation to the **Value Matrix**. Within each attention head, we primarily utilize the **Value (down) Matrix**. This design choice ensures that the proposed change vector remains of a smaller dimension, which enhances computational efficiency.
>
>The smaller proposed change vector is then multiplied by a larger structure known as the **Output Matrix**. This Output Matrix effectively integrates all the **Value (up) Matrices** from the various heads into a single, comprehensive matrix. This approach allows for the seamless combination of contextually rich representations generated by each head, facilitating a more effective adjustment to the embeddings based on the attention scores.

>[!summary] Summary  
>**Distinct Attention Patterns**: The model utilizes 96 distinct Key and Query matrices, each generating a unique attention pattern. These patterns allow the model to focus on different aspects of the input data, capturing a variety of relationships within the sequence.
>
>**Value Matrices and Value Vectors**: Correspondingly, there are 96 distinct LoRA (Low-Rank Adaptation) Value matrices. Each of these matrices produces 96 sequences of Value vectors that represent the contextual information relevant to the input.
>
**Integration into Attention Mechanism**: These Value vectors are then integrated into the attention formula, which computes how much focus each part of the input should receive based on the derived attention scores.
>
**Proposed Changes in Embeddings**: As a result, the model outputs 96 different proposed changes to the embeddings for each input embedding. This allows the model to generate contextually rich representations that enhance its understanding of the data.
>
**Final Update to Embeddings**: Finally, the model sums all the proposed changes from the different attention patterns and adds this cumulative adjustment to the original embeddings. This process ensures that the embeddings reflect the integrated contextual information, improving their representational power for downstream tasks.
>
Whatever was covered above was just one **single head of attention**. In GPT-3, there are 96 attention blocks, and each block contains 96 such **single heads of attention**, collectively referred to as **Multi-Headed Attention**.
