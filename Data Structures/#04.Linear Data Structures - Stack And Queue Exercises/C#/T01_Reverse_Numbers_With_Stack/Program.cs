using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

class Program
{
    static void Main(string[] args)
    {

        string[] input = Regex.Split(Console.ReadLine(), "\\s+").ToArray();
        Stack<int> numbers = new Stack<int>();

        for (int i = 0; i < input.Length; i++)
        {
            numbers.Push(int.Parse(input[i]));
        }

        Console.WriteLine(String.Join(" ",numbers.ToArray()));

    }
}
