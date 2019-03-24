package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1303번: 전쟁-전투
 *
 *	@see https://www.acmicpc.net/problem/1303/
 *
 */
public class Boj1303 {
	private static final String SPACE = " ";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final char BLUE = 'B';
	private static final char WHITE = 'W';
	
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
		
		int[][] battleField = new int[M][N];
		for(int i = 0; i < M; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				battleField[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(returnAnswer(N, M, battleField));
	}
	
	private static StringBuilder returnAnswer(int n, int m, int[][] arr) {
		StringBuilder sb = new StringBuilder();
	return sb.append(bfs(n, m, arr, WHITE)).append(SPACE).append(bfs(n, m, arr, BLUE));		// 각 진영 별 전투력 측정
	}
	
	private static int bfs(int n, int m, int[][] arr, char target) {
		boolean[][] isVisited = new boolean[m][n];
		int value = 0;
		
		Queue<Point> q = new LinkedList<>();
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(isVisited[i][j] || arr[i][j] != target) continue;
				
				q.offer(new Point(i, j));
				
				int count = 1;
				isVisited[i][j] = true;
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow < 0 || nextCol < 0 || nextRow >= m || nextCol >= n) continue;
						if(isVisited[nextRow][nextCol] || arr[nextRow][nextCol] != target) continue;
						isVisited[nextRow][nextCol] = true;
						count++;
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
				
				value += (count * count);
			}
		}
		
		return value;
	}
}
