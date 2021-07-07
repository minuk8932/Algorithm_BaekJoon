package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1245번: 농장 관리
 *
 * @see https://www.acmicpc.net/problem/1245
 *
 */
public class Boj1245 {

    private static int N, M;
    private static int[] parent;

    private static PriorityQueue<Farm> pq = new PriorityQueue<>();

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    private static final int ROW = 0, COL = 1;

    private static class Farm implements Comparable<Farm>{
        int summit;
        Point point;

        public Farm(int summit, Point point) {
            this.summit = summit;
            this.point = point;
        }

        @Override
        public int compareTo(Farm f) {
            return f.summit - this.summit;
        }
    }

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

        init();

        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                pq.offer(new Farm(map[i][j], new Point(i, j)));
            }
        }

        System.out.println(clustering(map));
    }

    /**
     *
     * Clustering
     *
     * line 104: clustering fields by summits
     *
     * @param map
     * @return
     */
    private static int clustering(int[][] map) {
        int count = 0;

        while(!pq.isEmpty()) {
            Farm start = pq.poll();
            if(map[start.point.row][start.point.col] == 0) break;
            if(parent[find(index(start.point))] != -1) continue;

            Queue<Point> q = new LinkedList<>();
            q.offer(start.point);
            count++;

            while(!q.isEmpty()) {
                Point current = q.poll();

                for(final int[] DIRECTION: DIRECTIONS) {
                     int nextRow = current.row + DIRECTION[ROW];
                     int nextCol = current.col + DIRECTION[COL];

                     Point next = new Point(nextRow, nextCol);

                     if(outOfRange(next)) continue;
                     if(map[current.row][current.col] < map[nextRow][nextCol]) continue;
                     if(merged(index(current), index(next))) continue;

                     q.offer(next);
                }
            }
        }

        return count;
    }

    private static int index(Point p) {
        return p.row * M + p.col;
    }

    private static boolean outOfRange(Point p) {
        return p.row < 0 || p.row >= N || p.col < 0 || p.col >= M;
    }

    private static void init() {
        parent = new int[N * M];

        for(int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }
}
