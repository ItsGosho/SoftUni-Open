import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap;
    private int size;

    public BinaryHeap() {
        this.heap = new ArrayList<>();
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void insert(T element) {

        this.heap.add(element);

        int parentIndex = ((this.heap.size() - 1) - 1) / 2;
        this.heapifyUp(parentIndex);

        size++;

    }

    private void heapifyUp(int parentIndex) {
        T parentIndexElement = heap.get(parentIndex);
        int currentIndex = heap.size() - 1;
        T currentElement = heap.get(heap.size() - 1);

        while (parentIndexElement.compareTo(currentElement) < 0) {
            this.heap.set(parentIndex, currentElement);
            this.heap.set(currentIndex, parentIndexElement);

            currentIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
            parentIndexElement = this.heap.get(parentIndex);
        }

    }

    private void heapifyDown() {
        int elementIndex = 0;
        T element = this.heap.get(elementIndex);
        int elementLeftChildIndex = 2 * elementIndex + 1;
        T elementLeftChild = this.heap.get(elementLeftChildIndex);

        while (elementLeftChild != null &&  element.compareTo(elementLeftChild) < 0) {
            this.heap.set(elementIndex, elementLeftChild);
            this.heap.set(elementLeftChildIndex, element);

            elementIndex = elementLeftChildIndex;
            elementLeftChildIndex = 2 * elementIndex + 1;

            if (elementLeftChildIndex <= this.heap.size() - 1) {
                elementLeftChild = this.heap.get(elementLeftChildIndex);
            } else {
                break;
            }
        }

    }

    public T peek() {

        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        return this.heap.get(0);
    }

    public T pull() {

        if (this.size == 0) {
            throw new IllegalArgumentException();
        }


        T element = this.heap.get(0);

        this.heap.set(0, this.heap.get(this.size - 1));
        this.heap.set(this.size -1 , null);

        this.size--;
        if(this.size > 1){
            this.heapifyDown();
        }

        return element;
        //TODO: ako e samo edin da go opraq!!!
    }
}
