package T01_Sum_And_Average;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SumAndAverage {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        List<Integer> passedNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(x-> !x.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int sum = passedNumbers.stream().mapToInt(Integer::intValue).sum();
        double average = passedNumbers.stream().mapToInt(Integer::intValue).average().getAsDouble();

        System.out.println(String.format("Sum=%d; Average=%.2f", sum, average));

    }
}
