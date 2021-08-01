package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 22352번: 항체 인식
 *
 * @see https://www.acmicpc.net/problem/22352
 *
 */
public class Boj22352 {

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static int N, M;
    private static int[][] src, snk;
    private static boolean[][] difference;

    private static Point target = new Point(-1, -1);

    private static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        src = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                src[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        snk = new int[N][M];
        difference = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                snk[i][j] = Integer.parseInt(st.nextToken());
                difference[i][j] = src[i][j] != snk[i][j];

                if(!difference[i][j]) continue;
                target = new Point(i, j);
            }
        }

        System.out.println(comparing());
    }

    private static String comparing() {
        if(target.row == -1) return "YES";

        boolean[][] visit = new boolean[N][M];
        Queue<Point> q = new ArrayDeque<>();
        q.offer(target);

        visit[target.row][target.col] = true;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for (final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol) || visit[nextRow][nextCol]) continue;
                if(src[nextRow][nextCol] != src[current.row][current.col]) continue;
                visit[nextRow][nextCol] = true;

                q.offer(new Point(nextRow, nextCol));
            }
        }

        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < M; ++col) {
                if (difference[row][col] != visit[row][col]) return "NO";
                if (visit[row][col] &&
                        snk[target.row][target.col] != snk[row][col]) return "NO";
            }
        }

        return "YES";
    }



    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= M;
    }
}
