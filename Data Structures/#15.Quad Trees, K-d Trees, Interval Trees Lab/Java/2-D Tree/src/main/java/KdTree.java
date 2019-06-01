import java.util.function.Consumer;

public class KdTree {

    private Node root;


    public void insert(Point2D point) {


        if (this.root == null) {
            this.root = new Node(point);
            return;
        }

        this.insert(this.root, 0, point);
    }

    private Node insert(Node current, int depth, Point2D pointToInsert) {

        if (current == null) {
            return new Node(pointToInsert);
        }

        if (depth % 2 == 0) {
            //compare by X

            if (pointToInsert.getX() < current.getPoint2D().getX()) {
                current.setLeft(this.insert(current.getLeft(), ++depth, pointToInsert));
            } else {
                current.setRight(this.insert(current.getRight(), ++depth, pointToInsert));
            }

        } else if (depth % 2 == 1) {
            //compare by Y

            if (pointToInsert.getY() < current.getPoint2D().getY()) {
                current.setLeft(this.insert(current.getLeft(), ++depth, pointToInsert));
            } else {
                current.setRight(this.insert(current.getRight(), ++depth, pointToInsert));
            }
        }

        return current;
    }


    public boolean contains(Point2D point) {
        throw new UnsupportedOperationException();
    }

    public void eachInOrder(Consumer<Point2D> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<Point2D> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.getLeft(), consumer);
        consumer.accept(node.getPoint2D());
        this.eachInOrder(node.getRight(), consumer);
    }

    public Node getRoot() {
        return this.root;
    }
}
