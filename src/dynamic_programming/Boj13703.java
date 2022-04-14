package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13703번: 물벼룩의 생존확률
 *
 * @see https://www.acmicpc.net/problem/13703
 *
 */
public class Boj13703 {

    private static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        dp = new long[3][129][n + 1];
        for(int i = 0; i < 129; i++) {
            Arrays.fill(dp[0][i], -1L);
            Arrays.fill(dp[1][i], -1L);
            Arrays.fill(dp[2][i], -1L);
        }

        System.out.println(recursion(2, k, n));
    }

    private static long recursion(int move, int depth, int time) {
        if(depth == 0) return 0L;
        if(time == 0) return 1L;

        if(dp[move][depth][time] != -1) return dp[move][depth][time];
        long answer = recursion(1, depth + 1, time - 1);
        answer += recursion(0, depth - 1, time - 1);

        return dp[move][depth][time] = answer;
    }
}
