import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1486 {
	private static final String SPACE = " ";

	private static int N = 0;
	private static int M = 0;
	private static int T = 0;
	private static int D = 0;
	private static int[][] map = null;
	private static int[][] isVisited = null;

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int LEV = 1;
	
	private static Point top = null;
	private static int[][] timeList = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		T = Integer.parseInt(st.nextToken()); // 높이차이 한계, Math.abs([i - 1] -
												// [i]) <= T
		D = Integer.parseInt(st.nextToken()); // 호텔에서 출발해 호텔까지 오는데 걸리는 시간
		
		timeList = new int[2][N * M];		// 0 : go, 1 : back

		map = new int[N][M];
		isVisited = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < M; j++) {
				char tmp = line.charAt(j);

				if (tmp >= 'A' && tmp <= 'Z') {
					map[i][j] = (tmp - 'A');
				}

				else if (tmp >= 'a' && tmp <= 'z') {
					map[i][j] = (tmp - 'a' + 26);
				}
			}
		}
		
		Arrays.fill(timeList[0], Integer.MAX_VALUE);
		
	}

	private static class Point implements Comparable<Point> {
		int row;
		int col;
		int cost;

		public Point(int row, int col, int cost) {
			this.row = row;
			this.col = col;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point p) {
			return this.cost < p.cost ? -1 : 1;
		}
	}
	
	private static void dijkstraGo(Point p){
	}
}
