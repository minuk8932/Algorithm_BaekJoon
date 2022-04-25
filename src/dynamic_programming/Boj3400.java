package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3400번: Expressions
 *
 * @see https://www.acmicpc.net/problem/3400
 *
 */
public class Boj3400 {

    private static int[][][] dp;
    private static int d;
    private static int half;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        half = (n + 1) >> 1;

        dp = new int[n + 1][d + 1][half + 1];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(recursion(n, 0, 0));
    }

    private static int recursion(int current, int depth, int max) {
        if(depth == 0 && current == 0 && max == d) return 1;
        if(current == 0 || max > half) return 0;
        if(depth < 0 || depth > d) return 0;

        if(dp[current][depth][max] != -1) return dp[current][depth][max];

        int answer = recursion(current - 1, depth + 1, Math.max(max, depth + 1));
        answer += recursion(current - 1, depth - 1, Math.max(max, depth - 1));

        return dp[current][depth][max] = answer;
    }
}
