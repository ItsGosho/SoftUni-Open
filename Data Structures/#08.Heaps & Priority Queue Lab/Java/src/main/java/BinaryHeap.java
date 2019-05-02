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

        while (parentIndexElement.compareTo(currentElement) < 0){
            this.heap.set(parentIndex,currentElement);
            this.heap.set(currentIndex,parentIndexElement);

            currentIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
            parentIndexElement = this.heap.get(parentIndex);
        }

    }

    public T peek() {

        if(this.size == 0){
            throw new IllegalArgumentException();
        }

        return this.heap.get(0);
    }

    public T pull() {
        throw new UnsupportedOperationException();
    }
}
