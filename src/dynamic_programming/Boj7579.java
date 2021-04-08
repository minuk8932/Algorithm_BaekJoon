package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 7579번: 앱
 *
 * @see https://www.acmicpc.net/problem/7579
 *
 */
public class Boj7579 {

    private static int[][] dp = new int[101][10_001];

    private static int[] memory, cost;
    private static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memory = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        cost = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(binarySearch());
    }

    /**
     *
     * Binary Search
     *
     * Find minimum cost
     *
     * @return
     */
    private static int binarySearch() {
        int start = 0;
        int end = dp[0].length;

        int result = 0;

        while(start <= end) {
            int mid = (start + end) >> 1;

            if(recursion(N - 1, mid) >= M) {
                end = mid - 1;
                result = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return result;
    }

    /**
     *
     * Recursion
     *
     * Find max cost when make total
     *
     * @param current
     * @param total
     * @return
     */
    private static int recursion(int current, int total) {
        if (current < 0) return 0;

        if (dp[current][total] != -1) return dp[current][total];
        int result = recursion(current - 1, total);

        if (total >= cost[current])
            result = Math.max(result
                    , recursion(current - 1, total - cost[current]) + memory[current]);

        return dp[current][total] = result;

    }
}
