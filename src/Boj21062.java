import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj21062 {

    private static int[][] parent = new int[2][];
    private static final long INF = 1_000_000_000_000_000L;

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

        List<Long> values1 = kruskal(0, pq);

        while(m2-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            pq.offer(new Node(u, v, w));
        }

        List<Long> values2 = kruskal(1, pq);

        /**
         * input
         * 4 3 3 2
         * 0 1 1
         * 1 2 1
         * 2 3 4
         * 0 1 3
         * 1 2 3
         *
         * output 24
         * answer 22
         * => fixed but, still blocked 12%
         *
         */
        System.out.println(getMinCost(values1, values2, n1, n2));
    }

    private static long getMinCost(List<Long> v1, List<Long> v2, int n1, int n2) {
        long[] mst = {makeSum(v1), makeSum(v2)};
        long[][] prefixMax = {prefixSumMax(v1), prefixSumMax(v2)};
        long[][] prefixMin = {prefixSumMin(v1), prefixSumMin(v2)};

        long max = Math.min(calculation(n1, mst, 0, 1, prefixMax[1])
                , calculation(n2, mst, 1, 0, prefixMax[0]));
        long min = Math.min(calculation(n1, mst, 0, 1, prefixMin[1])
                , calculation(n2, mst, 1, 0, prefixMin[0]));

        return Math.min(max, min);
    }

    private static long calculation(int n, long[] mst, int src, int snk, long[] prefix) {
        long result = INF;

        for(int i = 0; i < prefix.length; i++) {
            result = Math.min(result
                    , mst[src] * (i + 1) + (mst[snk] * n - prefix[i] * (n - 1)));
        }

        return result;
    }

    private static long[] prefixSumMin(List<Long> value) {
        int size = value.size();
        long[] sum = new long[size + 1];

        for(int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + value.get(i - 1);
        }

        return sum;
    }

    private static long[] prefixSumMax(List<Long> value) {
        int size = value.size();
        long[] sum = new long[size + 1];

        for(int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + value.get(size - i);
        }

        return sum;
    }

    private static long makeSum(List<Long> value) {
        long sum = 0;

        for(long v: value) {
            sum += v;
        }

        return sum;
    }

    private static List<Long> kruskal (int idx, PriorityQueue<Node> pq){
        List<Long> value = new ArrayList<>();

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(merged(idx, current.node1, current.node2)) continue;
            value.add(current.cost);
        }

        return value;
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
