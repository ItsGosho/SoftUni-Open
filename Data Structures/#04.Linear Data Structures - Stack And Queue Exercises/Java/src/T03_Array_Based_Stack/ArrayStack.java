package T03_Array_Based_Stack;

public class ArrayStack<T> {

    private static final int INITIAL_CAPACITY = 16;
    private static final int INITIAL_SIZE = 0;

    private T[] elements;
    private int size;
    private int count;

    public ArrayStack() {
        this.count = INITIAL_SIZE;
        this.size = INITIAL_CAPACITY;
    }

    public ArrayStack(int capacity) {
        this();
        this.size = capacity;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(T element) {

        if (this.count == 0) {
            this.elements = (T[]) new Object[this.size];
            this.elements[this.count] = element;
            this.count++;
            return;
        }

        if (this.count == this.size) {
            this.grow();
        }

        this.elements[this.count] = element;
        this.count++;
    }

    public T pop() {

        if(this.count-1 < 0){
            throw new IllegalArgumentException();
        }

        T element = this.elements[this.count-1];
        this.elements[this.count-1] = null;

        return element;
    }

    public T[] toArray() {
        T[] resultArray = (T[]) new Object[this.count];

        this.fillArray(resultArray);

        return resultArray;
    }

    private void grow() {
        this.size = this.size * 2;
        T[] newArray = (T[]) new Object[this.size];

        this.fillArray(newArray);

        this.elements = newArray;
    }

    private void fillArray(T[] newArray){
        for (int i = 0; i < this.count; i++) {
            newArray[i] = this.elements[i];
        }
    }

}