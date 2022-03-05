package dynamic_programming;

import common.Point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Predicate;

/**
 *
 * @author exponential-e
 * 백준 24426번: 알고리즘 수업 - 행렬 경로 문제 3
 *
 * @see https://www.acmicpc.net/problem/24426
 *
 */
public class Boj24426 {

    private static int n;
    private static int[][] map;
    private static long[][] dp;

    private static Point<Integer, Integer> stopover;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        stopover = new Point.Builder(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
                .build();
        Point<Integer, Integer> start = new Point.Builder(0, 0).build();
        Point<Integer, Integer> end = new Point.Builder(n, n).build();

        long one = path(start, stopover);
        long another = path(stopover, end);
        long duplicate = map[stopover.getRow()][stopover.getCol()];
        long except = pathExcept(start, end);

        System.out.println(one + another - duplicate + " " + except);
    }

    private static long pathExcept(Point<Integer, Integer> start, Point<Integer, Integer> end) {
        dp = new long[n + 1][n + 1];

        boolean flag = false;
        for (int i = start.getRow() + 1; i <= end.getRow(); i++) {
            flag |= STOPPED.test(new Point.Builder(i, 1).build());
            if(flag){
                map[i][1] = 0;
                continue;
            }

            dp[i][1] = map[i][1] + dp[i - 1][1];
        }

        flag = false;
        for (int i = start.getCol() + 1; i <= end.getCol(); i++) {
            flag |= STOPPED.test(new Point.Builder(1, i).build());
            if(flag){
                map[1][i] = 0;
                continue;
            }

            dp[1][i] = map[1][i] + dp[1][i - 1];
        }

        for(int i = start.getRow() + 1; i <= end.getRow(); i++) {
            for(int j = start.getCol() + 1; j <= end.getCol(); j++) {
                if(STOPPED.test(new Point.Builder(i, j).build())) continue;
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + map[i][j];
            }
        }

        return dp[end.getRow()][end.getCol()];
    }

    private static long path(Point<Integer, Integer> start, Point<Integer, Integer> end) {
        dp = new long[n + 1][n + 1];
        int s = start.getRow() == 0 ? 1: start.getRow();
        int e = start.getCol() == 0 ? 1: start.getCol();

        for (int i = s; i <= end.getRow(); i++) {
            dp[i][e] = map[i][e] + dp[i - 1][e];
        }

        for (int i = e; i <= end.getCol(); i++) {
            dp[s][i] = map[s][i] + dp[s][i - 1];
        }

        for(int i = s; i <= end.getRow(); i++) {
            for(int j = e; j <= end.getCol(); j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + map[i][j];
            }
        }

        return dp[end.getRow()][end.getCol()];
    }

    private static final Predicate<Point> STOPPED = p ->
            p.getRow() == stopover.getRow() && p.getCol() == stopover.getCol();
}
