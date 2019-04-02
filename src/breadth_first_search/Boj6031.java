package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6031번: Feeding time
 *
 *	@see https://www.acmicpc.net/problem/6031/
 *
 */
public class Boj6031 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
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
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[H][W];
		for(int i = 0; i < H; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < W; j++) {
				map[i][j] = line.charAt(j) == '.' ? true: false;
			}
		}
		
		System.out.println(bfs(H, W, map));
	}
	
	private static int bfs(int h, int w, boolean[][] arr) {
		int max = 0;
		
		for(int row = 0; row < h; row++) {
			for(int col = 0; col < w; col++) {
				if(!arr[row][col]) continue;
				arr[row][col] = false;
				
				int count = 1;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) continue;
						if(!arr[nextRow][nextCol]) continue;
						arr[nextRow][nextCol] = false;
						
						count++;
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
				
				if(count > max) max = count;
			}
		}
		
		return max;
	}
}
