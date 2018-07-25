package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba 
 * 백준 3482번: Labyrinth
 *
 * @see https://www.acmicpc.net/problem/3482/
 *
 */
public class Boj3482 {
	private static final String END_LINE = ".\n";
	private static final String ANSWER = "Maximum rope length is ";

	private static final char BLOCK = '#';
	private static final char WAY = '.';

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_000;

	private static char[][] map = null;
	private static int isVisited[][] = null;

	private static int max = 0;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			map = new char[R][C];
			isVisited = new int[1001][1001];
			Point start = new Point(0, 0);

			int idx = 0;
			max = 0;

			for (int i = 0; i < R; i++) {
				String line = br.readLine();

				for (int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);
				}
			}

LOOP:		 for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					int cnt = 0;

					if (map[i][j] == WAY) {
						for (final int[] DIRECTION : DIRECTIONS) {
							int nextRow = i + DIRECTION[ROW];
							int nextCol = j + DIRECTION[COL];

							if (nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
								if (map[nextRow][nextCol] == BLOCK) {
									cnt++;
								}
							}
						}

						if (cnt == 3) {
							start = new Point(i, j); 	// 시작 가능한 지점 구하기
							break LOOP;
						}
					}
				}
			}

			bfs(R, C, start); 	// 임시 시작 지점으로부터 너비 우선 탐색을 통한 최대 지점 찾기

			Point init = new Point(0, 0);
LOOP:		for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (isVisited[i][j] == 0)
						continue;

					if (isVisited[i][j] == max) { 	// 최대 값을 갖는 점 중 하나를 init 변수에 저장
						max = isVisited[i][j];
						init = new Point(i, j);
						
						break LOOP;
					}
				}
			}

			max = 0;
			isVisited = new int[1001][1001]; 	// 방문 배열과 최댓값 초기화
			bfs(R, C, init); 	// 최대 지점에서 다시 최대값 재탐색

			sb.append(ANSWER).append(max == 0 ? 0 : max - 1).append(END_LINE); // 최댓값이 0이면, 0 나머지는 max -1 를 버퍼에 저장
		}

		System.out.println(sb.toString()); // 결과값 한번에 출력
	}

	/**
	 * 정점 이너 클래스
	 * 
	 * @author minchoba
	 *
	 */
	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	/**
	 * 너비 우선 탐색 메소드
	 * 
	 */
	private static void bfs(int R, int C, Point s) {
		if (s == null) return;

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(s.row, s.col));

		isVisited[s.row][s.col] = 1;

		while (!q.isEmpty()) {
			Point current = q.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
					if (isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] == WAY) {
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;

						if (isVisited[nextRow][nextCol] > max) max = isVisited[nextRow][nextCol]; // 최댓값이 나올때 값을 갱신

						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
	}
}
