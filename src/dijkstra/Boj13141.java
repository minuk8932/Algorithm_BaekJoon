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
 * 백준 13141번: ignition
 *
 * @see https://www.acmicpc.net/problem/13141/
 *
 */
public class Boj13141 {
    private static ArrayList<Node>[] graph;
    private static double[][] cost;
    private static double[][] burned;

    private static int N;

    private static final double INF = 50_000_000;

    private static class Node implements Comparable<Node>{
        int node;
        double cost;

        public Node(int node, double cost){
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        burned = new double[3][M];
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()) - 1;
            int E = Integer.parseInt(st.nextToken()) - 1;
            double L = Double.parseDouble(st.nextToken());

            graph[S].add(new Node(E, L));
            graph[E].add(new Node(S, L));
            burned[0][i] = S;                   // save input
            burned[1][i] = E;
            burned[2][i] = L;
        }

        double result = INF;
        cost = new double[N][N];

        for(int i = 0; i < N; i++){
            Arrays.fill(cost[i], INF);
            dijkstra(i);
        }

        for(int i = 0; i < N; i++){                     // considering all cases
            double sum = 0;

            for(int j = 0; j < M; j++){
                int node1 = (int) burned[0][j];
                int node2 = (int) burned[1][j];
                double w = burned[2][j];

                sum = Math.max(cost[i][node1] + cost[i][node2] + w, sum);
            }

            result = Math.min(sum / 2, result);
        }

        System.out.println(result);
    }

    private static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        cost[start][start] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(cost[start][current.node] < current.cost) continue;

            for(Node next: graph[current.node]){
                if(cost[start][next.node] > cost[start][current.node] + next.cost){     // find shortest path
                    cost[start][next.node] = cost[start][current.node] + next.cost;

                    pq.offer(new Node(next.node, cost[start][next.node]));
                }
            }
        }
    }
}
