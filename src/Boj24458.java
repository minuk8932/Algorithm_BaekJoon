import common.Coordinate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj24458 {
    private static Deque<Coordinate> stack = new ArrayDeque<>();
    private static List<Coordinate> prison = new ArrayList<>();

    private static final int INF = 1_000_000_000;
    private static Coordinate min = new Coordinate.Builder(INF, INF).build();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            minProcessing(x, y);
            prison.add(new Coordinate.Builder(x, y).build());
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            minProcessing(x, y);
            prison.add(new Coordinate.Builder(x, y).build());
        }

        sorting();
        convexHull(N + M);

        System.out.println();
    }

    private static void convexHull(int N) {
        stack.push(prison.get(0));
        stack.push(prison.get(1));

        for (int idx = 2; idx < N; idx++) {
            Coordinate next = prison.get(idx);
            int count = 0;

            while (stack.size() >= 2) {
                Coordinate second = stack.pop();
                Coordinate first = stack.peek();

                if (CCW(first, second, next) <= 0){
                    count++;
                    continue;
                }

                stack.push(second);
                break;
            }

            System.out.println(count);
            stack.push(next);
        }
    }

    private static void sorting() {
        Collections.sort(prison, (p1, p2) -> {
            long c = CCW(min, p1, p2);

            if (c > 0) return -1;
            else if (c < 0) return 1;
            else if (c == 0) {
                long a = distancePow(min, p1);
                long b = distancePow(min, p2);

                if (a < b) return -1;
                else if (a > b) return 1;
                else if (a == b) return 0;
            }

            return 0;
        });
    }

    private static void minProcessing(int x, int y) {
        if (y < min.getY()) {
            min = new Coordinate.Builder(x, y).build();
        }
        else if (y == min.getY()) {
            if (x < min.getX()) {
                min = new Coordinate.Builder(x, y).build();
            }
        }
    }

    private static long CCW(Coordinate v1, Coordinate v2, Coordinate v3) {
        return (v1.getX() * v2.getY() + v2.getX() * v3.getY() + v3.getX() * v1.getY()) -
                (v3.getX() * v2.getY() + v2.getX() * v1.getY() + v1.getX() * v3.getY());
    }

    private static long distancePow(Coordinate c1, Coordinate c2) {
        return (c2.getX() - c1.getX()) * (c2.getX() - c1.getX()) + (c2.getY() - c1.getY()) * (c2.getY() - c1.getY());
    }
}
