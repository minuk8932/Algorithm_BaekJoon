import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4991 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final char START = 'o';
	private static final char DIRT = '*';
	private static final char BLOCK = 'x';

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_001;

	private static int res;
	private static boolean[][] isVisited;

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
			char[][] map = new char[h][w];
			Point start = new Point(0, 0);
			isVisited = new boolean[h][w];

			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);

					if (map[i][j] == START) start = new Point(i, j);
					if (map[i][j] == DIRT) dustCnt++;
				}
			}
					
			res = INF;
			
			bfs(map, h, w, start, dustCnt, 0);			
			sb.append(res == INF ? -1 : res).append(NEW_LINE);
		}

		System.out.println(sb);
	}

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	private static void bfs(char[][] arr, int h, int w, Point current, int count, int move) {
		
	}
}
