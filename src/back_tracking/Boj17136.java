package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17136번: 색종이 붙이기
 *
 * @see https://www.acmicpc.net/problem/17136
 *
 */
public class Boj17136 {

    private static int result = 30;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[10][10];
        for(int i = 0; i < map.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < map.length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursion(map, 0, 0, 0, 0, 0, 0, 0);
        System.out.println(result == 30 ? -1: result);
    }

    /**
     *
     * Cover method
     *
     * line 63 ~ 77: size 5
     * line 79 ~ 93: size 4
     * line 95 ~ 109: size 3
     * line 111 ~ 125: size 2
     * line 127 ~ 131: size 1
     *
     * @param state
     * @param x
     * @param y
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     */
    private static void recursion(int[][] state, int x, int y, int a, int b, int c, int d, int e) {
        if(a > 5 || b > 5 || c > 5 || d > 5 || e > 5) return;

        if(isCovered(state)){
            result = Math.min(a + b + c + d + e, result);
            return;
        }

        for(int i = x; i < 10; i++) {
            for(int j = y; j < 10; j++) {
                if(state[i][j] == 0) continue;

                if(covering(i, j, 5, state)) {
                    for (int row = i; row < i + 5; row++) {
                        for (int col = j; col < j + 5; col++) {
                            state[row][col] = 0;
                        }
                    }

                    recursion(state, i, 0, a, b, c, d, e + 1);

                    for (int row = i; row < i + 5; row++) {
                        for (int col = j; col < j + 5; col++) {
                            state[row][col] = 1;
                        }
                    }
                }

                if(covering(i, j, 4, state)) {
                    for (int row = i; row < i + 4; row++) {
                        for (int col = j; col < j + 4; col++) {
                            state[row][col] = 0;
                        }
                    }

                    recursion(state, i, 0, a, b, c, d + 1, e);

                    for (int row = i; row < i + 4; row++) {
                        for (int col = j; col < j + 4; col++) {
                            state[row][col] = 1;
                        }
                    }
                }

                if(covering(i, j, 3, state)) {
                    for (int row = i; row < i + 3; row++) {
                        for (int col = j; col < j + 3; col++) {
                            state[row][col] = 0;
                        }
                    }

                    recursion(state, i, 0, a, b, c + 1, d, e);

                    for (int row = i; row < i + 3; row++) {
                        for (int col = j; col < j + 3; col++) {
                            state[row][col] = 1;
                        }
                    }
                }

                if(covering(i, j, 2, state)) {
                    for (int row = i; row < i + 2; row++) {
                        for (int col = j; col < j + 2; col++) {
                            state[row][col] = 0;
                        }
                    }

                    recursion(state, i, 0, a, b + 1, c, d, e);

                    for (int row = i; row < i + 2; row++) {
                        for (int col = j; col < j + 2; col++) {
                            state[row][col] = 1;
                        }
                    }
                }

                if(covering(i, j, 1, state)) {
                    state[i][j] = 0;
                    recursion(state, i, 0, a + 1, b, c, d, e);
                    state[i][j] = 1;
                }

                return;
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row >= 10 || col >= 10;
    }

    private static boolean isCovered(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                if(arr[i][j] == 1) return false;
            }
        }

        return true;
    }

    private static boolean covering(int i, int j, int size, int[][] state ) {
        for(int row = i; row < size + i; row++) {
            for(int col = j; col < size + j; col++) {
                if(outOfRange(row, col) || state[row][col] == 0) return false;
            }
        }

        return true;
    }
}
