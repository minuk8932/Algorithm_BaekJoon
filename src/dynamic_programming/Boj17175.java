package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 17175번: 피보나치는 지겨웡~
 *
 *	@see https://www.acmicpc.net/problem/17175/
 *
 */
public class Boj17175 {
	private static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(finbonacciCalled(N));
	}
	
	private static int finbonacciCalled(int n) {
		int[] dp = new int[n + 1];
		if(n == 0 || n == 1) return 1;
		
		dp[0] = dp[1] = 1;
		
		for(int i = 2; i < n + 1; i++) {				// 호출 횟수
			dp[i] = ((dp[i - 1] % MOD) + (dp[i - 2] % MOD) + 1) % MOD;
		}
		
		return dp[n];
	}
}
