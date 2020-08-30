package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 14852번: 타일 채우기 3
 *
 * @see https://www.acmicpc.net/problem/14852
 *
 */
public class boj14852 {
    private static final int MOD = 1_000_000_007;
    private static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new long[2][N + 1];

        System.out.println(tiling(N));
    }

    private static long tiling(int n) {
        dp[0][1] = 2;
        if(n == 1) return dp[0][n];

        dp[0][2] = 7; dp[1][2] = 1;

        for(int i = 3; i <= n; i++) {                           // meemoization
            dp[1][i] = modulation(dp[1][i - 1], dp[0][i - 3]);
            dp[0][i] = modulation(modulation(dp[0][i - 1] * 2, dp[0][i - 2] * 3), dp[1][i] * 2);
        }

        return dp[0][n];
    }

    private static long modulation(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }
}
