package breadth_first_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2589번 : 보물섬
 *
 *	@see https://www.acmicpc.net/problem/2589
 *
 */
public class Boj2589 {
	private static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		String[] line = new String[N];

		for (int row = 0; row < N; row++) {
			line[row] = br.readLine();
			for (int col = 0; col < M; col++) {
				map[row] = line[row].toCharArray();
			}
		}
		
		int cost = 0;
		
		for (int row = 0; row < N; row++) {						// 해당 맵에서 L이 존재하는 부분을 찾고
			for (int col = 0; col < M; col++) {
				if (map[row][col] == 'L') {
					
					int[][] isVisited = new int[N][M];
					
					Queue<Point> queue = new LinkedList<>();

					queue.offer(new Point(row, col));			// BFS 실행

					while (!queue.isEmpty()) {
						Point current = queue.poll();

						for (final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
								if(isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] == 'L'){		// 방문하지 않은 인덱스이면서, L인 곳만 방문
									isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;	// 방문 배열에 이동하는 시간을 담아줌
																		
									queue.offer(new Point(nextRow, nextCol));
									
									cost = Math.max(cost, isVisited[nextRow][nextCol]);								// 이중 가장 멀리 떨어져있는 두 L에 보물이 존재하므로 최댓값을 구함
									
									
								}
							}
						}
					}	
				}
			}
		}
		
		System.out.println(cost);		// 보물에서 보물까지 걸리는 시간 출력
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점이너 클래스
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
