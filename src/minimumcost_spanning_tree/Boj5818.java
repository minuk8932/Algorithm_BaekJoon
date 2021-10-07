package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 5818번: SPIJUNI
 *
 * @see https://www.acmicpc.net/problem/5818
 *
 */
public class Boj5818 {

    private static int[] parent;
    private static int[] message;

    private static Queue<Spy> pq;

    private static class Spy {
        int node1;
        int node2;
        int cost;

        public Spy(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init(N);

        pq = new PriorityQueue<>(Comparator.comparingInt(spy -> spy.cost));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i == j) continue;

                pq.offer(new Spy(i, j, cost));
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            message[i] = Integer.parseInt(st.nextToken());
            pq.offer(new Spy(N, i, message[i]));
        }

        System.out.println(kruskal());
    }

    private static void init(int n) {
        message = new int[n];

        parent = new int[n + 1];
        Arrays.fill(parent, -1);
    }

    private static int kruskal() {
        int cost = 0;

        while (!pq.isEmpty()) {
            Spy current = pq.poll();
            if (merged(current.node1, current.node2)) continue;

            cost += current.cost;
        }

        return cost;
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

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
