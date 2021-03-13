package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1023번: 괄호 문자열
 *
 * @see https://www.acmicpc.net/problem/1023
 *
 */
public class Boj1023 {

    private static long[][][] dp;
    private static int N;
    private static long K;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        System.out.println(makeParenthesis());
    }

    private static String makeParenthesis() {
        dp = new long[N + 1][(N << 1) + 1][2];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                dp[i][j][0] = dp[i][j][1] = -1L;
            }
        }

        long size = recursion(N, 0, false);
        if(size <= K) return "-1";

        printer(N, 0, false, K + 1);
        return sb.toString();
    }

    /**
     *
     * Printer
     *
     * line 62: last parenthesis, 1 == ( or )
     *
     * @param n
     * @param opened
     * @param flag
     * @param k
     */
    private static void printer(int n, int opened, boolean flag, long k) {
        if(n == 0) return;

        long length = recursion(n - 1, opened + 1, flag);
        if(length >= k) {
            if(n == 1 && k == 2) sb.append(')');
            else sb.append('(');

            printer(n - 1, opened + 1, flag, k);
        }
        else {
            sb.append(')');
            printer(n - 1, opened - 1, flag || opened <= 0, k - length);
        }
    }

    /**
     *
     * Recursion
     *
     * line 90: get next parenthesis, and opened <= 0 then it is !!parenthesis
     *
     * @param n
     * @param opened
     * @param flag
     * @return
     */
    private static long recursion(int n, int opened, boolean flag){
        if(n == 0) return opened != 0 || flag ? 1L: 0;

        long result = dp[n][N + opened][flag ? 1: 0];
        if(result != -1) return result;

        result = recursion(n - 1, opened + 1, flag)
                + recursion(n - 1, opened - 1, opened <= 0 || flag);

        return dp[n][opened + N][flag ? 1: 0] = result;
    }
}
