import java.util.Iterator;
import java.util.LinkedList;

public class HashTableIterator<K, V> implements Iterator<KeyValue<K, V>> {

    private int currentIndex = 0;
    private int currentCollisionIndex = 0;
    private LinkedList<KeyValue<K, V>>[] elements;

    public HashTableIterator(LinkedList<KeyValue<K, V>>[] elements) {
        this.elements = elements;
    }

    @Override
    public boolean hasNext() {
        int currentIteration = currentIndex;

        if (currentIteration >= elements.length)
            return false;

        for (int i = currentIteration; i < elements.length; i++) {
            if (elements[currentIteration] != null && !elements[currentIteration].isEmpty())
                return true;
        }

        return false;
    }

    @Override
    public KeyValue<K, V> next() {
        if (this.currentIndex > elements.length)
            throw new IllegalArgumentException("There are no more elements!");

        while (elements[this.currentIndex] == null) {
            this.currentIndex++;

            if (this.currentIndex >= elements.length)
                throw new IllegalArgumentException("There are no more elements!");
        }

        if (this.currentCollisionIndex >= elements[this.currentIndex].size())
            this.currentIndex++;

        if (this.currentIndex >= elements.length)
            throw new IllegalArgumentException("There are no more elements!");

        LinkedList<KeyValue<K, V>> collisionElements = elements[currentIndex];
        KeyValue<K, V> keyValue = collisionElements.get(this.currentCollisionIndex);

        this.currentCollisionIndex++;

        if (this.currentCollisionIndex > elements[this.currentIndex].size() - 1) {
            this.currentCollisionIndex = 0;
            this.currentIndex++;
        }

        return keyValue;
    }
}
