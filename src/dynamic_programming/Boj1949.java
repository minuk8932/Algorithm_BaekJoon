package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1949번: 우수 마을
 *
 * @see https://www.acmicpc.net/problem/1949/
 *
 */
public class Boj1949 {
    private static int[] population;
    private static int[][] dp;
    private static ArrayList<Integer>[] tree;

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

        dp = new int[2][N + 1];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);

        System.out.println(Math.max(recursion(0, 1, 0), recursion(0, 1, 1)));       // first select or not
    }

    private static int recursion(int prev, int current, int flag){
        if(dp[flag][current] != -1) return dp[flag][current];
        dp[flag][current] = flag == 1 ? population[current]: 0;     // current town is Excellent ?

        int result = dp[flag][current];

        for(int next: tree[current]) {
            if(next == prev) continue;

            if(flag == 0) result += Math.max(recursion(current, next, 1), recursion(current, next, 0));     // next not
            else result += recursion(current, next, 0);
        }

        return dp[flag][current] = result;
    }
}
