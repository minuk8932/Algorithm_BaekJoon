import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17489 {
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0, COL = 1;

	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	private static final String ERROR = "-1";

	private static class Point {
		int row;
		int col;
		int idx;

		public Point(int row, int col, int idx) {
			this.row = row;
			this.col = col;
			this.idx = idx;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		char[] S = br.readLine().toCharArray();

		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		System.out.println(search(N, M, L, S, map));
	}

	private static String search(int n, int m, int len, char[] key, char[][] arr) {
		int row = -1, col = -1;
		int max = 0;

		int[][] visit = new int[n][m];
		visit[0][0] = 1;
		
		if((len == 1 && (arr[0][1] == key[0] || arr[1][0] == key[0])) || len == 2) return ERROR;

		if (key[len - 1] == arr[0][0]) {
			max = visit[0][0];
			row = 0;
			col = 0;
		}

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, 0));

		while (!q.isEmpty()) {
			Point current = q.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int nextIdx = (current.idx + 1) % len;

				if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
				if (arr[nextRow][nextCol] != key[nextIdx]) continue;
				if (visit[nextRow][nextCol] != 0 && key[nextIdx] == key[0]) return ERROR;
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;

				if (arr[nextRow][nextCol] == key[len - 1]) {
					if (visit[nextRow][nextCol] > max) {
						max = visit[nextRow][nextCol];
						row = nextRow;
						col = nextCol;
					}
				}

				q.offer(new Point(nextRow, nextCol, nextIdx));
			}
		}

		if (row == -1 && col == -1) return ERROR;

		StringBuilder sb = new StringBuilder();
		return sb.append(max / len).append(NEW_LINE).append(row + 1).append(SPACE).append(col + 1).toString();
	}
}
