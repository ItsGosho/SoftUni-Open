using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Program
{
    static void Main(string[] args)
    {
        ArrayStack<int> arrayStack = new ArrayStack<int>();


        for (int i = 1; i < 501; i++)
        {
            arrayStack.Push(i);
        }

     
        Console.WriteLine(arrayStack.Count);
        int[] arr = arrayStack.ToArray();
        Console.WriteLine(String.Join(" ",arrayStack.ToArray()));
    }
}
