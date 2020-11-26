package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20208번: 진우의 민트초코우유
 *
 * @see https://www.acmicpc.net/problem/20208
 *
 */
public class Boj20208 {
    private static int max, H;
    private static boolean[] visit;
    private static Point home;
    private static ArrayList<Point> milk = new ArrayList<>();

    private static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int valley = Integer.parseInt(st.nextToken());
                if (valley == 1) home = new Point(i, j);
                if (valley == 2) milk.add(new Point(i, j));
            }
        }

        bruteForce(M);
        System.out.println(max);
    }

    private static void bruteForce(int m) {
        int size = milk.size();

        for(int i = 0; i < size; i++) {
            visit = new boolean[size];
            recursion(milk.get(i), i, m - manhattanDistance(home, milk.get(i)), 1);
        }
    }

    private static void recursion(Point current, int idx, int spare, int count) {
        if (spare < 0) return;
        if (manhattanDistance(current, home) <= (spare += H)) max = Math.max(count, max); // possibility: go back to home
        if (count == visit.length) return;
        visit[idx] = true;

        for (int i = 0; i < visit.length; i++) {
            if(visit[i]) continue;
            Point next = milk.get(i);               // next milk

            recursion(next, i, spare - manhattanDistance(current, next), count + 1);
            visit[i] = false;
        }
    }

    private static int manhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.row - p2.row) + Math.abs(p1.col - p2.col);
    }
}
