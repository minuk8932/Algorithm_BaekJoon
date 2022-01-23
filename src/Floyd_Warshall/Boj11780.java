package Floyd_Warshall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11780번: 플로이드 2
 *
 * @see https://www.acmicpc.net/problem/11780
 *
 */
public class Boj11780 {

    private static List<Integer>[][] path;
    private static StringBuilder builder = new StringBuilder();
    private static int n;
    private static int[][] cost;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final int INF = 2_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        cost = new int[n][n];
        path = new ArrayList[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;

            for(int j = 0; j < n; j++) {
                path[i][j] = new ArrayList<>();
            }
        }

        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int city1 = Integer.parseInt(st.nextToken()) - 1;
            int city2 = Integer.parseInt(st.nextToken()) - 1;
            int bus = Integer.parseInt(st.nextToken());

            cost[city1][city2] = Math.min(bus, cost[city1][city2]);
        }

        floydWarshall();
        printMap();
        printPath();

        System.out.print(builder.toString());
    }

    private static void printPath() {
        for(int from = 0; from < n; from++) {
            for(int to = 0; to < n; to++) {
                if(from == to || cost[from][to] == INF) {
                    builder.append(0).append(NEW_LINE);
                    continue;
                }

                builder.append(path[from][to].size() + 2).append(SPACE);
                builder.append(from + 1).append(SPACE);

                for(int p: path[from][to]) {
                    builder.append(p + 1).append(SPACE);
                }

                builder.append(to + 1).append(NEW_LINE);
            }
        }
    }

    private static void printMap() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                builder.append(cost[i][j] == INF ? 0: cost[i][j]).append(SPACE);
            }

            builder.append(NEW_LINE);
        }
    }

    private static void floydWarshall() {
        for(int v = 0; v < n; v++) {
            for(int s = 0; s < n; s++) {
                if(v == s) continue;

                for(int e = 0; e < n; e++) {
                    if(s == e || v == e) continue;
                    if(cost[s][v] == INF || cost[v][e] == INF) continue;

                    if(cost[s][e] <= cost[s][v] + cost[v][e]) continue;
                    cost[s][e] = cost[s][v] + cost[v][e];

                    path[s][e] = new ArrayList<>();
                    for(int city : path[s][v]){
                        path[s][e].add(city);
                    }

                    path[s][e].add(v);
                    for(int city : path[v][e]){
                        path[s][e].add(city);
                    }
                }
            }
        }
    }
}
