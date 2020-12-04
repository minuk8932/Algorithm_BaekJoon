package minimumcost_spanning_tree;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 
 * 	@author minchoba
 *	백준 1944번: 복제 로봇
 *
 *	@see https://www.acmicpc.net/problem/1944/
 *
 */
public class Boj1944 {
	private static PriorityQueue<Node> pq = new PriorityQueue<>();
	private static int[] parent;
	private static int N, M;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1 ,0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final char BLOCK = '1';
	private static final char START = 'S';
	private static final char KEY = 'K';

	private static ArrayList<Point> start = new ArrayList<>();
	private static int head = -1;

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	private static class Node implements Comparable<Node>{
		int from;
		int to;
		int cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		N = in.readInt();
		M = in.readInt();
		
		char[][] maze = new char[N][N];
		int[][] key = new int[N][N];
		int value = 0;
		
		for(int i = 0; i < N; i++) {
			String line = in.readString();
			
			for(int j = 0; j < N; j++) {
				maze[i][j] = line.charAt(j);

				if(maze[i][j] == KEY || maze[i][j] == START){
					if (maze[i][j] == START) head = value;
					key[i][j] = value++;
					start.add(new Point(i, j));
				}
			}
		}

		bfs(maze, key);
		int cost = kruskal(pq);
		System.out.println(-parent[find(head)] == M + 1 ? cost: -1);		// find all keies?
	}
	
	private static void bfs(char[][] map, int[][] k) {
		for(Point s: start) {
			Queue<Point> q = new LinkedList<>();
			q.offer(s);

			int[][] isVisited = new int[N][N];
			isVisited[s.row][s.col] = 1;

			while (!q.isEmpty()) {
				Point current = q.poll();

				for (final int[] DIRECTION : DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];

					if (outOfRange(nextRow, nextCol)) continue;
					if (isVisited[nextRow][nextCol] != 0 || map[nextRow][nextCol] == BLOCK) continue;
					isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;

					if (map[nextRow][nextCol] == KEY || map[nextRow][nextCol] == START) {				// cost save
						pq.offer(new Node(k[s.row][s.col], k[nextRow][nextCol], isVisited[nextRow][nextCol] - 1));
					}

					q.offer(new Point(nextRow, nextCol));
				}
			}
		}
	}
	
	private static void init() {
		for(int i = 0; i <= M; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static boolean merged(int x, int y) {
		x = find(x);
		y = find(y);

		if(x == y) return true;

		if(parent[x] < parent[y]){
			parent[x] += parent[y];
			parent[y] = x;
		}
		else{
			parent[y] += parent[x];
			parent[x] = y;
		}

		return false;
	}
	
	private static int kruskal(PriorityQueue<Node> pq) {
		parent = new int[M + 1];
		init();

		int minCost = 0;
		
		while(!pq.isEmpty()) {
			Node next = pq.poll();
			
			if(merged(next.from, next.to)) continue;
			minCost += next.cost;
		}
		
		return minCost;
	}

	private static boolean outOfRange(int row, int col) {
		return row < 0 || row >= N || col < 0 || col >= N;
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
