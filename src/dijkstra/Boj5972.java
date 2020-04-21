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
 * 백준 5972번: 택배 배송
 *
 * @see https://www.acmicpc.net/problem/5972/
 *
 */
public class Boj5972 {
    private static ArrayList<Node>[] graph;
    private static final int INF = 1_000_000_000;

    private static class Node implements Comparable<Node> {
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

        graph = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph[node1].add(new Node(node2, cost));
            graph[node2].add(new Node(node1, cost));
        }

        System.out.println(dijkstra(0, N - 1));
    }

    private static int dijkstra(int start, int end) {
        int[] dist = new int[end + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            for(Node next: graph[current.node]) {
                if(dist[next.node] <= dist[current.node] + next.cost) continue;
                dist[next.node] = dist[current.node] + next.cost;

                pq.offer(new Node(next.node, dist[next.node]));
            }
        }

        return dist[end];
    }
}
