package dynamic_programming;

import common.Time;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19621번: 회의실 배정 2
 *
 * @see https://www.acmicpc.net/problem/19621
 *
 */
public class Boj19621 {

    private static Time<Integer, Integer>[] conference;

    private static final int INF = 1_000_000_000;
    private static int[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        conference = new Time[N + 1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            conference[i] = new Time.Builder(s, e)
                    .cost(a)
                    .build();
        }

        dp = new int[N + 1];
        Arrays.fill(dp, INF);

        System.out.println(recursion(N));
    }

    private static int recursion(int current) {
        if(current <= 0) return 0;

        if(dp[current] != INF) return dp[current];
        int answer = recursion(current - 1);
        answer = Math.max(answer, recursion(current - 2) + conference[current].getCost());

        return dp[current] = answer;
    }
}
