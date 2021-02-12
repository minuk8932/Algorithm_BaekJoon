package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 11405번: 책 구매하기
 *
 * @see https://www.acmicpc.net/problem/11405
 *
 */
public class Boj11405 {

    private static int[][] capacity;
    private static int[][] distance;
    private static int[][] flow;
    private static ArrayList<Integer>[] connection;

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
        distance = new int[size][size];

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
                distance[i][j] = Integer.parseInt(st.nextToken());

                distance[j][i] = -distance[i][j];
                capacity[i][j] = INF;

                connection[i].add(j);
                connection[j].add(i);
            }
        }

        System.out.println(maximumFlowMinimumCost(src, snk, size));
    }

    /**
     *
     * MCMF
     *
     * line 87 ~ 113: SPFA
     *
     * @param S
     * @param E
     * @param N
     * @return
     */
    private static int maximumFlowMinimumCost(int S, int E, int N) {
        int[] prev = new int[N];
        int[] cost = new int[N];
        int result = 0;

        while(true){
            Queue<Integer> q = new LinkedList<>();

            boolean[] inQ = new boolean[N];
            Arrays.fill(prev, -1);
            Arrays.fill(cost, INF);

            q.offer(S);
            cost[S] = 0;
            inQ[S] = true;

            while(!q.isEmpty()){
                int current = q.poll();
                inQ[current] = false;

                for(int next: connection[current]){
                    if(cost[next] <= cost[current] + distance[current][next]) continue;
                    if(capacity[current][next] <= flow[current][next]) continue;
                    cost[next] = cost[current] + distance[current][next];
                    prev[next] = current;

                    if(!inQ[next]){
                        q.offer(next);
                        inQ[next] = true;
                    }
                }
            }

            if(prev[E] == -1) break;

            int maxFlow = INF;
            for(int i = E; i != S; i = prev[i]) {
                maxFlow = Math.min(maxFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
            }

            for(int i = E; i != S; i = prev[i]){
                result += maxFlow * distance[prev[i]][i];
                flow[prev[i]][i] += maxFlow;
                flow[i][prev[i]] -= maxFlow;
            }
        }

        return result;
    }
}
