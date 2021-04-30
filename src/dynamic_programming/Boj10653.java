package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10653번: 마라톤 2
 *
 * @see https://www.acmicpc.net/problem/10653
 *
 */
public class Boj10653 {

    private static CheckPoint[] point;
    private static int[][] dp;
    private static int[][] distance;

    private static final int INF = 1_000_000_000;

    private static class CheckPoint {
        int x;
        int y;

        public CheckPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        point = new CheckPoint[N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            point[i] = new CheckPoint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp = new int[N + 1][K + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        distance = new int[N + 1][N + 1];
        manhattanDistance();
        System.out.println(recursion(N, K));
    }

    /**
     *
     * Recursion
     *
     * line 72: 2 to N - 1, reach hopped K
     *
     * @param n
     * @param k
     * @return
     */
    private static int recursion(int n, int k) {
        if(n == 1) return 0;

        if(dp[n][k] != -1) return dp[n][k];
        int result = INF;

        for(int i = 0; i <= k; i++){
            if(n - i < 2) continue;
            result = Math.min(recursion(n - 1 - i,k - i) + distance[n][n - 1 - i], result);
        }

        return dp[n][k] = result;
    }

    private static void manhattanDistance() {
        for(int i = 1; i < distance.length; i++) {
            for(int j = 1; j < distance[i].length; j++) {
                distance[i][j] = Math.abs(point[i].x - point[j].x) + Math.abs(point[i].y - point[j].y);
            }
        }
    }
}
