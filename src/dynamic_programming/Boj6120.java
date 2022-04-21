package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 6120번: Cow Digit Game
 *
 * @see https://www.acmicpc.net/problem/6120
 *
 */
public class Boj6120 {

    private static final int INF = 1_000_000_000;
    private static final String WIN = "YES\n";
    private static final String LOSE = "NO\n";
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        dp = new int[2][1_000_001];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(recursion(N, true) == 1 ? WIN: LOSE);
        }

        System.out.print(sb);
    }

    private static int recursion(int current, boolean flag) {
        int index = flag ? 1: 0;
        if(current < 10) return index;

        if(dp[index][current] != -1) return dp[index][current];

        int max = getter(current, 7, true);
        int min = getter(current, 7, false);

        int left = recursion(current - max, !flag);
        int right = recursion(current - min, !flag);

        int answer = flag ? Math.max(left, right): Math.min(left, right);

        return dp[index][current] = answer;
    }

    private static int getter(int current, int loop, boolean flag) {
        int result = flag ? 0: INF;

        while(loop-- > 0) {
            int value = current % 10;
            current /= 10;
            if(value == 0) continue;

            result = flag ? Math.max(result, value): Math.min(result, value);
        }

        return result;
    }
}