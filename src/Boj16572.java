import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Boj16572 {
	private static final int INF = 2_001;
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		int n = in.readInt();
		
		boolean[][] pixel = new boolean[INF][INF];
		
		while(n-- > 0) {
			int A = in.readInt();
			int B = in.readInt();
			int C = in.readInt();
			
			pixel = makeTriangle(pixel, A, B ,C);
		}
		
		System.out.println(area(pixel));
	}
	
	private static boolean[][] makeTriangle(boolean[][] arr, int row, int col, int size) {
		int cost = 0;
		
		for(int i = row; i < row + size; i++) {
			for(int j = col; j < col + size - cost; j++) {
				if(arr[i][j]) continue;
				arr[i][j] = true;
			}
			
			cost++;
		}
		
		return arr;
	}
	
	private static int area(boolean[][] arr) {
		int sum = 0;
		
		for(int i = 1; i < INF; i++) {
			for(int j = 1; j < INF; j++) {
				if(arr[i][j]) sum++;
			}
		}
		
		return sum;
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
