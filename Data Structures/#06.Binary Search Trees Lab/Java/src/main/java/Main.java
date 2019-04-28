
public class Main {
    public static void main(String[] args) {

        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

        binarySearchTree.insert(12);
        binarySearchTree.insert(21);
        binarySearchTree.insert(5);
        binarySearchTree.insert(1);
        binarySearchTree.insert(8);
        binarySearchTree.insert(18);

        for (Integer integer : binarySearchTree.range(5, 18)) {
            System.out.println(integer);
        }
    }
}
