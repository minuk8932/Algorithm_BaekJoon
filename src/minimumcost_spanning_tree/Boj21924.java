package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 21924번: 도시 건설
 *
 * @see https://www.acmicpc.net/problem/21924
 *
 */
public class Boj21924 {

    private static long total;
    private static int[] parent;
    private static PriorityQueue<Node> pq = new PriorityQueue<>();

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
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            long cost = Long.parseLong(st.nextToken());

            pq.offer(new Node(node1, node2, cost));
            total += cost;
        }

        System.out.println(mst());
    }

    /**
     *
     * MST
     *
     * line 77 ~ 79: if all cities didn't link, then return -1
     *
     * @return
     */
    private static long mst() {
        long cost = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(merged(current.node1, current.node2)) continue;
            cost += current.cost;
        }

        int set = 0;
        for(int p: parent) {
            if(p < 0) set++;
        }

        return set >= 2 ? -1: total - cost;
    }

    private static void init(int n) {
        parent = new int[n];
        for(int i = 0; i < n; i++){
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
