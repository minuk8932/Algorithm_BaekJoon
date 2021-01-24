package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20158번: 사장님 달려가고 있습니다.
 *
 * @see https://www.amicpc.net/problem/20158
 *
 */
public class Boj20158 {

    private static int N;
    private static int[][] map;

    private static final int[][] DIRECTIONS = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int ROW = 0, COL = 1;
    private static final int INF = 1_000_000_001;

    private static class Point {
        int row;
        int col;
        int pop;
        int dir;

        public Point(int row, int col, int pop, int dir) {
            this.row = row;
            this.col = col;
            this.pop = pop;
            this.dir = dir;
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
                if (map[i][j] == 0) map[i][j] = INF;
            }
        }

        int cost = bfs();
        System.out.println(cost == INF ? "Fired": cost);
    }

    private static int bfs() {
        int[][][][] visit = new int[5][N][N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Arrays.fill(visit[1][i][j], -1);
                Arrays.fill(visit[2][i][j], -1);
                Arrays.fill(visit[3][i][j], -1);
                Arrays.fill(visit[4][i][j], -1);
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, 0, 0));

        visit[4][0][0][0] = visit[1][0][0][0] = visit[2][0][0][0] = visit[3][0][0][0] = 0;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(int d = 1; d < 5; d++) {
                int nextPop = d == current.dir ? current.pop + 1 : 1;
                int nextRow = current.row + DIRECTIONS[d][ROW] * nextPop;
                int nextCol = current.col + DIRECTIONS[d][COL] * nextPop;

                if(outOfRange(nextRow, nextCol)) continue;
                int v = visit[current.dir][current.row][current.col][current.pop];

                if(!reachable(current, nextRow, nextCol, d, v)) continue;
                if(map[nextRow][nextCol] <= v + 1) continue;
                if(visit[d][nextRow][nextCol][nextPop] != -1) continue;
                visit[d][nextRow][nextCol][nextPop] = v + 1;

                if(nextRow == N - 1 && nextCol == N - 1) return visit[d][nextRow][nextCol][nextPop];
                q.offer(new Point(nextRow, nextCol, nextPop, d));
            }
        }

        return INF;
    }

    /**
     * User can pass through the path. constructions processed between s and d, user cannot pass.
     *
     * @param current
     * @param nextRow
     * @param nextCol
     * @param dir
     * @param cost
     * @return
     */
    private static boolean reachable(Point current, int nextRow, int nextCol, int dir, int cost) {
        int row = current.row;
        int col = current.col;

        while(row < nextRow || col < nextCol) {
            row += DIRECTIONS[dir][ROW];
            col += DIRECTIONS[dir][COL];

            if (map[row][col] <= cost) return false;
        }

        return true;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
