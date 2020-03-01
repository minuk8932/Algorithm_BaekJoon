package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 8012번: 한동이는 영업 사원
 *
 * @see https://www.acmicpc.net/problem/8012/
 *
 */
public class Boj8012 {
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] deep;
    private static long[] cost;
    private static boolean[] visit;

    private static int n;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n];
        for(int i = 0; i < n; i++){
            tree[i] = new ArrayList<>();
        }

        parent = new int[n][21];
        deep = new int[n];
        cost = new long[n];
        visit = new boolean[n];

        int loop = n - 1;
        while(loop-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        dfs(0, 0);
        connecting();

        int m = Integer.parseInt(br.readLine());
        long result = 0;
        int prev = 0;

        while(m-- > 0){
            int post = Integer.parseInt(br.readLine()) - 1;
            result += cost[prev] + cost[post] - cost[LCA(prev, post)] * 2;

            prev = post;
        }

        System.out.println(result);
    }

    private static void dfs(int current, int depth){
        visit[current] = true;
        deep[current] = depth;

        for(int next: tree[current]){
            if(visit[next]) continue;

            parent[next][0] = current;
            cost[next] = cost[current] + 1;         // make prefix sum
            dfs(next, depth + 1);
        }
    }

    private static void connecting(){
        for(int p = 1; p < 21; p++){
            for(int cur = 0; cur < n; cur++){
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static int LCA(int x, int y){
        if(deep[x] > deep[y]){
            int tmp = x;
            x = y;
            y = tmp;
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
