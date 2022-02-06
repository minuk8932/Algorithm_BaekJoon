package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14728번: 벼락치기
 *
 * @see https://www.acmicpc.net/problem/14728
 *
 */
public class Boj14728 {

    private static int[] times;
    private static int[] scores;
    private static int[][] dp;

    private static final int INF = -1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][T + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        times = new int[N + 1];
        scores = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            scores[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(recursion(N, T));
    }

    /**
     *
     * Recursion: knapsack
     *
     * line 61: do or nothing
     *
     * @param current
     * @param time
     * @return
     */
    private static int recursion(int current, int time) {
        if(time < 0) return INF;
        if(current == 0) return 0;

        if(dp[current][time] != -1) return dp[current][time];

        int answer = Math.max(recursion(current - 1, time)
                , recursion(current - 1, time - times[current]) + scores[current]);

        return dp[current][time] = answer;
    }
}
