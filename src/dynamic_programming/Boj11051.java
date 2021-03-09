package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 11051번: 이항계수 2
 *
 *	@see https://www.acmicpc.net/problem/11052/
 *
 */
public class Boj11051 {

	private static final int MOD = 10_007;
	private static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][K + 1];
		for(int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(combination(N, K));
	}

	/**
	 *
	 * Combination
	 *
	 * line 52: nCr = n-1Cr + n-1Cr-1
	 *
	 * @param n
	 * @param k
	 * @return
	 */
	private static int combination(int n, int k) {
		if(n == k || k == 0) return 1;
		if(k == 1) return n;
		if(dp[n][k] != -1) return dp[n][k];

		int result = 0;
		if(k > (n >> 1)) k = n - k;

		result = modulation(result, modulation(combination(n - 1, k), combination(n - 1, k - 1)));
		return dp[n][k] = result;
	}

	private static int modulation(int a, int b) {
		return ((a % MOD) + (b % MOD)) % MOD;
	}
}
