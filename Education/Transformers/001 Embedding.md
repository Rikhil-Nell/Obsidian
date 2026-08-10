---
tags:
  - "#MachineLearning"
  - "#Transformers"
---
For simplicity lets assume each sentence is broken into tokens, and each token is a word.
LLM's have a predefined vocabulary of around 50,000 words (GPT-3).

The first matrix we come across is **Embedding Matrix**. It has a column vector for each of the 50,000 words 

These columns determine which vector each word from the input turns into.

>[!example] 
>![[Embedding 1|center]]

**Embedding matrix** is represented by $W_E$ , and its vales are learnt just like any other weight matrix. 
These embeddings are often high dimensional (12,288 in GPT-3).

>[!info]
> Each dimension in a high-dimensional embedding can capture some **specific aspect of meaning** or **context**. For instance, one dimension might relate to a word’s **gender** association, another to its **tense**, and another to its **proximity to certain other words**. The more dimensions, the more nuances the vector can encode. 

>[!intuition] 
>You can compare this to how **humans learn new meanings** of words. Initially, a word might be tied to one meaning (like "chest" as a body part), but as we encounter the word in new contexts (like "storage box"), we **add new dimensions of meaning** and refine our understanding, much like how embeddings are trained with vast amounts of data to capture every nuance of a word's usage

As the weights get trained and they start to settle, it tends to settle on a set of embeddings have a kind of **semantic** meaning.

To visualize this, 
The difference in magnitude of embedded vector for the word man and for the word woman is quite close to the difference in the magnitude of embedded vector for the word uncle and the word aunt.
$$E(Aunt) - E(Uncle) \approx E(Woman) - E(Man)$$
This shows that the model found it to be advantageous to encode gender relations in this space helping us comprehend the decision making behind the tweaking of weights

Similarly if you take: $$E(Hitler) + E(Italy) - E(Germany)$$
We get: $$E(Hitler) + E(Italy) - E(Germany) \approx E(Mussolini)$$
This is a good example of how that **contextual metadata** need not be a social concept at all, here the model decided that one of the dimensions in the space would have a measure of the 
*Italian-ness* and the other being the *WW2 Leaders*.

>[!intution]
>**Dot Product** of two vectors can be thought of as a way to measure how well they align
>**Computationally:** Multiply all the corresponding elements and add them up together.
>**Geometrically:** $$A\cdot B = \left\{ \begin{array}{ll} if < 0, \text{vectors point in same direction}\\ if= 0, \text{vectors are perpendicular} \\ if >0,\text{vectors point in opposite direction}\end{array} \right.$$

Another novel conclusion you can draw is to find the difference between the vectors of embedded words and figure out what that dimension means.

Taking the difference between $E(cats)$ and $E(cat)$, we get:
$$\vec{plural} = E(cats)-E(cat)$$

Now we can find the dot product of the $\vec{plural}$ and other words to test this hypothesis, we get the following:
$$\begin{align} \vec{plural} \cdot E(\text{puppies}) &= 1.85\\ \vec{plural} \cdot E(\text{puppy}) &= -4.04\end{align}$$
This shows how the adjusted weights had found certain words move towards and away from the $\vec{plural}$ vector.

A few other things that the **Embedding Matrix** keeps track of are:
- **Semantic meaning** (like whether "chest" refers to a body part or a container).
- **Contextual relationships** (like how "chest" is used in relation to other words).
- **Word properties** (like whether it tends to be a noun, and what it’s usually paired with).

Embedding is more than just what it means and its relation to the words around it, it is the capacity to soak in context.

The network can only operate on a fixed number of vectors at a time, this parameter is called **context size** (2,048 in GPT-3), to put it simply it means how much context it can incorporate when its making the prediction for the next word. This is also why the older chat model's used to "forget" the context of the conversation.

At the final step, to predict the next word we take the last vector from the column of vectors and multiply it with another weights matrix called **Unembedding Matrix $W_U$**, which then returns a vector of equal number of values as the words in the vocabulary and also mapped individually to each word, then this matrix is put through **SoftMax** a function to normalize these values into probability distribution and the word with the highest probability is assigned as the next word.

![[Embedding 2|1000]]

>[!info] SoftMax
>To expect a sequence of numbers to act as a probability distribution, it should do the following: 
>- Each value must be between 0 and 1.
>- All the values should add up to 1.
>
>However, since calculations in Deep Learning are primarily matrix operations, it's difficult for a vector of numbers to naturally satisfy these criteria.
>
>**SoftMax** solves this problem by converting any arbitrary list of numbers into a probability distribution:
>
>$$
\underbrace{
\begin{bmatrix}
x_1 \\ x_2 \\ x_3 \\ x_4 \\ x_5 \\ x_6 \\ x_7 \\
\end{bmatrix}
}_{\text{Logits}}
\quad \rightarrow \quad
\underbrace{
\begin{bmatrix}
\frac{e^{x_1}}{\sum_{n=1}^{7} e^{x_n}} \\
\frac{e^{x_2}}{\sum_{n=1}^{7} e^{x_n}} \\
\frac{e^{x_3}}{\sum_{n=1}^{7} e^{x_n}} \\
\frac{e^{x_4}}{\sum_{n=1}^{7} e^{x_n}} \\
\frac{e^{x_5}}{\sum_{n=1}^{7} e^{x_n}} \\
\frac{e^{x_6}}{\sum_{n=1}^{7} e^{x_n}} \\
\frac{e^{x_7}}{\sum_{n=1}^{7} e^{x_n}} \\
\end{bmatrix}
}_{\text{SoftMax}}
\quad \xrightarrow{} \quad
\underbrace{
\begin{bmatrix}
0.01 \\ 0.00 \\ 0.03 \\ 0.09 \\ 0.61 \\ 0.00 \\ 0.25 \\
\end{bmatrix}
}_{\text{Probabilities}}
>$$

>[!info] SoftMax with Temperature
>In certain cases, we want to control the "sharpness" of the probability distribution output by SoftMax. This is achieved by introducing a temperature parameter **T**.
>
>The **SoftMax with Temperature** formula is given by:
>
>$$\text{Softmax with temperature (T)}:\quad
>\frac{e^{x_i/T}}{\sum_{n=1}^{N} e^{x_n/T}}$$
>
>Where:
>- **T > 1**: The distribution becomes **smoother** (lower confidence in predictions, less peaky).
>- **T < 1**: The distribution becomes **sharper** (higher confidence, more peaky).
>
>This is particularly useful in **Transformer models**, especially in **attention mechanisms**, where temperature scaling helps adjust the focus of attention weights. Lower temperatures focus more on certain inputs, while higher temperatures spread attention more evenly. For example, **SoftMax with temperature** helps balance the attention when dealing with highly variable input values, providing more control over how much focus the model places on each element in a sequence.



