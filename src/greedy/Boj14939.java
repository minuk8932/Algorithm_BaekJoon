package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 14939번: 불 끄기
 *
 * @see https://www.acmicpc.net/problem/14939/
 *
 */
public class Boj14939 {
    private static final char ON = 'O';

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int INF = 1 << 10;

    private static int result = INF;
    private static boolean[][] dummy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[][] lights = new boolean[10][10];
        for(int i = 0; i < 10; i++) {
            String line = br.readLine();

            for(int j = 0; j < 10; j++) {
                lights[i][j] = line.charAt(j) == ON;
            }
        }

        System.out.println(turning(lights));
    }

    private static boolean[][] reset (boolean[][] bulb) {
        boolean[][] arr = new boolean[10][10];

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                arr[i][j] = bulb[i][j];
            }
        }

        return arr;
    }

    private static void click(int row, int col) {
        dummy[row][col] ^= true;

        for (final int[] DIRECTION : DIRECTIONS) {
            int adjRow = row + DIRECTION[ROW];
            int adjCol = col + DIRECTION[COL];

            if (outOfRange(adjRow, adjCol)) continue;
            dummy[adjRow][adjCol] ^= true;
        }
    }

    private static int turning (boolean[][] bulb) {

        for(int i = INF - 1; i >= 0; i--){              // considering all cases
            int count = 0;
            dummy = reset(bulb);

            for(int j = 0; j < 10; j++) {               // first row clicked
                if ((i & (1 << j)) != 0) {
                    click(0, j);
                    count++;
                }
            }

            for(int j = 1; j < 10; j++) {               // 2 ~ 10 row clicked
                for (int k = 0; k < 10; k++) {
                    if (!dummy[j - 1][k]) continue;     // if left light is off, then no touch current
                    click(j, k);
                    count++;
                }
            }

            boolean flag = true;
            for(int j = 0; j < 10; j++) {               // last row check
                if (dummy[9][j]) {
                    flag = false;
                    break;
                }
            }

            if(!flag) continue;
            result = Math.min(result, count);
        }

        return result == INF ? -1: result;
    }

    private static boolean outOfRange (int row, int col) {
        return row < 0 || row >= 10 || col < 0 || col >= 10;
    }
}
