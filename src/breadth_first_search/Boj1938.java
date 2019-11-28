package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author exponential-e
 *	백준 1938번: 통나무 옮기기
 *
 *	@see https://www.acmicpc.net/problem/1938/
 *
 */
public class Boj1938 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static Log start = new Log(new Point(-1, -1), new Point(-1, -1), new Point(-1, -1), false);
	private static Log end = new Log(new Point(-1, -1), new Point(-1, -1), new Point(-1, -1), false);
	
	private static class Log{
		Point b1;
		Point b2;
		Point b3;
		boolean pos;
		
		public Log(Point b1, Point b2, Point b3, boolean pos) {
			this.b1 = b1;
			this.b2 = b2;
			this.b3 = b3;
			this.pos = pos;
		}
	}
	
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
		int N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		int[] count = new int[2];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == 'B') {									// start
					count[0]++;
					
					if(count[0] == 1) start.b1 = new Point(i, j);
					if(count[0] == 2) start.b2 = new Point(i, j);
					if(count[0] == 3) {
						start.b3 = new Point(i, j);
						
						int x = Math.abs(start.b1.row - start.b3.row);
						if(x == 0) start.pos = true;					// lie
					}
				}
				
				if(map[i][j] == 'E') {									// end
					count[1]++;
					
					if(count[1] == 1) end.b1 = new Point(i, j);
					if(count[1] == 2) end.b2 = new Point(i, j);
					if(count[1] == 3) {
						end.b3 = new Point(i, j);
						
						int x = Math.abs(end.b1.row - end.b3.row);
						if(x == 0) end.pos = true;
					}
				}
			}
		}
		
		System.out.println(bfs(N, map));
	}
	
	private static int bfs(int n, char[][] arr) {
		int min = Integer.MAX_VALUE;
		int[][][] visit = new int[n][n][2];
		
		Queue<Log> q = new LinkedList<>();
		q.offer(start);
		
		visit[start.b2.row][start.b2.col][start.pos ? 1: 0] = 1;
		while(!q.isEmpty()) {
			Log current = q.poll();
			
			boolean flag = true;
			for(final int[] DIRECTION: DIRECTIONS) {				// can rotate ?
				int nextRow = current.b2.row + DIRECTION[ROW];
				int nextCol = current.b2.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || arr[nextRow][nextCol] == '1') {
					flag = false;
					break;
				}
			}
			
			if(flag) {												// rotating
				int[] adder = {current.pos ? -1: 1, current.pos ? 1: -1};
				
				int nextRow1 = current.b1.row + adder[0];
				int nextCol1 = current.b1.col + adder[1];
				int nextRow3 = current.b3.row + adder[1];
				int nextCol3 = current.b3.col + adder[0];
				boolean nextPos = !current.pos;
					
				int idx = nextPos ? 1: 0;
				int prev = current.pos ? 1: 0;

				if(visit[current.b2.row][current.b2.col][idx] == 0) {
					visit[current.b2.row][current.b2.col][idx] = visit[current.b2.row][current.b2.col][prev] + 1;
					
					if(nextRow1 == end.b1.row && nextCol1 == end.b1.col && nextRow3 == end.b3.row && nextCol3 == end.b3.col &&
							current.b2.row == end.b2.row && current.b2.col == end.b2.col && nextPos == end.pos)
						min = Math.min(min, visit[current.b2.row][current.b2.col][idx]);
						
					q.offer(new Log(new Point(nextRow1, nextCol1), current.b2, new Point(nextRow3, nextCol3), nextPos));
				}
			}
			
			for(int i = 0; i < 4; i++) {								// move
				int topRow = current.b1.row + DIRECTIONS[i][ROW];
				int topCol = current.b1.col + DIRECTIONS[i][COL];
				int botRow = current.b3.row + DIRECTIONS[i][ROW];
				int botCol = current.b3.col + DIRECTIONS[i][COL];
				int nextRow = current.b2.row + DIRECTIONS[i][ROW];
				int nextCol = current.b2.col + DIRECTIONS[i][COL];
				
				int idx = current.pos ? 1: 0;
				
				if(topRow < 0 || topRow >= n || topCol < 0 || topCol >= n || botRow < 0 || botRow >= n || botCol < 0 || botCol >= n 
						|| nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
				if(arr[topRow][topCol] == '1' || arr[botRow][botCol] == '1' || arr[nextRow][nextCol] == '1' || visit[nextRow][nextCol][idx] != 0) continue;
				visit[nextRow][nextCol][idx] = visit[current.b2.row][current.b2.col][idx] + 1;
				
				if(topRow == end.b1.row && topCol == end.b1.col && botRow == end.b3.row && botCol == end.b3.col
						&& nextRow == end.b2.row && nextCol == end.b2.col && current.pos == end.pos)
					min = Math.min(min, visit[nextRow][nextCol][idx]);
				
				q.offer(new Log(new Point(topRow, topCol), new Point(nextRow, nextCol), new Point(botRow, botCol), current.pos));
			}
		}
		
		return min == Integer.MAX_VALUE ? 0: min - 1;
	}
}
