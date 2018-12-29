package minimumcost_spanning_tree;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

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
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1 ,0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int N = in.readInt();
		int M = in.readInt();
		
		char[][] maze = new char[N][N];
		int[][] key = new int[N][N];
		int value = 0;
		
		for(int i = 0; i < N; i++) {
			String line = in.readString();
			
			for(int j = 0; j < N; j++) {
				maze[i][j] = line.charAt(j);
				
				if(maze[i][j] == 'S' || maze[i][j] == 'K') key[i][j] = value++;		// 노드 설정
			}
		}
		
		boolean search = bfs(maze, N, key);
		
		if(search) {		
			parent = new int[M + 1];
			init(M);
			
			System.out.println(kruskal(pq));
		}
		else {
			System.out.println(-1);
		}
	}
	
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
	
	private static boolean bfs(char[][] map, int n, int[][] k) {		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				if(map[row][col] != 'S' && map[row][col] != 'K') continue;
				boolean isReached = false;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				int[][] isVisited = new int[n][n];
				isVisited[row][col] = 1;
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
							if(isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] != '1') {
								isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;

								if(map[nextRow][nextCol] == 'S' || map[nextRow][nextCol] == 'K') {			// 도달 가능
									pq.offer(new Node(k[row][col], k[nextRow][nextCol], isVisited[nextRow][nextCol] - 1));
									isReached = true;
								}
									
								q.offer(new Point(nextRow, nextCol));
							}
						}
					}
				}
				
				if(!isReached) return false;		// 도달 못하는 경우
			}
		}
		
		return true;
	}
	
	private static void init(int n) {
		for(int i = 0; i < n + 1; i++) {
			parent[i] = i;
		}
	}
	
	private static int find(int x) {
		if(x == parent[x]) return x;
		else return find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) parent[y] = x;
		else parent[x] = y;
	}
	
	private static boolean isCycle(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		else return false;
	}
	
	private static int kruskal(PriorityQueue<Node> pq) {
		int minCost = 0;
		
		while(!pq.isEmpty()) {
			Node next = pq.poll();
			
			if(!isCycle(next.from, next.to)) {
				merge(next.from, next.to);
				minCost += next.cost;
			}
		}
		
		return minCost;
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
