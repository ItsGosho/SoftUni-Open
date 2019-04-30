import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Tree<Integer> tree = createTreeFromInput(scanner);
        int sum = Integer.parseInt(scanner.nextLine());
        System.out.println(String.format("Subtrees of sum %d:", sum));

        List<Tree<Integer>> test = tree.getAllSubTrees();

        for (Tree<Integer> item : test) {
            int sumOfTrees = item.getSumOfTrees();

            if(sumOfTrees == sum){
                System.out.println(String.join(" ",item.printPreOrder().stream().map(Object::toString).collect(Collectors.toList())));
            }
        }

    }

    private static Tree<Integer> createTreeFromInput(Scanner scanner) {
        Tree<Integer> tree = new Tree<>();
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
