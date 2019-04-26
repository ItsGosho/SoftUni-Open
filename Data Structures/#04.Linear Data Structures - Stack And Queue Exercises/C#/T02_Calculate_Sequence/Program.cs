using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Program
{
    static void Main(string[] args)
    {

        int number = int.Parse(Console.ReadLine());

        Queue<int> nums = new Queue<int>();
        nums.Enqueue(number);

        List<int> result = new List<int>();
        while (result.Count != 50)
        {
            int current = nums.Dequeue();

            int formulaFirstResult = current + 1;
            int formulaTwotResult = (2 * current) + 1;
            int formulaThreeResult = current + 2;

            nums.Enqueue(formulaFirstResult);
            nums.Enqueue(formulaTwotResult);
            nums.Enqueue(formulaThreeResult);
            result.Add(current);
        }
        Console.WriteLine(String.Join(", ",result.ToArray()));
    }
}
