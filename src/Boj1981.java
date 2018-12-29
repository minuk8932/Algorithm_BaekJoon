import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1981 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static int chMin;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n][n];
		int start = 201, end = 0;
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] > end) end = map[i][j];
				if(map[i][j] < start) start = map[i][j];
			}
		}
		
		int diff = 201;
		int min = start;
		int mid = 0;
		
		while(start <= end) {
			chMin = 201;
			mid = (start + end) / 2;
			boolean search = bfs(n, map, min, mid);

			if(search) {
				end = mid - 1;
				if(diff > mid - chMin) diff = mid - chMin;
			}
			else {
				start = mid + 1;
			}
		}
		
		System.out.println(diff);
	}
	
	private static class Point {
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;		
		}
	}
	
	private static boolean bfs(int N, int[][] arr, int s, int value) {		
		boolean[][] isVisited = new boolean[N][N];
		isVisited[0][0] = true;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
					if(!isVisited[nextRow][nextCol] && (arr[nextRow][nextCol] >= s && arr[nextRow][nextCol] < value + 1)) {
						isVisited[nextRow][nextCol] = true;
						
						if(arr[nextRow][nextCol] < chMin) chMin = arr[nextRow][nextCol];
						
						if(nextRow == N - 1 && nextCol == N - 1) return true;
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		return false;
	}
}
