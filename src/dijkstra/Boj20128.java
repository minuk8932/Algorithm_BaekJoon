package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20128번: Parity Constraint Shortest Path
 *
 * @see https://www.acmicpc.net/problem/20128
 *
 */
public class Boj20128 {

    private static final long INF = 1_000_000_000_000_000L;
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static ArrayList<Node>[] path;
    private static long[][] dist;

    private static class Node implements Comparable<Node>{
        int node;
        int count;
        long cost;

        public Node(int node, int count, long cost) {
            this.node = node;
            this.count = count;
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
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        path = new ArrayList[N];
        dist = new long[2][N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
            dist[0][i] = dist[1][i] = INF;
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            long cost = Long.parseLong(st.nextToken());

            path[node1].add(new Node(node2, 0, cost));
            path[node2].add(new Node(node1, 0, cost));
        }

        System.out.println(dijkstra(N));
    }

    /**
     *
     * Find shortest path, 1 -> all
     *
     * line 83: except path that already has shortest path
     * line 87 ~ 95: find next shortest path, and check parity constraint
     *
     * @param n
     * @return
     */
    private static String dijkstra(int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0L));

        dist[0][0] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if (current.cost > dist[current.count][current.node]) continue;

            for(Node next: path[current.node]) {
                long cost = next.cost + dist[current.count][current.node];
                int nextCount = (int) (cost % 2);

                if(dist[nextCount][next.node] <= cost) continue;
                dist[nextCount][next.node] = cost;

                pq.offer(new Node(next.node, nextCount, dist[nextCount][next.node]));
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(dist[1][i] == INF ? -1: dist[1][i]).append(SPACE)
                    .append(dist[0][i] == INF ? -1: dist[0][i]).append(NEW_LINE);
        }

        return sb.toString();
    }
}
