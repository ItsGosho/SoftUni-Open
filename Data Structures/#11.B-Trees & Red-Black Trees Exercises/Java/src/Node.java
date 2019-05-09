import java.util.LinkedHashSet;
import java.util.Set;

public class Node<T> {

    private T value;
    private Node<T> father;
    private Set<Node<T>> childrens;

    public Node(T value) {
        this.value = value;
        this.childrens = new LinkedHashSet<>();
    }

    public Node(T value,Node<T> father) {
        this.value = value;
        this.childrens = new LinkedHashSet<>();
        this.father = father;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Set<Node<T>> getChildrens() {
        return childrens;
    }

    public void add(Node<T> child) {
        if (this.childrens == null) {
            this.childrens = new LinkedHashSet<>();
        }

        this.childrens.add(child);
    }

    public void setChildrens(Set<Node<T>> childrens) {
        this.childrens = childrens;
    }

    public Node<T> getFather() {
        return father;
    }

    public void setFather(Node<T> father) {
        this.father = father;
    }
}
