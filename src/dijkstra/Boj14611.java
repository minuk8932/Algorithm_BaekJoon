package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14611번: 월요병
 *
 * @see https://www.acmicpc.net/problem/14611/
 *
 */
public class Boj14611 {
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    private static final int ROW = 0, COL = 1;

    private static final int NOPE = -1;
    private static final int BLOCKED = -2;

    private static int N, M;

    private static long[][] cost;
    private static int[][] map;

    private static ArrayList<Node>[] path = new ArrayList[3];

    private static class Node implements Comparable<Node> {
        int row;
        int col;
        long cost;

        public Node (int row, int col, long cost){
            this.row = row;
            this.col = col;
            this.cost = cost;
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

        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cost = new long[N][M];
        for(int i = 0; i < N; i++){
            Arrays.fill(cost[i], Long.MAX_VALUE);
        }

        init();                         // make list, (1 ~ N - 1, 0) + (0 ~ N - 2, 0 ~ M - 1) & (N - 1, 0 ~ M - 2) + (0 ~ N - 2, 0 ~ M - 1)

        for(int i = 0; i < 2; i++){
            seperate(i);
        }

        System.out.println(getResult());
    }

    private static void init() {
        for(int i = 0; i < 3; i++){
            path[i] = new ArrayList<>();
        }

        for(int i = 1; i < N; i++){
            path[0].add(new Node(i, 0, map[i][0]));
            path[2].add(new Node(i - 1, M - 1, map[i - 1][M - 1]));
        }

        for(int i = 0; i < M - 1; i++){
            path[1].add(new Node(N - 1, i, map[N - 1][i]));
            path[2].add(new Node(0, i + 1, map[0][i + 1]));
        }
    }

    private static void seperate (int index) {
        for (Node from: path[index]) {                      // find shortest path from
            if(map[from.row][from.col] == NOPE) continue;

            dijkstra(from);
        }
    }

    private static void dijkstra(Node start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        cost[start.row][start.col] = map[start.row][start.col] == BLOCKED ? 0: map[start.row][start.col];
        pq.offer(new Node(start.row, start.col, cost[start.row][start.col]));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol) || map[nextRow][nextCol] == NOPE) continue;
                long nextCost = map[nextRow][nextCol] == -2 ? 0: map[nextRow][nextCol];

                if(cost[nextRow][nextCol] > cost[current.row][current.col] + nextCost){
                    cost[nextRow][nextCol] = cost[current.row][current.col] + nextCost;

                    pq.offer(new Node(nextRow, nextCol, cost[nextRow][nextCol]));
                }
            }
        }
    }

    private static boolean outOfRange(int row, int col){
        return row < 0 || row >= N || col < 0 || col >= M;
    }

    private static long getResult() {
        long result = Long.MAX_VALUE;

        for(Node destination: path[2]){                                     // get result from destination
            if (map[destination.row][destination.col] == NOPE) continue;
            result = Math.min(cost[destination.row][destination.col], result);
        }

        return result == Long.MAX_VALUE ? -1: result;
    }
}
