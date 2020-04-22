package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11900번: 차이 그래프
 *
 * @see https://www.acmicpc.net/problem/11900/
 *
 */
public class Boj11900 {
    private static final String NEW_LINE = "\n";
    private static final int INF = 2_000_000_000;

    private static int N;
    private static int[] A;
    private static int[] dist;
    private static int[] parent;

    private static class Node implements Comparable<Node> {
        int node;
        int cost;

        public Node(int node, int cost) {
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
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        parent = new int[N];
        parent[0] = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            parent[i] = -1;
        }

        makeGraph();

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int idx = e - s;
            if(idx < 0) idx += N;

            s = find(s);
            e = find(e);

            sb.append(e != s ? -1: dist[idx]).append(NEW_LINE);             // shortest path
        }

        System.out.println(sb.toString());
    }

    private static void makeGraph() {
        dist = new int[N];
        Arrays.fill(dist, INF);

        for(int i = 0; i < N; i++) {
            if(dist[i] != INF) continue;
            dijkstra(i);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(current.cost > dist[current.node]) continue;

            for(int next = 0; next < N; next++) {
                if(current.node == next) continue;
                int diff = current.node - next;

                if(diff < 0) diff += N;
                if(A[diff] == 0) continue;

                if(dist[next] <= dist[current.node] + A[diff]) continue;
                dist[next] = dist[current.node] + A[diff];

                merge(start, next);                                         // make path set

                pq.offer(new Node(next, dist[next]));
            }
        }
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge (int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }
}
