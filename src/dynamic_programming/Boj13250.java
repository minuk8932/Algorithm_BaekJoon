package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 13250번: 주사위 게임
 *
 * @see https://www.acmicpc.net/problem/13250
 *
 */
public class Boj13250 {

    private static final double TERMINATE = 0.0;
    private static final double DICE_RATE = 6.0;
    private static double[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new double[1_000_001];
        Arrays.fill(dp, -1);

        System.out.println(recursion(N));
    }

    private static double recursion(int current) {
        if (current <= 0) return TERMINATE;

        if(dp[current] >= TERMINATE) return dp[current];
        double answer = 0;

        for (int next = 1; next <= 6; next++) {
            answer += (recursion(current - next) + 1) / DICE_RATE;
        }

        return dp[current] = answer;
    }
}
