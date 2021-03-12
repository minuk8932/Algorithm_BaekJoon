package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1256번: 사전
 *
 * @see https://www.acmicpc.net/problem/1256
 *
 */
public class Boj1256 {

    private static StringBuilder sb = new StringBuilder();
    private static long[][] dp = new long[101][101];
    private static int N, M;
    private static long K;
    private static final long INF = 1_000_000_001L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        System.out.println(dictionary());
    }

    private static String dictionary() {
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        long total = recursion(N, M);
        if(total < K) return "-1";

        print(N, M, K - 1);
        return sb.toString();
    }

    /**
     *
     * Print
     *
     * line 72: find current size
     * line 74 ~ 81: search word by remained size
     *
     * @param n
     * @param r
     * @param k
     */
    private static void print(int n, int r, long k) {
        if(n == 0) {
            for(int i = 0; i < r; i++) {
                sb.append('z');
            }

            return;
        }

        if(r == 0) {
            for(int i = 0; i < n; i++) {
                sb.append('a');
            }

            return;
        }

        long idx = recursion(n - 1, r);

        if(k < idx) {
            sb.append('a');
            print(n - 1, r, k);
        }
        else {
            sb.append('z');
            print(n, r - 1, k - idx);
        }
    }

    /**
     *
     * Recursion
     *
     * line 101: combination.
     *
     * @param n
     * @param r
     * @return
     */
    private static long recursion(int n, int r) {
        if(r == 0 || n == 0) return 1L;

        long result = dp[n][r];
        if(result != -1) return result;
        result = INF;

        result = Math.min(recursion(n - 1, r) + recursion(n, r - 1), result);
        return dp[n][r] = result;
    }
}
