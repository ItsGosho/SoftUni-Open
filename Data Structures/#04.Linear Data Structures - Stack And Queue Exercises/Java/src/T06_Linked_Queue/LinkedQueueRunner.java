package T06_Linked_Queue;

import java.util.LinkedList;
import java.util.List;

public class LinkedQueueRunner {
    public static void main(String[] args) {

        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();

        linkedQueue.enqueue(1);
        linkedQueue.enqueue(2);
        linkedQueue.enqueue(3);
        linkedQueue.enqueue(4);
        linkedQueue.enqueue(5);

        linkedQueue.toArray();

        System.out.println();

    }
}
