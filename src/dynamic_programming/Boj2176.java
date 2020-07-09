package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2176번: 합리적인 이동경로
 *
 * @see https://www.acmicpc.net/problem/2176/
 *
 */
public class Boj2176 {
    private static ArrayList<Node>[] path;
    private static int[] dp;
    private static int[][] dist;

    private static final int INF = 1_000_000_000;

    public static class Node implements Comparable<Node>{
        int node;
        int cost;

        public Node(int node, int cost) {
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

        dp = new int[N + 1];
        path = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++) {
            path[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            path[u].add(new Node(v, c));
            path[v].add(new Node(u, c));
        }

        dijkstra(N);
        System.out.println(recursion(1));
    }

    private static int recursion(int current) {                     // find all cases
        if (current == 2) return 1;

        if(dp[current] != 0) return dp[current];
        int result = 0;

        for(Node next: path[current]) {
            if(dist[current][2] <= dist[next.node][2]) continue;
            result += recursion(next.node);
        }

        return dp[current] = result;
    }

    private static void dijkstra(int n) {
        dist = new int[n + 1][n + 1];

        for(int start = 1; start <= n; start++) {                   // make path from 1 ~ to 2
            PriorityQueue<Node> pq = new PriorityQueue<>();
            Arrays.fill(dist[start], INF);

            dist[start][start] = 0;
            pq.offer(new Node(start, 0));

            while(!pq.isEmpty()) {
                Node current = pq.poll();

                if (current.cost > dist[start][current.node]) continue;

                for(Node next: path[current.node]) {
                    if(dist[start][next.node] <= dist[start][current.node] + next.cost) continue;
                    dist[start][next.node] = dist[start][current.node] + next.cost;

                    pq.offer(new Node(next.node, dist[start][next.node]));
                }
            }
        }
    }
}
