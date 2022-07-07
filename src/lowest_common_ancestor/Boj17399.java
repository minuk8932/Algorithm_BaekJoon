package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17399번: 트리의 외심
 *
 * @see https://www.acmicpc.net/problem/17399
 *
 */
public class Boj17399 {
    private static final String NEW_LINE = "\n";
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] depth;
    private static int N;

    public static void main(String[] args) throws Exception {
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

        dfs(0, -1);
        connecting();

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        while(Q-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken()) - 1;

            int xy = outward(x, y);
            int zx = outward(x, z);
            int yz = outward(z, y);

            int answer = -1;

            if(xy != -1){
                if(interval(x, xy) == interval(y, xy) && interval(y, xy) == interval(z, xy))
                    answer = xy;
            }

            if(zx != -1){
                if(interval(x, zx) == interval(y, zx) && interval(y, zx) == interval(z, zx))
                    answer = zx;
            }

            if(yz != -1){
                if(interval(x, yz) == interval(y, yz) && interval(y, yz) == interval(z, yz))
                    answer = yz;
            }

            sb.append(answer == -1? answer: answer + 1).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static int outward(int x, int y) {
        int inter = interval(x, y);
        if(inter % 2 == 1) return -1;

        if(depth[x] > depth[y]) return push(x, inter >> 1);
        return push(y, inter >> 1);
    }

    private static int push(int x, int diff){
        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if((jump & diff) <= 0) continue;

            x = parent[x][i];
        }

        return x;
    }

    private static int interval(int a, int b) {
        int LCA = lca(a,b);
        return depth[a] + depth[b] - (depth[LCA] << 1);
    }

    private static void init() {
        tree = new ArrayList[N];
        parent = new int[N][21];
        depth = new int[N];

        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }
    }

    private static void dfs(int current, int prev){
        for(int next: tree[current]) {
            if(next == prev) continue;
            depth[next] = depth[current] + 1;

            parent[next][0] = current;
            dfs(next, current);
        }
    }

    private static void connecting() {
        for(int p = 1; p < 21; p++){
            for(int cur = 0; cur < N; cur++){
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static int lca(int x, int y){
        if (depth[x] > depth[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--) {
            int jump = 1 << i;
            if(depth[y] - depth[x] >= jump) y = parent[y][i];
        }

        if (x == y) return x;

        for(int i = 20; i >= 0; i--) {
            if(parent[x][i] == parent[y][i]) continue;
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }
}
