package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5437 : 불
 *
 *	@see https://www.acmicpc.net/problem/5427/
 *
 */
public class Boj5427 {
	private static final String IM = "IMPOSSIBLE";
	private static final String NEW_LINE = "\n";
	private static final char BLOCK = '#';
	private static final char FIRE = '*';
	private static final char USER = '@';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = 1_000_000_000;
	
	private static Queue<Point> fireQ = new LinkedList<>();
	private static Point start = new Point(-1, -1);
	private static int[][] fireSpread;
	private static int[][] visit;
	
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
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[h][w];
			fireSpread = new int[h][w];
			visit = new int[h][w];
			
			for(int i = 0; i < h; i++) {
				String line = br.readLine();
				
				for(int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);
					fireSpread[i][j] = INF;
					
					if(map[i][j] == FIRE) {
						fireQ.offer(new Point(i, j));
						fireSpread[i][j] = 1;
					}
					if(map[i][j] == USER) start = new Point(i, j);
				}
			}
			
			fireBFS(h, w, map);
			int result = bfs(h, w, map);
			
			sb.append(result == -1 ? IM: result).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void fireBFS(int n, int m, char[][] arr) {
		while(!fireQ.isEmpty()) {
			Point current = fireQ.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
				if(fireSpread[nextRow][nextCol] != INF || arr[nextRow][nextCol] == BLOCK) continue;
				fireSpread[nextRow][nextCol] = fireSpread[current.row][current.col] + 1;					// fire spread time
				
				fireQ.offer(new Point(nextRow, nextCol));
			}
		}
	}
	
	private static int bfs(int n, int m, char[][] arr) {
		if(start.row == 0 || start.col == 0 || start.row == n - 1 || start.col == m - 1) return 1;			// start is end
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start.row, start.col));
		
		visit[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
				if(visit[nextRow][nextCol] != 0 || arr[nextRow][nextCol] == BLOCK || 
						fireSpread[nextRow][nextCol] <= visit[current.row][current.col] + 1) continue;		// fire already
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;

				if(nextRow == 0 || nextCol == 0 || nextRow == n - 1 || nextCol == m - 1) return visit[nextRow][nextCol];		// end
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return -1;
	}
}