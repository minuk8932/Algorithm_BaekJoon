package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1486번: 등산
 *
 *	@see https://www.acmicpc.net/problem/1486/
 *
 */
public class Boj1486 {
	private static final String SPACE = " ";

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_001;
	
	private static int[][][] timeList = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken()); // 높이차이 한계, Math.abs([i - 1] - [i]) <= T
		int D = Integer.parseInt(st.nextToken()); // 호텔에서 출발해 호텔까지 오는데 걸리는 시간
		
		timeList = new int[2][N][M];	// 0 : go, 1 : back

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < M; j++) {
				char tmp = line.charAt(j);

				if (tmp >= 'A' && tmp <= 'Z') {
					map[i][j] = (tmp - 'A');
				}

				else if (tmp >= 'a' && tmp <= 'z') {
					map[i][j] = (tmp - 'a' + 26);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				timeList[0][i][j] = INF;
				timeList[1][i][j] = INF;
			}
		}
		
		dijkstra(map, N, M, T, 0);
		dijkstra(map, N, M, T, 1);
		
		System.out.println(getHighestSum(map, N, M, D));		// 결과 출력
	}
	
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
	
	private static int getHighestSum(int[][] arr, int n, int m, int dist) {
		int highest = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				int sum = timeList[0][i][j] + timeList[1][i][j];	// 합 경로 계산
				
				if(sum <= dist) {
					highest = Math.max(arr[i][j], highest);
				}
			}
		}
		
		return highest;
	}
	
	private static void dijkstra(int[][] arr, int n, int m, int time, int goBack){
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, arr[0][0]));
		
		timeList[goBack][0][0] = 0;
		
		while(!pq.isEmpty()) {
			Point current = pq.poll();
			
			for(final int[] DIRECTION : DIRECTIONS) {
				int nextRow = DIRECTION[ROW] + current.row;
				int nextCol = DIRECTION[COL] + current.col;
				int nextCost = 0;

				if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
					if(Math.abs(arr[nextRow][nextCol] - current.cost) > time) continue;
					
					nextCost = getCost(arr, current.cost, nextCost, nextRow, nextCol, goBack);
					
					if(timeList[goBack][nextRow][nextCol] > timeList[goBack][current.row][current.col] + nextCost) {
						timeList[goBack][nextRow][nextCol] = timeList[goBack][current.row][current.col] + nextCost;
						
						pq.offer(new Point(nextRow, nextCol, arr[nextRow][nextCol]));
					}
				}
			}
		}
	}
	
	private static int getCost(int[][] arr, int current, int next, int row, int col, int gb) {
		int diff = arr[row][col] - current;
		diff *= diff;
		
		if(current == arr[row][col]) {
			return 1;
		}
		else if(current > arr[row][col]) {
			next = gb == 0 ? 1 : diff;
		}
		else {
			next = gb == 1 ? 1 : diff;
		}
		
		return next;
	}
}
