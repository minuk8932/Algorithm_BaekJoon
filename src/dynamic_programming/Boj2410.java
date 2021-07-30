package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 2410번: 2의 멱수의 합
 *
 * @see https://www.acmicpc.net/problem/2410
 *
 */
public class Boj2410 {

    private static final long MOD = 1_000_000_000L;
    private static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new long[N + 1][21];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(recursion(N, 0, (int) MOD));
    }

    private static long recursion(int n, int pow, int prev) {
        if(n == 0) return 1;
        if(n < 0) return 0;

        if(dp[n][pow] != -1) return dp[n][pow];
        long result = 0;

        for(int i = 20; i >= 0; i--) {
            int shift = 1 << i;

            if(shift > n) continue;
            if(prev < shift) continue;

            result = ((result % MOD) + (recursion(n - shift, i, shift) % MOD)) % MOD;
        }

        return dp[n][pow] = result;
    }
}
