package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15486번: 퇴사2
 *
 * @see https://www.acmicpc.net/problem/15486
 *
 */
public class Boj15486 {

    private static int[] T, P;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        T = new int[N + 2];
        P = new int[N + 2];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(consulting());
    }

    /**
     *
     * Consulting
     *
     * line 46 ~ 47: get next or find max between next and select current
     *
     * @return
     */
    private static int consulting() {
        int[] dp = new int[N + 2];

        for (int i = N; i >= 1; i--) {
            if (i + T[i] > N + 1) dp[i] = dp[i + 1];
            else dp[i] = Math.max(dp[i + 1], dp[i + T[i]] + P[i]);
        }

        return dp[1];
    }
}
