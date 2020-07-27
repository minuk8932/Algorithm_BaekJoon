package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11781번: 퇴근 시간
 *
 * @see https://www.acmicpc.net/problem/11781/
 *
 */
public class Boj11781 {
    private static ArrayList<Node>[] path;
    private static boolean[][] jam;
    private static long[] cost;
    private static long S, E;
    private static long INF = 4_000_000_000_000_000_000L;

    private static class Node implements Comparable<Node>{
        int node;
        long cost;

        public Node(int node, long cost) {
            this.node = node;
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
        S = Long.parseLong(st.nextToken()) * 2;
        E = Long.parseLong(st.nextToken()) * 2;

        path = new ArrayList[N];
        jam = new boolean[N][N];
        cost = new long[N];
        for(int i = 0; i < N ;i++) {
            path[i] = new ArrayList<>();
            cost[i] = INF;
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            long L = Long.parseLong(st.nextToken()) * 2;
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());

            path[A].add(new Node(B, L));
            path[B].add(new Node(A, L));

            if(t1 == 1) jam[A][B] = true;
            if(t2 == 1) jam[B][A] = true;
        }

        long result = dijstra();
        System.out.println(result / 2 + (result % 2 == 1 ? ".5": ""));
    }

    private static long dijstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        cost[0] = 0;

        pq.offer(new Node(0, 0));

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(cost[current.node] < current.cost) continue;

            for(Node next: path[current.node]) {                // check traffic jam
                long nextCost =  (jam[current.node][next.node]
                        ? calculation(current.node, next.cost): next.cost + cost[current.node]);

                if(cost[next.node] <= nextCost) continue;
                cost[next.node] = nextCost;

                pq.offer(new Node(next.node, cost[next.node]));
            }
        }

        long max = 0;
        for(int i = 0; i < cost.length; i++) {
            max = Math.max(max, cost[i]);
        }

        return max;
    }

    private static long calculation(int node, long ncost) {                  // cal quitting time
        long diff = -cost[node];

        if (cost[node] < S) {
            if (cost[node] + ncost >= S) {
                diff += S;

                long interval = Math.min(E - S, (ncost - diff) * 2);
                return cost[node] + ncost + interval / 2;
            }

            return cost[node] + ncost;
        }

        if (cost[node] >= E) return cost[node] + ncost;

        diff += E;
        long interval = Math.min(diff, ncost * 2);
        return cost[node] + ncost + interval / 2;
    }
}
