package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 4722번: Underground Cables
 *
 * @see https://www.acmicpc.net/problem/4722
 *
 */
public class Boj4722 {

    private static int[] parent;
    private static Queue<Node> pq;

    private static final String NEW_LINE = "\n";

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Node {
        int node1;
        int node2;
        double cost;

        public Node(int node1, int node2, double cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            parent = new int[N];
            Arrays.fill(parent, -1);

            Coordinate[] coors = new Coordinate[N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                coors[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            makeGraph(coors);
            sb.append(kruskal()).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static String kruskal() {
        double total = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(merged(current.node1, current.node2)) continue;
            total += current.cost;
        }

        return String.format("%.2f", total);
    }

    private static void makeGraph(Coordinate[] c) {
        pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.cost));

        for(int node1 = 0; node1 < c.length; node1++){
            for(int node2 = node1 + 1; node2 < c.length; node2++) {
                pq.offer(new Node(node1, node2, pow(c[node1], c[node2])));
            }
        }
    }

    private static double pow(Coordinate c1, Coordinate c2) {
        return Math.sqrt((c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y));
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
