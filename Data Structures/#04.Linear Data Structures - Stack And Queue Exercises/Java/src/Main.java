

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {

        Deque<Integer> items = new ArrayDeque<>();

        items.add(1);
        items.add(2);
        items.add(3);
        items.add(4);

        items.pop();

        System.out.println();
    }
}
