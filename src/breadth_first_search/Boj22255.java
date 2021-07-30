package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 22255번: 호석사우루스
 *
 * @see https://www.acmicpc.net/problem/22255
 *
 */
public class Boj22255 {

    private static int[][][] DIRECTIONS = {{{1, 0, 0}, {0, 1, 1}, {-1, 0, 2}, {0, -1, 3}},
            {{1, 0, 0}, {-1, 0, 2}}, {{0, 1, 1}, {0, -1, 3}}};

    private static final int ROW = 0, COL = 1, DIR = 2;
    private static final int BLOCK = -1;
    private static final int INF = 1_000_000_000;

    private static int N, M;

    private static class Point {
        int row;
        int col;
        int move;
        int dir;

        public Point(int row, int col, int move, int dir) {
            this.row = row;
            this.col = col;
            this.move = move;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Point start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1, 4);
        Point end = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1, 4);

        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(map, start, end));
    }

    /**
     *
     * BFS
     *
     * line 75: considering many elements: row, col, dir, move count
     *
     * @param map
     * @param s
     * @param e
     * @return
     */
    private static int bfs(int[][] map, Point s, Point e) {
        int[][][][] visit = new int[N][M][5][4];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int k = 0; k < 5; k++) {
                    Arrays.fill(visit[i][j][k], INF);
                }
            }
        }

        int result = INF;

        Queue<Point> q = new ArrayDeque<>();
        q.offer(s);

        visit[s.row][s.col][4][1] = 0;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for (final int[] DIRECTION: DIRECTIONS[current.move % 3]) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];
                int nextDir = DIRECTION[DIR];
                int nextMove = current.move + 1;

                if(outOfRange(nextRow, nextCol)) continue;
                if(map[nextRow][nextCol] == BLOCK) continue;
                if(visit[nextRow][nextCol][nextDir][nextMove % 3] <= visit[current.row][current.col][current.dir][current.move % 3] + map[nextRow][nextCol]) continue;
                visit[nextRow][nextCol][nextDir][nextMove % 3] = visit[current.row][current.col][current.dir][current.move % 3] + map[nextRow][nextCol];

                if(nextRow == e.row && nextCol == e.col) result = Math.min(result, visit[nextRow][nextCol][nextDir][nextMove % 3]);
                q.offer(new Point(nextRow, nextCol, nextMove, nextDir));
            }
        }

        return result == INF ? -1: result;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= M;
    }
}
