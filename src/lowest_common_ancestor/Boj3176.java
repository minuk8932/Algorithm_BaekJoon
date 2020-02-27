package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3176번: 도로 네트워크
 *
 * @see https://www.acmicpc.net/problem/3176/
 *
 */
public class Boj3176 {
    private static ArrayList<Node>[] tree;
    private static int[][] parent;
    private static int[] deep;
    private static int[][] min;
    private static int[][] max;
    private static boolean[] visit;

    private static int N;
    private static final int INF = 10_000_000;
    private static int[] result;
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

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
        parent = new int[N][21];
        deep = new int[N];
        min = new int[N][21];
        max = new int[N][21];
        visit = new boolean[N];

        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
            Arrays.fill(min[i], INF);
        }

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
        int K = Integer.parseInt(br.readLine());

        while(K-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            result = new int[2];
            result[1] = INF;
            lca(node1, node2);

            sb.append(result[1]).append(SPACE).append(result[0]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int current, int depth){
        visit[current] = true;
        deep[current] = depth;

        for(Node next: tree[current]){
            if(visit[next.node]) continue;

            parent[next.node][0] = current;
            max[next.node][0] = next.cost;      // set value
            min[next.node][0] = next.cost;
            dfs(next.node, depth + 1);
        }
    }

    private static void connecting(){
        for(int p = 1; p < 21; p++){
            for(int cur = 0; cur < N; cur++){
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
                max[cur][p] = Math.max(max[cur][p - 1], max[parent[cur][p - 1]][p - 1]);        // make path with value
                min[cur][p] = Math.min(min[cur][p - 1], min[parent[cur][p - 1]][p - 1]);
            }
        }
    }

    private static void lca(int x, int y){
        if(deep[x] > deep[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump){
                result[0] = Math.max(result[0], max[y][i]);             // get min max
                result[1] = Math.min(result[1], min[y][i]);
                y = parent[y][i];
            }
        }

        if(x == y) return;

        for(int i = 20; i >= 0; i--){
            if(parent[x][i] == parent[y][i]) continue;
            result[0] = Math.max(max[x][i], Math.max(result[0], max[y][i]));
            result[1] = Math.min(min[x][i], Math.min(result[1], min[y][i]));
            x = parent[x][i];
            y = parent[y][i];
        }

        result[0] = Math.max(max[x][0], Math.max(result[0], max[y][0]));
        result[1] = Math.min(min[x][0], Math.min(result[1], min[y][0]));
    }
}
