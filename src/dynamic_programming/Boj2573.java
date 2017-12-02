package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2573 {
	private static final String SPACE = " ";

	private static final int CONCURRENT = 0;
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int total = N * M;

		int[][] ice = new int[300][300];
		int max = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);
			for (int j = 0; j < M; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());

				if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
					ice[i][j] = 0;
				}
			}
		}

		int year = 0;
		int isDivide = 0, tmp = 0;

		LOOP: while (isDivide < 2) {
			boolean isVisited[][] = new boolean[N][M];
			
			int cnt = 0;
			
			isDivide = 0;
			year++;			
			
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if(ice[row][col] == 0){
						cnt++;
					}
					
					if (ice[row][col] != 0 && !isVisited[row][col]) {
						isVisited[row][col] = true;
						
						isDivide++;
						
						Queue<Point> q = new LinkedList<>();
						q.offer(new Point(row, col));
					
						if (ice[row + 1][col] == 0) {
							if (ice[row][col] != 0)
								ice[row][col]--;
						}
						if (ice[row][col - 1] == 0) {
							if (ice[row][col] != 0)
								ice[row][col]--;
						}
						if (ice[row - 1][col] == 0) {
							if (ice[row][col] != 0)
								ice[row][col]--;
						}
						if (ice[row][col + 1] == 0) {
							if (ice[row][col] != 0)
								ice[row][col]--;
						}
						

						while (!q.isEmpty()) {
							Point current = q.poll();

							for (final int[] DIRECTION : DIRECTIONS) {
								int nextRow = current.row + DIRECTION[ROW];
								int nextCol = current.col + DIRECTION[COL];

								if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
									if (ice[nextRow][nextCol] != 0 && !isVisited[nextRow][nextCol]) {
										isVisited[nextRow][nextCol] = true;

										q.offer(new Point(nextRow, nextCol));
										
										if (ice[nextRow + 1][nextCol] == 0 && !isVisited[nextRow + 1][nextCol]) {
											if (ice[nextRow][nextCol] != 0)
												ice[nextRow][nextCol]--;
										}
										if (ice[nextRow][nextCol - 1] == 0 && !isVisited[nextRow][nextCol - 1]) {
											if (ice[nextRow][nextCol] != 0)
												ice[nextRow][nextCol]--;
										}
										if (ice[nextRow - 1][nextCol] == 0 && !isVisited[nextRow - 1][nextCol]) {
											if (ice[nextRow][nextCol] != 0)
												ice[nextRow][nextCol]--;
										}
										if (ice[nextRow][nextCol + 1] == 0 && !isVisited[nextRow][nextCol + 1]) {
											if (ice[nextRow][nextCol] != 0)
												ice[nextRow][nextCol]--;
										}
									}
								}
							}
						}
					}
				}
			}
			if(cnt == total){
				tmp = cnt;
				break LOOP;
			}
		}

		if(tmp == total){
			System.out.println(CONCURRENT);
		}
		else{
			System.out.println(year - 1);
		}
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
