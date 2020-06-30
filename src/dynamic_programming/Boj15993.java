package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 15993번: 1, 2, 3 더하기 8
 *
 * @see https://www.acmicpc.net/problem/15993/
 *
 */
public class Boj15993 {
    private static int[][] dp = new int[2][100_001];

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final int MOD = 1_000_000_009;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        init();

        while(T--> 0){
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[1][n] % MOD).append(SPACE).append(dp[0][n] % MOD).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init () {
        dp[1][1] = dp[1][2] = dp[0][2] = 1;
        dp[1][3] = dp[0][3] = 2;

        for (int i = 4; i <= 100000; i++) {
            for (int j = 1; j <= 3; j++) {
                dp[1][i] = modulation(dp[1][i], dp[0][i - j]);          // odd
                dp[0][i] = modulation(dp[0][i], dp[1][i - j]);          // even
            }
        }
    }

    private static int modulation(int a, int b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }
}
