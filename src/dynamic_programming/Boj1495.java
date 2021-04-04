package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1495번: 기타리스트
 *
 * @see https://www.acmicpc.net/problem/1495
 *
 */
public class Boj1495 {

    private static int[][] dp = new int[101][1_001];
    private static int[] volume;
    private static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        volume = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            volume[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        boolean flag = true;
        int max = 0;
        for (int i = 0; i <= M; i++) {
            if (recursion(M, N, i) != 1) continue;
            flag = false;
            max = i;
        }

        System.out.println(flag ? -1: max);
    }

    /**
     *
     * Recursion
     *
     * line 69 ~ 70: find next volume possibility
     *
     * @param limit
     * @param count
     * @param current
     * @return
     */
    private static int recursion(int limit, int count, int current) {
        if (current > limit || current < 0) return 0;

        if (count == 0) {
            if (current == S) return 1;
            else return 0;
        }

        if (dp[count][current] != -1) return dp[count][current];

        dp[count][current] = recursion(limit, count - 1, current - volume[count - 1])
            | recursion(limit, count - 1, current + volume[count - 1]);
        return dp[count][current];
    }
}
