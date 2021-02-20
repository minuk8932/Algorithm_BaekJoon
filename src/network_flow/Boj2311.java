package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 2311번: 왕복 여행
 *
 * @see https://www.acmicpc.net/problem/2311
 *
 */
public class Boj2311 {

    private static List<Integer>[] connection;
    private static int[][] cost;
    private static int[][] capacity;
    private static int[][] flow;
    private static int S, T, size;

    private static final int INF = 1_000_000_000;

    /**
     *
     * Main
     *
     * line 50 ~ 62, if input is '1 2 5' then modeling like.. (format cost:cap, u=up, d=down, bidirection)
     *                   << 5:1
     *                   -5:0 >>
     *                  0 ------ 3
     *   u=0:0, d=0:1    \        \  u=0:1, d=0:0
     *                    1 ------ 2
     *                      5:1 >>
     *                      << -5:0
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        size = N * 2;
        S = 0;
        T = size - 1;

        connection = new ArrayList[size];
        capacity = new int[size][size];
        cost = new int[size][size];
        flow = new int[size][size];
        for (int i = 0; i < size; i++) {
            connection[i] = new ArrayList<>();
        }

        for (int i = 0; i < size; i += 2) {
            linker(i, i + 1, 0, 2);
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) << 1;
            int node2 = Integer.parseInt(st.nextToken()) << 1;
            int cost = Integer.parseInt(st.nextToken());

            linker(node1 - 1, node2 - 2, cost, 1);
            linker(node2 - 1, node1 - 2, cost, 1);
        }

        System.out.print(minimumCostMaximumFlow());
    }

    private static void linker(int from, int to, int c, int cap) {
        connection[from].add(to);
        connection[to].add(from);

        capacity[from][to] = cap;
        cost[from][to] = c;
        cost[to][from] = -c;

    }

    /**
     *
     * MCMF
     *
     * line 106 ~ 121: SPFA
     *
     * @return
     */
    private static int minimumCostMaximumFlow() {
        int[] dist = new int[size];
        int result = 0;

        while (true) {
            boolean[] inQ = new boolean[size];
            int[] prev = new int[size];
            Arrays.fill(dist, INF);

            Queue<Integer> q = new LinkedList<>();
            q.offer(S);
            inQ[S] = true;
            dist[S] = 0;

            while (!q.isEmpty()) {
                int current = q.poll();
                inQ[current] = false;

                for (int next : connection[current]) {
                    if (dist[next] <= dist[current] + cost[current][next]) continue;
                    if (capacity[current][next] <= flow[current][next]) continue;
                    dist[next] = dist[current] + cost[current][next];
                    prev[next] = current;

                    if (!inQ[next]) {
                        q.offer(next);
                        inQ[next] = true;
                    }
                }
            }

            if (dist[T] == INF) break;

            int maxFlow = Integer.MAX_VALUE;
            for (int i = T; i != S; i = prev[i]) {
                maxFlow = Math.min(maxFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
            }

            for (int i = T; i != S; i = prev[i]) {
                result += cost[prev[i]][i];

                flow[prev[i]][i] += maxFlow;
                flow[i][prev[i]] -= maxFlow;
            }
        }

        return result;
    }
}

