package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15592번: 1, 2, 3 더하기 7
 *
 * @see https://www.acmicpc.net/problem/15592
 *
 */
public class Boj15592 {

    private static final int MOD = 1_000_000_009;
    private static final String NEW_LINE = "\n";

    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        dp = new int[1_001][1_001];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sb.append(recursion(n, m)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int recursion(int current, int count) {
        if(count == 0 && current == 0) return 1;
        if(count <= 0 || current <= 0) return 0;

        if(dp[current][count] != -1) return dp[current][count];

        int answer = (((recursion(current - 1, count  - 1) % MOD) +
                (recursion(current - 2, count  - 1) % MOD)) % MOD +
                (recursion(current - 3, count  - 1) % MOD)) % MOD;

        return dp[current][count] = answer;
    }
}
