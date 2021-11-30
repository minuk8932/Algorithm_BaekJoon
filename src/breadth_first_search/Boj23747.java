package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 23747번: 와드
 *
 * @see https://www.acmicpc.net/problem/23747
 *
 */
public class Boj23747 {

    private static final char WARD = 'W';
    private static final char UP = 'U';
    private static final char DOWN = 'D';
    private static final char LEFT = 'L';
    private static final char RIGHT = 'R';
    private static final char OPEN = '.';
    private static final char CLOSE = '#';

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int CIPHER = 10_000;

    private static final String NEW_LINE = "\n";

    private static Set<Integer> warded = new HashSet<>();

    private static boolean[][] visit;
    private static char[][] map;
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

        map = new char[N][M];
        visit = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            String input = br.readLine();

            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        Point start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        char[] cmd = br.readLine().toCharArray();

        search(start, cmd);
        System.out.println(printer());
    }

    private static void search(Point s, char[] cmd) {
        for(char c: cmd) {
            if(c == WARD) {
                warded.add(s.row * CIPHER + s.col);
            }
            else {
                switch (c) {
                    case UP:
                        s.row -= 1;
                        break;

                    case DOWN:
                        s.row += 1;
                        break;

                    case LEFT:
                        s.col -= 1;
                        break;

                    case RIGHT:
                        s.col += 1;
                        break;
                }
            }
        }

        for(int w: warded) {
            floodFill(new Point(w / CIPHER, w % CIPHER));
        }

        holdPosition(s);
    }

    /**
     *
     * User position
     *
     * @param current
     */
    private static void holdPosition(Point current) {
        visit[current.row][current.col] = true;

        for(final int[] DIRECTION: DIRECTIONS) {
            int nextRow = current.row + DIRECTION[ROW];
            int nextCol = current.col + DIRECTION[COL];

            if(outOfRange(nextRow, nextCol)) continue;
            visit[nextRow][nextCol] = true;
        }
    }

    /**
     *
     * Ward position
     *
     * @param start
     */
    private static void floodFill(Point start) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(start);

        char target = map[start.row][start.col];
        visit[start.row][start.col] = true;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(target != map[nextRow][nextCol]) continue;
                if(visit[nextRow][nextCol]) continue;
                visit[nextRow][nextCol] = true;

                q.offer(new Point(nextRow, nextCol));
            }
        }
    }

    private static String printer() {
        StringBuilder sb = new StringBuilder();

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                sb.append(visit[row][col] ? OPEN: CLOSE);
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static boolean outOfRange (int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
