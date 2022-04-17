package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 6506번: 엘 도라도
 *
 * @see https://www.acmicpc.net/problem/6506
 *
 */
public class Boj6506 {

    private static final String NEW_LINE = "\n";
    private static long[][] dp;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if(n == 0 && k == 0) break;

            arr = new int[n + 1];
            st = new StringTokenizer(br.readLine());

            for(int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dp = new long[n + 1][k + 1];
            for(int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], -1L);
            }

            long answer = 0;
            for(int i = 1; i <= n; i++) {
                answer += recursion(i, k - 1);
            }

            sb.append(answer).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static long recursion(int current, int count) {
        if(count == 0) return 1L;

        if(dp[current][count] != -1) return dp[current][count];
        long answer = 0;

        for(int next = current + 1; next < arr.length; next++) {
            if (arr[current] >= arr[next]) continue;
            answer += recursion(next, count - 1);
        }

        return dp[current][count] = answer;
    }
}
