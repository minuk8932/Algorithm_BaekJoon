import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Boj3025 {
	private static final char STONE = 'O';
	private static final char BLOCK = 'X';
	private static final char EMPTY = '.';
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int R = in.readInt();
		int C = in.readInt();
		
		char[][] board = new char[R][C];
		for(int i = 0; i < R; i++) {
			String line = in.readString();
			
			for(int j = 0; j < C; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		int N = in.readInt();
		int[] query = new int[N];
		
		for(int i = 0; i < N; i++) {
			query[i] = in.readInt() - 1;
		}
		
		System.out.println(drop(R, C, N, board, query));
	}
	
	private static StringBuilder drop(int r, int c, int n, char[][] arr, int[] q) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < q.length; i++) {
			int row = 0, col = q[i];
			arr[row][col] = STONE;
			
			while(true) {
				if(row == r - 1) break;
				if(arr[row + 1][col] == BLOCK) break;
				
				if(arr[row + 1][col] == STONE) {
					if(col != 0 && arr[row][col - 1] == EMPTY && arr[row + 1][col - 1] == EMPTY) {
						arr[row][col] = EMPTY;
						arr[row][col - 1] = STONE;
							
						col -= 1;
					}
					else if(col != c - 1 && arr[row][col + 1] == EMPTY && arr[row + 1][col + 1] == EMPTY) {
						arr[row][col] = EMPTY;
						arr[row][col + 1] = STONE;
							
						col += 1;
					}
					else {
						break;
					}
				}
				else {
					arr[row][col] = EMPTY;
					arr[row + 1][col] = STONE;
					
					row += 1;
				}
			}
		}
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				sb.append(arr[i][j]);
			}
			sb.append(NEW_LINE);
		}
		
		return sb;
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
