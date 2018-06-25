package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 10026번 : 적록색약
 *
 *	@see https://www.acmicpc.net/problem/10026
 *
 */
public class Boj10026 {
	private static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char[][] redG = new char[N][N];				// 적록 색약 배열
		char[][] norm = new char[N][N];				// 정상인 배열

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			norm[i] = line.toCharArray();

			for (int j = 0; j < N; j++) {					// 적록 색약 배열을 채움
				if (norm[i][j] == 'R') {						// 적색 녹색 둘다 구분이 안되는 것이기 때문에 한가지 단어로 통일
					redG[i][j] = 'G';
				} else {
					redG[i][j] = norm[i][j];
				}
			}
		}

		// normal
		int[][] isVisited = new int[N][N];
		int normalCnt = 0;

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (isVisited[row][col] == 0) {
					isVisited[row][col] = 1;

					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(row, col));

					while (!queue.isEmpty()) {
						Point current = queue.poll();

						for (final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];

							if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {				// 정해진 배열 범위 안에서
								if (isVisited[nextRow][nextCol] == 0															// 아직 방문하지 않은 인덱스 이면서,
										&& norm[nextRow][nextCol] == norm[current.row][current.col]) {		// 동시에 현재와 다음 경로의 일반인 배열 안의 값이 같을 경우
									isVisited[nextRow][nextCol] = 1;
									
									normalCnt++;																						// 일반인이 봤을때 인접 블럭의 수
									
									queue.offer(new Point(nextRow, nextCol));									
								}
							}
						}
					}
				}
			}
		}

		// abnormal

		isVisited = new int[N][N];
		
		int abnormalCnt = 0;

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (isVisited[row][col] == 0) {
					isVisited[row][col] = 1;

					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(row, col));

					while (!queue.isEmpty()) {
						Point current = queue.poll();

						for (final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];

							if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
								if (isVisited[nextRow][nextCol] == 0
										&& redG[nextRow][nextCol] == redG[current.row][current.col]) {
									
									isVisited[nextRow][nextCol] = 1;

									queue.offer(new Point(nextRow, nextCol));
									abnormalCnt++;																	// 적록 색약인 사람이 보았을 때 인접 블럭의 수
								}
							}
						}
					}
				}
			}
		}
		
		int size = N * N;
		
		System.out.println((size - normalCnt) + " " + (size - abnormalCnt));					// 전체 크기에서 동일한 블럭을 가진 갯수, 즉 각각의 카운트를 뺀 값을 출력 = 결과
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점 이너 클래스	
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

}
