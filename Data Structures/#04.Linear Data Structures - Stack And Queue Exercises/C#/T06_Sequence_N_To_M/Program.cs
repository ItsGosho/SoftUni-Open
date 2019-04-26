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

        string[] input = Regex.Split(Console.ReadLine(), "\\s+");
        int n = int.Parse(input[0]);
        int m = int.Parse(input[1]);

        if (n > m)
        {
            return;
        }

        Queue<Sequence> sequences = new Queue<Sequence>();
        Sequence initialSequence = new Sequence(n);
        sequences.Enqueue(initialSequence);

        while (true)
        {
            Sequence current = sequences.Dequeue();

            Sequence formulaOne = new Sequence(current.CurrentValue + 1, current);
            Sequence formulaTwo = new Sequence(current.CurrentValue + 2, current);
            Sequence formulaThree = new Sequence(current.CurrentValue * 2, current);

            //all of the if`s are here to fix performace issues
            if (formulaOne.CurrentValue <= m)
                sequences.Enqueue(formulaOne);

            if (formulaTwo.CurrentValue <= m)
                sequences.Enqueue(formulaTwo);

            if (formulaThree.CurrentValue <= m)
                sequences.Enqueue(formulaThree);

            if (current.CurrentValue == m)
            {

                int[] result = current.toArray();
                Console.WriteLine(String.Join(" -> ", result));

                break;
            }

        }

    }

}

class Sequence
{
    private int currentValue;
    private Sequence lastSequence;

    public Sequence(int currentValue, Sequence lastSequence = null)
    {
        this.currentValue = currentValue;
        this.lastSequence = lastSequence;
    }

    public int CurrentValue { get => currentValue; set => currentValue = value; }
    internal Sequence LastSequence { get => lastSequence; set => lastSequence = value; }

    public int[] toArray()
    {
        Sequence last = this.lastSequence;
        List<int> res = new List<int>();
        res.Add(this.currentValue);

        while (last != null)
        {
            res.Add(last.CurrentValue);
            last = last.LastSequence;
        }

        res.Reverse();

        return res.ToArray();
    }
}