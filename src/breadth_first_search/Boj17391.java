package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17391번: 무한 부스터
 *
 *	@see https://www.acmicpc.net/problem/17391/
 *
 */
public class Boj17391 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}};
	private static final int ROW = 0, COL = 1;
	
	private static class Point{
		int row;
		int col;
		int boost;
		
		public Point(int row, int col, int boost) {
			this.row = row;
			this.col = col;
			this.boost = boost;
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
		
		q.offer(new Point(0, 0, arr[0][0]));
		visit[0][0] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int loop = current.boost;
				int nextRow = current.row, nextCol = current.col;
				
				while(loop-- > 0) {					// 부스팅 가능 칸 수
					nextRow += DIRECTION[ROW];
					nextCol += DIRECTION[COL];
					
					if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) break;		
					
					if(visit[nextRow][nextCol] == 0 || visit[nextRow][nextCol] > visit[current.row][current.col] + 1) {		// 두 경우에만 방문
						visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
							
						if(nextRow == n - 1 && nextCol == m - 1) min = Math.min(min, visit[nextRow][nextCol]);
						q.offer(new Point(nextRow, nextCol, arr[nextRow][nextCol]));
					}
				}
			}
		}
		
		return min - 1;
	}
}
