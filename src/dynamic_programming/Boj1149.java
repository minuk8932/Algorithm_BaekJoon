package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1149번: RGB 거리
 *
 *	@see https://www.acmicpc.net/problem/1149/
 *
 */
public class Boj1149 {
	private static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		dp = new int[N][3];
		
		int[][] home = new int[N][3];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 3; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(memo(N, home));
	}
	
	private static int memo(int n, int[][] home) {
		dp[n - 1][0] = home[n - 1][0];								// colored blue, red, green the last home
		dp[n - 1][1] = home[n - 1][1];
		dp[n - 1][2] = home[n - 1][2];
		
		for(int i = n - 2; i >= 0; i--) {							// get min each case
			dp[i][0] = Math.min(dp[i + 1][1], dp[i + 1][2]) + home[i][0];
			dp[i][1] = Math.min(dp[i + 1][0], dp[i + 1][2]) + home[i][1];
			dp[i][2] = Math.min(dp[i + 1][1], dp[i + 1][0]) + home[i][2];
		}
		
		return Math.min(dp[0][0], Math.min(dp[0][1], dp[0][2]));	// total
	}
}