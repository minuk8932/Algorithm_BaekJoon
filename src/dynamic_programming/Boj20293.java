package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 20293번: 연료가 부족해
 *
 * @see https://www.acmicpc.net/problem/20293
 *
 */
public class Boj20293 {

    private static ArrayList<Fuel> list = new ArrayList<>();
    private static long[] dp;

    private static final int INF = 10_000_000;

    private static int[][] map;
    private static int R, C;

    private static class Fuel implements Comparable<Fuel>{
        int row;
        int col;
        long cost;

        public Fuel(int row, int col, long cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(Fuel f) {
            return this.row + this.col - f.row - f.col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        int N = Integer.parseInt(br.readLine());

        list.add(new Fuel(0, 0, 0));
        list.add(new Fuel(R - 1, C - 1, 0));

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            map[row][col] = Integer.parseInt(st.nextToken());
            list.add(new Fuel(row, col, map[row][col]));
        }

        Collections.sort(list);
        System.out.println(binarySearch());
    }

    /**
     *
     * find possible fuel quantity by check points
     *
     * @return
     */
    private static int binarySearch() {
        int start = 0, end = 6_000;
        int result = INF;

        dp = new long[list.size()];

        while(start <= end) {
            int mid = (start + end) >> 1;

            if(search(mid)) {
                result = Math.min(mid, result);
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        return result;
    }

    /**
     *
     * just search reachable & shortest path -> get needs fuel
     *
     * @param cost
     * @return
     */
    private static boolean search(long cost) {
        Arrays.fill(dp, -1);
        dp[0] = cost;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (outOfRange(list.get(i), list.get(j))) continue;

                long distance = manhattanDistance(list.get(i), list.get(j));
                if (dp[j] < distance) continue;

                dp[i] = Math.max(dp[i], dp[j] - distance + list.get(i).cost);
            }
        }

        return dp[dp.length - 1] != -1;
    }

    private static int manhattanDistance(Fuel f1, Fuel f2) {
        return Math.abs(f1.row - f2.row) + Math.abs(f1.col - f2.col);
    }

    private static boolean outOfRange(Fuel f1, Fuel f2) {
        return f1.row < f2.row || f1.col < f2.col;
    }
}
