package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 10976번: 피난
 *
 * @see https://www.acmicpc.net/problem/10976/
 *
 */
public class Boj10976 {
    private static final String NEW_LINE = "\n";

    private static ArrayList<Integer>[] connection;
    private static int[][] capacity;
    private static int[][] flow;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
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

                connection[node1].add(node2);
                connection[node2].add(node1);

                if (node1 == 0 || node2 == N - 1) capacity[node1][node2] = 1;   // 1, N is 1 flow
                else capacity[node1][node2] = N;
            }

            sb.append(networkFlow(N, 0, N - 1)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int networkFlow(int n, int S, int T) {
        int[] prev = new int[n];
        int queen = 0;

        while(true) {
            Arrays.fill(prev, -1);

            Queue<Integer> q = new LinkedList<>();
            q.offer(S);

            prev[S] = 0;

            while(!q.isEmpty()) {
                int current = q.poll();

                for(int next: connection[current]) {
                    if(prev[next] != -1) continue;

                    if(capacity[current][next] - flow[current][next] > 0) {
                        prev[next] = current;
                        q.offer(next);
                    }
                }
            }

            if(prev[T] == -1) break;

            int min = n;
            for(int i = T; i != S; i = prev[i]) {
                min = Math.min(min, capacity[prev[i]][i] - flow[prev[i]][i]);
            }

            for(int i = T; i != S; i = prev[i]) {
                flow[prev[i]][i] += min;
                flow[i][prev[i]] -= min;
            }

            queen += min;                   // escaped queens
        }

        return queen;
    }
}
