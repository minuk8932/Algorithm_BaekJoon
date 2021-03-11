package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 
 * 	@author exponential-e
 *	백준 9252번 : LCS2
 *
 *	@see https://www.acmicpc.net/problem/9252
 *
 */
public class Boj9252 {

	private static ArrayDeque<Character> stack = new ArrayDeque<>();

	private static char[] src, snk;
	private static int[][] dp;

	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		src = br.readLine().toCharArray();
		snk = br.readLine().toCharArray();

		dp = new int[src.length + 1][snk.length + 1];
		for(int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(recursion(src.length, snk.length)).append(NEW_LINE);

		LCSwriter(src.length, snk.length);

		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		System.out.println(sb.toString());
	}

	/**
	 *
	 * LCS Writer
	 *
	 * line 63 ~ 67: find the longest LCS, and targetted
	 *
	 * @param N
	 * @param M
	 */
	private static void LCSwriter(int N, int M) {
		if(N == 0 || M == 0) return;

		if(src[N - 1] == snk[M - 1]) {
			stack.push(src[N - 1]);
			LCSwriter(N - 1, M - 1);
		}
		else {
			int left = recursion(N - 1, M);
			int right = recursion(N, M - 1);

			if(left > right) LCSwriter(N - 1, M);
			else LCSwriter(N, M - 1);
		}
	}

	/**
	 *
	 * Recursion
	 *
	 * line 79, 82: ref. Boj9251
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
