package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 2917번: 늑대 사냥꾼
 *
 * @see https://www.acmicpc.net/problem/2917/
 *
 */
public class Boj2917 {
    private static final int INF = 10_000_000;
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final char TREE = '+', START = 'V', END = 'J';

    private static int[][] cost;
    private static int N, M;

    private static Point start, end;
    private static Queue<Point> q = new LinkedList<>();

    private static class Point implements Comparable<Point>{
        int row;
        int col;
        int cost;
        int min;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point(int row, int col, int cost, int min) {
            this.row = row;
            this.col = col;
            this.cost = cost;
            this.min = min;
        }

        @Override
        public int compareTo(Point p) {
            return this.cost > p.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        cost = new int[N][M];

        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == TREE){
                    q.offer(new Point(i ,j));
                    cost[i][j] = 1;
            }

                if (map[i][j] == START) start = new Point(i, j);
                if (map[i][j] == END) end = new Point(i, j);
            }
        }

        makeCost();
        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(start.row, start.col, cost[start.row][start.col], Math.min(INF, cost[start.row][start.col] - 1)));

        while(!pq.isEmpty()) {
            Point current = pq.poll();

            if(current.row == end.row && current.col == end.col) return current.min;    // find min cost

            if(cost[current.row][current.col] == 0) continue;
            cost[current.row][current.col] = 0;

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(cost[nextRow][nextCol] == 0) continue;

                pq.offer(new Point(nextRow, nextCol, cost[nextRow][nextCol], Math.min(cost[nextRow][nextCol] - 1, current.min)));
            }
        }

        return 0;
    }

    private static void makeCost() {
        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(cost[nextRow][nextCol] != 0) continue;
                cost[nextRow][nextCol] = cost[current.row][current.col] + 1;     // make tree distance

                q.offer(new Point(nextRow, nextCol));
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
