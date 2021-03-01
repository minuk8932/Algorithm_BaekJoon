package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 14286번: 간선 끊어가기 2
 *
 * @see https://www.acmicpc.net/problem/14286
 *
 */
public class Boj14286 {

    private static ArrayList<Integer>[] connection;
    private static int[][] flow;
    private static int[][] capacity;

    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        connection = new ArrayList[N];
        capacity = new int[N][N];
        flow = new int[N][N];
        for(int i = 0; i < N; i++) {
            connection[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            linker(node1, node2, cost);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(networkFlow(start, end));
    }

    /**
     *
     * linker
     *
     * line 63 ~ 64: bidirection capacity
     *
     * @param from
     * @param to
     * @param cap
     */
    private static void linker(int from, int to, int cap) {
        connection[from].add(to);
        connection[to].add(from);
        capacity[from][to] += cap;
        capacity[to][from] += cap;
    }

    /**
     *
     * Edmond karf
     *
     * @param S
     * @param T
     * @return
     */
    private static int networkFlow(int S, int T) {
        int[] prev = new int[N];
        int cost = 0;

        while(true) {
            Arrays.fill(prev, -1);

            Queue<Integer> q = new LinkedList<>();
            q.offer(S);

            while(!q.isEmpty()) {
                int current = q.poll();

                for(int next: connection[current]) {
                    if(capacity[current][next] <= flow[current][next]) continue;
                    if(prev[next] != -1) continue;

                    prev[next] = current;
                    q.offer(next);
                }
            }

            if(prev[T] == -1) break;

            int minFlow = Integer.MAX_VALUE;
            for(int i = T; i != S; i = prev[i]) {
                minFlow = Math.min(minFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
            }

            for(int i = T; i != S; i = prev[i]) {
                flow[prev[i]][i] += minFlow;
                flow[i][prev[i]] -= minFlow;
            }

            cost += minFlow;
        }

        return cost;
    }
}
