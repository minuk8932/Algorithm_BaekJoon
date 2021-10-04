package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 23083번: 꿀벌 승연이
 *
 * @see https://www.acmicpc.net/problem/23083
 *
 */
public class Boj23083 {

    private static final int[][][] DIRECTIONS = {{{-1, 1}, {0, 1}, {1, 0}}, {{0, 1}, {1, 1}, {1, 0}}};
    private static final int ROW = 0, COL = 1;

    private static int N, M;

    private static boolean[][] blocked;
    private static long[][] dp;

    private static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = PARSER.apply(st.nextToken());
        M = PARSER.apply(st.nextToken());

        blocked = new boolean[N][M];
        dp = new long[N][M];

        int K = PARSER.apply(br.readLine());
        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            blocked[PARSER.apply(st.nextToken()) - 1][PARSER.apply(st.nextToken()) - 1] = true;
        }

        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1L);
        }

        System.out.println(recursion(0, 0));
    }

    /**
     *
     * Recursion
     *
     * line 67 ~ 75: move bee, by checking cols number
     *
     * @param row
     * @param col
     * @return
     */
    private static long recursion(int row, int col) {
        if(TERMINATE.test(row, col)) return 1;

        if(dp[row][col] != -1) return dp[row][col];
        long result = 0;

        for(final int[] DIRECTION: DIRECTIONS[col % 2]) {
            int nextRow = row + DIRECTION[ROW];
            int nextCol = col + DIRECTION[COL];

            if(OUT_OF_RANGE.test(nextRow, nextCol)) continue;
            if(blocked[nextRow][nextCol]) continue;

            result = MODULATION.apply(result, recursion(nextRow, nextCol));
        }

        return dp[row][col] = result % MOD;
    }

    private static final Function<String, Integer> PARSER = Integer::parseInt;
    private static final BiPredicate<Integer, Integer> OUT_OF_RANGE = (row, col) ->
            row < 0 || col < 0 || row >= N || col >= M;

    private static final BiFunction<Long, Long, Long> MODULATION = (x, y) ->
            ((x % MOD) + (y % MOD)) % MOD;

    private static final BiPredicate<Integer, Integer> TERMINATE = (row, col) ->
            row == N - 1 && col == M - 1;
}
