package test;
import java.util.*;

public class IterativeTowerOfHanoi {
    static void moveDisk(Stack<Integer> from, Stack<Integer> to, char fromRod, char toRod){
        int fromTop = from.isEmpty() ? Integer.MIN_VALUE : from.pop();
        int toTop = to.isEmpty() ? Integer.MIN_VALUE : to.pop();

        if(fromTop == Integer.MIN_VALUE){
            from.push(toTop);
            System.out.println(toRod + " " + fromRod);
        } else if(toTop == Integer.MIN_VALUE){
            to.push(fromTop);
            System.out.println(fromRod + " " + toRod);
        } else if(fromTop > toTop)

    }
}
