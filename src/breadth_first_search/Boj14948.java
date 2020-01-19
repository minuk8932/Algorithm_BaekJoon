package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 14948번: 군대 탈출
 *
 *	@see https://www.acmicpc.net/problem/14948/
 *
 */
public class Boj14948 {
	private static int end;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static class Point{
		int row;
		int col;
		boolean used;
		
		public Point(int row, int col, boolean used) {
			this.row = row;
			this.col = col;
			this.used = used;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] basecamp = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < m; j++) {
				basecamp[i][j] = Integer.parseInt(st.nextToken());
				if(end < basecamp[i][j]) end = basecamp[i][j];			// the max value
			}
		}
		
		System.out.println(binarySearch(n, m, basecamp));
	}
	
	private static int binarySearch(int n, int m, int[][] map) {
		int max = Integer.MAX_VALUE;
		int start = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			boolean flag = bfs(n, m, map, mid);							// find level
			
			if(flag) {
				max = Math.min(mid, max);								// min level escape
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		
		return max;
	}
	
	private static boolean bfs(int n, int m, int[][] map, int target) {
		if(n - 1 == 0 && m - 1 == 0 && map[0][0] == target) return true;
		if(map[0][0] > target) return false;
		boolean[][][] visit = new boolean[2][n][m];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, false));
		
		visit[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(range(n, m, nextRow, nextCol)) continue;
				if(map[nextRow][nextCol] > target) {						// use equipment
					if(!current.used) {
						int jumpRow = nextRow + DIRECTION[ROW];
						int jumpCol = nextCol + DIRECTION[COL];
						
						if(range(n, m, jumpRow, jumpCol)) continue;
						if(visit[1][jumpRow][jumpCol] || map[jumpRow][jumpCol] > target) continue;
						visit[1][jumpRow][jumpCol] = true;
						
						if(escape(jumpRow, jumpCol, n - 1, m - 1)) return true;
						q.offer(new Point(jumpRow, jumpCol, true));
					}
					
					continue;
				}
				
				if(visit[current.used ? 1: 0][nextRow][nextCol]) continue;
				visit[current.used ? 1: 0][nextRow][nextCol] = true;
				
				if(escape(nextRow, nextCol, n - 1, m - 1)) return true;
				q.offer(new Point(nextRow, nextCol, current.used));
			}
		}
		
		return false;
	}
	
	private static boolean range(int n, int m, int row, int col) {
		return row < 0 || col < 0 || row >= n || col >= m;
	}
	
	private static boolean escape(int row, int col, int dr, int dc) {
		return row == dr && col == dc;
	}
}
