using System;

public class CircularQueue<T>
{
    private const int DefaultCapacity = 4;
    private T[] elements;
    private int startIndex;
    private int endIndex;
    private int count;
    private int capacity;

    public int Count { get => count; set => count = value; }
    public int Capacity { get => capacity; set => capacity = value; }

    public CircularQueue(int capacity = DefaultCapacity)
    {
        this.capacity = capacity;
        this.startIndex = 0;
        this.endIndex = 0;
        this.elements = new T[this.capacity];
    }

    public void Enqueue(T element)
    {
       
        //If the queue is full
        if (this.count > this.capacity - 1)
        {
            this.Resize();
            this.endIndex = this.count;
            this.startIndex = 0;
        }

        //If the queue endIndex is at the end ,but there is empty space at the start
        if (this.endIndex > this.capacity -1)
        {
            this.endIndex = 0;
        }

        this.elements[endIndex] = element;
        this.endIndex++;
        this.count++;

        //If the queue endIndex is at the startIndex
        if (this.endIndex == this.startIndex)
        {
            this.endIndex--;
            this.Resize();
            this.endIndex = this.count;
            this.startIndex = 0;
        }
    }

    private void Resize()
    {
        this.capacity *= 2;
        T[] newArray = new T[this.capacity];
        this.CopyAllElements(newArray);

        this.elements = newArray;
    }

    private void CopyAllElements(T[] newArray)
    {
        
        if (this.endIndex > this.startIndex)
        {
            int counter = 0;
            for (int i = this.startIndex; i < this.endIndex; i++)
            {
                newArray[counter] = this.elements[i];
                counter++;
            }
            return;
        }

        if (this.endIndex < this.startIndex)
        {

            int counter = 0;
            for (int i = startIndex; i < this.capacity; i++)
            {
                newArray[counter] = this.elements[i];
                counter++;
            }

            if (endIndex == 1)
            {
                newArray[counter] = this.elements[0];
                return;
            }

            for (int i = 0; i <= this.endIndex; i++)
            {
                newArray[counter] = this.elements[i];
                counter++;
            }
            return;
        }
    }

    // Should throw InvalidOperationException if the queue is empty
    public T Dequeue()
    {

        if (this.count == 0)
        {
            throw new InvalidOperationException();
        }
        
        T removedElement = this.elements[this.startIndex];
        this.elements[startIndex] = default(T);
        this.startIndex++;
        this.count--;

      

        if (startIndex > this.capacity -1)
        {
            this.Resize();
        }

        return removedElement;
    }

    public T[] ToArray()
    {
        T[] newArr = new T[this.count];
        T[] copiedElement = new T[this.count];
        this.CopyAllElements(copiedElement);

        int counter = 0;
        for (int i = copiedElement.Length-1; i >= 0; i--)
        {
            newArr[counter] = copiedElement[i];
            counter++;
        }


        return newArr;
    }
}
