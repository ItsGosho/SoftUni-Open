import java.util.*;

public class Hierarchy<T> implements IHierarchy<T> {

    private Integer count;
    private Node<T> root;

    public Hierarchy(T element) {
        this.root = new Node<>(element);
        this.count = 1;
    }

    public void Add(T parent, T child) {

        Node<T> fatherResult = this.add(this.root, parent, child);

        if (fatherResult == null) {
            throw new IllegalArgumentException("Parent doesn't exists!");
        }

        Node<T> newNode = new Node<>(child, fatherResult);
        fatherResult.add(newNode);
        this.count++;
    }

    //Will search for the father and too if the new childValue exists already
    private Node<T> add(Node<T> start, T parentValue, T childValue) {

        Node<T> father = null;
        Queue<Node<T>> nodes = new ArrayDeque<>();
        nodes.add(start);

        while (nodes.size() != 0) {
            Node node = nodes.peek();

            if (node.getValue().equals(childValue)) {
                throw new IllegalArgumentException("Value already exists!");
            }

            if (node.getValue().equals(parentValue)) {
                father = node;
                break;
            }

            node.getChildrens().forEach(x -> {
                nodes.add((Node<T>) x);
            });

            nodes.poll();
        }

        return father;
    }

    public int getCount() {
        return this.count;
    }

    public void Remove(T element) {

        if (this.root.getValue().equals(element)) {
            throw new IllegalStateException("Collection is empty!");
        }

        int oldCount = this.count;
        Queue<Node<T>> nodes = new ArrayDeque<>();
        nodes.add(this.root);

        while (nodes.size() != 0) {
            Node node = nodes.peek();

            if (node.getValue().equals(element)) {
                Node<T> nodeFather = node.getFather();

                Set<Node<T>> newChildrens = new LinkedHashSet<>();

                nodeFather.getChildrens().remove(node);
                for (Object children : node.getChildrens()) {
                    Node<T> child = (Node<T>) children;
                    child.setFather(nodeFather);
                    nodeFather.add(child);
                }

                this.count--;
                break;
            }

            node.getChildrens().forEach(x -> {
                nodes.add((Node<T>) x);
            });

            nodes.poll();
        }

        if (oldCount == this.count) {
            throw new IllegalArgumentException();
        }

    }

    public boolean Contains(T element) {
        boolean isPresent = false;
        Queue<Node<T>> nodes = new ArrayDeque<>();
        nodes.add(this.root);

        while (nodes.size() != 0) {
            Node node = nodes.peek();

            if (node.getValue().equals(element)) {
                isPresent = true;
                break;
            }

            node.getChildrens().forEach(x -> {
                nodes.add((Node<T>) x);
            });

            nodes.poll();
        }

        return isPresent;
    }

    public T GetParent(T element) {

        if (this.count == 1 && this.root.getValue().equals(element)) {
            return null;
        }

        T fatherValue = null;
        Queue<Node<T>> nodes = new ArrayDeque<>();
        nodes.add(this.root);

        while (nodes.size() != 0) {
            Node node = nodes.peek();

            if (node.getValue().equals(element)) {
                Node<T> nodeFather = node.getFather();
                return nodeFather.getValue();
            }

            node.getChildrens().forEach(x -> {
                nodes.add((Node<T>) x);
            });

            nodes.poll();
        }

        throw new IllegalArgumentException("Not existing element");
    }

    public Iterable<T> GetChildren(T element) {

        Queue<Node<T>> nodes = new ArrayDeque<>();
        nodes.add(this.root);

        while (nodes.size() != 0) {
            Node node = nodes.peek();

            if (node.getValue().equals(element)) {
                List<T> values = new ArrayList<>();

                for (Object item : node.getChildrens()) {
                    Node<T> itemm = (Node<T>) item;
                    values.add(itemm.getValue());
                }

                return () -> values.iterator();
            }

            node.getChildrens().forEach(x -> {
                nodes.add((Node<T>) x);
            });

            nodes.poll();
        }

        throw new IllegalArgumentException("Not existing element!");
    }

    public Iterable<T> GetCommonElements(IHierarchy<T> other) {
        Set<T> result = new LinkedHashSet<>();
        Set<T> valuesOne = new LinkedHashSet<>();
        Set<T> valuesTwo = new LinkedHashSet<>();

        other.iterator().forEachRemaining(x -> {
            valuesOne.add(x);
        });

        this.iterator().forEachRemaining(x -> {
            valuesTwo.add(x);
        });

        valuesTwo.forEach(x -> {
            if (valuesOne.contains(x)) {
                result.add(x);
            }
        });

        return () -> result.iterator();
    }

    @Override
    public Iterator<T> iterator() {
        Set<T> result = new LinkedHashSet<>();

        Queue<Node<T>> nodes = new ArrayDeque<>();
        nodes.add(this.root);

        while (nodes.size() != 0) {
            Node node = nodes.peek();

            result.add((T) node.getValue());

            node.getChildrens().forEach(x -> {
                nodes.add((Node<T>) x);
            });

            nodes.poll();
        }

        return result.iterator();
    }
}
