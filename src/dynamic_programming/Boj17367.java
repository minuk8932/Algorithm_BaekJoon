package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 17367번: 공교육 도박
 *
 * @see https://www.acmicpc.net/problem/17367/
 *
 */
public class Boj17367 {
    private static double[][][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        double result = 0;
        dp = new double[6][6][6][N];

        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
                for(int k = 0; k < 6; k++) {                        // considering just 3 result recently
                    result += recursion(i, j, k, N - 1);
                }
            }
        }

        System.out.println(result / 36.0);          // expectation
    }

    private static double recursion(int first, int second, int third, int count) {
        if(count == 1) return 0;

        if (dp[first][second][third][count] != 0) return dp[first][second][third][count];
        double result = 0;
        double cost = diceCost(first + 1, second + 1, third + 1);

        for (int next = 0; next < 6; next++) {
            result += recursion(second, third, next, count - 1);
        }

        return dp[first][second][third][count] = Math.max(cost, result) / 6.0;      // save max cost
    }

    private static int diceCost (int x, int y, int z) {
        if(x == y && y == z) return 10_000 + x * 1_000;
        else if(x == y) return 1_000 + x * 100;
        else if(z == y) return 1_000 + y * 100;
        else if(x == z) return 1_000 + z * 100;
        else return Math.max(x, Math.max(y, z)) * 100;
    }
}
