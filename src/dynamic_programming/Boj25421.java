package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Boj25421 {

    private static final int MOD = 987_654_321;
    private static int[][] dp;
    private static HashMap<Integer, ArrayList<Integer>> range = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n + 1][10];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        init();

        int answer = 0;
        for(int start = 1; start < 10; start++) {
            answer += recursion(n, start);
            answer %= MOD;
        }

        System.out.println(answer);
    }

    private static int recursion(int current, int value) {
        if(current == 0) return 1;

        if(dp[current][value] != -1) return dp[current][value];
        int answer = recursion(current - 1, value);
        answer %= MOD;

        if (current > 1) {
            ArrayList<Integer> ranges = range.get(value);

            for(int candidate: ranges) {
                answer += recursion(current - 1, candidate);
                answer %= MOD;
            }
        }

        return dp[current][value] = answer % MOD;
    }

    private static void init() {
        for(int value = 1; value <= 9; value++) {
            ArrayList<Integer> ranges = new ArrayList<>();

            for(int add = -2; add <= 2; add++) {
                if(add == 0) continue;

                int summary = value + add;
                if(summary <= 0 || summary > 9) continue;

                ranges.add(summary);
            }

            range.put(value, ranges);
        }
    }
}
