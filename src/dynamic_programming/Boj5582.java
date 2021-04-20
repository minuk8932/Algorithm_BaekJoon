package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 5582번: 공통 부분 문자열
 *
 * @see https://www.acmicpc.net/problem/5582
 *
 */
public class Boj5582 {

    private static char[] S, T;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();
        T = br.readLine().toCharArray();

        dp = new int[S.length + 1][T.length + 1];
        System.out.println(find(S.length, T.length));
    }

    private static int find(int N, int M) {
        int result = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (S[i - 1] != T[j - 1]) continue;
                dp[i][j] = dp[i - 1][j - 1] + 1;
                result = Math.max(dp[i][j], result);
            }
        }

        return result;
    }
}

