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

    public void removeAt(int index) {

        if(index >= this.size || index < 0){
            throw new IllegalArgumentException();
        }

        T[] newArray = (T[]) new Object[this.size];

        for (int i = 0; i < this.size-1; i++) {
            if (index != i) {
                newArray[i] = this.values[i];
            }
        }

        this.values = newArray;
        this.elementsCount--;
    }

    public int getCount(){
        return this.elementsCount;
    }

}
