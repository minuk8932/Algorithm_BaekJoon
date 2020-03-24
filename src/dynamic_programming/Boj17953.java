package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17953번: 디저트
 *
 * @see https://www.acmicpc.net/problem/
 *
 */
public class Boj17953 {
    private static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] dessert = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++){
                dessert[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(getSatisfy(N, M, dessert));
    }

    private static int getSatisfy(int n, int m, int[][] arr){
        dp = new int[n][m];
        for(int i = 0; i < n; i++){
            dp[i][0] = arr[i][0];               // first day
        }

        int result = 0;

        for(int j = 1; j < m; j++){
            for(int i = 0; i < n; i++){
                for(int k = 0; k < n; k++){
                    dp[i][j] = Math.max((i == k ? arr[i][j] / 2: arr[i][j]) + dp[k][j - 1], dp[i][j]);      // i: current, k: all kinds, j: days
                }
            }
        }

        for(int i = 0; i < n; i++){
            if(dp[i][m - 1] > result) result = dp[i][m - 1];        // find max
        }

        return result;
    }
}
