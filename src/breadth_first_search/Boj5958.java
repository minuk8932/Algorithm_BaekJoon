package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 5958번: Space Exploration
 *
 *	@see https://www.acmicpc.net/problem/5958/
 *
 */
public class Boj5958 {
	private static boolean[][] isStar;
	
	private static final char STAR = '*';
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static class Point{
		int row, col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		isStar = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				isStar[i][j] = line.charAt(j) == STAR ? true: false;		// 항성인 경우 true
			}
		}
		
		System.out.println(getAsteroids(N));
	}
	
	private static int getAsteroids(int n) {
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!isStar[i][j]) continue;
				isStar[i][j] = false;			// 탐색한 항성은 지워줌
				
				bfs(n, new Point(i, j));
				count++;						// 항성 무리의 갯수
			}
		}
		
		return count;
	}
	
	private static void bfs(int n, Point start) {
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n) continue;
				if(!isStar[nextRow][nextCol]) continue;
				isStar[nextRow][nextCol] = false;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
	}
}
