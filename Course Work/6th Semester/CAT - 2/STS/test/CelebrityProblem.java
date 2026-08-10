import java.util.*;
package test;
public class CelebrityProblem {
    static int findCelebrity(int [][] m){
        int n = m.length;
        int candidate = 0;

        for(int i = 1; i<n; i++){
            if (m[candidate][i] == 1)
                candidate = i;
        }

        for(int i = 1; i < n; i++){
            if(i != candidate){
                if(m[candidate][i] == 1 || m[i][candidate] == 0)
                    return -1;
            }
        }

        return candidate;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] m = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = sc.nextInt();
            }
        }

        System.out.println(findCelebrity(m));
        sc.close();
    }
}
