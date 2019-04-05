package baekjoon_contest;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class BSIS_ProblemF {
	private static final String NEW_LINE = "\n";
	private static long[] tree;
	
	public static void main(String[] args) throws Exception{		
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = in.readInt();
		int Q1 = in.readInt();
		int Q2 = in.readInt();
		
		int S = 1;
		while(S < N) S <<= 1;
		
		tree = new long[S * 2];
		for(int i = S; i < S + N; i++) {
			tree[i] = in.readInt();
		}
		
		init();
		
		int loop = Q1 + Q2;
		while(loop-- > 0) {
			int cmd = in.readInt();
			int from = 0;
			int to = 0;
			
			if(cmd == 1) {
				from = in.readInt();
				to = in.readInt();
				
				sb.append(getSum(from + S - 1, to + S - 1)).append(NEW_LINE);
			}
			else {
				from = in.readInt();
				to = in.readInt();
				int cost = in.readInt();
				
				update(from + S - 1, to + S - 1, cost);
			}
		}
		
		System.out.println(sb);
	}
	
	private static void init() {
		for(int i = tree.length - 1; i > 1; i -= 2) {
			tree[i / 2] = tree[i] + tree[i - 1];
		}
	}
	
	private static long getSum(int left, int right) {
		long total = 0;

		while(left <= right) {
			if(right % 2 == 0) total += tree[right--];
			if(left % 2 == 1) total += tree[left++];
			
			right /= 2;
			left /= 2;
		}
		
		return total;
	}
	
	private static void update(int left, int right, int hit) {
		for(int i = left; i < right + 1; i++) {
			tree[i] += hit;
		}
		
		for(int i = tree.length - 1; i > 1; i -= 2) {
			tree[i / 2] = tree[i] + tree[i - 1];
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
}