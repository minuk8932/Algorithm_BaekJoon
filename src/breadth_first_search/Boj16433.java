package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 16433번: 주디와 당근 농장
 *
 *	@see https://www.acmicpc.net/problem/16433/
 *
 */
public class Boj16433 {
	private static final int[][] DIRECTIONS = { { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };
	private static final int ROW = 0, COL = 1;

	private static final String NEW_LINE = "\n";
	private static final char CARROT = 'v';
	private static final char EMPTY = '.';

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()) - 1;
		int C = Integer.parseInt(st.nextToken()) - 1;

		System.out.println(bfs(N, R, C));
	}

	private static StringBuilder bfs(int n, int r, int c) {
		StringBuilder sb = new StringBuilder();
		boolean[][] isVisited = new boolean[n][n];

		isVisited[r][c] = true;

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(r, c));

		while (!q.isEmpty()) {
			Point current = q.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n) continue;
				if (isVisited[nextRow][nextCol]) continue;
				isVisited[nextRow][nextCol] = true;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(isVisited[i][j]) sb.append(CARROT);
				else sb.append(EMPTY);
			}
			sb.append(NEW_LINE);
		}

		return sb;
	}
}