package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17489번: 보물 찾기
 *
 *	@see https://www.acmicpc.net/problem/17489/
 *
 */
public class Boj17489 {
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0, COL = 1;
	private static final int CIPHER = 1_000;

	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	private static final String ERROR = "-1";

	private static class Point {
		int row;
		int col;
		int idx;
		HashSet<Integer> passed;

		public Point(int row, int col, int idx, HashSet<Integer> passed) {
			this.row = row;
			this.col = col;
			this.idx = idx;
			this.passed = passed;
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
		if(len == 1 && arr[0][0] != key[0]) return ERROR;						// no treasure
		StringBuilder sb = new StringBuilder();
				
		int row = -1, col = -1;
		int max = 0;

		int[][] visit = new int[n][m];
		visit[0][0] = 1;
		row = 0;
		col = 0;

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, 0, new HashSet(1 * CIPHER + 1)));				// saved path

		while (!q.isEmpty()) {
			Point current = q.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int nextIdx = (current.idx + 1) % len;
				int nextPass = (nextRow + 1) * CIPHER + (nextCol + 1);

				if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
				if (arr[nextRow][nextCol] != key[nextIdx]) continue;
				
				if(current.passed.contains((nextPass))) return ERROR;
				
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;	// distance
				
				if (arr[nextRow][nextCol] == key[len - 1]) {
					if (visit[nextRow][nextCol] > max) {
						max = visit[nextRow][nextCol];							// max distance when find treasure
						row = nextRow;
						col = nextCol;
					}
				}
				
				Point next = new Point(0, 0, 0, new HashSet());					// save next
				next.row = nextRow;
				next.col = nextCol;
				next.idx = nextIdx;
				next.passed.addAll(current.passed);
				next.passed.add(nextPass);

				q.offer(next);
			}
		}

		if (row == -1 || col == -1 || visit[row][col] / len == 0) return ERROR;
		return sb.append(visit[row][col] / len).append(NEW_LINE).append(row + 1).append(SPACE).append(col + 1).toString();
	}
}
