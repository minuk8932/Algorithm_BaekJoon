package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25170번: 명랑한 아리의 외출
 *
 * @see https://www.acmicpc.net/problem/25170
 *
 */
public class Boj25170 {

    private static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {-1, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int INF = -1_000_000_000;

    private static int[][][] dp;
    private static int[][] work;
    private static int[][] times;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][M + 1][T + 1];
        work = new int[N + 1][M + 1];
        times = new int[N + 1][M + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
             for(int j = 1; j <= M; j++) {
                 work[i][j] = Integer.parseInt(st.nextToken());
             }
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                times[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        System.out.println(recursion(N, M, T));
    }

    private static int recursion(int row, int col, int time) {
        if(time < 0 || row <= 0 || col <= 0) return INF;
        if(row == 1 && col == 1) return 0;

        if(dp[row][col][time] != INF) return dp[row][col][time];
        int answer = INF;

        for(final int[] DIRECTION: DIRECTIONS) {
            int nextRow = row + DIRECTION[ROW];
            int nextCol = col + DIRECTION[COL];

            answer = Math.max(answer, recursion(nextRow, nextCol, time - 1));
            answer = Math.max(answer,
                recursion(nextRow, nextCol, time - times[row][col] - 1)
                    + work[row][col]);
        }

        return dp[row][col][time] = answer;
    }
}
