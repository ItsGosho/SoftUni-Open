using System;
using System.Collections.Generic;

public class RedBlackTree<T> : IBinarySearchTree<T> where T : IComparable
{
    private Node root;
    private const bool RED = false;
    private const bool BLACK = true;


    private RedBlackTree(Node node)
    {
        this.PreOrderCopy(node);
    }

    public RedBlackTree()
    {
    }

    private Node FindElement(T element)
    {
        Node current = this.root;

        while (current != null)
        {
            if (current.Value.CompareTo(element) > 0)
            {
                current = current.Left;
            }
            else if (current.Value.CompareTo(element) < 0)
            {
                current = current.Right;
            }
            else
            {
                break;
            }
        }

        return current;
    }

    private void PreOrderCopy(Node node)
    {
        if (node == null)
        {
            return;
        }

        this.Insert(node.Value);
        this.PreOrderCopy(node.Left);
        this.PreOrderCopy(node.Right);
    }

    private Node Insert(T element, Node node)
    {
        if (node == null)
        {
            node = new Node(element);
        }
        else if (element.CompareTo(node.Value) < 0)
        {
            node.Left = this.Insert(element, node.Left);
        }
        else if (element.CompareTo(node.Value) > 0)
        {
            node.Right = this.Insert(element, node.Right);
        }

        if (this.IsFlipColor(node))
        {
            this.FlipColor(node);
        }

        if (this.IsLeftRotation(node))
        {
            node = this.RotateLeft(node);
        }

        if (this.IsRightRotation(node))
        {
            node = this.RotateRight(node);
        }

        node.Count = 1 + this.Count(node.Left) + this.Count(node.Right);
        return node;
    }


    //-----------

    private bool IsLeftRotation(Node node)
    {

        if (node == null || node.Right == null)
        {
            return false;
        }

        if (node.Right.Color == RED && (node.Left == null || node.Left.Color == BLACK))
        {
            return true;
        }

        return false;
    }

    private bool IsRightRotation(Node node)
    {

        if (node == null || node.Left == null)
        {
            return false;
        }

        if (node.Left.Left == null)
        {
            return false;
        }

        if (node.Left.Color == RED && node.Left.Left.Color == RED && (node.Right == null || node.Right.Color == BLACK))
        {
            return true;
        }

        return false;
    }

    private bool IsFlipColor(Node node)
    {
        if (node == null)
        {
            return false;
        }

        if (node.Left == null || node.Right == null)
        {
            return false;
        }

        if (node.Left.Color == RED & node.Right.Color == RED)
        {
            return true;
        }

        return false;
    }


    private Node RotateLeft(Node father)
    {
        Node temp = father.Right;
        father.Right = temp.Left;
        temp.Left = father;
        temp.Left.Color = RED;
        temp.Color = BLACK;

        return temp;
    }

    private Node RotateRight(Node father)
    {
        Node temp = father.Left;
        father.Left = temp.Right;
        temp.Right = father;
        temp.Right.Color = RED;
        temp.Color = BLACK;

        return temp;
    }

    private void FlipColor(Node father)
    {
        father.Left.Color = BLACK;
        father.Right.Color = BLACK;

        if (!this.root.Equals(father))
        {
            father.Color = RED;
        }
    }

    //-------------

    private void EachInOrder(Node node, Action<T> action)
    {
        if (node == null)
        {
            return;
        }

        this.EachInOrder(node.Left, action);
        action(node.Value);
        this.EachInOrder(node.Right, action);
    }

    private int Count(Node node)
    {
        if (node == null)
        {
            return 0;
        }

        return node.Count;
    }

    public void Insert(T element)
    {

        if (this.root == null)
        {
            this.root = new Node(element,BLACK);
            this.root.Count++;
            return;
        }

        this.root = this.Insert(element, this.root);
    }

    public bool Contains(T element)
    {
        Node current = this.FindElement(element);

        return current != null;
    }

    public void EachInOrder(Action<T> action)
    {
        this.EachInOrder(this.root, action);
    }

    public IBinarySearchTree<T> Search(T element)
    {
        Node current = this.FindElement(element);

        return new RedBlackTree<T>(current);
    }

    private Node FindMin(Node node)
    {
        if (node.Left == null)
        {
            return node;
        }

        return this.FindMin(node.Left);
    }

    public int Count()
    {
        return this.Count(this.root);
    }

    private class Node : ICloneable
    {

        public Node(T value)
        {
            this.Value = value;
        }

        public Node(T value, bool color)
        {
            this.Value = value;
            this.Color = color;
        }

        public T Value { get; set; }
        public bool Color { get; set; }
        public Node Left { get; set; }
        public Node Right { get; set; }

        public int Count { get; set; }

        public object Clone()
        {
            return this.MemberwiseClone();
        }
    }

}
