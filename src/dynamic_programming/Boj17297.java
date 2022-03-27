package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 17297번: Messi Gimossi
 *
 * @see https://www.acmicpc.net/problem/17297
 *
 */
public class Boj17297 {

    private static long[] dp = new long[41];
    private static int target = -1;

    private static final String[] MESSI = new String[] {"Messi", "Messi Gimossi"};
    private static final String CHAIN = "Messi Messi Gimossi";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        dp[1] = 5; dp[2] = 13;
        recursion(40);

        int index = subString(M);
        System.out.println(index == 6 ? CHAIN: MESSI[target - 1].charAt(index - 1));
    }

    /**
     *
     * Substring
     *
     * line 54 ~ line 61
     *      if, position 'index' is bigger than dp[target - 1] then, index - dp[target - 1] (right)
     *      else, just continue (left)
     *
     *      dp[target] composition
     *           ---dp[target-1]---      ---dp[target-2]---
     *      [#########################] [##################]
     *
     * @param index
     * @return
     */
    private static int subString(int index) {
        for(int i = dp.length - 1; i > 0; i--) {
            if(dp[i] < index) break;
            target = i;
        }

        while(target > 2) {
            target--;
            if(index == dp[target] + 1) return 6;

            if(index <= dp[target]) continue;
            index -= (dp[target] + 1);
            target--;
        }

        return index;
    }

    private static long recursion(int current) {
        if(current <= 2) return dp[current];

        if(dp[current] != 0) return dp[current];
        return dp[current] = recursion(current - 1) + recursion(current - 2) + 1;
    }
}
