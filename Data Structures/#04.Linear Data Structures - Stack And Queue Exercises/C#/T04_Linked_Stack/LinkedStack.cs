using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class LinkedStack<T>
{
    private Node<T> firstNode;
    private int count;

    public int Count { get => count; set => count = value; }

    public void Push(T element)
    {
        if (this.count == 0)
        {
            firstNode = new Node<T>(element);
            count++;
            return;
        }

        firstNode = new Node<T>(element, firstNode);
        count++;

    }
    public T Pop()
    {

        if (this.count == 0)
        {
            throw new InvalidOperationException();
        }

        T element = firstNode.Value;
        firstNode = firstNode.NextNode;
        count--;
        return element;
    }
    public T[] ToArray()
    {
        T[] result = new T[this.count];

        Node<T> currentNode = this.firstNode;

        int counter = 0;
        while (currentNode != null)
        {
            result[counter] = currentNode.Value;
            currentNode = currentNode.NextNode;
            counter++;
        }
        return result;
    }
    private class Node<T>
    {
        private T value;
        public Node<T> NextNode { get; set; }
        public T Value { get => value; set => this.value = value; }

        public Node(T value, Node<T> nextNode = null)
        {
            this.value = value;
            this.NextNode = nextNode;
        }
    }
}

