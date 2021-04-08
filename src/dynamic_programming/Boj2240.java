package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2240번: 자두 나무
 *
 * @see https://www.acmicpc.net/problem/2240
 *
 */
public class Boj2240 {

    private static int[][][] dp = new int[2][31][1_001];
    private static int[] plum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        plum = new int[T + 1];
        for(int i = 1; i <= T; i++) {
            plum[i] = Integer.parseInt(br.readLine()) - 1;
        }

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 31; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(recursion(false, W, T));
    }

    /**
     *
     * Recursion
     *
     * line 57: no more move count
     * line 59 ~ 62: can move
     *
     * @param pos
     * @param move
     * @param sec
     * @return
     */
    private static int recursion(boolean pos, int move, int sec) {
        if(sec == 0) return 0;

        int index = boolToInt(pos);
        if(dp[index][move][sec] != -1) return dp[index][move][sec];

        int result = recursion(pos, move, sec - 1) + (plum[sec] == index ? 1: 0);

        if(move > 0) {
            result = Math.max(result
                    , recursion(!pos, move - 1, sec - 1) + (plum[sec] != index ? 1: 0));
        }

        return dp[index][move][sec] = result;
    }

    private static int boolToInt(boolean p) {
        return p ? 1: 0;
    }
}
