package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10942번: 팰린드롬?
 *
 * @see https://www.acmicpc.net/problem/10942
 *
 */
public class Boj10942 {

    private static int N;
    private static int[] number;
    private static int[][] dp;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        number = new int[N + 1];
        dp = new int[N + 1][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);

            if(i == 0) continue;
            number[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            sb.append(recursion(S, E)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Recursion
     *
     * line 65: length 1, palindrome
     * line 66: compare index 1 diff
     * line 68: find next palindrome
     *
     * @param from
     * @param to
     * @return
     */
    private static int recursion(int from, int to) {
        if (dp[from][to] != -1) return dp[from][to];

        if (from == to) return dp[from][to] = 1;
        if (to - from == 1) return dp[from][to] = (number[from] == number[to]) ? 1: 0;

        if (number[from] == number[to] && recursion(from + 1, to - 1) == 1) return dp[from][to] = 1;
        return dp[from][to] = 0;
    }
}
