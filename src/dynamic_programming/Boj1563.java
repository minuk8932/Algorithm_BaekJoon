package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 1563번: 개근상
 *
 * @see https://www.acmicpc.net/problem/1563
 *
 */
public class Boj1563 {

    private static int[][][] dp;
    private static int MOD = 1_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1][4];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(recursion(N, 0, 0));
    }

    /**
     *
     * Recursion
     *
     * line 48 ~ 50: Sequence -> absent, late, attend
     *
     * @param day
     * @param late
     * @param absent
     * @return
     */
    private static int recursion(int day, int late, int absent) {
        if(late >= 2 || absent == 3) return 0;
        if(day == 0) return 1;

        if(dp[day][late][absent] != -1) return dp[day][late][absent];

        int result = recursion(day - 1, late, absent + 1);
        result = modulation(result, recursion(day - 1, late + 1, 0));
        result = modulation(result, recursion(day - 1, late, 0));

        return dp[day][late][absent] = result;
    }

    private static int modulation(int a, int b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }
}
