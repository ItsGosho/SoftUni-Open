public class Main {

    public static void main(String[] args) {


        KdTree tree = new KdTree();
        tree.insert(new Point2D(5, 5));
        tree.insert(new Point2D(3, 2));
        tree.insert(new Point2D(2, 6));
        tree.insert(new Point2D(8, 8));
        tree.insert(new Point2D(8, 9));

    }
}
