package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1102번: 발전소
 *
 * @see https://www.acmicpc.net/problem/1102
 *
 */
public class Boj1102 {

    private static int[] dp;
    private static int[][] plant;

    private static int N;
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        plant = new int[N][N];
        dp = new int[1 << N];
        Arrays.fill(dp, INF);

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                plant[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String status = br.readLine();
        int P = Integer.parseInt(br.readLine());
        int bit = 0;

        boolean[] onOff = new boolean[status.length()];
        for(int i = 0; i < onOff.length; i++) {
            onOff[i] = status.charAt(i) == 'Y';
            if(!onOff[i]) continue;

            P--;
            bit |= 1 << i;
        }

        int ans = recursion(bit, P);
        System.out.println(ans >= INF ? -1: ans);
    }

    /**
     *
     * Recursion
     *
     * line 80: find next bit
     *
     * @param masking
     * @param p
     * @return
     */
    private static int recursion(int masking, int p) {
        if(p <= 0) return 0;
        if(dp[masking] != INF) return dp[masking];
        int result = INF;

        for(int i = 0; i < N; i++) {
            int bit1 = 1 << i;
            if((masking & bit1) == 0) continue;

            for(int j = 0; j < N; j++) {
                int bit2 = 1 << j;
                if((masking & bit2) == bit2) continue;

                result = Math.min(result, recursion(masking | bit2, p - 1) + plant[i][j]);
            }
        }

        return dp[masking] = result;
    }
}
