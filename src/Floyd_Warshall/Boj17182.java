package Floyd_Warshall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17182번: 우주 탐사선
 *
 * @see https://www.acmicpc.net/problem/17182/
 *
 */
public class Boj17182 {
    private static int[][] planet;

    private static int INF = 10_000_000;
    private static int visit = 0, result = INF;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        planet = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                planet[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        floydWashall();                 // find the shortest path
        visit |= (1 << K);
        travel(K, 0);

        System.out.println(result);
    }

    private static void floydWashall() {
        for(int v = 0; v < N; v++) {
            for(int s = 0; s < N; s++) {
                for(int e = 0; e < N; e++) {
                    if(planet[s][e] <= planet[s][v] + planet[v][e]) continue;
                    planet[s][e] = planet[s][v] + planet[v][e];
                }
            }
        }
    }

    private static void travel(int current, int cost) {
        if(visit == (1 << N) - 1){
            result = Math.min(result, cost);        // min cost
            return;
        }

        for(int next = 0; next < N; next++) {
            int bit = 1 << next;
            if((visit & bit) != 0) continue;
            visit |= bit;

            travel(next, cost + planet[current][next]);
            visit ^= bit;                           // backTracking
        }
    }
}
