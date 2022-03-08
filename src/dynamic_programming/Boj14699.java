package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14699번: 관악산 등산
 *
 * @see https://www.acmicpc.net/problem/14699
 *
 */
public class Boj14699 {

    private static final String NEW_LINE = "\n";

    private static int[] dp;
    private static int[] height;
    private static List<Integer>[] mountain;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        height = new int[N + 1];
        mountain = new ArrayList[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            mountain[i] = new ArrayList<>();
        }

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int shelter1 = Integer.parseInt(st.nextToken());
            int shelter2 = Integer.parseInt(st.nextToken());

            if(height[shelter1] < height[shelter2]) mountain[shelter1].add(shelter2);
            else mountain[shelter2].add(shelter1);
        }

        StringBuilder sb = new StringBuilder();
        dp = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            sb.append(recursion(i) + 1).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int recursion(int current) {
        if(dp[current] != 0) return dp[current];
        int answer = 0;

        for(int next: mountain[current]) {
            if(height[current] >= height[next]) continue;
            answer = Math.max(answer, recursion(next) + 1);
        }

        return dp[current] = answer;
    }
}
