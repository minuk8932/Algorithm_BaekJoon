import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3482 {
	private static final String SPACE = " ";
	private static final String END_LINE = ".\n";
	private static final String ANSWER = "Maximum rope length is ";

	private static final char BLOCK = '#';
	private static final char WAY = '.';

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int C = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			Point start = new Point(-1, -1);

			char[][] map = new char[R][C];
			for (int i = 0; i < R; i++) {
				String line = br.readLine();

				for (int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);

					if (map[i][j] == WAY) {
						start = new Point(i, j);
					}
				}
			}
			int[][] isVisited = new int[R][C];
			
			bfs(R, C, isVisited, map, start);

			sb.append(ANSWER).append(" ").append(END_LINE);
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

	private static void bfs(int R, int C, int[][] isVisited, char[][] map, Point p) {
		int max = 0;

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(p.row, p.col));

		isVisited[p.row][p.col] = 1;

		while (!q.isEmpty()) {
			Point current = q.poll();
			
			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
					if (isVisited[nextRow][nextCol] == 0){
						if(map[nextRow][nextCol] != BLOCK) {
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
							
							q.offer(new Point(nextRow, nextCol));
	
							if (isVisited[nextRow][nextCol] > max) {
								max = isVisited[nextRow][nextCol];
							}
						}
					}					
				}
			}
		}
		
		//debug
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				System.out.print(isVisited[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
