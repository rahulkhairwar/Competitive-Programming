package com.coursera.algorithms_divide_and_conquer;

import java.io.*;
import java.util.*;

public final class CountingInversions
{
    public static void main(String[] args)
    {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

		try
		{
			in = new InputReader(new FileInputStream(new File("/Users/rahulkhairwar/Documents/IntelliJ IDEA "
					+ "Workspace/Competitive Programming/src/com/coursera/algorithms_divide_and_conquer/inversions.txt")));
			Solver solver = new Solver(in, out);
			solver.solve();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}


        in.close();
        out.flush();
        out.close();
    }

    static class Solver
    {
        int n;
        int[] bit;
        InputReader in;
        PrintWriter out;

		void solve()
		{
			n = (int) 1e5;
			bit = new int[n + 1];

			long cnt = 0;

			for (int i = 0; i < n; i++)
			{
				int x = in.nextInt();

				cnt += query(n) - query(x);
				add(x);
			}

			out.println(cnt);
		}

		void add(int ind)
		{
			while (ind <= n)
			{
				bit[ind]++;
				ind += ind & -ind;
			}
		}

		int query(int ind)
		{
			int ans = 0;

			while (ind > 0)
			{
				ans += bit[ind];
				ind -= ind & -ind;
			}

			return ans;
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

        public boolean isSpaceChar(int c)
        {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
