package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14925번: 목장 건설하기
 *
 * @see https://www.acmicpc.net/problem/14925/
 *
 */
public class Boj14925 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] land = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(makeFarm(N, M, land));
    }

    private static int makeFarm(int n, int m, int[][] l) {
        int[][] dp = new int[n][m];
        int result = 0;

        dp[0][0] = result = l[0][0] == 0 ? 1: 0;
        for(int i = 1; i < n; i++) {
            dp[i][0] = l[i][0] == 0 ? 1: 0;
            result = Math.max(result, dp[i][0]);
        }

        for(int i = 1; i < m; i++) {
            dp[0][i] = l[0][i] == 0 ? 1: 0;
            result = Math.max(result, dp[0][i]);
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {            // find small square when, (i - 1, j) (i - 1, j - 1) (i, j - 1)
                if(l[i][j] != 0) continue;

                dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                result = Math.max(dp[i][j], result);
            }
        }

        return result;
    }
}
