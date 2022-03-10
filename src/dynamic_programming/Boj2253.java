package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2253번: 점프
 *
 * @see https://www.acmicpc.net/problem/2253
 *
 */
public class Boj2253 {

    private static final int SIZE = 10_001;

    private static boolean[] skip = new boolean[SIZE];
    private static int N;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        while(M-- > 0) {
            skip[Integer.parseInt(br.readLine())] = true;
        }

        dp = new int[N + 1][146];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], SIZE);
        }

        int result = recursion(1, 0);
        System.out.println(result == SIZE ? -1: result);
    }

    private static int recursion(int current, int jump) {
        if(current == N) return 0;
        if(dp[current][jump] != SIZE) return dp[current][jump];
        int answer = SIZE;

        for(int i = -1; i <= 1; i++) {
            int nJump = jump + i;
            if (nJump <= 0) continue;

            int next = current + nJump;
            if(next > N || skip[next]) continue;

            answer = Math.min(answer, recursion(next, nJump) + 1);
        }

        return dp[current][jump] = answer;
    }
}
