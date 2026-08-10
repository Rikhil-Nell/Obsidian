```
Name: Rikhil Nellimarla 
Registration Number: 23BEC7030
```

# 1.     Kth Smallest element

[https://www.hackerrank.com/contests/algorithms-2-1/challenges/iiitsalgoq1](https://www.hackerrank.com/contests/algorithms-2-1/challenges/iiitsalgoq1)

Only available in C/C++

# 2.     Find Equilibrium Point

 [https://www.codechef.com/practice/course/tcs-nqt-questions/TCSNQTC/problems/TCSNQTCP09](https://www.codechef.com/practice/course/tcs-nqt-questions/TCSNQTC/problems/TCSNQTCP09)

```java
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int T = fs.nextInt();
        while (T-- > 0) {
            int N = fs.nextInt();
            int[] nums = new int[N];

            long totalSum = 0;
            for (int i = 0; i < N; i++) {
                nums[i] = fs.nextInt();
                totalSum += nums[i];
            }

            long leftSum = 0;
            int answer = -1;

            for (int i = 0; i < N; i++) {
                long rightSum = totalSum - leftSum - nums[i];
                if (leftSum == rightSum) {
                    answer = i;
                    break; // leftmost equilibrium index
                }
                leftSum += nums[i];
            }

            out.append(answer).append('\n');
        }

        System.out.print(out.toString());
    }

    // Fast input reader (essential for CodeChef)
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}

```

## Output:

![[Pasted image 20260108081335.png]]

# 3.  Backtracking - Find Unique Permutations

[https://www.codechef.com/practice/course/flipkart-interview-questions/FLIPKARTPREP/problems/PREP24](https://www.codechef.com/practice/course/flipkart-interview-questions/FLIPKARTPREP/problems/PREP24)


```java
import java.io.*;
import java.util.*;

class Main {

    static List<int[]> result;
    static int[] arr;
    static boolean[] used;
    static int n;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int T = fs.nextInt();
        while (T-- > 0) {
            n = fs.nextInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = fs.nextInt();
            }

            Arrays.sort(arr); // crucial for lexicographic order
            used = new boolean[n];
            result = new ArrayList<>();

            backtrack(new int[n], 0);

            out.append(result.size()).append('\n');
            for (int[] perm : result) {
                for (int x : perm) {
                    out.append(x).append(' ');
                }
                out.append('\n');
            }
        }

        System.out.print(out.toString());
    }

    static void backtrack(int[] curr, int idx) {
        if (idx == n) {
            result.add(curr.clone());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;

            // skip duplicates
            if (i > 0 && arr[i] == arr[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            curr[idx] = arr[i];
            backtrack(curr, idx + 1);
            used[i] = false;
        }
    }

    // Fast input (mandatory for CodeChef)
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}

```

## Output:
![[Pasted image 20260108081508.png]]
# 4.     Mutated Minions

[https://www.codechef.com/problems/CHN15A?tab=statement](https://www.codechef.com/problems/CHN15A?tab=statement)

```java
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();
        while (T-- > 0) {
            int N = fs.nextInt();
            int K = fs.nextInt();
            int count = 0;

            for (int i = 0; i < N; i++) {
                int val = fs.nextInt();
                if ((val + K) % 7 == 0) {
                    count++;
                }
            }
            sb.append(count).append('\n');
        }
        System.out.print(sb.toString());
    }

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}

```

## Output:
![[Pasted image 20260108081647.png]]

# 5.     Sums in a Triangle

[https://www.codechef.com/problems/SUMTRIAN](https://www.codechef.com/problems/SUMTRIAN)

```java
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();
        while (T-- > 0) {
            int N = fs.nextInt();
            int[][] dp = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= i; j++) {
                    dp[i][j] = fs.nextInt();
                }
            }

            for (int i = N - 2; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    dp[i][j] += Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
                }
            }

            sb.append(dp[0][0]).append('\n');
        }

        System.out.print(sb.toString());
    }

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}

```

## Output:
![[Pasted image 20260108081906.png]]
# 6.    Dominant Army

[https://www.codechef.com/problems/DOMINANT](https://www.codechef.com/problems/DOMINANT)

```java
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();
        while (T-- > 0) {
            int NA = fs.nextInt();
            int NB = fs.nextInt();
            int NC = fs.nextInt();

            if (NA > NB + NC || NB > NA + NC || NC > NA + NB) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.print(sb.toString());
    }

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}

```

## Output:
![[Pasted image 20260108082142.png]]

# 7.     Most Frequent Element

[https://www.hackerrank.com/contests/bits-hyderabad-practice-test-1/challenges/most-frequent-element](https://www.hackerrank.com/contests/bits-hyderabad-practice-test-1/challenges/most-frequent-element)

```java
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        HashMap<Long, Integer> freq = new HashMap<>();
        for (int i = 0; i < N; i++) {
            long x = Long.parseLong(br.readLine().trim());
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        long answer = Long.MAX_VALUE;
        int maxFreq = 0;

        for (Map.Entry<Long, Integer> entry : freq.entrySet()) {
            long key = entry.getKey();
            int value = entry.getValue();

            if (value > maxFreq || (value == maxFreq && key < answer)) {
                maxFreq = value;
                answer = key;
            }
        }

        System.out.println(answer);
    }
}

```

## Output:
![[Pasted image 20260108082225.png]]

# 8.     Quicksort In-Place

[https://www.hackerrank.com/challenges/quicksort3/problem](https://www.hackerrank.com/challenges/quicksort3/problem)

```java
import java.io.*;
import java.util.*;

public class Solution {

    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(0, n - 1);
    }

    static void quickSort(int low, int high) {
        if (low >= high) return;

        int p = partition(low, high);
        printArray();

        quickSort(low, p - 1);
        quickSort(p + 1, high);
    }

    static int partition(int low, int high) {
        int pivot = arr[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                swap(i, j);
                i++;
            }
        }
        swap(i, high);
        return i;
    }

    static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void printArray() {
        StringBuilder sb = new StringBuilder();
        for (int x : arr) {
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}

```

## Output:
![[Pasted image 20260108082449.png]]

# 9.     Maneuvering a Cave

[https://www.codechef.com/practice/course/tcs-interview-questions/CODEVITA2020/problems/CAVEPATH](https://www.codechef.com/practice/course/tcs-interview-questions/CODEVITA2020/problems/CAVEPATH)


```java
import java.io.*;
import java.util.*;

class Main {
    static final long MOD = 1000000007L;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();
        while (T-- > 0) {
            int N = fs.nextInt();
            int M = fs.nextInt();

            long[][] dp = new long[N][M];

            for (int i = 0; i < N; i++) {
                dp[i][0] = 1;
            }
            for (int j = 0; j < M; j++) {
                dp[0][j] = 1;
            }

            for (int i = 1; i < N; i++) {
                for (int j = 1; j < M; j++) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                }
            }

            sb.append(dp[N - 1][M - 1]).append('\n');
        }

        System.out.print(sb.toString());
    }

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}

```

## Output:

![[Pasted image 20260108082655.png]]
# 10.     Tram ride

https://www.hackerearth.com/practice/data-structures/arrays/1-d/practice-problems/algorithm/tram-ride-d7ff3a92/?purpose=login&source=problem-page&update=google



```java
static long solve(int N, int start, int finish, int[] Ticket_cost){

        if (start == finish) return 0;

  

        start--;  // convert to 0-based

        finish--;

  

        long clockwise = 0;

        int i = start;

        while (i != finish) {

            clockwise += Ticket_cost[i];

            i = (i + 1) % N;

        }

  

        long anticlockwise = 0;

        i = start;

        while (i != finish) {

            i = (i - 1 + N) % N;

            anticlockwise += Ticket_cost[i];

        }

  

        return Math.min(clockwise, anticlockwise);

    }

}
```

## Output:

![[Pasted image 20260108083029.png]]
