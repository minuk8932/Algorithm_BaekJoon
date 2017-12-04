package breadth_first_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// night moving

public class JavaTest {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";

	private static final int[][] DIRECTIONS = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { -1, 2 },
			{ 1, -2 }, { -1, -2 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			int I = Integer.parseInt(br.readLine());
			boolean[][] chess = new boolean[I][I];

			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int posC1 = Integer.parseInt(st.nextToken());
			int posC2 = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine(), SPACE);
			int posF1 = Integer.parseInt(st.nextToken());
			int posF2 = Integer.parseInt(st.nextToken());

			if (posC1 == posF1 && posC2 == posF2) {
				
				sb.append(0).append(NEW_LINE);
			} 
			else {
				
				for (int row = posC1; row < I; row++) {
					for (int col = posC2; col < I; col++) {
						if (!chess[row][col]) {
							chess[row][col] = true;

							Queue<Point> queue = new LinkedList<>();
							queue.offer(new Point(row, col));
							
							int times = 0;
							
							while (!queue.isEmpty()) {
								Point current = queue.poll();
								
								for (final int[] DIRECTION : DIRECTIONS) {
									int nextRow = current.row + DIRECTION[ROW];
									int nextCol = current.col + DIRECTION[COL];
									
									if (nextRow >= 0 && nextRow < I && nextCol >= 0 && nextCol < I) {
										if (!chess[nextRow][nextCol]) {		// 단계별로 동시에 체크가 안되는데... 그걸 체크해야 옳바른 값을..!
											chess[nextRow][nextCol] = true;
											
											times++;
																						
											queue.offer(new Point(nextRow, nextCol));

											if (nextRow == posF1 && nextCol == posF2) {
												sb.append(times).append(NEW_LINE);
											}

										}
									}
								}
							}
						}

					}
				}
			}

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
}
