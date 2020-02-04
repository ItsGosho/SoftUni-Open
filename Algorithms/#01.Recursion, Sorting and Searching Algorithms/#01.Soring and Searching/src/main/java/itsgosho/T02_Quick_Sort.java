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

        List<Integer> inputSecond = new ArrayList<Integer>() {{
            add(1);
            add(3);
            add(2);
        }};

        List<Integer> inputThird = new ArrayList<Integer>() {{
            add(3);
            add(8);
            add(1);
            add(7);
            add(6);
            add(2);
            add(5);
            add(4);
            add(9);
        }};

        List<Integer> input = inputFirst;

        quickSort(input);
        System.out.println();
    }

    private static <T extends Comparable<T>> void quickSort(List<T> elements) {
        //initial sort
        int pivotPosition = quickSort(0, elements.size() - 1, elements);
        test(0, pivotPosition - 1, elements);
        test(pivotPosition + 1, elements.size() - 1, elements);
    }

    private static <T extends Comparable<T>> void test(int start, int end, List<T> elements) {

        if (start >= end || end >= elements.size()) {
            return;
        }

        int pivotPosition = quickSort(start, end, elements);

        test(0, pivotPosition - 1, elements);
        test(pivotPosition + 1, end, elements);
    }

    private static <T extends Comparable<T>> int quickSort(int start, int end, List<T> elements) {

        proceedPivot(start, end, elements);

        int iPivot = end;
        T pivot = elements.get(end);
        boolean isFound = false;

        while (start <= end) {

            while (start <= end) {
                T currentElement = elements.get(start);

                if (currentElement.compareTo(pivot) > 0) {
                    isFound = true;
                    break;
                }

                start++;
            }

            while (end >= start) {
                T currentElement = elements.get(end);

                if (currentElement.compareTo(pivot) < 0) {
                    isFound = true;
                    break;
                }

                end--;
            }

            if (start <= end) {
                Collections.swap(elements, start, end);

                start++;
                end--;
            } else {
                break;
            }
        }

        if (isFound) {
            Collections.swap(elements, start, iPivot);
            iPivot = start;
        }

        return iPivot;
    }

    //Directly under quickSort
    private static <T extends Comparable<T>> void proceedPivot(int start, int end, List<T> elements) {
        int pivotIndex = start + (Math.max(start, end) - Math.min(start, end)) / 2;
        Collections.swap(elements, pivotIndex, end);
    }
}
