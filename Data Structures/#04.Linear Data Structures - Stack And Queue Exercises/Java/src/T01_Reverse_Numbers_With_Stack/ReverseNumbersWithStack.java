package T01_Reverse_Numbers_With_Stack;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class ReverseNumbersWithStack {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Stack<Integer> passedNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(x-> !x.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(Stack::new));


        int numbersSize = passedNumbers.size();

        for (int i = 0; i < numbersSize; i++) {
            System.out.printf(Integer.toString(passedNumbers.pop())+ " ");
        }

    }
}
