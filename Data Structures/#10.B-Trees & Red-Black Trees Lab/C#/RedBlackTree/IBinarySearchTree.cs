using System;
using System.Collections.Generic;

public interface IBinarySearchTree<T> where T : IComparable
{
    //Basic Tree Operations
    void Insert(T element);
    void EachInOrder(Action<T> action);

    //Binary Search Tree Operations
    int Count();
}