using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Program
{
    static void Main(string[] args)
    {
        LinkedQueue<int> linkedQueue = new LinkedQueue<int>();
        linkedQueue.Enqueue(1);
        linkedQueue.Enqueue(2);
        linkedQueue.Enqueue(3);
        linkedQueue.Dequeue();



        Console.WriteLine(String.Join(" ",linkedQueue.ToArray()));
    }
}
