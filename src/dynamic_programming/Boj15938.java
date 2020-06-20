package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15938번: 더위 피하기
 *
 * @see https://www.acmicpc.net/problem/15938/
 *
 */
public class Boj15938 {
    private static final int MOD = 1_000_000_007;
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static int T;

    private static Point end;
    private static boolean[][] blocked = new boolean[401][401];
    private static int[][][] dp = new int[205][401][401];

    private static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        Point start = new Point(200, 200);
        int dx = 200 - x;
        int dy = 200 - y;

        T = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        end = new Point(Integer.parseInt(st.nextToken()) + dx, Integer.parseInt(st.nextToken()) + dy);

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()) + dx;
            y = Integer.parseInt(st.nextToken()) + dy;

            if(outOfRange(new Point(x, y))) continue;
            blocked[x][y] = true;
        }

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(recursion(start, 0) % MOD);
    }

    private static int recursion(Point current, int time) {
        if (arrived(current)) return 1;
        if (Math.abs(current.x - end.x) + Math.abs(current.y - end.y) > T - time) return 0;     // too far
        if (time == T) return 0;

        if(dp[time][current.x][current.y] != -1) return dp[time][current.x][current.y] % MOD;
        dp[time][current.x][current.y] = 0;

        int result = 0;

        for(final int[] DIRECTION: DIRECTIONS) {                    // make all cases
            int nextX = current.x + DIRECTION[ROW];
            int nextY = current.y + DIRECTION[COL];

            if (outOfRange(new Point(nextX, nextY))) continue;
            if (blocked[nextX][nextY]) continue;

            dp[time + 1][nextX][nextY] = modulation(recursion(new Point(nextX, nextY), time + 1), dp[time][current.x][current.y]);
            result = modulation(result, dp[time + 1][nextX][nextY]);
        }

        return result % MOD;
    }

    private static int modulation(int a, int b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }

    private static boolean outOfRange(Point p) {
        return p.x < 0 || p.x > 400 || p.y < 0 || p.y > 400;
    }

    private static boolean arrived(Point p) {
        return p.x == end.x && p.y == end.y;
    }
}
