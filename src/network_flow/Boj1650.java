package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1650번: 지민이의 테러 Season II
 *
 * @see https://www.acmicpc.net/problem/1650
 *
 */
public class Boj1650 {

    private static ArrayList<Node>[] connection;
    private static int S, T, size = 1_001;

    private static final int INF = 1_000_000_000;

    private static class Node {
        int node1;
        int node2;
        int cost;
        int capacity;
        int flow;
        Node rev;

        public Node(int node1, int node2, int cost, int capacity) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        S = 0;
        T = size - 1;

        connection = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            connection[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            linker(node1, node2, cost, 1);
            linker(node2, node1, cost, 1);
        }

        linker(S, 1, 0, 2);
        linker(N, T, 0, 2);

        System.out.print(minimumCostMaximumFlow());
    }

    /**
     *
     * MCMF
     *
     * line 80 ~ 106: SPFA
     * We need check between two nodes, there are many edges. ref :: Boj 16707
     *
     * @return
     */
    private static int minimumCostMaximumFlow() {
        int[] dist = new int[size];
        Node[] prev;

        int result = 0;

        while (true) {
            Queue<Integer> q = new LinkedList<>();
            boolean[] inQ = new boolean[size];
            prev = new Node[size];
            Arrays.fill(dist, INF);

            q.offer(S);
            inQ[S] = true;
            dist[S] = 0;
            while (!q.isEmpty()) {
                int current = q.poll();
                inQ[current] = false;

                for (Node node : connection[current]) {
                    int next = node.node2;

                    if (dist[next] <= dist[current] + node.cost) continue;
                    if (node.capacity <= node.flow) continue;
                    dist[next] = dist[current] + node.cost;
                    prev[next] = node;

                    if (!inQ[next]) {
                        q.offer(next);
                        inQ[next] = true;
                    }
                }
            }

            if(dist[T] == INF) break;

            for (int i = T; i != S; i = prev[i].node1) {
                result += prev[i].cost * 1;

                prev[i].flow += 1;
                prev[i].rev.flow -= 1;
            }
        }

        return result;
    }

    private static void linker(int from, int to, int c, int cap) {
        Node node1 = new Node(from, to, c, cap);
        Node node2 = new Node(to, from, -c, 0);
        node1.rev = node2;
        node2.rev = node1;

        node1.flow = 0;
        node2.flow = 0;

        connection[from].add(node1);
        connection[to].add(node2);
    }
}
