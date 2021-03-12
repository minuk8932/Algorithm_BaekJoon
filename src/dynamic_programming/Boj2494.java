package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 2494번: 숫자 맞추기
 *
 * @see https://www.acmicpc.net/problem/2494
 *
 */
public class Boj2494 {

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static int N;
    private static int[][] dp;
    private static int[] src, snk;

    private static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        src = stringToIntArray(br.readLine());
        snk = stringToIntArray(br.readLine());

        dp = new int[N][10];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(recursion(0, 0)).append(NEW_LINE);

        printer(0, 0);
        int idx = 1;

        for(int a: list) {
            sb.append(idx++).append(SPACE).append(a).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Printer
     *
     * line 68: find min cost
     * line 71 ~ 78: get current offset
     *
     * @param wheel
     * @param offset
     */
    private static void printer(int wheel, int offset) {
        if(wheel == N) return;

        int current = (src[wheel] + offset) % 10;

        int left = (snk[wheel] - current + 10) % 10;
        int right = (current - snk[wheel] + 10) % 10;

        int[] cost = {recursion(wheel + 1, (offset + left) % 10) + left
                , recursion(wheel + 1, offset) + right};

        if(cost[0] < cost[1]){
            list.add(left);
            printer(wheel + 1, (offset + left) % 10);
        }
        else {
            list.add(-right);
            printer(wheel + 1, offset);
        }
    }

    /**
     *
     * Recursion
     *
     * line 99 ~ 102: find min cost, when turn left(+) or right(-)
     *
     * @param wheel
     * @param offset
     * @return
     */
    private static int recursion(int wheel, int offset) {
        if(wheel == N) return 0;

        int result = dp[wheel][offset];
        if (result != -1) return result;

        int current = (src[wheel] + offset) % 10;

        int left = (snk[wheel] - current + 10) % 10;
        int right = (current - snk[wheel] + 10) % 10;
        result = Math.min(recursion(wheel + 1, (offset + left) % 10) + left
                , recursion(wheel + 1, offset) + right);

        return dp[wheel][offset] = result;
    }

    private static int[] stringToIntArray(String input) {
        int[] arr = new int[N];

        for(int i = 0; i < N; i++){
            arr[i] = input.charAt(i) - '0';
        }

        return arr;
    }
}
