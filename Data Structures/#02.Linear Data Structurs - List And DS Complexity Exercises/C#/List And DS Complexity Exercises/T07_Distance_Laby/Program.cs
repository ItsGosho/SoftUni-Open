using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;

class Program
{

    private const string StartPositionSymbol = "*";
    private const string WallSymbol = "x";
    private const string EmptyPositionSymbol = "0";

    static void Main(string[] args)
    {

        int rowsAndColumns = int.Parse(Console.ReadLine());
        string[][] labyrinth = new string[rowsAndColumns][];

        FillTheData(rowsAndColumns,ref labyrinth);

        int startPositionRow = 0;
        int startPositionColumn = 0;
        FindStartPosition(labyrinth, ref startPositionRow, ref startPositionColumn);

        Queue<LabyrinthNode> labyrinthNodes = new Queue<LabyrinthNode>();
        labyrinthNodes.Enqueue(new LabyrinthNode(labyrinth[startPositionRow][startPositionColumn], startPositionRow, startPositionColumn,"1"));

        while (labyrinthNodes.Count != 0)
        {
            LabyrinthNode labyrinthNode = labyrinthNodes.Peek();

            //Position up
            if (IsPositionValid(labyrinth, labyrinthNode.Row - 1, labyrinthNode.Column))
            {
                int rowUp = labyrinthNode.Row - 1;
                int columnUp = labyrinthNode.Column;
                string value = labyrinth[rowUp][columnUp];
                string nextValue = labyrinthNode.Value != StartPositionSymbol ? (int.Parse(labyrinthNode.NextValue) + 1).ToString() : "1";

                labyrinthNodes.Enqueue(new LabyrinthNode(value,rowUp,columnUp, nextValue));
            }

            //Position down
            if (IsPositionValid(labyrinth, labyrinthNode.Row + 1, labyrinthNode.Column))
            {
                int rowDown = labyrinthNode.Row + 1;
                int columnDown = labyrinthNode.Column;
                string value = labyrinth[rowDown][columnDown];
                string nextValue = labyrinthNode.Value != StartPositionSymbol ? (int.Parse(labyrinthNode.NextValue) + 1).ToString() : "1";

                labyrinthNodes.Enqueue(new LabyrinthNode(value, rowDown, columnDown, nextValue));
            }

            //Position right
            if (IsPositionValid(labyrinth, labyrinthNode.Row, labyrinthNode.Column + 1))
            {
                int rowRight = labyrinthNode.Row;
                int columnRight = labyrinthNode.Column + 1;
                string value = labyrinth[rowRight][columnRight];
                string nextValue = labyrinthNode.Value != StartPositionSymbol ? (int.Parse(labyrinthNode.NextValue) + 1).ToString() : "1";

                labyrinthNodes.Enqueue(new LabyrinthNode(value, rowRight, columnRight, nextValue));
            }

            //Position left
            if (IsPositionValid(labyrinth, labyrinthNode.Row, labyrinthNode.Column - 1))
            {
                int rowLeft = labyrinthNode.Row;
                int columnLeft = labyrinthNode.Column - 1;
                string value = labyrinth[rowLeft][columnLeft];
                string nextValue = labyrinthNode.Value != StartPositionSymbol ? (int.Parse(labyrinthNode.NextValue) + 1).ToString() : "1";

                labyrinthNodes.Enqueue(new LabyrinthNode(value, rowLeft, columnLeft, nextValue));
            }

            if (IsElementValid(labyrinthNode.Value))
            {
                labyrinth[labyrinthNode.Row][labyrinthNode.Column] = (int.Parse(labyrinthNode.NextValue)).ToString();
            }

            labyrinthNodes.Dequeue();
        }

        FillUnreachable(ref labyrinth);

        Print(labyrinth);
    }

    private static void FillUnreachable(ref string[][] labyrinth)
    {
        for (int i = 0; i < labyrinth.Length; i++)
        {
            for (int k = 0; k < labyrinth[i].Length; k++)
            {
                string element = labyrinth[i][k];

                if (element == EmptyPositionSymbol)
                {
                    labyrinth[i][k] = "u";
                }
            }
        }
    }

    private static void FillTheData(int rowsAndColumns,ref string[][] labyrinth)
    {
        int counter = 0;

        while (counter < rowsAndColumns)
        {

            char[] readedInput = Console.ReadLine().ToCharArray();

            for (int i = 0; i < rowsAndColumns; i++)
            {
                if (labyrinth[counter] == null)
                {
                    labyrinth[counter] = new string[rowsAndColumns];
                }

                string str = readedInput[i].ToString();
                labyrinth[counter][i] = str;
            }

            counter++;
        }
    }

    private static void Print(string[][] labyrinth)
    {
        for (int i = 0; i < labyrinth.Length; i++)
        {
            for (int k = 0; k < labyrinth[i].Length; k++)
            {
                string value = labyrinth[i][k];

                Console.Write(value);
            }
            Console.WriteLine();
        }
    }
  
    private static Boolean IsElementValid(string element)
    {
        if (!element.Equals(StartPositionSymbol) && !element.Equals(WallSymbol) && int.Parse(element) == int.Parse(EmptyPositionSymbol))
        {
            return true;
        }

        return false;
    }

    private static Boolean IsPositionValid(string[][] labyrinth, int row, int column)
    {

        if (row < 0 || row > labyrinth.Length - 1)
        {
            return false;
        }

        if (column > labyrinth[row].Length - 1 || column < 0)
        {
            return false;
        }

        string value = labyrinth[row][column];
        if (IsElementValid(value) == false)
        {
            return false;
        }

        return true;
    }

    private static void FindStartPosition(string[][] labyrinth, ref int row, ref int column)
    {
        for (int i = 0; i < labyrinth.Length; i++)
        {
            for (int k = 0; k < labyrinth[i].Length; k++)
            {
                string element = labyrinth[i][k];
                if (element.Equals(StartPositionSymbol))
                {
                    row = i;
                    column = k;
                    break;
                }
            }
        }
    }


}

class LabyrinthNode
{
    private string nextValue;
    private string value;
    private int row;
    private int column;

    public LabyrinthNode(string value, int row, int column, string nextValue)
    {
        this.value = value;
        this.row = row;
        this.column = column;
        this.nextValue = nextValue;
    }

    public string Value
    {
        get { return this.value; }
        set { this.value = value; }
    }

    public string NextValue
    {
        get { return this.nextValue; }
        set { this.nextValue = value; }
    }

    public int Row
    {
        get { return this.row; }
        set { this.row = value; }
    }

    public int Column
    {
        get { return this.column; }
        set { this.column = value; }
    }
}


