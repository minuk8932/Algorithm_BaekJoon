import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Boj15875 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static int[] parent;
	private static boolean[][] outLine;
	private static boolean flag = false;
	
	private static class Point implements Comparable<Point>{
		int row;
		int col;
		int depth;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public Point(int row, int col, int depth) {
			this.row = row;
			this.col = col;
			this.depth = depth;
		}

		@Override
		public int compareTo(Point p) {
			return this.depth < p.depth ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int H = in.readInt();
		int W = in.readInt();
		
		PriorityQueue<Point> saved = new PriorityQueue<>();
		int[][] map = new int[H][W];
		outLine = new boolean[H][W];

		init(H, W);
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				map[i][j] = in.readInt();
				saved.add(new Point(i, j, map[i][j]));
			}
		}
		
		System.out.println(search(H, W, map, saved));
	}
	
	private static void init(int h, int w) {
		parent = new int[h * w];
		
		for(int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
	
	private static boolean isCycle(int x, int y) {
		x = find(x);
		y = find(y);
		
		return x == y ? true: false;
	}
	
	private static int search(int h, int w, int[][] arr, PriorityQueue<Point> pq) {	
		int max = 0;
		
		while(!pq.isEmpty()) {
			Point current = pq.poll();
			if(parent[find(current.row * w + current.col)] != -1 || outLine[current.row][current.col]) continue;
			
			boolean isPond = setPond(h, w, arr, current);
			
			if(isPond) {
				int size = parent[find(current.row * w + current.col)];
				max = Math.max(max, size == -1 ? 0 : -size);
			}
			
//			for(int i = 0; i < parent.length; i++) {
//				System.out.print(parent[i] + " ");
//				if((i + 1) % 5 == 0) System.out.println();
//			}
//			System.out.println();
		}

		return max == 0 && flag ? 1: max;
	}
	
	private static boolean setPond(int h, int w, int[][] arr, Point start) {
		boolean[][] visit = new boolean[h][w];
		LinkedList<Point> tmp = new LinkedList<>();
		
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		
		visit[start.row][start.col] = true;
		outLine[start.row][start.col] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			outLine[current.row][current.col] = true;
			if(current.row == 0 || current.row == h - 1 || current.col == 0 || current.col == w - 1) return false;
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= h || nextCol >= w) continue;
				if(visit[nextRow][nextCol] || isCycle(current.row * w + current.col, nextRow * w + nextCol)) continue;
				
				if(arr[nextRow][nextCol] <= start.depth) {
					visit[nextRow][nextCol] = true;

					tmp.add(new Point(nextRow, nextCol));
					q.offer(new Point(nextRow, nextCol));
				}
			}
		}
		
		flag = true;
		outLine[start.row][start.col] = false;
		
		for(Point p: tmp) {
            int x = start.row * w + start.col;
            int y = p.row * w + p.col;
            
			if(!isCycle(x, y)) {
				merge(x, y);
				outLine[p.row][p.col] = false;
			}
		}
		
		return true;
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
