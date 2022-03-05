package dynamic_programming;

import common.Road;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1446번: 지름길
 *
 * @see https://www.acmicpc.net/problem/1446
 *
 */
public class Boj1446 {

    private static Road<Integer, Integer>[] roads;
    private static int[] dp;

    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        dp = new int[D + 1];
        Arrays.fill(dp, -1);

        roads = new Road[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            roads[i] = new Road.Builder(s, e)
                    .cost(l)
                    .build();
        }

        Arrays.sort(roads, Comparator
                .comparingInt(Road<Integer, Integer>::getStart)
                .thenComparingInt(Road::getEnd)
                .thenComparingInt(Road::getCost));

        System.out.println(recursion(D, 0));
    }

    /**
     *
     * Recursion
     *
     * line 69: get default path from current to destination
     * line 75: find short path
     *
     * @param distance
     * @param current
     * @return
     */
    private static int recursion(int distance, int current) {
        if(current > distance) return INF;
        if(current == distance) return 0;

        if(dp[current] != -1) return dp[current];
        int answer = distance - current;

        for(Road<Integer, Integer> road: roads) {
            if(current > road.getStart()) continue;

            int difference = road.getStart() - current;
            answer = Math.min(answer, recursion(distance, road.getEnd()) + road.getCost() + difference);
        }

        return dp[current] = answer;
    }
}
