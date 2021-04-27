package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 9658번: 돌 게임 4
 *
 * @see https://www.acmicpc.net/problem/9658
 *
 */
public class Boj9658 {

    private static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        System.out.println(recusrion(N, true) == 1 ? "SK": "CY");
    }

    private static int recusrion(int n, boolean flag) {
        if(n == 1 || n == 3) return 0;
        if(n == 2 || n == 4 || n == 5) return 1;

        if(dp[n] != -1) return dp[n];
        dp[n] = recusrion(n - 1, !flag) + recusrion(n - 3, !flag) + recusrion(n - 4, !flag);

        return dp[n] = (dp[n] < 3? 1: 0);
    }
}
