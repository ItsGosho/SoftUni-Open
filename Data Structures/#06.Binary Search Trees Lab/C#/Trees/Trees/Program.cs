using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Program
{
    public static void Main(string[] args)
    {

        BinarySearchTree<int> binarySearchTree = new BinarySearchTree<int>();
        binarySearchTree.Insert(12);
        binarySearchTree.Insert(5);
        binarySearchTree.Insert(1);
        binarySearchTree.Insert(8);
        binarySearchTree.Insert(21);
        binarySearchTree.Insert(18);
        binarySearchTree.Insert(23);

        binarySearchTree.Range(1,18);

        //binarySearchTree.EachInOrder(x =>
        //{
        //    Console.WriteLine(x);
        //});

        Console.WriteLine();
    }
}
