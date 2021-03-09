package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author exponential-e
 *	백준 11055번 : 가장 큰 증가하는 부분 수열
 *
 *	@see https://www.acmicpc.net/problem/11055
 *
 */

public class Boj11055 {

	private static int[][] dp;
	private static int[] seq;
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		seq = new int[N + 1];
		dp = new int[N + 1][1_002];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(recursion(N, 1_001));
	}

	/**
	 *
	 * Recursion
	 *
	 * line 55: not add next
	 * line 56: add next
	 *
	 * @param n
	 * @param prev
	 * @return
	 */
	private static int recursion(int n, int prev) {
		if(n < 0) return 0;
		if(dp[n][prev] != -1) return dp[n][prev];

		int result = recursion(n - 1, prev);
		if(prev == -1 || prev > seq[n]) result = Math.max(result, recursion(n - 1, seq[n]) + seq[n]);

		return dp[n][prev] = result;
	}
}
