package T04_Linked_Stack;

public class LinkedStackMain {
    public static void main(String[] args) {

        LinkedStack<Integer> linkedStack = new LinkedStack<>();

        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        linkedStack.push(4);
        linkedStack.push(5);
        linkedStack.pop();

        Object[] toArr = linkedStack.toArray();
        System.out.println();

    }
}
