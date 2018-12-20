package breadth_first_search;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 3197번: 백조의 호수
 *
 *	@see https://www.acmicpc.net/problem/3197/
 *
 */
public class Boj3197 {
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		int R = in.readInt();
		int C = in.readInt();
		
		Point[] start = new Point[2];
		int idx = 0;
		
		int[][] melt = new int[R][C];
		char[][] map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String line = in.readString();
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == 'L') {
					start[idx] = new Point(i, j);
					idx++;
				}
			}
		}
		
		melt = init(R, C, melt, map);		// 얼음이 녹는 시간 측정
		
		int left = 0, right = 0, min = Integer.MAX_VALUE;
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(melt[i][j] > right) right = melt[i][j];
			}
		}

		while(left <= right) {				// 이분 탐색으로 만나는 날짜를 찾아줌
			int mid = (left + right) / 2;
			boolean attaced = canMeet(R, C, melt, mid, start);
			
			if(!attaced) {			// 안만난 경우 일수를 증가
				left = mid + 1;
			}
			else {						// 만난 경우 최소 일수를 찾음
				if(min > mid) min = mid;
				right = mid - 1;
			}
		}
		
		System.out.println(min);		// 결과 출력
	}

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static int[][] init(int N, int M, int[][] arr, char[][] graph){
		int counts = 1;
		boolean[][] isVisited = new boolean[N][M];
		
		Queue<Point> q = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(graph[i][j] == 'L' || graph[i][j] == '.') {		// 녹기 시작할 기준부터 큐에 저장
					q.offer(new Point(i, j));
					isVisited[i][j] = true;
				}
			}
		}
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0; i < size; i++) {			// 테두리부터(1일차) 녹여가야하므로 큐내에 들어있는 것들만 우선적으로 돌림
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < M) {
						if(!isVisited[nextRow][nextCol] && graph[nextRow][nextCol] != 'L') {
							arr[nextRow][nextCol] = counts;
							isVisited[nextRow][nextCol] = true;
							
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
			
			counts++;
		}
		
		return arr;
	}
	
	private static boolean canMeet(int N, int M, int[][] arr, int limits, Point[] start) {
		boolean[][] isVisited = new boolean[N][M];

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start[0].row, start[0].col));
		
		isVisited[start[0].row][start[0].col] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
					if(!isVisited[nextRow][nextCol] && arr[nextRow][nextCol] <= limits) {
						isVisited[nextRow][nextCol] = true;
						
						if(nextRow == start[1].row && nextCol == start[1].col) return true;
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		return false;
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
