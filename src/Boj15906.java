import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15906 {
    private static final int INF = 1_000_000_000;

    private static boolean[][][] change;
    private static int N;
    private static boolean[] rows;
    private static boolean[] cols;
    private static Point end = new Point(0, 0, 0);

    private static final char WARP = '#';
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static class Point {
        int row;
        int col;
        int mode;

        public Point(int row, int col, int mode) {
            this.row = row;
            this.col = col;
            this.mode = mode;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        end = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
        rows = new boolean[N];
        cols = new boolean[N];
        change = new boolean[2][N][N];

        char[][] map = new char[N][N];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);

                if(map[i][j] == WARP) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        System.out.println(bfs(t, map));
    }

    private static int bfs (int t, char[][] map) {
        int[][][] visit = new int[2][N][N];

        for(int i = 0; i < N; i++) {
            Arrays.fill(visit[0][i], INF);
            Arrays.fill(visit[1][i], INF);
        }

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, 0));
        q.offer(new Point(0, 0, 1));

        visit[0][0][0] = 1;
        visit[1][0][0] = 1 + t;

        while(!q.isEmpty()) {
            Point current = q.poll();
            int nextMode = current.mode ^ 1;

            if(current.mode == 0){
                for(final int[] DIRECTION: DIRECTIONS) {
                    int nextRow = current.row + DIRECTION[ROW];
                    int nextCol = current.col + DIRECTION[COL];

                    if(outOfRange(nextRow, nextCol)) continue;
                    if(visit[current.mode][nextRow][nextCol] <= visit[current.mode][current.row][current.col] + 1) continue;
                    visit[current.mode][nextRow][nextCol] = visit[current.mode][current.row][current.col] + 1;

                    q.offer(new Point(nextRow, nextCol, current.mode));
                }

                if(change[nextMode][current.row][current.col])  continue;
                change[nextMode][current.row][current.col] = true;
                visit[nextMode][current.row][current.col] = visit[current.mode][current.row][current.col] + t;

                q.offer(new Point(current.row, current.col, nextMode));
            }
            else {
                int[] dir = {0, 0};

                if(rows[current.row]) {
                    dir[0] = findDecrease(0, current.col, cols, current.col);
                    dir[1] = findIncrease(current.col + 1, N, cols, current.col);

                    for(int next: dir) {
                        if(next == -1) continue;

                        if(visit[current.mode][current.row][next] <= visit[current.mode][current.row][current.col] + 1) continue;
                        visit[current.mode][current.row][next] = visit[current.mode][current.row][current.col] + 1;

                        q.offer(new Point(current.row, next, current.mode));
                    }

                    if(change[nextMode][current.row][current.col]) continue;
                    change[nextMode][current.row][current.col] = true;

                    q.offer(new Point(current.row, current.col, nextMode));
                }

                if(cols[current.col]) {
                    dir[0] = findDecrease(0, current.row, rows, current.row);
                    dir[1] = findIncrease(current.row + 1, N, rows, current.row);

                    for(int next: dir) {
                        if(next == -1) continue;
                        if(visit[current.mode][next][current.col] <= visit[current.mode][current.row][current.col] + 1) continue;
                        visit[current.mode][next][current.col] = visit[current.mode][current.row][current.col] + 1;

                        q.offer(new Point(next, current.col, current.mode));
                    }

                    if(change[nextMode][current.row][current.col]) continue;
                    change[nextMode][current.row][current.col] = true;

                    q.offer(new Point(current.row, current.col, nextMode));
                }
            }
        }

        return Math.min(visit[0][end.row][end.col], visit[1][end.row][end.col]) - 1;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }

    private static int findIncrease (int start, int end, boolean[] target, int src) {
        for(int idx = start; idx < end; idx++) {
            if(target[src]) return idx;
        }

        return -1;
    }

    private static int findDecrease (int start, int end, boolean[] target, int src) {
        for(int idx = start; idx >= end; idx--) {
            if(target[src]) return idx;
        }

        return -1;
    }
}
