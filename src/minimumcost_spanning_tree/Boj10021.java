package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10021번: Watering the Fields
 *
 * @see https://www.acmicpc.net/problem/10021
 *
 */
public class Boj10021 {

    private static int[] parent;
    private static PriorityQueue<Node> pq = new PriorityQueue<>();

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

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        init(N);

        Coordinate[] coors = new Coordinate[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            coors[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        graphModeling(coors, C);
        System.out.println(MST());
    }

    /**
     *
     * Graph modeling
     *
     * line 77: except cheap cost.
     *
     * @param c
     * @param limit
     */
    private static void graphModeling(Coordinate[] c, int limit) {
        for(int i = 0; i < c.length; i++) {
            for(int j = i + 1; j < c.length; j++) {
                int cost = euclideanDistance(c[i], c[j]);
                if(cost < limit) continue;

                pq.offer(new Node(i, j, cost));
            }
        }
    }

    /**
     *
     * MST
     *
     * line 103 ~ 104: if graph has more than two groups, then -1.
     *
     * @return
     */
    private static int MST() {
        int cost = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(merged(current.node1, current.node2)) continue;
            cost += current.cost;
        }

        int set = 0;
        for(int i = 0; i < parent.length; i++) {
            if(parent[i] < 0) set++;
        }

        return set > 1 ? -1: cost;
    }

    private static int find(int x){
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

    private static int euclideanDistance(Coordinate c1, Coordinate c2) {
        return (c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y);
    }
}
