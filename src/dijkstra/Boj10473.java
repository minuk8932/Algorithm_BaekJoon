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
 * 백준 10473번: 인간 대포
 *
 * @see https://www.acmicpc.net/problem/10473/
 *
 */
public class Boj10473 {
    private static Coordinate start;
    private static Coordinate end;
    private static ArrayList<Node>[] graph;

    private static final double INF = 10_000_000;

    private static class Coordinate {
        double x;
        double y;

        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Node implements Comparable<Node>{
        int node;
        double cost;

        public Node(int node, double cost) {
            this.node = node;
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
        start = new Coordinate(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        end = new Coordinate(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));

        int N = Integer.parseInt(br.readLine());
        Coordinate[] fires = new Coordinate[N + 2];

        fires[0] = start;
        fires[N + 1] = end;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            fires[i] = new Coordinate(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        settings(N, fires);
        System.out.println(dijkstra(N));
    }

    private static double dijkstra(int n) {
        double[] cost = new double[n + 2];
        Arrays.fill(cost, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        cost[0] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(current.cost > cost[current.node]) continue;

            for(Node next: graph[current.node]) {
                if(cost[next.node] <= cost[current.node] + next.cost) continue;
                cost[next.node] = cost[current.node] + next.cost;

                pq.offer(new Node(next.node, cost[next.node]));
            }
        }

        return cost[n + 1];
    }

    private static void settings (int n, Coordinate[] f) {
        graph = new ArrayList[n + 2];
        for(int i = 0; i < n + 2; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Node(n + 1, getDistance(start, end) / 5));

        for(int i = 1; i <= n; i++) {
            double d = getDistance(start, f[i]);
            double walk = d / 5;
            graph[0].add(new Node(i, walk));                                            // start -> canon

            d = getDistance(end, f[i]);
            graph[i].add(new Node(n + 1, Math.abs(d - 50) / 5 + 2));        // canon -> end
        }

        for(int node1 = 1; node1 <= n; node1++) {
            for(int node2 = 1; node2 <= n; node2++) {
                if(node1 == node2) continue;
                double d = getDistance(f[node1], f[node2]);
                double walk = d / 5;

                graph[node1].add(new Node(node2, Math.min(walk, Math.abs(d - 50) / 5 + 2)));        // canon -> canon
            }
        }
    }

    private static double getDistance(Coordinate c1, Coordinate c2) {
        double x = c1.x - c2.x;
        double y = c1.y - c2.y;
        return Math.sqrt(x * x + y * y);
    }
}
