using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class ArrayList<T>
{
    private const int InitialCapacity = 2;

    private int capacity;
    private int count;
    private T[] elements;

    public ArrayList()
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

            return this.elements[index];
        }

        set
        {
            if (index > this.count - 1)
            {
                throw new IndexOutOfRangeException();
            }
            this.elements[index] = value;
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
        while (newArrayCounter != this.count - 1)
        {

            if (oldArrayCounter != index)
            {
                arrayToFill[newArrayCounter] = this.elements[oldArrayCounter];
                newArrayCounter++;
            }

            oldArrayCounter++;
        }
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

    public T[] Elements
    {
        get
        {
            return this.elements;
        }
        private set
        {

        }
    }

}
