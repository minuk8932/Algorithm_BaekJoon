package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14596번: Quilting (Small)
 *
 * @see https://www.acmicpc.net/problem/14596/
 *
 */
public class Boj14596 {
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] left = new int[H + 2][W + 2];
        int[][] right = new int[H + 2][W + 2];

        for(int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= W; j++) {
                left[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= W; j++) {
                right[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(quilting(H, W, left, right));
    }

    private static int quilting(int h, int w, int[][] l, int[][] r) {
        int[][] dp = new int[h + 2][w + 2];

        for(int i = 1; i <= h; i++) {
            Arrays.fill(dp[i], INF);
        }

        for(int i = 1; i <= h; i++) {
            for(int j = 1; j <= w; j++) {
                int min = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i - 1][j + 1]);     // make boundary
                dp[i][j] = Math.min(min + getOptimal(l[i][j], r[i][j]), dp[i][j]);
            }
        }

        int min = INF;
        for(int i = 1; i <= w; i++) {
            min = Math.min(min, dp[h][i]);
        }

        return min;
    }

    private static int getOptimal(int l, int r) {               // check optimal
        int diff = l - r;
        return diff * diff;
    }
}
