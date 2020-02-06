package itsgosho;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;

public class T01_Merge_Sort {

    public static void main(String[] args) {
        List<Integer> inputFirst = new ArrayList<Integer>() {{
            add(6);
            add(5);
        }};

        List<Integer> inputSecond = new ArrayList<Integer>() {{
            add(12);
            add(9);
            add(1);
            add(2);
            add(4);
            add(3);
            add(8);
            add(6);
            add(7);
            add(5);
            add(11);
            add(40);
        }};

        List<Double> inputMerge = new ArrayList<>();
        List<Double> inputStream = new ArrayList<>();

        for (int i = 0; i < 1_000_000; i++) {
            Double number = Math.random();

            inputMerge.add(number);
            inputStream.add(number);
        }

        /*FIRST TRY:* FAILED DUE TO WRONG GROUPING/
        /*                              0 1 2 3 4 5 6 7 8 9 10
                                        -----------------------
         * I. 0 to 0 with 1 to 1 ->     9 12 1 2 4 3 8 6 7 5 11
         * II. 2 to 2 with 3 to 3 ->    9 12 1 2 4 3 8 6 7 5 11
         * III. 4 to 4 with 5 to 5 ->   9 12 1 2 3 4 8 6 7 5 11
         * IV. 6 to 6 with 7 to 7 ->    9 12 1 2 3 4 6 8 7 5 11
         * V. 8 to 8 with 9 to 9 ->     9 12 1 2 3 4 6 8 5 7 11
         * VI. 10 to 10 with NOTHING -> 9 12 1 2 3 4 6 8 5 7 11
         * -------------------------------------------------------
         * I. 0 to 1 with 2 to 3 ->   1 2 9 12 3 4 6 8 5 7 11
         * II. 4 to 5 with 6 to 7 ->   1 2 9 12 3 4 6 8 5 7 11
         * III. 8 to 9 with 10 to 11 -> 1 2 9 12 3 4 6 8 5 7 11
         * -------------------------------------------------------
         * I. 0 to 2 with 3 to 5 ->  1 2 3 4 9 12 6 8 5 7 11
         * II. 6 to 8 with 9 to 11 -> 1 2 3 4 9 12 6 8 5 7 11
         * */

        /*
         * SECOND TRY: WORKING!
         * *INITIAL TWO-TWO SORT*
         *                                9 12 1 2 3 4 6 8 5 7 11
         * I. 0 to 1 with 2 to 3 ->       1 2 9 12 3 4 6 8 5 7 11
         * II. 4 to 5 with 6 to 7 ->      1 2 9 12 3 4 6 8 5 7 11
         * III. 8 to 9 with 10 to 11 ->   1 2 9 12 3 4 6 8 5 7 11
         * ------------------------------------------------------
         * I. 0 to 3 with 4 to 7 ->      1 2 3 4 6 8 9 12 5 7 11
         * II. 8 to 11 with NOTHING ->     1 2 3 4 6 8 9 12 5 7 11
         * ------------------------------------------------------
         * I. 0 to 7 with 8 to NOTHING ->     1 2 3 4 5 6 7 8 9 11 12
         * */

        /*THIRD TRY: WORKING*/
        /*                              0 1 2 3 4 5 6 7 8 9 10
                                        -----------------------
         * I. 0 to 0 with 1 to 1 ->     9 12 1 2 4 3 8 6 7 5 11
         * II. 2 to 2 with 3 to 3 ->    9 12 1 2 4 3 8 6 7 5 11
         * III. 4 to 4 with 5 to 5 ->   9 12 1 2 3 4 8 6 7 5 11  SCALING: 2
         * IV. 6 to 6 with 7 to 7 ->    9 12 1 2 3 4 6 8 7 5 11
         * V. 8 to 8 with 9 to 9 ->     9 12 1 2 3 4 6 8 5 7 11
         * VI. 10 to 10 with NOTHING -> 9 12 1 2 3 4 6 8 5 7 11
         * -------------------------------------------------------
         * I. 0 to 1 with 2 to 3 ->   1 2 9 12 3 4 6 8 5 7 11
         * II. 4 to 5 with 6 to 7 ->   1 2 9 12 3 4 6 8 5 7 11   SCALING: 4
         * III. 8 to 9 with 10 to 11 -> 1 2 9 12 3 4 6 8 5 7 11
         * -------------------------------------------------------
         * I. 0 to 3 with 4 to 7 ->  1 2 3 4 6 8 9 12 5 7 11
         * II. 8 to 11 with NOTHING->  1 2 3 4 6 8 9 12 5 7 11   SCALING: 8
         * -------------------------------------------------------
         * I. 0 to 7 with 8 to 11 ->  1 2 3 4 5 6 7 8 9 11 12
         //EV 16 to ....                                         SCALING: 18
         * */

        //Getting by two groups will always increase as 1 -> 2 -> 4 -> 8
        //Which will result in indexes as n - 1
        int scaling = 1;

        StopWatch stopWatch = StopWatch.createStarted();
        while (true) {
            scaling *= 2;

            int leftStart = 0;
            int leftEnd = (scaling / 2) - 1;
            int rightStart = leftEnd + 1;
            int rightEnd = (scaling - 1) < inputMerge.size() ? scaling - 1 : inputMerge.size() - 1;

            if (leftEnd > inputMerge.size() - 1) {
                break;
            }

            while (rightEnd < inputMerge.size()) {
                List<Double> leftElements = new ArrayList<>(inputMerge.subList(leftStart, leftEnd + 1));
                List<Double> rightElements = new ArrayList<>(inputMerge.subList(rightStart, rightEnd + 1));

                mergeReference(inputMerge, leftStart, leftElements, rightElements);

                leftStart += scaling;
                leftEnd += scaling;
                rightStart += scaling;
                rightEnd += scaling;
            }
        }

        //My +20/30ms of 1_000_000 elements ,compared to the integrated.
        stopWatch.stop();
        System.out.println("My Merge Sort: " + stopWatch.toString());
        stopWatch.reset();

        stopWatch.start();
        inputStream.sort((x1, x2) -> x2.compareTo(x1));
        stopWatch.stop();
        System.out.println("Java`s Sort: " + stopWatch.toString());
    }

    public static <T extends Comparable> void mergeReference(List<T> original, int leftStart, List<T> left, List<T> right) {
        int leftPosition = 0;
        int rightPosition = 0;
        int positioner = leftStart;

        while (leftPosition < left.size() && rightPosition < right.size()) {
            T leftElement = left.get(leftPosition);
            T rightElement = right.get(rightPosition);

            if (leftElement.compareTo(rightElement) <= 0) {
                original.set(positioner, leftElement);
                leftPosition++;
            } else if (leftElement.compareTo(rightElement) > 0) {
                original.set(positioner, rightElement);
                rightPosition++;
            }

            positioner++;
        }

        if (leftPosition < left.size()) {
            for (int i = leftPosition; i < left.size(); i++) {
                T leftElement = left.get(i);
                original.set(positioner, leftElement);
                positioner++;
            }
        } else if (rightPosition < right.size()) {
            for (int i = rightPosition; i < right.size(); i++) {
                T rightElement = right.get(i);
                original.set(positioner, rightElement);
                positioner++;
            }
        }
    }
}
