import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class HashTable<K, V> implements Iterable<KeyValue<K, V>> {

    private static final int DEFAULT_CAPACITY = 100;
    private static final int GROWN_RATION = 2;

    private LinkedList<KeyValue<K, V>>[] elements;
    private int size;
    private int capacity;
    private int initialCapacity;
    private int totalUsedSlots;

    public HashTable() {
        this.size = 0;
        this.totalUsedSlots = 0;
        this.initialCapacity = DEFAULT_CAPACITY;
        this.capacity = DEFAULT_CAPACITY;
        this.elements = new LinkedList[this.capacity];
    }

    public HashTable(int capacity) {
        this();
        this.initialCapacity = capacity;
        this.capacity = capacity;
        this.elements = new LinkedList[this.capacity];
    }

    public void add(K key, V value) {
        int position = this.findSlotNumber(key);

        if (this.elements[position] == null)
            this.elements[position] = new LinkedList<>();

        if (this.elements[position].isEmpty())
            this.totalUsedSlots++;

        KeyValue<K, V> keyValue = new KeyValue<>(key, value);

        if (this.hasEqual(key))
            throw new IllegalArgumentException("Duplicate key!");

        this.elements[position].add(keyValue);
        this.size++;
        this.growIfNeeded();
    }

    private boolean hasEqual(K key) {
        int position = this.findSlotNumber(key);

        return this.elements[position].stream()
                .filter(x -> x.getKey().equals(key))
                .findFirst().orElse(null) != null;
    }

    private int findSlotNumber(K key) {
        int keyHashCode = key.hashCode();

        return Math.abs(keyHashCode) % this.capacity;
    }

    private void growIfNeeded() {
        double size = this.size;
        double capacity = this.capacity;
        double occupiedPercentage = (size / capacity) * 100;

        if (occupiedPercentage >= 75)
            this.grow();
    }

    private void grow() {
        this.capacity *= GROWN_RATION;
        this.size = 0;
        this.totalUsedSlots = 0;

        LinkedList<KeyValue<K, V>>[] grownElements = new LinkedList[this.capacity];

        for (LinkedList<KeyValue<K, V>> collisionElements : this.elements) {

            if (collisionElements == null)
                continue;

            for (KeyValue<K, V> collisionElement : collisionElements) {
                int position = this.findSlotNumber(collisionElement.getKey());

                if (grownElements[position] == null)
                    grownElements[position] = new LinkedList<>();

                if (grownElements[position].isEmpty())
                    this.totalUsedSlots++;

                grownElements[position].add(collisionElement);
                this.size++;
            }
        }

        this.elements = grownElements;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.elements.length;
    }

    public boolean addOrReplace(K key, V value) {
        this.growIfNeeded();

        int position = this.findSlotNumber(key);
        KeyValue<K, V> keyValue = new KeyValue<>(key, value);

        if (this.elements[position] == null)
            this.elements[position] = new LinkedList<>();

        if (this.elements[position].isEmpty()) {
            this.size++;
            this.elements[position] = new LinkedList<>();
            this.totalUsedSlots++;
            this.growIfNeeded();
            return this.elements[position].add(keyValue);
        }

        LinkedList<KeyValue<K, V>> collisionElements = this.elements[position];

        ListIterator<KeyValue<K, V>> listIterator = collisionElements.listIterator();

        while (listIterator.hasNext()) {
            KeyValue<K, V> iteratedKeyValue = listIterator.next();

            if (iteratedKeyValue.getKey().equals(key)) {
                listIterator.set(keyValue);
                return true;
            }
        }

        return collisionElements.add(keyValue);
    }

    public V get(K key) {
        KeyValue<K, V> keyValue = this.find(key);

        if (keyValue == null)
            throw new IllegalArgumentException("Element not found!");

        return keyValue.getValue();
    }

    public KeyValue<K, V> find(K key) {
        int position = this.findSlotNumber(key);

        if (this.elements[position] == null)
            return null;

        LinkedList<KeyValue<K, V>> collisionElements = this.elements[position];

        return collisionElements.stream()
                .filter(x -> x.getKey().equals(key))
                .findFirst().orElse(null);
    }

    public boolean containsKey(K key) {
        return this.find(key) != null;
    }

    public boolean remove(K key) {
        int position = this.findSlotNumber(key);

        if (this.elements[position] == null)
            return false;

        if (this.elements[position].isEmpty())
            return false;

        LinkedList<KeyValue<K, V>> collisionElements = this.elements[position];
        boolean isRemoved = collisionElements.removeIf(x -> x.getKey().equals(key));

        if (collisionElements.isEmpty())
            this.totalUsedSlots--;

        if (isRemoved)
            this.size--;

        return isRemoved;
    }

    public void clear() {
        this.size = 0;
        this.totalUsedSlots = 0;
        this.capacity = this.initialCapacity;
        this.elements = new LinkedList[this.capacity];
    }

    public Iterable<K> keys() {
        Iterator<KeyValue<K, V>> iterator = iterator();

        return () -> new Iterator<K>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public K next() {
                KeyValue<K, V> keyValue = iterator.next();

                return keyValue != null ? keyValue.getKey() : null;
            }
        };
    }

    public Iterable<V> values() {
        Iterator<KeyValue<K, V>> iterator = iterator();

        return () -> new Iterator<V>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public V next() {
                KeyValue<K, V> keyValue = iterator.next();

                return keyValue != null ? keyValue.getValue() : null;
            }
        };
    }

    @Override
    public Iterator<KeyValue<K, V>> iterator() {

        return new Iterator<KeyValue<K, V>>() {

            private int currentIndex = 0;
            private int currentCollisionIndex = 0;

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
        };
    }
}
