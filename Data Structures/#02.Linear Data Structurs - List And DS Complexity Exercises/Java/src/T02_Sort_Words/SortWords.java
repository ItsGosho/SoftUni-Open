package T02_Sort_Words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SortWords {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> passedWords = Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

        List<String> sortedWords = passedWords
                .stream()
                .sorted((x1,x2)->x1.compareTo(x2))
                .collect(Collectors.toList());

        System.out.println(String.join(" ",sortedWords));

    }
}
