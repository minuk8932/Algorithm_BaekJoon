package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14167번: Moocast
 *
 * @see https://www.acmicpc.net/problem/14167
 *
 */
public class Boj14167 {
    private static PriorityQueue<Cows> pq = new PriorityQueue<>();
    private static int[] parent;

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Cows implements Comparable<Cows> {
        int node1;
        int node2;
        int cost;

        public Cows(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cows c) {
            return this.cost - c.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Coordinate[] cow = new Coordinate[N];
        parent = new int[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cow[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            parent[i] = -1;
        }

        makeGraph(cow);
        System.out.println(mst());
    }

    private static int mst() {
        int cost = 0;

        while(!pq.isEmpty()) {
            Cows current = pq.poll();
            if(merged(current.node1, current.node2)) continue;

            cost = Math.max(cost, current.cost);            // find max distance of minimum
        }

        return cost;
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

    private static void makeGraph(Coordinate[] c) {
        for(int i = 0; i < c.length; i++) {                 // graph modeling
            for(int j = i + 1; j < c.length; j++) {
                pq.offer(new Cows(i, j, getEuclideanDistancePow(c[i], c[j])));
            }
        }
    }

    private static int getEuclideanDistancePow(Coordinate c1, Coordinate c2) {
        return (int) (Math.pow(c1.x - c2.x, 2) + Math.pow(c1.y - c2.y, 2));
    }
}
