import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1486 {
	private static final String SPACE = " ";

	private static int N = 0;
	private static int M = 0;
	private static int T = 0;
	private static int D = 0;

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int LOW = 1;
	
	private static Point top = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		T = Integer.parseInt(st.nextToken()); // 높이차이 한계, Math.abs([i - 1] -
												// [i]) <= T
		D = Integer.parseInt(st.nextToken()); // 호텔에서 출발해 호텔까지 오는데 걸리는 시간

		int[][] map = new int[N][M];
		int[][] timer = new int[2][52];
		int leng = 0;

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

				leng = Math.max(leng, map[i][j]);
			}
		}

		dijkstraMin(map, timer);
		dijkstraMax(map, timer);

		int res = 0;

		for (int i = 0; i < leng + 1; i++) {
			int tmp = timer[0][timer[0].length - i - 1] + timer[1][i];

			 System.out.println(timer[0][timer[0].length - i - 1] + " " + timer[1][i] + " " + i);

			if (tmp <= D + 1 && timer[0][i] != 0 && timer[1][i] != 0) {
				res = Math.max(i, res);
			}
		}

		System.out.println(res);
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

	private static void dijkstraMax(int[][] map, int[][] timer) {
		int[][] isVisited = new int[N][M];

		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(top.row, top.col, top.cost));
		isVisited[top.row][top.col] = 1;

		while (!pq.isEmpty()) {
			Point current = pq.poll();
			
			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M && isVisited[nextRow][nextCol] == 0) {
					int comp = map[nextRow][nextCol] - map[current.row][current.col];

					if (Math.abs(comp) > T) {
						continue;
					}

					if (comp <= 0) {
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + LOW;
					}
					else {
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + (int) Math.pow(comp, 2);
					}

					timer[0][map[nextRow][nextCol]] = isVisited[nextRow][nextCol];

					pq.offer(new Point(nextRow, nextCol, map[nextRow][nextCol]));
				}
			}
		}
	}

	private static Point dijkstraMin(int[][] map, int[][] timer) {
		int lastRow = 0, lastCol = 0;
		int max = 0;

		int[][] isVisited = new int[N][M];

		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, map[0][0]));
		isVisited[0][0] = 1;

		while (!pq.isEmpty()) {
			Point current = pq.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M && isVisited[nextRow][nextCol] == 0) {
					int comp = map[nextRow][nextCol] - map[current.row][current.col];

					if (Math.abs(comp) > T) {
						continue;
					}

					if (comp <= 0) {
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + LOW;
					} 
					else {
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + (int) Math.pow(comp, 2);
					}

					timer[1][map[nextRow][nextCol]] = isVisited[nextRow][nextCol];
					
					if(map[nextRow][nextCol] > max){
						max = map[nextRow][nextCol];
						
						lastRow = nextRow;
						lastCol = nextCol;
					}

					pq.offer(new Point(nextRow, nextCol, map[nextRow][nextCol]));
				}
			}
		}

		return top = new Point(lastRow, lastCol, max);
	}
}
