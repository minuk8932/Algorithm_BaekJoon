package Floyd_Warshall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 23286번: 허들 넘기
 *
 * @see https://www.acmicpc.net/problem/23286
 *
 */
public class Boj23286 {

    private static int[][] path;

    private static final String NEW_LINE = "\n";
    private static final int INF = 1_000_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        init(N);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken());

            path[u][v] = h;
        }

        floydWashall(N);

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            sb.append(path[s][e] == INF ? -1: path[s][e]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void floydWashall(int n) {
        for(int v = 0; v < n; v++) {
            for(int s = 0; s < n; s++) {
                for(int e = 0; e < n; e++) {
                    if(path[s][e] <= Math.max(path[s][v], path[v][e])) continue;
                    path[s][e] = Math.max(path[s][v], path[v][e]);
                }
            }
        }
    }

    private static void init(int n) {
        path = new int[n][n];

        for(int i = 0; i < n; i++) {
            Arrays.fill(path[i], INF);
        }
    }
}
