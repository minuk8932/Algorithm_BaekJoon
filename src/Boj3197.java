import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3197 {
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final char SWAN = 'L';
	private static final char WATER = '.';
	private static final char ICE = 'X';

	private static char[][] map = null;
	private static int[][] meltTime = null;
	private static int timer = 1;
	private static boolean isOver = true;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		Point[] start = new Point[2];
		int idx = 0;

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == SWAN) {
					start[idx] = new Point(i, j);
					idx++;
				}
			}
		}

		int res = 0;
		meltTime = new int[R][C];

		while (true) {
			isOver = true;

			bfs(R, C);
			timer++;

			if (isOver) break;
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(meltTime[i][j]);
			}
			System.out.println();
		}

//		System.out.println(max);
	}

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static void dijkstra() {
		
	}

	private static void bfs(int N, int M) {
		boolean[][] isVisited = new boolean[N][M];

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (isVisited[row][col])
					continue;
				if (map[row][col] != ICE) {
					isVisited[row][col] = true;

					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));

					while (!q.isEmpty()) {
						Point current = q.poll();

						for (final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];

							if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
								if (!isVisited[nextRow][nextCol]) {
									if (map[nextRow][nextCol] == ICE) {
										isVisited[nextRow][nextCol] = true;
										map[nextRow][nextCol] = WATER;
										meltTime[nextRow][nextCol] = timer;
										isOver = false;
										
									} 
									else if (map[nextRow][nextCol] == WATER) {
										isVisited[nextRow][nextCol] = true;
										q.offer(new Point(nextRow, nextCol));
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
