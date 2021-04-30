package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16991번: 외판원 순회3
 *
 * @see https://www.acmicpc.net/problem/16991
 *
 */
public class Boj16991 {

    private static Coordinate[] coors;
    private static double[][] dp;

    private static final double INF = 1_000_000_000;
    private static int full;

    private static class Coordinate{
        double x;
        double y;

        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        full = (1 << N) - 1;

        coors = new Coordinate[N + 1];
        dp = new double[N + 1][1 << N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coors[i] = new Coordinate(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        coors[N] = coors[0];

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(recursion(N, 1));
    }

    /**
     *
     * Recursion
     *
     * line 66: Last time, visit start node necessary
     * line 75: Move next, that not visited before.
     *
     * @param n
     * @param current
     * @return
     */
    private static double recursion(int n, int current) {
        if(current == full) return distance(coors[n], coors[0]);

        if(dp[n][current] != -1) return dp[n][current];
        double result = INF;

        for(int i = 1; i < coors.length - 1; i++) {
            int next = (1 << i);
            if((current & next) != 0) continue;

            result = Math.min(result, recursion(i, current | next) + distance(coors[n], coors[i]));
        }

        return dp[n][current] = result;
    }

    private static double distance(Coordinate c1, Coordinate c2) {
        return Math.sqrt(Math.pow(c1.x - c2.x, 2) + Math.pow(c1.y - c2.y, 2));
    }
}
