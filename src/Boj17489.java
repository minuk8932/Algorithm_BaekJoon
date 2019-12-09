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
		StringBuilder sb = new StringBuilder();
		
		if(len == 1) {
			int count = 0;
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(arr[i][j] == key[0]) count++;
				}
			}
			
			if(count == 1 && arr[0][0] == key[0]) return sb.append(1).append(NEW_LINE).append(1).append(SPACE).append(1).toString();
			else return ERROR;
		}
			
		int row = -1, col = -1;
		int max = 0;

		int[][][] visit = new int[n][m][26];
		visit[0][0][arr[0][0] - 'A'] = 1;

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
				if (visit[nextRow][nextCol][arr[nextRow][nextCol] - 'A'] != 0 && max > visit[nextRow][nextCol][arr[nextRow][nextCol] - 'A']) return ERROR;
				visit[nextRow][nextCol][arr[nextRow][nextCol] - 'A'] = visit[current.row][current.col][arr[current.row][current.col] - 'A'] + 1;

				if (arr[nextRow][nextCol] == key[len - 1]) {
					if (visit[nextRow][nextCol][arr[nextRow][nextCol] - 'A'] > max) {
						max = visit[nextRow][nextCol][arr[nextRow][nextCol] - 'A'];
						row = nextRow + 1;
						col = nextCol + 1;
					}
				}

				q.offer(new Point(nextRow, nextCol, nextIdx));
			}
		}

		if (row == -1 || col == -1) return ERROR;
		return sb.append(max / len).append(NEW_LINE).append(row).append(SPACE).append(col).toString();
	}
}
