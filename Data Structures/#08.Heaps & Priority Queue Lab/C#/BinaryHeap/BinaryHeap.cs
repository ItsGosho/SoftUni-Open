using System;
using System.Collections.Generic;

public class BinaryHeap<T> where T : IComparable<T>
{
    private List<T> heap;
    private int count;

    public BinaryHeap()
    {
        this.count = 0;
        this.heap = new List<T>();
    }

    public int Count
    {
        get
        {
            return this.count;
        }
    }

    //Parent (index - 1) / 2
    //Left Child (2 * index + 1)
    //Right Child (2 * index + 2)
    public void Insert(T item)
    {
        this.heap.Add(item);
        this.HeapifyUp(this.count);
        this.count++;

    }

    private void HeapifyUp(int elementIndex)
    {
        T element = this.heap[elementIndex];
        int parentIndex = (elementIndex - 1) / 2;
        T parent = this.heap[parentIndex];

        while (element.CompareTo(parent) > 0)
        {
            this.Swap(elementIndex, parentIndex);

            //update values
            elementIndex = parentIndex;
            parentIndex = (elementIndex - 1) / 2;
            element = this.heap[elementIndex];
            parent = this.heap[parentIndex];
        }
    }

    private void Swap(int index1, int index2)
    {
        T index1Element = this.heap[index1];

        this.heap[index1] = this.heap[index2];
        this.heap[index2] = index1Element;
    }

    public T Peek()
    {
        return this.heap[0];
    }

    public T Pull()
    {
        if (this.count == 0)
            throw new InvalidOperationException();

        T element = this.heap[0];
        this.Swap(0, this.count - 1);
        this.heap.RemoveAt(this.count - 1);
        this.HeapifyDown(0);
        this.count--;

        return element;
    }

    private void HeapifyDown(int elementIndex)
    {

        while (true)
        {

            int leftChildIndex = (2 * elementIndex) + 1;
            int rightChildIndex = (2 * elementIndex) + 2;

            if (leftChildIndex > this.heap.Count-1 || rightChildIndex > this.heap.Count-1) break;

            T element = this.heap[elementIndex];
            T leftChild = this.heap[leftChildIndex];
            T rightChild = this.heap[rightChildIndex];

            if (element.CompareTo(rightChild) > 0 && element.CompareTo(leftChild) > 0)
            {
                break;
            }

            if (leftChild.CompareTo(element) >= 0)
            {
                this.Swap(elementIndex, (2 * elementIndex) + 1);
                elementIndex = (2 * elementIndex) + 1;
            }
            else if (rightChild.CompareTo(element) < 0)
            {
                this.Swap(elementIndex, (2 * elementIndex) + 2);
                elementIndex = (2 * elementIndex) + 2;
            }

        }
    }

}
