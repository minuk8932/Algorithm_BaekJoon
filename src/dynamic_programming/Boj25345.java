package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25345번: 루나의 게임 세팅
 *
 * @see https://www.acmicpc.net/problem/25345
 *
 */
public class Boj25345 {

    private static long[][] dp;
    private static long MOD = 1_000_000_007L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new long[N + 1][N + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        combination(N, K);
        long answer = dp[N][K];
        for(int i = 1; i < K; i++){
            answer <<= 1L;
            answer %= MOD;
        }

        System.out.println(answer);
    }

    private static long combination(int n, int k) {
        if(n == k || k == 0) return dp[n][k] = 1L;

        if(dp[n][k] != -1) return dp[n][k];
        long answer = (combination(n - 1, k) % MOD) + (combination(n - 1, k - 1) % MOD);

        return dp[n][k] = answer % MOD;
    }
}
