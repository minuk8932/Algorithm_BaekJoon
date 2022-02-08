package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 2482번: 색상환
 *
 * @see https://www.acmicpc.net/problem/2482
 *
 */
public class Boj2482 {

    private static long[][] dp;
    private static final long MOD = 1_000_000_003L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        dp = new long[N + 1][K + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(recursion(N, K));
    }

    private static long recursion(int current, int k) {
        if (k == 1) return current;
        if (current <= 0 || k > (current >> 1)) return 0;

        if(dp[current][k] != -1) return dp[current][k];
        long answer = (recursion(current - 1, k) % MOD) + (recursion(current - 2, k - 1) % MOD);

        return dp[current][k] = answer % MOD;
    }
}
