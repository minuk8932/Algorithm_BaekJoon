package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 10623번: Breadth-First Search by Foxpower
 *
 * @see https://www.acmicpc.net/problem/10623
 *
 */
public class Boj10623 {
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] depth;
    private static long[] cost;
    private static boolean[] visit;

    private static LinkedList<Integer> query = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init(N);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N; i++) {
            int p = Integer.parseInt(st.nextToken()) - 1;
            tree[p].add(i);
            tree[i].add(p);
        }

        recursion(0, 0);
        connection(N);
        makeQuery(N);                       // ordering sequence

        System.out.println(getWorst());     // make cost with LCA
    }

    private static void init(int n) {
        tree = new ArrayList[n];
        parent = new int[n][21];
        depth = new int[n];
        cost = new long[n];
        visit = new boolean[n];

        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
    }

    private static void recursion(int current, int count) {
        visit[current] = true;
        depth[current] = count;

        for (int next: tree[current]) {
            if(visit[next]) continue;

            parent[next][0] = current;
            cost[next] = cost[current] + 1;
            recursion(next, count + 1);
        }
    }

    private static void connection(int n) {
        for(int p = 1; p < 21; p++) {
            for(int current = 0; current < n; current++) {
                parent[current][p] = parent[parent[current][p - 1]][p - 1];
            }
        }
    }

    private static long getWorst() {
        long result = 0;
        int x = query.remove();

        while(!query.isEmpty()) {
            int y = query.remove();
            result += cost[x] + cost[y] - cost[lca(x, y)] * 2;

            x = y;
        }

        return result;
    }

    private static void makeQuery(int n) {
        boolean[] chekced = new boolean[n];
        Queue<Integer> q = new LinkedList<>();

        q.offer(0);
        query.add(0);
        chekced[0] = true;

        while(!q.isEmpty()) {
            int current = q.poll();

            for (int next : tree[current]) {
                if (chekced[next]) continue;
                chekced[next] = true;

                query.add(next);
                q.offer(next);
            }
        }
    }

    private static int lca(int x, int y) {
        if(depth[x] > depth[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--) {                          // level up
            int jump = 1 << i;
            if(jump <= depth[y] - depth[x]) y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--) {                          // find lca
            if(parent[x][i] == parent[y][i]) continue;
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }
}
