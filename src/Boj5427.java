import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj5427 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String CANNOT_ESCAPE = "IMPOSSIBLE";

	private static char[][] top = null;
	private static final char WAY = '.';
	private static final char BLOCK = '#';
	private static final char START = '@';
	private static final char FIRE = '*';

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			top = new char[h][w];
			for (int i = 0; i < h; i++) {
				top[i] = br.readLine().toCharArray();
			}

			Queue<Point> q = new LinkedList<>();

			int startH = 0, startW = 0;

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (top[i][j] == FIRE) {
						q.offer(new Point(i, j, FIRE));
					} else if (top[i][j] == START) {
						startH = i;
						startW = j;
						top[i][j] = START;
					}
				}
			}

			int escape = 0;

			if (startH == 0 || startH == h - 1 || startW == 0 || startW == w - 1) {
				escape = 1;
			} 
			else {

				int[][] isVisited = new int[h][w];
				
				q.offer(new Point(startH, startW, START));

				while (!q.isEmpty()) {
					Point current = q.poll();

					for (final int[] DIRECTION : DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];

						if (nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
							if (isVisited[nextRow][nextCol] == 0) {
								if (top[nextRow][nextCol] == WAY) {
									// isVisited[nextRow][nextCol] =
									// isVisited[row][col] + 1;
									escape = isVisited[nextRow][nextCol];

									// TODO : how to check when he cann't
									// escape

								} else {
									sb.append(CANNOT_ESCAPE).append(NEW_LINE);
								}
								// q.offer(new Point(nextRow, nextCol));
							}
						}
					}
				}

				sb.append(escape);
			}
		}
		br.close();

		System.out.println(sb.toString());
	}

	private static class Point {
		int row;
		int col;
		char item;

		public Point(int row, int col, char item) {
			this.row = row;
			this.col = col;
			this.item = item;
		}
	}

	public static void spreadFire(int x, int y) {
		boolean isVisited[][] = new boolean[x][y];

		for (final int[] DIRECTION : DIRECTIONS) {
			if (top[x][y] == FIRE && !isVisited[x][y]) {
				int nextX = DIRECTION[ROW] + x;
				int nextY = DIRECTION[COL] + y;

				if (top[nextX][nextY] == WAY) {
					top[nextX][nextY] = FIRE;
				}
			}
		}
	}
}
