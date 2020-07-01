package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11062번: 카드 게임
 *
 * @see https://www.acmicpc.net/problem/11062
 *
 */
public class Boj11062 {
	private static final String NEW_LINE = "\n";

	private static int[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] cards = new int[N];
			dp = new int[2][N][N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}

			sb.append(recursion(0, N - 1, false, cards)).append(NEW_LINE);
		}

		System.out.println(sb.toString());
	}

	private static int recursion(int left, int right, boolean flag, int[] arr) {
		if (left > right) return 0;

		if (dp[flag ? 1: 0][left][right] != 0) return dp[flag ? 1: 0][left][right];

		if (flag) {
			dp[1][left][right] = Math.min(recursion(left, right - 1, false, arr)
					, recursion(left + 1, right, false, arr));							// another
		}
		else {
			dp[0][left][right] = Math.max(recursion(left, right - 1, true, arr) + arr[right]
					, recursion(left + 1, right, true, arr) + arr[left]);					// player get the card
		}

		return dp[flag ? 1: 0][left][right];
	}
}
