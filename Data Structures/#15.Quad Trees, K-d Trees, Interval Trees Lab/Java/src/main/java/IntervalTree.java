import java.util.ArrayList;
import java.util.function.Consumer;

public class IntervalTree {

    private Node root;

    public void insert(double start, double end) {
        this.root = this.insert(this.root, start, end);
    }

    public void eachInOrder(Consumer<Interval> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    public Interval searchAny(double start, double end) {
        throw new UnsupportedOperationException();
    }

    public Iterable<Interval> searchAll(double start, double end) {
        ArrayList<Interval> result = this.searchAll(this.root, start, end, new ArrayList<>());
        return result;
    }

    private ArrayList<Interval> searchAll(Node node, double start, double end, ArrayList<Interval> result) {

        if (node.left != null && node.left.max > start) {
            this.searchAll(node.left, start, end, result);
        }
        
        if (node.interval.intersects(start, end)) {
            result.add(node.interval);
        }

        if (node.right != null && node.right.interval.getStart() < end) {
            this.searchAll(node.right, start, end, result);
        }

        return result;
    }

    private void eachInOrder(Node node, Consumer<Interval> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.interval);
        this.eachInOrder(node.right, consumer);
    }

    private Node insert(Node node, double start, double end) {
        if (node == null) {
            return new Node(new Interval(start, end));
        }

        int cmp = Double.compare(start, node.interval.getStart());
        if (cmp < 0) {
            node.left = insert(node.left, start, end);
        } else if (cmp > 0) {
            node.right = insert(node.right, start, end);
        }

        node.updateMax(end);
        return node;
    }

    private class Node {

        private Interval interval;
        private double max;
        private Node right;
        private Node left;

        public Node(Interval interval) {
            this.interval = interval;
            this.max = interval.getEnd();
        }

        public void updateMax(Double newMax) {
            if (newMax != null && newMax > max) {
                this.max = newMax;
            }
        }

        public double getMax() {
            return this.max;
        }

    }

}
