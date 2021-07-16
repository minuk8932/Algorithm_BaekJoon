package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22115번: 창영이와 커피
 *
 * @see https://www.acmicpc.net/problem/22115
 *
 */
public class Boj22115 {

    private static int[] coffee;
    private static int[][] dp;
    private static int N;

    private static final int INF = 10_000_000;

    private static final BinaryOperator<Integer> SUB = (x, y) -> x - y;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        coffee = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            coffee[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][K + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        final Function<Integer, Integer> VALIDATOR = x -> x == INF ? -1: x;
        System.out.println(VALIDATOR.apply(recursion(N, K)));
    }

    /**
     *
     * Recursion
     *
     * line 62: drink ith coffee or not, knapsack
     *
     * @param current
     * @param cost
     * @return
     */
    private static int recursion(int current, int cost) {
        if(cost == 0) return 0;
        if(cost < 0 || current <= 0) return INF;

        if(dp[current][cost] != -1) return dp[current][cost];

        int result = Math.min(recursion(current - 1, cost)
                , recursion(current - 1, SUB.apply(cost, coffee[current - 1])) + 1);

        return dp[current][cost] = result;
    }
}
