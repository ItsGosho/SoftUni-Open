import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {

    private T value;
    private Tree<T> father;
    private List<Tree<T>> children;

    public Tree() {
        this.children = new ArrayList<>();
    }

    public Tree(T value, Tree<T> father) {
        this();
        this.value = value;
        this.father = father;
    }

    public Tree(T value, Tree<T>... children) {
        this();
        this.value = value;

        for (Tree<T> child : children) {
            child.father = this;
            this.children.add(child);
        }
    }

    public void attachChildren(T father, T value) {

        if (this.value == null) {
            this.value = father;
            this.children.add(new Tree<>(value, this));
            return;
        }

        if (this.value == father) {
            this.children.add(new Tree<>(value, this));
            return;
        }

        Queue<Tree<T>> childToTraverse = new ArrayDeque<>(this.children);
        this.attachChildren(childToTraverse, father, value);
    }

    //BFS
    private void attachChildren(Queue<Tree<T>> childToTraverse, T fatherValue, T newChildrenValue) {

        while (childToTraverse.size() != 0) {
            Tree<T> tree = childToTraverse.poll();

            if (tree.value == fatherValue) {
                tree.children.add(new Tree<>(newChildrenValue, tree));
            }

            childToTraverse.addAll(tree.children);
        }

    }

    public Tree<T> getRootNode() {
        Queue<Tree<T>> childToTraverse = new ArrayDeque<>(this.children);
        return this;
    }

    public List<Tree<T>> getLeafNodes() {
        Queue<Tree<T>> childToTraverse = new ArrayDeque<>(this.children);

        List<Tree<T>> result = new ArrayList<>();

        if (this.children.size() == 0) {
            result.add(this);
        }

        while (childToTraverse.size() != 0) {
            Tree<T> tree = childToTraverse.poll();

            if (tree.children.size() == 0) {
                result.add(tree);
            }

            childToTraverse.addAll(tree.children);
        }

        return result;
    }

    public List<Tree<T>> getMiddleNodes() {
        Queue<Tree<T>> childToTraverse = new ArrayDeque<>(this.children);

        List<Tree<T>> result = new ArrayList<>();

        if (this.children.size() != 0 && this.father != null) {
            result.add(this);
        }

        while (childToTraverse.size() != 0) {
            Tree<T> tree = childToTraverse.poll();

            if (tree.children.size() != 0 && tree.father != null) {
                result.add(tree);
            }

            childToTraverse.addAll(tree.children);
        }

        return result;
    }

    public Tree<T> getDeepestNode() {
        Map<Tree<T>, Integer> result = new LinkedHashMap<>();

        this.getNodesHeight(this, new Stack<>(), result, 0);

        int deepestCount = result.values().stream().max(Integer::compareTo).orElse(null);
        Tree<T> deepestNode = result.entrySet().stream().filter(x -> x.getValue() == deepestCount).findFirst().map(Map.Entry::getKey).orElse(null);

        return deepestNode;
    }

    public Stack<Tree<T>> getLongestPath() {
        Stack<Tree<T>> result = new Stack<>();

        Tree<T> currentNode = this.getDeepestNode();
        while (currentNode != null) {
            result.add(currentNode);
            currentNode = currentNode.father;
        }

        return result;
    }

    private void getNodesHeight(Tree<T> tree, Stack<Tree<T>> stack, Map<Tree<T>, Integer> result, Integer deep) {

        deep++;
        for (Tree<T> child : tree.children) {

            stack.push(child);
            this.getNodesHeight(child, stack, result, deep);
        }

        if (!stack.isEmpty() && stack.peek().children.size() == 0) {
            Tree<T> deepestTree = stack.pop();
            result.put(deepestTree, deep);
        }
    }

    public List<Tree<T>> getChildrensWithSumOfFathers(T sum) {
        List<Tree<T>> result = new ArrayList<>();
        Queue<Tree<T>> childToTraverse = new ArrayDeque<>(this.children);

        if (this.value == sum) {
            result.add(this);
        }

        while (childToTraverse.size() != 0) {
            Tree<T> tree = childToTraverse.poll();

            if (tree.getSumOfFathers() == sum) {
                result.add(tree);
            }

            childToTraverse.addAll(tree.children);
        }


        return result;
    }

    private Integer getSumOfFathers() {
        Integer sum = (Integer) this.value;

        Tree<T> current = this.father;
        while (current != null) {
            sum += (Integer) current.getValue();
            current = current.father;
        }

        return sum;
    }

    public List<Tree<T>> getAllSubTrees() {
        Queue<Tree<T>> childToTraverse = new ArrayDeque<>(this.children);
        List<Tree<T>> subTrees = new ArrayList<>();

        if (this.children.size() != 0) {
            subTrees.add(this);
        }

        while (childToTraverse.size() != 0) {
            Tree<T> tree = childToTraverse.poll();

            if (tree.children.size() != 0) {
                subTrees.add(tree);
            }

            childToTraverse.addAll(tree.children);
        }

        return subTrees;
    }

    public void test() {
        
    }

    public Integer getSumOfTrees() {
        int sum = (int) this.value;

        Queue<Tree<T>> childToTraverse = new ArrayDeque<>(this.children);

        while (childToTraverse.size() != 0) {
            Tree<T> children = childToTraverse.poll();
            sum += (int) children.value;

            childToTraverse.addAll(children.children);
        }

        return sum;
    }

    // append output to builder
    public String print(int indent, StringBuilder builder) {

        builder.append(repeat(indent, this.value, false));
        this.printer(indent + 1, builder, this);
        builder.append("\n");

        return builder.toString();
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
        Queue<Tree<T>> childToTraverse = new ArrayDeque<>(this.children);

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

    public List<T> printPreOrder() {
        List<T> result = new ArrayList<>();
        result.add(this.value);
        this.preOrder(this, result);

        return result;
    }

    private void preOrder(Tree<T> tree, List<T> result) {

        for (Tree<T> child : tree.children) {
            result.add(child.value);
            this.preOrder(child, result);
        }
    }

    public T getValue() {
        return value;
    }

    public Tree<T> getFather() {
        return father;
    }
}