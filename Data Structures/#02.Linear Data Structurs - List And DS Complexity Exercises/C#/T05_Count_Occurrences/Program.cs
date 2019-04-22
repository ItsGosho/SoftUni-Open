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

        List<int> numbers = Regex.Split(input, "\\s+").Select(x => int.Parse(x.Trim())).ToList();
        List<int> proceededNumbers = new List<int>();
        List<String> result = new List<string>();

        for (int i = 0; i < numbers.Count; i++)
        {
            int number = numbers[i];
            int count = 0;
            for (int k = i; k < numbers.Count; k++)
            {
                int current = numbers[k];
                if (current.Equals(number) && !proceededNumbers.Contains(current))
                {
                    count++;
                }
            }

            if (!proceededNumbers.Contains(number))
            {
                result.Add(String.Format("{0} -> {1} times", number, count));
            }

            proceededNumbers.Add(number);

        }
        Console.WriteLine(String.Join("\n", result));

    }
}
