package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1520번 : 내리막길	
 *
 *	@see https://www.acmicpc.net/problem/1520
 *
 */
public class Boj1520 {
	private static final String SPACE = " ";
	
	private static final int UNVISITED = -1;
	private static final int MAX = 501;
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	private static int M = 0;
	private static int N = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int[][] path = new int[MAX][MAX];
		
		for(int i = 1; i < M + 1; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 1; j < N + 1; j++){
				path[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		int[][] dp = new int[MAX][MAX];
		
		for (int[] isVisited : dp) {					// 방문 여부 확인 할 배열로 dp 선언 후
			Arrays.fill(isVisited, UNVISITED);		// 데이터 입력은 0 이상이므로, -1로 방문하지 않은 것으로 모두 채워줌
		}
		
		dp[M][N] = 1;										// 목적지 표시
		
		System.out.println(dfs(path, dp, 1, 1));	// dfs를 이용한 dp 함수 호출 후 반환 값을 출력
	}
	
	/**
	 * 
	 * 조건에 따라 탐색을 수행하는 dfs 메소드
	 * 
	 * 	@param path
	 * 	@param dp
	 * 	@param row
	 * 	@param col
	 * 	@return
	 */
	private static int dfs(int[][] path, int[][] dp, int row, int col){		
		if(row == M && col == N){			// 종점일 경우 1 반환
			return 1;
		}
		
		if(dp[row][col] > UNVISITED){	// 만약 이미 방문한 지점이라면 그 위치 값 반환
			return dp[row][col];
		}
		
		if(dp[row][col] == UNVISITED){	// 아직 방문하지 않은 점이라면, 0으로 채워서 방문한것으로 표시
			dp[row][col] = 0;
		}
		
		for(final int[] DIRECTION : DIRECTIONS){
			int nextRow = row + DIRECTION[ROW];
			int nextCol = col + DIRECTION[COL];
			
			if(nextRow > 0 && nextRow < M + 1 && nextCol > 0 && nextCol < N + 1 && path[row][col] > path[nextRow][nextCol]){
				dp[row][col] += dfs(path, dp, nextRow, nextCol);	// 1, 1에 경로의 갯수를 하나씩 추가
			}
		}
		
		return dp[row][col];	// 1, 1에서의 dp 배열 값을 반환
	}
}
