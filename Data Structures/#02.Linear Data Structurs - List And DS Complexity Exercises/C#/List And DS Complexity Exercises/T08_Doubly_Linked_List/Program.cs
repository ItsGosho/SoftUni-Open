using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Program
{
    static void Main(string[] args)
    {
        var list = new DoublyLinkedList<int>();

        list.AddLast(1);
     
        //list.AddLast(80);
        //list.AddLast(40);

        Console.WriteLine(list.RemoveLast());
        Console.WriteLine(list.RemoveFirst());
        Console.WriteLine(list.RemoveLast());
        Console.WriteLine("--");


        Console.WriteLine(String.Join(" ", list.ToArray()));
    }
}
