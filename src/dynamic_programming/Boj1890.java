package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1890번: 점프
 *
 *	@see https://www.acmicpc.net/problem/1890/
 *
 */
public class Boj1890 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(jumping(N, map));
	}
	
	private static long jumping(int n, int[][] arr) {
		long[][] dp = new long[n][n];
		dp[0][0] = 1;
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				if(dp[row][col] == 0 || arr[row][col] == 0) continue;
				int nextRow = row + arr[row][col];			// 아래로 점프
				int nextCol = col + arr[row][col];			// 오른쪽 점프
				
				if(nextRow < n) dp[nextRow][col] += dp[row][col];		// 점프 가능한 경우
				if(nextCol < n) dp[row][nextCol] += dp[row][col];
			}
		}
		
		return dp[n - 1][n - 1];
	}
}
