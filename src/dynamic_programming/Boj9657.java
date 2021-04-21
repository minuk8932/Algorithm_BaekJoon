package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 9657번: 돌 게임3
 *
 * @see https://www.acmicpc.net/problem/9657
 *
 */
public class Boj9657 {

    private static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        System.out.println(recursion(N) == 1 ? "SK": "CY");
    }

    private static int recursion(int n) {
        if(n <= 0 || n == 2) return 0;
        if(n == 1 || n == 3 || n == 4) return 1;

        if(dp[n] != -1) return dp[n];
        int result = recursion(n - 1) + recursion(n - 3) + recursion(n - 4);

        if(result < 3) return dp[n] = 1;
        return dp[n] = 0;
    }
}
