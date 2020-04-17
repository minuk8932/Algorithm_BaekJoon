package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14927번: 전구 끄기
 *
 * @see https://www.acmicpc.net/problem/14927/
 *
 */
public class Boj14927 {
    private static final char ON = '1';

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int INF = 1 << 18;

    private static int result = INF;
    private static int N;
    private static boolean[][] dummy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        boolean[][] lights = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                lights[i][j] = st.nextToken().charAt(0) == ON;
            }
        }

        System.out.println(turning(lights));
    }

    private static boolean[][] reset (boolean[][] bulb) {
        boolean[][] arr = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
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
        int loop = 1 << N;

        for(int i = loop - 1; i >= 0; i--){              // considering all cases
            int count = 0;
            dummy = reset(bulb);

            for(int j = 0; j < N; j++) {               // first row clicked
                if ((i & (1 << j)) != 0) {
                    click(0, j);
                    count++;
                }
            }

            for(int j = 1; j < N; j++) {               // 2 ~ N row clicked
                for (int k = 0; k < N; k++) {
                    if (!dummy[j - 1][k]) continue;     // if left light is off, then no touch current
                    click(j, k);
                    count++;
                }
            }

            boolean flag = true;
            for(int j = 0; j < N; j++) {               // last row check
                if (dummy[N - 1][j]) {
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
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
