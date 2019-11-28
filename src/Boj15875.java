import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Boj15875 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static int[] parent;
	private static ArrayList<Point> start = new ArrayList<>();
	private static ArrayList<Point> mins = new ArrayList<>();
	
	private static class Point implements Comparable<Point>{
		int row;
		int col;
		int val;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public Point(int row, int col, int val) {
			this.row = row;
			this.col = col;
			this.val = val;
		}

		@Override
		public int compareTo(Point p) {
			return this.val < p.val ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int H = in.readInt();
		int W = in.readInt();
		
		int[][] map = new int[H][W];
		parent = new int[H * W];
		Arrays.fill(parent, -1);
		
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				map[i][j] = in.readInt();
				start.add(new Point(i, j, map[i][j]));
				
				if(min > map[i][j]) min = map[i][j];
			}
		}
		
		Collections.sort(start);
		System.out.println(bfs(H, W, map));
	}
	
	private static int bfs(int h, int w, int[][] arr) {
		boolean[][] visit = new boolean[h][w];
		Queue<Point> q = new LinkedList<>();
		
		int max = 0;
		
		for(Point s: start) {
			if(visit[s.row][s.col]) continue;
			visit[s.row][s.col] = true;
			
			q.offer(new Point(s.row, s.col));
			int par = s.row * w + s.col;
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) {
						parent[current.row * w + current.col] = -1;
						continue;
					}
					
					if(arr[nextRow][nextCol] > s.val) continue;
					
					if(visit[nextRow][nextCol]) continue;
					visit[nextRow][nextCol] = true;
					
					merged(par, nextRow * w + nextCol);
					max = Math.max(max, -parent[find(par)]);
					
					for(int i = 0; i < parent.length; i++) {
						if(i % w == 0) System.out.println();
						System.out.print(parent[i] + " ");
					}
					System.out.println();
					
					q.offer(new Point(nextRow, nextCol));
				}
			}
		}
		
		return max;
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static boolean merged(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
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
