import common.Coordinate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj24458 {
    private static Deque<Coordinate> stack = new ArrayDeque<>();
    private static List<Coordinate> prison = new ArrayList<>();
    
    private static Set<Long> jailer = new HashSet<>();
    private static Set<Long> prisoner = new HashSet<>();
    private static int jailers;

    private static final int INF = 2_000_000_000;
    private static final long SHIFT = 200_000_000L;
    private static final long CIPHER = 1_000_000_000L;

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

            long index = indexing(x, y);
            prisoner.add(index);
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

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

    private static long indexing(int x, int y) {
        return CIPHER * (SHIFT + x) + (SHIFT + y);
    }

    private static int supervising() {
        int count = 0;
        int size = 0;
        int p = prisoner.size();

        while(!stack.isEmpty()) {
            Coordinate current = stack.poll();
            long index = indexing(current.getX(), current.getY());
            System.out.println(index);

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

        while(size-- > 0) {
            Coordinate current = stack.poll();
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
            Coordinate next = prison.get(idx);
            System.out.println(next.getX() + " " + next.getY());

            while (stack.size() >= 2) {
                Coordinate second = stack.pop();
                Coordinate first = stack.peek();

                if (CCW(first, second, next) < 0){
                    System.out.println("----");
                    System.out.println(CCW(first, second, next));
                    System.out.println(first.getX() + " " + first.getY());
                    System.out.println(second.getX() + " " + second.getY());
                    System.out.println(next.getX() + " " + next.getY());
                    continue;
                }

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
        return ((long) v1.getX() * v2.getY() + (long) v2.getX() * v3.getY() + (long) v3.getX() * v1.getY()) -
                ((long) v3.getX() * v2.getY() + (long) v2.getX() * v1.getY() + (long) v1.getX() * v3.getY());
    }

    private static long distancePow(Coordinate c1, Coordinate c2) {
        return (long) (c2.getX() - c1.getX()) * (long) (c2.getX() - c1.getX()) +
                (long) (c2.getY() - c1.getY()) * (long) (c2.getY() - c1.getY());
    }
}
