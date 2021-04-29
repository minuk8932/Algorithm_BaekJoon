package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 14606번: 피자 (small)
 *
 * @see https://www.acmicpc.net/problem/14606
 *
 */
public class Boj14606 {
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(recursion(N, 0));
    }

    private static int recursion(int n, int m) {
        if(n <= 1) return 0;

        if(dp[n][m] != -1) return dp[n][m];
        int result = 0;

        for(int i = 1; i <= n / 2; i++) {
            result = Math.max(result, recursion(n - i, i) + (i * (n - i)));
        }

        return dp[n][m] = result;
    }
}
