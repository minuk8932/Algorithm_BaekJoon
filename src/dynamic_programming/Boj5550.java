package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 5550번: 헌책방
 *
 * @see https://www.acmicpc.net/problem/5550
 *
 */
public class Boj5550 {

    private static int N, K;
    private static int[][] dp;
    private static int[][] prefix;

    private static List<Integer>[] books;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        books = new ArrayList[10];
        for(int i = 0; i < 10; i++) {
            books[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int genre = Integer.parseInt(st.nextToken()) - 1;

            books[genre].add(-cost);
        }

        init();

        prefix = new int[10][N];
        for(int i = 0; i < 10; i++) {
            int size = books[i].size();
            if(size == 0) continue;

            Collections.sort(books[i]);
            prefix[i][0] -= books[i].get(0);
            for(int j = 1; j < size; j++) {
                prefix[i][j] = prefix[i][j - 1] - books[i].get(j);
            }
        }

        System.out.println(recursion(9, K));
    }

    /**
     *
     * Recursion
     *
     * line 76: make result by genre: n, cost: prefix state
     *
     * @param genre
     * @param total
     * @return
     */
    private static int recursion(int genre, int total) {
        if(total <= 0) return 0;
        if(genre < 0) return 0;

        if(dp[genre][total] != -1) return dp[genre][total];
        int result = recursion(genre - 1, total);
        
        int len = Math.min(total, books[genre].size());
        for(int size = 1; size <= len; size++) {
            result = Math.max(result
                    , recursion(genre - 1, total - size) + prefix[genre][size - 1] + (size - 1) * size);
        }

        return dp[genre][total] = result;
    }

    private static void init() {
        dp = new int[10][N + 1];

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
    }
}
