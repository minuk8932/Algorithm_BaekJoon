import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3697 {
	private static final String NEW_LINE = "\n";
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static int[] parent;
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[n][m];
			init(n, m);
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append(bfs(n, m, d, map)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void init(int N, int M) {
		parent = new int[N * M];
		
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
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
	
	private static int bfs(int N, int M, int D, int[][] h) {
		boolean[][] isVisited = new boolean[N][M];
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {				
				if(isVisited[row][col]) continue;
				isVisited[row][col] = true;
				
				int dSummit = h[row][col] - D;
				boolean isPassed = false;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						int pCurr = row * M + col;
						int pNext = nextRow * M + nextCol;
						
						if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
						if(isVisited[nextRow][nextCol]) continue;
							
						if(dSummit >= h[nextRow][nextCol]) isPassed = true;
							
						if(isPassed && h[nextRow][nextCol] > h[row][col]) {
							isVisited[nextRow][nextCol] = true;
							merge(pCurr, pNext);
						}
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		return getDminus();
	}
	
	private static int getDminus() {
		int count = 0;
		
		for(int i = 0; i < parent.length; i++) {
			System.out.println(parent[i]);
			if(parent[i] < 0) count += parent[i];
		}
		
		return -count;
	}
}
