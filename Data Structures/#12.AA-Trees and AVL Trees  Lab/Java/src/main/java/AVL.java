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

        while (true) {
            int cmp = item.compareTo(currentNode.value);

            if (cmp < 0) {

                if (currentNode.left == null) {

                    currentNode.left = new Node<>(item, currentNode);
                    currentNode.left.updateFathersHeightAndBF();
                    this.rotate(currentNode);

                    break;
                }

                currentNode = currentNode.left;
            } else if (cmp > 0) {

                if (currentNode.right == null) {

                    currentNode.right = new Node<>(item, currentNode);
                    currentNode.right.updateFathersHeightAndBF();
                    this.rotate(currentNode);

                    break;
                }

                currentNode = currentNode.right;
            }
        }

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

        while (true) {
            //Will find the next node which BF is not in -1 && 1 range ,traversing from the passed node trough his fathers!
            Node<T> nextRotationNode = this.getNextRotationNode(node);

            if (nextRotationNode == null) {
                break;
            }

            int balanceFactor = nextRotationNode.balanceFactor;

            if (balanceFactor < -1) {

                if (nextRotationNode.right.balanceFactor > 0) {
                    this.rightRotation(nextRotationNode.right);
                }

                this.leftRotation(nextRotationNode);
            } else if (balanceFactor > 1) {

                if (nextRotationNode.left.balanceFactor < 0) {
                    this.leftRotation(nextRotationNode.left);
                }

                this.rightRotation(nextRotationNode);
            }

            break;
        }

    }

    private Node<T> getNextRotationNode(Node<T> startNode) {
        Node<T> currentNode = startNode;
        Node<T> result = null;
        //while i dont find node with BF > 1 && BF < -1
        while (currentNode != null) {

            if (currentNode.balanceFactor > 1 || currentNode.balanceFactor < -1) {
                result = currentNode;
                break;
            }
            currentNode = currentNode.father;
        }

        return result;
    }

    private Node<T> leftRotation(Node<T> node) {
        Node<T> temp = node.right;

        node.right = temp.left;
        node.left = new Node<T>(node);

        this.setFather(node.left, node);
        this.setFather(temp.right, node);

        node.value = temp.value;
        node.right = temp.right;
        node.height = temp.height;

        this.updateHeighAndBF(node.left);
        this.updateHeighAndBF(node.right);

        return node;
    }

    private Node<T> rightRotation(Node<T> node) {
        Node<T> temp = node.left;

        node.left = temp.right;
        node.right = new Node<T>(node);

        this.setFather(node.right, node);
        this.setFather(temp.left, node);

        node.value = temp.value;
        node.left = temp.left;

        this.updateHeighAndBF(node.left);
        this.updateHeighAndBF(node.right);

        return node;
    }

    private void setFather(Node<T> node, Node<T> father) {
        if (node != null) {
            node.father = father;
        }
    }

    private void updateHeighAndBF(Node<T> node) {
        if (node != null) {
            node.updateFathersHeightAndBF();
        }
    }

}
