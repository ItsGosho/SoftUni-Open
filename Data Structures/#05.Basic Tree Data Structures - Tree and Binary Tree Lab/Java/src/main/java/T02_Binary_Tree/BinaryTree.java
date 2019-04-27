package T02_Binary_Tree;

import T01_Tree.Tree;

import java.util.Stack;
import java.util.function.Consumer;

public class BinaryTree<T> {

    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree(T value) {
        this.value = value;
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this(value);
        this.leftChild = child;
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightCHild) {
        this(value, leftChild);
        this.rightChild = rightCHild;
    }

    // append output to builder
    public String printIndentedPreOrder(int indent, StringBuilder builder) {

        this.preOder(this, indent-2, builder);

        builder.replace(0,1,"");
        builder.append("\n");
        return builder.toString();
    }

    private void preOder(BinaryTree<T> binaryTree, int indent, StringBuilder builder) {

        indent+=2;
        while (binaryTree != null) {
            builder.append(repeat(indent,binaryTree.value,true));
            this.preOder(binaryTree.leftChild, indent, builder);
            this.preOder(binaryTree.rightChild, indent, builder);
            return;
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

    public void eachInOrder(Consumer<T> consumer) {
        this.inOrder(this,consumer);
    }

    private void inOrder(BinaryTree<T> binaryTree,Consumer<T> consumer){

        while (binaryTree != null){
            this.inOrder(binaryTree.leftChild,consumer);
            consumer.accept(binaryTree.value);
            this.inOrder(binaryTree.rightChild,consumer);
            return;
        }

    }

    public void eachPostOrder(Consumer<T> consumer) {
        this.inPostOrder(this,consumer);
    }

    private void inPostOrder(BinaryTree<T> binaryTree,Consumer<T> consumer){

        while (binaryTree != null){
            this.inPostOrder(binaryTree.leftChild,consumer);
            this.inPostOrder(binaryTree.rightChild,consumer);
            consumer.accept(binaryTree.value);
            return;
        }

    }

}
