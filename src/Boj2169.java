import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2169 {
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

        System.out.println(findPath(N, M, map));
    }

    private static long findPath(int n, int m, int[][] arr){
        long[][] dp = new long[n][m];
        for(int i = 0; i < n; i++){
            dp[i][0] = arr[i][0];
        }

        for(int i = 0; i < m; i++){
            dp[i][i] = arr[0][i];
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(j == m - 1 || i ==  n - 1){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
                }
                else{
                    dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], dp[i][j + 1])) + arr[i][j];
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}
