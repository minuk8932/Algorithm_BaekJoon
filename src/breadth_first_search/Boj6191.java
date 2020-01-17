package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 6191번: Cows on skates
 *
 *	@see https://www.acmicpc.net/problem/6191/
 *
 */
public class Boj6191 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		visit = new int[R][C];
		bfs(R, C, map);
		revBfs(R, C, map);
	}
	
	private static void bfs(int r, int c, char[][] map) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		
		visit[0][0] = 1;				// make cost from (0, 0) to (r - 1, c - 1)
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= r || nextCol >= c) continue;
				if(map[nextRow][nextCol] == '*' || visit[nextRow][nextCol] != 0) continue;
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
				
				if(nextRow == r - 1 && nextCol == c - 1) return;
				q.offer(new Point(nextRow, nextCol));
			}
		}
	}
	
	private static void revBfs(int r, int c, char[][] map) {
		int size = visit[r - 1][c - 1];
		LinkedList<Point> path = new LinkedList<>();
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(r - 1, c - 1));
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			if(visit[current.row][current.col] != size) continue;			// find unique shortest path by reversed
			path.add(new Point(current.row, current.col));
			size--;
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= r || nextCol >= c) continue;
				if(map[nextRow][nextCol] == '*') continue;

				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!path.isEmpty()) {
			Point p = path.removeLast();
			sb.append(p.row + 1).append(SPACE).append(p.col + 1).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
