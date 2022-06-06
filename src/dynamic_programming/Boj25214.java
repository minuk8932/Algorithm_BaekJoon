package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25214번: 크림 파스타
 *
 * @see https://www.acmicpc.net/problem/25214
 *
 */
public class Boj25214 {

    private static final String SPACE = " ";

    private static int[] A;
    private static int[] dp;

    private static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N + 1];
        dp = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = -1;
        }

        dp[0] = -1;
        min = A[1];
        recursion(N);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(dp[i]).append(SPACE);
        }

        System.out.println(sb);
    }

    private static int recursion(int current) {
        if(current == 0) return 0;

        if(dp[current] != -1) return dp[current];

        int answer = Math.max(recursion(current - 1), A[current] - min);
        min = Math.min(min, A[current]);

        return dp[current] = answer;
    }
}
