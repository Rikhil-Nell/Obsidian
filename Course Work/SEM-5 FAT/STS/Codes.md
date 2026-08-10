>[!example] Rikhil Nellimarla (23BEC7030)

## BinaryPalindrome.java
```java
public class BinaryPalindrome{

    public static boolean isBinaryPalindrome(int n){
        String binary = Integer.toBinaryString(n);
        String reversed = new StringBuilder(binary).reverse().toString();
        return binary.equals(reversed);
    }

    public static void main(String[] args) {
        System.out.println(isBinaryPalindrome(5));
    }
}
```

## BlockSwap.java
```java
import java.util.*;

class BlockSwap {
    static void rotateArr(Integer[] arr, int d) {
        int n = arr.length;
        d = d % n;
        if (d == 0) return;
        
        List<Integer> list = Arrays.asList(arr);
        Collections.rotate(list, -d);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        Integer[] arr = new Integer[n];
        
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        System.out.print("Enter rotation distance: ");
        int d = sc.nextInt();
        
        rotateArr(arr, d);
        
        System.out.print("Rotated array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
```

## Booth.java
```java
public class Booth {
    
    public static int boothMultiplication(int m, int r) {
        int A = m;          // Multiplicand
        int Q = r;          // Multiplier
        int Q_1 = 0;        // Extra bit
        int count = 32;     // Number of bits (for int)
        int product = 0;
        
        while (count > 0) {
            // Check last two bits (Q0 and Q-1)
            int Q0 = Q & 1;
            
            if (Q0 == 1 && Q_1 == 0) {
                // 10: Subtract A
                product -= A;
            } else if (Q0 == 0 && Q_1 == 1) {
                // 01: Add A
                product += A;
            }
            // 00 or 11: Do nothing
            
            // Arithmetic right shift
            Q_1 = Q0;
            Q = Q >> 1;
            A = A << 1;
            
            count--;
        }
        
        return product;
    }
    
    public static void main(String[] args) {
        System.out.println(boothMultiplication(155, -900));      // 21
        System.out.println(boothMultiplication(-7, 3));     // -21
        System.out.println(boothMultiplication(7, -3));     // -21
        System.out.println(boothMultiplication(-7, -3));    // 21
    }
}
```

## ChineseRemainder.java
```java
public class ChineseRemainder{

    public static int modInverse(int a, int m){

        for(int x = 1; x < m; x++){

            if((a * x) % m == 1){

                return x;

            }
        }
        return 1;
    }

    public static int crt(int[] rem, int[] mod){

        int M = 1;

        for(int i = 0; i < mod.length; i++){
            M *= mod[i];
        }

        int result = 0;

        for(int i = 0; i < mod.length; i++){

            int Mi = M / mod[i];

            int yi = modInverse(Mi, mod[i]);

            result += rem[i] * Mi * yi;

        }

        return result % M;
    }

    public static void main(String[] args) {
        int rem[] = {2, 3, 1};
        int mod[] = {3, 4, 5};
        System.out.println("x is " + crt(rem, mod));
    }
}
```

## Euclid.java
```java
public class Euclid {
    
    public static int gcd(int a, int b) {
        // Base case: if b is 0, gcd is a
        if (b == 0) {
            return a;
        }
        // Recursive case: gcd(a, b) = gcd(b, a % b)
        return gcd(b, a % b);
    }
    
    public static void main(String[] args) {
        System.out.println(gcd(48, 18));  // 6
        System.out.println(gcd(56, 98));  // 14
        System.out.println(gcd(101, 103)); // 1
        System.out.println(gcd(270, 192)); // 6
    }
}
```

## Euler.java
```java
public class Euler{
    public static int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        return gcd(b, a % b);
    }

    public static int etf(int n){
        int result = 1;

        for(int i = 2; i <= n; i++){
            if(gcd(i, n) == 1){
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(etf(60));
    }
}
```

## Karatsuba.java
```java
public class Karatsuba {
    
    public static int karatsuba(int x, int y) {
        // Split numbers into high and low parts
        int half = Math.max(Integer.toString(x).length(), Integer.toString(y).length()) / 2;
        int highX = x / (int) Math.pow(10, half);
        int lowX = x % (int) Math.pow(10, half);
        int highY = y / (int) Math.pow(10, half);
        int lowY = y % (int) Math.pow(10, half);
        
        // Compute the three products
        int z0 = lowX * lowY;
        int z2 = highX * highY;
        int z1 = (lowX + highX) * (lowY + highY) - z2 - z0;
        
        // Combine the results
        return (int) (z2 * Math.pow(10, 2 * half) + z1 * Math.pow(10, half) + z0);
    }
    
    public static void main(String[] args) {
        System.out.println(karatsuba(1234, 5678));  // 7006652
        System.out.println(karatsuba(12, 34));      // 408
        System.out.println(karatsuba(123, 456));    // 56088
    }
}
```

## LongestSequence.java
```java
public class LongestSequence {
    
    public static int longestSequenceOfOnes(int n){
        if (n == 0){
            return 1;
        }

        int maxLength = 0;
        int currentLength = 0;
        int previousLength = 0;

        while(n != 0){
            if ((n & 1) == 1){
                currentLength++;
            }
            else{
                previousLength = (n & 2) == 0 ? 0 : currentLength;
                currentLength = 0;
            }
            maxLength = Math.max(maxLength, previousLength + currentLength + 1);
            n >>= 1;
        }
        return maxLength;
    }
    
    public static void main(String[] args) {
        System.out.println(longestSequenceOfOnes(1775));  // 8 (binary: 11011101111)
        System.out.println(longestSequenceOfOnes(15));    // 5 (binary: 1111)
        System.out.println(longestSequenceOfOnes(0));     // 1 (binary: 0)
        System.out.println(longestSequenceOfOnes(1));     // 2 (binary: 1)
    }
}
```

## Manacher.java
```java
import java.util.*;

public class Manacher {
    
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        
        // Step 1: Transform string - add '#' between characters
        // "abc" becomes "#a#b#c#"
        StringBuilder transformed = new StringBuilder("#");
        for (char c : s.toCharArray()) {
            transformed.append(c).append("#");
        }
        String t = transformed.toString();
        
        // Step 2: Array to store palindrome radius at each position
        int[] radius = new int[t.length()];
        int center = 0;      // Center of rightmost palindrome
        int right = 0;       // Right boundary of rightmost palindrome
        int maxLen = 0;      // Maximum palindrome length found
        int maxCenter = 0;   // Center of maximum palindrome
        
        // Step 3: Expand around each center
        for (int i = 0; i < t.length(); i++) {
            // Mirror position of i with respect to center
            int mirror = 2 * center - i;
            
            // Use previously computed values if within right boundary
            if (i < right) {
                radius[i] = Math.min(right - i, radius[mirror]);
            }
            
            // Try to expand palindrome centered at i
            int left = i - (radius[i] + 1);
            int rightPos = i + (radius[i] + 1);
            
            while (left >= 0 && rightPos < t.length() && 
                   t.charAt(left) == t.charAt(rightPos)) {
                radius[i]++;
                left--;
                rightPos++;
            }
            
            // Update center and right if we expanded past right boundary
            if (i + radius[i] > right) {
                center = i;
                right = i + radius[i];
            }
            
            // Track maximum palindrome
            if (radius[i] > maxLen) {
                maxLen = radius[i];
                maxCenter = i;
            }
        }
        
        // Step 4: Extract the longest palindrome from original string
        int start = (maxCenter - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        
        String result = longestPalindrome(input);
        System.out.println("Longest palindromic substring: \"" + result + "\"");
        System.out.println("Length: " + result.length());
    }
}
```

## MaxProductSubArray.java
```java
import java.util.*;
import java.util.Scanner;

public class MaxProductSubArray {
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        
        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }
            
            maxProd = Math.max(nums[i], maxProd * nums[i]);
            minProd = Math.min(nums[i], minProd * nums[i]);
            
            result = Math.max(result, maxProd);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] nums = new int[n];
        
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        
        System.out.println("Maximum product of subarray: " + maxProduct(nums));
    }
}
```

## MaxSumOfHourglass.java
```java
import java.io.*;
import java.util.*;

class MaxSumOfHourglass {
    static int findMaxSum(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        
        if (rows < 3 || cols < 3) {
            throw new IllegalArgumentException("Matrix must be at least 3x3");
        }
        
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                int sum = mat[i][j] + mat[i][j + 1] + mat[i][j + 2]
                        + mat[i + 1][j + 1]
                        + mat[i + 2][j] + mat[i + 2][j + 1] + mat[i + 2][j + 2];
                
                maxSum = Math.max(maxSum, sum);
            }
        }
        
        return maxSum;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();
        
        int[][] mat = new int[rows][cols];
        System.out.println("Enter matrix elements:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        
        System.out.println("Maximum hourglass sum: " + findMaxSum(mat));
    }
}
```

## MoveCharToStart.java
```java
import java.util.*;

class MoveCharToStart {
    static void moveChar(char[] str) {
        int i = str.length - 1;
        
        for (int j = i; j >= 0; j--) {
            if (str[j] != ' ') {
                str[i] = str[j];
                i--;
            }
        }
        
        while (i >= 0) {
            str[i] = ' ';
            i--;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        char[] str = input.toCharArray();
        
        moveChar(str);
        
        System.out.println("String with spaces moved to beginning: \"" + String.valueOf(str) + "\"");
    }
}
```

## SegmentedSieve.java
```java
import java.util.*;

public class SegmentedSieve {
    
    public static void segSieve(int l, int h) {
        // Regular sieve up to h
        boolean[] prime = new boolean[h + 1];
        Arrays.fill(prime, true);
        
        prime[0] = prime[1] = false;
        
        for(int p = 2; p * p <= h; p++) {
            if(prime[p]) {
                for(int i = p * p; i <= h; i += p) {
                    prime[i] = false;
                }
            }
        }
        
        // Only print primes in range [l, h]
        System.out.print("Primes between " + l + " and " + h + ": ");
        for(int i = l; i <= h; i++) {
            if(prime[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        segSieve(10, 30);
        segSieve(100, 150);
    }
}
```

## Sieve.java
```java
import java.util.*;

public class Sieve{

    public static void sieve(int num){

        boolean[] prime = new boolean[num + 1];

        Arrays.fill(prime, true);

        for (int i = 2; i*i <= num; i++){
            if (prime[i]){
                for (int j = i*i; j <= num; j += i){
                    prime[j] = false;
                }
            }
        }

        for (int k = 2; k <= num; k++){
            if (prime[k]){
                System.out.println(k + " ");
            }
        }
    }

    public static void main(String[] args) {
        sieve(100);
    }
}
```

## Strobogrammatic.java
```java
public class Strobogrammatic {

    public static boolean isStrobogrammatic(String n){
        String pairs = "00 11 88 69 96";

        int left = 0, right = n.length() - 1;

        while(left <= right){
            String pair = "" + n.charAt(left) + n.charAt(right);
            if (!pairs.contains(pair)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isStrobogrammatic("69"));    // true
        System.out.println(isStrobogrammatic("88"));    // true
        System.out.println(isStrobogrammatic("818"));   // true
        System.out.println(isStrobogrammatic("962"));   // false
        System.out.println(isStrobogrammatic("1691"));  // true
    }
}
```

## TwoNibbles.java
```java
public class TwoNibbles {
    
    public static int swapNibbles(int n) {
        return ((n & 0x0F) << 4) | ((n & 0xF0) >> 4);
    }
    
    public static void main(String[] args) {
        System.out.println(swapNibbles(100));  // 70
        System.out.println(swapNibbles(129));  // 24
        System.out.println(swapNibbles(240));  // 15
        System.out.println(swapNibbles(15));   // 240
    }
}
```

## WeightedSubstring.java
```java
import java.util.*;

class WeightedSubstring {
    static int distinctSubString(String P, String Q, int K) {
        int N = P.length();
        HashSet<String> validSubstrings = new HashSet<>();
        
        for (int i = 0; i < N; i++) {
            int weightSum = 0;
            StringBuilder substring = new StringBuilder();
            
            for (int j = i; j < N; j++) {
                char currentChar = P.charAt(j);
                int charIndex = currentChar - 'a';
                weightSum += (Q.charAt(charIndex) - '0');
                substring.append(currentChar);
                
                if (weightSum > K) {
                    break;
                }
                
                validSubstrings.add(substring.toString());
            }
        }
        
        return validSubstrings.size();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string P: ");
        String P = scanner.nextLine();
        System.out.print("Enter string Q (26 digits for a-z weights): ");
        String Q = scanner.nextLine();
        System.out.print("Enter maximum weight K: ");
        int K = scanner.nextInt();
        
        System.out.println("Number of distinct substrings with weight <= " + K + ": " + distinctSubString(P, Q, K));
    }
}
```
