package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18404번: 현명한 나이트
 *
 * @see https://www.acmicpc.net/problem/18404
 *
 */
public class Boj18404 {
    private static int[][] visit;
    private static int N;

    private static final int[][] DIRECTIONS = {{1, 2}, {2, 1}, {-1, 2}, {-2, 1},
            {1, -2}, {2, -1}, {-1, -2}, {-2, -1}};
    private static final int ROW = 0, COL = 1;
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
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Point start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        visit = new int[N][N];
        bfs(start);                 // make cost at first

        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            sb.append(visit[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] - 1).append(SPACE);
        }

        System.out.println(sb.toString());
    }

    private static void bfs(Point src) {
        Queue<Point> q = new LinkedList<>();
        q.offer(src);

        visit[src.row][src.col] = 1;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if (outOfRange(nextRow, nextCol)) continue;
                if (visit[nextRow][nextCol] != 0) continue;
                visit[nextRow][nextCol] = visit[current.row][current.col] + 1;

                q.offer(new Point(nextRow, nextCol));
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
