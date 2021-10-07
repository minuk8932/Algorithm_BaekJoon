package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1368 {

    private static int[] parent;
    private static int[] dug;

    private static Queue<Hole> pq;

    private static class Hole {
        int node1;
        int node2;
        int cost;

        public Hole(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
    }

    /**
     *
     * Main
     *
     * line 51: Just digging merged with node N
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init(N);

        for (int i = 0; i < N; i++) {
            dug[i] = Integer.parseInt(br.readLine());
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(hole -> hole.cost));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i == j) pq.offer(new Hole(N, i, dug[i]));
                else pq.offer(new Hole(i, j, cost));
            }
        }

        System.out.println(kruskal());
    }

    private static void init(int n) {
        dug = new int[n];

        parent = new int[n + 1];
        Arrays.fill(parent, -1);
    }

    private static int kruskal() {
        int cost = 0;

        while (!pq.isEmpty()) {
            Hole current = pq.poll();
            int x = find(current.node1);
            int y = find(current.node2);

            if (merged(x, y)) continue;

            cost += current.cost;
        }

        return cost;
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        if (x == y) return true;

        if (parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        } else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }
}
