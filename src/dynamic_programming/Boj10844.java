package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author exponential-e
 *	백준 10844번 : 쉬운계단수
 *
 *	@see https://www.acmicpc.net/problem/10844/
 *
 */
public class Boj10844 {
	private static final int MOD = 1_000_000_000;
	private static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		dp = new int[N + 1][11];
		for(int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(recursion(N, -1));
	}

	/**
	 *
	 * recursion
	 *
	 * line 46 ~ 54: first value fix by 1 ~ 9 & next cases.
	 *
	 * @param n
	 * @param prev
	 * @return memoization
	 */
	private static int recursion(int n, int prev) {
		if(n == 0) return 1;
		if(dp[n][prev == -1 ? 10: prev] != -1) return dp[n][prev];

		int result = 0;

		if(prev == -1) {
			for(int i = 1; i < 10; i++) {
				result = modulation(result, recursion(n - 1, i));
			}
		}
		else {
			if(prev != 9) result = modulation(result, recursion(n - 1, prev + 1));
			if(prev != 0) result = modulation(result, recursion(n - 1, prev - 1));
		}

		return dp[n][prev == -1 ? 10: prev] = result;
	}

	private static int modulation(int a, int b) {
		return ((a % MOD) + (b % MOD)) % MOD;
	}
}
