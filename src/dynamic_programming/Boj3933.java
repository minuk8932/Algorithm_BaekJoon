package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 3933번: 라그랑주 네 제곱수 정리
 *
 * @see https://www.acmicpc.net/problem/3933
 *
 */
public class Boj3933 {

    private static long[][][] dp;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        dp = new long[(1 << 15) + 1][5][182];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i][0], -1L);
            Arrays.fill(dp[i][1], -1L);
            Arrays.fill(dp[i][2], -1L);
            Arrays.fill(dp[i][3], -1L);
            Arrays.fill(dp[i][4], -1L);
        }

        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            sb.append(recursion(n, 1, 4)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Recursion
     *
     * line 60: unselect
     * line 61: select
     *
     * @param current
     * @param prev
     * @param num
     * @return
     */
    private static long recursion(int current, int prev, int num) {
        if(current == 0) return 1L;
        if(current < 0) return 0L;
        if(prev == 182 || num <= 0) return 0L;

        if(dp[current][num][prev] != -1) return dp[current][num][prev];
        long answer = recursion(current, prev + 1, num);
        answer += recursion(current - prev * prev, prev, num - 1);

        return dp[current][num][prev] = answer;
    }
}
