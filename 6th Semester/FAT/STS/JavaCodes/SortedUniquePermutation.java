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
        for (String perm : set) System.out.println(perm);
    }
}
