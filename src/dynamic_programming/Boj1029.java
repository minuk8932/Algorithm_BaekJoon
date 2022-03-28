package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 1029번: 그림 교환
 *
 * @see https://www.acmicpc.net/problem/1029
 *
 */
public class Boj1029 {

    private static int[][][] dp;
    private static int[][] cost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][1 << (N + 1)][10];
        cost = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++){
            String input = br.readLine();

            for(int j = 1; j <= N; j++) {
                cost[i][j] = input.charAt(j - 1) - '0';
            }
        }

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(recursion(1, 2, 0) + 1);
    }

    private static int recursion(int current, int buyer, int value) {
        if(dp[current][buyer][value] != -1) return dp[current][buyer][value];
        int answer = 0;

        for(int next = 1; next < dp.length; next++) {
            int shift = 1 << next;

            if((buyer & shift) != 0) continue;
            if(cost[current][next] < value) continue;

            answer = Math.max(recursion(next, buyer | shift, cost[current][next]) + 1, answer);
        }

        return dp[current][buyer][value] = answer;
    }
}
