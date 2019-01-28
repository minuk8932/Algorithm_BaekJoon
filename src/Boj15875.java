import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15875 {
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[H][W];
		int left = 1_000_000_001, right = 0;
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(left > map[i][j]) left = map[i][j];
				if(right < map[i][j]) right = map[i][j];
			}
		}
		
		parent = new int[H * W];
		Arrays.fill(parent, -1);
		
		System.out.println(binarySearch(H, W, map, left, right));
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static boolean merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		
		if(parent[x] > parent[y]) {
			parent[y] += parent[x];
			parent[x] = y;
		}
		else {
			parent[x] += parent[y];
			parent[y] = x;
		}
		
		return false;
	}
	
	private static int binarySearch(int h, int w, int[][] arr, int start, int end) {
		int max = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			int size = 0;
			bfs(h, w, arr, mid);
			
			if(size <= max) {
				start = mid + 1;
				if(size > max) max = size;
			}
			else {
				end = mid - 1;
			}
		}
		
		return max;
	}
	
	private static void bfs(int h, int w, int[][] arr, int limit) {
		boolean[][] isVisited = new boolean[h][w];
		
		for(int row = 0; row < h; row++) {
			for(int col = 0; col < w; col++) {
				if(isVisited[row][col] || arr[row][col] >= limit) continue;
				isVisited[row][col] = true;
					
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = row + DIRECTION[ROW];
					int nextCol = col + DIRECTION[COL];
						
					if(nextRow <= 0 || nextRow >= h - 1 || nextCol <= 0 || nextCol >= w - 1) continue;
					if(isVisited[nextRow][nextCol] || arr[nextRow][nextCol] >= limit) continue;
					if(merge(oneDsize(row, col, w), oneDsize(nextRow, nextCol, w))) continue;
					isVisited[nextRow][nextCol] = true;
				}
			}
		}
	}
	
	private static int oneDsize(int row, int col, int leng) {
		return row * leng + col;
	}
}
