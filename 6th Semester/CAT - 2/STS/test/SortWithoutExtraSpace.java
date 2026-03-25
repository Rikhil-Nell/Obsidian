package test;
import java.util.*;

public class SortWithoutExtraSpace {
   static void sortStack(Stack<Integer> stack){
    if (stack.isEmpty()) return;

    int top = stack.pop();
    sortStack(stack);
    insertInSortedOrder(stack, top);

   }
   
   static void insertInSortedOrder(Stack<Integer> stack, int value){
    if(stack.isEmpty() || stack.peek() <= value){
            stack.push(value);
            return;    
    }

    int top = stack.pop();
    insertInSortedOrder(stack, value);

    }
}
int top = stack.pop();
    sortStack(stackSortWithoutExtraSpace);