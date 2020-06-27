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
 * @see https://www.acmicpc.net/problem/2157/
 *
 */
public class Boj2157 {
    private static int[][] dp;
    private static int N, M;

    private static final int INF = -2_000_000_000;

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
        int result = recursion(0, 1);
        System.out.println(result < 0 ? 0: result);
    }

    private static void init(int[][] input) {
        path = new ArrayList[N];
        dp = new int[N][N];

        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
            Arrays.fill(dp[i], -1);
        }

        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){             // make pre data
                if(input[i][j] == 0) continue;
                path[i].add(new Node(j, input[i][j]));
            }
        }
    }

    private static int recursion(int current, int count) {
        if (current == N - 1) return 0;
        if (count >= M) return INF;

        if (dp[current][count] != -1) return dp[current][count];

        for (Node next: path[current]) {                // memoization cost
            if (current > next.node) continue;
            dp[current][count] = Math.max(dp[current][count], recursion(next.node, count + 1) + next.cost);
        }

        return dp[current][count];
    }
}
