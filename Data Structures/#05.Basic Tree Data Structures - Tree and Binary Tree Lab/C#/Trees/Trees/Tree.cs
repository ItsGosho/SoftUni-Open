using System;
using System.Collections.Generic;

public class Tree<T>
{
    private T value;
    private List<Tree<T>> trees;
    public Tree(T value, params Tree<T>[] children)
    {
        this.trees = new List<Tree<T>>();
        this.value = value;
        this.trees.AddRange(children);
    }

    public void Print(int indent = 0)
    {
        throw new NotImplementedException();
    }

    public void Each(Action<T> action)
    {
        throw new NotImplementedException();
    }

    public IEnumerable<T> OrderDFS()
    {
        Stack<T> stack = new Stack<T>();
        this.DFS(this, stack);
        return stack;
    }

    private void DFS(Tree<T> tree, Stack<T> stack)
    {
        foreach (var item in tree.trees)
        {
            this.DFS(item, stack);
        }
        stack.Push(tree.value);
    }

    public IEnumerable<T> OrderBFS()
    {
        Queue<Tree<T>> trees = new Queue<Tree<T>>();
        List<T> result = new List<T>();
        trees.Enqueue(this);

        this.BFS(trees, result);

        return result;
    }

    private void BFS(Queue<Tree<T>> trees, List<T> result)
    {

        if (trees.Count == 0)
            return;

        Tree<T> item = trees.Dequeue();
        result.Add(item.value);

        foreach (var tree in item.trees)
        {
            trees.Enqueue(tree);
        }

        this.BFS(trees, result);
    }
}
