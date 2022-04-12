package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24428번: 알고리즘 수업 - 행렬 경로 문제 5
 *
 * @see https://www.acmicpc.net/problem/24428
 *
 */
public class Boj24428 {

    private static final int INF = -2_000_000_000;
    private static boolean[][] checkPoint;
    private static int[][] map;
    private static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        init(n);

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int P = Integer.parseInt(br.readLine());
        while(P-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            checkPoint[row][col] = true;
        }

        System.out.println(Math.max(recursion(n, n, 0), -1));
    }

    private static int recursion(int row, int col, int count) {
        if(count >= 3 && row == 1 && col == 1) return map[row][col];
        if(row == 0 || col == 0) return INF;

        int flag = Math.min(3, count);
        if(dp[flag][row][col] != -1) return dp[flag][row][col];

        int answer = recursion(row - 1, col, count + (checkPoint[row - 1][col] ? 1: 0))
                + map[row][col];

        answer = Math.max(answer,
            recursion(row, col - 1, count + (checkPoint[row][col - 1] ? 1: 0))
                + map[row][col]);

        return dp[flag][row][col] = answer;
    }

    private static void init(int n) {
        map = new int[n + 1][n + 1];
        dp = new int[4][n + 1][n + 1];

        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp[0][i], -1);
            Arrays.fill(dp[1][i], -1);
            Arrays.fill(dp[2][i], -1);
            Arrays.fill(dp[3][i], -1);
        }

        checkPoint = new boolean[n + 1][n + 1];
    }
}
