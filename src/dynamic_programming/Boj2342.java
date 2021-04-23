package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2342번: Dance Dance Revolution
 *
 * @see https://www.acmicpc.net/problem/2342
 *
 */
public class Boj2342 {

    private static List<Integer> btns = new ArrayList<>();
    private static int[][][] dp;

    private static int size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()) {
            int button = Integer.parseInt(st.nextToken());
            if(button == 0) break;

            btns.add(button);
        }

        size = btns.size();
        init();
        System.out.println(recursion(0, 0, 0));
    }

    /**
     *
     * Recursion
     *
     * line 60 ~ 61: check current position & get next cost
     *
     * @param idx
     * @param left
     * @param right
     * @return
     */
    private static int recursion(int idx, int left, int right) {
        if(idx == size) return 0;

        if(dp[idx][left][right] != -1) return dp[idx][left][right];
        int target = btns.get(idx);
        int result;

        int[] add = new int[2];
        int[] adjacent = target % 2 == 1 ? new int[]{2, 4} : new int[]{1, 3};

        add[0] = push(left, target, adjacent[0], adjacent[1]);
        add[1] = push(right, target, adjacent[0], adjacent[1]);

        result = Math.min(recursion(idx + 1, target, right) + add[0]
                , recursion(idx + 1, left, target) + add[1]);

        return dp[idx][left][right] = result;
    }

    private static int push(int foot, int cur, int adj1, int adj2) {
        if(foot == 0) return 2;
        else if(foot == cur) return 1;
        else if(foot == adj1 || foot == adj2) return 3;
        else return 4;
    }

    private static void init() {
        dp = new int[btns.size()][5][5];

        for (int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }
}
