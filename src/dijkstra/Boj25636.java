package dijkstra;

import common.Node;
import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25636번: 소방차
 *
 * @see https://www.acmicpc.net/problem/25636
 *
 */
public class Boj25636 {

    private static final String NO_WAY = "-1";
    private static final long INF = 1_000_000_000_000_000L;
    private static long[] water;
    private static long[] weight;
    private static long[] distance;

    private static ArrayList<ArrayList<Node<Integer, Long>>> path = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        water = new long[N];
        weight = new long[N];
        distance = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            water[i] = Long.parseLong(st.nextToken());
            path.add(i, new ArrayList<>());
            distance[i] = INF;
        }

        int M = Integer.parseInt(br.readLine());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            long w = Long.parseLong(st.nextToken());

            path.get(u).add(new Node.Builder<Integer, Long>(v).cost(w).build());
            path.get(v).add(new Node.Builder<Integer, Long>(u).cost(w).build());
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()) - 1;
        int T = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(dijkstra(S, T));
    }

    private static String dijkstra(int src, int snk) {
        PriorityQueue<Node<Integer, Long>> pq = new PriorityQueue<>(
            Comparator.comparingLong(Node<Integer, Long>::getCost));

        pq.offer(new Node.Builder<Integer, Long>(src).cost(0L).build());
        distance[src] = 0;

        long answer = INF;
        long waterTank = 0;

        weight[src] = water[src];

        while(!pq.isEmpty()) {
            Node<Integer, Long> current = pq.poll();
            if(current.getCost() > distance[current.getNode()]) continue;

            for(Node<Integer, Long> next: path.get(current.getNode())) {
                if(distance[next.getNode()] < distance[current.getNode()] + next.getCost()) continue;

                if(distance[next.getNode()] > distance[current.getNode()] + next.getCost()) {
                    distance[next.getNode()] = distance[current.getNode()] + next.getCost();
                    weight[next.getNode()] = weight[current.getNode()] + water[next.getNode()];

                    pq.offer(new Node.Builder<Integer, Long>(next.getNode())
                        .cost(distance[next.getNode()])
                        .build());
                }
                else {
                    long nextWeight = weight[current.getNode()] + water[next.getNode()];
                    weight[next.getNode()] = Math.max(nextWeight, weight[next.getNode()]);
                }

                if(next.getNode() != snk) continue;
                answer = distance[next.getNode()];
                waterTank = weight[next.getNode()];
            }
        }

        return answer == INF ? NO_WAY: answer + " " + waterTank;
    }
}
