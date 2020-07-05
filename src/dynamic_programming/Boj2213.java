package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2213번: 트리의 최대 독립 집합
 *
 * @see https://www.acmicpc.net/problem/2213
 *
 */
public class Boj2213 {
    private static int[] population;
    private static int[][] dp;
    private static boolean[] visit;
    private static ArrayList<Integer>[] tree;
    private static ArrayList<Integer> path = new ArrayList<>();

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        population = new int[N + 1];
        tree = new ArrayList[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            tree[i] = new ArrayList<>();
        }

        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        System.out.println(makeList(N));
    }

    private static String makeList(int n) {
        dp = new int[2][n + 1];
        visit = new boolean[n + 1];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);

        int[] result = {recursion(0, 1, 0), recursion(0, 1, 1)};

        StringBuilder sb = new StringBuilder();
        sb.append(Math.max(result[0], result[1])).append(NEW_LINE);

        findpath(0, 1);
        Collections.sort(path);

        for(int p: path) {
            sb.append(p).append(SPACE);
        }

        return sb.toString();
    }

    private static void findpath(int prev, int current) {
        if (dp[1][current] > dp[0][current]) {              // find path by declaration
            if(!visit[prev]) {
                visit[current] = true;
                path.add(current);
            }
        }

        for (int next : tree[current]) {
            if (next == prev) continue;
            findpath(current, next);
        }
    }

    private static int recursion(int prev, int current, int flag){       // @see Boj1949
        if(dp[flag][current] != -1) return dp[flag][current];
        dp[flag][current] = flag == 1 ? population[current]: 0;

        int result = dp[flag][current];

        for(int next: tree[current]) {
            if(next == prev) continue;

            if(flag == 0) result += Math.max(recursion(current, next, 0), recursion(current, next, 1));
            else result += recursion(current, next, 0);
        }

        return dp[flag][current] = result;
    }
}
