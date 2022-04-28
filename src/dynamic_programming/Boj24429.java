package dynamic_programming;

import common.Point;
import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 24429번: 알고리즘 수업 - 행렬 경로 문제6
 *
 * @see https://www.acmicpc.net/problem/24429
 *
 */
public class Boj24429 {

    private static int[][] map;
    private static int[][] dp;

    private static Map<Integer, Integer> interceptor = new HashMap<>();
    private static Queue<Point<Integer, Integer>> pq;

    private static Point<Integer, Integer> end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];
        dp = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        Point<Integer, Integer> target = new Point.Builder(n, n).build();
        int P = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>(Comparator.comparingInt(Point::getCost));

        while(P-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            int distance = manhattanDistance(new Point.Builder(row, col).build(), target);
            pq.offer(new Point.Builder(row, col).cost(distance).build());
            interceptor.merge(distance, 1, Integer::sum);
        }

        Point<Integer, Integer> last = new Point.Builder(1, 1).build();
        int distance = manhattanDistance(last, target);
        pq.offer(new Point.Builder(1, 1).cost(distance).build());
        interceptor.merge(distance, 1, Integer::sum);

        System.out.println(process(target));
    }

    private static int process(Point<Integer, Integer> start) {
        int answer = 0;

        while(!pq.isEmpty()) {
            Point current = pq.poll();

            end = new Point.Builder(current.getRow(), current.getCol()).build();

            if(interceptor.get(current.getCost()) > 1) return -1;
            if(start.getRow() < end.getRow() || start.getCol() < end.getCol()) return -1;

            answer += recursion(start);
            start = end;
        }

        return answer + map[1][1];
    }

    /**
     *
     * Recursion
     *
     * line 110: except not search value.
     *
     * @param current
     * @return
     */
    private static int recursion(Point<Integer, Integer> current) {
        if(current.getRow() == end.getRow() && current.getCol() == end.getCol()) return 0;

        if (dp[current.getRow()][current.getCol()] != -1)
            return dp[current.getRow()][current.getCol()];

        Point<Integer, Integer> next;
        int answer = -1;

        if(current.getRow() > end.getRow()) {
            next = new Point.Builder(current.getRow() - 1, current.getCol()).build();
            answer = Math.max(recursion(next) + map[current.getRow()][current.getCol()]
                , answer);
        }

        if(current.getCol() > end.getCol()) {
            next = new Point.Builder(current.getRow(), current.getCol() - 1).build();
            answer = Math.max(recursion(next) + map[current.getRow()][current.getCol()]
                , answer);
        }

        if (answer == -1) return 0;
        return dp[current.getRow()][current.getCol()] = answer;
    }

    private static int manhattanDistance(Point<Integer, Integer> src, Point<Integer, Integer> snk) {
        return Math.abs(src.getRow() - snk.getRow()) + Math.abs(src.getCol() - snk.getCol());
    }
}
