import common.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj21317 {

    private static int K;
    private static int[][] dp;

    private static Pair[] jumps;

    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][4];

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        jumps = new Pair[N + 1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            jumps[i] = new Pair.Builder(f, s).build();
        }

        K = Integer.parseInt(br.readLine());
        System.out.println(recursion(N, 0));
    }

    private static int recursion(int current, int jump) {
        if(current < 0) return INF;
        if(current == 0) return 0;

        if(dp[current][jump] != -1) return dp[current][jump];
        int answer = INF;

        answer = Math.min(answer, recursion(current - 1, 1) + jumps[current].getFirst());
        answer = Math.min(answer, recursion(current - 2, 2) + jumps[current].getSecond());
        answer = Math.min(answer, recursion(current - 3, 3) + K);

        return dp[current][jump] = answer;
    }
}
