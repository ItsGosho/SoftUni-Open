using System;
using System.Collections.Generic;

public class BinarySearchTree<T> : IBinarySearchTree<T> where T : IComparable
{
    private Node root;

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
            node.Count++;
        }
        else if (element.CompareTo(node.Value) > 0)
        {
            node.Right = this.Insert(element, node.Right);
            node.Count++;
        }

        return node;
    }

    private void Range(Node node, Queue<T> queue, T startRange, T endRange)
    {
        if (node == null)
        {
            return;
        }

        int nodeInLowerRange = startRange.CompareTo(node.Value);
        int nodeInHigherRange = endRange.CompareTo(node.Value);

        if (nodeInLowerRange < 0)
        {
            this.Range(node.Left, queue, startRange, endRange);
        }
        if (nodeInLowerRange <= 0 && nodeInHigherRange >= 0)
        {
            queue.Enqueue(node.Value);
        }
        if (nodeInHigherRange > 0)
        {
            this.Range(node.Right, queue, startRange, endRange);
        }
    }

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

    private BinarySearchTree(Node node)
    {
        this.PreOrderCopy(node);
    }

    public BinarySearchTree()
    {
    }

    public void Insert(T element)
    {
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

    public BinarySearchTree<T> Search(T element)
    {
        Node current = this.FindElement(element);

        return new BinarySearchTree<T>(current);
    }

    public void DeleteMin()
    {
        Stack<Node> path = new Stack<Node>();
        if (this.root == null)
        {
            return;
        }

        Node current = this.root;
        Node parent = null;
        while (current.Left != null)
        {
            parent = current;
            path.Push(parent);
            current = current.Left;
        }

        if (parent == null)
        {
            this.root = this.root.Right;
        }
        else
        {
            parent.Left = current.Right;
        }

        while (path.Count != 0)
        {
            path.Pop().updateCount();
        }
    }

    public IEnumerable<T> Range(T startRange, T endRange)
    {
        Queue<T> queue = new Queue<T>();

        this.Range(this.root, queue, startRange, endRange);

        return queue;
    }

    public void Delete(T element)
    {
        if (this.root == null)
        {
            throw new InvalidOperationException();
        }

        bool isDeleted = false;
        Stack<Node> path = new Stack<Node>();
        Queue<Node> nodes = new Queue<Node>();
        nodes.Enqueue(this.root);
        path.Push(nodes.Peek());

        while (nodes.Count != 0)
        {
            Node node = nodes.Peek();

            if (node != null)
            {
                if (node.Value.Equals(element))
                {
                    isDeleted = true;
                    if (node.Right != null)
                    {
                        Node temp = node.Left;
                        node.Value = node.Right.Value;
                        node.Right = node.Right.Right;
                        node.Left = temp;
                        node.updateCount();
                        break;
                    }
                    node.Value = node.Left.Value;
                    node.Left = node.Left.Left;
                    node.updateCount();
                    break;
                }

                if (node.Value.CompareTo(element) < 0)
                {
                    nodes.Enqueue(node.Right);
                    path.Push(node.Right);
                }
                else
                {
                    nodes.Enqueue(node.Left);
                    path.Push(node.Left);
                }
            }
            nodes.Dequeue();
        }

        if (!isDeleted)
        {
            throw new InvalidOperationException();
        }

        while (path.Count != 0)
        {
            path.Pop().updateCount();
        }
    }

    public void DeleteMax()
    {
        if (this.root == null)
        {
            throw new InvalidOperationException();
        }

        if (this.root.Right == null)
        {
            this.root = this.root.Left;
            this.root.updateCount();
            return;
        }

        Stack<Node> path = new Stack<Node>();
        Queue<Node> nodes = new Queue<Node>();
        nodes.Enqueue(this.root);
        path.Push(nodes.Peek());

        while (nodes.Count != 0)
        {
            Node node = nodes.Peek();

            if (node.Right.Right == null)
            {
                node.Right = node.Right.Left;
                break;
            }

            nodes.Enqueue(node.Right);
            path.Push(node.Right);
            nodes.Dequeue();
        }

        while (path.Count != 0)
        {
            path.Pop().updateCount();
        }
    }

    public int Count()
    {
        if (this.root == null)
        {
            return 0;
        }

        return this.root.Count;
    }

    public int Rank(T element)
    {

        int count = 0;
        Queue<Node> nodes = new Queue<Node>();
        nodes.Enqueue(this.root);

        while (nodes.Count != 0)
        {

            Node node = nodes.Peek();

            if (node != null)
                if (element.CompareTo(node.Value) > 0)
                {
                    count++;
                    nodes.Enqueue(node.Left);
                    nodes.Enqueue(node.Right);
                }
                else
                {
                    nodes.Enqueue(node.Left);
                }
            nodes.Dequeue();
        }

        return count;
    }

    public T Select(int rank)
    {
        Queue<Node> nodes = new Queue<Node>();
        nodes.Enqueue(this.root);

        while (nodes.Count != 0)
        {
            Node node = nodes.Peek();

            int result = this.Rank(node.Value);

            if (result.Equals(rank))
            {
                return node.Value;
            }

            if (node.Left != null)
                nodes.Enqueue(node.Left);

            if (node.Right != null)
                nodes.Enqueue(node.Right);

            nodes.Dequeue();
        }

        return default(T);
    }

    public T Ceiling(T element)
    {
        throw new NotImplementedException();
    }

    public T Floor(T element)
    {
        throw new NotImplementedException();
    }

    private class Node
    {

        public Node(T value)
        {
            this.Value = value;
            this.Count = 0;
        }

        public T Value { get; set; }
        public Node Left { get; set; }
        public Node Right { get; set; }

        public void updateCount()
        {
            int leftCount = this.Left != null ? this.Left.Count + 1 : 0;
            int rightCount = this.Right != null ? this.Right.Count + 1 : 0;

            this.Count = leftCount + rightCount;
        }


        public int Count { get; set; }

    }
}