package dynamic_programming;

import common.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17070번: 파이프 옮기기
 *
 * @see https://www.acmicpc.net/problem/17070
 *
 */
public class Boj17070 {

    private static int[][][][] dp;
    private static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N + 1][N + 1][N + 1][N + 1];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp.length; j++) {
                for(int k = 0; k < dp.length; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        int left = recursion(
            new Point.Builder(N, N).build()
            , new Point.Builder(N, N - 1).build()
            , 0
        );

        int up = recursion(
            new Point.Builder(N, N).build()
            , new Point.Builder(N - 1, N).build()
            , 1
        );

        int diagonal = recursion(
            new Point.Builder(N, N).build()
            , new Point.Builder(N - 1, N - 1).build()
            , 2
        );

        System.out.println(left + up + diagonal);
    }

    private static int recursion(Point<Integer, Integer> post, Point<Integer, Integer> prev, int type) {
        if(prev.getRow() <= 0 || prev.getCol() <= 0) return 0;

        if(type == 2 && (map[post.getRow()][post.getCol() - 1] == 1
            || map[post.getRow() - 1][post.getCol()] == 1)) return 0;

        if(map[prev.getRow()][prev.getCol()] == 1
            || map[post.getRow()][post.getCol()] == 1) return 0;

        if(prev.getRow() == 1 && prev.getCol() == 1
            && post.getRow() == 1 && post.getCol() == 2) return 1;

        if(dp[post.getRow()][post.getCol()][prev.getRow()][prev.getCol()] != -1)
            return dp[post.getRow()][post.getCol()][prev.getRow()][prev.getCol()];

        int answer = recursion(
            new Point.Builder(prev.getRow(), prev.getCol()).build()
            , new Point.Builder(prev.getRow() - 1, prev.getCol() - 1).build()
            , 2
        );

        int nextLeft;
        int nextUp;

        if(type == 0){
            nextLeft = recursion(
                new Point.Builder(prev.getRow(), prev.getCol()).build()
                , new Point.Builder(prev.getRow(), prev.getCol() - 1).build()
                , 0
            );

            answer += nextLeft;
        }
        else if(type == 1){

            nextUp = recursion(
                new Point.Builder(prev.getRow(), prev.getCol()).build()
                , new Point.Builder(prev.getRow() - 1, prev.getCol()).build()
                , 1
            );

            answer += nextUp;
        }
        else {
            nextLeft = recursion(
                new Point.Builder(prev.getRow(), prev.getCol()).build()
                , new Point.Builder(prev.getRow(), prev.getCol() - 1).build()
                , 0
            );

            nextUp = recursion(
                new Point.Builder(prev.getRow(), prev.getCol()).build()
                , new Point.Builder(prev.getRow() - 1, prev.getCol()).build()
                , 1
            );

            answer += nextLeft + nextUp;
        }

        return dp[post.getRow()][post.getCol()][prev.getRow()][prev.getCol()] = answer;
    }
}
