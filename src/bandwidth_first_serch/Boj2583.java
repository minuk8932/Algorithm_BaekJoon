package bandwidth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2583 {
	private static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] area = new int[N][N];

		int minH = 2;
		int maxH = 100;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			for (int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());

				minH = Math.min(minH, area[i][j]);
				maxH = Math.max(maxH, area[i][j]);
			}
		}
		br.close();

		int res = 0;
		
		for (int waterH = minH - 1; waterH < maxH + 1; waterH++) {
			int[][] isVisited = new int[N][N];
			
			int cnt = 0;
			
			Queue<Point> queue = new LinkedList<>();
			
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (area[row][col] > waterH && isVisited[row][col] == 0) {
						//isVisited[row][col] = 1;
						cnt++;
						
						queue.offer(new Point(row, col));

						while (!queue.isEmpty()) {
							Point current = queue.poll();

							for (final int[] DIRECTION : DIRECTIONS) {
								int nextRow = DIRECTION[ROW] + current.row;
								int nextCol = DIRECTION[COL] + current.col;

								if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
									if (area[nextRow][nextCol] > waterH && isVisited[nextRow][nextCol] == 0) {
										isVisited[nextRow][nextCol] = 1;

										queue.offer(new Point(nextRow, nextCol));
									}
								}
							}
						}
					}
				}
			}
			res = Math.max(cnt, res);
		}

		System.out.println(res);

	}

	private static class Point {
		public int row;
		public int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

}
