package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16156번: 장애물 달리기
 *
 * @see https://www.acmicpc.net/problem/16156/
 *
 */
public class Boj16156 {
    private static long[][] dist;
    private static int N, M;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final long INF = 1_000_000_000_000L;

    private static final String NEW_LINE = "\n";

    private static class Node implements Comparable<Node>{
        int row;
        int col;
        int target;
        long cost;

        public Node(int row, int col, int target, long cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
            this.target = target;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new long[N][M];
        int[][] map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = INF;
            }
        }

        System.out.println(dijkstra(map));
    }

    private static String dijkstra(int[][] map) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int start = 0; start < N; start++) {                        // find shortest path by reversed
            pq.offer(new Node(start, M - 1, start, 0));
            dist[start][M - 1] = map[start][M - 1];
        }

        int[] arrived = new int[N];

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(dist[current.row][current.col] < current.cost) continue;
            if(current.col == 0) arrived[current.target]++;             // start point not contains

            for (int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(dist[nextRow][nextCol] <= dist[current.row][current.col] + map[nextRow][nextCol]) continue;
                dist[nextRow][nextCol] = dist[current.row][current.col] + map[nextRow][nextCol];

                pq.offer(new Node(nextRow, nextCol, current.target, dist[nextRow][nextCol]));
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(arrived[i]).append(NEW_LINE);
        }

        return sb.toString();
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
