package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3114번: 사과와 바나나
 *
 * @see https://www.acmicpc.net/problem/3114/
 *
 */
public class Boj3114 {
    private static int[][] B;
    private static int[][] A;
    private static int N, M;

    private static int[][] dp;
    private static final char APPLE = 'A';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N + 2][M + 2];
        B = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= M; j++) {
                String input = st.nextToken();

                if (input.charAt(0) == APPLE) A[i][j] = Integer.parseInt(input.substring(1));
                else B[i][j] = Integer.parseInt(input.substring(1));
            }
        }

        init();                             // make accumulate
        System.out.println(boundary());
    }

    private static int boundary (){
        dp = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            dp[i][1] = dp[i - 1][1] + B[i][2];
        }

        for(int j = 1; j <= M; j++) {
            dp[1][j] = dp[0][j] + B[1][j + 1];
        }

        for(int i = 2; i <= N; i++){
            for(int j = 2; j <= M; j++){
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + A[i][j - 1] + B[i][j + 1];        // make path
                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + B[i][j + 1] - B[i][j]);
            }
        }

        return dp[N][M];
    }

    private static void init(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                A[i][j] += A[i][j - 1];
                B[i][M - j] += B[i][M - j + 1];
            }
        }
    }
}