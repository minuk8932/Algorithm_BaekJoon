package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16195번: 1, 2, 3 더하기 9
 *
 * @see https://www.acmicpc.net/problem/16195
 *
 */
public class Boj16195 {

    private static final int MOD = 1_000_000_009;
    private static final String NEW_LINE = "\n";

    private static int[][] dp = new int[1_001][1_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int answer = 0;
            for(int i = 1; i <= m; i++) {
                answer = ((answer % MOD) + (recursion(n, i) % MOD)) % MOD;
            }

            sb.append(answer).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int recursion(int current, int count) {
        if(current == 0 && count == 0) return 1;
        if(current <= 0 || count <= 0) return 0;

        if (dp[current][count] != -1) return dp[current][count];

        int answer = (((recursion(current - 1, count - 1) % MOD)
                + (recursion(current - 2, count - 1) % MOD)) % MOD
                + (recursion(current - 3, count - 1) % MOD)) % MOD;

        return dp[current][count] = answer;
    }
}
