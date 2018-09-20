package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2638번: 치즈
 *
 *	@see https://www.acmicpc.net/problem/2638/
 *
 */
public class Boj2638 {
	private static int[][] map = null;
	private static int[][] melt = null;
	private static boolean[][] isVisited = null;

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	private static final int CHEESE = 1;
	private static final int OUTSIDE = 0;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int res = 0;
		
		while(getCheeseCount(N, M) != 0) {			// 반복문을 돌면서 남은 치즈의 갯수를 확인	
			isVisited = new boolean[N][M];
			melt = new int[N][M];
			
			search(N, M, new Point(0, 0));		// dfs 메소드를 통한 사라져야 할 치즈를 찾음
			
			removeCheese(N, M);			// 사라져야 할 치즈 제거
			res++;						// 시간 계산
		}
		
		System.out.println(res);		// 최종 치즈가 다 녹는데 걸리는 시간 출력
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
	 * 깊이 우선 탐색 메소드
	 * 
	 */
	private static void search(int n, int m, Point p) {
		if(isVisited[p.row][p.col]) return;		// 이미 방문한 정점인지 체크
		isVisited[p.row][p.col] = true;
		
		for(final int[] DIRECTION: DIRECTIONS) {
			int nextRow = p.row + DIRECTION[ROW];
			int nextCol = p.col + DIRECTION[COL];
			
			if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
				if(map[nextRow][nextCol] == CHEESE) {		// 현 정점의 주변에 치즈가 존재하는 경우
					melt[nextRow][nextCol]++;				// 녹이기 +1
				}
				
				if(map[nextRow][nextCol] == OUTSIDE) {			// 주변이 외부인 경우
					search(n, m, new Point(nextRow, nextCol));	// 깊이 우선 탐색 재귀 호출, 갇힌 공간이라면 최종적으로 방문 배열을 통해 종료됨
				}
			}
		}
	}
	
	/**
	 * 치즈 제거 메소드
	 * 
	 */
	private static void removeCheese(int n, int m) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {			// 녹이기 배열이 2이상인 경우, 외부와 최소 2군데 맞닿아 있음
				if(melt[i][j] > 1) map[i][j] = 0;
			}
		}
	}
	
	/**
	 * 남은 치즈 갯수 반환 메소드
	 * 
	 */
	private static int getCheeseCount(int n, int m) {
		int cnt = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {			// 현재 남아있는 치즈의 수를 구해서 반환
				if(map[i][j] == CHEESE) cnt++;
			}
		}
		
		return cnt;
	}
}
