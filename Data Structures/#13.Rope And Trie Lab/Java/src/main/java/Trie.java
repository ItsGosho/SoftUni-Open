import java.util.*;

public class Trie<T> {
    private Node root;

    public T getValue(String key) {
        Node result = this.getNode(this.root, key, 0);

        if (result == null || !result.isTerminal()) {
            throw new IllegalArgumentException();
        }

        return result.getValue();
    }

    public boolean contains(String key) {
        Node node = this.getNode(this.root, key, 0);
        return node != null && node.isTerminal();
    }

    public void insert(String key, T value) {

        if (this.root == null) {
            Node rootNode = new Node();
            this.insertNew(key.toCharArray(), value, 0, rootNode);
            this.root = rootNode;
            return;
        }

        this.insert(this.root, key.toCharArray(), value, 0);
    }

    private Node insertNew(char[] key, T value, Integer counter, Node node) {

        char currentChar = key[counter];
        node = node.addNext(currentChar);

        counter++;
        if (counter > key.length - 1) {
            node.isTerminal = true;
            node.value = value;
            return node;
        }

        this.insertNew(key, value, counter, node);

        return node;
    }

    public Iterable<String> getByPrefix(String prefix) {
        Deque<String> result = new LinkedList<>();
        Node node = this.getNode(this.root, prefix, 0);

        this.collect(node, prefix, result);
        return reverseCollection(result);
    }

    private void collect(Node node, String prefix, Deque<String> result) {
        if (node == null) {
            return;
        }

        if (node.getValue() != null && node.isTerminal()) {
            result.addFirst(prefix);
        }

        for (Character c : node.getNext().keySet()) {
            this.collect(node.getNext().get(c), prefix + c, result);
        }
    }

    private void insert(Node node, char[] key, T value, int counter) {

        char currentkey = key[counter];
        if (node.getNext().containsKey(currentkey)) {
            node = node.getNext().get(currentkey);
            counter++;
            this.insert(node,key,value,counter);
        } else {
            this.insertNew(key,value,counter,node);
        }
    }

    private Node getNode(Node node, String key, int v) {
        if (node == null) {
            return null;
        }

        if (v == key.length()) {
            return node;
        }

        char c = key.charAt(v);
        return this.getNode(node.getNext().get(c), key, v + 1);
    }

    private Iterable<String> reverseCollection(Deque<String> collection) {
        ArrayList<String> result = new ArrayList<>();

        Iterator<String> iterator = collection.descendingIterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        return result;
    }

    private class Node {
        private T value;
        private boolean isTerminal;
        private Map<Character, Node> next;

        public Node() {
            this.next = new LinkedHashMap<>();
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public boolean isTerminal() {
            return isTerminal;
        }

        public void setTerminal(boolean terminal) {
            this.isTerminal = terminal;
        }

        public Map<Character, Node> getNext() {
            return this.next;
        }

        public Node addNext(Character character) {
            Node node = new Node();
            this.next.put(character, node);

            return node;
        }
    }
}
