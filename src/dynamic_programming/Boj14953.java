package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14953번: Game map
 *
 * @see https://www.acmicpc.net/problem/14953
 *
 */
public class Boj14953 {

    private static List<List<Integer>> graph;
    private static int[] size;
    private static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        init(n);

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());

            size[city1]++;
            size[city2]++;

            graph.get(city1).add(city2);
            graph.get(city2).add(city1);
        }

        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, recursion(i) + 1);
        }

        System.out.println(max);
    }

    private static int recursion(int current) {
        if(dp[current] != -1) return dp[current];
        int answer = 0;

        for(int next: graph.get(current)) {
            if(size[current] >= size[next]) continue;
            answer = Math.max(recursion(next) + 1, answer);
        }

        return dp[current] = answer;
    }

    private static void init(int n) {
        graph = new ArrayList<>();
        size = new int[n];
        dp = new int[n];

        for(int i = 0; i < n; i++) {
            graph.add(i, new ArrayList<>());
            dp[i] = -1;
        }
    }
}
