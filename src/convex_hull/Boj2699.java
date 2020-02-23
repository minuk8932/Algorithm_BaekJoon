package convex_hull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 2699번: 격자점 컨벡스헐
 *
 * @see https://www.acmicpc.net/problem/2699/
 *
 */
public class Boj2699 {
    private static ArrayDeque<Point> stack;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final int INF = 1_000_000_000;

    private static Point target;
    private static Point starter;

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
                long c = CCW(target, p1, p2);

                if (c > 0) {
                    return -1;
                }

                else if (c < 0) {
                    return 01;
                }

                else  {
                    long a = distancePow(target, p1);
                    long b = distancePow(target, p2);

                    if (a < b) return -1;
                    else if (a > b) return 1;
                    else return 0;
                }
            }
        };
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        StringBuilder sb = new StringBuilder();
        int T = in.readInt();

        while(T-- > 0){
            int N = in.readInt();

            starter = new Point(INF, -INF);
            target = new Point(INF, INF);

            Point[] coor = new Point[N];
            for(int i = 0; i < N; i++){
                int x = in.readInt();
                int y = in.readInt();

                coor[i] = new Point(x, y);

                if (y < target.y) {
                    target = new Point(x, y);
                }

                else if (y == target.y) {
                    if (x < target.x) {
                        target = new Point(x, y);
                    }
                }

                if (y > starter.y) {                    // find starter
                    starter = new Point(x, y);
                }

                else if (y == starter.y) {
                    if (x < starter.x) {
                        starter = new Point(x, y);
                    }
                }
            }

            Arrays.sort(coor, Point.comparator);
            convexHull(N, coor);

            boolean flag = false;
            Queue<Point> q = new LinkedList<>();

            sb.append(stack.size()).append(NEW_LINE);
            while(!stack.isEmpty()){            // get convexhull except before starter
                Point current = stack.pop();

                if(current.x == starter.x && current.y == starter.y) flag = true;
                if(flag) sb.append(current.x).append(SPACE).append(current.y).append(NEW_LINE);
                else q.offer(current);
            }

            while(!q.isEmpty()){                // get remained
                Point current = q.poll();
                sb.append(current.x).append(SPACE).append(current.y).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void convexHull(int N, Point[] p) {
        stack = new ArrayDeque<>();

        stack.push(new Point(p[0].x, p[0].y));
        stack.push(new Point(p[1].x, p[1].y));

        for (int idx = 2; idx < N; idx++) {
            Point next = new Point(p[idx].x, p[idx].y);

            while (stack.size() >= 2) {
                Point second = stack.pop();
                Point first = stack.peek();

                if (CCW(first, second, next) > 0) {             // is convexhull?
                    stack.push(second);
                    break;
                }
            }

            stack.push(next);
        }
    }

    private static long CCW(Point v1, Point v2, Point v3) {
        return (v1.x * v2.y + v2.x * v3.y + v3.x * v1.y) - (v3.x * v2.y + v2.x * v1.y + v1.x * v3.y);
    }

    private static long distancePow(Point p1, Point p2) {
        return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    }

    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}
