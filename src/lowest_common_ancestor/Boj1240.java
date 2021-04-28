package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1240번: 노드사이의 거리
 *
 * @see https://www.acmicpc.net/problem/1240
 *
 */
public class Boj1240 {

    private static List<Node>[] path;
    private static int[][] parent;
    private static int[] cost;
    private static int[] depth;

    private static int N;
    private static final String NEW_LINE = "\n";

    private static class Node {
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

        init();

        int loop = N - 1;
        while(loop-- > 0 ){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken());

            path[v1].add(new Node(v2, e));
            path[v2].add(new Node(v1, e));
        }

        dfs(0, 0);
        connection();

        StringBuilder sb = new StringBuilder();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            sb.append(cost[from] + cost[to] - cost[LCA(from, to)] * 2).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * LCA
     *
     * @param x
     * @param y
     * @return
     */
    private static int LCA(int x, int y) {
        if(depth[x] > depth[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 10; i >= 0; i--) {
            int jump = 1 << i;
            if(jump > depth[y] - depth[x]) continue;
            y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 10; i >= 0; i--) {
            if(parent[x][i] == parent[y][i]) continue;
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }

    private static void connection() {
        for(int p = 1; p < 11; p++) {
            for(int current = 0; current < N; current++) {
                parent[current][p] = parent[parent[current][p - 1]][p - 1];
            }
        }
    }

    private static void dfs(int current, int deep) {
        depth[current] = deep;

        for(Node next: path[current]) {
            if(depth[next.node] != -1) continue;
            parent[next.node][0] = current;
            cost[next.node] = cost[current] + next.cost;

            dfs(next.node, deep + 1);
        }
    }

    private static void init() {
        parent = new int[N][11];
        cost = new int[N];
        path = new ArrayList[N];
        depth = new int[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
            depth[i] = -1;
        }
    }
}
