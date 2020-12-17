package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 20125번: 쿠키의 신체 측정
 *
 * @see https://www.acmicpc.net/problem/20125
 *
 */
public class Boj20125 {
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static final char HEAD = '*';

    private static final int[][] DIRECTIONS = {{0, -1}, {0, 1}, {1, 0}};
    private static final int ROW = 0, COL = 1;

    private static Point start;
    private static int N;

    private static class Point {
         int row;
         int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[][] board = new char[N][N];
        boolean flag = false;

        for(int i = 0; i < N; i++) {
            String input = br.readLine();

            for(int j = 0; j < N; j++) {
                board[i][j] = input.charAt(j);

                if(!flag && board[i][j] == HEAD) {
                    flag = true;
                    start = new Point(i + 1, j);
                }
            }
        }

        System.out.println(analysis(board));
    }

    private static String analysis(char[][] arr) {
        StringBuilder sb = new StringBuilder();

        sb.append(start.row + 1).append(SPACE).append(start.col + 1).append(NEW_LINE);

        int leftArm = lengthCheck(arr, DIRECTIONS[0], new Point(start.row, start.col), -1);
        int rightArm = lengthCheck(arr, DIRECTIONS[1], new Point(start.row, start.col), -1);
        int back = lengthCheck(arr, DIRECTIONS[2], start, -1);
        int leftLeg = lengthCheck(arr, DIRECTIONS[2], new Point(start.row, start.col - 1), 0);
        int rightLeg = lengthCheck(arr, DIRECTIONS[2], new Point(start.row, start.col + 1), 0);

        sb.append(leftArm).append(SPACE).append(rightArm).append(SPACE)
                .append(back).append(SPACE).append(leftLeg).append(SPACE).append(rightLeg);

        return sb.toString();
    }

    private static int lengthCheck(char[][] arr, final int[] D, Point s, int count){

        while(!outOfRange(s.row, s.col) && arr[s.row][s.col] == HEAD) {
            s.row += D[ROW];
            s.col += D[COL];

            count++;
        }

        return count;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
