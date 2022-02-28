package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17216번: 가장 큰 감소 부분 수열
 *
 * @see https://www.acmicpc.net/problem/17216
 *
 */
public class Boj17216 {

    private static int[] A;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        A = new int[N + 1];
        dp = new int[1_001][N + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(recursion(0, N));
    }

    private static int recursion(int prev, int current) {
        if(current == 0) return 0;

        if(dp[prev][current] != -1) return dp[prev][current];
        int answer = recursion(prev, current - 1);

        if(prev < A[current]) {
            answer = Math.max(answer, recursion(Math.max(prev, A[current]), current - 1) + A[current]);
        }

        return dp[prev][current] = answer;
    }
}
