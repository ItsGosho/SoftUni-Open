public class Main {

    public static void main(String[] args) {


        Trie<Integer> trie = new Trie<>();

        trie.insert("The", 3);
        trie.insert("There", 10);
        trie.insert("These", 2);
        trie.insert("Trie", 7);


        trie.getByPrefix("Tr").forEach(x->{

        });
    }
}
