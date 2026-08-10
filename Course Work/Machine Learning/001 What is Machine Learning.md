---
tags:
  - "#MachineLearning"
---
`A computer program is said to learn from experience E with respect to`
`some task T and some performance measure P, if its performance on T, as`
`measured by P, improves with experience E. - Tom Mitchell`

## **Types of Machine Learning Systems**

Machine Learning models can be broadly segregated into groups in the following manner:

#### **1. Based on Supervision**

- **Supervised Learning:**  
    Trained on labeled data (e.g., spam detection).
- **Unsupervised Learning:**  
    Trained on unlabeled data (e.g., clustering, dimensionality reduction).
- **Semi-Supervised Learning:**  
    Combines a small amount of labeled data with a large amount of unlabeled data.
- **Self-Supervised Learning:**  
    Uses the data itself to generate pseudo-labels (e.g., contrastive learning).
- **Reinforcement Learning:**  
    Learns by interacting with the environment to maximize a reward (e.g., game-playing AI).
#### **2. Based on Mode of Learning**

- **Online Learning:**  
    The model learns incrementally as new data arrives, ideal for streaming data.  
    _Example:_ Stock price prediction.
    
- **Batch Learning:**  
    The model is trained on the entire dataset at once, typically offline.  
    _Example:_ Image recognition models trained on large datasets.
    
#### **3. Based on Prediction Strategy**

- **Instance-Based Learning:**  
    Memorizes the training data and uses it to make predictions by comparing new instances.  
    _Example:_ k-Nearest Neighbors (k-NN).
    
- **Model-Based Learning:**  
    Generalizes from the training data by building a predictive model.  
    _Example:_ Linear Regression, Decision Trees.

---
## **Training Supervision:**

### **Supervised Learning:**

In supervised learning, the training set you feed to the algorithm includes the desired solutions, called labels

A typical supervised learning task is **classification**. The spam filter is a good example of this: it is trained with many example emails along with their class (spam or ham), and it must learn how to classify new emails. 

Another typical task is to predict a target numeric value, such as the price of a car, given a set of features (mileage, age, brand, etc.). This sort of task is called **regression**. To train the system, you need to give it many examples of cars, including both their features and their targets (i.e., their prices). 

>[!info]
> Note that some regression models can be used for classification as well, and vice versa. For example, **logistic regression** is commonly used for classification, as it can output a value that corresponds to the probability of belonging to a given class (e.g., 20% chance of being spam)

### **Unsupervised Learning:

In **unsupervised learning**, the training set you feed to the algorithm does not include labels or desired solutions. The system tries to learn patterns, structure, or relationships in the input data without explicit guidance.

A typical unsupervised learning task is **clustering**, where the goal is to group the data into clusters based on their similarities. For example, a customer segmentation system might analyze customer data (such as purchase history, browsing habits, and demographic information) and identify distinct customer groups, such as “frequent buyers” or “occasional browsers.”

Another common task is **dimensionality reduction**, which aims to simplify the data by reducing the number of features while preserving its most important characteristics. This can be useful for visualizing high-dimensional data or preprocessing data for supervised learning. For instance, **Principal Component Analysis (PCA)** can be used to project customer data into two or three dimensions for easier visualization.

> [!info]  
> Note that some algorithms can be adapted for both clustering and dimensionality reduction. For example, **k-means clustering** is a popular method for grouping data, but it can also help identify representative points in a dataset, which can serve as a form of feature reduction. Similarly, **autoencoders**, a type of neural network, can perform dimensionality reduction while also learning to reconstruct the input data.

---
### **Semi-Supervised Learning:**

**Semi-supervised learning** lies between supervised and unsupervised learning. It leverages a small amount of labeled data combined with a large amount of unlabeled data to improve learning performance. This approach is especially useful when labeling data is expensive or time-consuming, but obtaining unlabeled data is relatively easy.

The algorithm first uses the labeled data to create a basic understanding of the task. It then applies this understanding to label or group the unlabeled data, iteratively improving its performance as more patterns emerge.

#### **Example**:

Consider a photo album with thousands of images. Labeling every image (e.g., “dog,” “cat,” “car”) might be impractical. Instead, a small subset of these images is labeled manually, and the remaining images are unlabeled. Using semi-supervised learning, a model could identify patterns from the labeled images and extend its predictions to classify the unlabeled ones. This approach is often used in **image recognition** or **text categorization**, where data labeling is labor-intensive.

> [!tldr] **Real-World Use Case:**  
> Google Photos uses semi-supervised learning to identify and cluster similar faces across a photo collection with minimal labeled input (e.g., a user tagging a single face). It generalizes these labels to other images automatically.

---

### **Self-Supervised Learning**

**Self-supervised learning** is a form of unsupervised learning where the system generates pseudo-labels from the data itself. These pseudo-labels are used as the supervision signal, allowing the model to learn without requiring human-labeled data. This approach is particularly effective for tasks where labeled data is scarce or expensive to obtain.

#### **Example**:

A classic example is learning representations of images. An image might be altered (e.g., rotated, cropped, or parts of it masked), and the model is trained to predict these alterations. By solving these pretext tasks, the model learns meaningful representations of the data, which can then be fine-tuned for downstream tasks like image classification or object detection.

> [!tldr] **Real-World Use Case:**  
> Models like **BERT** (Bidirectional Encoder Representations from Transformers) use self-supervised learning to predict missing words in a sentence. This technique enables the model to understand language structure and semantics without requiring labeled datasets.

---

### **Reinforcement Learning**

**Reinforcement learning (RL)** involves training an agent to make decisions by interacting with an environment. The agent learns to maximize a reward signal by taking actions that lead to desired outcomes. Unlike supervised learning, RL doesn’t require labeled input-output pairs; instead, it learns through trial and error.

#### **Example**:

A robot learning to walk is a classic example of RL. The robot (agent) starts with no knowledge of how to move. It interacts with its environment (the ground and its own sensors), trying different movements. If it makes progress (e.g., moves forward without falling), it receives a positive reward. Over time, it learns to walk efficiently by maximizing these rewards.

> [!tldr] **Real-World Use Case:**  
> **AlphaGo**, developed by DeepMind, used reinforcement learning to master the game of Go. By playing millions of games against itself, it learned strategies that surpassed even the best human players.

---
## **Batch Versus Online Learning:**

### **Batch Learning**

- **Definition:**  
    The model is trained on the entire dataset at once and is then deployed. No incremental updates are made after deployment.
    
- **Characteristics:**
    
    - Requires significant time and computational resources.
    - Typically done offline (also called _offline learning_).
    - The model remains static after training, leading to potential _model rot_ or _data drift_ as the environment changes.
- **Challenges:**
    
    - Performance decays over time as data evolves.
    - Regular retraining on the full dataset is required to stay updated, which can be computationally expensive.
    - Infeasible for systems needing rapid updates or those with limited resources.
- **Use Cases:**  
    Suitable for systems where data evolves slowly, like classifying images of cats and dogs.
    
- **Drawbacks:**
    
    - Retraining is expensive and resource-intensive.
    - Limited adaptability for rapidly changing environments.

---

### **Online Learning**

- **Definition:**  
    The model is trained incrementally by feeding data sequentially or in mini-batches, enabling it to learn on the fly.
    
- **Characteristics:**
    
    - Training is fast and requires fewer resources.
    - Adapts rapidly to new data (based on _learning rate_).
    - Useful for handling streaming data or very large datasets (_out-of-core learning_).
- **Key Parameters:**
    
    - **Learning Rate:** Determines the speed of adaptation.
        - _High learning rate_: Fast adaptation but may forget old data quickly.
        - _Low learning rate_: Slower adaptation but retains more historical knowledge.
- **Challenges:**
    
    - Bad data can significantly degrade the model’s performance.
    - Requires monitoring for input quality and performance drops.
    - May require anomaly detection algorithms to handle outliers.
- **Use Cases:**  
    Ideal for dynamic systems like stock market predictions or applications on resource-limited devices (e.g., smartphones).
    

---

#### **Comparison of Batch and Online Learning**

|Feature|Batch Learning|Online Learning|
|---|---|---|
|**Adaptability**|Static after deployment|Continuously adapts to new data|
|**Training**|Full dataset at once|Incremental with single instances/mini-batches|
|**Resource Usage**|High (CPU, memory, disk, etc.)|Low (can run on resource-constrained systems)|
|**Response to Change**|Slow (requires retraining)|Rapid (updates as data arrives)|
|**Examples**|Image classifiers, traditional ML tasks|Stock market prediction, streaming data|

---

### **Warnings and Best Practices**

- **Batch Learning:**
    
    - Requires regular retraining to prevent data drift.
    - Automate the training pipeline to reduce downtime and cost.
- **Online Learning:**
    
    - Monitor for bad data that can degrade model performance.
    - Implement anomaly detection and performance monitoring.
    - Be cautious of learning rate to balance adaptation speed and stability.

---

## **Instance-Based and Model-Based Learning**

### **Generalization in Machine Learning**

- The goal of most ML tasks is to generalize well on unseen data.
- Good performance on training data is not sufficient; the focus is on making accurate predictions for new instances.

---

### **Instance-Based Learning**

- **Definition:**  
    The system memorizes training examples and generalizes to new instances by comparing them to stored examples using a similarity measure.
- **How it Works:**
    - Learns by heart.
    - Uses a similarity metric (e.g., number of common words for emails).
    - Generalizes based on the most similar instances (e.g., k-nearest neighbors).
- **Example:**
    - A spam filter flags emails as spam if they are similar to known spam emails.
    - A classification system predicts based on the majority class of the nearest neighbors.

---

### **Model-Based Learning**

- **Definition:**  
    Builds a predictive model based on training data, then uses the model to make predictions.
- **Steps in Model-Based Learning:**
    1. **Study the data:** Explore patterns and trends (e.g., GDP vs. life satisfaction).
    2. **Select a model:** Choose a type of model (e.g., linear regression).
    3. **Train the model:**
        - Define a cost function to minimize errors.
        - Use an algorithm to find the optimal parameters.
    4. **Evaluate and predict:** Use the trained model to make predictions on new data.
- **Example:**
    - Predicting life satisfaction from GDP per capita using a linear regression model.

---

### **Comparison of Instance-Based and Model-Based Learning**

|**Feature**|**Instance-Based Learning**|**Model-Based Learning**|
|---|---|---|
|**Approach**|Memorizes and compares to stored instances|Builds a mathematical model to generalize|
|**Generalization**|Uses similarity measures|Uses the model to predict outcomes|
|**Performance**|Relies on the quality of similarity measures|Relies on the model's structure and parameters|
|**Flexibility**|Adapts well to localized data patterns|Better for capturing global trends|
|**Example Algorithms**|k-Nearest Neighbors|Linear Regression, Decision Trees|

---

### **Key Concepts in Model-Based Learning**

- **Model Selection:**  
    Choosing the type of model and specifying its architecture.
- **Training:**  
    Optimizing model parameters to fit the training data.
- **Inference:**  
    Using the trained model to make predictions on new data.
- **Performance Measure:**
    - _Utility Function:_ Measures how good the model is.
    - _Cost Function:_ Measures how bad the model is (e.g., mean squared error in linear regression).

---

### **Summary**

- **Instance-Based Learning:** Simple and direct, relies heavily on similarity metrics.
- **Model-Based Learning:** Structured and versatile, allows modeling complex relationships.
- Both methods aim to generalize well on unseen data, but their application depends on the problem context and data characteristics.
---
