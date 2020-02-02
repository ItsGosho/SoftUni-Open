package itsgosho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T02_Quick_Sort {
    public static void main(String[] args) {


        List<Integer> inputFirst = new ArrayList<Integer>() {{
            add(1);
            add(7);
            add(8);
            add(3);
            add(2);
            add(9);
            add(6);
        }};

        List<Integer> input = inputFirst;

        quickSort(inputFirst, 0, inputFirst.size() - 1);
    }

    /*
     * To achieve working with reference we will need the start and end of the array.
     * The original array will be passed
     * The arrayStart will be used as a moving point
     * The arrayEnd will be used as a moving point and pivot
     * */
    private static <T extends Comparable<T>> void quickSort(List<T> originalElements, int arrayStart, int arrayEnd) {

        int iPivot = arrayEnd;
        T pivot = originalElements.get(arrayEnd);

        while (arrayStart <= arrayEnd) {
            while (arrayStart <= arrayEnd) {
                T currentElement = originalElements.get(arrayStart);

                if (currentElement.compareTo(pivot) > 0) {
                    break;
                }

                arrayStart++;
            }

            while (arrayEnd >= arrayStart) {
                T currentElement = originalElements.get(arrayEnd);

                if (currentElement.compareTo(pivot) < 0) {
                    break;
                }

                arrayEnd--;
            }


            Collections.swap(originalElements, arrayStart, arrayEnd);

            arrayStart++;
            arrayEnd--;
        }

        Collections.swap(originalElements, arrayStart, iPivot);
    }
}
