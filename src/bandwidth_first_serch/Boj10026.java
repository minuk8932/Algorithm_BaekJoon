package bandwidth_first_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj10026 {
	private static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char[][] redG = new char[N][N];
		char[][] norm = new char[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			norm[i] = line.toCharArray();

			for (int j = 0; j < N; j++) {
				if (norm[i][j] == 'R') {
					redG[i][j] = 'G';
				} else {
					redG[i][j] = norm[i][j];
				}
			}
		}

		// normal
		int[][] isVisited = new int[N][N];
		int normalCnt = 0;

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (isVisited[row][col] == 0) {
					isVisited[row][col] = 1;

					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(row, col));

					while (!queue.isEmpty()) {
						Point current = queue.poll();

						for (final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];

							if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
								if (isVisited[nextRow][nextCol] == 0
										&& norm[nextRow][nextCol] == norm[current.row][current.col]) {
									isVisited[nextRow][nextCol] = 1;
									
									normalCnt++;
									
									queue.offer(new Point(nextRow, nextCol));									
								}
							}
						}
					}
				}
			}
		}

		// abnormal

		isVisited = new int[N][N];
		
		int abnormalCnt = 0;

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (isVisited[row][col] == 0) {
					isVisited[row][col] = 1;

					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(row, col));

					while (!queue.isEmpty()) {
						Point current = queue.poll();

						for (final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];

							if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
								if (isVisited[nextRow][nextCol] == 0
										&& redG[nextRow][nextCol] == redG[current.row][current.col]) {
									
									isVisited[nextRow][nextCol] = 1;

									queue.offer(new Point(nextRow, nextCol));
									abnormalCnt++;
								}
							}
						}
					}
				}
			}
		}
		
		int size = N * N;
		
		System.out.println((size - normalCnt) + " " + (size - abnormalCnt));
	}

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

}
