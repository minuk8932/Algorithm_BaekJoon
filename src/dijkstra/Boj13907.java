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
 * 백준 13907번: 세금
 *
 * @see https://www.acmicpc.net/problem/13907
 *
 */
public class Boj13907 {
    private static ArrayList<Node>[] path;
    private static int[][] dist;
    private static int min;

    private static final int INF = 1_000_000_000;
    private static final String NEW_LINE = "\n";

    private static class Node implements Comparable<Node>{
        int node;
        int hop;
        int cost;

        public Node(int node, int cost, int hop) {
            this.node = node;
            this.hop = hop;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()) - 1;
        int D = Integer.parseInt(st.nextToken()) - 1;

        dist = new int[N][N];
        path = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
            Arrays.fill(dist[i], INF);
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            path[node1].add(new Node(node2, cost, 0));
            path[node2].add(new Node(node1, cost, 0));
        }

        dijkstra(S);                // get shortest path

        min = INF;
        pathSet(D);                 // first query

        StringBuilder sb = new StringBuilder();
        sb.append(min).append(NEW_LINE);

        while(K-- > 0) {
            int increment = Integer.parseInt(br.readLine());
            min = INF;

            for(int limit = 0; limit < N; limit++) {
                int cost = limit * increment + dist[D][limit];      // tax add per roads

                if(min > cost) min = cost;
                dist[D][limit] = cost;                              // increased tax applied
            }

            sb.append(min).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void pathSet(int end){
        for(int p = 0; p < dist[end].length; p++) {
            if(dist[end][p] == INF) continue;

            if(min > dist[end][p]) min = dist[end][p];
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));

        dist[start][0] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if (isFind(current)) continue;                                  // this node already find cost
            if (current.hop >= dist.length - 1) continue;
            if (dist[current.node][current.hop] < current.cost) continue;

            for(Node next: path[current.node]) {
                if(dist[next.node][current.hop + 1] <= dist[current.node][current.hop] + next.cost) continue;
                dist[next.node][current.hop + 1] = dist[current.node][current.hop] + next.cost;

                pq.offer(new Node(next.node, dist[next.node][current.hop + 1], current.hop + 1));
            }
        }
    }

    private static boolean isFind(Node current) {
        int index = 0;

        while(index < current.hop) {                                        // if both cost & hop count less, need not
            if (dist[current.node][index++] < current.cost) return true;
        }

        return false;
    }
}
