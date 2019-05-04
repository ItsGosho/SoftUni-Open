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

    //MAX HEAP
    private void heapifyUp(int parentIndex) {
        T parentIndexElement = heap.get(parentIndex);
        int currentIndex = heap.size() - 1;
        T currentElement = heap.get(currentIndex);

        while (parentIndexElement.compareTo(currentElement) > 0) {
            this.heap.set(parentIndex, currentElement);
            this.heap.set(currentIndex, parentIndexElement);

            currentIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
            parentIndexElement = this.heap.get(parentIndex);
        }

    }

    //MAX HEAP
    private void heapifyDown(int elementIndex) {
        T element = this.heap.get(elementIndex);

        while (true) {
            boolean changes = false;
            T leftChild = leftChild(elementIndex);
            T rightChild = rightChild(elementIndex);

            if (leftChild != null)
                if (this.isHighestThan(element, leftChild)) {
                    int leftChildIndex = ((2 * elementIndex) + 1);

                    this.swap(elementIndex, leftChildIndex);

                    elementIndex = leftChildIndex;
                    leftChild = leftChild(elementIndex);
                    rightChild = rightChild(elementIndex);
                    changes = true;
                }

            if (rightChild != null)
                if (this.isHighestThan(element, rightChild)) {
                    int rightChildIndex = ((2 * elementIndex) + 2);

                    this.swap(elementIndex, rightChildIndex);

                    elementIndex = rightChildIndex;
                    leftChild = leftChild(elementIndex);
                    rightChild = rightChild(elementIndex);
                    changes = true;
                }


            if (!changes) {
                break;
            }
        }

    }

    private void swap(int index1, int index2) {
        T index1Element = this.heap.get(index1);
        T index2Element = this.heap.get(index2);

        this.heap.set(index1, index2Element);
        this.heap.set(index2, index1Element);
    }

    private boolean isHighestThan(T element1, T element2) {
        return element1.compareTo(element2) > 0;
    }

    private T leftChild(int elementIndex) {
        int index = ((2 * elementIndex) + 1);
        return index > this.heap.size() - 1 ? null : this.heap.get(index);
    }

    private T rightChild(int elementIndex) {
        int index = ((2 * elementIndex) + 2);
        return index > this.heap.size() - 1 ? null : this.heap.get(index);
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
        this.heap.remove(this.size -1);

        this.size--;
        if (this.size > 1) {
            this.heapifyDown(0);
        }

        return element;
    }
}
