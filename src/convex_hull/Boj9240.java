package convex_hull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 9240번: 로버트 후드
 *
 * @see https://www.acmicpc.net/problem/9240/
 *
 */
public class Boj9240 {
    private static ArrayDeque<Point> stack = new ArrayDeque<>();
    private static final int INF = 1_000_000_000;

    private static Point min = new Point(INF, INF);

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Point[] wall = new Point[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (y < min.y) {
                min = new Point(x, y);
            }

            else if (y == min.y) {
                if (x < min.x) {
                    min = new Point(x, y);
                }
            }

            wall[i] = new Point(x, y);
        }

        Arrays.sort(wall, Point.comparator);
        convexHull(N, wall);

        System.out.println(getDistance());
    }

    private static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        private static Comparator<Point> comparator = new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                long c = CCW(min, p1, p2);

                if (c > 0) {
                    return -1;
                }

                else if (c < 0) {
                    return 1;
                }

                else if (c == 0) {
                    long a = distancePow(min, p1);
                    long b = distancePow(min, p2);

                    if (a < b) return -1;
                    else if (a > b) return 1;
                    else if (a == b) return 0;
                }

                return 0;
            }
        };
    }

    private static long CCW(Point v1, Point v2, Point v3) {
        return (v1.x * v2.y + v2.x * v3.y + v3.x * v1.y) - (v3.x * v2.y + v2.x * v1.y + v1.x * v3.y);
    }

    private static long distancePow(Point p1, Point p2) {
        return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    }

    private static void convexHull(int N, Point[] p) {
        stack.push(new Point(p[0].x, p[0].y));              // make candidate
        stack.push(new Point(p[1].x, p[1].y));

        for (int idx = 2; idx < N; idx++) {
            Point next = new Point(p[idx].x, p[idx].y);

            while (stack.size() >= 2) {                     // find next
                Point second = stack.pop();
                Point first = stack.peek();

                if (CCW(first, second, next) > 0) {         // is candidate
                    stack.push(second);
                    break;
                }
            }

            stack.push(next);
        }
    }

    private static double getDistance() {
        long totalLen = 0;

        ArrayList<Point> list = new ArrayList<>();
        while(!stack.isEmpty()) list.add(stack.pop());

        int size = list.size();

        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                long dist = distancePow(list.get(i), list.get(j));      // find largest distance

                if(totalLen < dist) totalLen = dist;
            }
        }

        return Math.sqrt(totalLen);
    }
}
