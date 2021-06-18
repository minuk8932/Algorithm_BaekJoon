package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21610번: 마법사 상어와 비바라기
 *
 * @see https://www.acmicpc.net/problem/21610
 *
 */
public class Boj21610 {

    private static final int[][] DIRECTIONS = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    private static final int[][] MAGIC_DIRECTIONS = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int CIPHER = 100;

    private static int[][] buckets;
    private static int N;

    private static List<Point> clouds = new LinkedList<>();
    private static List<Point> increments = new LinkedList<>();
    private static Set<Integer> except;

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

        buckets = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                buckets[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds.add(new Point(N - 1, 0));
        clouds.add(new Point(N - 1, 1));
        clouds.add(new Point(N - 2, 0));
        clouds.add(new Point(N - 2, 1));

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            except = new HashSet<>();

            cloudsMoving(d, s);
            raining();
            waterCopy();
            cloudMaking();
        }

        System.out.println(waterAmount());
    }

    private static void cloudMaking() {
        for (int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                if(except.contains(row * CIPHER + col)) continue;
                if(buckets[row][col] < 2) continue;

                clouds.add(new Point(row, col));
                buckets[row][col] -= 2;
            }
        }
    }

    private static void waterCopy() {
        for(int key: except) {
            int row = key / CIPHER;
            int col = key % CIPHER;

            for(final int[] DIRECTION: MAGIC_DIRECTIONS) {
                int nextRow = row + DIRECTION[ROW];
                int nextCol = col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(buckets[nextRow][nextCol] <= 0) continue;
                buckets[row][col]++;
            }
        }
    }

    private static void raining() {
        while(!clouds.isEmpty()) {
            Point current = clouds.remove(0);

            buckets[current.row][current.col]++;
            except.add(current.row * CIPHER + current.col);
        }
    }

    private static void cloudsMoving(int dir, int separate) {
        int size = clouds.size();

        while(size-- > 0) {
            Point current = clouds.remove(0);
            int row = current.row + DIRECTIONS[dir][ROW] * separate;
            int col = current.col + DIRECTIONS[dir][COL] * separate;

            row = shift(row);
            col = shift(col);

            clouds.add(new Point(row, col));
        }
    }

    private static int waterAmount() {
        int L = 0;

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                L += buckets[i][j];
            }
        }

        return L;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }

    private static int shift(int index) {
        return ((index % N) + N) % N;
    }
}
