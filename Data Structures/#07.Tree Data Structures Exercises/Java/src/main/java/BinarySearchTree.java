import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;
    private int nodesCount;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node root) {
        this.preOrderCopy(root);
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

    public void insert(T value) {
        this.nodesCount++;

        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        Node parent = null;
        Node current = this.root;
        while (current != null) {
            parent = current;
            parent.childrenCount++;

            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                return;
            }
        }

        Node newNode = new Node(value);
        if (value.compareTo(parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
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

    public BinarySearchTree<T> search(T item) {
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

        return new BinarySearchTree<>(current);
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

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new LinkedList<>();
        this.range(this.root, queue, from, to);
        return queue;
    }

    private void range(Node node, Deque<T> queue, T startRange, T endRange) {
        if (node == null) {
            return;
        }

        int compareStart = startRange.compareTo(node.value);
        int compareEnd = endRange.compareTo(node.value);
        if (compareStart < 0) {
            this.range(node.left, queue, startRange, endRange);
        }
        if (compareStart <= 0 && compareEnd >= 0) {
            queue.addLast(node.value);
        }
        if (compareEnd > 0) {
            this.range(node.right, queue, startRange, endRange);
        }
    }

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }

        return minv;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node min = this.root;
        Node parent = null;

        while (min.left != null) {
            parent = min;
            parent.childrenCount--;
            min = min.left;
        }

        if (parent == null) {
            this.root = this.root.right;
        } else {
            parent.left = min.right;
        }

        this.nodesCount--;
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        if (this.root.right == null) {

            if (this.root.left != null) {
                this.root = this.root.left;
                return;
            }

            this.root = null;
            return;

        }

        Node fatherElement = this.root;
        Node rightElement = this.root.right;

        while (rightElement != null) {

            if (rightElement.right == null) {
                fatherElement.right = rightElement.left;
                break;
            }

            fatherElement = rightElement;
            rightElement = rightElement.right;

        }
        this.nodesCount--;
    }

    public T ceil(T element) {

        if(this.root == null){
            return null;
        }

        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(this.root);

        Node currentHighest = null;
        while (nodes.size() != 0) {
            Node node = nodes.peek();

            if (node.value.compareTo(element) >= 0) {

                if (currentHighest == null) {
                    currentHighest = node;
                } else if (currentHighest.value.compareTo(node.value) >= 0) {
                    currentHighest = node;
                }
            }

            if (node.left != null)
                nodes.add(node.left);

            if (node.right != null)
                nodes.add(node.right);

            nodes.poll();
        }

        if (currentHighest == null)
            return null;

        return currentHighest.value;
    }

    //BFS
    public T floor(T element) {
        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(this.root);

        Node currentSmallest = null;
        while (nodes.size() != 0) {
            Node node = nodes.peek();

            if (node.value.compareTo(element) <= 0) {

                if (currentSmallest == null) {
                    currentSmallest = node;
                } else if (currentSmallest.value.compareTo(node.value) <= 0) {
                    currentSmallest = node;
                }
            }

            if (node.left != null)
                nodes.add(node.left);

            if (node.right != null)
                nodes.add(node.right);

            nodes.poll();
        }

        if (currentSmallest == null)
            return null;

        return currentSmallest.value;
    }

    public void delete(T key) {

        if (this.search(key).root == null) {
            return;
        }

        if (this.root.childrenCount == 1) {
            this.root = null;
            return;
        }

        if (this.root.value.equals(key)) {

            if (this.root.left == null) {
                this.root = this.root.right;
                return;
            }

            if (this.root.right == null) {
                this.root = this.root.left;
                return;
            }

            Node newRoot = this.root.right;

            Node edge = newRoot;
            while (edge != null){
                Node nextEdge = edge.left;

                if (nextEdge == null) {
                    edge.left = this.root.left;
                    break;
                }

                edge = edge.left;
            }

            this.root = newRoot;
            return;
        }

        this.delete(this.root, null, key, null);
    }

    private void delete(Node startNode, Node father, T element, String comingPosition) {

        while (startNode != null) {

            if (startNode.value.equals(element)) {
                switch (comingPosition) {
                    case "left":

                        father.left = startNode.right;

                        Node lastElement = father.left;

                        if (lastElement == null)
                            father.left = startNode.left;

                        while (lastElement != null) {
                            Node next = lastElement.left;
                            if (next == null) {
                                lastElement.left = startNode.left;
                                break;
                            }
                            lastElement = lastElement.left;
                        }
                        break;

                    case "right":
                        father.right = startNode.left;

                        Node lastRightElement = father.right;

                        if (lastRightElement == null)
                            father.right = startNode.right;

                        while (lastRightElement != null) {
                            Node next = lastRightElement.right;
                            if (next == null) {
                                lastRightElement.right = startNode.right;
                                break;
                            }
                            lastRightElement = lastRightElement.right;
                        }
                        break;
                }
            }

            this.delete(startNode.left, startNode, element, "left");
            this.delete(startNode.right, startNode, element, "right");
            return;
        }
    }

    public int rank(T item) {

        List<Node> nodes = new ArrayList<>();
        this.rank(this.root, item, nodes);

        return nodes.size();
    }

    private void rank(Node node, T smallerThan, List<Node> nodes) {

        while (node != null) {

            if (node.value.compareTo(smallerThan) < 0) {
                nodes.add(node);
            }

            if (node.value.compareTo(smallerThan) <= 0) {
                this.rank(node.left, smallerThan, nodes);
                this.rank(node.right, smallerThan, nodes);
            } else {
                this.rank(node.left, smallerThan, nodes);
            }

            break;
        }
    }

    public T select(int n) {
        Map<T, Integer> map = new LinkedHashMap<>();
        this.select(this.root, map);

        return map.entrySet().stream().filter(x -> x.getValue() == n).findFirst().orElse(null).getKey();
    }

    private void select(Node node, Map<T, Integer> map) {

        while (node != null) {
            this.select(node.left, map);

            List<Node> nodes = new ArrayList<>();
            this.rank(this.root, node.value, nodes);

            map.put(node.value, (nodes.size()));
            //System.out.println(node.value + " -> " + (nodes.size() - 1));

            this.select(node.right, map);
            return;
        }
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        private int childrenCount;

        public Node(T value) {
            this.value = value;
            this.childrenCount = 1;
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

        public int getChildrenCount() {
            return childrenCount;
        }

        public void setChildrenCount(int childrenCount) {
            this.childrenCount = childrenCount;
        }

        @Override
        public String toString() {
            return this.value + "";
        }
    }
}

