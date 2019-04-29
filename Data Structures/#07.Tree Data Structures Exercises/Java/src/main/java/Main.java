import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Tree<Integer> tree = new Tree<>();

        Scanner scanner = new Scanner(System.in);
        int operationsCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < operationsCount - 1; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            int father = Integer.parseInt(input[0]);
            int value = Integer.parseInt(input[1]);

            tree.attachChildren(father,value);
        }
    }
}
