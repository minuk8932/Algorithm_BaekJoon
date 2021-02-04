package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author exponential-e
 * 백준 19580번: 마스크가 필요해
 *
 * @see https://www.acmicpc.net/problem/19580
 *
 */
public class Boj19580 {
    private static PriorityQueue<Range> citizen = new PriorityQueue<>();
    private static TreeMap<Long, Integer> store;

    private static class Range implements Comparable<Range>{
        long from;
        long to;

        public Range(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Range r) {
            if (this.to < r.to) return -1;
            else if(this.to > r.to) return 1;
            else {
                if(this.from > r.from) return -1;
                else if(this.from < r.from) return 1;
                else return 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            citizen.offer(new Range(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
        }

        store = new TreeMap<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            long key = Long.parseLong(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            store.merge(key, value, Integer::sum);
        }

        System.out.println(buying());
    }

    /**
     *
     * Optimized buying process
     *
     * line 77: lower bound
     * line 85 ~ : possible
     *
     * @return
     */
    private static int buying() {
        int result = 0;

        while(!citizen.isEmpty() && !store.isEmpty()) {
            Range current = citizen.poll();
            Map.Entry<Long, Integer> entry = store.ceilingEntry(current.from);
            if(entry == null) continue;

            long cost = entry.getKey();
            int quantity = entry.getValue();

            if(cost > current.to) continue;
            result++;
            quantity--;

            if(quantity == 0) store.remove(cost);
            else store.put(cost, quantity);
        }

        return result;
    }
}
