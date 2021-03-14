package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2291번: Sequence
 *
 * @see https://www.acmicpc.net/problem/2291
 *
 */
public class boj2291 {

    private static long[][][] dp;
    private static StringBuilder sb = new StringBuilder();
    private static int N, M;

    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        dp = new long[N + 1][M + 1][M + 1];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1L);
            }
        }

        long result = recursion(N, M, 1);
        printer(N, M, result - K, 1);
        System.out.println(sb.toString());
    }

    /**
     *
     * Printer
     *
     * line 58 ~ 64: pivot is bigger than k, then next is elements else k - pivot
     *
     * @param n
     * @param m
     * @param k
     * @param current
     */
    private static void printer(int n, int m, long k, int current) {
        if (n == 0) return;

        for (int next = m; next >= current; next--) {
            long pivot = recursion(n - 1, m - next, next);

            if (k < pivot) {
                sb.append(next).append(SPACE);
                printer(n - 1, m - next, k, next);
                return;
            }

            k -= pivot;
        }
    }

    /**
     *
     * Recursion
     *
     * line 88: make sequence by value decrease
     *
     * @param n
     * @param m
     * @param current
     * @return
     */
    private static long recursion(int n, int m, int current) {
        if(n == 0) return m == 0 ? 1: 0;
        if(m <= 0) return 0;

        long result = dp[n][m][current];
        if(result != -1) return result;
        result = 0;

        for(int next = m; next >= current; next--) {
            result += recursion(n - 1, m - next, next);
        }

        return dp[n][m][current] = result;
    }
}
