package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 4184번: Ocean Current
 * 
 * @see https://www.acmicpc.net/problem/4184/
 *
 */
public class Boj4184 {
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_001;

	private static final char N = '0';
	private static final char NE = '1';
	private static final char E = '2';
	private static final char SE = '3';
	private static final char S = '4';
	private static final char SW = '5';
	private static final char W = '6';
	private static final char NW = '7';

	private static final String NEW_LINE = "\n";

	private static int[][] cost = null;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		char[][] map = new char[r + 1][c + 1];
		for (int i = 1; i < r + 1; i++) {
			String line = br.readLine();

			for (int j = 0; j < c; j++) {
				map[i][j + 1] = line.charAt(j);
			}
		}

		int n = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int startRow = Integer.parseInt(st.nextToken());			// 각 케이스 별 시작과 끝 정점을 저장
			int startCol = Integer.parseInt(st.nextToken());
			int endRow = Integer.parseInt(st.nextToken());
			int endCol = Integer.parseInt(st.nextToken());

			costInit(r, c);												// 각 케이스 별 비용 배열 초기화
			dijkstra(r, c, startRow, startCol, endRow, endCol, map);	// 다익스트라 알고리즘 실행

			sb.append(cost[endRow][endCol]).append(NEW_LINE);			// 다익스트라 알고리즘을 통해 나온 최소 비용을 버퍼에 저장
		}

		System.out.println(sb.toString());				// 결과 값 한번에 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point implements Comparable<Point> {
		int row;
		int col;
		int cost;

		public Point(int row, int col, int cost) {
			this.row = row;
			this.col = col;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point p) {
			return this.cost < p.cost ? -1 : 1;
		}
	}
	
	/**
	 *	다익스트라 알고리즘 메소드
	 * 
	 */
	private static void dijkstra(int n, int m, int sRow, int sCol, int eRow, int eCol, char[][] map) {
		cost[sRow][sCol] = 0;

		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(sRow, sCol, cost[sRow][sCol]));

		while (!pq.isEmpty()) {
			Point current = pq.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int nextCost = 0;

				if (nextRow > 0 && nextRow < n + 1 && nextCol > 0 && nextCol < m + 1) {
					int tmpRow = 0;
					int tmpCol = 0;

					switch (map[current.row][current.col]) {		// 현재 물의 흐름에 따라 해당 흐름을 변수 tmp에 저장
					case N:
						tmpRow = -1;
						tmpCol = 0;
						break;

					case NE:
						tmpRow = -1;
						tmpCol = 1;
						break;

					case E:
						tmpRow = 0;
						tmpCol = 1;
						break;

					case SE:
						tmpRow = 1;
						tmpCol = 1;
						break;

					case S:
						tmpRow = 1;
						tmpCol = 0;
						break;

					case SW:
						tmpRow = 1;
						tmpCol = -1;
						break;

					case W:
						tmpRow = 0;
						tmpCol = -1;
						break;

					case NW:
						tmpRow = -1;
						tmpCol = -1;
						break;
					}
					
					// 물의 흐름과 배가 가려는 방향이 같다면 0, 아니면 1을 다음 비용으로 저장
					nextCost = (DIRECTION[ROW] == tmpRow && DIRECTION[COL] == tmpCol) ? 0 : 1;

					if (cost[nextRow][nextCol] > cost[current.row][current.col] + nextCost) {	// 다음 비용의 값이 현재까지 비용보다 큰 경우
						cost[nextRow][nextCol] = cost[current.row][current.col] + nextCost;		// 값 갱신
						
						pq.offer(new Point(nextRow, nextCol, cost[nextRow][nextCol]));			// 다음 정점과 비용을 우선순위 큐에 담고 반복
					}
				}
			}
		}
	}

	/**
	 * 비용 배열 초기화 메소드
	 * 
	 */
	private static void costInit(int r, int c) {
		cost = new int[r + 1][c + 1];

		for (int i = 0; i < r + 1; i++) {	// 배열을 최댓값으로 초기화
			Arrays.fill(cost[i], INF);
		}
	}
}
