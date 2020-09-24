import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //String input1 = "text, me, so, do, m, ran";
        //String input2 = "somerandomtext";
        String input1 = scanner.nextLine().trim();
        String input2 = scanner.nextLine().trim();

        HashSet<String> givenWords = new HashSet<String>(Arrays.asList(input1.split(", ")));

        WordTree wordTree = new WordTree(new Node(input2, givenWords));
        List<String> foundWords = wordTree.getFoundWordsSortedDesc();
        System.out.println(String.join("\r\n", foundWords));

    }

    public static class WordTree {

        private HashSet<String> foundWords;
        private Node root;

        public WordTree(Node root) {
            this.root = root;
            this.foundWords = new HashSet<>();

            this.startDivision(root);
        }

        public void startDivision(Node node) {

            int startIndex = node.getStartIndex();
            String value = node.getValue();
            HashMap<String, Integer> possibleValues = node.getPossibleValues();

            if (this.isNodeContained(node))
                this.foundWords.add(node.getValue());

            for (int i = startIndex; i < value.length() - 1; i++) {
                String firstHalf = value.substring(0, i + 1);

            /*String[] possibleContained = firstHalf.split("\\s+");

            if (possibleContained.length > 0 && !possibleValues.contains(possibleContained[possibleContained.length - 1]))
                continue;*/

                String secondHalf = value.substring(i + 1, value.length());
                String formedWord = firstHalf + " " + secondHalf;

                Node formedNode = new Node(formedWord, i + 2, possibleValues);
                node.addNode(formedNode);
                this.startDivision(formedNode);
            }
        }

        public boolean isNodeContained(Node node) {
            String[] values = node.getValue().split("\\s+");

            HashMap<String, Integer> timesContained = new HashMap<>();

            for (String s : values) {

                if (timesContained.containsKey(s))
                    timesContained.put(s, timesContained.get(s) + 1);
                else
                    timesContained.put(s, 1);
            }

            for (Map.Entry<String, Integer> nodeWordsEntry : timesContained.entrySet()) {

                if (!node.getPossibleValues().containsKey(nodeWordsEntry.getKey()))
                    return false;

                if (node.getPossibleValues().get(nodeWordsEntry.getKey()) < nodeWordsEntry.getValue())
                    return false;
            }

            return true;
        }

        public List<String> getFoundWordsSortedDesc() {
            return foundWords.stream()
                    .sorted(String::compareTo)
                    .collect(Collectors.toList());
        }

        public HashSet<String> getFoundWords() {
            return foundWords;
        }

        public void setFoundWords(HashSet<String> foundWords) {
            this.foundWords = foundWords;
        }

        public Node getRoot() {
            return root;
        }

        public void setRoot(Node root) {
            this.root = root;
        }
    }


    public static class Node {

        private String value;
        private HashMap<String, Integer> possibleValues;
        private int startIndex;
        private List<Node> nodes;

        public Node() {
            this.startIndex = 0;
            this.nodes = new LinkedList<>();
            this.possibleValues = new HashMap<>();
        }

        public Node(String value, HashSet<String> possibleValues) {
            this();
            this.value = value;
            this.setPossibleValues(possibleValues);
        }

        public Node(String value, int startIndex, HashSet<String> possibleValues) {
            this();
            this.startIndex = startIndex;
            this.value = value;
            this.setPossibleValues(possibleValues);
        }

        public Node(String value, int startIndex, HashMap<String, Integer> possibleValues) {
            this();
            this.startIndex = startIndex;
            this.value = value;
            this.setPossibleValues(possibleValues);
        }

        public boolean addNode(Node node) {
            return this.nodes.add(node);
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<Node> getNodes() {
            return nodes;
        }

        public void setNodes(List<Node> nodes) {
            this.nodes = nodes;
        }

        public HashMap<String, Integer> getPossibleValues() {
            return possibleValues;
        }

        public void setPossibleValues(HashSet<String> possibleValues) {
            for (String s : possibleValues) {
                if (this.possibleValues.containsKey(s))
                    this.possibleValues.put(s, this.possibleValues.get(s) + 1);
                else
                    this.possibleValues.put(s, 1);
            }
        }

        public void setPossibleValues(HashMap<String, Integer> possibleValues) {
            this.possibleValues = possibleValues;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value='" + value + '\'' +
                    ", startIndex=" + startIndex +
                    '}';
        }
    }

}
