package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 23563번: 벽 타기
 *
 * @see https://www.acmicpc.net/problem/23563
 *
 */
public class Boj23563 {

    private static final int INF = 1_000_000_000;

    private static final char BLOCK = '#';
    private static final char START = 'S';
    private static final char END = 'E';

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0;
    private static final int COL = 1;

    private static Point start;
    private static Point end;

    private static char[][] map;
    private static boolean[][] adjBlock;

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
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        adjBlock = new boolean[H][W];
        for(int i = 0; i < H; i++) {
            String line = br.readLine();

            for(int j = 0; j < W; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == START) start = new Point(i, j);
                if(map[i][j] == END) end = new Point(i, j);
            }
        }

        init(H, W);
        System.out.println(bfs(H, W));
    }

    /**
     *
     * 0-1 BFS
     *
     * line 95 ~ 102: wall riding possibility
     * line 107 ~ 108: 0-1 process
     *
     * @param h
     * @param w
     * @return
     */
    private static int bfs(int h, int w) {
        int[][] visit = new int[h][w];
        for(int i = 0; i < h; i++) {
            Arrays.fill(visit[i], INF);
        }

        Deque<Point> deq = new ArrayDeque<>();
        deq.offer(start);
        visit[start.row][start.col] = 0;

        while(!deq.isEmpty()) {
            Point current = deq.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(map[nextRow][nextCol] == BLOCK) continue;

                int cost = visit[current.row][current.col];
                boolean path = false;
                if (adjBlock[current.row][current.col] && adjBlock[nextRow][nextCol]) {
                    path = true;
                }
                else {
                    cost += 1;
                }

                if(visit[nextRow][nextCol] <= cost) continue;
                visit[nextRow][nextCol] = cost;

                if (path) deq.offerFirst(new Point(nextRow, nextCol));
                else deq.offerLast(new Point(nextRow, nextCol));
            }
        }

        return visit[end.row][end.col];
    }

    private static void init(int h, int w) {
        for(int row = 0; row < h; row++) {
            for(int col = 0; col < w; col++) {
                if(map[row][col] == BLOCK) continue;
                adjBlock[row][col] = isNearPost(new Point(row, col));
            }
        }
    }

    private static boolean isNearPost(Point current) {
        for(final int[] DIRECTION: DIRECTIONS) {
            int nextRow = current.row + DIRECTION[ROW];
            int nextCol = current.col + DIRECTION[COL];

            if(map[nextRow][nextCol] == BLOCK) return true;
        }

        return false;
    }
}
