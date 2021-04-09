package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 1562번: 계단 수
 *
 * @see https://www.acmicpc.net/problem/1562
 *
 */
public class Boj1562 {

    private static final long MOD = 1_000_000_000L;
    private static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new long[10][1024][N + 1];
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 1024; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        long result = 0;
        for(int i = 1; i <= 9; i++) {
            result = modulation(recursion(N, i, 1 << i), result);
        }

        System.out.println(result);
    }

    /**
     *
     * Recursion
     *
     * line 52: bits all fulfillment ? 1
     * line 59: make stair number
     *
     * @param n
     * @param current
     * @param mask
     * @return
     */
    private static long recursion(int n, int current, int mask) {
        if(current < 0 || current >= 10) return 0;
        if(n <= 0) return 0;
        if(n == 1) return mask == 1023 ? 1: 0;

        if(dp[current][mask][n] != -1) return dp[current][mask][n];
        long result = 0;
        int right = current + 1;
        int left = current - 1;

        result = modulation(result, modulation(recursion(n - 1, right, mask | (1 << right))
                    , recursion(n - 1, left, mask | (1 << left))));

        return dp[current][mask][n] = result;
    }

    private static long modulation(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }
}
