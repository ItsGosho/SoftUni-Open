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

        List<int> foundedLongestSequence = new List<int>();

        int lastPreviousNumber = int.MinValue;
        int lastLongestCount = int.MinValue;
       
        int currentPreviousNumber = int.MinValue;
        int currentLongestCount = int.MinValue;

        for (int i = 0; i < numbers.Count; i++)
        {
            int currentNumber = numbers[i];
            if (currentNumber.Equals(currentPreviousNumber))
            {
                currentLongestCount++;
            }
            else
            {   

                if (lastLongestCount < currentLongestCount)
                {
                    lastPreviousNumber = currentPreviousNumber;
                    lastLongestCount = currentLongestCount;
                    
                    currentNumber = 0;
                    currentLongestCount = 0;
                }

                currentPreviousNumber = numbers[i];
                currentLongestCount = 0;

            }
        }

        if (lastLongestCount < currentLongestCount)
        {
            lastPreviousNumber = currentPreviousNumber;
            lastLongestCount = currentLongestCount;
        }

        if (lastLongestCount == 0 && lastPreviousNumber == 0)
        {
            lastPreviousNumber = numbers[0];
        }

        string result = "";
        for (int i = 0; i < lastLongestCount+1; i++)
        {
            result += lastPreviousNumber + " ";
        }

        Console.WriteLine(result.Trim());
    }
}
