package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.BiFunction;

/**
 *
 * @author exponential-e
 * 백준 24464번: 득수 밥 먹이기
 *
 * @see https://www.acmicpc.net/problem/24464
 *
 */
public class Boj24464 {

    private static final int MOD = 1_000_000_007;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[6][N + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(recursion(5, N));
    }

    private static int recursion(int restaurant, int day) {
        if (day == 0) return 1;

        if(dp[restaurant][day] != -1) return dp[restaurant][day];
        int answer;

        if(restaurant == 0) {
            answer = recursion(1, day - 1) % MOD;
            answer = MODULATION.apply(recursion(2, day - 1), answer);
            answer = MODULATION.apply(recursion(3, day - 1), answer);
            answer = MODULATION.apply(recursion(4, day - 1), answer);
        }
        else if(restaurant == 1) {
            answer = recursion(0, day - 1) % MOD;
            answer = MODULATION.apply(recursion(3, day - 1), answer);
            answer = MODULATION.apply(recursion(4, day - 1), answer);
        }
        else if(restaurant == 2) {
            answer = recursion(0, day - 1) % MOD;
            answer = MODULATION.apply(recursion(4, day - 1), answer);
        }
        else if(restaurant == 3) {
            answer = recursion(0, day - 1) % MOD;
            answer = MODULATION.apply(recursion(1, day - 1), answer);
        }
        else if(restaurant == 4) {
            answer = recursion(0, day - 1) % MOD;
            answer = MODULATION.apply(recursion(1, day - 1), answer);
            answer = MODULATION.apply(recursion(2, day - 1), answer);
        }
        else {
            answer = recursion(0, day - 1) % MOD;
            answer = MODULATION.apply(recursion(1, day - 1), answer);
            answer = MODULATION.apply(recursion(2, day - 1), answer);
            answer = MODULATION.apply(recursion(3, day - 1), answer);
            answer = MODULATION.apply(recursion(4, day - 1), answer);
        }

        return dp[restaurant][day] = answer;
    }

    private static final BiFunction<Integer, Integer, Integer> MODULATION = (a, b) ->
            ((a % MOD) + (b % MOD)) % MOD;
}
