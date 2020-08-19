package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 3709번: 레이저 빔은 어디로
 *
 * @see https://www.acmicpc.net/problem/3709
 *
 */
public class Boj3709 {
    private static int n;
    private static boolean[][] map;

    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static class Point{
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
        int T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            map = new boolean[n + 2][n + 2];

            while(r-- > 0) {
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
            }

            st = new StringTokenizer(br.readLine());
            Point result = rotating(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            sb.append(result.row).append(SPACE).append(result.col).append(NEW_LINE);
        }

        System.out.print(sb.toString());
    }

    private static Point rotating(Point current) {
        Point result = new Point(0, 0);

        for(int idx = 0; idx < 4; idx++) {
            int nextRow = current.row + DIRECTIONS[idx][ROW];
            int nextCol = current.col + DIRECTIONS[idx][COL];

            if (outOfRange(nextRow, nextCol)) continue;
            result = move(new Point(nextRow, nextCol), map[nextRow][nextCol] ? (idx + 1) % 4 : idx);        // result position
            break;
        }

        return result;
    }

    private static Point move(Point next, int dir) {
        while (!outOfRange(next.row, next.col)) {                   // straight
            next.row += DIRECTIONS[dir][ROW];
            next.col += DIRECTIONS[dir][COL];

            if (map[next.row][next.col]) dir = (dir + 1) % 4;
        }

        return new Point(next.row, next.col);
    }

    private static boolean outOfRange(int row, int col) {
        return row < 1 || col < 1 || row > n || col > n;
    }
}
