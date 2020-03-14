package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15480번: LCA와 쿼리
 *
 * @see https://www.acmicpc.net/problem/15480
 *
 */
public class Boj15480 {
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] deep;
    private static boolean[] visit;

    private static int N;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        int loop = N - 1;
        while(loop-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        dfs(0, 0);
        connecting();

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            int[] lca = {LCA(u, v), LCA(u, r), LCA(v, r)};
            int max = Math.max(deep[lca[0]], Math.max(deep[lca[1]], deep[lca[2]]));

            if(max == deep[lca[0]]) sb.append(lca[0] + 1).append(NEW_LINE);         // find lca
            else if (max == deep[lca[1]]) sb.append(lca[1] + 1).append(NEW_LINE);
            else sb.append(lca[2] + 1).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        tree = new ArrayList[N];
        parent = new int[N][21];
        deep = new int[N];
        visit = new boolean[N];

        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }
    }

    private static void dfs(int current, int depth){
        visit[current] = true;
        deep[current] = depth;

        for(int next: tree[current]){
            if(visit[next]) continue;

            parent[next][0] = current;
            dfs(next, depth + 1);
        }
    }

    private static void connecting(){
        for(int p = 1; p < 21; p++){
            for(int cur = 0; cur < N; cur++){
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static int LCA(int x, int y){
        if(deep[x] > deep[y]){
            int swap = x;
            x = y;
            y = swap;
        }

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump) y = parent[y][i];     // matching level
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--){
            if(parent[x][i] == parent[y][i]) continue;          // find lca

            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }
}
