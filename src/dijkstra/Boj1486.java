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

	private static int N = 0;
	private static int M = 0;
	private static int T = 0;
	private static int D = 0;
	private static int[][] map = null;

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_001;
	
	private static int[][][] timeList = null;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		T = Integer.parseInt(st.nextToken()); // 높이차이 한계, Math.abs([i - 1] - [i]) <= T
		D = Integer.parseInt(st.nextToken()); // 호텔에서 출발해 호텔까지 오는데 걸리는 시간
		
		timeList = new int[2][N][M];	// 0 : go, 1 : back

		map = new int[N][M];

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
			for(int j = 0; j < M; j++) {	// 시간 측정 배열 초기화
				timeList[0][i][j] = INF;
				timeList[1][i][j] = INF;
			}
		}
		
		dijkstraGo();		// 최단경로 정방향
		dijkstraBack();		// 최단경로 역방향
		
		int highest = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int sum = timeList[0][i][j] + timeList[1][i][j];	// 합 경로 계산
				
				if(sum <= D) {								// 합 경로가 D보다 작거나 같은 경우 중
					highest = Math.max(map[i][j], highest);	// 가장 높은 위치를 받아둠
				}
			}
		}
		
		System.out.println(highest);		// 결과값 출력
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
	
	/**
	 * 	정방향 최단거리 메소드
	 */
	private static void dijkstraGo(){
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, map[0][0]));
		
		timeList[0][0][0] = 0;
		
		while(!pq.isEmpty()) {
			Point current = pq.poll();
			
			for(final int[] DIRECTION : DIRECTIONS) {
				int nextRow = DIRECTION[ROW] + current.row;
				int nextCol = DIRECTION[COL] + current.col;
				int nextCost = 0;

				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
					// 두 위치의 차가 T보다 큰 경우 다음 순서로 넘어감
					if(Math.abs(map[nextRow][nextCol] - current.cost) > T) continue;
					
					if(current.cost >= map[nextRow][nextCol]) {		// 현재 비용과 다음 비용의 크기를 비교하여 값 설정
						nextCost = 1;
					}
					else {
						int diff = map[nextRow][nextCol] - current.cost;
						nextCost = diff * diff;
					}
					
					// 현재 인덱스까지 비용 > 이전 인덱스 + 다음 이동 비용 인 경우 값을 작은 것으로 초기화
					if(timeList[0][nextRow][nextCol] > timeList[0][current.row][current.col] + nextCost) {
						timeList[0][nextRow][nextCol] = timeList[0][current.row][current.col] + nextCost;
						
						pq.offer(new Point(nextRow, nextCol, map[nextRow][nextCol]));
					}
				}
			}
		}
	}
	
	/**
	 * 	역방향 최단거리 메소드
	 */
	private static void dijkstraBack(){
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, map[0][0]));
		
		timeList[1][0][0] = 0;
		
		while(!pq.isEmpty()) {
			Point current = pq.poll();
			
			for(final int[] DIRECTION : DIRECTIONS) {
				int nextRow = DIRECTION[ROW] + current.row;
				int nextCol = DIRECTION[COL] + current.col;
				int nextCost = 0;
				
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
					if(Math.abs(map[nextRow][nextCol] - current.cost) > T) continue;
					
					if(current.cost > map[nextRow][nextCol]) {			// 정방향 메소드와 조건을 반대로하여 값 할당
						int diff = map[nextRow][nextCol] - current.cost;
						nextCost = diff * diff;
					}
					else {
						nextCost = 1;
					}
						
					if(timeList[1][nextRow][nextCol] > timeList[1][current.row][current.col] + nextCost) {
						timeList[1][nextRow][nextCol] = timeList[1][current.row][current.col] + nextCost;
							
						pq.offer(new Point(nextRow, nextCol, map[nextRow][nextCol]));
					}
				}
			}
		}
	}
}
