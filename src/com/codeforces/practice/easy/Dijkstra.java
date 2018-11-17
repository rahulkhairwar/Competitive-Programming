package com.codeforces.practice.easy;

import java.io.*;
import java.util.*;

public final class Dijkstra
{
    public static void main(String[] args)
    {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
		Solver solver = new Solver(in, out);

		solver.solve();
        in.close();
        out.flush();
        out.close();
    }

    static class Solver
    {
		static final long INFINITY = (long) 1e16;
        int v, e;
		Node[] nodes;
		List<Edge>[] adj;
        InputReader in;
        PrintWriter out;

		void solve()
		{
			v = in.nextInt();
			e = in.nextInt();

			createGraph();

			nodes = new Node[v];

			for (int i = 0; i < v; i++)
				nodes[i] = new Node(i, INFINITY, -1);

			nodes[0].dist = 0;

			PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>()
			{
				@Override public int compare(Integer o1, Integer o2)
				{
					return Long.compare(nodes[o1].dist, nodes[o2].dist);
				}
			});

			queue.add(0);

			while (queue.size() > 0)
			{
				int curr = queue.poll();

				if (adj[curr] == null)
					continue;

				Iterator<Edge> iterator = adj[curr].iterator();

				while (iterator.hasNext())
				{
					Edge edge = iterator.next();

					if (nodes[curr].dist + edge.weight < nodes[edge.to].dist)
					{
						nodes[edge.to].dist = nodes[curr].dist + edge.weight;
						nodes[edge.to].parent = curr;
						queue.add(edge.to);
					}
				}
			}

			if (nodes[v - 1].dist == INFINITY)
				out.println(-1);
			else
			{
				int[] ans = new int[v];
				int counter = 0;
				int curr = v - 1;

				while (curr != -1)
				{
					ans[counter++] = curr;
					curr = nodes[curr].parent;
				}

				for (int i = counter - 1; i >= 0; i--)
					out.print(ans[i] + 1 + " ");
			}
		}

		void createGraph()
		{
			adj = new ArrayList[v];

			for (int i = 0; i < e; i++)
			{
				int from, to, weight;

				from = in.nextInt() - 1;
				to = in.nextInt() - 1;
				weight = in.nextInt();

				if (adj[from] == null)
					adj[from] = new ArrayList<>();

				adj[from].add(new Edge(to, weight));

				if (adj[to] == null)
					adj[to] = new ArrayList<>();

				adj[to].add(new Edge(from, weight));
			}
		}

		static class Node
		{
			int node, parent;
			long dist;

			public Node(int node, long dist, int parent)
			{
				this.node = node;
				this.dist = dist;
				this.parent = parent;
			}

		}

		class Edge
		{
			int to;
			long weight;

			public Edge(int to, long weight)
			{
				this.to = to;
				this.weight = weight;
			}

		}

        public Solver(InputReader in, PrintWriter out)
        {
        	this.in = in;
        	this.out = out;
        }

	}

    static class InputReader
    {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream)
        {
            this.stream = stream;
        }

        public int read()
        {
            if (numChars == -1)
                throw new InputMismatchException();

            if (curChar >= numChars)
            {
                curChar = 0;
                try
                {
                    numChars = stream.read(buf);
                } catch (IOException e)
                {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }

            return buf[curChar++];
        }

        public int nextInt()
        {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            int sgn = 1;

            if (c == '-')
            {
                sgn = -1;
                c = read();
            }

            int res = 0;

            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();

                res *= 10;
                res += c & 15;

                c = read();
            } while (!isSpaceChar(c));

            return res * sgn;
        }

        public int[] nextIntArray(int arraySize)
        {
            int array[] = new int[arraySize];

            for (int i = 0; i < arraySize; i++)
                array[i] = nextInt();

            return array;
        }

        public long nextLong()
        {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            int sign = 1;

            if (c == '-')
            {
                sign = -1;

                c = read();
            }

            long result = 0;

            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();

                result *= 10;
                result += c & 15;

                c = read();
            } while (!isSpaceChar(c));

            return result * sign;
        }

        public long[] nextLongArray(int arraySize)
        {
            long array[] = new long[arraySize];

            for (int i = 0; i < arraySize; i++)
                array[i] = nextLong();

            return array;
        }

        public float nextFloat()
        {
            float result, div;
            byte c;

            result = 0;
            div = 1;
            c = (byte) read();

            while (c <= ' ')
                c = (byte) read();

            boolean isNegative = (c == '-');

            if (isNegative)
                c = (byte) read();

            do
            {
                result = result * 10 + c - '0';
            } while ((c = (byte) read()) >= '0' && c <= '9');

            if (c == '.')
                while ((c = (byte) read()) >= '0' && c <= '9')
                    result += (c - '0') / (div *= 10);

            if (isNegative)
                return -result;

            return result;
        }

        public double nextDouble()
        {
            double ret = 0, div = 1;
            byte c = (byte) read();

            while (c <= ' ')
                c = (byte) read();

            boolean neg = (c == '-');

            if (neg)
                c = (byte) read();

            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = (byte) read()) >= '0' && c <= '9');

            if (c == '.')
                while ((c = (byte) read()) >= '0' && c <= '9')
                    ret += (c - '0') / (div *= 10);

            if (neg)
                return -ret;

            return ret;
        }

        public String next()
        {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            StringBuilder res = new StringBuilder();

            do
            {
                res.appendCodePoint(c);

                c = read();
            } while (!isSpaceChar(c));

            return res.toString();
        }

        public boolean isSpaceChar(int c)
        {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String nextLine()
        {
            int c = read();

            StringBuilder result = new StringBuilder();

            do
            {
                result.appendCodePoint(c);

                c = read();
            } while (!isNewLine(c));

            return result.toString();
        }

        public boolean isNewLine(int c)
        {
            return c == '\n';
        }

        public void close()
        {
            try
            {
                stream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

}
