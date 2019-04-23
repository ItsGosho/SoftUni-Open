using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Program
{
    static void Main(string[] args)
    {
        ReversedList<int> reversedList = new ReversedList<int>();

        reversedList.Add(1);
        reversedList.Add(2);
        reversedList.Add(3);
        reversedList.Add(4);

        Console.WriteLine(reversedList[1]);
        Console.WriteLine("-----");

        foreach (int item in reversedList)
        {
            Console.WriteLine(item);
        }

    }
}
