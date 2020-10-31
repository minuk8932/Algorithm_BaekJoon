package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2126번: 지진
 *
 * @see https://www.acmicpc.net/problem/2126
 *
 */
public class Boj2126 {
    private static ArrayList<Node> list = new ArrayList<>();
    private static double F;

    private static final int INF = 1_000_000_000;
    private static int N;
    private static int[] parent;

    private static class Node implements Comparable<Node>{
        int node1;
        int node2;
        double cost;
        double time;

        public Node(int node1, int node2, double cost, double time) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
            this.time = time;
        }

        public Node(int node1, int node2, double cost) {
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
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        F = Double.parseDouble(st.nextToken());

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());
            long t = Long.parseLong(st.nextToken());

            list.add(new Node(i, j, c, t));
        }

        double result = binarySearch();
        System.out.println(result <= 0 ? "0.0000": String.format("%.4f", result));
    }

    private static double binarySearch() {
        int loop = 100;
        double start = 0;
        double end = INF;
        double result = 0;

        while(loop-- > 0){
            double mid = (start + end) / 2;

            if(mst(mid)){                   // find target value
                start = mid + 1;
                result = mid;
            }
            else{
                end = mid - 1;
            }
        }

        return result;
    }

    private static boolean mst(double mid) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(Node data: list) {
            pq.offer(new Node(data.node1, data.node2, data.cost + mid * data.time));
        }

        init();

        double cost = F;

        while(!pq.isEmpty()){                   // kruskal
            Node current = pq.poll();
            if(merged(current.node1, current.node2)) continue;
            cost -= current.cost;
        }

        return cost > 0;
    }

    private static void init() {
        parent = new int[N];
        for(int i = 0; i < N; i++) {
            parent[i] = -1;
        }
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
