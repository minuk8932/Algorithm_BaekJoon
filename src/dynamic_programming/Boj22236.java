package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 22236번: 가희와 비행기
 *
 * @see https://www.acmicpc.net/problem/22236
 *
 */
public class Boj22236 {

    private static long[][] dp;
    private static int d;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        final long M = Long.parseLong(st.nextToken());

        dp = new long[d + 1][d + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(recursion(d, 0, M));
    }

    private static long recursion(int distance, int height, final long MOD) {
        if(distance > 0 && distance < d && height <= 0) return 0;
        if(distance == 0) return height == 0 ? 1: 0;

        if(dp[distance][height] != -1) return dp[distance][height];
        long result = recursion(distance - 1, height + 1, MOD)
                + recursion(distance - 1, height - 1, MOD);

        return dp[distance][height] = result % MOD;
    }
}
