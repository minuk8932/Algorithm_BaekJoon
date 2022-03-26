package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;

/**
 *
 * @author exponential-e
 * 백준 2186번: 문자판
 *
 * @see https://www.acmicpc.net/problem/2186
 *
 */
public class Boj2186 {
    private static int N, M, K;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static int[][][] dp;

    private static char[] key;
    private static char[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        init();

        for(int i = 1; i <= N; i++){
            String line = br.readLine();

            for(int j = 1; j <= M; j++){
                map[i][j] = line.charAt(j - 1);
            }
        }

        key = br.readLine().toCharArray();

        int answer = 0;
        for(int row = 1; row <= N; row++) {
            for(int col = 1; col <= M; col++) {
                answer += recursion(row, col, 0);
            }
        }

        System.out.println(answer);
    }

    private static final BiPredicate<Integer, Integer> OUT_OF_RANGE = (row, col) ->
        row < 1 || row > N || col < 1 || col > M;

    private static int recursion(int row, int col, int index) {
        if(key[index] != map[row][col]) return 0;
        if(index == key.length - 1) return 1;

        if(dp[row][col][index] != -1) return dp[row][col][index];
        int answer = 0;

        for(int d = 0; d < 4; d++) {
            int nextRow = row;
            int nextCol = col;

            for(int j = 1; j <= K; j++) {
                nextRow += DIRECTIONS[d][ROW];
                nextCol += DIRECTIONS[d][COL];

                if(OUT_OF_RANGE.test(nextRow, nextCol)) break;
                answer += recursion(nextRow, nextCol, index + 1);
            }
        }

        return dp[row][col][index] = answer;
    }

    private static void init() {
        dp = new int[N + 1][M + 1][81];
        map = new char[N + 1][M + 1];

        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }
}
