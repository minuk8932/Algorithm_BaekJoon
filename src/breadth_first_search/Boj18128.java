package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 18128번: 치삼이의 징검다리 건너기
 *
 * @see https://www.acmicpc.net/problem/18128
 *
 */
public class Boj18128 {

    private static int N;
    private static int left, right;

    private static List<Point> water = new LinkedList<>();
    private static int[][] map;
    private static int[][] visit;

    private static final int[][] WATER_DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    private static final int ROW = 0;
    private static final int COL = 1;
    private static final int INF = 1_000_000_000;

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
        N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        while(W-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            water.add(new Point(r, c));
        }

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            String input = br.readLine();

            for(int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        waterSpread();
        System.out.println(binarySearch());
    }

    /**
     *
     * Binary search
     *
     * line 79: find reachable time.
     *
     * @return
     */
    private static int binarySearch() {
        int result = INF;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if(reachable(mid)) {
                right = mid - 1;
                result = Math.min(mid, result);
            }
            else {
                left = mid + 1;
            }
        }

        return result == INF ? -1: result - 2;
    }

    /**
     *
     * Reachable
     *
     * line 115: end point considering any other cases. just return.
     *
     * @param limit
     * @return
     */
    private static boolean reachable(int limit) {
        boolean[][] checker = new boolean[N][N];

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0));
        checker[0][0] = true;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if (outOfRange(nextRow, nextCol)) continue;
                if (nextRow == N - 1 && nextCol == N - 1) return true;

                if (visit[nextRow][nextCol] >= limit || map[nextRow][nextCol] == 0) continue;
                if (checker[nextRow][nextCol]) continue;
                checker[nextRow][nextCol] = true;

                q.offer(new Point(nextRow, nextCol));
            }
        }

        return false;
    }

    /**
     *
     * Water spread
     *
     * line 155: save time (water spread)
     *
     */
    private static void waterSpread() {
        visit = new int[N][N];

        Queue<Point> q = new ArrayDeque<>();
        while(!water.isEmpty()) {
            Point start = water.remove(0);
            q.offer(start);

            visit[start.row][start.col] = 1;
        }

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] WATER_DIRECTION: WATER_DIRECTIONS) {
                int nextRow = current.row + WATER_DIRECTION[ROW];
                int nextCol = current.col + WATER_DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(visit[nextRow][nextCol] != 0) continue;
                visit[nextRow][nextCol] = visit[current.row][current.col] + 1;

                right = Math.max(right, visit[nextRow][nextCol]);
                q.offer(new Point(nextRow, nextCol));
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
