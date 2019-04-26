package T06_Linked_Queue;

import org.w3c.dom.Node;

public class LinkedQueue<E> {

    private int size;
    private int elementsCount;
    private QueueNode<E> head;
    private QueueNode<E> tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {

        if (this.elementsCount == 0) {
            QueueNode<E> node = new QueueNode<>();
            node.value = element;

            this.head = node;
            this.tail = node;
            this.elementsCount++;

            return;
        }

        QueueNode<E> newNode = new QueueNode<>();
        newNode.value = element;

        this.head.nextNode = newNode;
        newNode.prevNode = this.head;

        this.head = newNode;
        this.elementsCount++;
    }

    public E dequeue() {

        if(this.elementsCount == 0){
            throw new ArrayIndexOutOfBoundsException();
        }

        E element = this.tail.value;

        if(this.tail.nextNode != null){
            this.tail.nextNode.prevNode = null;
        }

        this.tail = this.tail.nextNode;

        this.elementsCount--;
        return element;
    }

    public E[] toArray() {
        E[] arr = (E[]) new Object[this.elementsCount];

        int indexCounter = 0;
        QueueNode currentNode = this.head;

        while (currentNode != null){
            arr[indexCounter] = (E) currentNode.value;
            currentNode = currentNode.prevNode;
            indexCounter++;

        }

        return arr;
    }


    private class QueueNode<E> {
        private E value;

        private QueueNode<E> nextNode;
        private QueueNode<E> prevNode;

        public E getValue() {
            return this.value;
        }

        private void setValue(E value) {
            this.value = value;
        }

        public QueueNode<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(QueueNode<E> nextNode) {
            this.nextNode = nextNode;
        }

        public QueueNode<E> getPrevNode() {
            return this.prevNode;
        }

        public void setPrevNode(QueueNode<E> prevNode) {
            this.prevNode = prevNode;
        }
    }
}