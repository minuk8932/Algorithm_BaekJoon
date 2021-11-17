package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5971번: Meeting place
 *
 * @see https://www.acmicpc.net/problem/5971
 *
 */
public class Boj5971 {

    private static int[][] parent;
    private static int[] depth;

    private static List<Integer>[] path;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        path = new ArrayList[N];
        parent = new int[N][21];
        depth = new int[N];
        for (int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
            depth[i] = -1;
        }

        for(int i = 1; i < N; i++) {
            int node = Integer.parseInt(br.readLine()) - 1;
            path[i].add(node);
            path[node].add(i);
        }

        recursion(0, 0);
        connection(N);

        StringBuilder sb = new StringBuilder();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            sb.append(LCA(from, to) + 1).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int LCA(int x, int y) {
        if(depth[x] > depth[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(depth[y] - depth[x] >= jump) y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--){
            if(parent[x][i] != parent[y][i]){
                x = parent[x][i];
                y = parent[y][i];
            }
        }

        return parent[x][0];
    }

    private static void recursion(int current, int count) {
        depth[current] = count;

        for(int next: path[current]) {
            if(depth[next] != -1) continue;

            parent[next][0] = current;
            recursion(next, count + 1);
        }
    }

    private static void connection(int n) {
        for(int p = 1; p < 21; p++) {
            for(int cur = 0; cur < n; cur++) {
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }
}
