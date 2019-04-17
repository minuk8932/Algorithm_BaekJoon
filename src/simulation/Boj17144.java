package simulation;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 17144번: 미세먼지 안녕!
 *
 *	@see https://www.acmicpc.net/problem/17144/
 *
 */
public class Boj17144 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int CLEANER = -1;
	
	private static int[][] room;
	private static int[][] tmp;
	private static int row = -1;
	private static Queue<Point> q = new LinkedList<>();
	
	private static class Point{
		int row;
		int col;
		
		public Point (int row ,int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int R = in.readInt();
		int C = in.readInt();
		int T = in.readInt();
		
		room = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				room[i][j] = in.readInt();
				
				if(room[i][j] == CLEANER && row == -1) row = i;
				if(room[i][j] >= 5) q.offer(new Point(i, j));		// 확산 가능한 먼지만 큐에 저장
			}
		}

		System.out.println(process(R, C ,T));
	}
	
	private static int process(int r, int c, int time) {
		while(time-- > 0) {
			tmp = new int[r][c];
			
			while(!q.isEmpty()) {
				Point dust = q.poll();
				
				int count = 0;
				int spread = room[dust.row][dust.col] / 5;		// 확산량
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = dust.row + DIRECTION[ROW];
					int nextCol = dust.col + DIRECTION[COL];
					
					if(nextRow < 0 || nextRow >= r || nextCol < 0 || nextCol >= c) continue;
					if(room[nextRow][nextCol] == CLEANER) continue;
					tmp[nextRow][nextCol] += spread;
					
					count++;
				}
				
				room[dust.row][dust.col] -= (spread * count);		// 확산된 만큼 근원지 먼지 감소
			}
			
			spreading(r, c);		// 확산된 먼지 값을 기존 맵에 추가
			
			pushTop(c);				// 공기 청정기 작동
			pushBottom(r, c);
			
			initQ(r, c);			// 현 상태에서 큐에 저장 가능한 먼지를 담아줌
		}
		
		return getDust(r, c);
	}
	
	private static void spreading(int r, int c) {
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(room[i][j] == -1) continue;
				room[i][j] += tmp[i][j];
			}
		}
	}
	
	private static void initQ(int r, int c) {
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(room[i][j] >= 5) q.offer(new Point(i, j));
			}
		}
	}
	
	private static int getDust(int r ,int c) {
		int total = 0;
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(room[i][j] > 0) total += room[i][j];
			}
		}
		
		return total;
	}
	
	private static void pushTop(int c) {
		int startRow = row;
		
		for(int row = startRow - 1; row > 0; row--) {
			room[row][0] = room[row - 1][0];
		}
		
		for(int col = 0; col < c - 1; col++) {
			room[0][col] = room[0][col + 1];
		}
		
		for(int row = 1; row < startRow + 1; row++) {
			room[row - 1][c - 1] = room[row][c - 1];
		}
		
		for(int col = c - 1; col > 0; col--) {
			if(room[startRow][col - 1] == -1) {
				room[startRow][col] = 0;
				continue;
			}
			
			room[startRow][col] = room[startRow][col - 1];
		}
	}
	
	private static void pushBottom(int r, int c) {
		int startRow = row + 1;
		
		for(int row = startRow + 1; row < r - 1; row++) {
			room[row][0] = room[row + 1][0];
		}
		
		for(int col = 0; col < c - 1; col++) {
			room[r - 1][col] = room[r - 1][col + 1];
		}
		
		for(int row = r - 1; row > startRow; row--) {
			room[row][c - 1] = room[row - 1][c - 1];
		}
		
		for(int col = c - 1; col > 0; col--) {
			if(room[startRow][col - 1] == -1) {
				room[startRow][col] = 0;
				continue;
			}
			room[startRow][col] = room[startRow][col - 1];
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
