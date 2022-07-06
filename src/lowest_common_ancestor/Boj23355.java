package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23355번: 공사
 *
 * @see https://www.acmicpc.net/problem/23355
 *
 */
public class Boj23355 {

    private static final String Y = "YES\n";
    private static final String N = "NO\n";

    private static int[] depth;
    private static int[][] parent;
    private static ArrayList<ArrayList<Integer>> path = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        init(n);

        int loop = n - 1;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            path.get(x).add(y);
            path.get(y).add(x);
        }

        recursion(0, -1);
        connection(n);

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(br.readLine());

        int[] LCAs = new int[4];

        while(q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            int k = Integer.parseInt(st.nextToken()) - 1;
            int l = cmd == 1 ? -1: Integer.parseInt(st.nextToken()) - 1;

            if(l != -1 && depth[k] < depth[l]) {
                int swap = l;
                l = k;
                k = swap;
            }

            int LCA = LCA(i, j);
            LCAs[0] = LCA(k, i);
            LCAs[1] = LCA(k, j);

            if(l != -1) {
                LCAs[2] = LCA(l, i);
                LCAs[3] = LCA(l, j);
            }

            if(cmd == 1) {
                if(LCA == i) {
                    sb.append(LCAs[0] == i && LCAs[1] == k ? N: Y);
                }
                else if(LCA == j) {
                    sb.append(LCAs[0] == k && LCAs[1] == j ? N: Y);
                }
                else {
                    sb.append((LCA(LCA, k) == LCA && (k == LCAs[0] || k == LCAs[1])) ? N: Y);
                }
            }
            else {
                if(LCA == i){
                    sb.append(LCAs[1] == k && LCAs[3] == l && LCAs[0] == i && LCAs[2] == i ? N: Y);
                }
                else if(LCA == j){
                    sb.append(LCAs[0] == k && LCAs[2] == l && LCAs[1] == j && LCAs[3] == j ? N: Y);
                }
                else{
                    sb.append(
                        LCA(k, LCA) == LCA && LCA(l ,LCA) == LCA &&
                            ((LCAs[1] == k && LCAs[3] == l) || (LCAs[0] == k && LCAs[2] == l))
                            ? N: Y);
                }
            }
        }

        System.out.println(sb);
    }

    private static int LCA(int x, int y) {
        if(depth[x] > depth[y]) {
            int swap = x;
            x = y;
            y = swap;
        }

        for(int p = 20; p >= 0; p--) {
            int jump = 1 << p;

            if(depth[y] - depth[x] < jump) continue;
            y = parent[y][p];
        }

        if(x == y) return x;

        for(int p = 20; p >= 0; p--) {
            if(parent[x][p] == parent[y][p]) continue;
            x = parent[x][p];
            y = parent[y][p];
        }

        return parent[x][0];
    }

    private static void connection(int n) {
        for(int p = 1; p < 21; p++) {
            for(int cur = 0; cur < n; cur++) {
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static void recursion(int current, int prev) {
        for(int next: path.get(current)) {
            if(next == prev) continue;

            depth[next] = depth[current] + 1;
            parent[next][0] = current;

            recursion(next, current);
        }
    }

    private static void init(int n) {
        depth = new int[n];
        parent= new int[n][21];

        for(int i = 0; i < n; i++) {
            path.add(i, new ArrayList<>());
        }
    }
}
