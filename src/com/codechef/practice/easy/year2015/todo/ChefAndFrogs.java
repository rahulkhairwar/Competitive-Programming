package com.codechef.practice.easy.year2015.todo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.InputMismatchException;

class ChefAndFrogs
{
	private static int n, k, p, frog1, frog2, array[], sortedArray[];
	private static InputReader reader;
	private static OutputWriter writer;
	
	public static void main(String[] args)
	{
		ChefAndFrogs frogs = new ChefAndFrogs();
		
		reader = frogs.new InputReader(System.in);
		writer = frogs.new OutputWriter(System.out);
		
		getAttributes();
		
		writer.flush();
		
		reader.close();
		writer.close();
	}
	
	public static void getAttributes()
	{
		n = reader.nextInt();
		k = reader.nextInt();
		p = reader.nextInt();
		
		array = new int[n];
		sortedArray = new int[n];
		
		for (int i = 0; i < n; i++)
			array[i] = reader.nextInt();
		
		sortedArray = Arrays.copyOf(array, n);
		
		Arrays.sort(sortedArray);
		
		int frogOneAt, j;
		boolean canTalk;
		
		System.out.println("index of all the frogs in sortedArray");
		
		for (int i = 0; i < n; i++)
		{
			System.out.println("frog " + (i + 1) + " at : " + binarySearch(array[i]));
		}
		
		for (int i = 0; i < p; i++)
		{
			frog1 = reader.nextInt();
			frog2 = reader.nextInt();
			
			frogOneAt = binarySearch(array[frog1 - 1]);
			j = frogOneAt;
			canTalk = true;
			
			while (sortedArray[j] < array[frog2 - 1])	// check this!!
			{
				if (sortedArray[j + 1] - sortedArray[j] > k)
				{
					canTalk = false;
					
					break;
				}
				
				j++;	
			}
			
			if (canTalk)
				writer.println("Yes");
			else
				writer.println("No");
		}
	}
	
	public static int binarySearch(int searchValue)
	{
		int first, last, middle;
		
		first = 0;
		last = n - 1;
		
		while (first <= last)
		{
			middle = (first + last) / 2;
			
			if (sortedArray[middle] == searchValue)
				return middle;
			
			if (sortedArray[middle] < searchValue)
				first = middle + 1;
			else
				last = middle - 1;
		}
		
		return -1;
	}
	
	public static int absolute(int number)
	{
		if (number >= 0)
			return number;
		else
			return -number;
	}
	
	class InputReader
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
				}
				catch (IOException e)
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
			}
			while (!isSpaceChar(c));

			return res * sgn;
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
			}
			while (!isSpaceChar(c));

			return result * sign;
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
			}
			while (!isSpaceChar(c));

			return res.toString();
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
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	class OutputWriter
	{
		private PrintWriter writer;

		public OutputWriter(OutputStream stream)
		{
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					stream)));
		}

		public OutputWriter(Writer writer)
		{
			this.writer = new PrintWriter(writer);
		}

		public void println(int x)
		{
			writer.println(x);
		}

		public void println(long x)
		{
			writer.println(x);
		}

		public void print(int x)
		{
			writer.print(x);
		}

		public void print(long x)
		{
			writer.println(x);
		}

		public void println(String s)
		{
			writer.println(s);
		}

		public void print(String s)
		{
			writer.print(s);
		}

		public void println()
		{
			writer.println();
		}

		public void printSpace()
		{
			writer.print(" ");
		}

		public void flush()
		{
			writer.flush();
		}

		public void close()
		{
			writer.close();
		}

	}

}