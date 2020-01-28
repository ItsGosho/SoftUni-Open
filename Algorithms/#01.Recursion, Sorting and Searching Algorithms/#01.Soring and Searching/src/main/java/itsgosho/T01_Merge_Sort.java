package itsgosho;

import java.util.ArrayList;
import java.util.List;

public class T01_Merge_Sort {

    public static void main(String[] args) {
        List<Integer> inputFirst = new ArrayList<Integer>() {{
            add(5);
            add(4);
            add(3);
            add(2);
            add(1);
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
        }};

        List<Integer> input = inputFirst;

        int subtractFirstIndex = 0;
        int subtractSecondIndex = 1;

        while (true) {

            List<Integer> firstGroupElements = input.subList(subtractFirstIndex, subtractSecondIndex);
            List<Integer> secondGroupElements = input.subList(subtractFirstIndex, subtractSecondIndex);

            subtractFirstIndex += 2;
            subtractSecondIndex += 2;
            break;
        }

        List<Integer> test = merge(new ArrayList<Integer>() {{
            add(1);
        }}, new ArrayList<Integer>() {{
            add(2);
            add(3);
        }});

        System.out.println();
    }

    public static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();

        int leftPosition = 0;
        int rightPosition = 0;
        int max = Math.max(left.size(), right.size());

        while (leftPosition < left.size() && rightPosition < right.size()) {
            Integer leftElement = left.get(leftPosition);
            Integer rightElement = right.get(rightPosition);

            if (leftElement.compareTo(rightElement) <= 0) {
                result.add(leftElement);
                leftPosition++;
            } else if (leftElement.compareTo(rightElement) > 0) {
                result.add(rightElement);
                rightPosition++;
            }
        }

        if (leftPosition < left.size()) {
            for (int i = leftPosition; i < left.size(); i++) {
                Integer leftElement = left.get(i);
                result.add(leftElement);
            }
        } else if (rightPosition < right.size()) {
            for (int i = rightPosition; i < right.size(); i++) {
                Integer rightElement = right.get(i);
                result.add(rightElement);
            }
        }

        return result;
    }

   /* public static void start(List<Integer> left, List<Integer> right, List<Integer> leftSort, List<Integer> rightSort) {
        System.out.println();

        List<Integer> leftFirstHalf = left.subList(0, Math.round((left.size() + 1) / 2));
        List<Integer> leftSecondHalf = left.subList(Math.round((left.size() + 1) / 2), left.size());

        if (leftSecondHalf.size() != 0) {
            start(leftFirstHalf, leftSecondHalf, leftSort, rightSort);
        } else {

            return;
        }

        List<Integer> rightFirstHalf = right.subList(0, Math.round((right.size() + 1) / 2));
        List<Integer> rightSecondHalf = rig


        ht.subList(Math.round((right.size() + 1) / 2), right.size());

        if (rightSecondHalf.size() != 0) {
            start(rightFirstHalf, rightSecondHalf, leftSort, rightSort);
        } else {

            *//*SORT*//*
            return;
        }
    }*/

}
