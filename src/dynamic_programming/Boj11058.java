package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 11058번: 크리 보드
 *
 * @see https://www.acmicpc.net/problem/11058
 *
 */
public class Boj11058 {

    private static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new long[N + 1];
        Arrays.fill(dp, -1);
        System.out.println(recursion(N));
    }

    private static long recursion(int n) {
        if(n <= 6) return n;

        if(dp[n] != -1) return dp[n];
        long result = Math.max(recursion(n - 3) * 2, Math.max(recursion(n - 4) * 3, recursion(n - 5) * 4));

        return dp[n] = result;
    }
}
