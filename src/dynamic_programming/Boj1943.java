package dynamic_programming;

import common.Pair;
import java.io.*;
import java.util.*;

public class Boj1943 {
    private static final int TEST_LOOP = 3;
    private static final String NEW_LINE = "\n";

    private static Pair<Integer>[] coins;
    private static int[][] dp;
    private static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < TEST_LOOP; t++) {
            N = Integer.parseInt(br.readLine());
            coins = new Pair[N];

            int total = 0;
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int value = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());

                coins[i] = new Pair.Builder<>(value, count)
                    .build();
                total += value * count;
            }

            if (total % 2 != 0) {
                sb.append(0).append(NEW_LINE);
                continue;
            }

            int max = total >> 1;
            dp = new int[N + 1][max + 1];
            for (int[] ints : dp) {
                Arrays.fill(ints, -1);
            }

            sb.append(recursion(0, max)).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static int recursion(int idx, int cost) {
        if (cost < 0) return 0;
        if (idx == N) return cost == 0 ? 1: 0;

        if (dp[idx][cost] != -1) return dp[idx][cost];

        int answer = 0;
        for(int i = 0; i <= coins[idx].getSecond(); i++) {
            answer = Math.max(answer, recursion(idx + 1, cost - coins[idx].getFirst() * i));
        }

        return dp[idx][cost] = answer;
    }
}
