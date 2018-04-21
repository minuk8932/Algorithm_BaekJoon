package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10026번 : 적록색약 (by DFS)
 *
 *	@see https://www.acmicpc.net.problem/10026/
 *
 */
public class Boj10026 {
	private static final String SPACE = " ";

	private static final char IS_GREEN = 'G';
	private static final char RED = 'R';

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	private static int N = 0;
	private static int sector = 0;
	private static boolean[][] isVisited = null;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];			// 정상인의 경우
		char[][] mapRG = new char[N][N];		// 색약의 경우

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				mapRG[i][j] = map[i][j];

				if (mapRG[i][j] == IS_GREEN) {		// 색약이면 초록을 빨간색으로 바꿔줌 (즉 초록 == 빨강 같은 구역)
					mapRG[i][j] = RED;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		isVisited = new boolean[N][N];				// 방문 배열 초기화

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (!isVisited[row][col]) {					// 해당 위치가 아직 방문 전이라면
					sector++;									// 구역의 갯수 +1, 즉 dfs 메소드가 한번 종료되면, 다른 구역으로 넘어간다는 의미
					dfs(new Point(row, col), map);	// dfs 메소드 실행
				}
			}
		}
		sb.append(sector).append(SPACE);			// 해당 맵의 구역의 개수

		sector = 0;
		isVisited = new boolean[N][N];	
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (!isVisited[row][col]) {					// 위의 반복문과 마찬가지로 작동
					sector++;
					dfs(new Point(row, col), mapRG);
				}
			}
		}
		sb.append(sector);

		System.out.println(sb.toString());			// 버퍼에 담긴 각각의 구역 갯수를 한번에 출력
	}
	
	/**
	 * 깊이 우선 탐색 알고리즘
	 * 	@param p		방문할 정점
	 * 	@param map 정상인? 또는 색약?
	 */
	private static void dfs(Point p, char[][] map) {
		isVisited[p.row][p.col] = true;							// 탐색을 시작하는 위치를 방문함으로 표기
		
		for (final int[] DIRECTION : DIRECTIONS) {			// 시작 위치부터 4방향으로 탐색
			int nextRow = p.row + DIRECTION[ROW];
			int nextCol = p.col + DIRECTION[COL];

			if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {							// 다음 이동할 행과 열이 범위내에 존재하고
				if (map[p.row][p.col] == map[nextRow][nextCol] && !isVisited[nextRow][nextCol]) {	// 이전의 알파벳과 다음 목적 배열의 알파벳이 같고, 아직 방문하기 전이라면
					dfs(new Point(nextRow, nextCol), map);					// 같은 구역으로 여기면서 해당 다음 정점을 이용해 다시 dfs 메소드를 호출
				}
			}
		}
	}
	
	/**
	 * 	@author minchoba
	 *	행과 열의 정점을 저장할 이너 클래스
	 */
	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
