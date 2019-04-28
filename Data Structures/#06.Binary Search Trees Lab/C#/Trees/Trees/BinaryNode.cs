using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class BinaryNode<T> where T : IComparable<T>
{
    private T value;
    private BinaryNode<T> father;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;

    public BinaryNode(T value = default(T), BinaryNode<T> father = null)
    {
        this.value = value;
        this.father = father;
    }

    public T Value { get => value; set => this.value = value; }
    internal BinaryNode<T> Father { get => father; set => father = value; }
    internal BinaryNode<T> LeftChild { get => leftChild; set => leftChild = value; }
    internal BinaryNode<T> RightChild { get => rightChild; set => rightChild = value; }
}
