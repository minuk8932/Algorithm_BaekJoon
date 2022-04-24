package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9230번: Taxi routes
 *
 * @see https://www.acmicpc.net/problem/9230
 *
 */
public class Boj9230 {

    private static final String MAP = "Map ";
    private static final String COLON = ": ";
    private static final String NEW_LINE = "\n";

    private static boolean[][] intersection;
    private static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int index = 1;

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) break;

            intersection = new boolean[N + 1][M + 1];

            while(true) {
                st = new StringTokenizer(br.readLine());
                int street = Integer.parseInt(st.nextToken());
                int avenue = Integer.parseInt(st.nextToken());
                if(street == 0 && avenue == 0) break;

                intersection[street][avenue] = true;
            }

            dp = new int[3][N + 1][M + 1];
            for(int i = 0; i <= N; i++) {
                Arrays.fill(dp[0][i], -1);
                Arrays.fill(dp[1][i], -1);
                Arrays.fill(dp[2][i], -1);
            }

            sb.append(MAP).append(index++).append(COLON)
                .append(
                    totalPath(N - 1, M - 1) -
                    recursion(0, N - 1, M - 1)).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static int totalPath(int row, int col) {
        if(row == 0 && col == 0) return 1;
        if(row < 0 || col < 0) return 0;

        if(dp[2][row][col] != -1) return dp[2][row][col];
        int answer = totalPath(row - 1, col);
        answer += totalPath(row, col - 1);

        return dp[2][row][col] = answer;
    }

    private static int recursion(int visit, int row, int col) {
        if(visit == 1 && row == 0 && col == 0) return 1;
        if(row < 0 || col < 0) return 0;

        if(dp[visit][row][col] != -1) return dp[visit][row][col];
        int answer = recursion(visit | (intersection[row][col] ? 1: 0), row - 1, col);
        answer += recursion(visit | (intersection[row][col] ? 1: 0), row, col - 1);

        return dp[visit][row][col] = answer;
    }
}
