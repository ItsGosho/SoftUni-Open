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

        Swap(0, 1, arr);
        Console.WriteLine();
    }

    private static void HeapifyDown(int elementIndex, T[] heap, int border)
    {

        while (true)
        {
            bool change = false;
            int leftChildIndex = (2 * elementIndex) + 1;
            int rightChildIndex = (2 * elementIndex) + 2;

            T element = heap[elementIndex];

            if (!IsHeapifyDownPossibleRoute(element, leftChildIndex,rightChildIndex,heap))
            {
                break;
            }

            if (IsHeapifyDownLeft(leftChildIndex,rightChildIndex,heap,border))
            {
                change = HeapifyDownLeft(elementIndex,leftChildIndex,heap);
            }
            else if (IsHeapifyDownRight(leftChildIndex,rightChildIndex,heap,border))
            {
                change = HeapifyDownLeft(elementIndex, rightChildIndex, heap);
            }

            if (!change)
            {
                break;
            }
        }
    }

    private static bool IsHeapifyDownPossibleRoute(T element, int leftChildIndex, int rightChildIndex,T[] heap)
    {
        T leftChild = heap[leftChildIndex];
        T rightChild = heap[rightChildIndex];

        if (element.CompareTo(rightChild) >= 0 && element.CompareTo(leftChild) >= 0)
        {
            return false;
        }

        return true;
    }

    private static bool IsHeapifyDownRight(int leftChildIndex, int rightChildIndex, T[] heap, int border)
    {
        T leftChild = heap[leftChildIndex];
        T rightChild = heap[rightChildIndex];

        if (leftChild.CompareTo(rightChild) < 0 && rightChildIndex < border)
        {
            return true;
        }

        return false;
    }

    private static bool IsHeapifyDownLeft(int leftChildIndex,int rightChildIndex,T[] heap,int border)
    {
        T leftChild = heap[leftChildIndex];
        T rightChild = heap[rightChildIndex];

        if (leftChild.CompareTo(rightChild) >= 0 && leftChildIndex < border)
        {
            return true;
        }

        return false;
    }

    private static bool HeapifyDownLeft(int elementIndex,int leftChildIndex,T[] heap)
    {
        Swap(elementIndex, leftChildIndex, heap);
        elementIndex = leftChildIndex;
        return true;
    }

    private static bool HeapifyDownRight(int elementIndex, int rightChildIndex, T[] heap)
    {
        Swap(elementIndex, rightChildIndex, heap);
        elementIndex = rightChildIndex;
        return true;
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
