using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class LinkedQueue<T>
{
    private QueueNode<T> head;
    private QueueNode<T> tail;
    private int count;

    public int Count { get => count; set => count = value; }

    public void Enqueue(T element)
    {
        if (this.count == 0)
        {
            QueueNode<T> firstNode = new QueueNode<T>(element);
            this.head = firstNode;
            this.tail = firstNode;
            count++;
            return;
        }

        QueueNode<T> newNode = new QueueNode<T>(element);
        this.head.NextNode = newNode;
        newNode.PrevNode = this.head;
        this.head = newNode;
        count++;

    }
    public T Dequeue()
    {

        if (this.count == 0)
        {
            throw new InvalidOperationException();
        }

        T element = this.tail.Value;

        if (this.count == 1)
        {
            this.head = this.tail = null;
            count--;
            return element;
        }


        this.tail.NextNode.PrevNode = null;
        this.tail = this.tail.NextNode;
        count--;

        return element;
    }
    public T[] ToArray()
    {

        T[] result = new T[this.count];
        QueueNode<T> currentNode = this.tail;

        int counter = 0;
        while (currentNode != null)
        {
            result[counter] = currentNode.Value;
            currentNode = currentNode.NextNode;
            counter++;
        }

        return result;
    }

    private class QueueNode<T>
    {
        public T Value { get; private set; }

        public QueueNode<T> NextNode { get; set; }
        public QueueNode<T> PrevNode { get; set; }

        public QueueNode(T value, QueueNode<T> nextNode = null, QueueNode<T> prevNode = null)
        {
            Value = value;
            NextNode = nextNode;
            PrevNode = prevNode;
        }
    }
}
