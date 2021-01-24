package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 20500번: Ezreal 여눈부터 가네 ㅈㅈ
 *
 * @see https://www.acmicpc.net/problem/20500
 *
 */
public class Boj20500 {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(finder(N));
    }

    private static int finder(int n) {
        if(n == 1) return 0;
        if(n == 2) return 1;

        int[][] dp = new int[3][n];                 // [x % 3][n digits]

        dp[0][0] = 0;
        dp[0][1] = 1; dp[1][1] = 1; dp[2][1] = 0;   // each sum of ciphers is 3's multiple & number end by five

        for (int i = 2; i < n; i++) {
            for(int j = 0; j < 3; j++) {
                dp[j][i] = modulation(dp[(j + 1) % 3][i - 1], dp[(j + 2) % 3][i - 1]);
            }
        }

        return dp[0][n - 1];
    }

    private static int modulation(int a, int b) {
        return (a % MOD + b % MOD) % MOD;
    }
}
