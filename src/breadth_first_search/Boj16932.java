package breadth_first_search;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 16932번: 모양 만들기
 *
 *	@see https://www.acmicpc.net/problem/16932/
 *
 */
public class Boj16932 {
	private static int[][] set;
	private static int[] pair;
	
	private static ArrayList<Point> zeroes = new ArrayList<>();
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static class Point{
		int row, col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		
		boolean[][] map = new boolean[N][M];
		set = new int[N][M];
		pair = new int[N * M + 1];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int value = in.readInt();
				
				map[i][j] = value == 1 ? true : false;
				if(!map[i][j]) zeroes.add(new Point(i, j));
			}
		}
		
		makeSet(N, M, map);
		System.out.println(getMax(N, M, map));
	}
	
	private static void makeSet(int n, int m, boolean[][] arr) {
		Queue<Point> q = new LinkedList<>();
		int count = 1;
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < m; col++) {
				if(!arr[row][col] || set[row][col] != 0) continue;
				set[row][col] = count;
				int value = 1;
				
				q.offer(new Point(row, col));
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) continue;
						if(!arr[nextRow][nextCol] || set[nextRow][nextCol] != 0) continue;
						set[nextRow][nextCol] = count;
						value++;
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
				
				pair[count++] = value;			// 구역을 지정하고, 각 구역의 크기를 다시 저장
			}
		}
	}
	
	private static int getMax(int n, int m, boolean[][] arr) {
		HashSet<Integer> hs;
		int max = 0;
		
		for(Point zero: zeroes) {
			hs = new HashSet<>();			// 배열 재 생성시 시간이 오래걸림... (이유는 아직 모름)
			int size = 1;
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int adjRow = zero.row + DIRECTION[ROW];
				int adjCol = zero.col + DIRECTION[COL];
					
				if(adjRow < 0 || adjCol < 0 || adjRow >= n || adjCol >= m) continue;		// 아직 탐색하지 않은 값들만 더해줌
				if(hs.contains(set[adjRow][adjCol]) || !arr[adjRow][adjCol]) continue;
				hs.add(set[adjRow][adjCol]);
					
				size += pair[set[adjRow][adjCol]];
			}

			if(size > max) max = size;
		}

		return max;
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
