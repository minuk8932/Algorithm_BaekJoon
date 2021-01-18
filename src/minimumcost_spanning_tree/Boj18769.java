package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18769번: 그리드 네트워크
 *
 * @see https://www.acmicpc.net/problem/18769
 *
 */
public class Boj18769 {
    private static int[] parent;
    private static PriorityQueue<Node> pq;

    private static final String NEW_LINE = "\n";

    private static class Node implements Comparable<Node>{
        int node1;
        int node2;
        int cost;

        public Node(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            init(R * C);

            for(int i = 0; i < R; i++) {
                input(br.readLine(), C - 1, i * C, 1);
            }

            for(int i = 0; i < R - 1; i++) {
                input(br.readLine(), C, i * C, C);
            }

            sb.append(kruskal()).append(NEW_LINE);          // make mst with kruskal
        }

        System.out.println(sb.toString());
    }

    private static void input(String line, int end, int idx, int adder) {
        StringTokenizer st = new StringTokenizer(line);

        for (int j = 0; j < end; j++) {
            int cost = Integer.parseInt(st.nextToken());
            int cur = idx + j;

            pq.offer(new Node(cur, cur + adder, cost));
        }
    }

    private static int kruskal() {
        int cost = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(merged(current.node1, current.node2)) continue;

            cost += current.cost;
        }

        return cost;
    }

    private static void init(int n) {
        pq = new PriorityQueue<>();
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
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
}
