package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 2350번: 대 운하
 *
 * @see https://www.acmicpc.net/problem/2350
 *
 */
public class Boj2350 {

    private static Queue<Canal> pq;
    private static List<Node>[] path;

    private static int[] parent;
    private static int[] visit;

    private static final int INF = 1_000;
    private static final String NEW_LINE = "\n";

    private static class Canal{
        int city1;
        int city2;
        int width;

        public Canal(int city1, int city2, int width) {
            this.city1 = city1;
            this.city2 = city2;
            this.width = width;
        }
    }

    private static class Node {
        int vertex;
        int edge;

        public Node(int vertex, int edge) {
            this.vertex = vertex;
            this.edge = edge;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        init(N);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Canal(i, j, w));
        }

        kruskal();
        StringBuilder sb = new StringBuilder();
        visit = new int[N];
        int vcost = 1;

        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            sb.append(search(start, end, vcost++)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int search(int s, int e, int v) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(s, INF));
        visit[s] = v;

        while(!q.isEmpty()) {
            Node current = q.poll();

            for (Node next : path[current.vertex]) {
                if (visit[next.vertex] == v) continue;
                visit[next.vertex] = v;

                int cost = Math.min(next.edge, current.edge);
                if(next.vertex == e) return cost;

                q.offer(new Node(next.vertex, cost));
            }
        }

        return 0;
    }

    /**
     *
     * Kruskal
     *
     * line 114 ~ 115: make set & setting tree path
     *
     */
    private static void kruskal() {
        while(!pq.isEmpty()) {
            Canal current = pq.poll();

            if(merged(current.city1, current.city2)) continue;
            path[current.city1].add(new Node(current.city2, current.width));
            path[current.city2].add(new Node(current.city1, current.width));
        }
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }

    private static void init(int n) {
        parent = new int[n];

        pq = new PriorityQueue<>(Comparator.comparingInt(canal -> -canal.width));
        path = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            parent[i] = -1;
            path[i] = new ArrayList<>();
        }
    }
}
