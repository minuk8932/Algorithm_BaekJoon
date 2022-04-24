package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 9845번: Housing
 *
 * @see https://www.acmicpc.net/problem/9845
 *
 */
public class Boj9845 {

    private static int[][] dp;
    private static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[101][101];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int start = 5; start <= 100; start++) {
            for (int div = 5; div <= start; div++){
                recursion(start, div);
            }
        }

        System.out.println(dp[n][5]);
    }

    private static int recursion(int current, int division) {
        if (dp[current][division] != -1) return dp[current][division];
        if (current < division) return dp[current][division] = 0;

        dp[current][division] = 1;
        for (int next = division; next <= current; next++){
            dp[current][division] += recursion(current - next, next);
        }

        return dp[current][division];
    }
}
