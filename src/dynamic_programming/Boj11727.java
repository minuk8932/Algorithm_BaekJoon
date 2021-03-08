package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author exponential-e
 *	백준 11727번 : nx2 타일링 2
 *	
 *	@see https://www.acmicpc.net/problem/11727/
 *
 */

public class Boj11727 {
	private static final int MOD = 10_007;
	private static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		dp = new int[N + 1];
		Arrays.fill(dp, -1);
		System.out.println(recursion(N));
	}

	/**
	 *
	 * recursion
	 *
	 * line 43: if size >= 2, then can fill the tile 2 cases
	 *
	 * @param n
	 * @return memoization
	 */
	private static int recursion(int n) {
		if (n == 0) return 1;
		if (n == 1) return 1;
		if (dp[n] != -1) return dp[n];

		int result = recursion(n - 1);
		if (n >= 2) result = modulation(result, recursion(n - 2) * 2);

		return dp[n] = result;
	}

	private static int modulation(int a, int b) {
		return ((a % MOD) + (b % MOD)) % MOD;
	}
}
