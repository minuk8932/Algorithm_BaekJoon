package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19240번: 장난감 동맹군
 *
 * @see https://www.acmicpc.net/problem/19240
 *
 */
public class Boj19240 {
    private static ArrayList<Integer>[] path;
    private static int[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            init(N);

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;

                path[u].add(v);
                path[v].add(u);
            }

            for(int i = 0; i < N; i++) {
                if(visit[i] != -1) continue;
                visit[i] = 0;

                dfs(i);                     // graph numbering
            }

            sb.append(dividable() ? "YES\n": "NO\n");
        }

        System.out.print(sb.toString());
    }

    private static boolean dividable() {
        for(int i = 0; i < path.length; i++) {              // is bipartite graph?
            for(int adj: path[i]) {
                if(visit[i] % 2 == visit[adj] % 2) return false;
            }
        }

        return true;
    }

    private static void dfs(int current) {
        for(int next: path[current]) {
            if(visit[next] != -1) continue;
            visit[next] = visit[current] + 1;

            dfs(next);
        }
    }

    private static void init(int n) {
        visit = new int[n];
        path = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            visit[i] -= 1;
            path[i] = new ArrayList<>();
        }
    }
}
