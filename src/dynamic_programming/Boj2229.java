package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2229번: 조 짜기
 *
 * @see https://www.acmicpc.net/problem/2229
 *
 */
public class Boj2229 {

    private static int[] students;
    private static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        students = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        System.out.println(recursion(N));
    }

    /**
     *
     * recursion
     *
     * line 57 ~ 58: make min, max value in each groups
     * line 60: traversal next case
     *
     * @param current
     * @return
     */
    private static int recursion(int current) {
        if(current <= 0) return 0;

        if(dp[current] != -1) return dp[current];
        int min = 10_001;
        int max = 0;

        int answer = 0;

        for(int i = current; i > 0; i--) {
            min = Math.min(min, students[i]);
            max = Math.max(max, students[i]);

            answer = Math.max(answer, recursion(i - 1) + (max - min));
        }

        return dp[current] = answer;
    }
}
