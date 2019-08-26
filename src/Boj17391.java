import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17391 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
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
		
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(N, M, map));
	}
	
	private static int bfs(int n, int m, int[][] arr) {
		int min = Integer.MAX_VALUE;
		
		Queue<Point> q = new LinkedList<>();
		int[][] visit = new int[n][m];
		
		q.offer(new Point(0, 0));
		visit[0][0] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int boost = arr[current.row][current.col];
				int nextRow = current.row, nextCol = current.col;
				
				while(boost-- > 0) {
					nextRow += DIRECTION[ROW];
					nextCol += DIRECTION[COL];
					
					if(nextRow < 0) {
						nextRow = 0;
						break;
					}
					
					if(nextCol < 0) {
						nextCol = 0;
						break;
					}
					
					if(nextRow >= n) {
						nextRow = n - 1;
						break;
					}
					
					if(nextCol >= m) {
						nextCol = m - 1;
						break;
					}
				}
				
				if(visit[nextRow][nextCol] == 0) {
					visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
					
					if(nextRow == n - 1 && nextCol == m - 1) min = Math.min(min, visit[nextRow][nextCol]);
					q.offer(new Point(nextRow, nextCol));
				}
				else {
					if(visit[nextRow][nextCol] > visit[current.row][current.col] + 1) {
						visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
						
						if(nextRow == n - 1 && nextCol == m - 1) min = Math.min(min, visit[nextRow][nextCol]);
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		return min - 1;
	}
}
