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
            cost[i] = Long.MAX_VALUE;
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

        System.out.println(dijstra());
    }

    private static double dijstra() {
        double max = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        cost[0] = 0;

        pq.offer(new Node(0, 0));

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(cost[current.node] < current.cost) continue;

            for(Node next: path[current.node]) {                // check traffic jam
                long nextCost = cost[current.node] + (jam[current.node][next.node] ? calculation(current.node, next.cost): next.cost);

                if(cost[next.node] <= nextCost) continue;
                cost[next.node] = nextCost;
                max = Math.max(cost[next.node], max);

                pq.offer(new Node(next.node, cost[next.node]));
            }
        }

        return max / 2.0;
    }

    private static long calculation(int cur, long ncost) {                  // cal quitting time
        if(cost[cur] >= E) return ncost;
        long diff = -cost[cur];

        if (cost[cur] < S) {
            diff += S;
            long interval = E - S;

            if (diff >= ncost) return ncost;
            if ((ncost - diff) * 2 <= interval) return ncost * 2 - diff;

            return ncost + interval / 2;
        }
        else {
            diff += E;
            if (ncost * 2 <= diff) return ncost * 2;

            return ncost + diff / 2;
        }
    }
}
