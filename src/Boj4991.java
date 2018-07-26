import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj4991 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final char START = 'o';
	private static final char DIRT = '*';
	private static final char BLOCK = 'x';
	private static final char CLEAN = '.';

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int MAX = 30;
	private static final int INF = 10_001;

	private static Point start = null;
	private static Point[] dust = new Point[10];

	private static char[][] map = null;
	private static int res = 0;

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

			int dustCnt = 0;
			map = new char[MAX][MAX];

			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);

					if (map[i][j] == START) {
						start = new Point(i, j);
					}

					if (map[i][j] == DIRT) {
						dust[dustCnt] = new Point(i, j);
						dustCnt++;
					}
				}
			}

			res = 0;
			
			while(dustCnt-- > 0) {
				bfs(map, h, w);
				
				if(res == INF) break;
			}
			
			sb.append(res >= INF ? -1 : res).append(NEW_LINE);
		}

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

	private static void bfs(char[][] map, int N, int M) {
		int min = INF, tmpRow = 0, tmpCol = 0;

		for (Point d : dust) {
			if(d == null) continue;
			if(d.row == -1 && d.col == -1) continue;
			
			int[][] isVisited = new int[MAX][MAX];

			isVisited[d.row][d.col] = 1;
			Queue<Point> q = new LinkedList<>();

			q.offer(new Point(d.row, d.col));

			while (!q.isEmpty()) {
				Point current = q.poll();

				for (final int[] DIRECTION : DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];

					if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
						if (isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] != BLOCK) {
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;

							if (map[nextRow][nextCol] == START) {
								if(min > isVisited[nextRow][nextCol]) {
									min = isVisited[nextRow][nextCol] - 1;
									tmpRow = d.row;
									tmpCol = d.col;
								}
							}

							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
		
		if(min == INF) {
			res = INF;
			return;
		}
		
		res += min;
		map[start.row][start.col] = CLEAN;
		start = new Point(tmpRow, tmpCol);
		map[tmpRow][tmpCol] = START;
		
		
		for(Point d: dust) {
			if(d != null) {
				if(tmpRow == d.row && tmpCol == d.col) d.row = d.col = -1;
			}
		}
	}
}
