package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 1720번: 타일 코드
 *
 * @see https://www.acmicpc.net/problem/1720/
 *
 */
public class Boj1720 {
	private static long[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		dp = new long[N + 1];
		recursion(N);

		long res = N % 2 == 0 ? dp[N / 2] + 2 * dp[N / 2 - 1]: dp[(N - 1) / 2];		// divide odd, even
		System.out.println(N == 1 ? 1: (res + dp[N]) / 2);
	}
	
	private static long recursion(int n) {
		if(dp[n] > 0) return dp[n];
		if(n == 1 || n == 0) return dp[n] = 1;
		
		return dp[n] = recursion(n - 1) + recursion(n - 2) * 2;				// filling tiles
	}
}
