package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

/**
 *
 * @author exponential-e
 * 백준 17271번: 리그 오브 레전설 (small)
 *
 * @see https://www.acmicpc.net/problem/17271
 *
 */
public class Boj17271 {

    private static final int MOD = 1_000_000_007;

    private static int[] dp;
    private static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        System.out.println(recursion(N));
    }

    private static int recursion(int n) {
        if(n < 0) return 0;
        if(n == 0) return 1;

        if(dp[n] != -1) return dp[n];

        int answer = recursion(n - 1);
        if(n >= M) answer = MODULATION.apply(answer, recursion(n - M));

        return dp[n] = answer;
    }

    private static final BiFunction<Integer, Integer, Integer> MODULATION = (a, b) -> ((a % MOD) + (b % MOD)) % MOD;
}
