package greedy;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * 
 * 	@author minchoba
 *	백준 16572번: Pixel triangle
 *
 *	@see https://www.acmicpc.net/problem/16572/
 *
 */
public class Boj16572 {
	private static final int INF = 2_001;

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		int n = in.readInt();
		
		int[][] pixel = new int[INF][INF];
		
		while(n-- > 0) {
			int A = in.readInt();
			int B = in.readInt();
			int C = in.readInt();
			
			if(pixel[A][B] >= C) continue;
			pixel[A][B] = C;				// 현재 위치의 자신보다 큰 삼각형이 입력으로 들어올 때
		}
		
		System.out.println(makeTriangle(pixel));
	}
	
	private static int makeTriangle(int[][] arr) {
		int count = 0;
		
		for(int i = 1; i < INF; i++) {
			for(int j = 1; j < INF; j++) {
				int cost = getMax(arr[i][j], arr[i - 1][j] - 1, arr[i][j - 1] - 1);
				if(cost == 0) continue;			// 모든 칸이 0이면 삼각형이 없는 경우
				
				arr[i][j] = cost;
				count++;				// 삼각형이 존재하는 칸이라면 + 1
			}
		}

		return count;
	}
	
	private static int getMax(int cost1, int cost2, int cost3) {
		int max = Math.max(cost1, cost2);
		
		return max > cost3 ? max: cost3;
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
