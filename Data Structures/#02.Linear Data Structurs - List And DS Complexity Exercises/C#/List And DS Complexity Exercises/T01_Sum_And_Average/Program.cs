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
        List<int> numbers = Regex.Split(input,"\\s+").Select(x=>int.Parse(x.Trim())).ToList();

        int sum = numbers.Sum();
        double average = numbers.Average();

        Console.WriteLine(String.Format("Sum={0}; Average={1:f2}",sum,average));

    }
}  
