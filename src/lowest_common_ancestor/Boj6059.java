package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 6059번: Pasture Walking
 *
 * @see https://www.acmicpc.net/problem/6059
 *
 */
public class Boj6059 {
    private static int[][] parent;
    private static ArrayList<Node>[] tree;
    private static int[] depth;
    private static int[] cost;
    private static boolean[] visit;

    private static int N;
    private static final String NEW_LINE = "\n";

    private static class Node{
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N][11];
        tree = new ArrayList[N];
        depth = new int[N];
        visit = new boolean[N];
        cost = new int[N];

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            tree[node1].add(new Node(node2, cost));
            tree[node2].add(new Node(node1, cost));
        }

        recursion(0, 0);
        connection();

        StringBuilder sb = new StringBuilder();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int lca = LCA(x, y);
            sb.append(cost[x] + cost[y] - cost[lca] * 2).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void recursion(int current, int deep) {
        visit[current] = true;
        depth[current] = deep;

        for(Node next: tree[current]) {
            if(visit[next.node]) continue;

            parent[next.node][0] = current;
            cost[next.node] = cost[current] + next.cost;            // make prefix cost
            recursion(next.node, deep + 1);
        }
    }

    private static void connection() {
        for(int p = 1; p < 11; p++){
            for(int current = 0; current < N; current++){
                parent[current][p] = parent[parent[current][p - 1]][p - 1];
            }
        }
    }

    private static int LCA(int x, int y) {
        if(depth[x] > depth[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 10; i >= 0; i--){
            int jump = 1 << i;
            if(depth[y] - depth[x] >= jump) y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 10; i >= 0; i--){
            if(parent[x][i] != parent[y][i]){
                x = parent[x][i];
                y = parent[y][i];
            }
        }

        return parent[x][0];
    }
}
