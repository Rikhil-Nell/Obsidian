# Extracted Course Content

**Source Files**: Materials\exam-portion.txt, Materials\L22 -Max Equlibrium Sum.pptx, Materials\L23-Leaders in  an Array.pptx, Materials\L24- Majority element.pptx, Materials\L27-Quick, Selection Sort.pptx, Materials\L31-Sorted Unique Permutation.pptx, Materials\L32-Maneuvering.pptx, Materials\L33-Combination.pptx, Materials\L34-Josephus trap.pptx, Materials\L35-Maze Solving.pptx, Materials\L36-N Queens.pptx, Materials\Module-02.pdf, Materials\Module-1.pptx, Materials\STS4005 CAT 01 CODES.docx, Materials\STS4005_After_CAT02_Topics_Codes.txt, Materials\STS4005_CAT2_codes.txt

**Total Pages/Slides**: 233

**Extraction Time**: 2026-05-07T01:36:44.687139

---

## Table of Contents

- **Main**
  - Brute Force Algorithm
  - Scan Array from Right
  - Module-02
  - ❑Booth's Algorithm
  - 2.Time Complexity should be O
  - }                                                              Time Complexity – O(n)
  - Time Complexity – O( srqt(n))
  - • Composite Numbers?
  - ❖Now, encircle the rest of the numbers 7, 11, 13, 17 and 19.
  - • Step-4 Take a loop and print wherever array contains true value.
  - }
  - ➢Hence O(n log(log n))
  - Euclid’s Algorithm
  - Greatest Common Divisors-----------------3
  - gcd(12,33)=3
  - 15. }
  - Euler’s Phi Algorithm
  - factor ( GCD or HCF ) is 1 .
  - Therefore phi(5) is  4.
  - Gcd(7,8)=1
  - the below formula:
  - So (7-1)(3-1)=6(2)=12 relatively primes are there for 21.
  - vi. 372
  - return gcd(b % a, a);    } }  }
  - Chinese Remainder Theorem
  - • Check Whether (m1,m2,....,mn) are relatively prime or not.
  - -1) mod M
  - -1) mod M
  - M3 = M/m3 ➔105/7➔15
  - -1 =1
- **X=23**
  - • 23 ≡ 2(mod 3)        23 ≡ 3(mod 5)      23 ≡ 2(mod 7)
  - x ≡ 1(mod 11)
  - x ≡ 3(mod8).​
  - Binary Palindrome
  - 0, 1, 11, 101, 111, 1001, 1111, 10001, 10101, 11011, 11111, 100001, ...
  - }
  - Strobogrammatic Number
  - iii. Different Digit
  - • Strobogrammatic Number list is:-[1,0,8]
  - • Strobogrammatic Number list is:-[1,0,8,6,9]
  - 9  -------------------------->6
  - }
  - Module-01
  - Data Structures
  - Arrays
  - Linked Lists
  - Components of a Linked List
  - Node Creation
  - Insertion in linked list
  - Insertion in the beginning of the LL
  - Insertion at the end of the linkedlist
  - Insertion at some position in a linkedlist
  - Displaying Linked List
  - Loop Detection
  - Form Cycle
  - Doubly Linked List
  - Sort the bitonic DLL
  - Segregate even and odd nodes in a LL
  - Merge Sort Principle
  - Merge Sort for Doubly Linked List
  - Dividing
  - Merging
  - Stack
  - Types of Stack
  - Stack using arrays
  - Stack using LinkedList
  - Stack Implementation using Collections
  - Minimum Stack
  - The Celebrity Problem
  - Code
  - Tower of Hanoi
  - Examples
  - Algorithm
  - Stock Span Problem
  - Example
  - Stock Span Problem Code
  - Stack Permutations
  - Code
  - Queue
  - Queue using Arrays
  - Queue using LinkedList
  - Priority Queue
  - Characteristics of a Priority queue
  - Code

---


# Source: L22 -Max Equlibrium Sum.pptx


## Page 1


[Speaker Notes]
1st slide (Mandatory)

### Figures on this page:

![[L22 -Max Equlibrium Sum_s1_img1.png]]


## Page 2



### Figures on this page:

![[L22 -Max Equlibrium Sum_s2_img1.png]]

![[L22 -Max Equlibrium Sum_s2_img2.jpg]]


## Page 3



### Figures on this page:

![[L22 -Max Equlibrium Sum_s3_img1.png]]

![[L22 -Max Equlibrium Sum_s3_img2.png]]


## Page 4



### Figures on this page:

![[L22 -Max Equlibrium Sum_s4_img1.png]]

![[L22 -Max Equlibrium Sum_s4_img2.png]]


## Page 5



### Figures on this page:

![[L22 -Max Equlibrium Sum_s5_img1.png]]

![[L22 -Max Equlibrium Sum_s5_img2.png]]


## Page 6



### Figures on this page:

![[L22 -Max Equlibrium Sum_s6_img1.png]]

![[L22 -Max Equlibrium Sum_s6_img2.png]]


## Page 7



### Figures on this page:

![[L22 -Max Equlibrium Sum_s7_img1.png]]

![[L22 -Max Equlibrium Sum_s7_img2.png]]


## Page 8



### Figures on this page:

![[L22 -Max Equlibrium Sum_s8_img1.png]]

![[L22 -Max Equlibrium Sum_s8_img2.png]]

![[L22 -Max Equlibrium Sum_s8_img3.png]]


## Page 9



### Figures on this page:

![[L22 -Max Equlibrium Sum_s9_img1.png]]

![[L22 -Max Equlibrium Sum_s9_img2.png]]

![[L22 -Max Equlibrium Sum_s9_img3.png]]


## Page 10



### Figures on this page:

![[L22 -Max Equlibrium Sum_s10_img1.png]]

![[L22 -Max Equlibrium Sum_s10_img2.png]]


## Page 11



### Figures on this page:

![[L22 -Max Equlibrium Sum_s11_img1.png]]

![[L22 -Max Equlibrium Sum_s11_img2.png]]

![[L22 -Max Equlibrium Sum_s11_img3.png]]


## Page 12



### Figures on this page:

![[L22 -Max Equlibrium Sum_s12_img1.png]]

![[L22 -Max Equlibrium Sum_s12_img2.png]]

![[L22 -Max Equlibrium Sum_s12_img3.png]]


## Page 13

//Max Equilibrium Sum
public class MES {
public static int getMaxEquilibriumSumOptimized(int[] arr) {
int totalSum = 0;
int leftSum = 0;
int maxSum = Integer.MIN_VALUE;
for (int i = 0; i < arr.length; i++) {
totalSum += arr[i];
}
for (int i = 0; i < arr.length; i++) {
totalSum -= arr[i];
if (leftSum == totalSum && leftSum > maxSum) {
maxSum = leftSum;
}
leftSum += arr[i];
}
return maxSum;
}
public static void main(String[] args) {
int[] arr = {1, 2, 3, 4, 5, 4, 3, 2, 1};
int maxSum = getMaxEquilibriumSumOptimized(arr);
System.out.println("Max Equilibrium Sum : " + maxSum);
}
}

## Page 14

THANK YOU

### Figures on this page:

![[L22 -Max Equlibrium Sum_s14_img1.png]]

![[L22 -Max Equlibrium Sum_s14_img2.png]]


# Source: L23-Leaders in  an Array.pptx


## Page 1


[Speaker Notes]
1st slide (Mandatory)

### Figures on this page:

![[L23-Leaders in  an Array_s1_img1.png]]


## Page 2



### Figures on this page:

![[L23-Leaders in  an Array_s2_img1.png]]


## Page 3



### Figures on this page:

![[L23-Leaders in  an Array_s3_img1.png]]

![[L23-Leaders in  an Array_s3_img2.png]]


## Page 4



### Figures on this page:

![[L23-Leaders in  an Array_s4_img1.png]]

![[L23-Leaders in  an Array_s4_img2.png]]


## Page 5



### Figures on this page:

![[L23-Leaders in  an Array_s5_img1.png]]

![[L23-Leaders in  an Array_s5_img2.png]]

![[L23-Leaders in  an Array_s5_img3.png]]


## Page 6



### Figures on this page:

![[L23-Leaders in  an Array_s6_img1.png]]

![[L23-Leaders in  an Array_s6_img2.png]]

![[L23-Leaders in  an Array_s6_img3.png]]


## Page 7



### Figures on this page:

![[L23-Leaders in  an Array_s7_img1.png]]

![[L23-Leaders in  an Array_s7_img2.png]]

![[L23-Leaders in  an Array_s7_img3.png]]


## Page 8: Brute Force Algorithm

# Brute Force Algorithm

### Figures on this page:

![[L23-Leaders in  an Array_s8_img1.png]]

![[L23-Leaders in  an Array_s8_img2.png]]

![[L23-Leaders in  an Array_s8_img3.png]]


## Page 9: Scan Array from Right

# Scan Array from Right

### Figures on this page:

![[L23-Leaders in  an Array_s9_img1.png]]

![[L23-Leaders in  an Array_s9_img2.png]]


## Page 10

THANK YOU

### Figures on this page:

![[L23-Leaders in  an Array_s10_img1.png]]

![[L23-Leaders in  an Array_s10_img2.png]]


# Source: L24- Majority element.pptx


## Page 1


[Speaker Notes]
1st slide (Mandatory)

### Figures on this page:

![[L24- Majority element_s1_img1.png]]


## Page 2



### Figures on this page:

![[L24- Majority element_s2_img1.png]]

![[L24- Majority element_s2_img2.png]]


## Page 3



### Figures on this page:

![[L24- Majority element_s3_img1.png]]

![[L24- Majority element_s3_img2.png]]

![[L24- Majority element_s3_img3.png]]


## Page 4



### Figures on this page:

![[L24- Majority element_s4_img1.png]]

![[L24- Majority element_s4_img2.png]]

![[L24- Majority element_s4_img3.png]]


## Page 5



### Figures on this page:

![[L24- Majority element_s5_img1.png]]

![[L24- Majority element_s5_img2.png]]

![[L24- Majority element_s5_img3.png]]


## Page 6



### Figures on this page:

![[L24- Majority element_s6_img1.png]]

![[L24- Majority element_s6_img2.png]]


## Page 7



### Figures on this page:

![[L24- Majority element_s7_img1.png]]

![[L24- Majority element_s7_img2.png]]

![[L24- Majority element_s7_img3.png]]


## Page 8



### Figures on this page:

![[L24- Majority element_s8_img1.png]]

![[L24- Majority element_s8_img2.png]]


## Page 9



### Figures on this page:

![[L24- Majority element_s9_img1.png]]

![[L24- Majority element_s9_img2.png]]


## Page 10



### Figures on this page:

![[L24- Majority element_s10_img1.png]]

![[L24- Majority element_s10_img2.png]]


## Page 11



### Figures on this page:

![[L24- Majority element_s11_img1.png]]

![[L24- Majority element_s11_img2.png]]


## Page 12



### Figures on this page:

![[L24- Majority element_s12_img1.png]]

![[L24- Majority element_s12_img2.png]]

![[L24- Majority element_s12_img3.png]]


## Page 13



### Figures on this page:

![[L24- Majority element_s13_img1.png]]

![[L24- Majority element_s13_img2.png]]


## Page 14



### Figures on this page:

![[L24- Majority element_s14_img1.png]]

![[L24- Majority element_s14_img2.png]]


## Page 15



### Figures on this page:

![[L24- Majority element_s15_img1.png]]

![[L24- Majority element_s15_img2.png]]


## Page 16



### Figures on this page:

![[L24- Majority element_s16_img1.png]]

![[L24- Majority element_s16_img2.png]]


## Page 17



### Figures on this page:

![[L24- Majority element_s17_img1.png]]

![[L24- Majority element_s17_img2.png]]


## Page 18

public class MajorityElement {
2
3	    public static int findMajorityElement(int[] nums) {
4	        int candidate = 0;
5	        int count = 0;
6
7	        for (int num : nums) {
8	            if (count == 0) {
9	                candidate = num;
10	                count = 1;
11	            } else if (candidate == num) {
12	                count++;
13	            } else {
14	                count--;
15	            }
16	        }
17
18	        // Verify the candidate
19	        int majorityCount = 0;
20
21	        for (int num : nums) {
22	            if (num == candidate) {
23	                majorityCount++;
24	            }
25	        }
26
27	        if (majorityCount > nums.length / 2) {
28	            return candidate;
29	        }
30
31	        return -1; // No majority element found
32	    }
33
34	    public static void main(String[] args) {
35	        int[] nums = {3, 2, 3};
36	        int majorityElement = findMajorityElement(nums);
37
38	        if (majorityElement != -1) {
39	            System.out.println("Majority element: " + majorityElement);
40	        } else {
41	            System.out.println("No majority element found.");
42	        }
43	    }
44	}

### Figures on this page:

![[L24- Majority element_s18_img1.png]]


## Page 19

THANK YOU

### Figures on this page:

![[L24- Majority element_s19_img1.png]]

![[L24- Majority element_s19_img2.png]]


# Source: L27-Quick, Selection Sort.pptx


## Page 1


[Speaker Notes]
1st slide (Mandatory)

### Figures on this page:

![[L27-Quick, Selection Sort_s1_img1.png]]


## Page 2

Topic/Course
Quick Sort
QuickSort is a widely used divide-and-conquer sorting algorithm. It works by selecting a pivot element from the list and partitioning the other elements into two sub-arrays, according to whether they are less than or greater than the pivot. The sub-arrays are then recursively sorted, and the process continues until the entire list is sorted.
Here's a step-by-step explanation of how the QuickSort algorithm works:
1. Choose a pivot element from the list. The pivot can be selected in various ways, such as taking the first element, the last element, or a random element. For simplicity, let's assume we select the last element of the list as the pivot.
2. Partition the list by rearranging the elements such that all elements smaller than the pivot are placed before it, and all elements greater than the pivot are placed after it. After this partitioning step, the pivot is in its final sorted position.
3. Recursively apply the above two steps to the sub-array of elements smaller than the pivot and the sub-array of elements greater than the pivot. This means performing the partitioning step on each sub-array and continuing the process until each sub-array contains only one element or is empty.
4. Once the recursion ends and all sub-arrays are sorted, the entire list is sorted.

### Figures on this page:

![[L27-Quick, Selection Sort_s2_img1.png]]


## Page 3

Here's an example to illustrate the steps:
Consider the list: [8, 3, 1, 7, 0, 10, 2]
Step 1: Choose the pivot. Let's choose the last element, 2.
Step 2: Partition the list. Rearrange the elements such that all elements smaller than 2 come before it, and all elements greater than 2 come after it.
Partitioned list: [1, 0, 2, 7, 8, 10, 3]
Step 3: Recursively apply the above steps to the sub-arrays. In this case, we have two sub-arrays: [1, 0] and [7, 8, 10, 3].
For the sub-array [1, 0]:
- Choose the last element, 0, as the pivot.
- Partition the list: [0, 1]
For the sub-array [7, 8, 10, 3]:
- Choose the last element, 3, as the pivot.
- Partition the list: [7, 8, 3, 10]
Continue applying the steps recursively to the sub-arrays until each sub-array contains only one element or is empty.
Step 4: The recursion ends when each sub-array is sorted (contains only one element or is empty). At this point, the entire list is sorted.
Final sorted list: [0, 1, 2, 3, 7, 8, 10]
QuickSort has an average time complexity of O(n log n), making it efficient for large lists. However, its worst-case time complexity can be O(n^2) if the pivot selection is unbalanced, which can be mitigated by using randomized pivot selection or other techniques. Overall, QuickSort is a widely used and efficient sorting algorithm.

### Figures on this page:

![[L27-Quick, Selection Sort_s3_img1.png]]


## Page 4

public class QuickSort {
public static void main(String[] args) {
int[] arr = {8, 3, 1, 7, 0, 10, 2};
quickSort(arr, 0, arr.length - 1);
System.out.println("Sorted array:");
for (int num : arr) {
System.out.print(num + " ");
}
}
public static void quickSort(int[] arr, int low, int high) {
if (low < high) {
// Partition the array
int partitionIndex = partition(arr, low, high);
// Recursively sort the sub-arrays
quickSort(arr, low, partitionIndex - 1);
quickSort(arr, partitionIndex + 1, high);
}
}
public static int partition(int[] arr, int low, int high) {
int pivot = arr[high];
int i = low - 1;
for (int j = low; j < high; j++) {
if (arr[j] <= pivot) {
i++;
// Swap arr[i] and arr[j]
int temp = arr[i];
arr[i] = arr[j];
arr[j] = temp;
}
}
int temp = arr[i + 1];
arr[i + 1] = arr[high];
arr[high] = temp;
return i + 1;
}
}

### Figures on this page:

![[L27-Quick, Selection Sort_s4_img1.png]]


## Page 5

Topic/Course
Selection Sort
Certainly! Here's a step-by-step explanation of how the selection sort algorithm works:
1. Start with an unsorted list of elements.
Example: [64, 25, 12, 22, 11]
2. The algorithm divides the list into two parts: the sorted part and the unsorted part. Initially, the sorted part is empty, and the unsorted part contains all the elements.
3. In each iteration, the algorithm finds the minimum element from the unsorted part of the list.
4. To find the minimum element, the algorithm compares each element in the unsorted part with the current minimum element. If it finds a smaller element, it updates the minimum element.
5. Once the minimum element is found, it is swapped with the first element of the unsorted part. This places the minimum element at the end of the sorted part and expands the sorted part by one element.
6. The algorithm repeats steps 3-5 until the entire list is sorted. The sorted part gradually grows from left to right until it encompasses the entire list.
7. Finally, when the algorithm completes all iterations, the list is fully sorted.

### Figures on this page:

![[L27-Quick, Selection Sort_s5_img1.png]]


## Page 6

Here's an example to illustrate the steps:
Iteration 1:
- Find the minimum element from the unsorted part [64, 25, 12, 22, 11]. The minimum is 11.
- Swap the minimum element (11) with the first element (64) of the unsorted part.
- The sorted part becomes [11], and the unsorted part becomes [64, 25, 12, 22].
Iteration 2:
- Find the minimum element from the unsorted part [64, 25, 12, 22]. The minimum is 12.
- Swap the minimum element (12) with the first element (64) of the unsorted part.
- The sorted part becomes [11, 12], and the unsorted part becomes [64, 25, 22].
Iteration 3:
- Find the minimum element from the unsorted part [64, 25, 22]. The minimum is 22.
- Swap the minimum element (22) with the first element (64) of the unsorted part.
- The sorted part becomes [11, 12, 22], and the unsorted part becomes [64, 25].
Iteration 4:
- Find the minimum element from the unsorted part [64, 25]. The minimum is 25.
- Swap the minimum element (25) with the first element (64) of the unsorted part.
- The sorted part becomes [11, 12, 22, 25], and the unsorted part becomes [64].
Iteration 5:
- Find the minimum element from the unsorted part [64]. The minimum is 64.
- Swap the minimum element (64) with the first element (64) of the unsorted part. (No change in this step)
- The sorted part becomes [11, 12, 22, 25, 64], and the unsorted part becomes [] (empty).
The iterations are now complete, and the list [64, 25, 12, 22, 11] is sorted in ascending order to [11, 12, 22, 25, 64].
Selection sort has a time complexity of O(n^2), where n is the number of elements in the list. It is not very efficient for large lists, but it is simple to implement and performs well for small lists or partially sorted lists.

### Figures on this page:

![[L27-Quick, Selection Sort_s6_img1.png]]


## Page 7

public class SelectionSort {
public static void main(String[] args) {
int[] arr = {64, 25, 12, 22, 11};
selectionSort(arr);
System.out.println("Sorted array:");
for (int num : arr) {
System.out.print(num + " ");
}
}
public static void selectionSort(int[] arr) {
int n = arr.length;
for (int i = 0; i < n - 1; i++) {
int minIndex = i;
for (int j = i + 1; j < n; j++) {
if (arr[j] < arr[minIndex]) {
minIndex = j;
}
}
int temp = arr[minIndex];
arr[minIndex] = arr[i];
arr[i] = temp;
}
}
}

### Figures on this page:

![[L27-Quick, Selection Sort_s7_img1.png]]


## Page 8

THANK YOU

### Figures on this page:

![[L27-Quick, Selection Sort_s8_img1.png]]

![[L27-Quick, Selection Sort_s8_img2.png]]


# Source: L31-Sorted Unique Permutation.pptx


## Page 1


[Speaker Notes]
1st slide (Mandatory)

### Figures on this page:

![[L31-Sorted Unique Permutation_s1_img1.png]]


## Page 2

Sorted Unique Permutation
Print distinct sorted permutations with duplicates allowed in input
Write a program to print all distinct permutations of a given string in sorted order. Note that the input string may contain duplicate characters.In mathematics, the notion of permutation relates to the act of arranging all the members of a set into some sequence or order, or if the set is already ordered, rearranging (reordering) its elements, a process called permuting.
Examples:
Input : BAC Output : ABC ACB BAC BCA CAB CBAInput : AAB Output : AAB ABA BAAInput : DBCA Output: ABCD ABDC ACBD ACDB ADBC ADCB BACD BADC BCAD BCDA BDAC BDCA CABD CADB CBAD CBDA CDAB CDBA DABC DACB DBAC DBCA DCAB DCBA

### Figures on this page:

![[L31-Sorted Unique Permutation_s2_img1.png]]


## Page 3

sorted Permutations
Concept Used: The number of Strings generated by a string of distinct characters of length ‘n’ is equal to ‘n!’. Sorting any given string and generating the lexicographically next bigger string until we reach the largest lexicographically string from those characters.
Different permutations of word “geeks” Length of string = 5 Character ‘e’ repeats 2 times. Result = 5!/2! = 60.

## Page 4

Sorted Permutations
Steps:
Example: Consider a string “ABCD”.Step 1: Sort the string. Step 2: Obtain the total number of permutations which can be formed from that string. Step 3: Print the sorted string and then loop for the number of (permutations-1) times as 1st string is already printed. Step 4: Find the next greater string.

## Page 5

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class Permutations {
public static void main(String[] args) {
String input = "BAC";
distinctPermutations(input);
}
public static void distinctPermutations(String input) {
char[] chars = input.toCharArray();
Arrays.sort(chars);
input = new String(chars);
permute(input.toCharArray(), 0);
}
public static void permute(char[] chars, int index) {
if (index == chars.length - 1) {
System.out.println(String.valueOf(chars));
return;
}
Set<Character> used = new HashSet<>();
for (int i = index; i < chars.length; i++) {
if (used.contains(chars[i]))
continue;
used.add(chars[i]);
swap(chars, index, i);
permute(chars, index + 1);
swap(chars, index, i);
}
}
public static void swap(char[] chars, int i, int j) {
char temp = chars[i];
chars[i] = chars[j];
chars[j] = temp;
}
}

## Page 6

THANK YOU

### Figures on this page:

![[L31-Sorted Unique Permutation_s6_img1.png]]

![[L31-Sorted Unique Permutation_s6_img2.png]]


# Source: L32-Maneuvering.pptx


## Page 1


[Speaker Notes]
1st slide (Mandatory)

### Figures on this page:

![[L32-Maneuvering_s1_img1.png]]


## Page 2

Topic/Course
Maneuvering
Maneuvering a Cave Problem
Problem:
The task is to count all the possible paths from top left to bottom right of a m x n matrix with the constraints that from each cell you can either move only to right or down.
Examples :
Input : m = 2, n = 2;
Output : 2
There are two paths
(0, 0) -> (0, 1) -> (1, 1)
(0, 0) -> (1, 0) -> (1, 1)

### Figures on this page:

![[L32-Maneuvering_s2_img1.png]]


## Page 3

Let NumberOfPaths(m, n) be the count of paths to reach row number m and column number n in the matrix, NumberOfPaths(m, n) can be recursively written as following.
class Main {
static int numberOfPaths(int m, int n){
if (m == 1 || n == 1)
return 1;
return numberOfPaths(m-1,n)+numberOfPaths(m,n-1);
// + numberOfPaths(m-1, n-1);
}
public static void main(String args[])
{
System.out.println(numberOfPaths(3, 3));
}
}

### Figures on this page:

![[L32-Maneuvering_s3_img1.png]]


## Page 4

THANK YOU

### Figures on this page:

![[L32-Maneuvering_s4_img1.png]]

![[L32-Maneuvering_s4_img2.png]]


# Source: L33-Combination.pptx


## Page 1


[Speaker Notes]
1st slide (Mandatory)

### Figures on this page:

![[L33-Combination_s1_img1.png]]


## Page 2

Topic/Course
Combination
Permutation and Combination are two important concepts.
Permutation is the different arrangements of the set elements. The arrangements can be made by taking one element at a time, some element at a time and all elements at a time.
Combination is the different selections of the set of elements taken one by one, or some, or all at a time. In Java, the definition of Permutation and Combination is the same.

### Figures on this page:

![[L33-Combination_s2_img1.png]]

![[L33-Combination_s2_img2.png]]

![[L33-Combination_s2_img3.png]]


## Page 3

Combination is the different selections of the set of elements taken one by one, or some, or all at a time.
In Java, the definition of Permutation and Combination is the same.
For example, if we have a set having only two elements, X and Y.
Combination value is very easy in Java but to get all the combination of array, list, and set elements is more difficult than getting permutations. For getting the combination value programmatically in Java, we use the following formula:
Combination = fact(n) / (fact(r) * fact(n-r));

### Figures on this page:

![[L33-Combination_s3_img1.png]]


## Page 4



### Figures on this page:

![[L33-Combination_s4_img1.png]]

![[L33-Combination_s4_img2.png]]


## Page 5

import java.util.*;
public class CombinationExample {
static int fact(int number) {
int f = 1;
int j = 1;
while(j <= number) {
f = f * j;
j++;
}
return f;
}
public static void main(String args[]) {
List<Integer> numbers = new ArrayList<Integer>();
numbers.add(9);
numbers.add(12);
numbers.add(19);
numbers.add(61);
numbers.add(19);
int n = numbers.size();
int r = 2;  int result;
result = fact(n) / (fact(r) * fact(n-r));
System.out.println("The combination value for the numbers list is: " + result);
}
}

### Figures on this page:

![[L33-Combination_s5_img1.png]]


## Page 6



### Figures on this page:

![[L33-Combination_s6_img1.png]]

![[L33-Combination_s6_img2.png]]


## Page 7

THANK YOU

### Figures on this page:

![[L33-Combination_s7_img1.png]]

![[L33-Combination_s7_img2.png]]


# Source: L34-Josephus trap.pptx


## Page 1


[Speaker Notes]
1st slide (Mandatory)

### Figures on this page:

![[L34-Josephus trap_s1_img1.png]]


## Page 2

Josephus trap
In computer science and mathematics, the Josephus Problem (or Josephus permutation) is a theoretical problem.
The problem statement:
There are n people standing in a circle waiting to be executed. The counting out begins at some point in the circle and proceeds around the circle in a fixed direction.
How is the Josephus problem solved?
The Josephus problem can be solved using recursion. For each iteration, recursively delete the Kth position until only one person is left.

### Figures on this page:

![[L34-Josephus trap_s2_img1.png]]


## Page 3

Example Diagram

### Figures on this page:

![[L34-Josephus trap_s3_img1.png]]

![[L34-Josephus trap_s3_img2.png]]


## Page 4

import java.io.*;
class Main {
static int josephus(int n, int k){
if (n == 1)
return 1;
else
return (josephus(n - 1, k) + k - 1) % n + 1;
}
public static void main(String[] args){
int n = 14;
int k = 2;
System.out.println("The chosen place is "
+ josephus(n, k));
}
}
Output
The chosen place is 13

### Figures on this page:

![[L34-Josephus trap_s4_img1.png]]


## Page 5

THANK YOU

### Figures on this page:

![[L34-Josephus trap_s5_img1.png]]

![[L34-Josephus trap_s5_img2.png]]


# Source: L35-Maze Solving.pptx


## Page 1


[Speaker Notes]
1st slide (Mandatory)

### Figures on this page:

![[L35-Maze Solving_s1_img1.png]]


## Page 2

Topic/Course
Maze Solving
Introduction
Consider the maze to be a black and white image, with black pixels representing walls, and white pixels representing a path. Two white pixels are special, one being the entry to the maze and another exit.
Given such a maze, we want to find a path from entry to the exit.
Modelling the Maze
0 -> Road
1 -> Wall
2 -> Maze entry
3 -> Maze exit
4 -> Cell part of the path from entry to exit

### Figures on this page:

![[L35-Maze Solving_s2_img1.png]]


## Page 3

Gray blocks are dead ends (value = 0).
binary matrix representation of the above maze.
{ 1, 0, 0, 0}
{1, 1, 0, 1}
{0, 1, 0, 0}
{1, 1, 1, 1}

### Figures on this page:

![[L35-Maze Solving_s3_img1.png]]

![[L35-Maze Solving_s3_img2.png]]


## Page 4



### Figures on this page:

![[L35-Maze Solving_s4_img1.png]]

![[L35-Maze Solving_s4_img2.png]]


## Page 5

public class RatMaze {
final int N = 4;
void printSolution(int sol[][]){
for (int i = 0; i < N; i++) {
for (int j = 0; j < N; j++)
System.out.print(" " + sol[i][j] + " ");
System.out.println();
}}
boolean isSafe(int maze[][], int x, int y){
return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
}
{
int sol[][] = { { 0, 0, 0, 0 },
{ 0, 0, 0, 0 },
{ 0, 0, 0, 0 },
{ 0, 0, 0, 0 } };
if (solveMazeUtil(maze, 0, 0, sol) == false) {
System.out.print("Solution doesn't exist");
return false;
}
printSolution(sol);
return true;
}
boolean solveMazeUtil(int maze[][], int x, int y,int sol[][]){
if (x == N - 1 && y == N - 1) {
sol[x][y] = 1;
return true;
}
if (isSafe(maze, x, y) == true) {
sol[x][y] = 1;
if (solveMazeUtil(maze, x + 1, y, sol))
return true;
if (solveMazeUtil(maze, x, y + 1, sol))
return true;

### Figures on this page:

![[L35-Maze Solving_s5_img1.png]]


## Page 6

sol[x][y] = 0;            return false;        }         return false;    }     public static void main(String args[]){        RatMaze rat = new RatMaze();        int maze[][] = { { 1, 0, 0, 0 },                         { 1, 1, 0, 1 },                         { 0, 1, 0, 0 },                         { 1, 1, 1, 1 } };        rat.solveMaze(maze);    }}
Output:       1  0  0  0        1  1  0  0        0  1  0  0        0  1  1  1
maze[][]={ {1, 1, 1, 1, 1},		{0, 0, 1, 0, 1},		{1, 1, 1, 1, 0},		{1, 0, 0, 0, 1},		{1, 1, 1, 1, 1}   };

### Figures on this page:

![[L35-Maze Solving_s6_img1.png]]


## Page 7

THANK YOU

### Figures on this page:

![[L35-Maze Solving_s7_img1.png]]

![[L35-Maze Solving_s7_img2.png]]


# Source: L36-N Queens.pptx


## Page 1


[Speaker Notes]
1st slide (Mandatory)

### Figures on this page:

![[L36-N Queens_s1_img1.png]]


## Page 2

N Queens
The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other.
Example
The following is a solution for 4 Queen problem. The expected output is a binary matrix which has 1s for the blocks where queens are placed.

### Figures on this page:

![[L36-N Queens_s2_img1.png]]

![[L36-N Queens_s2_img2.png]]


## Page 3

What is Backtracking?
In backtracking, we start with one pos­si­ble move out of many avail­able moves. We then try to solve the prob­lem.
What is the N-Queens Problem?
How can N queens be placed on an NxN chessboard so that no two of them attack each other?
This problem is commonly seen for N=4 and N=8.
Let’s look at an example where N=4
N Queens Problem

### Figures on this page:

![[L36-N Queens_s3_img1.png]]


## Page 4

Before solving the problem, you need to know about the movement of the queen in chess.
A queen can move any number of steps in any direction. The only constraint is that it can’t change its direction while it’s moving.
One thing that is clear by looking at the queen’s movement is that no two queens can be in the same row or column.
That allows us to place only one queen in each row and each column.
When N=4, the solution looks like :
N Queens Problem-Movement of Queen

### Figures on this page:

![[L36-N Queens_s4_img1.png]]


## Page 5

Solution to the N-Queens Problem
The way we try to solve this is by placing a queen at a position and trying to rule out the possibility of it being under attack. We place one queen in each row/column.
If we see that the queen is under attack at its chosen position, we try the next position.
If a queen is under attack at all the positions in a row, we backtrack and change the position of the queen placed prior to the current position.
We repeat this process of placing a queen and backtracking until all the N queens are placed successfully.
Queen solution
N Queens Problem-Movement of Queen

### Figures on this page:

![[L36-N Queens_s5_img1.png]]

![[L36-N Queens_s5_img2.png]]


## Page 6

The step by step backtracking is shown as follows:
Start
No place for queen 3, so we backtrack

### Figures on this page:

![[L36-N Queens_s6_img1.png]]

![[L36-N Queens_s6_img2.png]]

![[L36-N Queens_s6_img3.png]]


## Page 7

After backtracking we are not able to place queen 4, so again we backtrack.
This time we backtrack all the way to the first queen.

### Figures on this page:

![[L36-N Queens_s7_img1.png]]

![[L36-N Queens_s7_img2.png]]

![[L36-N Queens_s7_img3.png]]


## Page 8

First three queens placed successfully after backtracking.
All the queens placed

### Figures on this page:

![[L36-N Queens_s8_img1.png]]

![[L36-N Queens_s8_img2.png]]

![[L36-N Queens_s8_img3.png]]


## Page 9

public class NQueenProblem {
final int N = 4;
void printSolution(int board[][]){
for (int i = 0; i < N; i++) {
for (int j = 0; j < N; j++)
System.out.print(" " + board[i][j]
+ " ");
System.out.println();        }
}
boolean isSafe(int board[][], int row, int col){
int i, j;
for (i = 0; i < col; i++)
if (board[row][i] == 1)
return false;
for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
if (board[i][j] == 1)
return false;
for (i = row, j = col; j >= 0 && i < N; i++, j--)
if (board[i][j] == 1)
return false;
return true;
}
boolean solveNQUtil(int board[][], int col){
if (col >= N)
return true;
for (int i = 0; i < N; i++) {
if (isSafe(board, i, col)) {
board[i][col] = 1;

### Figures on this page:

![[L36-N Queens_s9_img1.png]]


## Page 10

if (solveNQUtil(board, col + 1) == true)
return true;
board[i][col] = 0; // BACKTRACK
}        }
return false;
}
boolean solveNQ()
{
int board[][] = { { 0, 0, 0, 0 },
{ 0, 0, 0, 0 },
{ 0, 0, 0, 0 },
{ 0, 0, 0, 0 } };
if (solveNQUtil(board, 0) == false) {
System.out.print("Solution does not exist");
return false;
}
printSolution(board);
return true;
}
public static void main(String args[]){
NQueenProblem Queen = new NQueenProblem();
Queen.solveNQ();
}}
Output:
0  0  1  0
1  0  0  0
0  0  0  1
0  1  0  0

### Figures on this page:

![[L36-N Queens_s10_img1.png]]


## Page 11

THANK YOU

### Figures on this page:

![[L36-N Queens_s11_img1.png]]

![[L36-N Queens_s11_img2.png]]


# Source: Module-02.pdf


## Page 1: Module-02


# Module-02


## Page 2: ❑Booth's Algorithm


# ❑Simple Sieve


# ❑Segmented Sieve


# ❑Euler's phi Algorithm


# ❑Remainder Theorem


# ❑Strobogrammatic Number


# ❑Binary Palindrome


# ❑Booth's Algorithm


## Page 3: 2.Time Complexity should be O


# • Write a java program for finding a number is prime or not.


# 1.Time complexity should be  O(n).


# we will try to reduce it to


# 2.Time Complexity should be O


### Figures on this page:

![[Module-02_p3_img1.png]]

![[Module-02_p3_img2.png]]

![[Module-02_p3_img3.png]]

![[Module-02_p3_img4.png]]


## Page 4: }                                                              Time Complexity – O(n)


## 1.


## import java.util.Scanner;


## 2.


## public class Main {


## 3.


## public static void main(String[] args) {


## 4.


## Scanner sc=new Scanner(System.in);


## 5.


## int n=sc.nextInt();


## 6.


## for(int i=2;i<=n;i++)


## 7.


## {


## 8.


## if(n%i==0)


## 9.


## {


## 10.


## System.out.print("not a prime");


## 11.


## return;


## 12.


## }


## 13.


## }


## 14.


## System.out.print("prime");


## 15.


## return;


## 16.


## }


## 17.


## }                                                              Time Complexity – O(n)


## Page 5: Time Complexity – O( srqt(n))


## 1.


## import java.util.Scanner;


## 2.


## public class Main


## 3.


## {


## 4.


## public static void main(String[] args) {


## 5.


## Scanner sc=new Scanner(System.in);


## 6.


## int n=sc.nextInt();


## 7.


## for(int i=2;i*i<=n;i++)


## 8.


## {


## 9.


## if(n%i==0)


## 10.


## {


## 11.


## System.out.print("not a prime");


## 12.


## return;


## 13.


## }


## 14.


## }


## 15.


## System.out.print("prime");


## 16.


## return;


## 17.


## }


## 18. }


## Time Complexity – O( srqt(n))


## Page 6: • Composite Numbers?


## • Sieve of Eratosthenes is a method to find the prime numbers and


# composite numbers among a group of numbers.


# • Prime Numbers?


# • Composite Numbers?


## Page 7: ❖Now, encircle the rest of the numbers 7, 11, 13, 17 and 19.


# • What are all prime numbers less than 20. (Use the Sieve of


# Eratosthenes method).


# ❖Now, exclude 1 since it is not a prime nor a composite number.


# ❖In the next step, encircle 2 and cross the multiples of 2 (4, 6, 8, 10, 12,


# 14, 16, 18, 20)


# ❖Encircle 3 and cross the left multiples of 3 (9, 15)


# ❖Encircle 5 and cross the left multiples of 5 (only 10 is left)


# ❖Now, encircle the rest of the numbers 7, 11, 13, 17 and 19.


## Page 8



### Figures on this page:

![[Module-02_p8_img1.png]]


## Page 9: • Step-4 Take a loop and print wherever array contains true value.


# Steps of Sieve’s Algorithm


# • Step-1 Take a Boolean array of size like up to what range you want to


# know the prime numbers.


# • Step-2 Except 0 & 1 index of the array mark all the remaining


# elements as “true”.


# • Step-3 Start from 2 cross out all the multiples of 2 and then for 3 and


# so on..


# • Step-4 Take a loop and print wherever array contains true value.


## Page 10: }


# Code


## 1.


## public static void sieve(int n)


## 2.


## {


## 3.


## boolean p[]=new boolean[n+1];


## 4.


## for(int i=2;i<=n;i++)


## 5.


## p[i]=true;


## 6.


## for(int i=2;i*i<=n;i++)  {


## 7.


## for(int j=i*i;j<=n;j=j+i)  {


## 8.


## if(p[j]==true)


## 9.


## p[j]=false;


## 10.


## }


## 11.


## }


## 12.


## for(int i=2;i<=n;i++)   {


## 13.


## if(p[i])


## 14.


## System.out.print(i+" ");


## 15.


## }


## 16.


## }


## Page 11: ➢Hence O(n log(log n))


# Time Complexity


# ➢For each prime p we are marking 2p,3p,4p,……kp.


# ➢n/2 + n/3 + n/4 +…….


# ➢n(1/2 + 1/3 + 1/4+…..)


# ➢n(log(log n))


## ➢Hence O(n log(log n))


## Page 12: Euclid’s Algorithm


# Euclid’s Algorithm


## Page 13: Greatest Common Divisors-----------------3


# • GCD can be found in two ways:


# i.


# Traditional Method


# ii.


# Euclid’s Algorithm


## Traditional Method


# Example : gcd (12,33)→?


# Given  Numbers               12                                  33


# Divisors ----------------1,2,3,4,6,12                   1,3,11,33


# Common Divisors    ----------------------- 1,3


# Greatest Common Divisors-----------------3


## Page 14: gcd(12,33)=3


## Euclid Algorithm:


# Example gcd(12,33)----->?


# Max(12,33) will be given to a


# quotient                     a                        b                       remainder


# 2                            33                     12                                9


# 1                            12                       9                                3


# 3                             9                        3                                0


# 3                        0


# Stop the process as b is ‘0’ and whatever the ‘a’ contains that is the gcd


# of the two numbers.


# gcd(12,33)=3


## Page 15: 15. }


## 1.


## import java.util.Scanner;


## 2.


## class Main{


## 3.


## public static int gcd(int a,int b)


## 4.


## {


## 5.


## if(b==0)


## 6.


## return a;


## 7.


## return gcd(b,a%b);


## 8.


## }


## 9.


## public static void main (String[] args) {


## 10.


## Scanner sc=new Scanner(System.in);


## 11.


## int n=sc.nextInt();


## 12.


## int m=sc.nextInt();


## 13.


## System.out.print(gcd(n,m));


## 14.


## }


## 15. }


## Page 16: Euler’s Phi Algorithm


# Euler’s Phi Algorithm


## Page 17: factor ( GCD or HCF ) is 1 .


# Euler’s Phi Function


# • Denoted by


# • Euler's phi (or totient) function of a positive integer n is the number of


# integers in {1,2,3,...,n} which are relatively prime to n.


## Relatively Prime:


## Two numbers are said to be relatively prime if their greatest common


# factor ( GCD or HCF ) is 1 .


### Figures on this page:

![[Module-02_p17_img1.png]]


## Page 18: Therefore phi(5) is  4.


# • Example-1


# So for the numbers that are less than 5 i.e. 1,2,3 & 4 for these numbers


# we have to check whether gcd is 1 or not and finally we have to count


# how many pairs of n gcd is 1 and we have to say the count that is called


# as eulers phi function.


# Gcd(1,5)----→1


# Gcd(2,5)----→1


# Gcd(3,5)----→1


# Gcd(4,5)-----→1


# Therefore phi(5) is  4.


### Figures on this page:

![[Module-02_p18_img1.png]]

![[Module-02_p18_img2.png]]

![[Module-02_p18_img3.png]]

![[Module-02_p18_img4.png]]

![[Module-02_p18_img5.png]]

![[Module-02_p18_img6.png]]

![[Module-02_p18_img7.png]]

![[Module-02_p18_img8.png]]

![[Module-02_p18_img9.png]]

![[Module-02_p18_img10.png]]

![[Module-02_p18_img11.png]]

![[Module-02_p18_img12.png]]

![[Module-02_p18_img13.png]]

![[Module-02_p18_img14.png]]

![[Module-02_p18_img15.png]]

![[Module-02_p18_img16.png]]

![[Module-02_p18_img17.png]]

![[Module-02_p18_img18.png]]

![[Module-02_p18_img19.png]]

![[Module-02_p18_img20.png]]

![[Module-02_p18_img21.png]]

![[Module-02_p18_img22.png]]

![[Module-02_p18_img23.png]]

![[Module-02_p18_img24.png]]

![[Module-02_p18_img25.png]]

![[Module-02_p18_img26.png]]


## Page 19: Gcd(7,8)=1


# • Example-2


# ?????


# We have to check number with 1,2,3,4,5,6,7.


# Gcd(1,8)=1


# Gcd(2,8)=2


# Gcd(3,8)=1


# Gcd(4,8)=4               we have to count only pairs which give gcd as 1 so


# Gcd(5,8)=1                    phi (8)-->4.


# Gcd(6,8)=2


# Gcd(7,8)=1


### Figures on this page:

![[Module-02_p19_img1.png]]

![[Module-02_p19_img2.png]]

![[Module-02_p19_img3.png]]

![[Module-02_p19_img4.png]]

![[Module-02_p19_img5.png]]

![[Module-02_p19_img6.png]]

![[Module-02_p19_img7.png]]

![[Module-02_p19_img8.png]]


## Page 20: the below formula:


# • So if the input number is large it is difficult find all the relative prime


# pairs that is reason Euler introduced 3 formulas through which we can


# easily tell the relatively prime pairs count.


# i.


# If the given number ‘n’ is prime then


# ii.


# If the given number is not a prime number but we can represent ‘n’


# as product as primes the                                                 here p != q.


# iii. If the above two failed then find the prime factors and substitute in


# the below formula:


### Figures on this page:

![[Module-02_p20_img1.png]]

![[Module-02_p20_img2.png]]

![[Module-02_p20_img3.png]]

![[Module-02_p20_img4.png]]

![[Module-02_p20_img5.png]]

![[Module-02_p20_img6.png]]

![[Module-02_p20_img7.png]]

![[Module-02_p20_img8.png]]

![[Module-02_p20_img9.png]]

![[Module-02_p20_img10.png]]

![[Module-02_p20_img11.png]]

![[Module-02_p20_img12.png]]

![[Module-02_p20_img13.png]]

![[Module-02_p20_img14.png]]

![[Module-02_p20_img15.png]]

![[Module-02_p20_img16.png]]

![[Module-02_p20_img17.png]]

![[Module-02_p20_img18.png]]

![[Module-02_p20_img19.png]]

![[Module-02_p20_img20.png]]

![[Module-02_p20_img21.png]]

![[Module-02_p20_img22.png]]

![[Module-02_p20_img23.png]]

![[Module-02_p20_img24.png]]

![[Module-02_p20_img25.png]]

![[Module-02_p20_img26.png]]

![[Module-02_p20_img27.png]]

![[Module-02_p20_img28.png]]

![[Module-02_p20_img29.png]]

![[Module-02_p20_img30.png]]

![[Module-02_p20_img31.png]]

![[Module-02_p20_img32.png]]

![[Module-02_p20_img33.png]]

![[Module-02_p20_img34.png]]

![[Module-02_p20_img35.png]]

![[Module-02_p20_img36.png]]

![[Module-02_p20_img37.png]]

![[Module-02_p20_img38.png]]

![[Module-02_p20_img39.png]]

![[Module-02_p20_img40.png]]

![[Module-02_p20_img41.png]]

![[Module-02_p20_img42.png]]

![[Module-02_p20_img43.png]]

![[Module-02_p20_img44.png]]

![[Module-02_p20_img45.png]]

![[Module-02_p20_img46.png]]

![[Module-02_p20_img47.png]]

![[Module-02_p20_img48.png]]

![[Module-02_p20_img49.png]]

![[Module-02_p20_img50.png]]

![[Module-02_p20_img51.png]]

![[Module-02_p20_img52.png]]

![[Module-02_p20_img53.png]]

![[Module-02_p20_img54.png]]

![[Module-02_p20_img55.png]]

![[Module-02_p20_img56.png]]

![[Module-02_p20_img57.png]]

![[Module-02_p20_img58.png]]

![[Module-02_p20_img59.png]]

![[Module-02_p20_img60.png]]

![[Module-02_p20_img61.png]]

![[Module-02_p20_img62.png]]

![[Module-02_p20_img63.png]]

![[Module-02_p20_img64.png]]

![[Module-02_p20_img65.png]]

![[Module-02_p20_img66.png]]

![[Module-02_p20_img67.png]]

![[Module-02_p20_img68.png]]

![[Module-02_p20_img69.png]]

![[Module-02_p20_img70.png]]

![[Module-02_p20_img71.png]]

![[Module-02_p20_img72.png]]

![[Module-02_p20_img73.png]]

![[Module-02_p20_img74.png]]

![[Module-02_p20_img75.png]]

![[Module-02_p20_img76.png]]

![[Module-02_p20_img77.png]]

![[Module-02_p20_img78.png]]

![[Module-02_p20_img79.png]]

![[Module-02_p20_img80.png]]


## Page 21: So (7-1)(3-1)=6(2)=12 relatively primes are there for 21.


# Examples:


# 1. num=7


# As num is prime we can substitute in phi(num)=num-1 formula


# So 7-1=6 relatively primes are there for 7.


# 2. num=21


# Here num is not a prime number but this can be represented in the


# product of primes form i.e. 7*3


# So (7-1)(3-1)=6(2)=12 relatively primes are there for 21.


## Page 22: vi. 372


# 3. num=1000


# Prime factors of 1000 are 2^3 * 5^3


# So after removing powers we will remain with 2 and 5 and now apply


# 3rd formula we will get 400 as the result.


## Practice Questions


# i.


# 5


# ii.


# 31


# iii. 35


# iv. 7000


# v.


# 369


# vi. 372


### Figures on this page:

![[Module-02_p22_img1.png]]

![[Module-02_p22_img2.png]]

![[Module-02_p22_img3.png]]

![[Module-02_p22_img4.png]]

![[Module-02_p22_img5.png]]

![[Module-02_p22_img6.png]]

![[Module-02_p22_img7.png]]

![[Module-02_p22_img8.png]]

![[Module-02_p22_img9.png]]

![[Module-02_p22_img10.png]]

![[Module-02_p22_img11.png]]

![[Module-02_p22_img12.png]]

![[Module-02_p22_img13.png]]

![[Module-02_p22_img14.png]]

![[Module-02_p22_img15.png]]

![[Module-02_p22_img16.png]]

![[Module-02_p22_img17.png]]

![[Module-02_p22_img18.png]]

![[Module-02_p22_img19.png]]

![[Module-02_p22_img20.png]]

![[Module-02_p22_img21.png]]

![[Module-02_p22_img22.png]]

![[Module-02_p22_img23.png]]

![[Module-02_p22_img24.png]]

![[Module-02_p22_img25.png]]

![[Module-02_p22_img26.png]]

![[Module-02_p22_img27.png]]


## Page 23: return gcd(b % a, a);    } }  }


## 1.


## import java.util.*;


## 2.


## public class Eulers {


## 3.


## public static void main(String args[]) {


## 4.


## Scanner sc = new Scanner(System.in);


## 5.


## int num = sc.nextInt();


## 6.


## int a = 1, count = 0;


## 7.


## for (a = 1; a < num; a++) {


## 8.


## if (gcd(a, num) == 1)


## 9.


## count++;    }


## 10.


## System.out.print(count);


## 11.


## }


## 12.


## public static int gcd(int a, int b) {


## 13.


## if (a == 0)


## 14.


## return b;


## 15.


## return gcd(b % a, a);    } }  }


## Page 24: Chinese Remainder Theorem


# Chinese Remainder Theorem


## Page 25: • Check Whether (m1,m2,....,mn) are relatively prime or not.


## Chinese Remainder Theorem


# • It is used to solve a set of different congruent equations with one


# variable but different modulo which are relatively prime.


## • x ≡ a1(mod m1),


## x ≡ a2(mod m2),


## .


## .


## .


## x ≡ an(mod mn).


# • Where one variable i.e common variable is ‘x’ which we have to find


# with the help of different modulo m1,m2,m3,…..,mn and a1,a2,….,an.


# • Check Whether (m1,m2,....,mn) are relatively prime or not.


## Page 26: -1) mod M


## • Substitute the values after finding M1 , M2 , M3 and M1


## -1, M2


## -1, M3


## -1 .


## x=(a1*M1* M1


## -1 + a2*M2* M2


## -1 + a3*M3* M3


## -1 +…….+ an*Mn* Mn


## -1) mod M


## Page 27: -1) mod M


# Steps :


## • Step 1: Calculate the value of M where M = m1*m2*m3.


## • Step 2: Find M1 , M2 , M3 where M1 = M/m1, M2= M/m2, M3 = M/m3.


## • Step 3: Find M1


## -1, M2


## -1, M3


## -1 which are the modular multiplicative


## inverses of M-1, M-2, M-3 respectively using the congruence relations


# give below.


## M1* M1


## -1 ≡ 1(mod m1)


## M2* M2


## -1 ≡ 1(mod m2)


## M3* M3


## -1 ≡ 1(mod m3)


## • Step 4:


## x=(a1*M1* M1


## -1 + a2*M2* M2


## -1 + a3*M3* M3


## -1 +…….+ an*Mn* Mn


## -1) mod M


## Page 28: M3 = M/m3 ➔105/7➔15


# Example - 01


# • x ≡ 2(mod 3)


# x ≡ 3(mod 5)


# x ≡ 2(mod 7)


# Solution:


## Step→01


# M=m1*m2*m3=>3*5*7➔105


## Step→02


## M1 = M/m1 ➔105/3➔35


## M2 = M/m2 ➔105/5➔21


## M3 = M/m3 ➔105/7➔15


## Page 29: -1 =1


## • Step→03


## M1* M1


## -1 = 1 mod m1


## M2* M2


## -1 = 1 mod m2


## M3* M3


## -1 = 1 mod m3


# 35*(1)!= 1 mod 3


# 35*(2)==1 mod 3


## So M1


## -1 =2


# 21*(1)==1 mod 5


## So M2


## -1 =1


# 15*(1)==1 mod 7


## So M3


## -1 =1


### Figures on this page:

![[Module-02_p29_img1.png]]

![[Module-02_p29_img2.png]]

![[Module-02_p29_img3.png]]

![[Module-02_p29_img4.png]]

![[Module-02_p29_img5.png]]


## Page 30: X=23


# • Step→04


## x=(a1*M1* M1


## -1 + a2*M2* M2


## -1 + a3*M3* M3


## -1 +…….+ an*Mn* Mn


## -1)


# x=(2*35*2)+(3*21*1)+(2*15*1)


# x=140+63+30


# x=233


## Step→05


## X=x mod M


# X=233 mod 105


## X=23


## Page 31: • 23 ≡ 2(mod 3)        23 ≡ 3(mod 5)      23 ≡ 2(mod 7)


# Verifying……


# • x ≡ 2(mod 3)


# x ≡ 3(mod 5)


# x ≡ 2(mod 7)


# Now as now value is 23 substitute that and check whether it is getting


# the remainder as given in the question or not.


# • 23 ≡ 2(mod 3)        23 ≡ 3(mod 5)      23 ≡ 2(mod 7)


### Figures on this page:

![[Module-02_p31_img1.png]]

![[Module-02_p31_img2.png]]

![[Module-02_p31_img3.png]]

![[Module-02_p31_img4.png]]

![[Module-02_p31_img5.png]]

![[Module-02_p31_img6.png]]

![[Module-02_p31_img7.png]]

![[Module-02_p31_img8.png]]

![[Module-02_p31_img9.png]]

![[Module-02_p31_img10.png]]

![[Module-02_p31_img11.png]]

![[Module-02_p31_img12.png]]

![[Module-02_p31_img13.png]]

![[Module-02_p31_img14.png]]

![[Module-02_p31_img15.png]]

![[Module-02_p31_img16.png]]

![[Module-02_p31_img17.png]]

![[Module-02_p31_img18.png]]

![[Module-02_p31_img19.png]]

![[Module-02_p31_img20.png]]

![[Module-02_p31_img21.png]]

![[Module-02_p31_img22.png]]

![[Module-02_p31_img23.png]]

![[Module-02_p31_img24.png]]

![[Module-02_p31_img25.png]]

![[Module-02_p31_img26.png]]

![[Module-02_p31_img27.png]]

![[Module-02_p31_img28.png]]

![[Module-02_p31_img29.png]]

![[Module-02_p31_img30.png]]

![[Module-02_p31_img31.png]]

![[Module-02_p31_img32.png]]

![[Module-02_p31_img33.png]]

![[Module-02_p31_img34.png]]

![[Module-02_p31_img35.png]]


## Page 32: x ≡ 1(mod 11)


# Practice questions:


# 1. x ≡ 8(mod 9)


# x ≡ 3(mod 20)


# 2. x ≡ 5(mod 3)


# x ≡ 2(mod 5)


# x ≡ 1(mod 11)


## Page 33: x ≡ 3(mod8).​


# 3.        x ​≡ 1(mod3)


# x ≡ 4(mod5)


# x ≡ 6(mod7)


# 4.


# x ​≡ 2(mod3)


# x ≡ 3(mod8).​


## Page 34: Binary Palindrome


# Binary Palindrome


## Page 35: 0, 1, 11, 101, 111, 1001, 1111, 10001, 10101, 11011, 11111, 100001, ...


# Binary Palindrome


# • Problem Statement:


# Take a number and convert it into binary form then check whether it is


# palindrome or not.


# Binary Palindrome Examples:


# 0, 1, 11, 101, 111, 1001, 1111, 10001, 10101, 11011, 11111, 100001, ...


## Page 36: }


## 1.


## public static boolean isbp(int n)


## 2.


## {


## 3.


## if(n==0)   return true;


## 4.


## int len=(int) (Math.log(n)/Math.log(2))+ 1;


## 5.


## int left=len-1, right=0;


## 6.


## while(left>right)


## 7.


## {  int l=(n>>left)&1;


## 8.


## int r=(n>>right)&1;


## 9.


## if(l!=r)


## 10.


## {


## 11.


## return false;


## 12.


## }


## 13.


## left--;


## 14.


## right++;


## 15.


## }


## 16.


## return true;


## 17.


## }


## Page 37: Strobogrammatic Number


## Strobogrammatic Number


## Page 38: iii. Different Digit


## Strobogrammatic Number:


# • It is a number that looks same when rotated 180 degrees.


# Examples : 69,96,101,88,181,609,916…..


# • When rotating digits it may falls under any of the following case:


# i.


# Invalid


# ii.


# Stays Same


# iii. Different Digit


## Page 39: • Strobogrammatic Number list is:-[1,0,8]


# If num.length()==1


# • Strobogrammatic Number list is:-[1,0,8]


### Figures on this page:

![[Module-02_p39_img1.png]]


## Page 40: • Strobogrammatic Number list is:-[1,0,8,6,9]


# If num.length()>1


# • Strobogrammatic Number list is:-[1,0,8,6,9]


### Figures on this page:

![[Module-02_p40_img1.png]]


## Page 41: 9  -------------------------->6


# • Logic is given below


# • If the


# left part  is                       right part   should contain


# 0 -------------------------->0


# 1 -------------------------->1


# 8  ------------------------->8


# 6 --------------------------> 9


# 9  -------------------------->6


## Page 42: }


# 1.


# public static boolean issn(String s)


# 2.


# {


# 3.


# int l=0,r=s.length()-1;


# 4.


# while(l<=r)


# 5.


# {


# 6.


# int a=s.charAt(l);


# 7.


# int b=s.charAt(r);


# 8.


# if(a=='0'&& b=='0' || a=='1' && b=='1' || a=='8' && b=='8' || a=='6' && b=='9' || a=='9' && b=='6')


# 9.


# {


# 10.


# l++;


# 11.


# r--;


# 12.


# }


# 13.


# else


# 14.


# return false;    }


# 15.


# return true;


# 16.


# }


# Source: Module-1.pptx


## Page 1: Module-01

# Module-01
Linked List , Stack , Queue

## Page 2: Data Structures

# Data Structures
Organized ways to store and manipulate data.
Real-Time Applications
Databases: Efficient storage and retrieval.
Operating Systems: Process scheduling.
Networking: Routing and data packet management.
Web Development: Session and state management.
AI/ML: Data preprocessing and model representation.

## Page 3



### Figures on this page:

![[Module-1_s3_img1.png]]


## Page 4: Arrays

# Arrays
Collection of elements of same type , identified by index.
Characteristics:
Fixed size.
Random access.
Homogeneous data.
Syntax
int[] numbers = {1, 2, 3, 4};System.out.println(numbers[2]);

## Page 5



### Figures on this page:

![[Module-1_s5_img1.png]]


## Page 6

Advantages:
Fast access.
Disadvantages:
Static size.
Insertion/Deletion is costly.

## Page 7: Linked Lists

# Linked Lists
Sequence of nodes, where each node points to the next.

### Figures on this page:

![[Module-1_s7_img1.png]]


## Page 8

Advantages:
Dynamic size.
Easier insertion/deletion.
Disadvantages:
No random access.
Higher memory usage.

## Page 9: Components of a Linked List

# Components of a Linked List
Node Structure:
Data: Holds the value.
Next: Pointer/reference to the next node.
Head:
Points to the first node.
Tail:
(Optional) Points to the last node.

## Page 10: Node Creation

# Node Creation
class Node
{
int data;
Node next;
Node(int data) {
this.data = data;
this.next = null;
}
}

## Page 11: Insertion in linked list

# Insertion in linked list
Insertion at the Beginning
Insert a Node after a Given Node in the Linked List
Insert a Node at the End of Linked List

## Page 12: Insertion in the beginning of the LL

# Insertion in the beginning of the LL
void insert(int data) {
Node newNode = new Node(data);
if (head == null) {
head = newNode;
}
else {
newnode.next=head;
head=newnode;
} }

## Page 13: Insertion at the end of the linkedlist

# Insertion at the end of the linkedlist
void insert(int data) {
Node newNode = new Node(data);
if (head == null) {
head = newNode;
} else {
Node temp = head;
while (temp.next != null) {
temp = temp.next;
}
temp.next = newNode;
}

## Page 14: Insertion at some position in a linkedlist

# Insertion at some position in a linkedlist
static void InsertatPositon(int value, int pos) {
ListNode newnode = new ListNode(value);
int len = Length();
if (pos <= len) {
if (pos == 0) {
newnode.next = head;
head = newnode;
}
else {
ListNode curr = head;
while (--pos != 0)
curr = curr.next;
newnode.next = curr.next;
curr.next = newnode;
}
} else
System.out.println("Invalid Positon");
}

## Page 15: Displaying Linked List

# Displaying Linked List
void display() {
Node temp = head;
while (temp != null) {
System.out.print(temp.data + " -> ");
temp = temp.next;
}
System.out.println("null");
}

## Page 16: Loop Detection

# Loop Detection
public static boolean detectloop()
{
Node5 slow=head;
Node5 fast=head;
while(fast!=null && fast.next!=null)
{
slow=slow.next;
fast=fast.next.next;
if(slow==fast)
return true;
}
return false;
}

## Page 17: Form Cycle

# Form Cycle
public static boolean formcycle(int a,int b)
{
int x=1;
Node5 p1=head;
Node5 p2=head;
while(p1.data!=a || x!=b)
{
if(p1.data!=a)
{
p1=p1.next;
if(p1.next==null)return false;
}
if(x!=b)
{
p2=p2.next;
x++;
} }
p2.next=p1;
return true;
}

## Page 18: Doubly Linked List

# Doubly Linked List
class Node {
int data;
Node next;
Node prev;
public Node(int data) {
this.data = data;
this.next = null;
this.prev = null;
}
}

## Page 19

public void addNode(int data) {
Node newNode = new Node(data);
if(head == null) {
head = tail = newNode;
head.previous = null;
tail.next = null;
}
else {
tail.next = newNode;
newNode.previous = tail;
tail = newNode;
tail.next = null;
}
}

## Page 20: Sort the bitonic DLL

# Sort the bitonic DLL
static Node sortBitonicDLL(Node head) {
if (head == null || head.next == null) return head;
Node last = head;
while (last.next != null) {
last = last.next;
}
Node front = head;
Node result = null;
Node tail = null;

## Page 21

while (front != null && last != null && front != last && last.next != front) {
Node newNode;
if (front.data < last.data) {
newNode = new Node(front.data);
front = front.next;
} else {
newNode = new Node(last.data);
last = last.prev;
}
if (result == null) {
result = newNode;
tail = result;
} else {
tail.next = newNode;
newNode.prev = tail;
tail = tail.next;
}
}

## Page 22

while (front != null && front != last.next) {
Node newNode = new Node(front.data);
front = front.next;
tail.next = newNode;
newNode.prev = tail;
tail = tail.next;
}
while (last != null && last.next != front) {
Node newNode = new Node(last.data);
last = last.prev;
tail.next = newNode;
newNode.prev = tail;
tail = tail.next;
}
return result;
}

## Page 23: Segregate even and odd nodes in a LL

# Segregate even and odd nodes in a LL
public static void segregate()
{
Node6 even_start=null;
Node6 even_end=null;
Node6 odd_start=null;
Node6 odd_end=null;
Node6 curr=head;
while(curr!=null)
{
int ele=curr.d;

## Page 24

if(ele%2==0)
{
if(even_start==null)
{
even_start=curr;
even_end=curr;
}
else
{
even_end.next=curr;
even_end=even_end.next;
}
}

## Page 25

else
{
if(odd_start==null)
{
odd_start=curr;
odd_end=curr;
}
else
{
odd_end.next=curr;
odd_end=odd_end.next;
}
}
curr=curr.next;
}

## Page 26

if(odd_start==null || even_start==null)
return;
even_end.next=odd_start;
odd_end.next=null;
head=even_start;
}

## Page 27: Merge Sort Principle

# Merge Sort Principle
Divide: Split the list into two halves using a mid node. The first half runs from the head to just before mid, and the second half starts at mid and runs to the end.
Recursively Sort: Apply MergeSort recursively on both halves.
Merge: Merge the two sorted halves into one sorted list and return the new head node.

## Page 28



### Figures on this page:

![[Module-1_s28_img1.jpg]]


## Page 29: Merge Sort for Doubly Linked List

# Merge Sort for Doubly Linked List
Input: 10 <-> 8 <-> 4 <-> 2
Output: 2 <-> 4 <-> 8 <-> 10
Input: 5 <-> 3 <-> 2
Output: 2 <-> 3 <-> 5

## Page 30

static Node MergeSort(Node head) {
if (head == null || head.next == null) {
return head;
}
Node second = split(head);
Node first = MergeSort(head);
second = MergeSort(second);
return merge(first, second);
}

## Page 31: Dividing

# Dividing
static Node split(Node head) {
Node fast = head,slow = head;
while (fast != null && fast.next != null  && fast.next.next != null) {
fast = fast.next.next;
slow = slow.next;
}
Node temp = slow.next;
slow.next = null;
if (temp != null) {
temp.prev = null;
}
return temp;
}

## Page 32: Merging

# Merging
static Node merge(Node first, Node second) {
if (first == null)     return second;
if (second == null)  return first;
if (first.data < =second.data) {
first.next = merge(first.next, second);
if (first.next != null) {
first.next.prev = first;
}
first.prev = null;
return first;
}
else {
second.next = merge(first, second.next);
if (second.next != null) {
second.next.prev = second;
}
second.prev = null;
return second;
}
}

## Page 33: Stack

# Stack
Stack is a linear data structure that follows LIFO (Last In First Out) Principle.
It means both insertion and deletion operations happen at one end only.
Basic Operations on Stack:
In order to make manipulations in a stack, there are certain operations provided to us.
push() to insert an element into the stack
pop() to remove an element from the stack
top() Returns the top element of the stack.
isEmpty() returns true if stack is empty else false.
isFull() returns true if the stack is full else false.

## Page 34



### Figures on this page:

![[Module-1_s34_img1.png]]


## Page 35: Types of Stack

# Types of Stack
Fixed Size Stack  (using arrays)
Dynamic Size Stack (using linkedlist)

## Page 36: Stack using arrays

# Stack using arrays
public class Stack_Array {
static int top=-1;
static int maxcap=1000;
static int arr[]=new int[maxcap];
static boolean isfull()
{
return top==maxcap-1;
}

## Page 37

static boolean isempty()
{
return top==-1;
}
static void push(int e)
{
if(isfull())
System.out.println("can't insert element");
else
arr[++top]=e;
}

## Page 38

static void pop()
{
if(isempty())
System.out.println("can't perform deletion");
else
top--;
}
static void top()
{
if(isempty())
System.out.println("No peek element");
else
System.out.println(arr[top]);
}

## Page 39

public static void display()
{
if(isempty())
System.out.println("no elements to display");
else
{
System.out.println("stack contains:");
for(int i=top;i>=0;i--)
{
System.out.print(arr[i]+" ");
}
}
}

## Page 40

public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
for(int i=0;i<n;i++)
{
int m=sc.nextInt();
push(m);
}
top();
pop();
display();
pop();
pop();
}}

## Page 41: Stack using LinkedList

# Stack using LinkedList
class Node3
{
int data;
Node3 next;
Node3(int e)
{
this.data=e;
this.next=null;
}
}

## Page 42

public class StackLL {
static Node3 top;
public static void push(int e)
{
Node3 newnode=new Node3(e);
if(top==null)
top=newnode;
else {
newnode.next=top;
top=newnode;
}
}

## Page 43

public static void pop()
{
if(top==null)
{
System.out.println(“No elements Stack Empty");
}
else
{
top=top.next;
}
}

## Page 44

public static void display()
{
Node3 temp=top;
while(temp!=null)
{
System.out.print(temp.data+" ");
temp=temp.next;
}
}

## Page 45

Scanner sc=new Scanner(System.in);
while(true)
{
int n=sc.nextInt();
if(n!=-1)
push(n);
else
break;
}
display();
}
}

## Page 46: Stack Implementation using Collections

# Stack Implementation using Collections
import java.util.Stack;
public class  Main{
public static void main(String[] args)
{
Stack<Integer> stk= new Stack<>();
boolean result = stk.empty();
System.out.println("Is the stack empty? " + result);
stk.push(78);
stk.push(113);
stk.push(90);
stk.push(120);
System.out.println("Elements in Stack: " + stk);
result = stk.empty();
System.out.println("Is the stack empty? " + result);
}
}

## Page 47: Minimum Stack

# Minimum Stack
Problem: Design and implement a stack that supports push(),pop(), top() and retrieving the minimum element in constant time.
Implement a  Stack class, which supports the following methods in O(1) time complexity.
void push() : Insert element onto the stack.
void pop() : Remove the top element from the stack.
int top() : Retrieve the top element in the stack.
int getmin() : Retrieve the minimum element in the stack.

## Page 48

public class Minimum_Stack
{
Stack<Integer> st;
Stack<Integer> mst;
Minimum_Stack()
{
st=new Stack<Integer>();
mst=new Stack<Integer>();
}

## Page 49

void getmin()
{
if(mst.isEmpty())
System.out.println("Stack is Empty");
else
System.out.println(mst.peek());
}
void peek()
{
if(st.isEmpty())
System.out.println("Stack is Empty");
else
System.out.println(st.peek());
}

## Page 50

void pop() {
int t=st.pop();
if(t==mst.peek())
mst.pop();
}

## Page 51

void push(int x)
{
if(st.isEmpty())
{
st.push(x);
mst.push(x);
}
else
{
st.push(x);
if(x<=mst.peek())
mst.push(x);
}
}

## Page 52

public static void main(String[] args) {
Minimum_Stack m=new Minimum_Stack();
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
for(int i=0;i<n;i++)
{
int l=sc.nextInt();
m.push(l);
}
m.getmin();
}
}

## Page 53: The Celebrity Problem

# The Celebrity Problem
Problem: You are given a number n, representing the number of people in a party.
A celebrity is defined as somebody who knows no other person but everybody else knows him.
Print the index of the celebrity in the party, and there is no celebrity, then print "none".
Example:
Consider a party of 4 people: with the array of strings as:
010100001101                                                               1110
In this scenario, the person with index 1 is the celebrity as everybody knows him but he does not know anybody else.
Hence, the answer will be 1.

## Page 54: Code

# Code
public class celebrity_problem
{
static boolean knows(int a,int b,int r[][])
{
return r[a][b]==1;
}

## Page 55

static int findcelebrity(int n,int m[][])
{
Stack<Integer> st=new Stack<>();
int c;
for(int i=0;i<n;i++)
st.push(i);
while(st.size()>1)
{
int a=st.pop();
int b=st.pop();
if(knows(a,b,m))
st.push(b);
else
st.push(a);
}

## Page 56

c=st.pop();
for(int i=0;i<n;i++)
{
if((i!=c) &&( knows(c,i,m) || !knows(i,c,m)))
return -1;
}
return c;
}

## Page 57: Tower of Hanoi

# Tower of Hanoi
Tower of Hanoi is a mathematical puzzle where we have three rods (A, B, and C) and N disks. Initially, all the disks are stacked in decreasing value of diameter i.e., the smallest disk is placed on the top and they are on rod A. The objective of the puzzle is to move the entire stack to another rod (here considered C), obeying the following simple rules:
Only one disk can be moved at a time.
Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack i.e. a disk can only be moved if it is the uppermost disk on a stack.
No disk may be placed on top of a smaller disk

## Page 58



### Figures on this page:

![[Module-1_s58_img1.gif]]


## Page 59: Examples

# Examples
Input: 2
Output: Disk 1 moved from A to BDisk 2 moved from A to CDisk 1 moved from B to C
Input: 3Output: Disk 1 moved from A to CDisk 2 moved from A to BDisk 1 moved from C to BDisk 3 moved from A to CDisk 1 moved from B to ADisk 2 moved from B to CDisk 1 moved from A to C

## Page 60: Algorithm

# Algorithm
Calculate the total number of moves required i.e. "pow(2, n) - 1" here n is number of disks.
2. If number of disks (i.e. n) is even then interchange destination pole and auxiliary pole.
3. for i = 1 to total number of moves:
if i%3 == 1:
legal movement of top disk between source pole and destination pole
if i%3 == 2:
legal movement top disk between source pole and auxiliary pole
if i%3 == 0:
legal movement top disk between auxiliary pole and destination pole

## Page 61

public class TowerOfHanoiUsingStack {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter the number of disks: ");
int numOfDisks = sc.nextInt();
if (numOfDisks <= 0)
System.out.println("The number of disks must be greater than 0.");
else
towerOfHanoi(numOfDisks);
}

## Page 62

public static void towerOfHanoi(int numOfDisks) {
Stack<Integer> source = new Stack<>();
Stack<Integer> auxiliary = new Stack<>();
Stack<Integer> destination = new Stack<>();
char s = 'S', a = 'A', d = 'D';
for (int i = numOfDisks; i >= 1; i--) {
source.push(i);
}
int totalMoves = (int) Math.pow(2, numOfDisks) - 1;
if (numOfDisks % 2 == 0) {
char temp = d;
d = a;
a = temp;
}

## Page 63

for (int i = 1; i <= totalMoves; i++) {
if (i % 3 == 1) {
moveDisks(source, destination, s, d);
} else if (i % 3 == 2) {
moveDisks(source, auxiliary, s, a);
} else if (i % 3 == 0) {
moveDisks(auxiliary, destination, a, d);
}
}
}

## Page 64

private static void moveDisks(Stack<Integer> source, Stack<Integer> destination, char s, char d) {
if (source.isEmpty()) {
int disk = destination.pop();
System.out.println("Move disk " + disk + " from " + d + " to " + s);
source.push(disk);
} else if (destination.isEmpty()) {
int disk = source.pop();
System.out.println("Move disk " + disk + " from " + s + " to " + d);
destination.push(disk);
}

## Page 65

else if (source.peek() > destination.peek()) {
int disk = destination.pop();
System.out.println("Move disk " + disk + " from " + d + " to " + s);
source.push(disk);
} else {
int disk = source.pop();
System.out.println("Move disk " + disk + " from " + s + " to " + d);
destination.push(disk);
}
}
}

## Page 66: Stock Span Problem

# Stock Span Problem
Problem Statement
The stock span problem is a financial problem where we have a series of N daily price quotes for a stock and we need to calculate the span of the stock's price for all N days.
The stock span problem can be solved efficiently using stack data structure.
The idea is to use a stack to maintain the prices in monotonically decreasing order. We will iterate over the price array and for each price we will find the price just greater than the current price, lying on the left side of the array.

## Page 67: Example

# Example
Input:
size = 6
arr[]={97,64,32,11,22,56}
Step 1: Traversing the given input span for 97 will be 1
Step 2: 64 is smaller than 97, so span will be 1
Step 3: 32 is smaller than 64 & 97, so span will be 1
Step 4: 11 is smaller than 97,64 & 32, so span will be 1
Step 5: 22 is greater than 11, so the span is 2
Step 6: 56 is greater than 32,11,22, so the span is 4

## Page 68: Stock Span Problem Code

# Stock Span Problem Code
public class StockSpanProblem {
public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
int a[]=new int[n];
for(int i=0;i<n;i++)
a[i]=sc.nextInt();
int s[]=new int[n];
stockspan(n,a,s);
printarr(s);
}

## Page 69

public static void stockspan(int n,int a[],int s[])
{
Stack<Integer> st=new Stack<>();
st.push(0);
s[0]=1;
for(int i=1;i<n;i++)
{
while(!st.isEmpty()&&a[st.peek()]<=a[i]) {
st.pop();
}
s[i]=(st.isEmpty()?(i+1):i-st.peek());
st.push(i);
}
}

## Page 70

public static void printarr(int s[])
{
System.out.println(Arrays.toString(s));
}
}//class closing

## Page 71: Stack Permutations

# Stack Permutations
You have been given two arrays having an equal number of elements. You have to find whether one array is the valid stack permutation of the other. An array is said to be a valid stack permutation of the other if and only if after applying some push and pop operations onto the sequence of elements in that array, will result in the other array.

## Page 72

Example:
Input:
arr1[] = [ 1, 2, 3 ]
arr2[] = [ 2, 1, 3 ]
Output:
YES

## Page 73: Code

# Code
public class StackPermutations {
public static boolean check(int x[],int y[],int n)
{
Stack<Integer> s=new Stack<Integer>();
int j=0;
for(int i=0;i<n;i++)
{
s.push(x[i]);
while(!s.isEmpty()&&y[j]==s.peek())
{
s.pop();
j++;
}
}
return s.isEmpty();
}

## Page 74

public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
int a[]=new int[n];
int b[]=new int[n];
for(int i=0;i<n;i++)
a[i]=sc.nextInt();
for(int j=0;j<n;j++)
b[j]=sc.nextInt();
if(check(a,b,n))
System.out.println("Yes");
else
System.out.println("No");
}}

## Page 75: Queue

# Queue
A Queue Data Structure is a fundamental concept in computer science used for storing and managing data in a specific order.
It follows the principle of "First in, First out" (FIFO), where the first element added to the queue is the first one to be removed.

## Page 76



### Figures on this page:

![[Module-1_s76_img1.png]]


## Page 77: Queue using Arrays

# Queue using Arrays
public class Queue_Array {
static int maxcap = 1000;
static int[] a = new int[maxcap];
static int rear = -1, front = -1;
public static boolean isempty() {
return front == -1;
}
public static boolean isfull() {
return rear == maxcap - 1;
}

## Page 78

public static void enqueue(int e) {
if (isfull()) {
System.out.println("Queue is full! Can't insert.");
} else {
if (front == -1) {
front = 0;
}
a[++rear] = e;
}}

## Page 79

public static void dequeue()
{
if (isempty())
{
System.out.println("Queue is empty! Can't delete.");
}
else
{
System.out.println("Dequeued: " + a[front]);
front++;
if (front > rear) {
front = -1;
rear = -1;
}}}

## Page 80

public static void display()
{
if (isempty()) {
System.out.println("No elements to display!");
}
else
{
System.out.print("Queue elements: ");
for (int i = front; i <= rear; i++) {
System.out.print(a[i] + " ");
}
}
}

## Page 81

public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter the number of elements to enqueue: ");
int n = sc.nextInt();
System.out.println("Enter the elements:");
for (int i = 0; i < n; i++) {
int m = sc.nextInt();
enqueue(m);
}
display();
System.out.println("Performing two dequeue operations...");
dequeue();
display();
}}

## Page 82: Queue using LinkedList

# Queue using LinkedList
class Node{
int data;
Node next;
Node(int v)
{
data=v;
next=null;
}
}

## Page 83

public class Queue_LinkedList {
static Node rear,front;
public static boolean isempty(){
return rear==null;
}
public static void enqueue(int ele){
Node newnode=new Node(ele);
if(rear==null)
front=rear=newnode;
else
{
rear.next=newnode;
rear=newnode;
}}

## Page 84

public static void dequeue(){
if(isempty())
System.out.println("no elements to delete");
else
front=front.next;
}
public static void display(){
Node temp=front;
while(temp!=rear)
{
System.out.print(temp.data+" -> ");
temp=temp.next;
}
System.out.println();
}

## Page 85

public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
for(int i=0;i<n;i++)
{
int ele=sc.nextInt();
enqueue(ele);
}
display();
dequeue();
dequeue();
display();
}}

## Page 86: Priority Queue

# Priority Queue
A priority queue is an abstract data type that behaves similarly to the normal queue except that each element has some priority, i.e., the element with the highest priority would come first in a priority queue.
The priority of the elements in a priority queue will determine the order in which elements are removed from the priority queue.
The priority queue supports only comparable elements, which means that the elements are either arranged in an ascending or descending order.
For example, suppose we have some values like 1, 3, 4, 8, 14, 22 inserted in a priority queue with an ordering imposed on the values is from least to the greatest. Therefore, the 1 number would be having the highest priority while 22 will be having the lowest priority.

## Page 87: Characteristics of a Priority queue

# Characteristics of a Priority queue
A priority queue is an extension of a queue that contains the following characteristics:
Every element in a priority queue has some priority associated with it.
An element with the higher priority will be deleted before the deletion of the lesser priority.
If two elements in a priority queue have the same priority, they will be arranged using the FIFO principle.

## Page 88

Priority Queue using DLL
Priority queue is  abstract data type which behave similar to the linear queue except that each element  has priority.
Example:
Hospital Emergency Queue
The patients will be treated  according to their medical condition. (i.e: Person in pain – High priority).

### Figures on this page:

![[Module-1_s88_img1.png]]


## Page 89

Priority Queue using DLL
Example:
Front
Deletion
Rear
Insertion
Priority
1
2
3
4
5
The priority of the elements in the priority queue will determine the
order of  removal of the data elements.

### Figures on this page:

![[Module-1_s89_img1.png]]


## Page 90

import java.util.*;
class Main {
static class Node {
int data;
int priority;
Node next, prev;
public Node(int data, int priority) {
this.data = data;
this.priority = priority;
}
}
private static Node head = null;
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22

### Figures on this page:

![[Module-1_s90_img1.png]]


## Page 91

private static void push(int data, int priority) {
if (head == null) {
Node newNode = new Node(data, priority);
head = newNode;
return;
}
Node node = new Node(data, priority);
Node temp = head, parent = null;
while (temp != null && temp.priority >= priority) {
parent = temp;
temp = temp.next;
}
if (parent == null) {
node.next = head;
head.prev = node;
head = node; }
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22

### Figures on this page:

![[Module-1_s91_img1.png]]


## Page 92

else if (temp == null) {
parent.next = node;
node.prev = parent;
}
else {
parent.next = node;
node.prev = parent;
node.next = temp;
temp.prev = node;
}
}
private static int peek() {
if (head != null) {
return head.data;
}
return -1; }
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22

### Figures on this page:

![[Module-1_s92_img1.png]]


## Page 93

private static int pop() {
if (head != null) {
int curr = head.data;
head = head.next;
if (head != null)
head.prev = null;
return curr;
}
return -1;
}
public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
for(int i=0;i<n;i++)
{
int data=sc.nextInt();
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22

### Figures on this page:

![[Module-1_s93_img1.png]]


## Page 94

int pri=sc.nextInt();
push(data, pri);
}
System.out.println(peek());
System.out.println(pop());
System.out.println(pop());
System.out.println(peek());
}
}
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22

### Figures on this page:

![[Module-1_s94_img1.png]]


## Page 95

Sort without extra space
Problem:
Sort the given queue without using any extra space
10
1
90
107
5

### Figures on this page:

![[Module-1_s95_img1.png]]


## Page 96: Code

# Code
import java.util.*;
public class Queue_Sort {
public static void main (String[] args)
{
Queue<Integer> q = new LinkedList<Integer>();
q.add(3);
q.add(1);
q.add(4);
q.add(2);
q.add(5);
sortQueue(q);
System.out.println(q);
}

## Page 97

public static void sortQueue(Queue<Integer> q)
{
for(int i = 1; i <= q.size(); i++)
{
int min_index = minInd(q,q.size() - i);
insertMinToRear(q, min_index);
}
}

## Page 98

public static int minInd(Queue<Integer> q, int sortIndex) {
int min_index = -1;
int min_value = Integer.MAX_VALUE;
int s = q.size();
for (int i = 0; i < s; i++) {
int current = q.poll();
if (current <= min_value && i <= sortIndex) {
min_index = i;
min_value = current;
}
q.add(current);
}
return min_index;
}

## Page 99

public static void insertMinToRear(Queue<Integer> q, int min_index)
{
int min_value = 0;
int s = q.size();
for (int i = 0; i < s; i++) {
int current = q.poll();
if (i != min_index)
q.add(current);
else
min_value = current;
}
q.add(min_value);
}
}

# Source: STS4005 CAT 01 CODES.docx


## Page 1

STS4005 CAT 01 CODES
Max Equilibrium Sum
Code
import java.util.*;
class Main{
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
int a[]=new int[n];
int suf=0;
for(int i=0;i<n;i++)
{
a[i]=sc.nextInt();
suf+=a[i];
}
int pre=0;
int max=Integer.MIN_VALUE;
for(int i=0;i<n;i++)
{
pre+=a[i];
if(pre==suf)
{
System.out.println(pre+" "+i);
max=Math.max(max,suf);
}
suf-=a[i];
}
System.out.println(max);
}
}
Leaders of an array
Code
import java.util.*;
class Main {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
int n = sc.nextInt();
int[] a = new int[n];
for (int i = 0; i < n; i++) {
a[i] = sc.nextInt();
}
int max = 0;
for (int i = n - 1; i >= 0; i--) {
if (a[i] > max) {
System.out.print(a[i] + " ");
max = a[i];
}
}
}
}
Majority Element(Moore’s Voting Algorithm)
Code
import java.util.*;
class Main{
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
int a[]=new int[n];
for(int i=0;i<n;i++)
{
a[i]=sc.nextInt();
}
int c=0;
int ele=0;
for(int i=0;i<n;i++)
{
if(c==0)
{
ele=a[i];
c=1;
}
else if(a[i]==ele)
{
c++;
}
else{
c--;
}
}
int nc=0;
for(int i=0;i<n;i++)
{
if(a[i]==ele)
{
nc++;
}
}
if(nc>a.length/2)
{
System.out.println(ele);
}
}
}
Quick Sort
Code
import java.util.*;
public class QuickSort
{
public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
int arr[] = new int[n];
quicksort(arr,0,n-1);
for(int i=0;i<n;i++)
{
System.out.print(arr[i]+"  ");
}
}
public static void quicksort(int arr[],int lb,int ub)
{
int loc;
if(lb<ub)
{
loc=partition(arr,lb,ub);
quicksort(arr,lb,loc-1);
quicksort(arr,loc+1,ub);
}
}
public static int partition(int arr[],int lb,int ub)
{
int pivot=arr[lb];
int start=lb;
int end=ub;
while(start<end)
{
while(arr[start]<=pivot && start<end)
{
start++;
}
while(arr[end]>pivot)
{
end--;
}
int temp=arr[start];
arr[start]=arr[end];
arr[end]=temp;
}
int temp=arr[lb];
arr[lb]=arr[end];
arr[end]=temp;
return end;
}
}
Selection Sort
Code
import java.util.*;
class Main{
public static void main(String ars[])
{
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
int a[]=new int[n];
for(int i=0;i<n;i++)
{
a[i]=sc.nextInt();
}
for(int i=0;i<n-1;i++)
{
int min=i;
int j=0;
for(j=i+1;j<n;j++)
{
if(a[j]<a[min])
{
min=j;
}
}
if(min!=i)
{
int temp=a[min];
a[min]=a[i];
a[i]=temp;
}
}
System.out.println(Arrays.toString(a));
}
}
Activity Selection Problem
Code
import java.util.*;
class ActivitySelection {
public static void printMaxActivities(int s[], int f[], int n) {
int i, j;
System.out.println("Following activities are selected");
i = 0;
System.out.print(i + " ");
for (j = 1; j < n; j++) {
if (s[j] >= f[i]) {
System.out.print(j + " ");
i = j;
}
}
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter the number of activities: ");
int n = sc.nextInt();
int s[] = new int[n];
int f[] = new int[n];
System.out.println("Enter the start times of activities:");
for (int i = 0; i < n; i++) {
s[i] = sc.nextInt();
}
System.out.println("Enter the finish times of activities:");
for (int i = 0; i < n; i++) {
f[i] = sc.nextInt();
}
printMaxActivities(s, f, n);
}
}
Sorted Unique Permutation
Code
import java.util.*;
public class Main {
static TreeSet<String> set = new TreeSet<>();
public static void swap(char[] a, int i, int j) {
char temp = a[i];
a[i] = a[j];
a[j] = temp;
}
public static void permutations(char[] a, int fi) {
if (fi == a.length - 1) {
set.add(new String(a));
return;
}
for (int i = fi; i < a.length; i++) {
swap(a, fi, i);
permutations(a, fi + 1);
swap(a, fi, i);
}
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
String s = sc.next();
char[] a = s.toCharArray();
permutations(a, 0);
for (String perm : set) {
System.out.println(perm);
}
}
}
Maze Solving
Code
import java.util.*;
public class Main
{
static boolean findpath(int m[][],int x,int y,int r,int c,int path[][])
{
if(x==r-1 && y==c-1 && m[x][y]==1)
{
path[x][y]=1;
return true;
}
if(x>=0 && x<r &&y>=0 && y<c &&m[x][y]==1)
{
path[x][y]=1;
if(findpath(m,x,y+1,r,c,path))
{
return true;
}
if(findpath(m,x+1,y,r,c,path))
{
return true;
}
path[x][y]=0;
}
return false;
}
public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
int r=sc.nextInt();
int c=sc.nextInt();
int m[][]=new int[r][c];
for(int i=0;i<r;i++)
{
for(int j=0;j<c;j++)
{
m[i][j]=sc.nextInt();
}
}
int path[][]=new int[r][c];
if(findpath(m,0,0,r,c,path))
{
for(int i=0;i<r;i++)
{
for(int j=0;j<c;j++)
{
System.out.print(path[i][j]+" ");
}
System.out.println();
}
}
else{
System.out.println("No");
}
}
}
Manuevering
Code
import java.util.*;
public class Main
{
public static int man(int r,int c,int row,int col)
{
if(r==row-1 || c==col-1)
{
return 1;
}
return man(r+1,c,row,col)+man(r,c+1,row,col);
}
public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
int r=sc.nextInt();
int c=sc.nextInt();
System.out.println(man(0,0,r,c));
}
}
Josephus trap
Code
import java.util.*;
class Main{
static int josh(int n,int k)
{
if(n==1)
{
return 0;
}
return (josh(n-1,k)+k)%n;
}
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
int k=sc.nextInt();
System.out.println(josh(n,k));//0 based indexing
System.out.println(josh(n,k)+1);//1 based indexing
}
}
Combination
Code
import java.util.*;
class Main{
static void com(int a[],int c[],int s,int e,int ind,int d)
{
if(ind==d)
{
for(int j=0;j<d;j++){
System.out.print(c[j]+" ");
}
System.out.println();
return;
}
for(int j=s;j<=e;j++)
{
c[ind]=a[j];
com(a,c,j+1,e,ind+1,d);
}
}
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
int a[]=new int[n];
for(int i=0;i<n;i++)
{
a[i]=sc.nextInt();
}
int d=sc.nextInt();
int c[]=new int[d];
com(a,c,0,n-1,0,d);
}
}

