package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 20677번: Social Distancing
 *
 * @see https://www.acmicpc.net/problem/20677
 *
 */
public class Boj20677 {

    private static final int INF = 1_000_000_000;
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int[][] SPREAD_DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    private static final int ROW = 0, COL = 1;

    private static final char PATIENT = '*';
    private static final char START = 'S';
    private static final char END = 'E';
    private static final char SEAT = '#';

    private static Point start, end;

    private static int N, M;
    private static int[][] distance;
    private static ArrayList<Point> patients = new ArrayList<>();

    private static class Point implements Comparable<Point>{
        int row;
        int col;
        int dist;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point p) {
            return p.dist - this.dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distance = new int[N][M];

        char[][] hospital = new char[N][M];
        for(int i = 0; i < N; i++) {
            String input = br.readLine();

            for(int j = 0; j < M; j++) {
                distance[i][j] = INF;
                hospital[i][j] = input.charAt(j);
                if(hospital[i][j] == PATIENT) patients.add(new Point(i, j));
                if(hospital[i][j] == START) start = new Point(i, j);
                if(hospital[i][j] == END) end = new Point(i, j);
            }
        }

        System.out.println(bfs(hospital));
    }

    /**
     *
     * BFS by sorted cost
     *
     * line 99 ~ 100: find result by sorted decrease cost
     *
     * @param map
     * @return
     */
    private static String bfs(char[][] map) {
        patientFlood();

        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] visit = new boolean[N][M];
        int result = INF;

        pq.offer(new Point(start.row, start.col, distance[start.row][start.col]));
        visit[start.row][start.col] = true;

        while(!pq.isEmpty()) {
            Point current = pq.poll();

            result = Math.min(result, distance[current.row][current.col]);
            if(current.row == end.row && current.col == end.col) return patients.size() == 0 ? "safe": result + "";

            for (final int[] DIRECTION : DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if (outOfRange(nextRow, nextCol) || map[nextRow][nextCol] == SEAT) continue;
                if (visit[nextRow][nextCol] || map[nextRow][nextCol] == PATIENT) continue;
                visit[nextRow][nextCol] = true;

                pq.offer(new Point(nextRow, nextCol, distance[nextRow][nextCol]));
            }
        }

        return "-1";
    }

    /**
     *
     * Covid spreading
     *
     * line 135 ~ 145: make cost by max(|x1 - x2|, |y1 - y2|)
     *
     */
    private static void patientFlood() {
        Queue<Point> q = new LinkedList<>();

        for(Point p: patients) {
            q.offer(p);
            distance[p.row][p.col] = 0;
        }

        while(!q.isEmpty()) {
            Point current = q.poll();

            for (final int[] DIRECTION: SPREAD_DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(distance[nextRow][nextCol] <= distance[current.row][current.col] + 1) continue;
                distance[nextRow][nextCol] = distance[current.row][current.col] + 1;

                q.offer(new Point(nextRow, nextCol));
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
