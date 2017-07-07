package bandwidth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj7562 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int[][] DIRECTIONS = { { 1, 2 }, { -1, 2 }, { 1, -2 }, { -1, -2 }, { 2, 1 }, { 2, -1 },
			{ -2, 1 }, { -2, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		
		while (T-- > 0) {
			int I = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine(), " ");
			Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));


			int[][] isVisited = new int[I][I];
			isVisited[start.row][start.col] = 1;

			Queue<Point> queue = new LinkedList<>();
			queue.offer(start);

			while (!queue.isEmpty()) {
				Point current = queue.poll();
				
				if(current.row == end.row && current.col == end.col){
					break;
				}

				for (final int[] DIRECTION : DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];

					if (nextRow >= 0 && nextRow < I && nextCol >= 0 && nextCol < I) {
						if (isVisited[nextRow][nextCol] == 0) {
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;

							queue.offer(new Point(nextRow, nextCol));
							
							
						}
					}
				}
			}

			sb.append(isVisited[end.row][end.col] - 1).append(NEW_LINE);

		}
		br.close();
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