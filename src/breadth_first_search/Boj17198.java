package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 17198번: Bucket Brigade
 *
 *	@see https://www.acmicpc.net/problem/17198/
 *
 */
public class Boj17198 {
	private static final char ROCK = 'R';
	private static final char BARN = 'B';
	private static final char LAKE = 'L';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static Point start = new Point(0, 0);
	private static Point end = new Point(0, 0);
	
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
		char[][] map = new char[10][10];
		
		for(int i = 0; i < 10; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < 10; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == LAKE) end = new Point(i, j);
				if(map[i][j] == BARN) start = new Point(i, j);
			}
		}
		
		System.out.println(getCows(map));
	}
	
	private static int getCows(char[][] arr) {
		int[][] visit = new int[10][10];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		
		visit[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= 10 || nextCol < 0 || nextCol >= 10) continue;
				if(visit[nextRow][nextCol] != 0 || arr[nextRow][nextCol] == ROCK) continue;
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
				
				if(arr[nextRow][nextCol] == LAKE) break;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return visit[end.row][end.col] - 2;
	}
}
