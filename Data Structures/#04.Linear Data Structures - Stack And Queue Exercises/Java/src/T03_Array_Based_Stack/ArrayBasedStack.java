package T03_Array_Based_Stack;

public class ArrayBasedStack {
    public static void main(String[] args) {

        ArrayStack<Integer> arrayStack = new ArrayStack<>(3);

        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.pop();
        System.out.println();

    }
}
