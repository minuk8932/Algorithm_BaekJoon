package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2624번: 동전 바꿔주기
 *
 * @see https://www.acmicpc.net/problem/2624
 *
 */
public class Boj2624 {

    private static int[][] dp;
    private static int[] penny;
    private static int[] count;

    private static int T, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        penny = new int[k];
        count = new int[k];
        for(int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            penny[i] = Integer.parseInt(st.nextToken());
            count[i] = Integer.parseInt(st.nextToken());
        }

        init();
        System.out.println(recursion(T, 0));
    }

    /**
     *
     * Recursion
     *
     * line 57: make exchange, with coin size
     *
     * @param total
     * @param current
     * @return
     */
    private static int recursion(int total, int current) {
        if(total == 0) return 1;
        if(total < 0 || current >= k) return 0;

        if(dp[total][current] != -1) return dp[total][current];
        int result = 0;

        for(int size = 0; size <= count[current]; size++) {
            result += recursion(total - penny[current] * size, current + 1);
        }

        return dp[total][current] = result;
    }

    private static void init() {
        dp = new int[T + 1][k];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
    }
}
