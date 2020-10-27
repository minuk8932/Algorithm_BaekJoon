package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14544번: The Other Way
 *
 * @see https://www.acmicpc.net/problem/14544
 *
 */
public class Boj14544 {
    private static ArrayList<Node>[] path;
    private static final int MOD = 1_000_000_009;
    private static final long INF = 1_000_000_000_000_000L;

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
        int S = Integer.parseInt(st.nextToken()) - 1;
        int E = Integer.parseInt(st.nextToken()) - 1;

        path = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());

            path[A].add(new Node(B, C));
            path[B].add(new Node(A, C));
        }

        System.out.println(dijkstra(N, S, E));
    }

    private static int dijkstra(int n, int s, int e) {
        long[] dest = new long[n];
        int[] count = new int[n];
        Arrays.fill(dest, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));

        dest[s] = 0;
        count[s] = 1;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if (current.cost > dest[current.node]) continue;                                    // dynamic

            for(Node next: path[current.node]) {
                if(dest[next.node] <= dest[current.node] + next.cost){
                    if(dest[next.node] == dest[current.node] + next.cost)
                        count[next.node] = modulation(count[next.node], count[current.node]);   // count add
                    continue;
                }

                dest[next.node] = dest[current.node] + next.cost;
                count[next.node] = count[current.node];

                pq.offer(new Node(next.node, dest[next.node]));
            }
        }

        return count[e];
    }

    private static int modulation(int n, int c) {
        return ((n % MOD) + (c % MOD)) % MOD;
    }
}
