package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author minchoba
 *	백준 1699번 : 제곱수의 합
 *
 *	@see http://www.acmicpc.net/problem/1699
 *
 */

public class Boj1699 {

	private static int[] dp;
	private static int[] pows;
	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int max = (int) Math.sqrt(N);

		dp = new int[N + 1];
		Arrays.fill(dp, -1);

		pows = new int[max];
		for(int i = 0; i < max; i++) {
			pows[i] = -(i + 1) * (i + 1);
		}

		Arrays.sort(pows);
		System.out.println(recursion(N));
	}

	/**
	 *
	 * recursion
	 *
	 * line 54 ~ 57: add -pows and make n to zero
	 *
	 * @param n
	 * @return
	 */
	private static int recursion(int n) {
		if (n == 0) return 0;

		if (dp[n] != -1) return dp[n];
		int result = INF;

		for(int nxt: pows) {
			if (n < -nxt) continue;
			result = Math.min(result, recursion(n + nxt));
		}

		return dp[n] = result + 1;
	}
}
