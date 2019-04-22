package T04_RemoveOddOccurrences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RemoveOddOccurrences {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Integer> passedNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(x -> !x.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        //1 2 3 4 5 3 6 7 6 7 6
        List<Integer> copyOfPassedNumbers = new ArrayList<>(passedNumbers);
        for (int k = 0; k < copyOfPassedNumbers.size(); k++) {

            int number = copyOfPassedNumbers.get(k);
            int occurrences = 0;

            for (int i = 0; i < copyOfPassedNumbers.size(); i++) {
                if (number == copyOfPassedNumbers.get(i)) {
                    occurrences++;
                }
            }

            if (occurrences % 2 != 0){
                passedNumbers = passedNumbers.stream().filter(x -> x != number).collect(Collectors.toList());
            }

        }

        for (int i = 0; i < passedNumbers.size(); i++) {
            System.out.print(passedNumbers.get(i)+" ");
        }

    }
}
