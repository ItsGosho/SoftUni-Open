package T06_Reversed_List;

public class ReversedList<T> {

    private int capacity;
    private int elementsCount;
    private T[] values;

    public ReversedList() {
        this.capacity = 2;
        this.elementsCount = 0;
        this.values = (T[]) new Object[this.capacity];
    }

    public void add(T element) {

        if (this.elementsCount >= this.capacity) {
            this.increaseCapacity();
        }

        this.values[this.elementsCount] = element;
        this.elementsCount++;
    }

    private void increaseCapacity() {
        T[] newArray = (T[]) new Object[this.capacity * 2];

        for (int i = 0; i < this.capacity; i++) {
            newArray[i] = this.values[i];
        }

        this.values = newArray;
        this.capacity *= 2;
    }

    public void removeAt(int index) {

        if(index >= this.capacity || index < 0){
            throw new IllegalArgumentException();
        }

        T[] newArray = (T[]) new Object[this.capacity];

        for (int i = 0; i < this.capacity-1; i++) {
            if (index != i) {
                newArray[i] = this.values[i];
            }
        }

        this.values = newArray;
        this.elementsCount--;
    }

    public T get(int index){
        index = this.elementsCount - index - 1;
        return this.values[index];
    }

    public int count(){
        return this.elementsCount;
    }

    public int capacity(){
        return this.capacity;
    }
}
