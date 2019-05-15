using System;
using System.Collections.Generic;

public static class Heap<T> where T : IComparable<T>
{
    public static void Sort(T[] arr)
    {
        //Creates max heap
        for (int i = 0; i < arr.Length; i++)
        {
            HeapifyUp(i, arr);
        }

        for (int i = arr.Length - 1; i >= 0; i--)
        {
            Swap(0, i, arr);
            HeapifyDown(0, arr, i);
        }
        Console.WriteLine();
    }

    private static void HeapifyDown(int elementIndex, T[] heap, int border)
    {

        while (elementIndex < border / 2)
        {
            T element = heap[elementIndex];
            int child = 2 * elementIndex + 1;

            if (child + 1 < border && IsLess(heap,child,child + 1))
            {
                child++;
            }

            if (IsLess(heap, child, elementIndex))
            {
                break;
            }

            Swap(elementIndex,child,heap);
            elementIndex = child;
        }
    }

    private static bool IsLess(T[] heap, int element1Index, int element2Index)
    {
        return heap[element1Index].CompareTo(heap[element2Index]) < 0;
    }

    private static void HeapifyUp(int elementIndex, T[] heap)
    {
        T element = heap[elementIndex];
        int parentIndex = (elementIndex - 1) / 2;
        T parent = heap[parentIndex];

        while (element.CompareTo(parent) > 0)
        {
            Swap(elementIndex, parentIndex, heap);

            //update values
            elementIndex = parentIndex;
            parentIndex = (elementIndex - 1) / 2;
            element = heap[elementIndex];
            parent = heap[parentIndex];
        }
    }

    private static void Swap(int index1, int index2, T[] heap)
    {
        T index1Element = heap[index1];

        heap[index1] = heap[index2];
        heap[index2] = index1Element;
    }

}
