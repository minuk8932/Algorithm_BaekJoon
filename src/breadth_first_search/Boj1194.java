package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1194번: 달이 차오른다, 가자
 *
 *	@see https://www.acmicpc.net/problem/1194/
 *
 */
public class Boj1194 {
	private static final char START = '0';
	private static final char BLOCK = '#';
	private static final char EXIT = '1';

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int NO_WAY = -1;
	
	private static final char DOOR_LOCK = 'A';
	private static final char KEY = 'a';

	private static int[][][] isVisited = null;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] map = new char[N][M];
		isVisited = new int[N][M][1 << 6];		// 6자리 비트마스크 생성, 열쇠 소지 여부 체크

		int sRow = 0, sCol = 0;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);

				if (map[i][j] == START) {		// 시작 정점 저장
					sRow = i;
					sCol = j;
				}
			}
		}

		System.out.println(bfs(N, M, sRow, sCol, map));	// 너비 우선 탐색을 이용한 결과값 출력
	}
	
	/**
	 * 	정점 이너 클래스
	 * 	@author minchoba
	 *	
	 */
	private static class Point {
		int row;
		int col;
		int key;

		public Point(int row, int col, int key) {
			this.row = row;
			this.col = col;
			this.key = key;
		}
	}
	
	/**
	 * 너비 우선 탐색 메소드
	 * 
	 */
	private static int bfs(int N, int M, int row, int col, char[][] map) {
		isVisited[row][col][0] = 1;			// 시작: 방문 배열 1 저장

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(row, col, 0));

		while (!q.isEmpty()) {
			Point current = q.poll();
			
			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int nextKey = 0;

				if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
					if (map[nextRow][nextCol] != BLOCK && isVisited[nextRow][nextCol][current.key] == 0) {	// 경로 존재, 현 위치에 방문하지 않은 경우
						// 다음 이동 경로에 열쇠가 떨어져 있는 경우
						if (KEY <= map[nextRow][nextCol] && map[nextRow][nextCol] <= (KEY + 5)) {
							// 떨어진 키가 가지고 있는 것이 아니라면, 줍고 해당 열쇠 비트를 켜줌 (a: 0번 ~ f: 5번)
							if(current.key != 1 << map[nextRow][nextCol] - KEY) nextKey = current.key | (1 << map[nextRow][nextCol] - KEY);
							
							// 다음 경로가 방문을 한 적이 없는 곳인 경우
							if (isVisited[nextRow][nextCol][nextKey] == 0) {
								isVisited[nextRow][nextCol][nextKey] = isVisited[current.row][current.col][current.key] + 1;

								q.offer(new Point(nextRow, nextCol, nextKey));
							}
						}
						// 현 위치에 문이 닫혀있는 경우
						else if (DOOR_LOCK <= map[nextRow][nextCol] && map[nextRow][nextCol] <= (DOOR_LOCK + 5)) {
							// 열쇠 비트로 해당 문의 열쇠를 가진지 확인
							nextKey = current.key & (1 << map[nextRow][nextCol] - DOOR_LOCK);
							
							// 열쇠를 가지고 있는 경우 문을 열고 지나감 (없다면, nextKey == 0, a: 1, b: 2, c: 4, d: 8, e: 16, f: 32)
							if (nextKey != 0) {
								isVisited[nextRow][nextCol][current.key] = isVisited[current.row][current.col][current.key] + 1;

								q.offer(new Point(nextRow, nextCol, current.key));
							}
						}
						
						// 아무런 조작 없이 이동 가능한 경로, 출구 포함
						else {
							isVisited[nextRow][nextCol][current.key] = isVisited[current.row][current.col][current.key] + 1;
							
							// 탐색 중 출구에 도달 한 경우, 현재 방문 배열의 값 - 1 반환
							if (map[nextRow][nextCol] == EXIT) return isVisited[nextRow][nextCol][current.key] - 1;
							q.offer(new Point(nextRow, nextCol, current.key));
						}
					}
				}
			}
		}
		
		// 탐색이 완료되어도 탈출 불가능한 경우
		return NO_WAY;
	}
}
