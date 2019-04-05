package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14430번: 자원 캐기
 *
 *	@see https://www.acmicpc.net/problem/14430/
 *
 */
public class Boj14430 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		
		System.out.println(explore(N, M, arr));
	}
	
	private static int explore(int n, int m, int[][] arr) {
		int[][] dp = new int[n][m];
		dp[0][0] = arr[0][0];
		
		for(int i = 1; i < m; i++) {
			dp[0][i] = dp[0][i - 1] + arr[0][i];
		}
		
		for(int i = 1; i < n; i++) {
			dp[i][0] = dp[i - 1][0] + arr[i][0];
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < m; j++) {			// 현재 위치에 도달 할 수 있는 방향 중 자원을 최대로 캔 방향 + 현재 위치의 자원
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
			}
		}
		
		return dp[n - 1][m - 1];
	}
}
