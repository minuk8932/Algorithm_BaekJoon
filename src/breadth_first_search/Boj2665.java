package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 2665번: 미로 만들기
 *
 *	@see https://www.acmicpc.net/problem/2665/
 *
 */
public class Boj2665 {
	private static final boolean BLOCK = true;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static class Point{
		int row;
		int col;
		int broken;
		
		public Point(int row, int col, int broken) {
			this.row = row;
			this.col = col;
			this.broken = broken;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		boolean[][] map = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < n; j++) {
				if(line.charAt(j) == '0') map[i][j] = BLOCK;		// 벽으로 막힌 경우
			}
		}
		
		System.out.println(bfs(n, map));
	}
	
	private static int bfs(int n, boolean[][] arr) {
		int count = n * 2 + 1;
		boolean[][][] isVisited = new boolean[n][n][count];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, 0));
		isVisited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int nextBroken = current.broken;
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
				if(arr[nextRow][nextCol] == BLOCK) nextBroken += 1;		// 벽을 뚫는 경우
				
				if(nextBroken >= count) continue;
				if(isVisited[nextRow][nextCol][nextBroken]) continue;
				isVisited[nextRow][nextCol][nextBroken] = true;
				
				q.offer(new Point(nextRow, nextCol, nextBroken));
			}
		}
		
		int result = 0;
		
		for(int i = 0; i < count; i++) {		// 도착지점으로 이동이 가능한 경로 중 가장 작은 비용
			if(isVisited[n - 1][n - 1][i]) {
				result = i;
				break;
			}
		}
		
		return result;
	}
}
