package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16933번: 벽 부수고 이동하기 3
 *
 * @see https://www.acmicpc.net/problem/16933/
 *
 */
public class Boj16933 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static class Point{
		int row;
		int col;
		boolean day;
		int broke;
		
		public Point(int row, int col, boolean day, int broke) {
			this.row = row;
			this.col = col;
			this.day = day;
			this.broke = broke;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		System.out.println(bfs(N, M, K, map));
	}
	
	private static int bfs(int n, int m, int k, int[][] map) {
		if(n == 1 && m == 1) return 1;

		int[][][][] visit = new int[2][n][m][k + 1];
		visit[0][0][0][0] = 1;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, true, 0));
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				boolean nextDay = !current.day;
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) continue;
				if(map[nextRow][nextCol] == 1){				// next is block
					if(current.broke + 1 > k) continue;		// breaking any more

					if(!current.day){
						if(visit[0][current.row][current.col][current.broke] != 0) continue;		// stay for day change
						visit[0][current.row][current.col][current.broke] =
								visit[1][current.row][current.col][current.broke] + 1;

						q.offer(new Point(current.row, current.col, nextDay, current.broke));
					}
					else {
						if(visit[1][nextRow][nextCol][current.broke + 1] != 0) continue;			// break blocks
						visit[1][nextRow][nextCol][current.broke + 1] =
								visit[0][current.row][current.col][current.broke] + 1;

						if(nextRow == n - 1 && nextCol == m - 1) return visit[0][nextRow][nextCol][current.broke + 1];
						q.offer(new Point(nextRow, nextCol, nextDay, current.broke + 1));
					}
				}
				else {
					if(visit[nextDay ? 0: 1][nextRow][nextCol][current.broke] != 0) continue;		// just move
					visit[nextDay ? 0: 1][nextRow][nextCol][current.broke] =
							visit[current.day ? 0: 1][current.row][current.col][current.broke] + 1;

					if(nextRow == n - 1 && nextCol == m - 1) return visit[nextDay ? 0: 1][nextRow][nextCol][current.broke];
					q.offer(new Point(nextRow, nextCol, nextDay, current.broke));
				}
			}
		}
		
		return -1;			// can not reach
	}
}
