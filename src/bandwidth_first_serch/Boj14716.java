package bandwidth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14716 {
	private static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 1, 1 }, { 0, 1 }, { 0, -1 }, { -1, 0 }, { -1, -1 },
			{ 1, -1 }, { -1, 1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int cnt = 0;

		int[][] card = new int[M][N];
		int[][] isVisited = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);
			for (int j = 0; j < N; j++) {
				card[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int row = 0; row < M; row++) {
			for (int col = 0; col < N; col++) {
				if (card[row][col] == 1 && isVisited[row][col] == 0) {
					isVisited[row][col] = 1;
					
					cnt++;
					
					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(row, col));

					while (!queue.isEmpty()) {
						Point current = queue.poll();

						for (final int[] DIRECTION : DIRECTIONS) {
							int nextRow = DIRECTION[ROW] + current.row;
							int nextCol = DIRECTION[COL] + current.col;

							if (nextRow >= 0 && nextRow < M && nextCol >= 0 && nextCol < N) {
								if (card[nextRow][nextCol] == 1) {
									if (isVisited[nextRow][nextCol] == 0) {
										isVisited[nextRow][nextCol] = 1;
										
										queue.offer(new Point(nextRow, nextCol));
										
									}
								}
							}
						}
					}
				}
			}
		}

		System.out.println(cnt);
	}

	private static class Point {
		int row = 0;
		int col = 0;

		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
