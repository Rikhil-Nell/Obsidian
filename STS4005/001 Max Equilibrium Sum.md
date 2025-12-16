
# Assignment 

**Print the Equilibrium Index for the input:**
```java
n = 5
arr = {2, -4, -2, 4, 2}
```

![[Pasted image 20251216211222.png]]

# Code

```java
import java.util.*;

class Main{

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int array[] = new int[n];
        int totalSum = 0;
        
        System.out.print("Enter " + n + " elements: ");
        for(int i = 0; i < n; i++)
        {
            array[i] = sc.nextInt();
            totalSum += array[i];
        }
        
        int maxEquilibriumSum = Integer.MIN_VALUE;
        int equilibriumIndex = -1;
        int prefixSum = 0;
        int suffixSum = totalSum;
        
        for(int i = 0; i < n; i++)
        {
            prefixSum += array[i];
            if(prefixSum == suffixSum)
            {
                if(prefixSum > maxEquilibriumSum)
                {
                    maxEquilibriumSum = prefixSum;
                    equilibriumIndex = i;
                }
            }
            suffixSum -= array[i];
        }
        
        sc.close();
        
        System.out.println("\n--- Result ---");
        
        if(equilibriumIndex != -1)
        {
            System.out.println("Maximum Equilibrium Sum: " + maxEquilibriumSum);
            System.out.println("Equilibrium Index: " + equilibriumIndex);
        }
        else
        {
            System.out.println("No equilibrium point found");
        }
    }
}
```

# Time and Space Complexities

Time Complexity: **O(n)**
Space Complexity: **O(n)**

