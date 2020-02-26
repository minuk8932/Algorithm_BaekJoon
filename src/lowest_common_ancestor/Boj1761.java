package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1761번: 정점들의 거리
 *
 * @see https://www.acmicpc.net/problem/1761/
 *
 */
public class Boj1761 {
    private static ArrayList<Node>[] tree;
    private static int[][] parent;
    private static int[] dist;
    private static int[] deep;
    private static boolean[] visit;
    private static int N;

    private static final String NEW_LINE = "\n";

    private static class Node{
        int node;
        int cost;

        public Node(int node, int cost){
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
        dist = new int[N];
        deep = new int[N];
        visit = new boolean[N];

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
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            sb.append(dist[node1] + dist[node2] - (dist[lca(node1, node2)] * 2)).append(NEW_LINE);  // find result by prefix sum
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int current, int depth){
        deep[current] = depth;
        visit[current] = true;

        for(Node next: tree[current]){
            if(visit[next.node]) continue;
            parent[next.node][0] = current;
            dist[next.node] = next.cost + dist[current];            // make prefix sum

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

    private static int lca(int x, int y){
        if(deep[x] > deep[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump){           // matching level
                y = parent[y][i];
            }
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--){
            if(parent[x][i] == parent[y][i]) continue;              // find lca
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }
}
