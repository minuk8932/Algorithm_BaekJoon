package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 14651번: 걷다보니 신천역 삼 Large
 *
 *	@see https://www.acmicpc.net/problem/14651/
 *
 */
public class Boj14651 {
	private static long[][] dp;
	private static int N;
	
	private static final int MOD = 1_000_000_009;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1][3];
		
		System.out.println(makeNumber());
	}
	
	private static long makeNumber() {
        if (N == 1) return 0;
        
		dp[2][0] = 0;
		dp[2][1] = dp[2][2] = 1; 			// 12, 21 1가지씩
		
		for (int i = 3; i < N + 1; i++) {
			for (int j = 0; j < 3; j++) {
				dp[i][j] = (dp[i - 1][j] % MOD * 3) % MOD;		// 트리의 가지 수
			}
		}
		
		long total = 0;
		for(int i = 0; i < 3; i++) {
			total = ((total % MOD) + (dp[N][i] % MOD)) % MOD;
		}
		
		return total;
	}
}
