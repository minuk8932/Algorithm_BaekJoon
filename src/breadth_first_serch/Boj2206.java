package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2206번 : 벽 부수고 이동하기
 *
 *	@see https://www.acmicpc.net/problem/2206
 *
 */
public class Boj2206 {
	private static final String SPACE = " ";
	private static final char WAY = '0';
	private static final char BLOCK = '1';
	
	private static final int INF = 1_000_001;
	private static final int NO_WAY = -1;
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	private static int N = 0;
	private static int M = 0;
	
	private static char[][] map = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N + 1][M + 1];
		for(int i = 1; i < N + 1; i++){
			String line = br.readLine();
					
			for(int j = 1; j < M + 1; j++){
				map[i][j] = line.charAt(j - 1);
			}
		}
		
		int[][][] isVisited = new int[N + 1][M + 1][2];
		for(int i = 1 ; i < N + 1; i++){
			for(int j = 1; j < M + 1; j++){
				Arrays.fill(isVisited[i][j], INF);				// 방문 배열에 INF(1_000_001)을 모두 채워줌
			}
		}
		
		breakingBfs(isVisited);								// BFS 알고리즘 실행
		
		// 만약 벽을 부수고 이동 또는 안부수고 이동할때 최종 목적지에 도달하지 못한경우 : -1
		// 그외에는 벽을 부수고 이동하거나, 부수지않고 이동할 때 두 방법 중 목적지에 도달하는데의 최솟값을 출력
		System.out.println(isVisited[N][M][1] == INF && isVisited[N][M][0] == INF ? NO_WAY : Math.min(isVisited[N][M][1], isVisited[N][M][0]));
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점 이너클래스
	 */
	private static class Point{
		int row;
		int col;
		int isBroken;
		
		public Point(int row, int col, int isBroken){
			this.row = row;
			this.col = col;
			this.isBroken = isBroken;			// 아직 벽을 부수지 않았다면 : 0, 부쉈다면 : 1
		}
	}
	
	/**
	 * BFS 알고리즘
	 * @param isVisited : 몇번만에 목적지에 방문 가능한지 수치를 저장 할 배열
	 */
	private static void breakingBfs(int[][][] isVisited){
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(1, 1, 0));										// 시작 정점과 벽을 부수지 않음으로 설정 후 큐에 담아줌
		isVisited[1][1][0] = 1;
		
		while(!q.isEmpty()){
			Point current = q.poll();
			
			for(final int[] DIRECTION : DIRECTIONS){
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int isBreaking = current.isBroken;					// 벽을 이미 부쉈는지, 아직 부수지않고 이동하였는지 체크
				
				if(nextRow > 0 && nextRow < N + 1 && nextCol > 0 && nextCol < M + 1){
					// 다음 경로는 길이있고, 만약 다음 방문 수치 > 현재까지의 방문수치 +1 일때,
					if(map[nextRow][nextCol] == WAY && isVisited[nextRow][nextCol][isBreaking] > isVisited[current.row][current.col][isBreaking] + 1){
						// 둘 중의 최솟값인 현재까지의 방문수치 + 1 값을 넣어줌
						isVisited[nextRow][nextCol][isBreaking] = isVisited[current.row][current.col][isBreaking] + 1;
						q.offer(new Point(nextRow, nextCol, isBreaking));
					}
					// 다음 경로는 벽이면서, 아직까지 벽을 한번도 부수지 않았고, 위의 경우와 같이 다음 방문수치가 더 클경우
					else if(map[nextRow][nextCol] == BLOCK && isBreaking == 0 && isVisited[nextRow][nextCol][isBreaking] > isVisited[current.row][current.col][isBreaking] + 1){
						// 둘 중의 최솟값인 현재까지의 방문수치 + 1 값을 넣어줌
						isVisited[nextRow][nextCol][1] = isVisited[current.row][current.col][0] + 1;
						q.offer(new Point(nextRow, nextCol, 1));
					}
				}
			}
		}
	}
}
