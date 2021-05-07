package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17845번: 수강 과목
 *
 * @see https://www.acmicpc.net/problem/17845
 *
 */
public class Boj17845 {

    private static int[][] dp;
    private static Subject[] subjects;

    private static class Subject {
        int importance;
        int time;

        public Subject(int importance, int time) {
            this.importance = importance;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        subjects = new Subject[K + 1];
        dp = new int[N + 1][K + 1];

        subjects[0] = new Subject(0, 0);

        for(int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            subjects[i] = new Subject(I, T);
        }

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(recursion(N, K));
    }

    /**
     *
     * Recursion, Top-Down
     *
     * line 70: select this, or not
     *
     * @param n
     * @param current
     * @return
     */
    private static int recursion(int n, int current) {
        if (n <= 0) return n == 0 ? 0: Integer.MIN_VALUE;
        if (current <= 0) return 0;

        if (dp[n][current] != -1) return dp[n][current];
        int result = Math.max(recursion(n, current - 1)
                , recursion(n - subjects[current].time, current - 1) + subjects[current].importance);

        return dp[n][current] = result;
    }
}
