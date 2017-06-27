package bandwidth_first_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2589 {
	private static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		String[] line = new String[N];

		for (int row = 0; row < N; row++) {
			line[row] = br.readLine();
			for (int col = 0; col < M; col++) {
				map[row] = line[row].toCharArray();
			}
		}
		
		int cost = 0;
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (map[row][col] == 'L') {
					
					int[][] isVisited = new int[N][M];
					
					Queue<Point> queue = new LinkedList<>();

					queue.offer(new Point(row, col));

					while (!queue.isEmpty()) {
						Point current = queue.poll();

						for (final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
								if(isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] == 'L'){
									isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
																		
									queue.offer(new Point(nextRow, nextCol));
									
									cost = Math.max(cost, isVisited[nextRow][nextCol]);
									
									
								}
							}
						}
					}	
				}
			}
		}
		
		System.out.println(cost);
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
