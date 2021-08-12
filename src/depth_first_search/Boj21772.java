package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 21772번: 가희와 고구마 먹방
 *
 * @see https://www.acmicpc.net/problem/21772
 *
 */
public class Boj21772 {

    private static int R, C;
    private static Point start;
    private static char[][] map;

    private static final char GAHEE = 'G';
    private static final char EMPTY = '.';
    private static final char BLOCK = '#';
    private static final char SWEET_POTATO = 'S';

    private static int max;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            String input = br.readLine();

            for(int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if(map[i][j] == GAHEE) start = new Point(i, j);
            }
        }

        recursion(start, T, 0);
        System.out.println(max);
    }

    private static void recursion(Point current, int t, int count) {
        if(t == 0) {
            max = Math.max(max, count);
            return;
        }

        recursion(current, t - 1, count);

        for (final int[] DIRECTION: DIRECTIONS) {
            int nextRow = current.row + DIRECTION[ROW];
            int nextCol = current.col + DIRECTION[COL];

            if(outOfRange(nextRow, nextCol)) continue;
            if(map[nextRow][nextCol] == BLOCK) continue;

            if(map[nextRow][nextCol] != SWEET_POTATO) {
                recursion(new Point(nextRow, nextCol), t - 1, count);
                continue;
            }

            map[nextRow][nextCol] = EMPTY;
            recursion(new Point(nextRow, nextCol), t - 1, count + 1);
            map[nextRow][nextCol] = SWEET_POTATO;
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= R || col < 0 || col >= C;
    }
}
