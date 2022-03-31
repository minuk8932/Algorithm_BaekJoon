package dynamic_programming;

import common.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2618 {

    private static final String NEW_LINE = "\n";
    private static int W;

    private static int[][] dp;
    private static Point<Integer, Integer>[][] events;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        W = Integer.parseInt(br.readLine());
        events = new Point[2][W + 1];
        for(int i = 1; i <= W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            events[0][i] = new Point.Builder<Integer, Integer>(row, col).build();
            events[1][i] = new Point.Builder<Integer, Integer>(row, col).build();
        }

        events[0][0] = new Point.Builder<Integer, Integer>(1, 1).build();
        events[1][0] = new Point.Builder<Integer, Integer>(N, N).build();

        dp = new int[W + 1][W + 1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        sb.append(recursion(0, 0)).append(NEW_LINE);
        trace(0, 0);
        System.out.println(sb.toString());
    }

    private static void trace(int left, int right) {
        if(right ==  W || left == W) return;

        int next = Math.max(left, right) + 1;
        int leftNext = dp[next][right] + manhattanDistance(0, left, next);
        int rightNext = dp[left][next] + manhattanDistance(1, right, next);

        if(leftNext < rightNext){
            sb.append(1).append(NEW_LINE);
            trace(next, right);
            return;
        }

        sb.append(2).append(NEW_LINE);
        trace(left, next);
    }

    private static int recursion(int left, int right) {
        if(right == W || left == W) return 0;

        if(dp[left][right] != -1) return dp[left][right];
        int next = Math.max(left, right) + 1;

        int answer = Math.min(
            recursion(next, right) + manhattanDistance(0, left, next)
            , recursion(left, next) + manhattanDistance(1, right, next));

        return dp[left][right] = answer;
    }

    private static int manhattanDistance(int idx, int prev, int current) {
        return Math.abs(events[idx][prev].getRow() - events[idx][current].getRow())
            + Math.abs(events[idx][prev].getCol() - events[idx][current].getCol());
    }
}
