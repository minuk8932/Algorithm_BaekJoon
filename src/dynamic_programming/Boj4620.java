package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 4620번: Pascal's Travels
 *
 * @see https://www.acmicpc.net/problem/4620
 *
 */
public class Boj4620 {
    private static long[][] dp;
    private static int N;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == -1) break;

            int[][] board = new int[N][N];
            dp = new long[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();

                for (int j = 0; j < N; j++) {
                    board[i][j] = line.charAt(j) - '0';
                }
            }

            sb.append(jump(0, 0, board)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static long jump(int row, int col, int[][] board) {
        if(row == N - 1 && col == N - 1) return 1L;
        if(board[row][col] == 0) return 0;

        if(dp[row][col] != 0) return dp[row][col];
        long result = 0;

        int nextRow = row + board[row][col];                                            // move under
        if(!outOfBound(nextRow, col)) result += jump(nextRow, col, board);

        int nextCol = col + board[row][col];                                            // move right
        if(!outOfBound(row, nextCol)) result += jump(row, nextCol, board);

        return dp[row][col] = result;
    }

    private static boolean outOfBound(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
