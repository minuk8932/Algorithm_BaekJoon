package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 2239번: 스도쿠
 *
 * @see https://www.acmicpc.net/problem/2239
 *
 */
public class Boj2239 {
    private static int[][] origin = new int[9][9];
    private static int[][] SUDOKU = new int[9][9];

    private static final String NEW_LINE = "\n";
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < SUDOKU.length; i++) {
            String line = br.readLine();

            for(int j = 0; j < SUDOKU.length; j++) {
                SUDOKU[i][j] = EXCEPT_ASCII.apply(line.charAt(j));
                origin[i][j] = SUDOKU[i][j];
            }
        }

        recursion(0, 0);
    }

    /**
     *
     * Recursion
     *
     * line 58 ~ 67: find next empty array
     * line 69: pass next
     *
     * @param row
     * @param col
     */
    private static void recursion(int row, int col) {
        if(flag) return;

        if(row == 9 && col == 0){
            flag = true;
            System.out.println(printing());

            return;
        }

        int nextRow = row + (col == 8 ? 1: 0);
        int nextCol = col == 8 ? 0: col + 1;

         if(SUDOKU[row][col] == 0) {
             for(int val = 1; val <= 9; val++) {
                 if(cross(row, col, val)) continue;
                 if(square(row, col, val)) continue;

                 SUDOKU[row][col] = val;
                 recursion(nextRow, nextCol);
                 SUDOKU[row][col] = origin[row][col];
             }
         }
         else {
             recursion(nextRow, nextCol);
         }

    }

    private static boolean cross(int r, int c, int input) {
        for(int i = 0; i < 9; i++) {
            if(SUDOKU[i][c] == input || SUDOKU[r][i] == input) return true;
        }

        return false;
    }

    private static boolean square(int r, int c, int input) {
        int row = ARRANGER.apply(r);
        int col = ARRANGER.apply(c);

        for(int i = row; i < row + 3; i++) {
            for(int j = col; j < col + 3; j++) {
                if(SUDOKU[i][j] == input) return true;
            }
        }

        return false;
    }

    private static String printing() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < SUDOKU.length; i++) {
            for(int j = 0; j < SUDOKU.length; j++) {
                sb.append(SUDOKU[i][j]);
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static final Function<Character, Integer> EXCEPT_ASCII = x -> x - '0';
    private static final Function<Integer, Integer> ARRANGER = x -> x / 3 * 3;

}
