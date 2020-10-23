package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20057번: 마법사 상어와 토네이도
 *
 * @see https://www.amcicpc.net/problem/20057
 *
 */
public class Boj20057 {
    private static final int[][] DIRECTIONS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private static final int[][][] SPREAD = {
            {{-1, 0}, {1, 0}, {-1, -1}, {1, -1}, {-2, -1}, {2, -1}, {-1, -2}, {1, -2}, {0, -3}, {0, -2}},
            {{0, -1}, {0, 1}, {1, -1}, {1, 1}, {1, -2}, {1, 2}, {2, -1}, {2, 1}, {3, 0}, {2, 0}},
            {{-1, 0}, {1, 0}, {-1, 1}, {1, 1}, {-2, 1}, {2, 1}, {-1, 2}, {1, 2}, {0, 3}, {0, 2}},
            {{0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {-1, -2}, {-1, 2}, {-2, -1}, {-2, 1}, {-3, 0}, {-2, 0}}};

    private static final int ROW = 0, COL = 1;
    private static final int[] PERCENT = {1, 1, 7, 7, 2, 2, 10, 10, 5};

    private static int[][] A;
    private static int result;
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

        A = new int[N + 2][N + 2];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tornado();
        System.out.println(result);
    }

    private static void tornado() {
        Point start = new Point((N + 1) / 2, (N + 1) / 2);
        int rotate = 0;
        int d = 0;

        while(true) {
            for (int i = 0; i < 2; i++) {
                for (int r = 0; r <= rotate; r++) {
                    move(start, d);

                    start.row += DIRECTIONS[d][ROW];
                    start.col += DIRECTIONS[d][COL];
                }

                d++;
                d %= 4;                                 // d -> r -> u -> l sequence
            }

            rotate++;
            if(rotate != N - 1) continue;

            for (int r = 0; r <= rotate; r++) {
                move(start, d);
                start.row += DIRECTIONS[d][ROW];
                start.col += DIRECTIONS[d][COL];
            }

            break;
        }
    }

    private static boolean outOfRange(Point p) {
        return p.row < 1 || p.row > N || p.col < 1 || p.col > N;
    }

    private static void move(Point current, int d) {
        Point next = new Point(current.row + DIRECTIONS[d][ROW], current.col + DIRECTIONS[d][COL]);

        int remained = A[next.row][next.col];
        if (remained == 0) return;

        A[next.row][next.col] = 0;
        int total = remained;

        for (int idx = 0; idx < 9; idx++) {
            Point target = new Point(current.row + SPREAD[d][idx][ROW], current.col + SPREAD[d][idx][COL]);
            int divide = (total * PERCENT[idx]) / 100;          // spreads

            if (outOfRange(target)) result += divide;
            else A[target.row][target.col] += divide;

            remained -= divide;
        }

        Point pushed = new Point(current.row + SPREAD[d][9][ROW], current.col + SPREAD[d][9][COL]);
        if (outOfRange(pushed)) result += remained;
        else A[pushed.row][pushed.col] += remained;
    }
}
