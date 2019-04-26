using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class ArrayStack<T>
{
    private const int InitialCapacity = 16;

    private T[] elements;
    private int count;
    private int capacity;

    public int Count { get => count; set => count = value; }
    public int Capacity { get => capacity; set => capacity = value; }

    public ArrayStack(int capacity = InitialCapacity)
    {
        this.capacity = capacity;
        this.count = 0;
        this.elements = new T[this.capacity];
    }
    public void Push(T element)
    {

        if (this.count > this.capacity - 1)
        {
            this.Grow();
        }

        this.elements[this.count] = element;
        count++;
    }

    public T Pop()
    {
        if (count == 0)
        {
            throw new InvalidOperationException();
        }

        count--;
        T element = this.elements[this.count];
        this.elements[this.count] = default(T);

        return element;
    }
    public T[] ToArray()
    {
        T[] resultedArray = new T[this.count];
        this.CopyAllElementsTo(ref resultedArray);
        this.Reverse(ref resultedArray);
        return resultedArray;
    }
    private void Grow()
    {
        this.capacity *= 2;
        T[] newArray = new T[this.capacity];
        this.CopyAllElementsTo(ref newArray);
        this.elements = newArray;

    }

    private void Reverse(ref T[] array)
    {
        T[] newArr = new T[array.Length];
        int counter = 0;
        for (int i = array.Length - 1; i >= 0; i--)
        {
            newArr[counter] = array[i];
            counter++;
        }

        array = newArr;
    }

    private void CopyAllElementsTo(ref T[] array)
    {

        for (int i = 0; i < this.count; i++)
        {
            array[i] = this.elements[i];
        }

    }
}

