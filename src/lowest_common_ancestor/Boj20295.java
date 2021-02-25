package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20295번: 사탕 배달
 *
 * @see https://www.acmicpc.net/problem/20295
 *
 */
public class Boj20295 {

    private static int[][] parent;
    private static int[] depth;
    private static int[] candy;
    private static ArrayList<Integer>[] path;
    private static int[][] stores;
    private static int result;

    private static final String POSSIBLE = "PLAY\n";
    private static final String IMPOSSIBLE = "CRY\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        parent = new int[N][21];
        stores = new int[N][21];
        path = new ArrayList[N];
        depth = new int[N];
        candy = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            depth[i] = -1;
            path[i] = new ArrayList<>();
            candy[i] = Integer.parseInt(st.nextToken());
            stores[i][0] |= 1 << candy[i];
            result |= 1 << candy[i];
        }

        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            path[node1].add(node2);
            path[node2].add(node1);
        }

        recursion(0, 0);
        connection();

        int M = Integer.parseInt(br.readLine());
        int start;

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int position = Integer.parseInt(st.nextToken()) - 1;
        int wish = 1 << Integer.parseInt(st.nextToken());

        sb.append((result & wish) != 0 ? POSSIBLE: IMPOSSIBLE);

        while(M-- > 1) {
            start = position;

            st = new StringTokenizer(br.readLine());
            position = Integer.parseInt(st.nextToken()) - 1;
            wish = 1 << Integer.parseInt(st.nextToken());

            result = 0;
            LCA(start, position);

            result |= (1 << candy[start]);
            result |= (1 << candy[position]);
            sb.append((result & wish) != 0 ? POSSIBLE : IMPOSSIBLE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Connection
     *
     * line 97: make paths' bits by sparse table
     *
     */
    private static void connection() {
        for(int p = 1; p < 21; p++) {
            for(int cur = 0; cur < parent.length; cur++) {
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
                stores[cur][p] |= stores[cur][p - 1] | stores[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static void recursion(int current, int deep) {
        depth[current] = deep;

        for(int next: path[current]) {
            if(depth[next] != -1) continue;

            parent[next][0] = current;
            stores[next][0] |= (1 << candy[current]);
            recursion(next, deep + 1);
        }
    }

    /**
     *
     * LCA
     *
     * line 134, line 142 ~ 143, line149 ~ 150:Check possibility of buying candy by binary lifting
     *
     * @param x
     * @param y
     */
    private static void LCA(int x, int y) {
        if(depth[x] > depth[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--) {
            int jump = 1 << i;

            if(depth[y] - depth[x] < jump) continue;
            result |= stores[y][i];
            y = parent[y][i];
        }

        if(x == y) return;

        for(int i = 20; i >= 0; i--) {
            if(parent[x][i] == parent[y][i]) continue;
            result |= stores[x][i];
            result |= stores[y][i];

            x = parent[x][i];
            y = parent[y][i];
        }

        result |= stores[x][0];
        result |= stores[y][0];
    }
}
