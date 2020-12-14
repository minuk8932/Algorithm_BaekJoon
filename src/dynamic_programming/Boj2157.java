package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 *
 * 백준 2157번: 여행
 * @see https://www.acmicpc.net/problem/2157
 *
 */
public class Boj2157 {
    private static int[][] dp;
    private static int N, M;

    private static ArrayList<Node>[] path;

    private static class Node {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            map[from][to] = Math.max(cost, map[from][to]);
        }

        init(map);
        System.out.println(memoization());
    }

    private static int memoization() {
        dp = new int[M + 1][N];
        for(Node start: path[0]) {
            dp[2][start.node] = Math.max(dp[2][start.node], start.cost);
        }

        for(int i = 2; i < M; i++) {
            for(int j = 0; j < N; j++) {                // find best way
                if(dp[i][j] == 0) continue;

                for (Node next : path[j]) {
                    dp[i + 1][next.node] = Math.max(dp[i + 1][next.node], dp[i][j] + next.cost);
                }
            }
        }

        int result = 0;
        for(int i = 0; i <= M; i++) {
            result = Math.max(dp[i][N - 1], result);
        }

        return result;
    }

    private static void init(int[][] input) {
        path = new ArrayList[N];

        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){             // make pre data
                if(input[i][j] == 0) continue;
                path[i].add(new Node(j, input[i][j]));
            }
        }
    }
}
