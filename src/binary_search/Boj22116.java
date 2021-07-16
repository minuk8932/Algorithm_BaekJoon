package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 22116번: 창영이와 퇴근
 *
 * @see https://www.acmicpc.net/problem/22116
 *
 */
public class Boj22116 {

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int INF = 1_000_000_000;

    private static int N;
    private static int[][] map;

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

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(binarySearch());
    }

    /**
     *
     * Binary search
     *
     * line 69: find max difference
     *
     * @return
     */
    private static int binarySearch() {
        if(N == 1) return 0;

        int start = 0;
        int end = INF - 1;

        int height = INF;

        while(start <= end) {
            int mid = (start + end) >> 1;

            if(bfs(mid)) {
                height = Math.min(mid, height);
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        return height;
    }

    private static boolean bfs(int limit) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0));

        boolean[][] visit = new boolean[N][N];
        visit[0][0] = true;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(visit[nextRow][nextCol]) continue;
                if(Math.abs(map[current.row][current.col] - map[nextRow][nextCol]) > limit) continue;
                visit[nextRow][nextCol] = true;

                if(nextRow == N - 1 && nextCol == N - 1) return true;
                q.offer(new Point(nextRow, nextCol));
            }
        }

        return false;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= N;
    }
}
