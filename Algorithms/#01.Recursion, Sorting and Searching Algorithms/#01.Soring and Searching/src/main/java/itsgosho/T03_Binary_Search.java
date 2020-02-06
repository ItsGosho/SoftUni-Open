package itsgosho;

import org.apache.commons.lang3.time.StopWatch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class T03_Binary_Search {

    private static final String FOUND_MESSAGE = "Element [%s] found at index [%s]";
    private static final String NOT_FOUND_MESSAGE = "Element not found!";

    public static void main(String[] args) {
        List<BigDecimal> numbers = new ArrayList<>();

        for (int i = 0; i <= 10_000_000; i++) {
            numbers.add(BigDecimal.valueOf(i));
        }

        StopWatch stopWatch = StopWatch.createStarted();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).compareTo(BigDecimal.valueOf(10_000_000)) == 0) {
                stopWatch.stop();
                System.out.println(stopWatch.toString());
                break;
            }
        }

        stopWatch.reset();
        stopWatch.start();
        BigDecimal searchedElement = BigDecimal.valueOf(9_000_000);
        int index = findElement(numbers, searchedElement);
        stopWatch.stop();
        System.out.println(stopWatch.toString());

        if (index > -1) {
            System.out.println(String.format(FOUND_MESSAGE, searchedElement.toString(), index));
        } else {
            System.out.println(NOT_FOUND_MESSAGE);
        }

        //Tested on i7-4710HQ - Linear 53ms , Binary < 0ms
    }

    private static <T extends Comparable<T>> int findElement(List<T> elements, T element) {
        return findElement(elements, element, 0, elements.size() - 1);
    }

    private static <T extends Comparable<T>> int findElement(List<T> elements, T element, int start, int end) {
        int pivotIndex = start + (Math.max(start, end) - Math.min(start, end)) / 2;

        if (pivotIndex > end) {
            return -1;
        }

        T currentElement = elements.get(pivotIndex);

        if (currentElement.compareTo(element) == 0) {
            return pivotIndex;
        } else if (currentElement.compareTo(element) > 0) {
            //left
            return findElement(elements, element, start, pivotIndex - 1);
        } else if (currentElement.compareTo(element) < 0) {
            //right
            return findElement(elements, element, pivotIndex + 1, end);
        }

        return pivotIndex;
    }

}
