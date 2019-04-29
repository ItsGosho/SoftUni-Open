import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {

    private T value;
    private Tree<T> father;
    private List<Tree<T>> children;

    public Tree() {

    }

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();

        for (Tree<T> child : children) {
            child.father = this;
            this.children.add(child);
        }
    }

    public void attachChildren(T father, T value) {
        //Ako nqma value-ta father-a stava root
        //Posle shte napravq BFS za da namera father-a
        //kato namerq fathera shete mu sloja Tree s novoto value
    }

    // append output to builder
    public String print(int indent, StringBuilder builder) {

        StringBuilder result = new StringBuilder();
        result.append(repeat(indent, this.value, false));
        this.printer(indent + 1, result, this);
        result.append("\n");

        return result.toString();
    }

    private void printer(int indent, StringBuilder builder, Tree<T> tree) {

        indent *= 2;
        for (Tree<T> child : tree.children) {

            builder.append(repeat(indent, child.value, true));

            printer(indent, builder, child);
        }

    }

    private String repeat(int times, Object str, boolean appendNewLine) {
        StringBuilder result = new StringBuilder();

        if (appendNewLine) result.append("\n");

        for (int i = 0; i < times; i++) {
            result.append(" ");
        }

        result.append(str);
        return result.toString();
    }

    public void each(Consumer<T> consumer) {
        throw new UnsupportedOperationException();
    }

    public Iterable<T> orderDFS() {
        List<T> result = new ArrayList<>();
        Stack<T> stack = new Stack<>();
        stack.push(this.value);
        this.DFS(this, stack, result);

        return result;
    }

    private void DFS(Tree<T> tree, Stack<T> stack, List<T> result) {

        for (Tree<T> child : tree.children) {

            stack.push(child.value);
            this.DFS(child, stack, result);
        }

        result.add(stack.pop());
    }

    public Iterable<T> orderBFS() {
        Queue<Tree<T>> childToTraverse = new ArrayDeque<>();
        childToTraverse.addAll(this.children);

        return this.BFS(childToTraverse);
    }

    private List<T> BFS(Queue<Tree<T>> childToTraverse) {

        List<T> result = new ArrayList<>();
        result.add(this.value);
        while (childToTraverse.size() != 0) {
            Tree<T> tree = childToTraverse.poll();
            result.add(tree.value);

            childToTraverse.addAll(tree.children);
        }

        return result;
    }

}