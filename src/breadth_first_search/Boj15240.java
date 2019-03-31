package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 15240번: Paint Bucket
 *
 *	@see https://www.acmicpc.net/problem/15240/
 *
 */
public class Boj15240 {
	private static final int[][] DIRECTIONS = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
	private static final int ROW = 0, COL = 1;

	private static final String NEW_LINE = "\n";

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
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[][] map = new int[R][C];
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		st = new StringTokenizer(br.readLine());
		Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		int color = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(R, C, map, start, color));		// 알맞은 팔레트에 알맞은 색을 채워준다.
	}

	private static StringBuilder bfs(int r, int c, int[][] arr, Point s, int color) {
		StringBuilder sb = new StringBuilder();
		boolean[][] isVisited = new boolean[r][c];

		int pallet = arr[s.row][s.col];
		
		isVisited[s.row][s.col] = true;
		arr[s.row][s.col] = color;

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(s.row, s.col));

		while (!q.isEmpty()) {
			Point current = q.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (nextRow < 0 || nextCol < 0 || nextRow >= r || nextCol >= c) continue;
				if (isVisited[nextRow][nextCol] || arr[nextRow][nextCol] != pallet) continue;
				isVisited[nextRow][nextCol] = true;
				arr[nextRow][nextCol] = color;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				sb.append(arr[i][j]);
			}
			sb.append(NEW_LINE);
		}

		return sb;
	}
}