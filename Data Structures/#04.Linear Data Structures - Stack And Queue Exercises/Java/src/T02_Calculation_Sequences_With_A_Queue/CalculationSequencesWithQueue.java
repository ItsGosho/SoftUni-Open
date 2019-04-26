package T02_Calculation_Sequences_With_A_Queue;

import java.util.*;
import java.util.stream.Collectors;

public class CalculationSequencesWithQueue {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Queue<Integer> members = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        members.add(n);
        result.add(n);

        while (result.size() <= 50) {

            int S = members.poll();

            int formulaOneResult = (S + 1);
            int formulaTwoResult = ((2 * S) + 1);
            int formulaThreeResult = (S + 2);

            members.add(formulaOneResult);
            members.add(formulaTwoResult);
            members.add(formulaThreeResult);
            result.add(formulaOneResult);
            result.add(formulaTwoResult);
            result.add(formulaThreeResult);
        }

        System.out.println(String.join(", ",result.stream().limit(50).map(x->Integer.toString(x)).collect(Collectors.toList())));
    }
}
