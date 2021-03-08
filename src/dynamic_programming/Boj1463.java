package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author minchoba 
 * 백준 1463번: 1로 만들기
 *
 * @see https://www.acmicpc.net/problem/1463/
 *
 */
public class Boj1463 {
	private static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		dp = new int[X + 1];
		Arrays.fill(dp, -1);

		dp[1] = 0;
		System.out.println(recursion(X));
	}

	/**
	 *
	 * Top-Down
	 *
	 * line 45: x / 3 case
	 * line 46: x / 2 case
	 * line 47: x - 1 case
	 *
	 * @param x
	 * @return current case & memoization
	 */
	private static int recursion(int x) {
		if(x == 1) return 0;

		if(dp[x] != -1) return dp[x];
		int result = Integer.MAX_VALUE;

		if(x % 3 == 0) result = Math.min(recursion(x / 3), result);
		if(x % 2 == 0) result = Math.min(recursion(x >> 1), result);
		if(x >= 1) result = Math.min(recursion(x - 1), result);

		return dp[x] = result + 1;
	}
}
