package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17085번: 십자가 2개 놓기
 *
 * @see https://www.acmicpc.net/problem/17085/
 *
 */
public class Boj17085 {
    private static int[] count = new int[2];

    private static int N, M;
    private static boolean[] visit;
    private static boolean[][] map;
    private static boolean[][] covered;
    private static ArrayList<Integer> combination = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) == '#';
            }
        }

        System.out.println(crossing());
    }

    private static int crossing() {
        for (int i = 0; i <= 7; i++) {
            visit = new boolean[8];
            combination.add(i * 10 + i);
            backTracking(i, i, 0);
        }

        int max = 0;

        for (int c : combination) {
            int[] index = {c / 10, c % 10};

            for (int row1 = 0; row1 < N; row1++) {
                for (int col1 = 0; col1 < M; col1++) {
                    if(!map[row1][col1]) continue;

                    covered = new boolean[N][M];
                    count[0] = 0;
                    if (!validate(row1, col1, index[0], 0)) continue;           // put one
                    covered[row1][col1] = true;

                    for (int row2 = 0; row2 < N; row2++) {
                        for (int col2 = 0; col2 < M; col2++) {
                            if(!map[row2][col2] || covered[row2][col2]) continue;

                            count[1] = 0;
                            if (!validate(row2, col2, index[1], 1)) continue;    // put another
                            int multiply = count[0] * count[1];

                            if(max < multiply) max = multiply;
                        }
                    }
                }
            }
        }

        return max;
    }

    private static boolean validate(int row, int col, int idx, int flag) {
        count[flag] = 1;

        for (int i = 1; i <= idx; i++) {
            int up = row - i;
            int down = row + i;
            int left = col - i;
            int right = col + i;

            if (up < 0 || down >= N || left < 0 || right >= M) return false;
            if (!map[up][col] || !map[down][col] || !map[row][left] || !map[row][right]) return false;
            if (covered[up][col] || covered[down][col] || covered[row][left] || covered[row][right]) return false;

            if(flag == 0) covered[up][col] = covered[down][col] = covered[row][left] = covered[row][right] = true;
            count[flag] += 4;
        }

        return true;
    }

    private static void backTracking(int current, int value, int count) {
        if (count == 1) {
            combination.add(value);
            return;
        }

        visit[current] = true;

        for (int next = 0; next <= 7; next++) {
            if (visit[next]) continue;

            backTracking(next, value * 10 + next, count + 1);
            visit[next] = false;
        }
    }
}
