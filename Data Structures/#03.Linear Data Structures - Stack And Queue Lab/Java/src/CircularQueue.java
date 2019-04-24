public class CircularQueue<E> {

    private int size;
    private int capacity;
    private int head;
    private int tail;
    private E[] circularQueue;

    public CircularQueue() {
        this.capacity = 16;
        this.head = 0;
        this.tail = 0;
        this.circularQueue = (E[]) new Object[this.capacity];
    }

    public CircularQueue(int initialCapacity) {
        this();
        this.capacity = initialCapacity;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {

        if (this.size == 0) {

            this.circularQueue[this.tail] = element;

            this.size++;
            return;
        }

        this.tail++;

        if (this.size == this.capacity) {
            this.grow();
        }

        if (this.tail == this.capacity) {
            this.tail = 0;
        }

        if (this.tail == this.head) {
            this.grow();
        }

        this.circularQueue[this.tail] = element;
        this.size++;
    }

    public E dequeue() {

        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        E element = this.circularQueue[this.head];
        this.circularQueue[this.head] = null;
        this.head++;
        this.size--;

        return element;
    }

    private void grow() {

        E[] newArray = (E[]) new Object[this.capacity * 2];

        this.copyAllTo(newArray);

        this.capacity = this.capacity * 2;
        this.circularQueue = newArray;

    }

    private void copyAllTo(E[] array) {

        if (this.head >= this.tail) {

            int newArrayPosCounter = 0;

            for (int i = this.head; i < this.size; i++) {
                array[newArrayPosCounter] = this.circularQueue[i];
                newArrayPosCounter++;
            }

            for (int i = 0; i < this.head; i++) {
                array[newArrayPosCounter] = this.circularQueue[i];
                newArrayPosCounter++;
            }
            this.head = 0;
            this.tail = this.size;

        } else {

            int newArrayPosCounter = 0;
            for (int i = this.head; i < this.size; i++) {

                array[newArrayPosCounter] = this.circularQueue[i];
                newArrayPosCounter++;
            }
        }
    }

    public E[] toArray() {
        E[] result = (E[]) new Object[this.size];
        this.copyAllTo(result);

        return result;
    }

}
