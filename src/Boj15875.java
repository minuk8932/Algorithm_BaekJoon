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
		
		Arrays.sort(depth);
		System.out.println(search(H, W, map, depth));
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
	
	private static int search(int h, int w, int[][] arr, int[] depth) {	
		int max = 0;
		int comp = 0;
		
		for(int size = 0; size < depth.length; size++) {
			if(depth[size] == comp || parent[size] != -1) continue;
			comp = depth[size];
			
			boolean[][] isVisited = new boolean[h + 2][w + 2];	
			
			for(int row = 1; row < h + 1; row++) {
				for(int col = 1; col < w + 1; col++) {
					if(parent[row * (w + 2) + col] != -1) continue;
					if(isVisited[row][col] || arr[row][col] > depth[size]) continue;
					isVisited[row][col] = true;
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()) {
						Point current = q.poll();
								
						for(final int[] DIRECTION: DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
										
							if(nextRow < 0 || nextRow > h + 1 || nextCol < 0 || nextCol > w + 1) continue;
							if(isVisited[nextRow][nextCol] || arr[nextRow][nextCol] > depth[size]) continue;
							isVisited[nextRow][nextCol] = true;
									
							merge(current.row * (w + 2) + current.col, nextRow * (w + 2) + nextCol);
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
			
			int pond = getPond();
			if(pond > max) max = pond;
			
			System.out.println(depth[size]);
			for(int x = 0; x < h + 2; x++) {
				for(int y = 0; y < w + 2; y++) {
					System.out.print(parent[x * (w + 2) + y] + " ");
				}
				System.out.println();
			}
		}
		
		return max;
	}
	
	private static int getPond() {
		int size = 0;
		
		int except = parent[0];
		except = except == -1 ? 0 : except;
		
		for(int pos = 0; pos < parent.length; pos++) {
			if(parent[pos] == -1 || except == parent[pos]) continue;
			if(-parent[pos] > size && parent[except] != parent[pos]) size = -parent[pos];
		}
		
		return size;
	}
}
