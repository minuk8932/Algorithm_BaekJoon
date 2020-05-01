package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16168번: 퍼레이드
 *
 * @see https://www.acmicpc.net/problem/16168/
 *
 */
public class Boj16168 {
    private static ArrayList<Integer>[] graph;

    private static int[][] visit;
    private static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V];
        for(int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        int loop = E;

        while (loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        System.out.println(parade(V, E));
    }

    private static String parade (int v, int e) {
        visit = new int[v][v];

        for(int i = 0; i < v; i++) {
            dfs(i, i, e);
            if(flag) return "YES";
        }

        return "NO";
    }

    private static void dfs(int current, int start, int count) {
        if (count == 0) {                   // if arrived when pass m edges
            flag = true;
            return;
        }

        for(int next: graph[current]) {
            if(visit[current][next] == start) continue;
            visit[current][next] = visit[next][current] = start;

            dfs(next, start, count - 1);
        }
    }
}
