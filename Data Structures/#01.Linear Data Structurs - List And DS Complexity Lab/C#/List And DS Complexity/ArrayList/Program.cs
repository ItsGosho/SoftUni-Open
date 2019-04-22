using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class Program
{
    public static void Main(string[] args)
    {

        ArrayList<int> test = new ArrayList<int>();

        test.Add(1);
        test.Add(2);
        test.Add(3);
        test.Add(4);
        test.Add(5);

        Console.WriteLine(test[3]);

        Console.WriteLine();
    }
}
