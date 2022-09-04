package dynamic_programming;

import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25419번: 정수를 끝까지 외치자
 *
 * @see https://www.acmicpc.net/problem/25419
 *
 */
public class Boj25419 {

    private static final HashSet<Integer> BANNED = new HashSet<>();
    private static int[] dp;
    private static int n, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            BANNED.add(Integer.parseInt(st.nextToken()));
        }

        dp = new int[n + k + 1];
        Arrays.fill(dp, -1);

        System.out.println(recursion(0));
    }

    private static int recursion(int current) {
        if(dp[current] != -1) return dp[current];
        int answer = 0;

        int loop = current == 0 ? Math.min(n, k): Math.min(n, current + k);

        for(int next = current + 1; next <= loop; next++) {
            if (BANNED.contains(next)) continue;
            answer |= (1 - recursion(next));
        }

        return dp[current] = answer;
    }
}
