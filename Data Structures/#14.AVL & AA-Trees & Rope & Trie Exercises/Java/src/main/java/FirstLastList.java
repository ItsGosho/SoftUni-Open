
import org.apache.commons.collections4.bag.TreeBag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {

    private LinkedList<T> elements;
    private TreeBag<T> elementsMin;
    private TreeBag<T> elementsMax;
    private int count;

    public FirstLastList() {
        this.elements = new LinkedList<>();
        this.elementsMin = new TreeBag<>((x1, x2) -> x1.compareTo(x2));
        this.elementsMax = new TreeBag<>((x1, x2) -> x2.compareTo(x1));

        this.count = 0;
    }

    @Override
    public void add(T element) {
        this.elements.add(element);
        this.elementsMin.add(element);
        this.elementsMax.add(element);
        this.count++;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public Iterable<T> first(int count) {
        if (this.count < count) {
            throw new IllegalArgumentException();
        }
        return this.getFromCollection(count, this.elements.iterator());
    }

    @Override
    public Iterable<T> last(int count) {
        if (this.count < count) {
            throw new IllegalArgumentException();
        }
        return this.getFromCollection(count, this.elements.descendingIterator());
    }

    @Override
    public Iterable<T> min(int count) {
        if (this.count < count) {
            throw new IllegalArgumentException();
        }
        return this.getFromCollection(count, this.elementsMin.iterator());
    }

    @Override
    public Iterable<T> max(int count) {
        if (this.count < count) {
            throw new IllegalArgumentException();
        }
        return this.getFromCollection(count, this.elementsMax.iterator());
    }

    private Iterable<T> getFromCollection(int count, Iterator<T> elements) {
        ArrayList<T> result = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger();
        elements.forEachRemaining(x -> {
            if (counter.get() == count) {
                return;
            }
            result.add(x);
            counter.getAndIncrement();
        });

        return result;
    }

    @Override
    public void clear() {
        this.elements.clear();
        this.elementsMin.clear();
        this.elementsMax.clear();
        this.count = 0;
    }

    @Override
    public int removeAll(T element) {
        Integer totalRemovedElements = this.elementsMin.getCount(element);

        this.elements.removeIf(x -> x.compareTo(element) == 0);
        this.elementsMin.removeIf(x -> x.compareTo(element) == 0);
        this.elementsMax.removeIf(x -> x.compareTo(element) == 0);

        this.count -= totalRemovedElements;
        return totalRemovedElements;
    }
}