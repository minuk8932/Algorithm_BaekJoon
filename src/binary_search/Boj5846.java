package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5846번: Tractor
 *
 * @see https://www.acmicpc.net/problem/5846
 *
 */
public class Boj5846 {
    private static int[][] visit;

    private static int[][] field;
    private static int start, end;
    private static int N, size;

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

    private static class Search {
        private static int parametricSearch() {
            int result = end;
            size = N * N;

            while (start <= end) {
                int mid = (start + end) / 2;

                if (judgement(mid)) {                   // set level
                    end = mid - 1;
                    result = Math.min(result, mid);
                }
                else {
                    start = mid + 1;
                }
            }

            return result;
        }

        private static boolean judgement(int mid) {
            visit = new int[N][N];

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (visit[row][col] != 0) continue;
                    if (Search.dfs(new Point(row, col), mid) * 2 >= size) return true;
                }
            }

            return false;
        }

        private static int dfs(Point start, int threshold) {
            if(visit[start.row][start.col] != 0) return 0;
            visit[start.row][start.col] = 1;

            for (final int[] DIRECTION : DIRECTIONS) {
                int nextRow = start.row + DIRECTION[ROW];
                int nextCol = start.col + DIRECTION[COL];

                if (outOfRange(nextRow, nextCol)) continue;
                if (threshold < Math.abs(field[nextRow][nextCol] - field[start.row][start.col])) continue;
                visit[start.row][start.col] += dfs(new Point(nextRow, nextCol), threshold);                 // count set size
            }

            return visit[start.row][start.col];
        }

        private static boolean outOfRange(int row, int col) {
            return row < 0 || row >= N || col < 0 || col >= N;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        field = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                end = Math.max(end, field[i][j]);
            }
        }

        System.out.println(Search.parametricSearch());
    }
}
