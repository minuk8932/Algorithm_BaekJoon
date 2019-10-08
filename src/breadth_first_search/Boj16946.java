package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 16946번: 벽 부수고 이동하기 4
 *
 *	@see https://www.acmicpc.net/problem/16946/
 *
 */
public class Boj16946 {
	private static final int MOD = 10;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static final String NEW_LINE = "\n";
	
	private static ArrayList<Point> target = new ArrayList<>();
	private static ArrayList<Point> source = new ArrayList<>();
	
	private static int[][] visit;
	private static boolean[][] block;
	
	private static int[] parent;
	
	private static class Point{
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
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		block = new boolean[N][M];
		
		init(N, M);
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
				
				if(map[i][j] == 1) {
					target.add(new Point(i, j));
					block[i][j] = true;
				}
				else {
					source.add(new Point(i, j));
				}
			}
		}
		
		System.out.println(search(N, M, map));
	}
	
	private static void init(int n, int m) {
		parent = new int[n * m];
		
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
	
	private static String search(int n, int m, int[][] map) {
		visit = new int[n][m];
		filling(n, m, map);								// get empty space size

		boolean[] setVisit = new boolean[n * m];
		
		for(Point start: target) {
			ArrayList<Integer> rollback = new ArrayList<>();
			int sum = 1;
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = start.row + DIRECTION[ROW];
				int nextCol = start.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
				int idx = find(nextRow * m + nextCol);
				if(block[nextRow][nextCol] || setVisit[idx]) continue;		// check dup
				setVisit[idx] = true;
				
				sum += visit[nextRow][nextCol];
				rollback.add(idx);
			}
			
			map[start.row][start.col] = (sum % MOD);	// filled value
			for(int i: rollback) {						// rollback used value
				setVisit[i] = false;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(map[i][j]);
			}
			sb.append(NEW_LINE);
		}
		
		return sb.toString();
	}
	
	private static void filling(int n, int m, int[][] arr){
		ArrayList<Point> reset = new ArrayList<>();
		
		for(Point start: source) {
			if(visit[start.row][start.col] != 0) continue;
			
			Queue<Point> q = new LinkedList<>();
			q.offer(new Point(start.row, start.col));
			
			visit[start.row][start.col] = 1;
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
					if(visit[nextRow][nextCol] != 0 || arr[nextRow][nextCol] == 1) continue;
					visit[nextRow][nextCol] = 1;
					visit[start.row][start.col]++;
					
					merge(start.row * m + start.col, nextRow * m + nextCol);			// merge linked space
					q.offer(new Point(nextRow, nextCol));
				}
			}
			
			reset.add(new Point(start.row, start.col, visit[start.row][start.col]));
		}
		
		visit = new int[n][m];
		
		for(Point fill: reset) {
			Queue<Point> q = new LinkedList<>();
			q.offer(new Point(fill.row, fill.col));
			
			visit[fill.row][fill.col] = fill.val;
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
					if(visit[nextRow][nextCol] != 0 || arr[nextRow][nextCol] == 1) continue;
					visit[nextRow][nextCol] = fill.val;
					
					q.offer(new Point(nextRow, nextCol));
				}
			}
		}
	}
}
