package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16405번: Treehouses
 *
 * @see https://www.acmicpc.net/problem/16405
 *
 */
public class Boj16405 {
    private static int[] parent;
    private static PriorityQueue<Cable> pq = new PriorityQueue<>();

    private static class Cabin {
        double x;
        double y;

        public Cabin(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Cable implements Comparable<Cable>{
        int node1;
        int node2;
        double cost;

        public Cable(int node1, int node2, double cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cable c) {
            return this.cost < c.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken()) - 1;
        int p = Integer.parseInt(st.nextToken());

        Cabin[] cab = new Cabin[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cab[i] = new Cabin(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        init(n, cab);

        while(p-- > 0) {
            st = new StringTokenizer(br.readLine());
            merged(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);     // already linked
        }

        for(int i = 1; i <= e; i++) {                                                                      // walk through
            merged(i, 0);
        }

        System.out.println(String.format("%.3f", mst()));
    }

    private static double mst() {
        double cost = 0;

        while(!pq.isEmpty()) {
            Cable current = pq.poll();

            if(merged(current.node1, current.node2)) continue;
            cost += current.cost;
        }

        return cost;
    }

    private static void init(int n, Cabin[] c) {
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = -1;

            for(int j = i + 1; j < n; j++) {                                // each cabin linked by distance
                pq.offer(new Cable(i, j, euclideanDistance(c[i], c[j])));
            }
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

        if(parent[x] < parent[y]){
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }

    private static double euclideanDistance(Cabin a, Cabin b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }
}
