package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13302번: 리조트
 *
 * @see https://www.acmicpc.net/problem/13302
 *
 */
public class Boj13302 {

    private static boolean[] holi;
    private static int[][] dp;
    private static int N;
    private static final int[] COST = {10_000, 25_000, 37_000};

    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        holi = new boolean[N + 1];
        dp = new int[N + 1][101];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        if(M != 0) {
            st = new StringTokenizer(br.readLine());

            while (M-- > 0) {
                holi[Integer.parseInt(st.nextToken())] = true;
            }
        }

        System.out.println(recursion(1, 0));
    }

    /**
     *
     * Recursion
     *
     * line 67 ~ 69: tickets cost & coupons
     * line 71: exchange 3 coupons to 1 day ticket
     *
     * @param day
     * @param coupon
     * @return
     */
    private static int recursion(int day, int coupon) {
        if(day > N) return 0;

        if(dp[day][coupon] != -1) return dp[day][coupon];
        int result = INF;

        if(holi[day]) {
            result = recursion(day + 1, coupon);
        }
        else {
            for(int i = 0; i < 3; i++) {
                result = Math.min(result, recursion(day + 2 * i + 1, coupon + i) + COST[i]);
            }

            if(coupon >= 3) result = Math.min(result, recursion(day + 1, coupon - 3));
        }

        return dp[day][coupon] = result;
    }
}
