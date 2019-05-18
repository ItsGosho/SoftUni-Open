namespace Hierarchy.Core
{
    using System;
    using System.Collections.Generic;
    using System.Collections;

    public class Hierarchy<T> : IHierarchy<T>
    {

        private Node<T> root;
        public Hierarchy(T root)
        {
            this.root = new Node<T>(root, null);
        }

        public int Count
        {
            get
            {
                return this.root.Count;
            }
        }

        public void Add(T element, T child)
        {
            Node<T> resultNode = null;
            bool isElementAlreadyPresent = false;
            Queue<Node<T>> nodes = new Queue<Node<T>>();
            nodes.Enqueue(this.root);

            while (nodes.Count != 0)
            {
                Node<T> current = nodes.Peek();

                if (current.Value.Equals(element))
                {
                    resultNode = current;
                }

                if (current.Value.Equals(child))
                {
                    isElementAlreadyPresent = true;
                    break;
                }

                current.Childrens.ForEach(x =>
                {
                    nodes.Enqueue(x);
                });

                nodes.Dequeue();
            }

            if (resultNode == null)
            {
                throw new ArgumentException("Parent doesn't exist!");
            }

            if (isElementAlreadyPresent)
            {
                throw new ArgumentException("Element already exists!");
            }

            resultNode.AddChildren(new Node<T>(child, resultNode));

        }

        public void Remove(T element)
        {
            if (this.root == null || this.root.Value.Equals(element))
            {
                throw new InvalidOperationException("You cannot delete the root node!");
            }

            Node<T> resultNode = null;
            Queue<Node<T>> nodes = new Queue<Node<T>>();
            nodes.Enqueue(this.root);

            while (nodes.Count != 0)
            {
                Node<T> current = nodes.Peek();

                if (current.Value.Equals(element))
                {
                    resultNode = current;
                    break;
                }

                current.Childrens.ForEach(x =>
                {
                    nodes.Enqueue(x);
                });

                nodes.Dequeue();
            }

            if (resultNode == null)
            {
                throw new ArgumentException("Element doesnt exists!");
            }

            Node<T> father = resultNode.Father;
            resultNode.UpdateFatherCountWithMinusOne();
            int resultNodeIndex = father.Childrens.IndexOf(resultNode);

            for (int i = 0; i < resultNode.Childrens.Count; i++)
            {
                father.Childrens.Insert(resultNodeIndex, resultNode.Childrens[i]);
                resultNodeIndex++;
            }

            father.Childrens.RemoveAt(resultNodeIndex);

        }

        public IEnumerable<T> GetChildren(T item)
        {
            Node<T> resultNode = null;
            Queue<Node<T>> nodes = new Queue<Node<T>>();
            nodes.Enqueue(this.root);

            while (nodes.Count != 0)
            {
                Node<T> current = nodes.Peek();

                if (current.Value.Equals(item))
                {
                    resultNode = current;
                    break;
                }

                current.Childrens.ForEach(x =>
                {
                    nodes.Enqueue(x);
                });

                nodes.Dequeue();
            }

            if (resultNode == null)
            {
                throw new ArgumentException("Element doesnt exists!");
            }

            List<T> childrens = new List<T>();

            resultNode.Childrens.ForEach(x =>
            {
                childrens.Add(x.Value);
            });

            return childrens;
        }

        public T GetParent(T item)
        {
            Node<T> resultNode = null;
            Queue<Node<T>> nodes = new Queue<Node<T>>();
            nodes.Enqueue(this.root);

            while (nodes.Count != 0)
            {
                Node<T> current = nodes.Peek();

                if (current.Value.Equals(item))
                {
                    resultNode = current;
                    break;
                }

                current.Childrens.ForEach(x =>
                {
                    nodes.Enqueue(x);
                });

                nodes.Dequeue();
            }

            if (resultNode == null)
            {
                throw new ArgumentException("Element doesnt exists!");
            }

            if (resultNode.Father != null)
            {
                return resultNode.Father.Value;
            }

            return default(T);
        }

        public bool Contains(T value)
        {
            bool isPresent = false;
            Queue<Node<T>> nodes = new Queue<Node<T>>();
            nodes.Enqueue(this.root);

            while (nodes.Count != 0)
            {
                Node<T> current = nodes.Peek();

                if (current.Value.Equals(value))
                {
                    isPresent = true;
                    break;
                }

                current.Childrens.ForEach(x =>
                {
                    nodes.Enqueue(x);
                });

                nodes.Dequeue();
            }

            return isPresent;
        }

        public IEnumerable<T> GetCommonElements(Hierarchy<T> other)
        {
            List<T> commonElements = new List<T>();

            foreach(var ele in other)
            {
                bool isPresent = this.Contains(ele);
                if(isPresent) commonElements.Add(ele);
            }

            return commonElements;
        }

        public IEnumerator<T> GetEnumerator()
        {
            List<T> result = new List<T>();
            Queue<Node<T>> nodes = new Queue<Node<T>>();
            nodes.Enqueue(this.root);

            while (nodes.Count != 0)
            {
                Node<T> current = nodes.Peek();

                result.Add(current.Value);
                current.Childrens.ForEach(x =>
                {
                    nodes.Enqueue(x);
                });

                nodes.Dequeue();
            }

            return result.GetEnumerator();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this.GetEnumerator();
        }
    }
}