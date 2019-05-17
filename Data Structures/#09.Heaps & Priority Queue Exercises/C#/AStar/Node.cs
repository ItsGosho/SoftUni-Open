using System;
using System.Collections.Generic;

public class Node : IComparable<Node>
{

    private int F;

    public Node(int row, int col)
    {
        this.Row = row;
        this.Col = col;
    }

    public Node(int row, int col, Node prev)
    {
        this.Row = row;
        this.Col = col;
        this.Prev = prev;
    }

    public int Row { get; set; }
    public int Col { get; set; }
    public int Steps { get; set; }
    public Node Prev { get; set; }

    public void setF(Node goal)
    {
        this.F = this.Steps + AStar.GetH(this, goal);
    }

    public int CompareTo(Node other)
    {
        return this.F.CompareTo(other.F);
    }

    public override bool Equals(object obj)
    {
        var other = (Node)obj;
        return this.Col == other.Col && this.Row == other.Row;
    }

    public override int GetHashCode()
    {
        var hash = 17;
        hash = 31 * hash + this.Row.GetHashCode();
        hash = 31 * hash + this.Col.GetHashCode();
        return hash;
    }

    public override string ToString()
    {
        return this.Row + " " + this.Col;
    }

    public List<Node> GetNodes()
    {
        List<Node> result = new List<Node>();
        Node current = this;

        while(current != null)
        {
            result.Add(current);
            current = current.Prev;
        }

        result.Reverse();
        return result;
    }

}
