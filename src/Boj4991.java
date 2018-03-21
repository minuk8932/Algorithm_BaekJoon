import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj4991 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final char START = 'o';
	private static final char DIRTY = '*';
	private static final char BLOCK = 'x';
	private static final int DIRTY_YET = -1;
	private static int isClean = 0;

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	private static final int MAX = 30;
	private static char[][] map = null;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			if (h == 0 && w == 0) {
				break;
			}

			int dirtCnt = 0;
			int sRow = 0, sCol = 0;
			int dRow = 0, dCol = 0;

			map = new char[MAX][MAX];
			boolean[][] dirtChk = new boolean[MAX][MAX]; // 아직 안치운 먼지인가

			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);

					if (map[i][j] == START) {
						sRow = i;
						sCol = j;
					}

					if (map[i][j] == DIRTY) {
						dirtChk[i][j] = true;
						dRow = i;
						dCol = j;

						dirtCnt++;
					}
				}
			}

			int[] fromTo = new int[dirtCnt + 1];
			int[] startEnd = new int[dirtCnt + 1];

			int row = sRow, col = sCol;

			Arrays.fill(fromTo, Integer.MAX_VALUE);
			Arrays.fill(startEnd, Integer.MAX_VALUE);

			PointToPointBFS(h, w, dRow, dCol, dirtCnt, fromTo, dirtChk);

			int[][] isVisited = new int[MAX][MAX];
			int idx = 1;

			Queue<Point> q = new LinkedList<>();
			q.offer(new Point(row, col));
			isVisited[row][col] = 1;

			while (!q.isEmpty()) {
				Point current = q.poll();

				for (final int[] DIRECTION : DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];

					if (nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
						if (isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] != BLOCK) {
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;

							if (map[nextRow][nextCol] == DIRTY) {
								startEnd[idx] = isVisited[nextRow][nextCol] - 1;
								idx++;
							}

							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}

			int[][] dp = new int[dirtCnt][dirtCnt];

			Arrays.sort(fromTo);
			Arrays.sort(startEnd);

			for (int i = 0; i < dirtCnt; i++) {
				dp[0][i] = startEnd[i];
			}
			
			boolean[] used = new boolean[dirtCnt];

			for (int i = 1; i < dirtCnt; i++) {
				for(int j = 0; j < dirtCnt; j++){
					//dp
				}
			}
			
//			for(int i = 0; i < dirtCnt; i++){
//				for(int j = 0; j < dirtCnt; j++){
//					System.out.print(dp[i][j] + " ");
//				}
//				System.out.println();
//			} 
			
			int res = Integer.MAX_VALUE;
			
			for (int i = 0; i < dirtCnt; i++) {
				if(dp[i][dirtCnt - 1] != 0)
					res = Math.min(res, dp[i][dirtCnt - 1]);
			}

			sb.append(res == Integer.MAX_VALUE ? DIRTY_YET : res).append(NEW_LINE);
		}
		br.close();

		System.out.println(sb.toString());
	}

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	private static void PointToPointBFS(int h, int w, int dRow, int dCol, int dirtCnt, int[] fromTo,
			boolean[][] dirtChk) {
		int start = dRow, end = dCol;

		for (int dustRow = 1; dustRow < dirtCnt + 1; dustRow++) {
			boolean[][] tmp = dirtChk;
			int[][] isVisited = new int[MAX][MAX];

			Queue<Point> q = new LinkedList<>();

			q.offer(new Point(dRow, dCol));
			isVisited[dRow][dCol] = 1;
			tmp[dRow][dCol] = false;

			LOOP: while (!q.isEmpty()) {
				Point current = q.poll();

				for (final int[] DIRECTION : DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];

					if (nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
						if (isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] != BLOCK) {
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;

							if (map[nextRow][nextCol] == DIRTY && tmp[nextRow][nextCol]) {
								tmp[nextRow][nextCol] = false;

								fromTo[dustRow] = isVisited[nextRow][nextCol] - 1;

								dRow = nextRow;
								dCol = nextCol;

								if (dustRow == dirtCnt - 1) {
									tmp[start][end] = true;
								}

								break LOOP;
							}

							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
	}
}
