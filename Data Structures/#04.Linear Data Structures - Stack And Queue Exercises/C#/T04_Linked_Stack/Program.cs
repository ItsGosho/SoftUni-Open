using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Program
{
    static void Main(string[] args)
    {

        LinkedStack<int> linkedStack = new LinkedStack<int>();

        linkedStack.Push(1);
        linkedStack.Push(2);
        linkedStack.Push(3);

        Console.WriteLine(String.Join(" ", linkedStack.ToArray()));
    }
}
