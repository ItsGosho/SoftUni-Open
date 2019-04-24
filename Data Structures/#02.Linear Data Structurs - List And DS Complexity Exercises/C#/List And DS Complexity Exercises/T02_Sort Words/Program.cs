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

        String input = Console.ReadLine();
        List<String> words = Regex.Split(input, "\\s+").ToList();

        var result = words.OrderBy(x=>x);

        Console.WriteLine(String.Join(" ",result));

    }
}
