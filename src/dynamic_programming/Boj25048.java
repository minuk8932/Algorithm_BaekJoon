package dynamic_programming;

import common.Node;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25048번: 랜선 연결
 *
 * @see https://www.acmicpc.net.problem/25048
 *
 */
public class Boj25048 {
    private static final long INF = Long.MAX_VALUE >> 2L;
    private static ArrayList<Node<Integer, Long>> switches = new ArrayList<>();

    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            switches.add(new Node.Builder(node).cost(cost).build());
        }

        int M = Integer.parseInt(br.readLine());

        Collections.sort(switches, Comparator
            .comparingLong(Node<Integer, Long>::getCost)
            .thenComparingInt(Node::getNode));

        dp = new long[N + 1][M + 1];
        for(int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }

        System.out.println(bottomUp(N, M));
    }

    private static long bottomUp(int n, int m) {
        dp[0][1] = 0;

        for(int i = 1; i <= n; i++) {
            Node<Integer, Long> current = switches.get(i - 1);

            for(int j = 1; j <= m; j++) {
                int range = j - current.getNode() + 2;

                if(range >= 0 && range <= m
                    && dp[i - 1][j] > dp[i - 1][range] + current.getCost()) {
                    dp[i][j] = dp[i - 1][range] + current.getCost();

                    continue;
                }

                dp[i][j] = dp[i - 1][j];
            }
        }

        return dp[n][m] == INF ? -1: dp[n][m];
    }
}