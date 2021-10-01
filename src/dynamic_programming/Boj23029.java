package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 23029번: 시식 코너는 나의 것
 *
 * @see https://www.acmicpc.net/problem/23029
 *
 */
public class Boj23029 {

    private static int[] dp;
    private static int[] food;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        food = new int[N + 1];
        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        for(int i = 1; i <= N; i++) {
            food[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(recursion(N));
    }

    private static int recursion(int n) {
        if (n < 0) return 0;
        if(dp[n] != -1) return dp[n];

        int result = recursion(n - 1);
        result = Math.max(recursion(n - 2) + food[n], result);

        if(n >= 2) result = Math.max(recursion(n - 3) + (food[n] >> 1) + food[n - 1], result);
        return dp[n] = result;
    }
}
