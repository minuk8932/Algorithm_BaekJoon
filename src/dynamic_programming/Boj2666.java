package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2666번: 벽장문의 이동
 *
 * @see https://www.acmicpc.net/problem/2666
 *
 */
public class Boj2666 {

    private static int[][][] dp;
    private static int[] opened = new int[2];
    private static int size, N;

    private static List<Integer> doors = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        opened[0] = Integer.parseInt(st.nextToken()) - 1;
        opened[1] = Integer.parseInt(st.nextToken()) - 1;

        int M = Integer.parseInt(br.readLine());
        while(M-- > 0) {
            doors.add(Integer.parseInt(br.readLine()) - 1);
        }

        size = doors.size();
        init();
        System.out.println(recursion(opened[0], opened[1], 0));
    }

    /**
     *
     * Recursion
     *
     * line 57: select 0 or 1 and switch index door
     *
     * @param door1
     * @param door2
     * @param index
     * @return
     */
    private static int recursion(int door1, int door2, int index) {
        if(size == index) return 0;
        if (dp[door1][door2][index] != -1) return dp[door1][door2][index];

        int result = Math.min(recursion(doors.get(index), door2, index + 1) + Math.abs((doors.get(index) - door1)),
                recursion(door1, doors.get(index), index + 1) + Math.abs((doors.get(index) - door2)));

        return dp[door1][door2][index] = result;
    }

    private static void init() {
        dp = new int[N][N][size];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }
}
