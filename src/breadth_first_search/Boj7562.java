package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7562번 : 나이트의 이동
 *
 *	@see https://www.acmicpc.net/problem/7562
 *
 */
public class Boj7562 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int[][] DIRECTIONS = { { 1, 2 }, { -1, 2 }, { 1, -2 }, { -1, -2 }, { 2, 1 }, { 2, -1 },
			{ -2, 1 }, { -2, -1 } };

	public static void main(String[] args) throws Exception {
		// 퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		
		while (T-- > 0) {											// 테스트 케이스 만큼 수행
			int I = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine(), " ");
			Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));


			int[][] isVisited = new int[I][I];
			isVisited[start.row][start.col] = 1;

			Queue<Point> queue = new LinkedList<>();						// BFS 알고리즘 시작
			queue.offer(start);

			while (!queue.isEmpty()) {
				Point current = queue.poll();
				
				if(current.row == end.row && current.col == end.col){	// 도착시 종료
					break;
				}

				for (final int[] DIRECTION : DIRECTIONS) {						// 나이트가 이동하듯이 배열에 수를 담아 이동시킴
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];

					if (nextRow >= 0 && nextRow < I && nextCol >= 0 && nextCol < I) {
						if (isVisited[nextRow][nextCol] == 0) {
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;		// 배열에 나이트가 이동하는 횟수를 차례차례담아줌

							queue.offer(new Point(nextRow, nextCol));
							
							
						}
					}
				}
			}

			sb.append(isVisited[end.row][end.col] - 1).append(NEW_LINE);									// 결과값을 버퍼에 저장

		}
		br.close();
		System.out.println(sb.toString());						// 결과값 한번에 출력 
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