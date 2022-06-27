package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 22871번: 징검다리 건너기
 *
 * @see https://www.acmicpc.net/problem/22871
 *
 */
public class Boj22871 {

    private static final long INF = 1_000_000_000_000L;
    private static long[] A;
    private static int[] dp;

    private static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        A = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(binarySearch(N));
    }

    private static long binarySearch(int n) {
        long start = 0;
        long end = INF;

        long answer = INF;

        while(start <= end) {
            long mid = (start + end) >> 1L;

            flag = false;
            dp = new int[n];
            Arrays.fill(dp, -1);
            recursion(0, mid);

            if(flag) {
                answer = Math.min(answer, mid);
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        return answer;
    }

    private static int recursion(int current, long limit) {
        if(current == A.length - 1) flag = true;

        if(dp[current] != -1) return dp[current];
        int answer = -1;

        for(int next = current + 1; next < A.length; next++) {
            if(force(current, next, A[current], A[next]) > limit) continue;
            answer = 1;
            recursion(next, limit);
        }

        return dp[current] = (answer == -1? 0: answer);
    }

    private static long force(int idx1, int idx2, long a1, long a2) {
        return (1L + Math.abs(a1 - a2)) * (idx2 - idx1);
    }
}
