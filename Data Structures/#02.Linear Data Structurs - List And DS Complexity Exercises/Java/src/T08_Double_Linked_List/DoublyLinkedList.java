package T08_Double_Linked_List;

import java.util.Iterator;
import java.util.function.Consumer;

public class DoublyLinkedList<E> implements Iterable<E> {

    private int size;
    private Node<E> head;
    private Node<E> tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E element) {

        if (this.size == 0) {
            Node<E> node = new Node<>(element, null, null);
            this.head = node;
            this.tail = node;
            this.size++;
            return;
        }

        Node<E> node = new Node<>(element, head, null);
        this.head = node;
        this.size++;

    }

    public void addLast(E element) {

        if (this.size == 0) {
            Node<E> node = new Node<>(element, null, null);
            this.head = node;
            this.tail = node;
            this.size++;
            return;
        }

        Node<E> node = new Node<>(element, null, this.tail);
        this.tail = node;
        this.size++;
    }

    public E removeFirst() {

        Node<E> removedNode = this.head.getNext();
        this.head = this.head.getNext();
        this.size--;

        return removedNode.getValue();
    }

    public E removeLast() {
        Node<E> removedNode = this.tail.getPrevious();
        this.tail = this.tail.getPrevious();
        this.size--;

        return removedNode.getValue();
    }

    public E[] toArray() {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }

}
