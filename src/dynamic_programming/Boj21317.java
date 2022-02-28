package dynamic_programming;

import common.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 21317번: 징검다리 건너기
 *
 * @see https://www.acmicpc.net/problem/21317
 *
 */
public class Boj21317 {

    private static int N;
    private static int K;
    private static int[][] dp;

    private static Pair[] jumps;

    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N][2];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        jumps = new Pair[N];
        for(int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            jumps[i] = new Pair.Builder(f, s).build();
        }

        K = Integer.parseInt(br.readLine());
        System.out.println(recursion(1, 0));
    }

    private static int recursion(int current, int jump) {
        if(current > N) return INF;
        if(current == N) return 0;

        if(dp[current][jump] != -1) return dp[current][jump];
        int answer = recursion(current + 1, jump) + jumps[current].getFirst();
        answer = Math.min(answer, recursion(current + 2, jump) + jumps[current].getSecond());

        if(jump == 0)
            answer = Math.min(answer, recursion(current + 3, 1) + K);

        return dp[current][jump] = answer;
    }
}
