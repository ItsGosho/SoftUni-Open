using System;
using System.Collections.Generic;

public class AStar
{

    private const char StartPositionSymbol = 'P';
    private const char WallSymbol = 'W';
    private const char TraversedPositionSymbol = 'T';
    private const char FinishSymbol = '*';

    private char[,] map;
    private PriorityQueue<Node> priorityQueue;

    public AStar(char[,] map)
    {
        this.map = map;
        this.priorityQueue = new PriorityQueue<Node>();
    }

    public static int GetH(Node current, Node goal)
    {
        int deltaX = Math.Abs(current.Col - goal.Col);
        int deltaY = Math.Abs(current.Row - goal.Row);

        return deltaX + deltaY;
    }

    public IEnumerable<Node> GetPath(Node start, Node goal)
    {
        this.priorityQueue.Enqueue(start);
        Node shortestPath = this.findPath(goal);

        if (shortestPath == null)
        {
            List<Node> result = new List<Node>();
            result.Reverse();
            result.Add(start);
            return result;
        };

        return shortestPath.GetNodes();
    }

    private void ClearMap()
    {
        for (int row = 0; row < map.GetLength(0); row++)
        {
            for (int col = 0; col < map.GetLength(1); col++)
            {
                if (map[row, col] == TraversedPositionSymbol)
                {
                    map[row, col] = WallSymbol;
                }
            }
        }
    }

    private Node findPath(Node goal)
    {

        while (this.priorityQueue.Count != 0)
        {
            Node currentNode = this.priorityQueue.Peek();

            if (this.IsGoal(currentNode, goal))
            {
                return currentNode;
            }

            Node nextUp = this.GoUp(currentNode, goal);
            Node nextDown = this.GoDown(currentNode, goal);
            Node nextLeft = this.GoLeft(currentNode, goal);
            Node nextRight = this.GoRight(currentNode, goal);

            this.ProceedNode(nextUp, currentNode, goal);
            this.ProceedNode(nextDown, currentNode, goal);
            this.ProceedNode(nextLeft, currentNode, goal);
            this.ProceedNode(nextRight, currentNode, goal);

            this.priorityQueue.Dequeue();
        }

        return null;
    }

    private void ProceedNode(Node node, Node father, Node goal)
    {
        if (node == null)
            return;

        node.Steps = father.Steps + 1;
        node.setF(goal);
        this.priorityQueue.Enqueue(node);

        if (!this.map[node.Row, node.Col].Equals(FinishSymbol))
            this.map[node.Row, node.Col] = TraversedPositionSymbol;
    }

    private Node GoUp(Node from, Node goal)
    {
        if (this.IsPositionValid(from.Row - 1, from.Col))
        {
            Node nextNode = new Node(from.Row - 1, from.Col, from);
            return nextNode;
        }

        return null;
    }

    private Node GoDown(Node from, Node goal)
    {
        if (this.IsPositionValid(from.Row + 1, from.Col))
        {
            Node nextNode = new Node(from.Row + 1, from.Col, from);
            return nextNode;
        }
        return null;
    }

    private Node GoLeft(Node from, Node goal)
    {
        if (this.IsPositionValid(from.Row, from.Col - 1))
        {
            Node nextNode = new Node(from.Row, from.Col - 1, from);
            return nextNode;
        }
        return null;
    }

    private Node GoRight(Node from, Node goal)
    {
        if (this.IsPositionValid(from.Row, from.Col + 1))
        {
            Node nextNode = new Node(from.Row, from.Col + 1, from);
            return nextNode;
        }
        return null;
    }

    private bool IsGoal(Node node, Node goal)
    {
        return node.Row == goal.Row && node.Col == goal.Col;
    }

    private bool IsPositionValid(int row, int col)
    {
        if (row < 0 || col < 0)
        {
            return false;
        }

        if (row > map.GetLength(0) - 1 || col > map.GetLength(1) - 1)
        {
            return false;
        }

        char positionSymbol = this.map[row, col];

        if (positionSymbol.Equals(StartPositionSymbol) || positionSymbol.Equals(TraversedPositionSymbol) || positionSymbol.Equals(WallSymbol))
        {
            return false;
        }


        return true;

    }

}

