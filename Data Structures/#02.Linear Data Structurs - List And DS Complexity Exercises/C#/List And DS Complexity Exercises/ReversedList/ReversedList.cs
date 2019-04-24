using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class ReversedList<T> : IEnumerable<T>
{
    private const int InitialCapacity = 2;

    private int capacity;
    private int count;
    private T[] elements;
    private int head;

    public ReversedList()
    {
        this.capacity = InitialCapacity;
        this.elements = new T[this.capacity];
    }

    public int Count
    {
        get
        {
            return this.count;
        }

        private set
        {

        }

    }

    public int Capacity
    {
        get
        {
            return this.capacity;
        }

        private set
        {

        }
    }

    public void Add(T item)
    {

        if (this.count == this.capacity)
        {
            this.Grow();
        }

        this.elements[this.count] = item;
        this.count++;

    }

    public T this[int index]
    {
        get
        {
            if (index > this.count - 1)
            {
                throw new IndexOutOfRangeException();
            }

            T[] reversedElements = this.GetInReversedOrder().ToArray();
            return reversedElements[index];
        }

        set
        {
            if (index > this.count - 1)
            {
                throw new IndexOutOfRangeException();
            }
            T[] reversedElements = this.GetInReversedOrder().ToArray();
            reversedElements[index] = value;
           
            this.elements = this.Reverse(reversedElements);
        }
    }

    public T RemoveAt(int index)
    {
        if (index >= this.count)
        {
            throw new IndexOutOfRangeException();
        }

        T removedElement = this.elements[index];
        T[] newArray = new T[this.capacity];

        this.MakeCopyWithoutSpecificIndex(newArray, index);

        this.elements = newArray;
        this.count--;
        this.Shrink();

        return removedElement;
    }

    private void MakeCopyWithoutSpecificIndex(T[] arrayToFill, int index)
    {

        int newArrayCounter = 0;
        int oldArrayCounter = 0;
        T[] reversedElements = this.GetInReversedOrder().ToArray();
        while (newArrayCounter != this.count - 1)
        {

            if (oldArrayCounter != index)
            {
                arrayToFill[newArrayCounter] = reversedElements[oldArrayCounter];
                newArrayCounter++;
            }

            oldArrayCounter++;
        }
        
        this.elements = this.Reverse(arrayToFill);
    }

    private void Shrink()
    {

        if (this.count <= this.elements.Length / 2 && this.count != 0)
        {
            T[] newArray = new T[this.capacity / 2];
            this.CopyIntoNewArray(newArray);
            this.elements = newArray;
            this.capacity = this.capacity / 2;
        }
    }

    private void Grow()
    {
        T[] newArray = new T[this.capacity * 2];

        this.CopyIntoNewArray(newArray);

        this.elements = newArray;
        this.capacity = this.capacity * 2;
    }

    private void CopyIntoNewArray(T[] arrayToBeFilled)
    {

        for (int i = 0; i < this.count; i++)
        {
            T currentElement = this.elements[i];

            if (currentElement != null)
            {
                arrayToBeFilled[i] = currentElement;
            }
        }

    }

    public void Shift(int index)
    {
        for (int i = index; i < this.capacity; i++)
        {
            this.elements[i] = this.elements[i + 1];
        }
    }

    public IEnumerator<T> GetEnumerator()
    {
        return this.GetInReversedOrder().GetEnumerator();
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetInReversedOrder().GetEnumerator();
    }

    public T[] Elements
    {
        get
        {
            return this.GetInReversedOrder().ToArray();
        }
        private set
        {

        }
    }

    private T[] Reverse(T[] arrayToReverse)
    {
        T[] result = new T[this.capacity];
        int resultCounter = 0;
        for (int i = arrayToReverse.Length-1; i >= 0; i--)
        {
            result[resultCounter] = arrayToReverse[i];
            resultCounter++;
        }

        return result;
    }

    private List<T> GetInReversedOrder()
    {
        List<T> reversedOrder = new List<T>(this.capacity);
        for (int i = this.count - 1; i >= 0; i--)
        {
            reversedOrder.Add(this.elements[i]);
        }

        return reversedOrder;
    }

}
