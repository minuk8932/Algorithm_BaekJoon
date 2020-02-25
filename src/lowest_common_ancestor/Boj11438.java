package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11438번: LCA2
 *
 * @see https://www.acmicpc.net/problem/11438/
 *
 */
public class Boj11438 {
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] deep;
    private static boolean[] visit;
    private static int N;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N];
        parent = new int[N][21];
        deep = new int[N];
        visit = new boolean[N];

        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }

        int loop = N - 1;
        StringTokenizer st = null;

        while(loop-- > 0){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        dfs(0, 0);                // find adjacent parent & depth
        connecting();                             // find adjacent parent & connecting

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            sb.append(lca(node1, node2) + 1).append(NEW_LINE);      // Lowest Common Ancestor
        }

        System.out.println(sb.toString());
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
            for(int current = 0; current < N; current++){
                parent[current][p] = parent[parent[current][p - 1]][p - 1];
            }
        }
    }

    private static int lca(int x, int y){
        if(deep[x] > deep[y]){                          // find the deeper
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump) y = parent[y][i];     // pull by level difference
        }

        if(x == y) return x;                        // get

        for(int i = 20; i >= 0; i--){
            if(parent[x][i] != parent[y][i]){       // find ancestor by compressed path(parent)
                x = parent[x][i];
                y = parent[y][i];
            }
        }

        return parent[x][0];
    }
}
