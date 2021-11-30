package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 23740번: 버스 노선 개편하기
 *
 * @see https://www.acmicpc.net/contest/problem/23740
 *
 */
public class Boj23740 {

    private static Queue<Route> pq;

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static class Route {
        private int start;
        private int end;
        private int cost;

        public Route(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public int getCost() {
            return this.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(
                Comparator.comparingInt(Route::getStart)
                .thenComparingInt(Route::getEnd)
        );
        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            pq.offer(new Route(S, E, C));
        }

        System.out.println(merging());
    }

    private static String merging() {
        List<Route> result = new ArrayList<>();

        Route current = pq.poll();
        while(!pq.isEmpty()) {
            Route next = pq.poll();

            if(next.start > current.end) {
                result.add(current);
                current = next;
            }
            else {
                current.end = Math.max(next.end, current.end);
                current.cost = Math.min(current.cost, next.cost);
            }
        }

        result.add(current);

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append(NEW_LINE);

        for (Route r: result) {
            sb.append(r.start).append(SPACE).append(r.end).append(SPACE).append(r.cost).append(NEW_LINE);
        }

        return sb.toString();
    }
}
