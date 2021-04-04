package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1185번: 유럽 여행
 *
 * @see https://www.acmicpc.net/problem/1185
 *
 */
public class Boj1185 {

    private static int[] parent;
    private static long[] cost;

    private static PriorityQueue<Node> pq = new PriorityQueue<>();

    private static class Node implements Comparable<Node>{
        int vertex1;
        int vertex2;
        long edge;

        public Node(int vertex1, int vertex2, long edge) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.edge = edge;
        }

        @Override
        public int compareTo(Node n) {
            return this.edge < n.edge ? -1: 1;
        }
    }

    /**
     *
     * Main
     *
     * line 60: make sum (visit costs + edge cost)
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        cost = new long[N];
        parent = new int[N];
        long min = 1_001;

        for(int i = 0; i < N; i++) {
            parent[i] = -1;
            cost[i] = Long.parseLong(br.readLine());
            min = Math.min(cost[i], min);
        }

        while(P-- > 0) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken()) << 1;

            pq.offer(new Node(v1, v2, c + cost[v1] + cost[v2]));
        }

        System.out.println(kruskal() + min);
    }

    private static long kruskal() {
        long result = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(merged(current.vertex1, current.vertex2)) continue;
            result += current.edge;
        }

        return result;
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
