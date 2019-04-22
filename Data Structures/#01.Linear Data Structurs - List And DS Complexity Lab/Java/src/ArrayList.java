public class ArrayList<T> {

    private int size;
    private int elementsCount;
    private T[] values;

    public ArrayList() {
        this.elementsCount = 0;
        this.size = 2;
        this.values = (T[]) new Object[this.size];
    }

    public void add(T element) {

        if (this.elementsCount >= this.size) {
            this.increaseCapacity();
        }

        this.values[this.elementsCount] = element;
        this.elementsCount++;
    }

    private void increaseCapacity() {
        T[] newArray = (T[]) new Object[this.size * 2];

        for (int i = 0; i < this.size; i++) {
            newArray[i] = this.values[i];
        }

        this.values = newArray;
        this.size *= 2;
    }

    public T removeAt(int index) {

        if (index >= this.size || index < 0) {
            throw new IllegalArgumentException();
        }

        T removedElement = this.values[index];
        T[] newArray = (T[]) new Object[this.size];

        this.makeCopyWithoutSpecificIndex(newArray, index);
        this.values = newArray;
        this.elementsCount--;
        this.shrink();

        return removedElement;
    }

    private void shrink() {

        if (this.size > 2) {
            int newSize = this.elementsCount <= this.size / 2 ? this.size / 2 : this.size;
            T[] newArray = (T[]) new Object[newSize];

            for (int i = 0; i < newSize; i++) {
                newArray[i] = this.values[i];
            }

            this.values = newArray;
            this.size = newSize;
        }

    }

    private void makeCopyWithoutSpecificIndex(T[] arrayToFill, int index) {

        int newArrayCounter = 0;
        int oldArrayCounter = 0;
        while (newArrayCounter != this.elementsCount - 1) {

            if (oldArrayCounter != index) {
                arrayToFill[newArrayCounter] = this.values[oldArrayCounter];
                newArrayCounter++;
            }

            oldArrayCounter++;
        }
    }

    public T get(int index) {

        if (index >= this.elementsCount) {
            throw new IndexOutOfBoundsException();
        }

        return this.values[index];
    }

    public int getCount() {
        return this.elementsCount;
    }

}
