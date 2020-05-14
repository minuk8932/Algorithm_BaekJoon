package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15724번: 주지수
 *
 * @see https://www.acmicpc.net/problem/15724/
 *
 */
public class Boj15724 {
    private static int[][] dp;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] country = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= M; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init(N, M, country);

        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());

        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append(dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init(int n, int m, int[][] c) {
        dp = new int[n + 1][m + 1];

        for(int i = 1; i <= m; i++) {
            dp[0][i] = dp[0][i - 1] + c[0][i];
        }

        for(int i = 1; i <= n; i++) {               // make prefix sum
            for(int j = 1; j <= m; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + c[i][j];
            }
        }
    }
}
