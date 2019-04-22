package T05_Count_Of_Occurrences;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CountOfOccurrences {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Integer> passedNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(x-> !x.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        //TODO:!
    }
}
