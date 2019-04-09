package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16930번: 달리기
 *
 *	@see https://www.acmicpc.net/problem/16930/
 *
 */
public class Boj16930 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_000_000;
	
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
		int K = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) == '#' ? false : true;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		Point start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		Point end = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		
		System.out.println(bfs(N, M, K, map, start, end));
	}
	
	private static int bfs(int n, int m, int k, boolean[][] arr, Point start, Point end) {
		int[][] visit = new int[n][m];
		for(int i = 0; i < n; i++) {
			Arrays.fill(visit[i], INF);
		}
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start.row, start.col));
		visit[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
				
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int move = 0;
					
				// 움직인 횟수가 같은 배열은 방문하지 않음
				while(move < k && range(n, m, nextRow, nextCol) && arr[nextRow][nextCol] && visit[nextRow][nextCol] > visit[current.row][current.col]) {
					if(visit[nextRow][nextCol] == INF) {			// 미방문 정점인 경우
						visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
						q.offer(new Point(nextRow, nextCol));
					}
					
					nextRow += DIRECTION[ROW];	// 달릴 수 있는 만큼 달려준다.
					nextCol += DIRECTION[COL];
					move++;
				}
			}
		}
		
		return visit[end.row][end.col] == INF ? -1 : visit[end.row][end.col] - 1;
	}
	
	private static boolean range(int n, int m, int row, int col) {
		return row >= 0 && row < n && col >= 0 && col < m ? true: false;
	}
}
