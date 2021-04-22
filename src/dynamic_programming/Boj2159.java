package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2159번: 케잌 배달
 *
 * @see https://www.acmicpc.net/problem/2159
 *
 */
public class Boj2159 {

    private static final long INF = 1_000_000_000_000L;
    private static long[][] dp;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int X = 0, Y = 1;

    private static int N;

    private static Coordinate[] T;
    private static Coordinate S;

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        S = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        T = new Coordinate[N];
        dp = new long[N][5];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            Arrays.fill(dp[i], -1);
        }

        long answer = INF;
        for(int p = 0; p < 5; p++) {
            answer = Math.min(answer, recursion(S, 0, p));
        }

        System.out.println(answer);
    }

    /**
     *
     * Recursion
     *
     * line 79 ~ 83: find next, correct(4) position or adjacent(0 ~ 3)
     *
     * @param current
     * @param idx
     * @param pos
     * @return
     */
    private static long recursion(Coordinate current, int idx, int pos) {
        if(idx >= N) return 0;
        if(dp[idx][pos] != -1) return dp[idx][pos];

        long result = recursion(T[idx], idx + 1, 4) + manhattanDistance(current, T[idx]);
        int d = 0;
        for(final int[] DIRECTION: DIRECTIONS) {
            Coordinate adjacent
                    = new Coordinate(T[idx].x + DIRECTION[X], T[idx].y + DIRECTION[Y]);

            result = Math.min(result, recursion(adjacent, idx + 1, d) + manhattanDistance(current, adjacent));
            d++;
        }

        return dp[idx][pos] = result;
    }

    private static long manhattanDistance(Coordinate c1, Coordinate c2) {
        return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
    }
}
