package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20152번: Game Addiction
 *
 * @see https://www.acmicpc.net/problem/20152
 *
 */
public class Boj20152 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        System.out.println(path(H, N));
    }

    private static long path(int h, int n) {
        if(h == n) return 1;

        int min = Math.min(n, h);
        int max = Math.max(n, h);

        max -= min;
        long[][] dp = new long[max + 1][max + 1];

        for(int i = 0; i <= max; i++) {
            dp[0][i] = 1L;
        }

        for(int row = 1; row <= max; row++) {
            for(int col = 1; col <= max; col++) {
                if(row > col) continue;
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
            }
        }

        return dp[max][max];
    }
}
