package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 11057번: 오르막 수
 *
 * @see https://www.acmicpc.net/problem/11057
 *
 */
public class Boj11057 {

	private static final int MOD = 10_007;
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
	 * line 47 ~ 49: distinct each ciphers, 1 & others
	 *
	 * @param n
	 * @param prev
	 * @return memoization
	 */
	private static int recursion(int n, int prev) {
		if(n == 0) return 1;
		if(dp[n][idx(prev)] != -1) return dp[n][idx(prev)];

		int result = 0;

		for(int cur = idx(prev) % 10; cur < 10; cur++) {
			result = modulation(result, recursion(n - 1, cur));
		}

		return dp[n][idx(prev)] = result;
	}

	private static int idx(int p) {
		return p == -1 ? 10: p;
	}

	private static int modulation(int a, int b) {
		return ((a % MOD) + (b % MOD)) % MOD;
	}
}
