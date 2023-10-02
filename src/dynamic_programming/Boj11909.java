package dynamic_programming;

import java.io.*;
import java.util.*;

public class Boj11909 {

    private static final int INF = 1_000_000_000;
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N + 1][N + 1];
        for(int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }

        System.out.println(recursion(N, N));
    }

    private static int recursion(int row, int col) {
        if(row == 1 && col == 1) return 0;

        if(dp[row][col] != INF) return dp[row][col];
        int answer = INF;

        for(int[] DIRECTION: DIRECTIONS) {
            int nextRow = row + DIRECTION[ROW];
            int nextCol = col + DIRECTION[COL];

            if(outOfBound(nextRow, nextCol)) continue;
            int diff = map[row][col] - map[nextRow][nextCol];
            diff = diff >= 0 ? diff + 1: 0;

            answer = Math.min(recursion(nextRow, nextCol) + diff, answer);
        }

        return dp[row][col] = answer;
    }

    private static boolean outOfBound(int row, int col) {
        return row <= 0 || col <= 0;
    }
}
