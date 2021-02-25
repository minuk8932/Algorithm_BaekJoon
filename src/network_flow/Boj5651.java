package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 5651번: 완전 중요한 간선
 *
 * @see https://www.acmicpc.net/problem/5651
 *
 */
public class Boj5651 {

    private static int[][] capacity;
    private static int[][] flow;
    private static int[] prev;

    private static ArrayList<Integer>[] connection;
    private static ArrayList<Pair> pairs;

    private static int S, T, N;
    private static final String NEW_LINE = "\n";

    private static class Pair {
        int s;
        int t;

        public Pair(int s, int t) {
            this.s = s;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            capacity = new int[N][N];
            flow = new int[N][N];
            connection = new ArrayList[N];
            pairs = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                connection[i] = new ArrayList<>();
            }

            S = 0; T = N - 1;
            while(M-- > 0){
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken()) - 1;
                int node2 = Integer.parseInt(st.nextToken()) - 1;
                int cap = Integer.parseInt(st.nextToken());

                capacity[node1][node2] += cap;
                connection[node1].add(node2);
                connection[node2].add(node1);
                pairs.add(new Pair(node1, node2));
            }

            sb.append(networkFlow()).append(NEW_LINE);
        }

        System.out.print(sb.toString());
    }

    /**
     *
     * Edmond Karf
     *
     * line 101: Check each edges remained capacity
     *
     * @return
     */
    private static int networkFlow() {
        prev = new int[N];

        while(true) {
            if(flowProcess(S, T)) break;

            int mFlow = Integer.MAX_VALUE;
            for(int i = T; i != S; i = prev[i]) {
                mFlow = Math.min(mFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
            }

            for(int i = T; i != S; i = prev[i]) {
                flow[prev[i]][i] += mFlow;
                flow[i][prev[i]] -= mFlow;
            }
        }

        int count = 0;

        for(Pair p: pairs) {
            if(flowProcess(p.s, p.t)) count++;
        }

        return count;
    }

    private static boolean flowProcess(int s, int t) {
        Arrays.fill(prev, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(s);

        while(!q.isEmpty() && prev[t] == -1) {
            int current = q.poll();

            for(int next: connection[current]) {
                if(prev[next] != -1) continue;
                if(capacity[current][next] <= flow[current][next]) continue;
                prev[next] = current;

                q.offer(next);
            }
        }

        return prev[t] == -1;
    }
}
