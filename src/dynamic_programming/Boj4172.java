package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 4172번: sqrt log sin
 *
 * @see https://www.acmicpc.net/problem/4172
 *
 */
public class Boj4172 {

    private static long[] dp = new long[1_000_001];
    private static final int MOD = 1_000_000;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Arrays.fill(dp, -1);

        StringBuilder sb = new StringBuilder();
        while(true) {
            int i = Integer.parseInt(br.readLine());
            if(i == -1) break;

            sb.append(recursion(i) % MOD).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static long recursion(int n) {
        if(n == 0) return 1;
        if(dp[n] != -1) return dp[n];

        long result = (recursion((int) (n - Math.sqrt(n))) % MOD + recursion((int) (Math.log(n))) % MOD
                + recursion((int) (n * Math.pow(Math.sin(n), 2)) % MOD)) % MOD;

        return dp[n] = result;
    }
}
