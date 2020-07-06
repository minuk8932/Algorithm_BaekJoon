package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2533번: 사회망 서비스(SNS)
 *
 * @see https://www.acmicpc.net/problem/2533/
 *
 */
public class Boj2533 {
    private static ArrayList<Integer>[] tree;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        dp = new int[N + 1][2];
        for(int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
            Arrays.fill(dp[i], -1);
        }

        int loop = N - 1;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        // then except max from all
        System.out.println(N - Math.max(recursion(0, 1, 0), recursion(0, 1, 1)));
    }

    private static int recursion(int prev, int current, int flag) {
        if(dp[current][flag] != -1) return dp[current][flag];
        dp[current][flag] = flag == 1 ? 1: 0;
        int result = dp[current][flag];

        for(int next: tree[current]) {
            if(next == prev) continue;

            int none = recursion(current, next, 0);

            if(flag == 0) result += Math.max(none, recursion(current, next, 1));        // find max coverage
            else result += none;
        }

        return dp[current][flag] = result;
    }
}
