package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17836번: 공주님을 구해라!
 *
 *	@see https://www.acmicpc.net.problem/17836/
 *
 */
public class Boj17836 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = Integer.MAX_VALUE;
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		Point sword = new Point(-1, -1);
	
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) sword = new Point(i, j);
			}
		}
		
		long result = 0;
		
		long normal = search(N, M, map, new Point(0, 0), new Point(N - 1, M - 1));			// save with no sword
		long toSword = search(N, M, map, new Point(0, 0), sword);							// time to sword place
		long fromSword = breakTrough(N, M, map, sword);										// save princess time from sword place to princess
		
		result = Math.min(normal, toSword + fromSword);
		System.out.println(result <= T ? result: "Fail");
	}
	
	private static int search(int n, int m, int[][] arr, Point start, Point finish) {
		int[][] visit = new int[n][m];
		for(int i = 0; i < n; i++) {
			Arrays.fill(visit[i], INF);
		}
		
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		
		visit[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
				if(visit[nextRow][nextCol] != INF || arr[nextRow][nextCol] == 1) continue;
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return visit[finish.row][finish.col] - 1;
	}
	
	private static int breakTrough(int n, int m, int[][] arr, Point start) {
		int[][] visit = new int[n][m];
		for(int i = 0; i < n; i++) {
			Arrays.fill(visit[i], INF);
		}
		
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		
		visit[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
				if(visit[nextRow][nextCol] != INF) continue;
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return visit[n - 1][m - 1] - 1;
	}
}
