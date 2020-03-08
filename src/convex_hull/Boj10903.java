package convex_hull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 * 	@author exponential-e
 *	백준 10903번: Wall construction
 *
 *	@see https://www.acmicpc.net/problem/10903/
 *
 */
public class Boj10903 {
    private static ArrayDeque<Point> stack = new ArrayDeque<>();
    private static final int INF = 1_000_000_000;
    private static final double PI = Math.PI;

    private static Point min = new Point(INF, INF);

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Point[] wall = new Point[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (y < min.y) {					// get target
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

        System.out.println(getDistance(L));
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
        stack.push(new Point(p[0].x, p[0].y));
        stack.push(new Point(p[1].x, p[1].y));

        for (int idx = 2; idx < N; idx++) {
            Point next = new Point(p[idx].x, p[idx].y);

            while (stack.size() >= 2) {
                Point second = stack.pop();
                Point first = stack.peek();

                if (CCW(first, second, next) > 0) {
                    stack.push(second);
                    break;
                }
            }

            stack.push(next);
        }
    }

    private static double getDistance(int limit) {
        int size = stack.size();
        double totalLen = (PI * size - (PI * (size - 2))) * limit;			// to find the circumference of a circle

        Point prev = stack.pop();
        Point start = prev;

        while(!stack.isEmpty()) {
            Point current = stack.pop();
            totalLen += Math.sqrt(distancePow(prev, current));				// spare length

            prev = current;
        }

        totalLen += Math.sqrt(distancePow(start, prev));
        return totalLen;
    }
}
