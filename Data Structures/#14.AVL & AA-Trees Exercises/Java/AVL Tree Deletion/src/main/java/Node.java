public class Node<T extends Comparable<T>> {

    public T value;
    public Node<T> left;
    public Node<T> right;

    public int height;
    public int balanceFactor;


    public Node(T value) {
        this.value = value;
        this.height = 1;
        this.updateBalanceFactor();
    }

    public Node(Node<T> node) {
        this.value = node.value;
        this.left = node.left;
        this.right = node.right;
        this.height = node.height;
        this.updateBalanceFactor();
    }

    public void updateBalanceFactor() {
        int leftCount = this.left != null ? this.left.height : 0;
        int rightCount = this.right != null ? this.right.height : 0;

        this.balanceFactor = leftCount - rightCount;
    }

}
