package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 20668번: 카트라이더
 *
 * @see https://www.acmicpc.net/problem/20668
 *
 */
public class Boj20668 {
    private static int N;
    private static ArrayList<Node>[] adj;

    private static final long INF = Long.MAX_VALUE;
    private static final long FACTORIAL_TEN = 3628800L;

    private static class Node{
        private int node;
        private int speed;
        private long cost;

        Node(int node, int speed, long cost) {
            this.node = node;
            this.speed = speed;
            this.cost = cost;
        }

        public int getNode() {
            return this.node;
        }

        public int getSpeed() {
            return this.speed;
        }

        public long getCost() {
            return this.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, k, FACTORIAL_TEN * l));
            adj[b].add(new Node(a, k, FACTORIAL_TEN * l));
        }

        long x = dijkstra();
        System.out.print(x / FACTORIAL_TEN);
        x %= FACTORIAL_TEN;

        double y = (double) x / FACTORIAL_TEN;
        System.out.println(String.format("%.9f", y).substring(1));
    }

    /**
     *
     * Dijkstra
     *
     * line 101: Speed limit
     *
     * @return
     */
    private static long dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(Node::getCost));
        pq.offer(new Node(0, 1, 0L));

        long[][] dist = new long[N][11];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }

        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (dist[current.node][current.speed] < current.cost) continue;

            for (Node next : adj[current.node]) {
                for (int d = -1; d <= 1; d++) {
                    int speedLimit = current.speed + d;
                    if (1 > speedLimit || speedLimit > next.speed) continue;

                    long nextCost = current.cost + (next.cost / speedLimit);
                    if (dist[next.node][speedLimit] <= nextCost) continue;
                    dist[next.node][speedLimit] = nextCost;

                    pq.offer(new Node(next.node, speedLimit, nextCost));
                }
            }
        }

        long min = INF;
        for (int i = 1; i <= 10; i++) {
            min = Math.min(min, dist[N - 1][i]);
        }

        return min;
    }
}