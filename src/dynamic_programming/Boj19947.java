package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19947번: 투자의 귀재 배주형
 *
 * @see https://www.acmicpc.net/problem/19947
 *
 */
public class Boj19947 {

    private static int[] dp;
    private static int H;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        dp = new int[Y + 1];
        Arrays.fill(dp, -1);

        System.out.println(recursion(Y));
    }

    private static int recursion(int year) {
        if(year <= 0) return H;

        if(dp[year] != -1) return dp[year];
        int answer = (int) (recursion(year - 1) * (1.05));

        if(year >= 3) answer = (int) Math.max(answer, recursion(year - 3) * (1.2));
        if(year >= 5) answer = (int) Math.max(answer, recursion(year - 5) * (1.35));

        return dp[year] = answer;
    }
}
