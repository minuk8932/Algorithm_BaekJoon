import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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
		
		int[][] map = new int[H + 2][W + 2];
		int[] depth = new int[H * W];
		int idx = 0;
		
		parent = new int[(H + 2) * (W + 2)];
		init(H, W);
		
		for(int i = 1; i < H + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < W + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());				
				depth[idx++] = map[i][j];
			}
		}
		
		
		int max = 0;
		Arrays.sort(depth);
		
		for(int i = 0; i < depth.length; i++) {
			bfs(H, W, map, depth[i]);
			
			for(int set = 0; set < parent.length; set++) {
				if(max < -parent[set]) max = -parent[set];
			}
			
			for(int row = 0; row < H + 2; row++) {
				for(int col = 0; col < W + 2; col++) {
					System.out.print(parent[row * (W + 2) + col] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		System.out.println(max);
	}
	
	private static void init(int h, int w) {
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
	
	private static void bfs(int h, int w, int[][] arr, int limit) {
		boolean[][] isVisited = new boolean[h + 2][w + 2];
		LinkedList<Integer> del = new LinkedList<>();

		for(int row = 1; row < h + 1; row++) {
			for(int col = 1; col < w + 1; col++) {
				if(isVisited[row][col] || arr[row][col] > limit) continue;
				isVisited[row][col] = true;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
							
						if(nextRow < 0 || nextRow > h + 1 || nextCol < 0 || nextCol > w + 1) continue;
						if(isVisited[nextRow][nextCol] || arr[nextRow][nextCol] > limit) continue;
						isVisited[nextRow][nextCol] = true;
						
						merge(current.row * (w + 2) + current.col, nextRow * (w + 2) + nextCol);
						
						if(nextRow == 1 || nextCol == 1 || nextRow == h || nextCol == w) {
							del.add(parent[find(nextRow * (w + 2) + nextCol)]);
							break;
						}

						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		while(!del.isEmpty()) {
			int cut = del.remove();
			
			for(int i = 0; i < parent.length; i++) {
				if(parent[i] == cut) parent[i] = -1;
			}
		}
	}
}
