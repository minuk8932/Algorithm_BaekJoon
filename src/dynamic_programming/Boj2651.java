package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 2651번: 자동차 경주 대회
 *
 * @see https://www.acmicpc.net/problem/2651
 *
 */
public class Boj2651 {
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final int INF = 1_000_000_000;

    private static int[] prefix, dp;
    private static int N, threshold;
    private static ArrayList<Integer> path = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        threshold = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        prefix = new int[N + 2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N + 1; i++) {
            prefix[i + 1] = prefix[i] + Integer.parseInt(st.nextToken());
        }

        int[] vertex = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            vertex[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 2];
        Arrays.fill(dp, INF);
        int cost = recursion(vertex, 0);

        System.out.println(print(vertex, cost));
    }

    private static String print(int[] vertex, long c) {
        StringBuilder sb = new StringBuilder();
        sb.append(c).append(NEW_LINE);

        makeRoute(vertex, 0);

        sb.append(path.size()).append(NEW_LINE);
        for(int p: path) {
            sb.append(p).append(SPACE);
        }

        return sb.toString();
    }

    private static int recursion(int[] v, int current) {
        if(dp[current] != INF) return dp[current];
        if(prefix[N + 1] - prefix[current] <= threshold) return dp[current] = 0;

        int result = dp[current + 1] + v[current];

        for(int next = current + 1; next <= N; next++) {
            if(prefix[next] - prefix[current] > threshold) continue;
            result = Math.min(recursion(v, next) + v[next - 1], result);        // make min cost
        }

        return dp[current] = result;
    }

    private static void makeRoute(int[] v, int current) {
        if(current == N + 1) return;
        if(prefix[N + 1] - prefix[current] <= threshold) return;

        for(int next = current + 1; next <= N; next++){
            if(dp[current] != dp[next] + v[next - 1]) continue;
            path.add(next);                                                      // back tracking

            makeRoute(v, next);
            return;
        }
    }
}
