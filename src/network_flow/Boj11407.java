package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 11407번: 책 구매하기 3
 *
 * @see https://www.acmicpc.net/problem/11407
 *
 */
public class Boj11407 {

    private static ArrayList<Integer>[] connection;
    private static int[][] capacity;
    private static int[][] flow;
    private static int[][] cost;

    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int size = N + M + 2;
        int src = 0;
        int snk = size - 1;

        capacity = new int[size][size];
        flow = new int[size][size];
        cost = new int[size][size];

        connection = new ArrayList[size];
        for(int i = 0; i < size; i++) {
            connection[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            capacity[i][snk] = Integer.parseInt(st.nextToken());
            connection[i].add(snk);
            connection[snk].add(i);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = N + 1; i < snk; i++){
            capacity[src][i] = Integer.parseInt(st.nextToken());
            connection[src].add(i);
            connection[i].add(src);
        }

        for(int i = N + 1; i < snk; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= N; j++){
                capacity[i][j] = Integer.parseInt(st.nextToken());

                connection[i].add(j);
                connection[j].add(i);
            }
        }

        for(int i = N + 1; i < snk; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= N; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
                cost[j][i] = -cost[i][j];
            }
        }

        maximumFlowMinimumCost(src, snk, size);
    }

    /**
     *
     * MCMF
     *
     * line 95 ~ 120: SPFA
     *
     * @param S
     * @param T
     * @param N
     */
    private static void maximumFlowMinimumCost(int S, int T, int N) {
        int[] prev = new int[N];
        int[] dist = new int[N];
        int result = 0;
        int value = 0;

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

            value += maxFlow;
        }

        System.out.println(value + "\n" + result);
    }
}
