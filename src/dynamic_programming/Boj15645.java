package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15645번: 내려가기 2
 *
 *	@see https://www.acmicpc.net/problem/15645/
 *
 */
public class Boj15645 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][3];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
			map[i][2] = Integer.parseInt(st.nextToken());
		}
		
		getMinMax(N, map);
	}
	
	private static void getMinMax(int n, int[][] arr) {
		int[][][] dp = new int[2][n][3];			// 0 최소, 1 최대
		
		for(int i = 0; i < 3; i++) {
			dp[0][0][i] = dp[1][0][i] = arr[0][i];
		}
		
		for(int i = 1; i < n; i++) {				// 내려가기
			dp[0][i][0] = Math.min(dp[0][i - 1][0], dp[0][i - 1][1]) + arr[i][0];
			dp[0][i][1] = Math.min(dp[0][i - 1][0], Math.min(dp[0][i - 1][1], dp[0][i - 1][2])) + arr[i][1];
			dp[0][i][2] = Math.min(dp[0][i - 1][2], dp[0][i - 1][1]) + arr[i][2];
			
			dp[1][i][0] = Math.max(dp[1][i - 1][0], dp[1][i - 1][1]) + arr[i][0];
			dp[1][i][1] = Math.max(dp[1][i - 1][0], Math.max(dp[1][i - 1][1], dp[1][i - 1][2])) + arr[i][1];
			dp[1][i][2] = Math.max(dp[1][i - 1][2], dp[1][i - 1][1]) + arr[i][2];
		}
		
		System.out.println(Math.max(dp[1][n - 1][0], Math.max(dp[1][n - 1][1], dp[1][n - 1][2]))
				+ " " + Math.min(dp[0][n - 1][0], Math.min(dp[0][n - 1][1], dp[0][n - 1][2])));
	}
}
