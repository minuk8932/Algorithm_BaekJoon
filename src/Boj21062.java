import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj21062 {

    private static int[][] parent = new int[2][];

    private static class Node implements Comparable<Node>{
        int node1;
        int node2;
        long cost;

        public Node(int node1, int node2, long cost) {
            this.node1 = node1;
            this.node2 = node2;
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
        int n1 = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());

        init(0, n1);
        init(1, n2);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        while(m1-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            pq.offer(new Node(u, v, w));
        }

        long value1 = kruskal(0, pq);

        while(m2-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            pq.offer(new Node(u, v, w));
        }

        long value2 = kruskal(1, pq);

        System.out.println(Math.min(value1 + value2 * n1, value2 + value1 * n2));
    }

    private static long kruskal (int idx, PriorityQueue<Node> pq){
        long cost = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(merged(idx, current.node1, current.node2)) continue;
            cost += current.cost;
        }

        return cost;
    }

    private static void init(int index, int n) {
        parent[index] = new int[n];

        for(int i = 0; i < n; i++) {
            parent[index][i] = -1;
        }
    }

    private static int find (int index, int x) {
        if(parent[index][x] < 0) return x;
        return parent[index][x] = find(index, parent[index][x]);
    }

    private static boolean merged (int index, int x, int y) {
        x = find(index, x);
        y = find(index, y);

        if(x == y) return true;

        if(parent[index][x] < parent[index][y]) {
            parent[index][x] += parent[index][y];
            parent[index][y] = x;
        }
        else {
            parent[index][y] += parent[index][x];
            parent[index][x] = y;
        }

        return false;
    }
}
