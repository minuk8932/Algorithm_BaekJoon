package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2248번: 이진수 찾기
 *
 * @see https://www.acmicpc.net/problem/2248
 *
 */
public class Boj2248 {

    private static long[][] dp;
    private static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        long I = Long.parseLong(st.nextToken());

        dp = new long[N + 1][L + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        result = new int[N];
        finder(N, L, I, 0);

        StringBuilder sb = new StringBuilder();
        for(int r: result){
            sb.append(r);
        }

        System.out.println(sb.toString());
    }

    private static long recursion(int n, int m){
        if(m == 0 || n == 0) return 1L;
        if(dp[n][m] != -1) return dp[n][m];

        long result = recursion(n - 1, m);
        if(m > 0) result += recursion(n - 1, m - 1);

        return dp[n][m] = result;
    }

    /**
     *
     * Finder
     *
     * line 74: find pivot which current position
     * line 75 ~ 82: can put '1' ?
     *
     * @param n
     * @param m
     * @param k
     * @param p
     */
    private static void finder(int n, int m, long k, int p){
        if(n == 0) return;
        if(m == 0){
            for(int i = 0; i < n; i++) {
                result[p + i] = 0;
            }

            return;
        }

        long pivot = recursion(n - 1, m);
        if(k <= pivot){
            result[p] = 0;
            finder(n - 1, m, k, p + 1);
        }
        else{
            result[p] = 1;
            finder(n - 1, m - 1, k - pivot, p + 1);
        }
    }
}