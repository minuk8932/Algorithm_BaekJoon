package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 5901번: Relocation
 *
 * @see https://www.acmicpc.net/problem/5901/
 *
 */
public class Boj5901 {
    private static ArrayList<Node>[] graph;
    private static final int INF = 1_000_000_000;

    private static boolean[] market;
    private static boolean[] visit;
    private static int[] index;
    private static int[][] cost;

    private static ArrayList<Integer> permutation = new ArrayList<>();
    private static int K, N;

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
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        market = new boolean[N];
        index = new int[K];
        cost = new int[K][N];

        for(int i = 0; i < K; i++) {
           index[i] = Integer.parseInt(br.readLine()) - 1;
           market[index[i]] = true;

           Arrays.fill(cost[i], INF);
        }

        graph = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph[node1].add(new Node(node2, cost));
            graph[node2].add(new Node(node1, cost));
        }

        System.out.println(search());
    }

    private static void makePath() {
        for(int i = 1; i <= K; i++) {           // make path
            visit = new boolean[K];
            backTracking(i, i, 0);
        }
    }

    private static void backTracking(int value, int current, int count) {
        if(count == K - 1) {
            permutation.add(value);
            return;
        }

        visit[current - 1] = true;
        for(int next = 1; next <= K; next++) {
            if(visit[next - 1]) continue;

            backTracking(value * 10 + next, next, count + 1);
            visit[next - 1] = false;
        }
    }

    private static int search() {
        makePath();

        for(int i = 0; i < K; i++) {                                            // shortest path for markets
            dijkstra(i);
        }

        int result = INF;

        for(int p: permutation) {
            int loop = p;

            ArrayList<Integer> element = new ArrayList<>();
            while(loop > 0) {
                element.add((loop % 10) - 1);
                loop /= 10;
            }

            int value = 0;

            for(int i = 1; i < K; i++) {
                value += cost[element.get(i - 1)][index[element.get(i)]];       // all markets distance
            }

            int prev = element.get(0);
            int post = element.get(K - 1);

            for(int i = 0; i < N; i++) {
                if(market[i]) continue;
                result = Math.min(result, cost[prev][i] + cost[post][i] + value);
            }
        }

        return result;
    }

    private static void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(index[s], 0));

        cost[s][index[s]] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(current.cost > cost[s][current.node]) continue;

            for(Node next: graph[current.node]) {
                if(cost[s][next.node] <= cost[s][current.node] + next.cost) continue;
                cost[s][next.node] = cost[s][current.node] + next.cost;

                pq.offer(new Node(next.node, cost[s][next.node]));
            }
        }
    }
}
