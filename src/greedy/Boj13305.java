package greedy;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 13305번: 주유소
 *
 *	@see https://www.acmicpc.net/problem/13305/
 *
 */
public class Boj13305 {
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		
		int[] d = new int[N];
		for(int i = 1; i < N; i++) {
			d[i] = in.readInt();
		}
		
		long[] c = new long[N];
		for(int i = 0; i < N; i++) {
			c[i] = in.readLong();
		}
		
		System.out.println(getCost(N, d, c));		// 총 비용
	}
	
	private static long getCost(int n, int[] dist, long[] cost) {
		long total = 0;
		long current = cost[0];
		
		for(int i = 1; i < n; i++) {
			total += (current * dist[i]);				// 가격이 낮은 주유소에서 몽땅 사벌임
			if(cost[i] < current) current = cost[i];
		}
		
		return total;
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
