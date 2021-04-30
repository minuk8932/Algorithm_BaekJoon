package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 10978번: 기숙사 재배정
 *
 * @see https://www.acmicpc.net/problem/10978
 *
 */
public class Boj10978 {

    private static long[] dp = new long[21];
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Arrays.fill(dp, -1);

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(recursion(N)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Recrusion
     *
     * recursion(n - 1) * (n - 1): a -> b and others
     * recursion(n - 2) * (n - 1): a -> b, b -> a and others
     *
     * @param n
     * @return
     */
    private static long recursion(int n) {
        if(n == 1) return 0;
        if(n == 2) return 1;

        if(dp[n] != -1) return dp[n];
        long result = (recursion(n - 1) + recursion(n - 2)) * (n - 1);

        return dp[n] = result;
    }
}
