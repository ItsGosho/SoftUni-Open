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

        List<int> copiedNumbers = new List<int>(numbers.Count);
        List<int> proceededNumbers = new List<int>();

        CopyListTo(numbers, copiedNumbers);

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
            proceededNumbers.Add(number);

            //remove

            if (count %2 != 0)
            {
                copiedNumbers.RemoveAll(x => x == number);
            }

        }
        Console.WriteLine(String.Join(" ",copiedNumbers));
    }

    public static void CopyListTo(List<int> source, List<int> destination)
    {
        for (int i = 0; i < source.Count; i++)
        {
            destination.Add(source[i]);
        }
    }
}
