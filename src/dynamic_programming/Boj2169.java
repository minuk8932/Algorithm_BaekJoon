package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2169번: 로봇 조종하기
 *
 * @see https://www.acmcicp.net/problem/2169/
 *
 */
public class Boj2169 {
    private static long[][] dp;
    private static final long MIN_INF = Long.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(search(N, M, map));
    }

    private static long search(int n, int m, int[][] map){
        dp = new long[n][m];
        dp[0][0] = map[0][0];

        for(int i = 1; i < m; i++){                 // ->
            dp[0][i] = dp[0][i - 1] + map[0][i];
        }

        long[][] save = new long[2][m];

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < m; j++) {             // temporary saved, make cases
                int k = m - 1 - j;

                save[0][j] = Math.max(dp[i - 1][j], j == 0 ? MIN_INF: save[0][j - 1]) + map[i][j];          // left
                save[1][k] = Math.max(dp[i - 1][k], k == m - 1 ? MIN_INF: save[1][k + 1]) + map[i][k];      // right
            }

            for(int j = 0; j < m; j++) {
                dp[i][j] = Math.max(save[0][j], save[1][j]);
            }
        }

        return dp[n - 1][m - 1];
    }
}
