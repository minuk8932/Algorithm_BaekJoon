package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11967번: 불켜기
 *
 *	@see https://www.acmicpc.net/problem/11967/
 *
 */
public class Boj11967 {
	private static int[][] map = null;

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int ON = 1;
	private static final int OFF = 0;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		Light[] info = new Light[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			info[i] = new Light(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		while (true) {
			if (!bfs(1, 1, N, info)) break;		// 너비 우선 탐색 메소드를 통해 새로이 켜진 불이 있는지 확인 후 없다면 반복문 종료
		}

		System.out.println(countRooms(N));		// 불이 켜진 방을 세주는 메소드를 통해 불이 켜진 방 갯수를 구함
	}
	
	/**
	 * 전등 버튼 정보 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Light {
		int btnRow;
		int btnCol;
		int onRow;
		int onCol;

		public Light(int btnRow, int btnCol, int onRow, int onCol) {
			this.btnRow = btnRow;
			this.btnCol = btnCol;
			this.onRow = onRow;
			this.onCol = onCol;
		}
	}
	
	/**
	 * 정점 이너 클래스
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
	 * @return	새로이 켜진 불이 있으면 true
	 */
	private static boolean bfs(int row, int col, int n, Light[] info) {
		boolean isChanged = false;		// 불의 변화 감지

		boolean[][] isVisited = new boolean[n + 1][n + 1];

		Queue<Point> q = new LinkedList<>();
		map[row][col] = ON;
		isVisited[row][col] = true;

		q.offer(new Point(1, 1));

		while (!q.isEmpty()) {
			Point current = q.poll();

			for (Light lNext : info) {
				if (current.row == lNext.btnRow && current.col == lNext.btnCol) {		// 불의 버튼정보를 통해
					if (map[lNext.onRow][lNext.onCol] == OFF) isChanged = true;		// 이전에 꺼진 방이 켜진경우 변화한것으로 체크 후

					map[lNext.onRow][lNext.onCol] = ON;		// 방의 불을 킴
				}
			}

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (nextRow > 0 && nextRow < n + 1 && nextCol > 0 && nextCol < n + 1) {
					if (map[nextRow][nextCol] == ON && !isVisited[nextRow][nextCol]) {		// 다음 목적지가 방문하지 않았고, 불이 켜져있는 경우
						isVisited[nextRow][nextCol] = true;

						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		return isChanged;
	}
	
	/**
	 * 방의 갯수를 세는 메소드
	 * 
	 */
	private static int countRooms(int n) {
		int cnt = 0;

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (map[i][j] == ON) cnt++;
			}
		}

		return cnt;
	}
}
