import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj2842 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_000;
	
	private static final char POST_OFFICE = 'P';
	private static final char HOME = 'K';
	
	private static boolean isDelivered;
	private static boolean[][] isVisited;
	
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
		int N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		Point post = new Point(-1, -1); 
		ArrayList<Point> edge = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == POST_OFFICE) post = new Point(i, j);
				else if(map[i][j] == HOME) edge.add(new Point(i, j));
			}
		}
		
		int[][] area = new int[N][N];
		int min = INF, max = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == POST_OFFICE || map[i][j] == HOME) {
					min = Math.min(area[i][j], min);
					max = Math.max(area[i][j], max);
				}
			}
		}
		
		System.out.println(binarySearch(N, map, area, edge, max - min, max));
	}
	
	private static int binarySearch(int n, char[][] map, int[][] cost, ArrayList<Point> list, int start, int end) {
		int result = INF;
		
		while(start <= end){
			int mid = (start + end) / 2;
			
			for(Point s: list) {
				isDelivered = false;
				isVisited = new boolean[n][n];
				
				dfs(n, map, cost, s.row, s.col, mid, cost[s.row][s.col], cost[s.row][s.col]);
				if(!isDelivered) break;
			}
			
			if(isDelivered) {
				end = mid - 1;
				if(result > mid) result = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return result;
	}
	
	private static void dfs(int n, char[][] map, int[][] cost, int row, int col, int limit, int min, int max) {
		if(map[row][col] == POST_OFFICE) {
			isDelivered = true;
			return;
		}
		
		if(isVisited[row][col]) return;
		isVisited[row][col] = true;
		
		for(final int[] DIRECTION: DIRECTIONS) {
			int nextRow = row + DIRECTION[ROW];
			int nextCol = col + DIRECTION[COL];
			
			if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
			if(isVisited[nextRow][nextCol]) continue;
			int tmin = Math.min(min, cost[nextRow][nextCol]);
			int tmax = Math.max(max, cost[nextRow][nextCol]);
			
			if(limit < tmax - tmin) continue;
			dfs(n, map, cost, nextRow, nextCol, limit, tmin, tmax);
			isVisited[nextRow][nextCol] = false;
		}
	}
}
