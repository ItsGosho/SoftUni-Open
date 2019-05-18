using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Node<T>
{

    private T value;
    private List<Node<T>> childrens;
    private int count;
    private Node<T> father;

    public Node(T value, Node<T> father)
    {
        this.value = value;
        this.childrens = new List<Node<T>>();
        this.count = 1;
        this.father = father;
        this.UpdateFatherCountWithOne();
    }

    public T Value { get => value; set => this.value = value; }
    public int Count { get => count; set => count = value; }
    public List<Node<T>> Childrens { get => childrens; set => childrens = value; }
    public Node<T> Father { get => father; set => father = value; }

    public void AddChildren(Node<T> node)
    {
        this.childrens.Add(node);
    }

    private void UpdateFatherCountWithOne()
    {
        Node<T> father = this.father;

        while (father != null)
        {
            father.count++;
            father = father.father;
        }
    }

    public void UpdateFatherCountWithMinusOne()
    {
        Node<T> father = this.father;

        while (father != null)
        {
            father.count--;
            father = father.father;
        }
    }

    public override string ToString()
    {
        return "V: " + value + " Count: " + count;
    }

}
