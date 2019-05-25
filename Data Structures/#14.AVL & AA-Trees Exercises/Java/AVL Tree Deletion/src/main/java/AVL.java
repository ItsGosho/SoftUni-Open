import java.util.Stack;
import java.util.function.Consumer;

public class AVL<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean contains(T item) {
        Node<T> node = this.search(this.root, item);
        return node != null;
    }

    public void insert(T item) {
        if (this.root == null) {
            this.root = new Node<>(item);
            return;
        }

        Node<T> currentNode = this.root;

        Stack<Node<T>> path = new Stack<>();
        path.add(currentNode);
        while (true) {
            int cmp = item.compareTo(currentNode.value);

            if (cmp < 0) {

                if (currentNode.left == null) {

                    currentNode.left = new Node<>(item);

                    break;
                }

                currentNode = currentNode.left;
            } else if (cmp > 0) {

                if (currentNode.right == null) {

                    currentNode.right = new Node<>(item);

                    break;
                }

                currentNode = currentNode.right;
            }
            path.add(currentNode);
        }

        System.out.println();
        while (path.size() != 0) {
            Node<T> node = path.pop();
            node.height++;
            node.updateBalanceFactor();
            this.rotate(node);


            this.update(node.left);
            this.update(node.right);
            this.update(node);
        }

        System.out.println();
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, action);
        action.accept(node.value);
        this.eachInOrder(node.right, action);
    }

    private Node<T> search(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            return search(node.left, item);
        } else if (cmp > 0) {
            return search(node.right, item);
        }

        return node;
    }

    public void rotate(Node<T> node) {

        //Will find the next node which BF is not in -1 && 1 range ,traversing from the passed node trough his fathers!

        int balanceFactor = node.balanceFactor;

        if (balanceFactor < -1) {

            if (node.right.balanceFactor > 0) {
                this.rightRotation(node.right);
            }

            this.leftRotation(node);
        } else if (balanceFactor > 1) {

            if (node.left.balanceFactor < 0) {
                this.leftRotation(node.left);
            }

            this.rightRotation(node);
        }
    }

    private Node<T> leftRotation(Node<T> node) {
        Node<T> temp = node.right;

        node.right = temp.left;
        node.left = new Node<T>(node);
        node.value = temp.value;
        node.right = temp.right;
        node.height = temp.height;

        return node;
    }

    private Node<T> rightRotation(Node<T> node) {
        Node<T> temp = node.left;

        node.left = temp.right;
        node.right = new Node<T>(node);
        node.value = temp.value;
        node.left = temp.left;

        return node;
    }

    private void update(Node<T> node) {
        if (node != null) {
            int leftNodeHeight = node.left != null ? node.left.height : 0;
            int rightNodeHeight = node.right != null ? node.right.height : 0;
            node.height = Math.max(leftNodeHeight + 1, rightNodeHeight + 1);

            node.updateBalanceFactor();
        }
    }


    public void delete(T element) {
    }

    private Node<T> findMax(Node<T> currentNode) {
        Node<T> current = currentNode.left;

        if (current != null && current.right != null) {
            current = currentNode.right;
        } else {
            return current;
        }

        return current;
    }

    public void deleteMin() {

    }

}
