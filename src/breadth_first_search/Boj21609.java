package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21609번: 상어 중학교
 *
 * @see https://www.acmicpc.net/problem/21609
 *
 */
public class Boj21609 {

    private static final int BLACK = -1;
    private static final int RAINBOW = 0;
    private static final int EMPTY = -2;
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static final long CIPHER = 1_000L;

    private static int[][] map;
    private static boolean[][] visit;
    private static int N;
    private static PriorityQueue<Long> pq;

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
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(map[i], EMPTY);

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(auto(M));
    }

    private static int auto(int m) {
        int result = 0;

        while(true) {
            pq = new PriorityQueue<>();
            visit = new boolean[N][N];

            for (int block = 1; block <= m; block++) {
                bfs(block);
            }

            if(pq.isEmpty()) break;

            long target = -pq.poll();
            int col = (int) (target % CIPHER);
            target /= CIPHER;
            int row = (int) (target % CIPHER);
            target /= CIPHER;

            removing(new Point(row, col));
            target /= CIPHER;

            result += target * target;

            drop();
            rotate();
            drop();
        }

        return result;
    }

    /**
     *
     * Removing
     *
     * line 105, 118: Remove blocks what selected.
     *
     * @param p
     */
    private static void removing(Point p) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] used = new boolean[N][N];

        int color = map[p.row][p.col];

        q.offer(p);
        used[p.row][p.col] = true;
        map[p.row][p.col] = EMPTY;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(map[nextRow][nextCol] != color && map[nextRow][nextCol] != RAINBOW) continue;
                if(used[nextRow][nextCol]) continue;
                used[nextRow][nextCol] = true;
                map[nextRow][nextCol] = EMPTY;

                q.offer(new Point(nextRow, nextCol));
            }
        }
    }

    /**
     *
     * Drop
     *
     * line 138 ~ 140: Find the highest empty space, and drop blocks.
     *
     */
    private static void drop() {
        for(int row = N - 1; row > 0; row--) {
            for(int col = 0; col < N; col++) {
                if(map[row][col] != EMPTY) continue;

                int r = row;
                while(r > 0 && map[r][col] == EMPTY) {
                    r--;
                }

                if(map[r][col] == BLACK) continue;
                map[row][col] = map[r][col];
                map[r][col] = EMPTY;
            }
        }
    }

    /**
     *
     * Rotate
     *
     * line 161: Rotate board CCW.
     *
     */
    private static void rotate() {
        int[][] arr = new int[N][N];

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                arr[row][col] = map[col][N - row - 1];
            }
        }

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                map[row][col] = arr[row][col];
            }
        }
    }

    /**
     *
     * BFS
     *
     * line 191 ~ 223: Find the largest block size, and min row, col values
     * line 225 ~ 229: Get candidate, and remove rainbow visit state.
     *
     * @param b
     */
    private static void bfs(int b) {
        Queue<Point> q = new ArrayDeque<>();
        List<Point> rainbows = new LinkedList<>();

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                if(map[row][col] != b) continue;
                if(visit[row][col]) continue;
                visit[row][col] = true;

                int size = 1;
                Point center = new Point(row, col);
                q.offer(new Point(row, col));

                while(!q.isEmpty()) {
                    Point current = q.poll();

                    for(final int[] DIRECTION: DIRECTIONS) {
                        int nextRow = current.row + DIRECTION[ROW];
                        int nextCol = current.col + DIRECTION[COL];

                        if(outOfRange(nextRow, nextCol)) continue;
                        if(map[nextRow][nextCol] != b && map[nextRow][nextCol] != RAINBOW) continue;
                        if(visit[nextRow][nextCol]) continue;
                        visit[nextRow][nextCol] = true;

                        size++;

                        if(map[nextRow][nextCol] == RAINBOW){
                            rainbows.add(new Point(nextRow, nextCol));
                        }
                        else {
                            if(nextRow <= center.row){
                                if(center.row == nextRow) center.col = Math.min(center.col, nextCol);
                                else center.col = nextCol;

                                center.row = nextRow;
                            }
                        }

                        q.offer(new Point(nextRow, nextCol));
                    }
                }

                int rSize = rainbows.size();
                dissolve(rainbows);
                if(size == rSize || size < 2) continue;

                pq.offer(-(((CIPHER * size + rSize) * CIPHER + center.row) * CIPHER + center.col));
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= N;
    }

    private static void dissolve(List<Point> rainbows) {
        while(!rainbows.isEmpty()) {
            Point current = rainbows.remove(0);
            visit[current.row][current.col] = false;
        }
    }
}
