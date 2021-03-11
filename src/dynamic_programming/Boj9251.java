package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author exponential-e
 * 백준 9251번 : LCS
 *
 * @see https://www.acmicpc.net/problem/9251
 *
 */
public class Boj9251 {

	private static char[] src, snk;
	private static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		src = br.readLine().toCharArray();
		snk = br.readLine().toCharArray();

		dp = new int[src.length + 1][snk.length + 1];
		for(int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(recursion(src.length, snk.length));
	}

	/**
	 *
	 * Recursion
	 *
	 * line 50: src[N] != snk[M], then we need check two cases by decrease N or M
	 * line 53: src[N] == snk[M], then we check just one case, next N & next M
	 *
	 * @param N
	 * @param M
	 * @return
	 */
	private static int recursion(int N, int M) {
		if(N == 0 || M == 0) return 0;

		int result = dp[N][M];
		if(result != -1) return result;

		if(src[N - 1] != snk[M - 1]) {
			result = Math.max(recursion(N, M - 1), recursion(N - 1, M));
		}
		else {
			result = recursion(N - 1, M - 1) + 1;
		}

		return dp[N][M] = result;
	}
}
