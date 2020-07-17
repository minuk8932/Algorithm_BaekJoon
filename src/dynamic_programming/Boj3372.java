package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3372번: 보드 점프
 *
 * @see https://www.acmicpc.net/problem/3372
 *
 */
public class Boj3372 {
    private static BigInteger[][] dp;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        dp = new BigInteger[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = BigInteger.ZERO;
            }
        }

        System.out.println(jump(0, 0, board));
    }

    private static BigInteger jump(int row, int col, int[][] board) {
        if(row == N - 1 && col == N - 1) return BigInteger.ONE;
        if(board[row][col] == 0) return BigInteger.ZERO;

        if(!dp[row][col].equals(BigInteger.ZERO)) return dp[row][col];
        BigInteger result = BigInteger.ZERO;

        int nextRow = row + board[row][col];                                            // move under
        if(!outOfBound(nextRow, col)) result = result.add(jump(nextRow, col, board));

        int nextCol = col + board[row][col];                                            // move right
        if(!outOfBound(row, nextCol)) result = result.add(jump(row, nextCol, board));

        return dp[row][col] = result;
    }

    private static boolean outOfBound(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
