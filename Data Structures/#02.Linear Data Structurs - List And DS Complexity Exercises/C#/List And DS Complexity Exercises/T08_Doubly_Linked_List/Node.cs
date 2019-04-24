using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Node<T>
{
    private T value;
    private Node<T> nextNode;
    private Node<T> previousNode;

    public Node(T value, Node<T> nextNode, Node<T> previousNode)
    {
        this.value = value;
        this.nextNode = nextNode;
        this.previousNode = previousNode;

    }

    public T Value { get => value; set => this.value = value; }
    internal Node<T> NextNode { get => nextNode; set => nextNode = value; }
    internal Node<T> PreviousNode { get => previousNode; set => previousNode = value; }
}
