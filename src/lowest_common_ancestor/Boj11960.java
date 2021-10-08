package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11960번: Max flow
 *
 * @see https://www.acmicpc.net/problem/11960
 *
 */
public class Boj11960 {

    private static int[] cost;
    private static int[] depth;
    private static int[][] parent;
    private static List<Integer>[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);

        int loop = N - 1;
        while (loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        dfs(0, 0);
        connection(N);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            int lca = LCA(x, y);

            cost[x]++;
            cost[y]++;
            cost[lca]--;
            cost[parent[lca][0]]--;
        }

        recursion(0, -1);
        System.out.println(Arrays.stream(cost).max().getAsInt());
    }

    /**
     *
     * Recursion
     *
     * line 75: find section sum by prefix sum. sum(x ~ y) - redundant(lca & lca's parents)
     *
     * @param current
     * @param prev
     */
    private static void recursion(int current, int prev){
        for(int next: tree[current]) {
            if(next == prev) continue;

            recursion(next, current);
            cost[current] += cost[next];
        }
    }

    private static int LCA(int x, int y) {
        if(depth[x] > depth[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--) {
            int jump = 1 << i;

            if(depth[y] - depth[x] < jump) continue;
            y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--) {
            if(parent[x][i] == parent[y][i]) continue;
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }

    private static void dfs(int current, int level) {

        depth[current] = level;

        for(int next: tree[current]) {
            if (depth[next] != -1) continue;

            parent[next][0] = current;
            dfs(next, level + 1);
        }
    }

    private static void connection(int n) {
        for(int p = 1; p < 21; p++) {
            for(int current = 0; current < n; current++) {
                parent[current][p] = parent[parent[current][p - 1]][p - 1];
            }
        }
    }

    private static void init(int n) {
        tree = new ArrayList[n];
        depth = new int[n];
        parent = new int[n][21];
        cost = new int[n];

        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            depth[i] = -1;
        }
    }
}
