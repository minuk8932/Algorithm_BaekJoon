package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 4792번: 레드 블루 스패닝 트리
 *
 * @see https://www.acmicpc.net/problem/4792
 *
 */
public class Boj4792 {

    private static int[] parent;
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
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0 && k == 0) break;

            PriorityQueue<Node> blue = new PriorityQueue<>();
            PriorityQueue<Node> red = new PriorityQueue<>();

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                char color = st.nextToken().charAt(0);
                int f = Integer.parseInt(st.nextToken()) - 1;
                int t = Integer.parseInt(st.nextToken()) - 1;

                blue.offer(new Node(f, t, color == 'B' ? 1: 0));
                red.offer(new Node(f, t, color == 'R' ? 1: 0));
            }

            init(n);
            int min = kruskal(blue);

            init(n);
            int max = n - 1 - kruskal(red);

            sb.append(min <= k && max >= k ? 1: 0).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int kruskal(PriorityQueue<Node> pq) {
        int cost = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(merged(current.node1, current.node2)) continue;
            cost += current.cost;
        }

        return cost;
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

        for(int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }
}
