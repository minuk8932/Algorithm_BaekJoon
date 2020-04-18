package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 5465번: 곰돌이
 *
 * @see https://www.acmicpc.net/problem/5465/
 *
 */
public class Boj5465 {
    private static final char TREE = 'T';
    private static final char HIVE = 'H';
    private static final char START = 'M';
    private static final char END = 'D';

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static int N, S;
    private static Point start, end;
    private static int[][] beeVisit;
    private static ArrayList<Point> hives = new ArrayList<>();

    private static class Point {
        int row;
        int col;
        int cnt;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][N];
        beeVisit = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(beeVisit[i], -1);
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == HIVE) {
                    hives.add(new Point(i, j));
                    beeVisit[i][j] = 0;
                }
                if (map[i][j] == START) start = new Point(i, j);
                if (map[i][j] == END) end = new Point(i, j);
            }
        }

        spread(map);
        System.out.println(binarySearch(map));
    }

    private static int binarySearch(char[][] map) {
        int start = 0, end = N * N * 2;
        int result = -1;

        while (start <= end) {                              // 0 ~ 2N^2
            int mid = (start + end) / 2;

            if (bfs(map, mid)) {
                start = mid + 1;
                result = mid;
            }
            else {
                end = mid - 1;
            }
        }

        return result;
    }

    private static boolean bfs(char[][] map, int delay) {
        int value = delay * S;
        if(value >= beeVisit[start.row][start.col]) return false;                   // blocked

        boolean[][] visit = new boolean[N][N];

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(start.row, start.col, value));

        visit[start.row][start.col] = true;

        while (!q.isEmpty()) {
            Point current = q.poll();

            for (final int[] DIRECTION : DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];
                int nextCount = current.cnt + 1;

                if (outOfRange(nextRow, nextCol) || map[nextRow][nextCol] == TREE) continue;
                if (nextCount >= beeVisit[nextRow][nextCol] || visit[nextRow][nextCol]) continue;
                visit[nextRow][nextCol] = true;

                if (end.row == nextRow && end.col == nextCol) return true;          // safe
                q.offer(new Point(nextRow, nextCol, nextCount));
            }
        }

        return false;
    }

    private static void spread(char[][] map) {
        Queue<Point> q = new LinkedList<>();

        for (Point start : hives) {
            q.offer(start);
        }

        while (!q.isEmpty()) {                                  // bee moves
            Point current = q.poll();

            for (final int[] DIRECTION : DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if (outOfRange(nextRow, nextCol)) continue;
                if (map[nextRow][nextCol] == TREE || map[nextRow][nextCol] == END) continue;
                if (beeVisit[nextRow][nextCol] != -1) continue;
                beeVisit[nextRow][nextCol] = beeVisit[current.row][current.col] + S;

                q.offer(new Point(nextRow, nextCol));
            }
        }

        beeVisit[end.row][end.col] = Integer.MAX_VALUE;         // can not reach
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
