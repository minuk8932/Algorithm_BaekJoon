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
 * 백준 15422번: Bumped
 *
 * @see https://www.acmicpc.net/problem/15422/
 *
 */
public class Boj15422 {
    private static ArrayList<Node>[] route;
    private static ArrayList<Pair> flight = new ArrayList<>();

    private static final long INF = 1_000_000_000_000L;

    private static class Node implements Comparable<Node> {
        int node;
        long cost;

        public Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1: 1;
        }
    }

    private static class Pair {
        int u;
        int v;

        public Pair(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        route = new ArrayList[n];
        for(int i = 0; i < n; i++){
            route[i] = new ArrayList<>();
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            route[node1].add(new Node(node2, cost));
            route[node2].add(new Node(node1, cost));
        }

        while(f-- > 0) {
            st = new StringTokenizer(br.readLine());
            flight.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        System.out.println(consideringAllCases(n, s, t));
    }

    private static long consideringAllCases(int n, int s, int t) {
        long result = dijkstra(n, s, t);                                // s to t
        int len = flight.size();

        for(int i = 0; i < len; i++) {                                  // s to u, v to t
            Pair current = flight.get(i);
            result = Math.min(result, dijkstra(n, s, current.u) + dijkstra(n, current.v, t));       // flight is one direction
        }

        return result;
    }

    private static long dijkstra(int n, int start, int end) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            for(Node next: route[current.node]) {
                if(dist[next.node] > dist[current.node] + next.cost) {
                    dist[next.node] = dist[current.node] + next.cost;

                    pq.offer(new Node(next.node, dist[next.node]));
                }
            }
        }

        return dist[end];
    }
}
