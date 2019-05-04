public class Heap<T extends Comparable<T>> {

    public static <T extends Comparable<T>> void sort(T[] array) {

        if(array.length == 0){
            return;
        }

        for (int i = array.length / 2; i >= 0; i--) {
            heapifyDown(array, i);
        }

        Object[] sortedArr = new Object[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            sortedArr[i] = array[i];
            array[i] = null;
            heapifyDown(array, 0);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = (T) sortedArr[i];
        }
    }

    private static <T extends Comparable<T>> void heapifyDown(T[] array, int elementIndex) {
        T element = array[elementIndex];

        while (true) {
            boolean changes = false;
            T leftChild = leftChild(array, elementIndex);
            T rightChild = rightChild(array, elementIndex);

            if (leftChild != null)
                if (isHigherThan(element, leftChild)) {
                    int leftChildIndex = ((2 * elementIndex) + 1);

                    swap(array, elementIndex, leftChildIndex);

                    elementIndex = leftChildIndex;
                    leftChild = leftChild(array, elementIndex);
                    rightChild = rightChild(array, elementIndex);
                    changes = true;
                }

            if (rightChild != null)
                if (isHigherThan(element, rightChild)) {
                    int rightChildIndex = ((2 * elementIndex) + 2);

                    swap(array, elementIndex, rightChildIndex);

                    elementIndex = rightChildIndex;
                    leftChild = leftChild(array, elementIndex);
                    rightChild = rightChild(array, elementIndex);
                    changes = true;
                }

            if (!changes) {
                break;
            }
        }

    }

    private static <T extends Comparable<T>> void swap(T[] array, int index1, int index2) {
        T index1Element = array[index1];
        T index2Element = array[index2];

        array[index1] = index2Element;
        array[index2] = index1Element;
    }

    private static <T extends Comparable<T>> boolean isHigherThan(T element1, T element2) {
        return element1.compareTo(element2) < 0;
    }

    private static <T extends Comparable<T>> T leftChild(T[] array, int elementIndex) {
        int index = ((2 * elementIndex) + 1);
        return index > array.length - 1 ? null : array[index];
    }

    private static <T extends Comparable<T>> T rightChild(T[] array, int elementIndex) {
        int index = ((2 * elementIndex) + 2);
        return index > array.length - 1 ? null : array[index];
    }


}
