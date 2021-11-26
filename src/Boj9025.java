import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj9025 {

    private static int[] parent;
    private static Queue<Node> pq;

    private static final String NEW_LINE = "\n";

    private static class Node {
        int node1;
        int node2;
        int cost;

        public Node(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken()) - 1;

            init(n);

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                int cost = Integer.parseInt(st.nextToken());

                pq.offer(new Node(from, to, cost));
            }

            sb.append(processing(s, t)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int processing(int S, int T) {
        int min = 0;

        while(!pq.isEmpty() && find(S) != find(T)) {
            Node current = pq.poll();

            if(merged(current.node1, current.node2)) continue;
            min = current.cost;
        }

        return min;
    }

    private static void init(int n) {
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = -1;
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(x -> -x.cost));
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
