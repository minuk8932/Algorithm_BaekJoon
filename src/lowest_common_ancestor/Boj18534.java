package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18534번: Rooted subtrees
 *
 * @see https://www.acmicpc.net/problem/18534
 *
 */
public class Boj18534 {
    private static int[][] parent;
    private static int[] depth;
    private static long[] cost;
    private static int N;
    private static ArrayList<Integer>[] tree;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        init();

        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        recursion(0, 0);
        connecting();

        StringBuilder sb = new StringBuilder();

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int p = Integer.parseInt(st.nextToken()) - 1;

            long total = cost[r] + cost[p] - cost[lca(r, p)] * 2 + 1;       // lca cost, not edge but vertex
            sb.append(total * (total - 1) / 2 + N).append(NEW_LINE);        // calculate all cases
        }

        System.out.println(sb.toString());
    }

    private static void recursion(int current, int deep) {
        depth[current] = deep;

        for(int next: tree[current]){
            if(depth[next] != -1) continue;
            parent[next][0] = current;
            cost[next] = cost[current] + 1;

            recursion(next, deep + 1);
        }
    }

    private static void init() {
        parent = new int[N][21];
        tree = new ArrayList[N];
        depth = new int[N];
        cost = new long[N];

        for(int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
            depth[i] = -1;
        }
    }

    private static void connecting(){
        for(int p = 1; p < 21; p++){
            for(int cur = 0; cur < N; cur++){
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static int lca(int x, int y){
        if(depth[x] > depth[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(depth[y] - depth[x] >= jump) y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--){
            if(parent[x][i] == parent[y][i]) continue; 
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }
}
