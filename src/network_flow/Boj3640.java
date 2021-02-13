package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 3640번: 제독
 *
 * @see https://www.acmicpc.net/problem/3640
 *
 */
public class Boj3640 {
    private static ArrayList<Integer>[] connection;

    private static int[][] capacity;
    private static int[][] flow;
    private static int[][] cost;

    private static final int INF = 1_000_000_000;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int size = N * 2 + 2;
            int src = 0;
            int snk = size - 1;

            capacity = new int[size][size];
            flow = new int[size][size];
            cost = new int[size][size];

            connection = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                connection[i] = new ArrayList<>();
            }

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                linker(node1 + N, node2, c, 1);
            }

            linker(1, N + 1, 0, 2);
            linker(N, N + N, 0, 2);

            for (int i = 2; i < N; i++) {
                linker(i, i + N, 0, 1);
            }

            linker(src, 1, 0, 2);
            linker(N, snk, 0, 2);

            sb.append(maximumFlowMinimumCost(src, snk, size)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Graph modeling
     *
     * @param from
     * @param to
     * @param c
     * @param cap
     */
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
     * line 117 ~ 132: SPFA
     *
     * @param S
     * @param T
     * @param N
     * @return
     */
    private static int maximumFlowMinimumCost(int S, int T, int N) {
        int[] prev = new int[N];
        int[] dist = new int[N];
        int result = 0;

        while(true){
            Queue<Integer> q = new LinkedList<>();

            boolean[] inQ = new boolean[N];
            Arrays.fill(prev, -1);
            Arrays.fill(dist, INF);

            q.offer(S);
            dist[S] = 0;
            inQ[S] = true;

            while(!q.isEmpty()){
                int current = q.poll();
                inQ[current] = false;

                for(int next: connection[current]){
                    if(dist[next] <= dist[current] + cost[current][next]) continue;
                    if(capacity[current][next] <= flow[current][next]) continue;
                    dist[next] = dist[current] + cost[current][next];
                    prev[next] = current;

                    if(!inQ[next]){
                        q.offer(next);
                        inQ[next] = true;
                    }
                }
            }

            if(prev[T] == -1) break;

            int maxFlow = INF;
            for(int i = T; i != S; i = prev[i]) {
                maxFlow = Math.min(maxFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
            }

            for(int i = T; i != S; i = prev[i]){
                result += maxFlow * cost[prev[i]][i];
                flow[prev[i]][i] += maxFlow;
                flow[i][prev[i]] -= maxFlow;
            }
        }

        return result;
    }
}

