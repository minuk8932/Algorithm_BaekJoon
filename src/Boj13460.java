import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13460 {
	private static final char BLOCK = '#';
	private static final char WAY = '.';
	private static final char HOLE = 'O';
	private static final char RED = 'R';
	private static final char BLUE = 'B';

	private static final int FAIL = -1;
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	private static Queue<Point> directQ = new LinkedList<>();
	private static Point[] ballPos = new Point[2];
	private static char[][] map = null;

	private static int[][][] isVisited = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		Point[] start = new Point[2];
		Point end = new Point(0, 0);

		isVisited = new int[N][M][2];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);

				if (map[i][j] == RED) {
					start[0] = new Point(i, j);
					map[i][j] = WAY;
				}

				if (map[i][j] == BLUE) {
					start[1] = new Point(i, j);
					map[i][j] = WAY;
				}

				if (map[i][j] == HOLE) {
					end.row = i;
					end.col = j;
				}
			}
		}
		
		ballPos[0] = new Point(start[0].row, start[0].col);		// 빨강먼저 움직이고		-> 파랑을 다른길로 빼고 빨강을 움직일 수 있는 경우를 고려해야함
		ballPos[1] = new Point(start[1].row, start[1].col);		// 파랑 움직임

		rBfs(N, M, start, end, 0, 1);
		bBfs(N, M, start, end, 1, 0);
		
		int redVisit = isVisited[end.row][end.col][0] - 1;
		int blueVisit = isVisited[end.row][end.col][1] - 1;

		if(redVisit > 10) {
			System.out.println(FAIL);
		}
		else {
			if(blueVisit > 0) {
				System.out.println(redVisit < blueVisit ? redVisit : FAIL);
			}
			else {
				System.out.println(redVisit);
			}
		}
	}

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	private static void rBfs(int R, int C, Point[] s, Point e, int color, int ballBlock) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(s[color].row, s[color].col));

		isVisited[s[color].row][s[color].col][color] = 1;

		while (!q.isEmpty()) {
			Point current = q.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
					if (map[nextRow][nextCol] != BLOCK && map[nextRow][nextCol] != BLUE	&& isVisited[nextRow][nextCol][color] == 0) {
						isVisited[nextRow][nextCol][color] = isVisited[current.row][current.col][color] + 1;
						directQ.offer(new Point(DIRECTION[ROW], DIRECTION[COL]));

						while (map[nextRow][nextCol] == WAY) {
							nextRow += DIRECTION[ROW];
							nextCol += DIRECTION[COL];
							
							isVisited[nextRow][nextCol][color] = isVisited[current.row][current.col][color] + 1;
							
							
						}
						
						isVisited[nextRow][nextCol][color] = isVisited[current.row][current.col][color];
						nextRow -= DIRECTION[ROW];
						nextCol -= DIRECTION[COL];

						if(color == 1) {
							if (isVisited[nextRow][nextCol][0] == isVisited[nextRow][nextCol][1]) {
								nextRow -= DIRECTION[ROW];
								nextCol -= DIRECTION[COL];
							}
						}

						ballPos[color] = new Point(nextRow, nextCol);

						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
	}
	
	private static void bBfs(int R, int C, Point[] s, Point e, int color, int ballBlock) {
		Queue<Point> q = new LinkedList<>();
		
		q.offer(new Point(s[color].row, s[color].col));
		isVisited[s[color].row][s[color].col][color] = 1;

		while (!q.isEmpty()) {
			Point current = q.poll();
			
			while(!directQ.isEmpty()) {
				Point direct = directQ.poll();
				
				int nextRow = current.row + direct.row;
				int nextCol = current.col + direct.col;
				
				if (nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
					if (map[nextRow][nextCol] != BLOCK && map[nextRow][nextCol] != RED && isVisited[nextRow][nextCol][color] < isVisited[current.row][current.col][color] + 1) {
						isVisited[nextRow][nextCol][color] = isVisited[current.row][current.col][color] + 1;
							
						if (map[nextRow][nextCol] == HOLE) return;
						
						while (map[nextRow][nextCol] == WAY) {
							nextRow += direct.row;
							nextCol += direct.col;
							
							isVisited[nextRow][nextCol][color] = isVisited[current.row][current.col][color] + 1;
							
							if (map[nextRow][nextCol] == HOLE) return;
						}
						
						isVisited[nextRow][nextCol][color] = isVisited[current.row][current.col][color];
	
						if(color == 1) {
							if (isVisited[nextRow][nextCol][0] == isVisited[nextRow][nextCol][1]) {
								nextRow -= direct.row;
								nextCol -= direct.col;
							}
						}
							
						ballPos[color] = new Point(nextRow, nextCol);
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
	}
}
