import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public class RedBlackTree<T extends Comparable<T>> {

    public static final Boolean RED = true;
    public static final Boolean BLACK = false;

    private Node root;
    private int nodesCount;

    public RedBlackTree() {
    }

    private RedBlackTree(Node root) {
        this.preOrderCopy(root);
    }

    public void insert(T value) {
        this.nodesCount++;

        if (this.root == null) {
            this.root = new Node(value, BLACK);
            return;
        }

        this.insert(this.root, value);
    }

    private void insert(Node node, T value) {

        while (node != null) {
            T nodeValue = node.value;

            //right
            if (this.isSmallerThan(nodeValue, value)) {

                if (node.right == null) {
                    node.right = new Node(value, RED);
                    this.rotation(node);
                    return;
                }

                this.insert(node.right, value);
            }

            //left
            if (!this.isSmallerThan(nodeValue, value)) {

                if (node.left == null) {
                    node.left = new Node(value, RED);
                    this.rotation(node);
                    return;
                }

                this.insert(node.left, value);
            }

            return;
        }

    }

    //Is and what rotation is needed:
    private void rotation(Node node) {

        while (node != null) {
            boolean isChanged = false;

            /*If the node`s left and right child colors equal to Red and this will repeat until there isn't*/
            while (true){
                boolean isColored = false;
                if (this.isFlipColorRequired(node)) {
                    Node newNode = this.flipColor(node);
                    node = this.getFather(newNode.value);
                    isColored = true;
                }

                if(!isColored) break;;
                isChanged = true;
            }

            /*If the node`s right child red*/
            if (this.isLeftRotationRequired(node)) {
                Node newNode = this.leftRotation(node);
                node = this.getFather(newNode.value);
                isChanged = true;
            }

            /*If two consecutive nodes are red - node & node.left*/
            if (this.isRightRotationRequired(node)) {
                node = this.rightRotation(this.getFather(node.value));
                isChanged = true;
            }

            if (!isChanged) break;
        }

    }

    private Node leftRotation(Node node) {
        Node temp = node.right;

        node.right = temp.left;
        node.color = RED;
        node.left = new Node(node);
        node.color = BLACK;
        node.value = temp.value;
        node.right = temp.right;

        return node;
    }

    private Node rightRotation(Node node) {
        Node temp = node.left;

        node.left = temp.right;
        node.color = RED;
        node.right = new Node(node);
        node.color = BLACK;
        node.value = temp.value;
        node.left = temp.left;

        return node;
    }

    private Node flipColor(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;

        if (!this.root.equals(node)) node.color = RED;

        return node;
    }

    private boolean isRightRotationRequired(Node node) {
        if (node == null) return false;
        if (!this.isRed(node)) return false;
        if (node.left == null) return false;
        if (!this.isRed(node.left)) return false;

        Node leftNode = node.left;

        return this.isRed(node) && this.isRed(leftNode);
    }

    private boolean isLeftRotationRequired(Node node) {

        if (node == null) return false;
        if (node.right == null) return false;

        return this.isRed(node.right);
    }

    private boolean isFlipColorRequired(Node node) {

        if (node == null) return false;
        if (node.left == null || node.right == null) return false;

        return this.isRed(node.left) && this.isRed(node.right);
    }

    private boolean isSmallerThan(T value1, T value2) {
        return value1.compareTo(value2) <= 0;
    }

    private boolean isRed(Node node) {
        return node.color == RED;
    }

    public boolean contains(T value) {
        Node current = this.root;
        while (current != null) {
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return current != null;
    }

    public RedBlackTree<T> search(T item) {

        Node current = this.root;

        while (current != null) {
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return new RedBlackTree<>(current);
    }

    public Node getFather(T item) {

        Node father = null;
        Node current = this.root;

        while (current != null) {

            if (item.compareTo(current.value) < 0) {
                father = current;
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                father = current;
                current = current.right;
            } else {
                break;
            }
        }

        return father;
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }

        return node.childrenCount;
    }

    private void preOrderCopy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public int getNodesCount() {
        return this.nodesCount;
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        private boolean color;
        private int childrenCount;

        public Node(T value, boolean color) {
            this.value = value;
            this.childrenCount = 1;
            this.color = color;
        }

        public Node(Node node) {
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
            this.color = node.color;
            this.childrenCount = node.childrenCount;
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

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        @Override
        public String toString() {
            String color = this.color == false ? "Black" : "Red";
            return this.value + " - " + color;
        }
    }
}


