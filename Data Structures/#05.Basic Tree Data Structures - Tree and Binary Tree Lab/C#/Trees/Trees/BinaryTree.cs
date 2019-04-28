using System;

public class BinaryTree<T>
{
    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree(T value, BinaryTree<T> leftChild = null, BinaryTree<T> rightChild = null)
    {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public void PrintIndentedPreOrder(int indent = 0)
    {
        string result = "";
        this.PreOrder(this,indent, ref result);
        Console.Write(result);
    }

    private void PreOrder(BinaryTree<T> binaryTree, int indent, ref string res)
    {
        while (binaryTree != null)
        {
            res += new String(' ', indent) + binaryTree.value + '\n';
            indent += 2;
            this.PreOrder(binaryTree.leftChild, indent, ref res);
            this.PreOrder(binaryTree.rightChild, indent, ref res);
            return;
        }
    }

    public void EachInOrder(Action<T> action)
    {
        this.InOrder(this,action);
    }

    private void InOrder(BinaryTree<T> binaryTree, Action<T> action)
    {
        while (binaryTree != null)
        {
            this.InOrder(binaryTree.leftChild,action);
            action.Invoke(binaryTree.value);
            this.InOrder(binaryTree.rightChild,action);
            return;
        }
    }

    public void EachPostOrder(Action<T> action)
    {
        this.PostOrder(this, action);
    }

    private void PostOrder(BinaryTree<T> binaryTree, Action<T> action)
    {
        while (binaryTree != null)
        {
            this.PostOrder(binaryTree.leftChild, action);
            this.PostOrder(binaryTree.rightChild, action);
            action.Invoke(binaryTree.value);
            return;
        }

    }
}
