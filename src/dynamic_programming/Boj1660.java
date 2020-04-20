package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 1660번: 캡틴 이다솜
 *
 * @see https://www.acmicpc.net/problem/1660/
 *
 */
public class Boj1660 {
    private static int[] dp = new int[122];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init();
        System.out.println(find(N));
    }

    private static void init() {                        // each floor sum
        for(int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + (1 + i) * i / 2;
        }
    }

    private static int find(int n) {
        int[] result = new int[n + 1];

        for(int i = dp[1]; i <= n; i++) {
            result[i] = result[i - dp[1]] + 1;
        }

        for(int i = 2; i < dp.length; i++) {
            for (int j = dp[i]; j <= n; j++) {          // make tower
                result[j] = Math.min(result[j - dp[i]] + 1, result[j]);
            }
        }

        return result[n];
    }
}
