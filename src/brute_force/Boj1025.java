package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;

/**
 *
 * @author exponential-e
 * 백준 1025번: 제곱수 찾기
 *
 * @see https://www.acmicpc.net/problem/1025
 *
 */
public class Boj1025 {

    private static int[][] map;
    private static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++){
            String line = br.readLine();

            for(int j = 1; j <= M; j++){
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        System.out.println(bigPowNumber());
    }

    private static int bigPowNumber() {
        int answer = -1;

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                for (int x = -N; x < N ; x++) {
                    for (int y = -M; y < M; y++) {
                        if (x == 0 && y == 0) continue;

                        int target = searching(row, col, x, y);
                        if (!isPow(target)) continue;

                        answer = Math.max(answer, target);
                    }
                }
            }
        }

        return answer;
    }

    private static int searching(int row, int col, int rowAdd, int colAdd) {
        int target = 0;
        int result = -1;

        while (!OUT_OF_RANGE.test(row, col)) {
            target *= 10;
            target += map[row][col];
            if (isPow(target)) result = Math.max(result, target);

            row += rowAdd;
            col += colAdd;
        }

        return result;
    }

    private static final BiPredicate<Integer, Integer> OUT_OF_RANGE = (row, col) ->
        row <= 0 || row > N || col <= 0 || col > M;

    private static boolean isPow (int value) {
        int sqrt = (int) Math.sqrt(value);
        return sqrt * sqrt == value;
    }
}
