package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2158번: 산악자전거
 *
 *	@see https://www.acmicpc.net/problem/2158/
 *
 */
public class Boj2158 {
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 2_100_000_000;

	private static double[][] times = null;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double V = Double.parseDouble(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[][] mountain = new int[R][C];
		times = new double[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());

			Arrays.fill(times[i], INF);			// 최단 경로를 구하기 위해 최대 시간을 담아줌

			for (int j = 0; j < C; j++) {
				mountain[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dijkstra(mountain, V, R, C);		// 다익스트라 메소드를 통한 최단 시간 계산
		System.out.printf("%.3f", times[R - 1][C - 1]);		// 목적지 도달 시 최단 경로를 출력, 오차범위: [10^-2] 이므로 [10^-3]까지 계산(반올림 연산 방지) 후 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point implements Comparable<Point> {
		int row;
		int col;
		double vel;
		double sec;

		public Point(int row, int col, double vel, double sec) {
			this.row = row;
			this.col = col;
			this.vel = vel;
			this.sec = sec;
		}

		@Override
		public int compareTo(Point p) {
			return this.sec < p.sec ? -1 : 1;
		}
	}
	
	/**
	 * 다익스트라 메소드
	 * @param meterPerSec: 초기 속력
	 */
	private static void dijkstra(int[][] map, double meterPerSec, int N, int M) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		times[0][0] = 0;
		
		pq.offer(new Point(0, 0, meterPerSec, times[0][0]));

		while (!pq.isEmpty()) {
			Point current = pq.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {		// 맵의 범위 내에서
					int dist = map[current.row][current.col] - map[nextRow][nextCol];	// 인접한 두 카의 거리를 구하고
					double tmpTime = 1 / current.vel;						// 현재 위치까지 걸린 시간을 속력을 통해 구함
					double nextVel = Math.pow(2, dist) * current.vel;		// 이후 다음 속력을 계산
					
					if(times[nextRow][nextCol] > times[current.row][current.col] + tmpTime) {	// 현 위치 까지의 시간이, 다른 방향에서 온 시간보다 큰 경우
						times[nextRow][nextCol] = times[current.row][current.col] + tmpTime;		// 값 갱신(최솟값)
						pq.offer(new Point(nextRow, nextCol, nextVel, times[nextRow][nextCol]));	// 우선순위 큐에 다음에 필요한 값들을 담음
					}
				}
			}
		}
	}
}
