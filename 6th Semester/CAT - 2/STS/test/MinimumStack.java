package test;
import java.util.*;

public class MinimumStack {
    static class MinStack{
        Stack<Integer> valueStack = new Stack<>();
        Stack<Integer> minimumStack = new Stack<>();
        
        void push(int value){
            valueStack.push(value);
            if (minimumStack.isEmpty() || value <= minimumStack.peek())
                minimumStack.push(value);
        }

        int pop() {
            if(valueStack.isEmpty()) return -1;
            int removed = valueStack.pop();
            if(removed == minimumStack.peek()) minimumStack.pop();
            return removed;
        }

        int top(){
            if(valueStack.isEmpty()) return -1;
            return valueStack.peek();

        }

        
        int getMin() {
            if(minimumStack.isEmpty()) return -1;
            return minimumStack.peek();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MinStack minStack = new MinStack();

        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            String command = scanner.next();

            if (command.equals("push")) {
                int value = scanner.nextInt();
                minStack.push(value);
            } else if (command.equals("pop")) {
                System.out.println(minStack.pop());
            } else if (command.equals("top")) {
                System.out.println(minStack.top());
            } else if (command.equals("getMin")) {
                System.out.println(minStack.getMin());
            }
        }

        scanner.close();
    }
}
