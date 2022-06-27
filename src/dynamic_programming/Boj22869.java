package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 22869번: 징검다리 건너기(small)
 *
 * @see https://www.acmicpc.net/problem/22869
 *
 */
public class Boj22869 {

    private static int[] A;
    private static int[] dp;
    private static String result = "NO";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];
        Arrays.fill(dp, -1);
        hopping(0, K);

        System.out.println(result);
    }

    private static int hopping(int current, int limit) {
        if(current == A.length - 1) result = "YES";

        if(dp[current] != -1) return dp[current];
        int answer = -1;

        for(int next = current + 1; next < A.length; next++) {
            if(force(current, next, A[current], A[next]) > limit) continue;
            answer = 1;
            hopping(next, limit);
        }

        return dp[current] = (answer == -1? 0: answer);
    }

    private static int force(int idx1, int idx2, int a1, int a2) {
        return (idx2 - idx1) * (1 + Math.abs(a1 - a2));
    }
}
