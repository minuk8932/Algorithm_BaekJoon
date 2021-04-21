package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17404번: RGB거리 2
 *
 * @see https://www.acmicpc.net/problem/17404
 *
 */
public class Boj17404 {

    private static int[][][] dp;
    private static int[][] houses;

    private static int N;
    private static final int INF = 10_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        houses = new int[N + 1][3];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            houses[i][0] = Integer.parseInt(st.nextToken());
            houses[i][1] = Integer.parseInt(st.nextToken());
            houses[i][2] = Integer.parseInt(st.nextToken());
        }

        dp = new int[3][N + 1][3];
        int min = INF;

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j ++) {
                dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = -1;
            }
        }

        for(int i = 0; i < 3; i++) {
            min = Math.min(recursion(i, i, 1), min);
        }

        System.out.println(min);
    }

    /**
     *
     * Recursion
     *
     * line 68: find other colors
     *
     * @param first: first index color
     * @param color
     * @param current
     * @return
     */
    private static int recursion(int first, int color, int current) {
        if(current == N) return first == color ? INF: houses[current][color];

        if(dp[first][current][color] != -1) return dp[first][current][color];
        int result = INF;

        for(int i = 0; i < 3; i++) {
            if(color == i) continue;
            result = Math.min(result, recursion(first, i, current + 1));
        }

        return dp[first][current][color] = result + houses[current][color];
    }
}
