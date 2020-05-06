package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9344번: 도로
 *
 * @see https://www.acmicpc.net/problem/9344/
 *
 */
public class Boj9344 {
    private static int[] parent;
    private static PriorityQueue<Node> path;

    private static final String Y = "YES\n";
    private static final String NO = "NO\n";

    private static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken()) - 1;
            int q = Integer.parseInt(st.nextToken()) - 1;

            init(N);

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken());

                path.offer(new Node(Math.min(u, v), Math.max(u, v), w));
            }

            sb.append(kruskal(Math.min(p, q), Math.max(p, q)) ? Y: NO);
        }

        System.out.println(sb.toString());
    }

    private static boolean kruskal(int node1, int node2) {
        while(!path.isEmpty()) {
            Node current = path.poll();
            if(merged(current.from, current.to)) continue;
            if(node1 == current.from && node2 == current.to) return true;       // must build
        }

        return false;
    }

    private static void init(int n) {
        parent = new int[n];
        path = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged (int x, int y) {
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
}
