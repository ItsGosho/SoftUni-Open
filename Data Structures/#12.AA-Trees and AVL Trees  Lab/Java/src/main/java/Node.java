public class Node<T extends Comparable<T>> {

    public T value;
    public Node<T> father;
    public Node<T> left;
    public Node<T> right;

    public int height;
    public int balanceFactor;


    public Node(T value) {
        this.value = value;
        this.height = 1;
        this.updateBalanceFactor();
    }

    public Node(T value, Node<T> father) {
        this.value = value;
        this.height = 1;
        this.father = father;
    }

    public Node(Node<T> node) {
        this.value = node.value;
        this.left = node.left;
        this.right = node.right;
        this.height = node.height;
        this.updateLeftRightFather();
    }

    private void updateLeftRightFather(){
        if(this.left != null){
            this.left.father = this;
        }

        if(this.right != null){
            this.right.father = this;
        }
    }

    private void updateBalanceFactor() {
        int leftCount = this.left != null ? this.left.height : 0;
        int rightCount = this.right != null ? this.right.height : 0;

        this.balanceFactor = leftCount - rightCount;
    }

    public void updateHeight() {
        int leftCount = this.left != null ? this.left.height : 0;
        int rightCount = this.right != null ? this.right.height : 0;

        this.height = Math.max(leftCount, rightCount) + 1;
        this.updateBalanceFactor();
    }

    public void updateFathersHeightAndBF() {
        this.updateHeight();
        Node<T> father = this.father;

        while (father != null) {
            father.updateHeight();

            father = father.father;
        }
    }

}
