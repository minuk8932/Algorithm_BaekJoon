package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 14619번: 섬 여행
 *
 * @see https://www.acmicpc.net/problem/14619/
 *
 */
public class Boj14619 {
    private static ArrayList<Integer>[] graph;
    private static int[][] dp;
    private static int[] H;

    private static final String NEW_LINE = "\n";
    private static final int INF = 100_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        H = new int[N];
        graph = new ArrayList[N];
        dp = new int[N][501];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
            Arrays.fill(dp[i], INF);
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        for(int i = 0; i < N; i++) {                // 1 ~ K times
            recursion(i, 500);
        }

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int K = Integer.parseInt(st.nextToken());

            sb.append(dp[A][K] == INF ? -1: dp[A][K]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int recursion(int current, int time) {
        if(time == 0) return H[current];
        if(dp[current][time] != INF) return dp[current][time];

        for(int next : graph[current]) {                                // compare with until current & next
            dp[current][time] = Math.min(dp[current][time], recursion(next, time - 1));
        }

        return dp[current][time];
    }
}
