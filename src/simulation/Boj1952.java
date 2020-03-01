package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1952번: 달팽이2
 *
 * @see https://www.acmicpc.net/problem/1952/
 *
 */
public class Boj1952 {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int ROW = 0, COL = 1;

    private static int N, M;
    private static boolean[][] visit;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N][M];
        System.out.println(search());
    }

    private static int search(){
        int row = 0, col = 0, dir = 0;
        int count = 0;

        while(true) {
            if(visit[row][col]) break;
            visit[row][col] = true;

            int nextRow = row + DIRECTIONS[dir][ROW];
            int nextCol = col + DIRECTIONS[dir][COL];

            if(isRange(nextRow, nextCol) || visit[nextRow][nextCol]) {
                dir = (dir + 1) % 4;
                nextRow = row + DIRECTIONS[dir][ROW];
                nextCol = col + DIRECTIONS[dir][COL];
                count++;
            }

            row = nextRow;
            col = nextCol;
        }

        return count - 1;
    }

    private static boolean isRange(int row, int col){
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
