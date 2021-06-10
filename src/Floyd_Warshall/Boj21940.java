package Floyd_Warshall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 21940번: 가운데에서 만나기
 *
 * @see https://www.acmicpc.net/problem/21940
 *
 */
public class Boj21940 {

    private static int[][] road;

    private static final String SPACE = " ";
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        road = resize(N);
        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            road[from][to] = cost;
        }

        floydWashall(N);
        int K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        List<Integer> shelter = new ArrayList<>();
        while(K-- > 0) {
            shelter.add(Integer.parseInt(st.nextToken()));
        }

        meetInTheMiddle(N, shelter);
    }

    /**
     *
     * Meet in the middle
     *
     * line 64 ~ 73: find min distance
     * line 75 ~ 84: find place agreed upon
     *
     * @param n
     * @param shelter
     */
    private static void meetInTheMiddle(int n, List<Integer> shelter) {
        List<Integer> X = new ArrayList<>();
        int min = INF;

        for(int promise = 1; promise <= n; promise++) {
            int distance = 0;

            for (int sh: shelter) {
                distance = Math.max(road[sh][promise] + road[promise][sh], distance);
            }

            if(distance >= min) continue;
            min = distance;
        }

        for(int promise = 1; promise <= n; promise++) {
            int distance = 0;

            for (int sh: shelter) {
                distance = Math.max(road[sh][promise] + road[promise][sh], distance);
            }

            if(distance != min) continue;
            X.add(promise);
        }

        StringBuilder sb = new StringBuilder();
        for(int x: X) {
            sb.append(x).append(SPACE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Floyd-Warshall Algorithm
     *
     * @param n
     */
    private static void floydWarshall(int n) {
        for(int v = 1; v <= n; v++) {
            for(int s = 1; s <= n; s++) {
                for(int e = 1; e <= n; e++) {
                    if(road[s][e] <= road[s][v] + road[v][e]) continue;
                    road[s][e] = road[s][v] + road[v][e];
                }
            }
        }
    }

    private static int[][] resize(int n) {
        int[][] arr = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                arr[i][j] = INF;
            }
        }

        return arr;
    }
}
