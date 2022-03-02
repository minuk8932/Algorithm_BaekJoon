package dynamic_programming;

import common.Time;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19622번: 회의실 배정 3
 *
 * @see https://www.acmicpc.net/problem/19622
 *
 */
public class Boj19622 {

    private static int[] dp;
    private static Time[] times;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        times = new Time[N + 1];
        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int capacity = Integer.parseInt(st.nextToken());

            times[i] = new Time.Builder(from, to)
                    .cost(capacity)
                    .build();
        }

        System.out.println(recursion(N));
    }

    private static int recursion(int current) {
        if(current <= 0) return 0;

        if(dp[current] != -1) return dp[current];
        int answer = Math.max(recursion(current - 1)
                , recursion(current - 2) + times[current].getCost());

        return dp[current] = answer;
    }
}
