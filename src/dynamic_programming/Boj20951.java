package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20951번: 유아와 곰두리차
 *
 * @see https://www.acmicpc.net/problem/29051
 *
 */
public class Boj20951 {

    private static long[][] dp;
    private static ArrayList<Integer>[] tree;

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        dp = new long[N + 1][8];
        for(int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
            Arrays.fill(dp[i], -1);
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        long result = 0;

        for(int i = 1; i <= N; i++) {
            result = modulation(result, recursion(i, 7));
        }

        System.out.println(result);
    }

    /**
     *
     * Dynamic Programming recursion
     *
     * line 72: dp[next][count - 1] -> The path until next, length count - 1
     *
     * @param current
     * @param count
     * @return
     */
    private static long recursion(int current, int count) {
        if(count == 0) return 1L;
        if(dp[current][count] != -1) return dp[current][count];

        long cost = 0;
        dp[current][count] = 0;

        for(int next: tree[current]) {
            if(count < 1) continue;
            dp[next][count - 1] = modulation(recursion(next, count - 1), dp[current][count]);
            cost = modulation(dp[next][count - 1], cost);
        }

        return dp[current][count] = cost;
    }

    private static long modulation(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }
}