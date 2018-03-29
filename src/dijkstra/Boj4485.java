package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *	백준 4485번 : 녹색 옷 입은 애가 젤다지?
 *
 *	@see https://www.acmicpc.net/problem/4485
 *
 */

public class Boj4485 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String COLON = ":";
	private static final String PROBLEM = "Problem";
	
	private static final int INFINITE = Integer.MAX_VALUE - 1;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String line = null;
		
		StringBuilder sb = new StringBuilder();
		int tCase = 1;
		
		while(!("0".equals(line = br.readLine()))){					// line 변수에 받은 값이 0이면 반복문 종료
			int N = Integer.parseInt(line);
			int[][] cave = new int[N][N];
			int[][] dist = new int[N][N];
			
			for(int i = 0; i < N; i++){
				st = new StringTokenizer(br.readLine(), SPACE);
				for(int j = 0; j < N; j++){
					cave[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = INFINITE;
				}
			}
			
			// Problem tCase: dist[N - 1][N - 1] (0,0 에서 N-1, N-1까지 최단 거리)
			sb.append(PROBLEM).append(SPACE).append(tCase).append(COLON).append(SPACE).append(dijkstra(cave, dist, N)).append(NEW_LINE);
			tCase++;
		}
		br.close();
		
		System.out.println(sb.toString());		// 결과 출력
	}
	
	/**
	 * 
	 *	 다익스트라 알고리즘 메소드
	 * 
	 * @param cave
	 * @param dist
	 * @param N
	 * @return	 dist[N - 1][N - 1] (최단거리)
	 * 
	 */
	
	private static int dijkstra(int[][] cave, int[][] dist, int N){
		// 우선순위 큐 객체 생성
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, cave[0][0]));										
		
		dist[0][0] = cave[0][0];																
		
		while(!pq.isEmpty()){
			Point current = pq.poll();
			
			if (current.cost > dist[current.row][current.col]) {
				continue;
			}
			
			for(int[] DIRECTION : DIRECTIONS){
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N){
					int minDist = dist[current.row][current.col] + cave[nextRow][nextCol];
					
					if(minDist < dist[nextRow][nextCol]){
						dist[nextRow][nextCol] = minDist;
						pq.offer(new Point(nextRow, nextCol, dist[nextRow][nextCol]));
					}
				}
			}
		}
		return dist[N - 1][N - 1];
	}
	
	/**
	 * 
	 * @author minchoba
	 *	탐색을 위한 정점 이너 클래스
	 *
	 */
	
	private static class Point implements Comparable<Point>{
		int row;
		int col;
		int cost;
		
		/**
		 *  
		 * @param row
		 * @param col
		 * @param cost
		 *	
		 */
		public Point(int row, int col, int cost){
			this.row = row;
			this.col = col;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Point p) {	 
			return this.cost < p.cost ? -1 : 1;
		}
		
	}
}
