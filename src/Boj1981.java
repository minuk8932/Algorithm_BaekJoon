import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1981 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static int max = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int diff = 0;
		
		while(map[0][0] > 0) {
			map[0][0]--;
			diff++;	
		}

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = Math.abs(map[i][j] - diff);
			}
		}
		
		map[0][0] = 0;
		
		bfs(n, map);
		
		System.out.println(max);
	}
	private static class Point {
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;		
		}
	}
	
	private static void bfs(int N, int[][] map) {
		boolean[][] isVisited = new boolean[N][N];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		
		int minCost = Math.abs(map[0][0] - map[1][0]);
		max = map[1][0];
		
		isVisited[0][0] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			int cost = 0;
			int loop = 0, size = q.size();
			int tmpRow = 0, tmpCol = 0, tmpCost = 201;
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
					cost = Math.abs(map[current.row][current.col] - map[nextRow][nextCol]);
					
					if(minCost < cost) loop++;
					
					if(size-- > 0) {
						if(tmpCost > cost) {
							tmpCost = cost;
							tmpRow = nextRow;
							tmpCol = nextCol;
						}
						continue;
					}
					
					if(size == loop) {
						nextRow = tmpRow;
						nextCol = tmpCol;
						max = tmpCost;
					}
					
					if(max < map[nextRow][nextCol]) max = map[nextRow][nextCol];
					
					if(!isVisited[nextRow][nextCol]) {
						System.out.println(nextRow + " " + nextCol);
						isVisited[nextRow][nextCol] = true;
						
						if(nextRow == N - 1 && nextCol == N - 1) return;						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
	}
}
