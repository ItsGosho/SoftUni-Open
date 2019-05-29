public class Main {

    public static void main(String[] args) {


        IntervalTree intervalTree = new IntervalTree();

        intervalTree.insert(20, 36);
        intervalTree.insert(3, 10);
        intervalTree.insert(29, 99);
        intervalTree.insert(0, 1);
        intervalTree.insert(10, 15);
        intervalTree.insert(25, 30);

        intervalTree.searchAll(9, 50);

    }
}
