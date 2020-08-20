package prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 19584번: 난개발
 *
 * @see https://www.acmicpc.net/problem/19584
 *
 */
public class Boj19584 {
    private static PriorityQueue<Coordinate> primary = new PriorityQueue<>();
    private static int[] range;
    private static long[] cost;

    private static int size;

    private static class Coordinate implements Comparable<Coordinate>{
        int index;
        int height;

        public Coordinate(int index, int height) {
            this.index = index;
            this.height = height;
        }

        @Override
        public int compareTo(Coordinate c) {
            return this.height < c.height ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        range = new int[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            primary.offer(new Coordinate(i, Integer.parseInt(st.nextToken())));
        }

        levelArrangement(N);
        cost = new long[size + 2];

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            makeRailRoad(range[u], range[v], c);                          // cost check and save
        }

        System.out.println(getMax());
    }

    private static void levelArrangement(int n) {
        int h = Integer.MIN_VALUE;
        size = -1;

        while(!primary.isEmpty()) {                       // find same level & compression
            Coordinate current = primary.poll();

            if(current.height != h){
                h = current.height;
                size++;
            }

            range[current.index] = size;
        }
    }

    private static void makeRailRoad(int u, int v, long c) {
        cost[Math.min(u, v)] += c;                      // current
        cost[Math.max(u, v) + 1] -= c;                  // next
    }

    private static long getMax() {
        long result = cost[0];

        for(int i = 1; i < cost.length; i++){
            cost[i] += cost[i - 1];                     // make accumulation by prefix sum
            result = Math.max(result, cost[i]);
        }

        return result;
    }
}
