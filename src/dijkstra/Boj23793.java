package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 23793번: 두 단계 최단 경로1
 *
 * @see https://www.acmicpc.net/problem/23793
 *
 */
public class Boj23793 {

    private static List<Node>[] path;
    private static long[] dist;

    private static final long INF = 1_000_000_000_000L;

    private static class Node {
        int node;
        long cost;

        public Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        path = new ArrayList[N];
        dist = new long[N];
        for (int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            long w = Long.parseLong(st.nextToken());

            path[u].add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;
        int Z = Integer.parseInt(st.nextToken()) - 1;

        long prev = dijkstra(X, Y, -1);
        long post = dijkstra(Y, Z, -1);
        long cost = prev + post;

        System.out.println((prev == -1 || post == -1 ? -1: cost) + " " + dijkstra(X, Z, Y));
    }

    /**
     *
     * Dijkstra for especially path
     *
     * line 87: just except
     *
     * @param from
     * @param to
     * @param except
     * @return
     */
    private static long dijkstra(int from, int to, int except) {
        Arrays.fill(dist, INF);

        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(node -> node.cost));
        pq.offer(new Node(from, -1));

        dist[from] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(dist[current.node] < current.cost) continue;

            for(Node next: path[current.node]) {
                if(except == next.node) continue;
                if(dist[next.node] <= dist[current.node] + next.cost) continue;
                dist[next.node] = dist[current.node] + next.cost;

                pq.offer(new Node(next.node, dist[next.node]));
            }
        }

        return dist[to] == INF ? -1: dist[to];
    }
}
