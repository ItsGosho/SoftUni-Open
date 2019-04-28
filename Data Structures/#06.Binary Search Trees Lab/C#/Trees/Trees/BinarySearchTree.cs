using System;
using System.Collections.Generic;

public class BinarySearchTree<T> where T : IComparable<T>
{

    private BinaryNode<T> root;

    internal BinaryNode<T> Root { get => root; set => root = value; }

    public void Insert(T value)
    {

        if (this.root == null)
        {
            this.root = new BinaryNode<T>(value);
            return;
        }

        if (this.Contains(value))
        {
            return;
        }

        this.Insert(this.root, value);
    }

    private void Insert(BinaryNode<T> binaryNode, T value)
    {
        while (binaryNode != null)
        {
            if (binaryNode.Value.CompareTo(value) < 0)
            {

                if (binaryNode.RightChild == null)
                {
                    binaryNode.RightChild = new BinaryNode<T>(value, binaryNode);
                    return;
                }
                this.Insert(binaryNode.RightChild, value);
            }
            else
            {
                if (binaryNode.LeftChild == null)
                {
                    binaryNode.LeftChild = new BinaryNode<T>(value, binaryNode);
                    return;
                }
                this.Insert(binaryNode.LeftChild, value);
            }
            return;
        }

    }

    public bool Contains(T value)
    {
        bool contains = false;
        this.EachInOrder(x =>
        {
            if (x.Equals(value))
            {
                contains = true;
                return;
            }
        });

        return contains;
    }

    public void DeleteMin()
    {
        if (this.root == null)
        {
            return;
        }

        if (this.root.LeftChild == null)
        {
            this.root = this.root.RightChild;
            return;
        }

        this.DeleteMin(this.root);
    }

    private void DeleteMin(BinaryNode<T> binaryNode)
    {
        while (binaryNode != null)
        {
            if (binaryNode.LeftChild == null)
            {
                binaryNode.Father.LeftChild = null;
                binaryNode.Father.LeftChild = binaryNode.RightChild;
            }

            this.DeleteMin(binaryNode.LeftChild);
            return;
        }
    }



    public BinarySearchTree<T> Search(T item)
    {
        BinaryNode<T> result = new BinaryNode<T>();

        this.Search(item, ref result, this.root);

        if (result.Value.Equals(default(T)))
        {
            return null;
        }

        BinarySearchTree<T> binarySearchTree = new BinarySearchTree<T>();
        binarySearchTree.Root = result;
        return binarySearchTree;
    }

    private void Search(T item, ref BinaryNode<T> result, BinaryNode<T> binaryNode)
    {
        while (binaryNode != null)
        {

            if (binaryNode.Value.Equals(item))
            {
                result = binaryNode;
                return;
            }

            if (binaryNode.Value.CompareTo(item) < 0)
            {
                this.Search(item, ref result, binaryNode.RightChild);
            }
            else
            {
                this.Search(item, ref result, binaryNode.LeftChild);
            }
            return;
        }

    }

    public IEnumerable<T> Range(T startRange, T endRange)
    {
        List<T> result = new List<T>();
        this.Range(this.root, result, startRange, endRange);
        return result;
    }

    private void Range(BinaryNode<T> binaryNode, List<T> result, T startRange, T endRange)
    {

        while (binaryNode != null)
        {

            if (binaryNode.Value.CompareTo(startRange) >= 0)
                this.Range(binaryNode.LeftChild, result, startRange, endRange);

            if (binaryNode.Value.CompareTo(startRange) >= 0 && binaryNode.Value.CompareTo(endRange) <= 0)
            {
                result.Add(binaryNode.Value);
            }


            if (binaryNode.Value.CompareTo(endRange) <= 0)
                this.Range(binaryNode.RightChild, result, startRange, endRange);

            return;
        }
    }

    public void EachInOrder(Action<T> action)
    {
        this.InOrder(this.root, action);
    }

    private void InOrder(BinaryNode<T> binaryNode, Action<T> action)
    {
        while (binaryNode != null)
        {
            this.InOrder(binaryNode.LeftChild, action);
            action.Invoke(binaryNode.Value);
            this.InOrder(binaryNode.RightChild, action);
            return;
        }
    }
}