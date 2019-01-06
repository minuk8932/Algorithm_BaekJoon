package depth_first_search;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 16437번: 양 구출 작전
 *
 *	@see https://www.acmicpc.net/problem/16437/
 *
 */
public class Boj16437 {	
	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		int N = in.readInt();

		ArrayList<Integer>[] tree = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}
		
		long[] cost = new long[N + 1];

		for (int i = 2; i < N + 1; i++) {
			boolean type = in.readString().charAt(0) == 'S' ? true : false;
			long count = in.readLong();
			int parent = in.readInt();

			tree[parent].add(i);
			cost[i] = type ? count : -count;		// 양인 경우 양수 늑대인 경우 음수
		}

		System.out.println(dfs(tree, cost, 1));			// 결과 출력
	}

	private static long dfs(ArrayList<Integer>[] arr, long[] cost, int current) {
		long total = cost[current];
		
		for(int next: arr[current]) {
			total += dfs(arr, cost, next);
		}
		
		return total > 0 ? total : 0;		// 늑대에 의해 부분합이 음수가 된 경우
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

		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
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
