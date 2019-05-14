using System;

public class HeapExample
{
    static void Main()
    {
        //Console.WriteLine("Created an empty heap.");
        //var heap = new BinaryHeap<int>();
        //heap.Insert(5);
        //heap.Insert(8);
        //heap.Insert(1);
        //heap.Insert(3);
        //heap.Insert(12);
        //heap.Insert(-4);

        //Console.WriteLine(heap.Peek());

        //Console.WriteLine("Heap elements (max to min):");
        //while (heap.Count > 0)
        //{
        //    var max = heap.Pull();
        //    Console.WriteLine(max);
        //}

        int[] test = new int[] { 3, 10, 13, -4, 40, 15, 7, 8 };
        Heap<int>.Sort(test);
        Console.WriteLine();
    }
}
