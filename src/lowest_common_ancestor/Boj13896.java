package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13869번: Sky tax
 *
 * @see https://www.acmicpc.net/problem/13869
 *
 */
public class Boj13896 {

    private static final String NEW_LINE = "\n";
    private static final String CASE = "Case #";
    private static final String COLON = ":\n";
    private static int[][] parent;
    private static int[] depth;
    private static int[] cost;
    private static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int test = 1; test <= T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken()) - 1;

            init(N);
            int loop = N - 1;
            while(loop-- > 0) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken()) - 1;
                int B = Integer.parseInt(st.nextToken()) - 1;

                graph.get(A).add(B);
                graph.get(B).add(A);
            }

            depth[R] = 0;
            recursion(R);
            connecting(N);

            sb.append(CASE).append(test).append(COLON);

            while(Q-- > 0) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int U = Integer.parseInt(st.nextToken()) - 1;

                if(S == 0) {        // change capital
                    R = U;
                }
                else {
                    int answer;

                    int lca = LCA(R, U);

                    if (R == U) answer = N;
                    else if (lca == U) answer = N - cost[pathCost(R, depth[R] - depth[lca] - 1)];
                    else answer = cost[U];

                    sb.append(answer).append(NEW_LINE);
                }
            }
        }

        System.out.println(sb);
    }

    private static int pathCost(int node, int interval){

        for (int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if (interval < jump) continue;

            node = parent[node][i];
            interval -= jump;
        }

        return node;
    }

    private static int LCA(int x, int y) {
        if(depth[x] > depth[y]) {
            int swap = x;
            x = y;
            y = swap;
        }

        for(int p = 20; p >= 0; p--) {
            int jump = 1 << p;
            if(depth[y] - depth[x] >= jump){
                y = parent[y][p];
            }
        }

        if(x == y) return x;

        for(int p = 20; p >= 0; p--) {
            if(parent[x][p] == parent[y][p]) continue;
            x = parent[x][p];
            y = parent[y][p];
        }

        return parent[x][0];
    }

    private static void connecting(int n) {
        for(int p = 1; p < 21; p++){
            for(int cur = 0; cur < n; cur++){
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static int recursion(int current) {
        int count = 1;

        for(int next: graph.get(current)){
            if(depth[next] != -1) continue;
            depth[next] = depth[current] + 1;

            parent[next][0] = current;
            count += recursion(next);
        }

        return cost[current] = count;
    }

    private static void init(int n) {
        parent = new int[n][21];
        depth = new int[n];
        cost = new int[n];
        graph = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            graph.add(i, new ArrayList<>());
            depth[i] = -1;
            cost[i] = 1;
        }
    }
}
