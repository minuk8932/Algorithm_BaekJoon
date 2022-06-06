import common.Coordinate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj24458 {
    private static final long INF = 2_000_000_000;
    private static final long SHIFT = 200_000_000L;
    private static final long CIPHER = 1_000_000_000L;
    private static Deque<Coordinate<Long, Long>> stack = new ArrayDeque<>();
    private static List<Coordinate<Long, Long>> prison = new ArrayList<>();
    private static Set<Long> jailer = new HashSet<>();
    private static Set<Long> prisoner = new HashSet<>();
    private static int jailers;
    private static Coordinate<Long, Long> min = new Coordinate.Builder(INF, INF).build();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            minProcessing(x, y);
            prison.add(new Coordinate.Builder(x, y).build());

            long index = indexing(x, y);
            prisoner.add(index);
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            minProcessing(x, y);
            prison.add(new Coordinate.Builder(x, y).build());

            long index = indexing(x, y);
            if(jailer.contains(index)) continue;
            jailer.add(index);
        }

        sorting();
        convexHull(N + M);

        rearranging();
        System.out.println(supervising());
    }

    private static long indexing(long x, long y) {
        return CIPHER * (SHIFT + x) + (SHIFT + y);
    }

    private static int supervising() {
        int count = 0;
        int size = 0;
        int p = prisoner.size();

        while(!stack.isEmpty()) {
            Coordinate<Long, Long> current = stack.poll();
            long index = indexing(current.getX(), current.getY());

            if(jailer.contains(index)){
                jailers++;
                count += (size == 0 ? 0: size - 1);
                size = 0;
                continue;
            }

            size++;
        }

        if(p == size - jailers) return p;
        return count + (size == 0 ? 0: size - 1);
    }

    private static void rearranging() {
        int size = stack.size();
        System.out.println(size);

        while(size-- > 0) {
            Coordinate<Long, Long> current = stack.poll();
            long index = indexing(current.getX(), current.getY());

            if(!jailer.contains(index)) {
                stack.offer(current);
                continue;
            }

            jailers++;
            break;
        }
    }

    private static void convexHull(int N) {
        stack.push(prison.get(0));
        stack.push(prison.get(1));

        for (int idx = 2; idx < N; idx++) {
            Coordinate<Long, Long> next = prison.get(idx);

            while (stack.size() >= 2) {
                Coordinate<Long, Long> second = stack.pop();
                Coordinate<Long, Long> first = stack.peek();

                if (CCW(first, second, next) < 0) continue;
                stack.push(second);
                break;
            }

            stack.push(next);
        }
    }

    private static void sorting() {
        Collections.sort(prison, (c1, c2) -> {
            long c = CCW(min, c1, c2);

            if (c > 0) return -1;
            else if (c < 0) return 1;
            else if (c == 0) {
                long a = distancePow(min, c1);
                long b = distancePow(min, c2);

                if (a < b) return -1;
                else if (a > b) return 1;
                else if (a == b) return 0;
            }

            return 0;
        });
    }

    private static void minProcessing(long x, long y) {
        if (y < min.getY()) {
            min = new Coordinate.Builder(x, y).build();
        }
        else if (y == min.getY()) {
            if (x < min.getX()) {
                min = new Coordinate.Builder(x, y).build();
            }
        }
    }

    private static long CCW(Coordinate<Long, Long> v1, Coordinate<Long, Long> v2, Coordinate<Long, Long> v3) {
        return (v1.getX() * v2.getY() + v2.getX() * v3.getY() + v3.getX() * v1.getY()) -
                (v3.getX() * v2.getY() + v2.getX() * v1.getY() + v1.getX() * v3.getY());
    }

    private static long distancePow(Coordinate<Long, Long> c1, Coordinate<Long, Long> c2) {
        return (c2.getX() - c1.getX()) * (c2.getX() - c1.getX()) +
                (c2.getY() - c1.getY()) * (c2.getY() - c1.getY());
    }
}
