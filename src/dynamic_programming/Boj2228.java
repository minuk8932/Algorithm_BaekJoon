package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2228번: 구간 나누기
 *
 * @see https://www.acmicpc.net/problem/2228
 *
 */
public class Boj2228 {

    private static int[] arr;
    private static int[] prefix;
    private static int[][] dp;

    private static final int INF = -1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][M + 1];
        prefix = new int[N + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            prefix[i] = prefix[i - 1] + arr[i];
        }

        System.out.println(recursion(N, M));
    }

    /**
     *
     * Recursion
     *
     * line 61: find next sector & sector sum
     *
     * @param n
     * @param m
     * @return
     */
    private static int recursion(int n, int m) {
        if (m == 0) return 0;
        if (n <= 0) return INF;

        if (dp[n][m] != -1) return dp[n][m];
        int result = recursion(n - 1, m);

        for (int i = 1; i <= n; i++) {
            result = Math.max(result, recursion(i - 2, m - 1) + prefix[n] - prefix[i - 1]);
        }

        return dp[n][m] = result;
    }
}
