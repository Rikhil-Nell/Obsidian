import java.util.*;

public class IterativeTowerOfHanoi {
    static void moveDisk(Stack<Integer> from, Stack<Integer> to, char fromRod, char toRod) {
        int fromTop = from.isEmpty() ? Integer.MIN_VALUE : from.pop();
        int toTop = to.isEmpty() ? Integer.MIN_VALUE : to.pop();

        if (fromTop == Integer.MIN_VALUE) {
            from.push(toTop);
            System.out.println(toRod + " " + fromRod);
        } else if (toTop == Integer.MIN_VALUE) {
            to.push(fromTop);
            System.out.println(fromRod + " " + toRod);
        } else if (fromTop > toTop) {
            from.push(fromTop);
            from.push(toTop);
            System.out.println(toRod + " " + fromRod);
        } else {
            to.push(toTop);
            to.push(fromTop);
            System.out.println(fromRod + " " + toRod);
        }
    }

    static void solve(int n, char source, char auxiliary, char destination) {
        Stack<Integer> sourceRod = new Stack<>();
        Stack<Integer> auxiliaryRod = new Stack<>();
        Stack<Integer> destinationRod = new Stack<>();

        for (int i = n; i >= 1; i--) {
            sourceRod.push(i);
        }

        if (n % 2 == 0) {
            char temp = destination;
            destination = auxiliary;
            auxiliary = temp;
        }

        int totalMoves = (1 << n) - 1;
        System.out.println(totalMoves);

        for (int move = 1; move <= totalMoves; move++) {
            if (move % 3 == 1) {
                moveDisk(sourceRod, destinationRod, source, destination);
            } else if (move % 3 == 2) {
                moveDisk(sourceRod, auxiliaryRod, source, auxiliary);
            } else {
                moveDisk(auxiliaryRod, destinationRod, auxiliary, destination);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        solve(n, 'A', 'B', 'C');

        scanner.close();
    }
}
