package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5535번: 패셔니스타
 *
 * @see https://www.acmicpc.net/problem/5535
 *
 */
public class Boj5535 {

    private static int[][] dp;
    private static int N, D;

    private static int[] T;
    private static Clothes[] clothes;

    private static class Clothes {
        int from;
        int to;
        int luxury;

        public Clothes(int from, int to, int luxury) {
            this.from = from;
            this.to = to;
            this.luxury = luxury;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        T = new int[D];
        for(int i = 0; i < D; i++) {
            T[i] = Integer.parseInt(br.readLine());
        }

        clothes = new Clothes[N + 1];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            clothes[i] = new Clothes(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken()));
        }

        init();
        System.out.println(recursion(0, N));
    }

    /**
     *
     * Recursion
     *
     * line 73: first value except, find maximum difference
     *
     * @param day
     * @param fashion
     * @return
     */
    private static int recursion(int day, int fashion) {
        if(day >= D) return 0;
        if(dp[day][fashion] != -1) return dp[day][fashion];
        int result = 0;

        for(int i = 0; i < N; i++) {
            if(outOfTemperature(T[day], clothes[i])) continue;
            result = Math.max(result, recursion(day + 1, i)
                    + (fashion == N ? 0: Math.abs(clothes[i].luxury - clothes[fashion].luxury)));
        }

        return dp[day][fashion] = result;
    }

    private static boolean outOfTemperature(int target, Clothes src) {
        return target < src.from || target > src.to;
    }

    private static void init() {
        dp = new int[D + 1][N + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
    }
}
