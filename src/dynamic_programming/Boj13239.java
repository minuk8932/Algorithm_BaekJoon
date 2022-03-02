package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13239번: Combinations
 *
 * @see https://www.amicpc.net/problem/13239
 *
 */
public class Boj13239 {

    private static int[][] dp = new int[1_001][1_001];

    private static final String NEW_LINE = "\n";
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            sb.append(recursion(n, k)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int recursion(int n, int k) {
        if(n == k) return 1;
        if(k == 0) return 1;

        if(dp[n][k] != -1) return dp[n][k];

        return dp[n][k] = ((recursion(n - 1, k) % MOD) + (recursion(n - 1, k - 1) % MOD)) % MOD;
    }
}
