package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1153번: 네 개의 소수
 *
 * @see https://www.acmicpc.net/problem/1153
 *
 */
public class Boj1153 {

    private static final int INF = 1_000_001;

    private static List<Integer> primes = new ArrayList<>();
    private static boolean[] prime = new boolean[INF];
    private static boolean[] values = new boolean[INF];
    private static int size;

    private static final String SPACE = " ";
    private static final String NONE = "-1";

    private static class Components {
        int a;
        int b;
        int c;
        int d;

        public Components(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public int getSum() {
            return primes.get(a) + primes.get(b) + primes.get(c) + primes.get(d);
        }

        @Override
        public String toString() {
            int[] arr = {primes.get(a), primes.get(b), primes.get(c), primes.get(d)};
            Arrays.sort(arr);

            StringBuilder builder = new StringBuilder();
            for (int a: arr) {
                builder.append(a).append(SPACE);
            }

            return builder.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        eratosthenesSieve();
        Components result = bfs(N);

        System.out.println(result == null ? NONE: result.toString());
    }

    /**
     *
     * Searching all cases
     *
     * @param target
     * @return
     */
    private static Components bfs(int target) {
        Queue<Components> q = new ArrayDeque<>();

        Components start = new Components(0, 0, 0 ,0);
        if(target == 8) return start;

        q.offer(start);
        values[start.getSum()] = true;

        while(!q.isEmpty()) {
            Components current = q.poll();

            Components[] nexts = {new Components(current.a, current.b, current.c, current.d),
                    new Components(current.a, current.b, current.c, current.d),
                    new Components(current.a, current.b, current.c, current.d),
                    new Components(current.a, current.b, current.c, current.d)};

            nexts[0].a++;
            nexts[1].b++;
            nexts[2].c++;
            nexts[3].d++;

            for(Components next: nexts) {
                if(outOfSize(next)) continue;

                int nextSums = next.getSum();
                if(nextSums > target) continue;

                if(values[nextSums]) continue;
                values[nextSums] = true;

                if(nextSums == target) return next;
                q.offer(next);
            }
        }

        return null;
    }

    private static boolean outOfSize(Components next) {
        return size <= next.a || size <= next.b || size <= next.c || size <= next.d;
    }

    /**
     *
     * Eratos Thenes sieve
     *
     */
    private static void eratosthenesSieve() {
        Arrays.fill(prime, true);

        prime[0] = prime[1] = false;

        for(int i = 2; i * i < INF; i++) {
            if(!prime[i]) continue;

            for(int j = i + i; j < INF; j += i) {
                prime[j] = false;
            }
        }

        for(int i = 2; i < INF; i++) {
            if(!prime[i]) continue;
            primes.add(i);
        }

        size = primes.size();
    }
}
