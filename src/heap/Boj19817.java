package heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19817번: Company merging
 *
 * @see https://www.acmicpc.net/problem/19817
 *
 */
public class Boj19817 {

    private static Queue<Company> pq;

    private static class Company {
        long size;
        long max;

        public Company(long size, long max) {
            this.size = size;
            this.max = max;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(Comparator
                .comparingLong((Company c) -> -c.max)
                .thenComparingLong(c -> c.size)
        );

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());

            int loop = m;
            long max = 0;
            while(loop-- > 0) {
                max = Math.max(max, Long.parseLong(st.nextToken()));
            }

            pq.offer(new Company(m, max));
        }

        System.out.println(merging());
    }

    /**
     *
     * Merging
     *
     * line 69: sorting max value desc, size value asc
     *
     * @return
     */
    private static long merging() {
        long cost = 0;
        long value = pq.poll().max;

        while(!pq.isEmpty()) {
            Company current = pq.poll();
            cost += (value - current.max) * current.size;
        }

        return cost;
    }
}
