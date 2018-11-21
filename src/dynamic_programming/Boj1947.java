package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1947번: 선물 전달
 *
 *	@see https://www.acmicpc.net/problem/1947/
 *
 */
public class Boj1947 {
	private static final int MOD = 1_000_000_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(bottomUp(N));		// 결과 출력
	}
	
	private static long bottomUp(int n) {	
		if(n == 1) return 0;
		if(n == 2) return 1;
		
		long[] dp = new long[n + 1];
		dp[1] = 0;
		dp[2] = 1;
		
		for(int i = 3; i < n + 1; i++) {					// 경우의 수
			dp[i] = (dp[i - 2] * (i - 1)) % MOD + (dp[i - 1] * (i - 1)) % MOD;
		}
		
		return dp[n] % MOD;
	}
}
