import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj20390 {
    private static PriorityQueue<Node> pq = new PriorityQueue<>();
    private static long A, B, C, D;
    private static final long LIMIT = 10_000_000_000L;

    private static int[] parent;

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
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());
        D = Long.parseLong(st.nextToken());

        long[] X = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            X[i] = Long.parseLong(st.nextToken()) % C;
        }

        A %= C;
        B %= C;

        init(X);
        System.out.println(mst());
    }

    private static long mst(){
        long cost = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(merged(current.node1, current.node2)) continue;
            cost += current.cost;
        }

        return cost;
    }

    private static void init(long[] x) {
        parent = new int[x.length];

        for(int i = 0; i < x.length; i++) {
            parent[i] = -1;

            for(int j = i + 1; j < x.length; j++) {
                pq.offer(new Node(i, j, distance(x[i], x[j])));
            }
        }
    }

    private static long distance(long x1, long x2) {
        long a = calculator(x1, A);
        long b = calculator(x2, B);

        long result = ((a + b) % C) ^ D;
        return result;
    }

    private static long calculator(long x, long v) {
        long r = (x * v) % C;
        if(x < LIMIT && v < LIMIT) return r;

        BigInteger X = new BigInteger(x + "");
        BigInteger V = new BigInteger(v + "");

        X = (X.multiply(V)).mod(new BigInteger(C + ""));
        return Long.parseLong(X.toString());
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        else return parent[x] = find(parent[x]);
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
