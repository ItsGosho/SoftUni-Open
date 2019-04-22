package T03_Longest_Subsequence;

import java.util.*;
import java.util.stream.Collectors;

public class LongestSubsequence {
    public static void main(String[] args) {

        //2 2 2 3 3 3 -> 2 2 2
        //4 4 5 5 5 -> 5 5 5
        //1 2 3 -> 1
        //0 -> 0
        //4 2 3 4 4 -> 4 4
        Scanner scanner = new Scanner(System.in);
        List<Integer> passedNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(x -> !x.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        int currentNumber = 0;
        int currentCount = 0;

        int highestNumber = 0;
        int highestCount = 0;

        //12 2 7 4 3 3 8 -> 3 3
        for (int i = 1; i < passedNumbers.size(); i++) {

            int current = passedNumbers.get(i);
            int previous = passedNumbers.get(i-1);

            if(current == previous){
                currentNumber = current;
                currentCount++;
            }else{
                if(currentCount > highestCount){
                    highestNumber = currentNumber;
                    highestCount = currentCount;
                }
                currentNumber = 0;
                currentCount = 0;
            }
        }

        if(currentCount > highestCount){
            highestCount = currentCount;
            highestNumber = currentNumber;
        }

        if(highestCount == 0){
            highestNumber = passedNumbers.get(0);
        }

        for (int i = 0; i < highestCount+1; i++) {
            System.out.print(highestNumber+" ");
        }

    }
}
