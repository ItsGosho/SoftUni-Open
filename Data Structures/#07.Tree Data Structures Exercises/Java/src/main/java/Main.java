import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.insert(1);
        binarySearchTree.insert(3);
        binarySearchTree.insert(4);
        binarySearchTree.insert(5);
        binarySearchTree.insert(8);
        binarySearchTree.insert(6);
        binarySearchTree.insert(9);
        binarySearchTree.insert(10);
        binarySearchTree.insert(37);
        binarySearchTree.insert(39);
        binarySearchTree.insert(45);

        binarySearchTree.delete(5);

        binarySearchTree.eachInOrder(System.out::println);
    }

}
