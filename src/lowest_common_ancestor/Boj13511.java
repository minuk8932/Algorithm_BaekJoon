package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13511번: 트리와 쿼리2
 *
 * @see https://www.acmicpc.net/problem/
 *
 */
public class Boj13511 {
    private static ArrayList<Node>[] tree;
    private static long[] cost;
    private static int[][] parent;
    private static int[] deep;
    private static boolean[] visit;

    private static int N;
    private static final String NEW_LINE = "\n";

    private static class Node{
        int node;
        long cost;

        public Node(int node, long cost){
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N];
        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }

        parent = new int[N][21];
        deep = new int[N];
        visit = new boolean[N];
        cost = new long[N];

        int loop = N - 1;
        while(loop-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            tree[node1].add(new Node(node2, cost));
            tree[node2].add(new Node(node1, cost));
        }

        dfs(0, 0);
        connecting();

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        while(Q-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int lca = LCA(node1, node2);

            if(cmd == 1){
                sb.append(cost[node1] + cost[node2] - cost[lca] * 2).append(NEW_LINE);
            }
            else{
                int num = Integer.parseInt(st.nextToken()) - 1;
                sb.append(LCA(node1, node2, lca, num) + 1).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int current, int depth){
        visit[current] = true;
        deep[current] = depth;

        for(Node next: tree[current]){
            if(visit[next.node]) continue;

            parent[next.node][0] = current;
            cost[next.node] = cost[current] + next.cost;            // make prefix sum

            dfs(next.node, depth + 1);
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
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump) y = parent[y][i];     // match level
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--){
            if(parent[x][i] == parent[y][i]) continue;          // find parent up to one level if not same
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }

    private static int LCA(int x, int y, int a, int k){
        int res = 0;

        if(deep[x] - deep[a] >= k) res = finder(x, k);                  // target is x's line
        else res = finder(y, deep[x] + deep[y] - deep[a] * 2 - k);  //  target is y's line

        return res;
    }

    private static int finder(int cur, int k){
        int t = k;

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(jump > t) continue;

            cur = parent[cur][i];               // find parent x or y
            t -= jump;
        }

        return cur;
    }
}
