package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10835번: 카드 게임
 *
 * @see https://www.acmicpc.net/problem/10835
 *
 */
public class Boj10835 {

    private static int[] left, right;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        left = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = N - 1; i >= 0; i--) {
            left[i] = Integer.parseInt(st.nextToken());
        }

        right = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = N - 1; i >= 0; i--) {
            right[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][N + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(recursion(N, N));
    }

    /**
     *
     * Recursion
     *
     * line 60: remove left
     * line 63: remove right and get score
     *
     * @param x
     * @param y
     * @return
     */
    private static int recursion(int x, int y) {
        if(x < 0 || y < 0) return 0;
        if(dp[x][y] != -1) return dp[x][y];
        int result;

        if(left[x] <= right[y]) {
            result = Math.max(recursion(x - 1, y), recursion(x - 1, y - 1));
        }
        else {
            result = recursion(x, y - 1) + right[y];
        }

        return dp[x][y] = result;
    }
}
