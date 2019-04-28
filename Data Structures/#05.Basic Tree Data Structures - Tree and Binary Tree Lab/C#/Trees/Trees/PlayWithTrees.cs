using System;

public class PlayWithTrees
{
    static void Main()
    {
        //var tree =
        //    new Tree<int>(7,
        //        new Tree<int>(19,
        //            new Tree<int>(1),
        //            new Tree<int>(12),
        //            new Tree<int>(31)),
        //        new Tree<int>(21),
        //        new Tree<int>(14,
        //            new Tree<int>(23),
        //            new Tree<int>(6)));

        //tree.Print();

        //Console.WriteLine();

        var binaryTree =
            new BinaryTree<string>("*",
                new BinaryTree<string>("+",
                   new BinaryTree<string>("3"),
                   new BinaryTree<string>("2")),
                new BinaryTree<string>("-",
                    new BinaryTree<string>("9"),
                   new BinaryTree<string>("6")));


        binaryTree.EachPostOrder(x =>
        {
            Console.WriteLine(x);
        });

    }
}
