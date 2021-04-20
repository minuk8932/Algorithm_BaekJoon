package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 1958번: LCS3
 *
 * @see https://www.acmicpc.net/problem/1958
 *
 */
public class Boj1958 {

    private static final char[][] STRING = new char[3][];
    private static final int[][][] dp = new int[100][100][100];

    private static int[] sizes = new int[3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br.readLine(), 0);
        input(br.readLine(), 1);
        input(br.readLine(), 2);

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(recursion(sizes[0], sizes[1], sizes[2]));
    }

    /**
     *
     * Recursion
     *
     * line 54: Matched
     * line 57 ~ 62: Not matched
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    private static int recursion(int x, int y, int z){
        if(x < 0 || y < 0 || z < 0) return 0;

        if(dp[x][y][z] != -1) return dp[x][y][z];
        int result = 0;

        if(STRING[0][x] == STRING[1][y] && STRING[1][y] == STRING[2][z]) {
            result = Math.max(recursion(x - 1, y - 1, z - 1) + 1, result);
        }
        else {
            result = Math.max(recursion(x - 1, y, z), result);
            result = Math.max(recursion(x, y - 1, z), result);
            result = Math.max(recursion(x, y, z - 1), result);
            result = Math.max(recursion(x - 1, y - 1, z), result);
            result = Math.max(recursion(x, y - 1, z - 1), result);
            result = Math.max(recursion(x - 1, y, z - 1), result);
        }

        return dp[x][y][z] = result;
    }

    private static void input(String in, int index) {
        int len = in.length();
        sizes[index] = len - 1;
        STRING[index] = new char[len];

        for(int i = 0; i < len; i++) {
            STRING[index][i] = in.charAt(i);
        }
    }
}
