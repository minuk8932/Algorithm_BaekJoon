package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16393번: Lost Map
 *
 * @see https://www.acmicpc.net/problem/16393
 *
 */
public class Boj16393 {
    private static PriorityQueue<Path> pq = new PriorityQueue<>();
    private static int[] parent;

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static class Path implements Comparable<Path>{
        int node1;
        int node2;
        int cost;

        public Path(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path p) {
            return this.cost < p.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init(N);

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                pq.offer(new Path(i, j, Integer.parseInt(st.nextToken())));
            }
        }

        System.out.println(kruskal());
    }

    private static void init(int n) {
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    private static String kruskal() {               // time complexity O(ElogE), if time limit more short then must use prim
        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()) {
            Path current = pq.poll();

            if(merged(current.node1, current.node2)) continue;
            sb.append(current.node1 + 1).append(SPACE).append(current.node2 + 1).append(NEW_LINE);
        }

        return sb.toString();
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
}
