package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author exponential-e
 * 백준 10749번: Superbull
 *
 * @see https://www.acmicpc.net/problem/10749
 *
 */
public class Boj10749 {

    private static int[] parent;
    private static Queue<Node> pq;

    private static class Node {
        int node1;
        int node2;
        long cost;

        public Node(int node1, int node2, long cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        init(N);

        long[] nodes = new long[N];
        for(int i = 0; i < N; i++) {
            nodes[i] = Long.parseLong(br.readLine());
        }

        cartesianProduct(N, nodes);
        System.out.println(mst());
    }

    /**
     *
     * MST
     *
     * Typical MST(kruskal)
     * @Warning: Integer overflow
     *
     */
    private static long mst() {
        long max = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(merged(current.node1, current.node2)) continue;
            max += current.cost;
        }

        return max;
    }

    private static void cartesianProduct(int n, long[] nodes) {
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                pq.offer(new Node(i, j, nodes[i] ^ nodes[j]));
            }
        }
    }

    private static void init(int n) {
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = -1;
        }

        pq = new PriorityQueue<>(Comparator.comparingLong(x -> -x.cost));
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
