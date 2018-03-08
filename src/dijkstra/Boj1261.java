package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1261번 : 알고스팟
 *
 * 	@see https://www.acmicpc.net/problem/1261/
 * 
 */
public class Boj1261 {
	private static final String SPACE = " ";
	
	private static final int INF = Integer.MAX_VALUE;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N + 1][M + 1];
		int[][] cost = new int[N + 1][M + 1];
		
		for(int i = 1; i < N + 1; i++){
			char[] tmp = br.readLine().toCharArray();
			Arrays.fill(cost[i], INF);
			
			for(int j = 1; j < M + 1; j++){
				map[i][j] = Character.getNumericValue(tmp[j - 1]);
			}
		}
		
		boolean[][] isVisited = new boolean[N + 1][M + 1];
		
		isVisited[1][1] = true;
		cost[1][1] = 0;
		
		PriorityQueue<Point> pq = new PriorityQueue<>();								// 완전 탐색(BFS)와 다익스트라 알고리즘을 같이 적용
		pq.offer(new Point(1, 1, cost[1][1]));
		
		while(!pq.isEmpty()){
			Point current = pq.poll();
			
			for(int[] DIRECTION : DIRECTIONS){												// 4방향으로 접근을 시도
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow > 0 && nextRow < N + 1 && nextCol > 0 && nextCol < M + 1 && !isVisited[nextRow][nextCol]){
					if(cost[nextRow][nextCol] > map[nextRow][nextCol] + cost[current.row][current.col]){		// 다음에 갈 방향의 비용 > 다음 맵의 값(벽인지 아닌지) + 현재까지의 비용 이면
						isVisited[nextRow][nextCol] = true;
						cost[nextRow][nextCol] = map[nextRow][nextCol] + cost[current.row][current.col];		//	 다음 비용을 교체해줌
					
						pq.offer(new Point(nextRow, nextCol, cost[nextRow][nextCol]));									// 다음 위치, 값 입력
					}
				}
			}
		}
		
		System.out.println(cost[N][M]);										// 최종 목적지 도달시 벽을 부순 최소갯수
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점 이너 클래스
	 *
	 */
	private static class Point implements Comparable<Point> {
		int row;
		int col;
		int cost;
		
		/**
		 * 
		 * @param row : 방향
		 * @param col : 방향
		 * @param cost : 접근하는 방향에 따른 비용
		 * 
		 */
		public Point(int row, int col, int cost){
			this.row = row;
			this.col = col;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point p) {										// 만약 굳이 벽을 안부숴도 되는 경로가 있다면, 해당 방향으로 접근
			return this.cost < p.cost ? -1 : 1;
		}
	}
}
