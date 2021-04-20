package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19952번: 인성 문제 있어??
 *
 * @see https://www.acmicpc.net/problem/19952
 *
 */
public class Boj19952 {
    private static final String PASS = "잘했어!!\n";
    private static final String NOT_PASS = "인성 문제있어??\n";

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static int[][] visit;
    private static int[][] map;
    private static int H, W;

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
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            int O = Integer.parseInt(st.nextToken());
            int F = Integer.parseInt(st.nextToken());

            Point start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            Point end = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            map = new int[H][W];
            visit = new int[H][W];

            while(O-- > 0) {
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] =
                        Integer.parseInt(st.nextToken());
            }

            sb.append(bfs(F, start, end) ? PASS: NOT_PASS);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Breadth First Search
     *
     * line 98: can reach
     * line 100: used all
     *
     * @param force
     * @param start
     * @param end
     * @return
     */
    private static boolean bfs(int force, Point start, Point end) {
        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        visit[start.row][start.col] = force;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(visit[nextRow][nextCol] != 0) continue;
                
                int curHeight = map[current.row][current.col];
                int nxtHeight = map[nextRow][nextCol];
                
                if(visit[current.row][current.col] < nxtHeight - curHeight) continue;
                visit[nextRow][nextCol] = visit[current.row][current.col] - 1;
                if(visit[nextRow][nextCol] < 0) return false;

                if(nextRow == end.row && nextCol == end.col) return true;
                q.offer(new Point(nextRow, nextCol));
            }
        }


        return false;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= H || col < 0 || col >= W;
    }
}
