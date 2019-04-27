package T02_Binary_Tree;

public class PlayWithBinaryTree {
    public static void main(String[] args) {
        BinaryTree<String> binaryTree =
                new BinaryTree<>("*",
                        new BinaryTree<>("+",
                                new BinaryTree<>("3"),
                                new BinaryTree<>("2")),
                        new BinaryTree<>("-",
                                new BinaryTree<>("9"),
                                new BinaryTree<>("6")));


        binaryTree.eachPostOrder(x-> {
            System.out.println(x);
        });
    }
}
