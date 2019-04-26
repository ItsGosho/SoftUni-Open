package T04_Linked_Stack;

public class LinkedStack<E> {

    private Node<E> firstNode;
    private int size;


    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {

        if(this.firstNode == null){
            Node<E> newNode = new Node<>(element);
            this.firstNode = newNode;
            this.size++;
            return;
        }

        Node<E> newNode = new Node<>(element,this.firstNode);
        this.firstNode = newNode;
        this.size++;
    }

    public E pop() {

        if(this.firstNode == null){
            throw new IllegalArgumentException();
        }

        Node<E> oldNode = this.firstNode;

        if(this.firstNode.getNextNode() != null){
            oldNode = this.firstNode.getNextNode();
        }

        this.firstNode = oldNode;
        this.size--;

        return oldNode.value;
    }

    public E[] toArray() {
        E[] result = (E[]) new Object[this.size];

        this.fillArrayWithValues(result,0,this.firstNode);

        return result;
    }

    private void fillArrayWithValues(E[] array,int indexer,Node<E> node){
        array[indexer] = node.value;

        if(node.getNextNode() == null){
            return;
        }

        Node<E> nextNode = node.getNextNode();
        indexer++;
        this.fillArrayWithValues(array,indexer,nextNode);
    }

    private class Node<E> {

        private E value;
        private Node<E> nextNode;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }
}