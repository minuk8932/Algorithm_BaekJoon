package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21922번: 학부 연구생 민상
 *
 * @see https://www.acmicpc.net.problem/21922
 *
 */
public class Boj21922 {

    private static int N, M;

    private static boolean[][][] flow;
    private static int[][] lab;
    private static Map<Integer, Integer> dirMapping = new HashMap<>();

    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static final int U = -10, D = 10, R = 1, L = -1;

    private static List<Point> aircon = new LinkedList<>();

    private static class Point {
        int row;
        int col;
        int dir;

        public Point(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][M];
        flow = new boolean[5][N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if(lab[i][j] == 9) aircon.add(new Point(i, j, 0));
            }
        }

        dirMapping.put(U, 0);
        dirMapping.put(D, 2);
        dirMapping.put(R, 1);
        dirMapping.put(L, 3);
        while(!aircon.isEmpty()) {
            bfs(aircon.remove(0));
        }

        System.out.println(getArea());
    }

    private static int getArea() {
        boolean[][] trace = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int k = 0; k < 5; k++) {
                    trace[i][j] |= flow[k][i][j];
                }
            }
        }

        int area = 0;

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                area += trace[i][j] ? 1: 0;
            }
        }

        return area;
    }

    /**
     *
     * BFS
     *
     * line 102 ~ 104: blowing wind
     * line 124 ~ 138: change direction by lab structure
     *
     * @param start
     */
    private static void bfs(Point start) {
        Queue<Point> q = new LinkedList<>();

        for(final int[] DIRECTION: DIRECTIONS) {
            q.offer(new Point(start.row, start.col, DIRECTION[ROW] * 10 + DIRECTION[COL]));
        }

        flow[4][start.row][start.col] = true;

        while(!q.isEmpty()) {
            Point current = q.poll();
            int r = current.dir / 10;
            int c = current.dir % 10;

            int nextRow = current.row + r;
            int nextCol = current.col + c;
            int nextDir = current.dir;

            if(outOfRange(nextRow, nextCol)) continue;
            if(flow[dirMapping.get(current.dir)][nextRow][nextCol]) continue;
            flow[dirMapping.get(current.dir)][nextRow][nextCol] = true;

            boolean pass = false;
            int mul = current.dir / 10;

            switch (current.dir) {
                case U:
                case D:
                    if(lab[nextRow][nextCol] == 2) pass = true;
                    else if(lab[nextRow][nextCol] == 3) nextDir = -1 * mul;
                    else if(lab[nextRow][nextCol] == 4) nextDir = 1 * mul;
                    break;

                case R:
                case L:
                    if(lab[nextRow][nextCol] == 1) pass = true;
                    else if(lab[nextRow][nextCol] == 3) nextDir = -10 * current.dir;
                    else if(lab[nextRow][nextCol] == 4) nextDir = 10 * current.dir;
                    break;
            }

            if(pass) continue;
            q.offer(new Point(nextRow, nextCol, nextDir));
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= M;
    }
}
