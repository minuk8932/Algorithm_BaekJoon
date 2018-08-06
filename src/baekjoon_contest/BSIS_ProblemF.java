package baekjoon_contest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class BSIS_ProblemF {
	private static final String NEW_LINE = "\n";
	private static final int INF = 1_000_001;
	
	private static StringBuilder sb = new StringBuilder();
	private static long[] damageSet = new long[INF];
	
	private static InputReader in = new InputReader(System.in);
	private static OutputWriter out = new OutputWriter(System.out);
	
	public static void main(String[] args) throws Exception{		
		int N = in.readInt();
		int Q1 = in.readInt();
		int Q2 = in.readInt();
		
		long[] tmp = new long[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			long d = in.readLong();
			damageSet[i] = d;
			tmp[i] = damageSet[i];
			damageSet[i] += damageSet[i - 1];
		}
		
		int loop = Q1 + Q2;
		
		while(loop-- > 0) {
			int q = in.readInt();
			int from = in.readInt();
			int to = in.readInt();
			int add = 0;
			
			long res = 0;
			
			if(q == 1) Query1(N, from, to, res);			
			else Query2(N, from, to, add);
		}
		
		out.print(sb.toString());
	}
	
	private static void Query1(int N, int from, int to, long result) {
		if(to == N) result = damageSet[N] - damageSet[from - 1];				
		else result = damageSet[to] - damageSet[from - 1];
		
		sb.append(result).append(NEW_LINE);
	}
	
	private static void Query2(int N, int from, int to, int add) {
		add = in.readInt();
		int rep = 0;
		
		for(int i = from; i < N + 1; i++) {
			if(i < to + 1) rep++;
			
			damageSet[i] += (add * rep);
		}
	}
	
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public long readLong() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}

	private static class OutputWriter {
		private final PrintWriter writer;

		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
		}

		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0) {
					writer.print(' ');
				}
				writer.print(objects[i]);
			}
			writer.flush();
		}
	}
}
