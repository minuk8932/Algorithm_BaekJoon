package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17472번: 다리 만들기2
 *
 *	@see https://www.acmicpc.net/problem/17472/
 *
 */
public class Boj17472 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = 1_000_000;
	
	private static ArrayList<Point> start = new ArrayList<>();
	private static int[][] map;
	private static int[][] check;
	private static int[][] visit;
	private static int areas;
	
	private static int[] parent;
	private static HashMap<Integer, Point> info = new HashMap<>();
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static class Path implements Comparable<Path>{
		int from;
		int to;
		int cost;
		
		public Path(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Path p) {
			return this.cost < p.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		init(N * M);								// disjoint-set init
		bfs(N, M);									// make island group
		
		check = new int[areas + 1][areas + 1];
		for(int i = 1; i < areas + 1; i++) {		// cost arr init
			Arrays.fill(check[i], INF);
		}
		
		for(Point s: start) {
			int src = map[s.row][s.col];
			visit = new int[N][M];
			
			for(final int[] DIRECTION: DIRECTIONS) {
				visit[s.row][s.col] = 1;
				dfs(N, M, s, new Point(DIRECTION[ROW], DIRECTION[COL]), src);
			}
		}
		
		System.out.println(getResult(N, M));
	}
	
	private static void init(int S) {
		parent = new int[S];

		for(int i = 0; i < S; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0 ) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static boolean merge(int x, int y) {			// union
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
	
	private static void dfs(int n, int m, Point s ,Point dir, int src) {
		if(map[s.row][s.col] != 0 && src != map[s.row][s.col]) {				// can make bridge
			if(visit[s.row][s.col] > 3) {
				check[src][map[s.row][s.col]] = Math.min(check[src][map[s.row][s.col]], visit[s.row][s.col] - 2);		// real bridge length
				check[map[s.row][s.col]][src] = check[src][map[s.row][s.col]];
			}
			
			return;
		}
		
		Point next = new Point(s.row + dir.row, s.col + dir.col);
		if(next.row < 0 || next.row >= n || next.col < 0 || next.col >= m || visit[next.row][next.col] != 0) return;
		if(src == map[next.row][next.col]) return;
		visit[next.row][next.col] = visit[s.row][s.col] + 1;
		dfs(n, m, next, dir, src);								// one way search
	}
	
	private static void bfs(int n, int m) {
		boolean[][] visited = new boolean[n][m];
		Queue<Point> q = new LinkedList<>();
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < m; col++) {
				if(visited[row][col] || map[row][col] == 0) continue;
				visited[row][col] = true;
				
				q.offer(new Point(row, col));
				map[row][col] = ++areas;
				info.put(areas, new Point(row, col));			// save island's set
				
				start.add(new Point(row, col));
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
						if(visited[nextRow][nextCol] || map[nextRow][nextCol] == 0) continue;
						visited[nextRow][nextCol] = true;
						
						start.add(new Point(nextRow, nextCol));
						
						map[nextRow][nextCol] = areas;
						
						merge(nextRow * m + nextCol, row * m + col);
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
	}
	
	private static long getResult(int n, int m) {
		long result = 0;
		
		PriorityQueue<Path> pq = new PriorityQueue<>();
		for(int i = 1; i < areas + 1; i++) {				// node to node, cost
			for(int j = 1; j < areas + 1; j++) {
				pq.offer(new Path(i, j, check[i][j]));
			}
		}
		
		while(!pq.isEmpty()) {								// MST: kruskal
			Path current = pq.poll();
			
			int node1 = info.get(current.from).row * m + info.get(current.from).col;
			int node2 = info.get(current.to).row * m + info.get(current.to).col;
			
			if(!merge(node1, node2)) {
				result += current.cost;
			}
		}
		
		int count = 0;
		for(int i = 0; i < parent.length; i++) {
			if(parent[i] < -1) count++;
		}
		
		return count >= 2 || result >= INF ? -1: result;			// if there are islands or no linked
	}
}
