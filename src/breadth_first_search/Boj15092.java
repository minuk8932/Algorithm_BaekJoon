package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 15092번: Sheba's Amoeba
 *
 *	@see https://www.acmicpc.net/problem/15092/
 *
 */
public class Boj15092 {
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, 1 },
			{ 1, -1 }, { -1, -1 } };
	private static final int ROW = 0, COL = 1;
	private static final char AMOEBA = '#';

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

		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		System.out.println(bfs(R, C, map));			// 아메바 마릿수 출력
	}

	private static int bfs(int r, int c, char[][] map) {
		boolean[][] isVisited = new boolean[r][c];
		int result = 0;

		for (int row = 0; row < r; row++) {
			for (int col = 0; col < c; col++) {
				if (isVisited[row][col] || map[row][col] != AMOEBA) continue;
				isVisited[row][col] = true;

				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));

				result++;

				while (!q.isEmpty()) {
					Point current = q.poll();

					for (final int[] DIRECTION : DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];

						if (nextRow < 0 || nextCol < 0 || nextRow >= r || nextCol >= c) continue;
						if (isVisited[nextRow][nextCol] || map[nextRow][nextCol] != AMOEBA) continue;
						isVisited[nextRow][nextCol] = true;

						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}

		return result;
	}
}