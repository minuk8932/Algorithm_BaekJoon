package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17397번: FLEX
 *
 * @see https://www.acmicpc.net/problem/17397
 *
 */
public class Boj17397 {

    private static final int INF = 1_000_000_000;
    private static int[][][] dp;
    private static int[] C;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        C = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][M + 1][11];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        System.out.println(recursion(1, M, 0));
    }

    private static int recursion(int current, int money, int budget) {
        if(current == N) return 0;

        if(dp[current][money][budget] != INF) return dp[current][money][budget];
        int answer = INF;

        int loop = Math.min(10, money);
        for(int i = 0; i <= loop; i++){
            answer = Math.min(answer, recursion(current + 1, money - i, i)
                + pow(C[current] + budget, C[current + 1] + i));
        }

        return dp[current][money][budget] = answer;
    }

    private static int pow(int a, int b) {
        int diff = a - b;
        return a > b ? diff * diff: 0;
    }
}
