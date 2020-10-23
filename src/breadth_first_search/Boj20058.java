package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20058번: 마법사 상어와 파이어스톰
 *
 * @see https://www.acmicpc.net/problem/20058
 *
 */
public class Boj20058 {
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static int N;
    private static int[][] map;

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
        int n = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        N = 1 << n;
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while(Q-- > 0) {
            int L = Integer.parseInt(st.nextToken());
            if(L != 0) rotation(1 << L);
            melting();
        }

        System.out.println(bfs());
    }

    private static void rotation(int size) {
        Point start = new Point(-size, -size);
        int[][] copy = new int[N][N];

        int limit = N / size;
        int adder = 0;

        for(int x = 0; x < limit; x++) {
            start.row += size;

            for(int y = 0; y < limit; y++) {
                start.col += size;

                Point p = new Point(adder + size - 1, start.col);
                for(int row = start.row; row < start.row + size; row++) {           // rotation boxes
                    p.row = adder + size - 1;

                    for(int col = start.col; col < start.col + size; col++) {
                        copy[row][col] = map[p.row][p.col];
                        p.row--;
                    }

                    p.col++;
                }
            }

            adder += size;
            start.col = -size;
        }

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                map[row][col] = copy[row][col];
            }
        }
    }

    private static void melting() {
        LinkedList<Point> pairs = new LinkedList<>();

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                if(map[row][col] == 0) continue;
                int count = 0;

                for(final int[] DIRECTION: DIRECTIONS) {
                    int nextRow = row + DIRECTION[ROW];
                    int nextCol = col + DIRECTION[COL];

                    if (outOfRange(nextRow, nextCol)) continue;
                    if (map[nextRow][nextCol] == 0) continue;
                    count++;
                }

                if(count < 3) pairs.add(new Point(row, col));           // pretend concurrency mistake
            }
        }

        while(!pairs.isEmpty()) {
            Point remove = pairs.remove();
            map[remove.row][remove.col]--;
        }
    }

    private static String bfs() {
        int size = 0;
        int total = 0;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                if(map[row][col] == 0) continue;
                total += map[row][col];
                map[row][col] = 0;

                Queue<Point> q = new LinkedList<>();
                q.offer(new Point(row, col));

                int count = 1;

                while(!q.isEmpty()) {
                    Point current = q.poll();

                    for(final int[] DIRECTION: DIRECTIONS) {
                        int nextRow = current.row + DIRECTION[ROW];
                        int nextCol = current.col + DIRECTION[COL];

                        if (outOfRange(nextRow, nextCol)) continue;
                        if (map[nextRow][nextCol] == 0) continue;
                        total += map[nextRow][nextCol];
                        map[nextRow][nextCol] = 0;

                        count++;
                        q.offer(new Point(nextRow, nextCol));
                    }
                }

                size = Math.max(count, size);
            }
        }

        return total + "\n" + size;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
