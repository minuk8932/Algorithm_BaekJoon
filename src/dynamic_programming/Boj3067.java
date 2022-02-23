package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3067번: Coins
 *
 * @see https://www.acmicpc.net/problem/3067
 *
 */
public class Boj3067 {

    private static int[][] dp;
    private static int[] coins;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            coins = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            dp = new int[N + 1][M + 1];
            for(int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], -1);
            }

            sb.append(recursion(N, M)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Recursion
     *
     * line 65: pass
     * line 66: select current repeatedly
     *
     * @param current
     * @param cost
     * @return
     */
    private static int recursion(int current, int cost) {
        if(cost == 0) return 1;
        if(cost < 0 || current == 0) return 0;

        if(dp[current][cost] != -1) return dp[current][cost];

        int answer = recursion(current - 1, cost);
        if(cost >= coins[current]) answer += recursion(current, cost - coins[current]);

        return dp[current][cost] = answer;
    }
}
