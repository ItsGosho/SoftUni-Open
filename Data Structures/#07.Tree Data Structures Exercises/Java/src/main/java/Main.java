import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Tree<Integer> tree = createTreeFromInput();

        int result = tree.getDeepestNode().getValue();
        System.out.println("Deepest node: "+result);
    }

    private static Tree<Integer> createTreeFromInput() {
        Tree<Integer> tree = new Tree<>();
        Scanner scanner = new Scanner(System.in);
        int operationsCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < operationsCount - 1; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            int father = Integer.parseInt(input[0]);
            int value = Integer.parseInt(input[1]);

            tree.attachChildren(father, value);
        }

        return tree;
    }

}
