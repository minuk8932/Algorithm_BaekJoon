package string_handle.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 1787번: 문자열 주기 예측
 *
 * @see https://www.acmicpc.net/problem/1787/
 *
 */
public class Boj1787 {
    private static long[] dp;
    private static int[] fail;

    private static final long INF = 1_000_000_000_000L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[] string = br.readLine().toCharArray();
        System.out.println(predictPeriod(n, string));
    }

    private static long predictPeriod(int n, char[] str) {
        failure(n, str);

        long result = 0;

        dp = new long[n];
        Arrays.fill(dp, -1);

        for(int i = 0; i < n; i++) {
            long len = recursion(i);
            result += (i + 1) - (len == INF ? i + 1: len);                              // add accumulate period
        }

        return result;
    }

    private static long recursion(int current) {
        if(current < 0) return INF;
        if(fail[current] == 0) return INF;

        if(dp[current] != -1) return dp[current];
        dp[current] = Math.min(fail[current], recursion(fail[current] - 1));     // current minimum peroid

        return dp[current];
    }

    private static void failure(int n, char[] str) {
        fail = new int[n];

        int j = 0;
        for(int i = 1; i < n; i++) {
            while(j > 0 && str[i] != str[j]) {
                j = fail[j - 1];
            }

            if(str[i] == str[j]) fail[i] = ++j;
        }
    }
}
