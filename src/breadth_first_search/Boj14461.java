package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14461번: 소가 길을 건너간 이유 7
 *
 * @see https://www.acmicpc.net/problem/14461/
 *
 */
public class Boj14461 {
    private static long[][][] visit;
    private static int N;

    private static final long INF = 10_000_000_000_000L;
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static class Point {
        int row;
        int col;
        int cross;

        public Point(int row, int col, int cross){
            this.row = row;
            this.col = col;
            this.cross = cross;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        visit = new long[N][N][3];
        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                visit[i][j][0] = visit[i][j][1] = visit[i][j][2] = INF;
            }
        }

        System.out.println(bfs(T, map));
    }

    private static long bfs(int t, int[][] m){
        Queue<Point> q = new LinkedList<>();

        q.offer(new Point(0, 0, 0));
        visit[0][0][0] = 0;

        while(!q.isEmpty()){
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS){                 // row, col, crossed times
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];
                int crossed = current.cross + 1;

                if (outOfRange(nextRow, nextCol)) continue;
                int ncost = (crossed % 3 == 0 ? m[nextRow][nextCol]: 0) + t;

                if (visit[nextRow][nextCol][crossed % 3] <= visit[current.row][current.col][current.cross % 3] + ncost) continue;
                visit[nextRow][nextCol][crossed % 3] = visit[current.row][current.col][current.cross % 3] + ncost;

                q.offer(new Point(nextRow, nextCol, crossed));
            }
        }

        long result = INF;
        for(int i = 0; i < 3; i++){                                 // find min
            result = Math.min(result, visit[N - 1][N - 1][i]);
        }

        return result;
    }

    private static boolean outOfRange(int row, int col) {
        return row >= N || row < 0 || col >= N || col < 0;
    }
}
