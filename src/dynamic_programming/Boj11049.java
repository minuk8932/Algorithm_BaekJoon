package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11049번: 행렬 곱셈 순서
 *
 * @see https://www.acmicpc.net/problem/11049/
 *
 * Same as
 * @see https://github.com/minuk8932/Algorithm_BaekJoon/blob/master/src/dynamic_programming/Boj11066.java
 *
 */
public class Boj11049 {
    private static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] mat = new int[N + 1];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            mat[i] = x;
            if(i == N - 1) mat[N] = y;
        }

        System.out.println(cartesianProduct(N, mat));
    }

    private static int cartesianProduct(int n, int[] m){
        dp = new int[n + 1][n + 1];

        for (int x = 0; x < n; x++) {
            for (int y = 1; y <= n - x; y++) {
                int z = x + y;
                if (y == z) continue;

                dp[y][z] = Integer.MAX_VALUE;

                for (int i = y; i < z; i++) {
                    dp[y][z] = Math.min(dp[y][z], dp[y][i] + dp[i + 1][z] + m[y - 1] * m[i] * m[z]);
                }
            }
        }

        return dp[1][n];
    }
}
