package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1103번: 게임
 *
 * @see https://www.acmicpc.net/problem/1103
 *
 */
public class Boj1103 {

    private static boolean flag;
    private static int N, M;
    private static int[][] map;
    private static int[][] dp;
    private static boolean[][][] visit;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static final char HOLE = 'H';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if(c == HOLE) map[i][j] = -1;
                else map[i][j] = c - '0';
            }
        }

        dp = new int[N][M];
        visit = new boolean[5][N][M];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        recursion(0, 0, 4);
        System.out.println(flag ? -1: dp[0][0]);
    }

    /**
     *
     * Recursion
     *
     * line 78 ~ 81: still looping
     * line 83: find move next
     *
     * @param row
     * @param col
     * @param dir
     * @return
     */
    private static int recursion(int row, int col, int dir) {
        if(dp[row][col] != -1) return dp[row][col];
        int result = 0;

        visit[dir][row][col] = true;

        for(int direction = 0; direction < 4; direction++) {
            int nextRow = row + DIRECTIONS[direction][ROW] * map[row][col];
            int nextCol = col + DIRECTIONS[direction][COL] * map[row][col];

            if(outOfRange(nextRow, nextCol)) continue;
            if(map[nextRow][nextCol] == -1) continue;
            if(visit[direction][nextRow][nextCol]){
                flag = true;
                continue;
            }

            result = Math.max(result, recursion(nextRow, nextCol, direction));
            visit[direction][nextRow][nextCol] = false;
        }

        return dp[row][col] = result + 1;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
