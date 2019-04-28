import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node root) {
        this();
        this.root = root;
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(T value) {

        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        Node node = new Node(value);
        ArrayDeque<Node> nodesPath = new ArrayDeque<>();
        this.findBiggestElementComparedToValue(this.root, node.value, nodesPath);

        Node tail = nodesPath.pop();

        if (node.value.compareTo(tail.value) < 0) {
            tail.left = node;
        } else {
            tail.right = node;
        }

    }

    private void findBiggestElementComparedToValue(Node root, T value, Queue<Node> nodesPath) {

        while (root != null) {
            T currentNodeValue = root.value;

            if (value.compareTo(currentNodeValue) < 0) {
                this.findBiggestElementComparedToValue(root.left, value, nodesPath);
                nodesPath.add(root);
            } else {
                this.findBiggestElementComparedToValue(root.right, value, nodesPath);
                nodesPath.add(root);
            }
            return;
        }

    }

    public boolean contains(T value) {
        return this.search(value).root != null;
    }

    public BinarySearchTree<T> search(T item) {

        Node result = this.search(this.root, item);
        BinarySearchTree<T> binarySearchTree = new BinarySearchTree<>(result);

        return binarySearchTree;

    }

    private Node search(Node root, T value) {
        while (root != null) {
            T currentNodeValue = root.value;

            if (currentNodeValue == value) {
                return root;
            }

            if (value.compareTo(currentNodeValue) < 0) {
                return this.search(root.left, value);
            } else {
                return this.search(root.right, value);
            }
        }

        return null;
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.inOrder(this.root, consumer);
    }

    private void inOrder(Node root, Consumer<T> consumer) {

        while (root != null) {
            this.inOrder(root.left, consumer);
            consumer.accept(root.value);
            this.inOrder(root.right, consumer);

            return;
        }

    }

    public Iterable<T> range(T from, T to) {
        ArrayDeque<T> arrayDeque = new ArrayDeque();

        this.findInRange(this.root, from, to, arrayDeque);

        return arrayDeque.stream().sorted((x1,x2)->x1.compareTo(x2)).collect(Collectors.toList());
    }

    private void findInRange(Node root, T fromValue, T toValue, ArrayDeque<T> arrayDeque) {

        while (root != null) {
            T currentValue = root.value;

            if (fromValue.compareTo(currentValue) <= 0 && toValue.compareTo(currentValue) >= 0) {
                arrayDeque.add(currentValue);
            }

            if (fromValue.compareTo(currentValue) < 0) {
                this.findInRange(root.left, fromValue, toValue, arrayDeque);
            }

            if(toValue.compareTo(currentValue) > 0){
                this.findInRange(root.right, fromValue, toValue, arrayDeque);
            }

            return;
        }

    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}

