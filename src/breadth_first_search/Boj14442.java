package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14442번: 벽 부수고 이동하기 2
 *
 *	@see https://www.amicpc.net/problem/14442/
 *
 */
public class Boj14442 {
	private static final int NO_WAY = -1;
	private static final int DIRECTIONS[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final char WAY = '0';
	private static final int INF = 10_000_001;
	
	private static int[][][] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		isVisited = new int[N + 1][M + 1][K + 2];
		char[][] map = new char[N + 1][M + 1];
		
		for(int i = 1; i < N + 1; i++) {
			String line = br.readLine();
			
			for(int j = 1; j < M + 1; j++) {
				map[i][j] = line.charAt(j - 1);
			}
		}
		
		bfs(N, M, K, map);		// 너비 우선 탐색 메소드를 통한 각 경우 별 최솟 값을 구함
		
		int min = INF;
		for(int i = 0; i < K + 1; i++) {				// K번 벽을 부수는 과정에서 가장 최소의 비용을 min에 저장
			if(isVisited[N][M][i] != 0) {
				min = Math.min(min, isVisited[N][M][i]);
			}
		}
		
		System.out.println(min == INF ? NO_WAY : min);	// min이 INF 인 경우 -1을 그 외 min 그대로 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point{
		int row;
		int col;
		int bCnt;
		
		public Point(int row, int col, int bCnt) {
			this.row = row;
			this.col = col;
			this.bCnt = bCnt;
		}
	}
	
	/**
	 * 너비 우선 탐색 메소드
	 * 
	 */
	private static void bfs(int row, int col, int k, char[][] m) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(1, 1, 0));			// (1, 1) 출발, 0: 벽 부순 횟수
		isVisited[1][1][0] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int nextBCnt = current.bCnt;
				
				if(nextRow > 0 && nextRow < row + 1 && nextCol > 0 && nextCol < col + 1) {
					if(m[nextRow][nextCol] == WAY) {							// 다음 경로가 길인 경우
						if(isVisited[nextRow][nextCol][current.bCnt] == 0) {		// 현재까지 벽을 부순 횟수에 해당하는 맵에서 방문하지 않은 경로
							isVisited[nextRow][nextCol][current.bCnt] = isVisited[current.row][current.col][current.bCnt] + 1;
							
							q.offer(new Point(nextRow, nextCol, current.bCnt));
						}
					}
					else {				// 벽을 부숴야 하는 경우
						nextBCnt += 1;	// 벽을 부수고
						
						if(nextBCnt < k + 1) {			// 벽을 부순 횟수가 가능 횟수보다 적거나 같은 경우				
							if(isVisited[nextRow][nextCol][nextBCnt] == 0) {		// 추가로 부순 횟수에 해당하는 배열이 아직 미 방문 상태이면
								isVisited[nextRow][nextCol][nextBCnt] = isVisited[current.row][current.col][current.bCnt] + 1;
								
								q.offer(new Point(nextRow, nextCol, nextBCnt));
							}
						}
					}
				}
			}
		}
	}
}
