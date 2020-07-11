package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13059번: Tourists
 *
 * @see https://www.acmicpc.net/problem/13059/
 *
 */
public class Boj13059 {
    private static int[][] parent;
    private static int[] depth;
    private static boolean[] visit;

    private static ArrayList<Integer>[] path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        init(N);

        int loop = N - 1;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            path[x].add(y);
            path[y].add(x);
        }

        recursion(0, 0);
        connecting(N);

        System.out.println(make(N));
    }

    private static long make(int n) {
        long result = 0;

        for(int i = 1; i <= n; i++) {                   // i's multiplies
            for(int j = i + i; j <= n; j += i) {
                result += depth[i - 1] + depth[j - 1] - 2 * depth[LCA(i - 1, j - 1)] + 1;       // find tourists count
            }
        }

        return result;
    }

    private static void init(int n) {
        path = new ArrayList[n];
        parent = new int[n][21];
        depth = new int[n];
        visit = new boolean[n];

        for(int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
        }
    }

    private static void recursion(int current, int deep) {
        depth[current] = deep;
        visit[current] = true;

        for(int next: path[current]){
            if(visit[next]) continue;
            parent[next][0] = current;

            recursion(next, deep + 1);
        }
    }

    private static void connecting(int n) {
        for(int p = 1; p < 21; p++) {
            for(int cur = 0; cur < n; cur++) {
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static int LCA(int x, int y) {
        if(depth[x] > depth[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--) {
            int jump = 1 << i;

            if(depth[y] - depth[x] >= jump) y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--) {
            if(parent[x][i] == parent[y][i]) continue;
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }
}
