using System;
using System.Collections;
using System.Collections.Generic;

public class DoublyLinkedList<T> : IEnumerable<T>
{
    private Node<T> head;
    private Node<T> tail;
    private int count;

    public int Count { get => count; set => count = value; }

    public void AddFirst(T element)
    {
        if (this.count == 0)
        {
            Node<T> firstNode = new Node<T>(element, null, null);
            this.head = firstNode;
            this.tail = firstNode;
            this.count++;
            return;
        }

        Node<T> newNode = new Node<T>(element, this.head, null);
        this.head.PreviousNode = newNode;
        this.head = newNode;
        this.count++;

    }

    public void AddLast(T element)
    {
        if (this.count == 0)
        {
            Node<T> firstNode = new Node<T>(element, null, null);
            this.head = firstNode;
            this.tail = firstNode;
            this.count++;
            return;
        }

        Node<T> newNode = new Node<T>(element, null, this.tail);
        this.tail.NextNode = newNode;
        this.tail = newNode;
        this.count++;

    }

    public T RemoveFirst()
    {

        if (this.count == 0)
        {
            throw new InvalidOperationException();
        }

        T element = this.head.Value;

        if (this.count == 1)
        {
            this.head = null;
            this.tail = null;
            this.count = 0;
            return element;
        }

        Node<T> newNode = this.head.NextNode;
        this.head.PreviousNode = null;
        newNode.PreviousNode = null;
        this.head = newNode;
        this.count--;

        return element;
    }

    public T RemoveLast()
    {
        if (this.count == 0)
        {
            throw new InvalidOperationException();
        }

        T element = this.head.Value;

        if (this.count == 1)
        {
            this.head = null;
            this.tail = null;
            this.count = 0;
            return element;
        }

        Node<T> newNode = this.tail.PreviousNode;
        this.tail.NextNode = null;
        newNode.NextNode = null;
        this.tail = newNode;
        this.count--;

        return element;
    }

    public void ForEach(Action<T> action)
    {
        Node<T> currentNode = this.head;

        while (currentNode != null)
        {
            action.Invoke(currentNode.Value);
            currentNode = currentNode.NextNode;
        }
    }

    public IEnumerator<T> GetEnumerator()
    {
        List<T> result = new List<T>();
        result.AddRange(this.ToArray());
        return result.GetEnumerator();
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.ToArray().GetEnumerator();
    }

    public T[] ToArray()
    {

        T[] arr = new T[this.count];
        Node<T> currentNode = this.head;

        int counter = 0;
        while (currentNode != null)
        {
            arr[counter] = currentNode.Value;
            currentNode = currentNode.NextNode;
            counter++;
        }

        return arr;
    }
}
