package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3987번: 보이저 1호
 *
 * @see https://www.acmicpc.net/problem/3987/
 *
 */
public class Boj3987 {
    private static final char[] DIRS = {'U', 'R', 'D', 'L'};
    private static final String CYCLE = "Voyager";
    private static final String NEW_LINE = "\n";
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static final char MIRROR_1 = '/';
    private static final char MIRROR_2 = '\\';
    private static final char BLACK_HOLE = 'C';

    private static int N, M;

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
        M = Integer.parseInt(st.nextToken());

        char[][] space = new char[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < M; j++) {
                space[i][j] = line.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        Point probe = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        System.out.println(fourDirection(space, probe));
    }

    /**
     *
     * @param space
     * @param probe
     * @return: max flight cost & direction
     * @variable: dir(direction) -> 0: U, 1: R, 2: D, 3: L (each has priority)
     */
    private static String fourDirection(char[][] space, Point probe){
        int[] cost = new int[4];
        int max = -1, d = 0;

        StringBuilder sb = new StringBuilder();

        for(int dir = 0; dir < 4; dir++) {
            cost[dir] = bfs(space, probe, dir);
            if(cost[dir] == -1) return sb.append(DIRS[dir]).append(NEW_LINE).append(CYCLE).toString();

            if (max <= cost[dir]) {
                if(max != cost[dir]) d = dir;
                max = cost[dir];
            }
        }

        return sb.append(DIRS[d]).append(NEW_LINE).append(max).toString();
    }

    /**
     *
     * @value: visit[nextRow][nextCol][chD], if this value is not 0, then has cycle
     */
    private static int bfs(char[][] s, Point start, int d) {
        if(s[start.row][start.col] == BLACK_HOLE) return 0;

        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        int[][][] visit = new int[N][M][4];
        visit[start.row][start.col][d] = 1;

        while(!q.isEmpty()) {
            Point current = q.poll();

            int nextRow = current.row + DIRECTIONS[d][ROW];
            int nextCol = current.col + DIRECTIONS[d][COL];

            if(outOfRange(nextRow, nextCol) || s[nextRow][nextCol] == BLACK_HOLE) return visit[current.row][current.col][d];
            if(s[nextRow][nextCol] == MIRROR_1 || s[nextRow][nextCol] == MIRROR_2) {
                int chD = changeDirection(d, s[nextRow][nextCol]);
                if(visit[nextRow][nextCol][chD] != 0) break;
                visit[nextRow][nextCol][chD] = visit[current.row][current.col][d] + 1;
                d = chD;

            }
            else {
                visit[nextRow][nextCol][d] = visit[current.row][current.col][d] + 1;
            }

            q.offer(new Point(nextRow, nextCol));
        }

        return -1;
    }

    /**
     *
     * @param d: current direction
     * @param mirror: change d to chg
     * @return: changed direction
     */
    private static int changeDirection(int d, char mirror) {
        int chg = -1;

        switch (mirror) {
            case MIRROR_1:
                if(d == 0) chg = 1;
                else if(d == 1) chg = 0;
                else if(d == 2) chg = 3;
                else chg = 2;
                break;

            case MIRROR_2:
                if(d == 0) chg = 3;
                else if(d == 1) chg = 2;
                else if(d == 2) chg = 1;
                else chg = 0;
                break;
        }

        return chg;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
