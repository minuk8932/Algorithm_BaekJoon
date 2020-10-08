package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 5852번: Island Travels
 *
 * @see https://www.acmicpc.net/problem/5852
 *
 */
public class Boj5852 {
    private static char[][] map;
    private static int R, C;
    private static int size, answer;
    private static int[][] set;
    private static int[][] visit;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1, INF = 1_000_000_000;
    private static final char BLOCKED = '.', ISLAND = 'X';

    private static class Point {
        int row;
        int col;
        int dep;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point(int row, int col, int dep) {
            this.row = row;
            this.col = col;
            this.dep = dep;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        set = new int[R][C];
        for(int i = 0; i < R; i++) {
            String line = br.readLine();

            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                set[i][j] = -1;
            }
        }

        answer = INF;
        makeSet();
        graphModeling();
        getResult();

        System.out.println(answer);
    }

    private static void bfs(Point p) {
        Queue<Point> q = new LinkedList<>();
        q.offer(p);

        set[p.row][p.col] = size;               // make groups

        while(!q.isEmpty()) {
            Point current = q.poll();

            for (final int[] DIRECTION : DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if (outOfRange(nextRow, nextCol)) continue;
                if (map[nextRow][nextCol] != ISLAND || set[nextRow][nextCol] != -1) continue;
                set[nextRow][nextCol] = size;

                q.offer(new Point(nextRow, nextCol));
            }
        }
    }

    private static void makeSet() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != ISLAND || set[i][j] != -1) continue;

                bfs(new Point(i, j));
                size++;
            }
        }
    }

    private static void graphModeling () {
        visit = new int[size][size];

        for(int i = 0; i < size; i++) {
            Arrays.fill(visit[i], INF);
        }

        for (int i = 0; i < size; i++) {
            Point start = new Point(-1, -1);
            boolean flag = false;

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if(flag) break;
                    if (set[r][c] != i) continue;

                    start = new Point(r, c);                // start target find
                    flag = true;
                }
            }

            int[][] dist = new int[R][C];
            for(int x = 0; x < R; x++) {
                Arrays.fill(dist[x], INF);
            }

            Queue<Point> q = new LinkedList<>();

            q.offer(new Point(start.row, start.col, 0));
            dist[start.row][start.col] = 0;

            while (!q.isEmpty()) {
                Point current = q.poll();

                if (set[current.row][current.col] != -1) {                     // if island, update depth
                    if (current.dep < visit[i][set[current.row][current.col]])
                        visit[i][set[current.row][current.col]] = current.dep;
                }

                for (final int[] DIRECTION: DIRECTIONS) {
                    int nextRow = current.row + DIRECTION[ROW];
                    int nextCol = current.col + DIRECTION[COL];

                    if (outOfRange(nextRow, nextCol)) continue;
                    if (map[nextRow][nextCol] == BLOCKED) continue;

                    if (map[nextRow][nextCol] == ISLAND) {
                        if (dist[nextRow][nextCol] <= current.dep) continue;
                        dist[nextRow][nextCol] = current.dep;                       // cost maintenance
                    }
                    else {
                        if(dist[nextRow][nextCol] <= current.dep + 1) continue;
                        dist[nextRow][nextCol] = current.dep + 1;                   // add cost
                    }

                    q.offer(new Point(nextRow, nextCol, dist[nextRow][nextCol]));
                }
            }
        }
    }

    private static void getResult () {
        int[][] result = new int[size][1 << 15];
        for(int i = 0; i < result.length; i++) {
            Arrays.fill(result[i], INF);
        }

        for (int i = 0; i < size; i++) {            // make bit mask
            result[i][1 << i] = 0;
        }

        ArrayList<Integer> bits = new ArrayList<>();
        int full = 1 << size;

        for (int m = 1; m < full; m++) {
            bits.add(m);
        }

        Collections.sort(bits);

        for (int b: bits) {
            for (int i = 0; i < size; i++) {
                if (result[i][b] == INF) continue;

                for (int j = 0; j < size; j++) {
                    int nBit = b | (1 << j);

                    if (result[j][nBit] <= result[i][b] + visit[i][j]) continue;
                    result[j][nBit] = result[i][b] + visit[i][j];               // find the shortest path by bits
                }
            }
        }

        for (int i = 0; i < size; i++) {
            answer = Math.min(answer, result[i][full - 1]);
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= R || col >= C;
    }
}
