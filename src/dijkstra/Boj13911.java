package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13911번: 집 구하기
 *
 * @see https://www.acmicpc.net/problem/13911/
 *
 */
public class Boj13911 {
    private static boolean[] notHome;
    private static ArrayList<Node>[] path;
    private static long[][] cost;

    private static class Node implements Comparable<Node> {
        int node;
        long cost;

        public Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1 : 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        path = new ArrayList[V];
        cost = new long[2][V];
        notHome = new boolean[V];

        for (int i = 0; i < V; i++) {
            path[i] = new ArrayList<>();
            cost[0][i] = Long.MAX_VALUE;
            cost[1][i] = Long.MAX_VALUE;
        }

        while (E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            long w = Long.parseLong(st.nextToken());

            path[u].add(new Node(v, w));
            path[v].add(new Node(u, w));
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        int[] mac = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            mac[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        long y = Long.parseLong(st.nextToken());

        int[] sta = new int[S];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            sta[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        dijkstra(cost[0], mac);                 // mc donald
        dijkstra(cost[1], sta);                 // starbucks

        System.out.println(getResult(x, y));
    }

    private static void dijkstra(long[] cost, int[] arr) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int start: arr) {
            pq.offer(new Node(start, 0));
            notHome[start] = true;

            cost[start] = 0;
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if(cost[current.node] < current.cost) continue;

            for (Node next : path[current.node]) {
                if (cost[next.node] > cost[current.node] + next.cost) {
                    cost[next.node] = cost[current.node] + next.cost;

                    pq.offer(new Node(next.node, cost[next.node]));
                }
            }
        }
    }

    private static long getResult(long x, long y){
        long min = Long.MAX_VALUE;

        for(int i = 0; i < notHome.length; i++){
            if(notHome[i]) continue;                            // mc or star
            if(cost[0][i] > x || cost[1][i] > y) continue;      // limit not satisfied

            long sum = cost[0][i] + cost[1][i];
            if(sum < min) min = sum;
        }

        return min == Long.MAX_VALUE ? -1: min;
    }
}
