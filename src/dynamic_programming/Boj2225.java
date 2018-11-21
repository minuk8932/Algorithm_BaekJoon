package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2225번: 합분해
 *
 *	@see https://www.acmicpc.net/problem/2225/
 *
 */
public class Boj2225 {
	private static final int MOD = 1_000_000_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(bottomUp(N, K));		// 결과 출력
	}
	
	private static int bottomUp(int n, int k) {
		int[][] dp = new int[n + 1][k + 1];
		
		for(int i = 1; i < k + 1; i++) {		// 1을 만드는 경우의 수: k에 따라 0만 하나씩 붙여주면됨
			dp[1][i] = i;
		}
		
		for(int i = 1; i < n + 1; i++) {		// n을 1개의 수로 만드는 경우의 수: n -> 한가지
			dp[i][1] = 1;
		}
		
		for(int i = 2; i < n + 1; i++) {
			for(int j = 2; j < k + 1; j++) {
				dp[i][j] = (dp[i - 1][j] % MOD) + (dp[i][j - 1] % MOD);
			}
		}
		
		return dp[n][k] % MOD;
	}
}
