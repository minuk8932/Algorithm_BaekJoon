package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24427번: 알고리즘 수업 - 행렬 경로 문제 4
 *
 * @see https://www.acmicpc.net/problem/24427
 *
 */
public class Boj24427 {

    private static final long INF = -1_000_000_000_000_000_000L;

    private static long[][] map;
    private static long[][][] dp;
    private static boolean[][] checkPoint;
    private static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        init();
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int P = Integer.parseInt(br.readLine());
        while (P-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            checkPoint[row][col] = true;
        }

        System.out.println(recursion(0, n, n));
    }

    private static long recursion(int visited, int row, int col) {
        if (row == 1 && col == 1 && visited == 1) return map[row][col];
        if (row == 0 || col == 0) return INF;

        if (dp[visited][row][col] != -1) return dp[visited][row][col];

        long answer = Math.max(
            recursion((checkPoint[row][col - 1] ? 1: 0) | visited, row, col - 1)
                + map[row][col]
            , recursion((checkPoint[row - 1][col] ? 1 : 0) | visited, row - 1, col)
                + map[row][col]);

        return dp[visited][row][col] = answer;
    }

    private static void init() {
        checkPoint = new boolean[n + 1][n + 1];
        map = new long[n + 1][n + 1];
        dp = new long[2][n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[0][i], -1L);
            Arrays.fill(dp[1][i], -1L);
        }
    }
}
