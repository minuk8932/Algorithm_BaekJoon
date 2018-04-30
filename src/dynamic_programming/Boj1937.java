package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1937번: 욕심쟁이 판다
 *
 *	@see https://www.acmicpc.net/problem/1937/
 *
 */
public class Boj1937 {
	private static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int MAX = 501;

	private static int n = 0;
	private static int[][] map = null;
	private static int[][] isVisited = null;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		isVisited = new int[MAX][MAX];
		map = new int[MAX][MAX];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();

		int max = 0;

		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (isVisited[row][col] != 0)					// 방문 데이터가 존재 할 경우 pass
					continue;
				
				max = Math.max(max, dfs(row, col));		// 깊이 우선 탐색 알고리즘을 실행해, 가장 길게 탐색을 수행 한 값을 max에 저장
			}
		}

		System.out.println(max);		// 최종 결과값 출력
	}
	
	/**
	 * 깊이 우선 탐색 알고리즘
	 * 	@param row: 방문할 행 좌표
	 * 	@param col: 방문할 열 좌표
	 * 	@return: 최종 방문 데이터 반환
	 */
	private static int dfs(int row, int col) {
		if(isVisited[row][col] != 0) return isVisited[row][col];	// 이미 방문한 경우 메모이제이션 되어있는 값을 반환

		isVisited[row][col] = 1;							// 방문 안한 경우 1저장

		for (final int[] DIRECTION : DIRECTIONS) {
			int nextRow = row + DIRECTION[ROW];
			int nextCol = col + DIRECTION[COL];

			if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;			// 맵밖으로 벗어나는 경우
			if (map[nextRow][nextCol] <= map[row][col]) continue;									// 대나무 수가 적어서 판다가 가지 않는 경우

			isVisited[row][col] = Math.max(isVisited[row][col], dfs(nextRow, nextCol) + 1);	// 그외에는 판다가 몇번 이동했는지 그 최댓 값을 현 위치 방문 배열에 담고, 다음 경로+1 재귀적 호출

		}

		return isVisited[row][col];	// 최종 방문한 횟수 반환
	}
}
