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
	private static final int INF = 1_000_000;
	
	private static char[][] map = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int C = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			map = new char[R][C];
			
			Point[] start = new Point[INF];
			for(int i = 0; i < INF; i++) {
				start[i] = new Point(0, 0);
			}
			
			boolean once = false;
			
			for (int i = 0; i < R; i++) {
				String line = br.readLine();

				for (int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			int idx = 0;
			
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(map[i][j] == WAY && !once) {						
						int chk = 0;
						
						for(final int[] DIRECTION : DIRECTIONS) {
							int nextRow = i + DIRECTION[ROW];
							int nextCol = j + DIRECTION[COL];
							
							if(nextRow < R && nextRow >= 0 && nextCol < C && nextCol >= 0 && map[nextRow][nextCol] == WAY) {
								chk++;
							}
						}
						
						if(chk <= 1) {
							once = true;
							start[idx].row = i;
							start[idx].col = j;
							
							idx++;
						}
					}
				}
			}
			
			int res = bfs(R, C, start);

			sb.append(ANSWER).append(SPACE).append(res == -1 ? 0 : res).append(END_LINE);
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

	private static int bfs(int R, int C, Point[] s) {
		int max = 0;
		
		for(int i = 0; i < s.length; i++) {
			Queue<Point> q = new LinkedList<>();
			q.offer(new Point(s[i].row, s[i].col));
			
			int[][] isVisited = new int[R][C];
			
			isVisited[s[i].row][s[i].col] = 1;
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION : DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
						if(isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] != BLOCK) {
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
							
							if(isVisited[nextRow][nextCol] > max) {
								max = isVisited[nextRow][nextCol];
							}
							
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
		
		return max - 1;
	}
}
