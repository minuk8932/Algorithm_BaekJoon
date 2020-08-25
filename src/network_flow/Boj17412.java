package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17412번: 도시 왕복하기 1
 *
 * @see https://www.acmicpc.net/problem/17412
 *
 */
public class Boj17412 {
    private static int[][] capacity;
    private static int[][] flow;
    private static ArrayList<Integer>[] path;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        capacity = new int[N][N];
        flow = new int[N][N];

        path = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            capacity[from][to] = 1;                         // a direction

            path[from].add(to);                             // make link
            path[to].add(from);
        }

        System.out.println(networkFlow(N));
    }

    private static int networkFlow(int n) {
        int[] visit = new int[n];

        int result = 0;
        int src = 0, snk = 1;

        while(true) {
            Arrays.fill(visit, -1);

            Queue<Integer> q = new LinkedList<>();
            q.offer(src);

            while(!q.isEmpty()) {
                int current = q.poll();

                for(int next: path[current]) {
                    if(visit[next] != -1) continue;

                    if(capacity[current][next] > flow[current][next]) {
                        visit[next] = current;
                        if(next == snk) break;

                        q.offer(next);
                    }
                }
            }

            if(visit[snk] == -1) break;

            int minFlow = 1;
            for(int i = snk; i != src; i = visit[i]) {              // remove flow
                flow[visit[i]][i] += minFlow;
                flow[i][visit[i]] -= minFlow;
            }

            result += minFlow;
        }

        return result;
    }
}
