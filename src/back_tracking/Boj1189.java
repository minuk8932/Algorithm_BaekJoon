package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1189번: 컴백 홈
 *
 * @see https://www.acmicpc.net/problem/1189/
 *
 */
public class Boj1189 {
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static boolean[][] visit;
    private static int R, C;
    private static int result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] blocked = new boolean[R][C];
        visit = new boolean[R][C];

        for(int i = 0; i < R; i++){
            String line = br.readLine();

            for(int j = 0; j < C; j++){
                char c = line.charAt(j);

                if(c == '.') continue;
                blocked[i][j] = true;
            }
        }

        dfs(0, C - 1, K, blocked, 1);
        System.out.println(result);
    }

    private static void dfs(int row, int col, int k, boolean[][] block, int count){
        if(row == R - 1 && col == 0){
            if(k == count) result++;
            return;
        }

        if(block[row][col]) return;                 // is blocked
        visit[row][col] = true;

        for(final int[] DIRECTION: DIRECTIONS){
            int nextRow = row + DIRECTION[ROW];
            int nextCol = col + DIRECTION[COL];

            if(outOfRange(nextRow, nextCol)) continue;
            if(block[nextRow][nextCol] || visit[nextRow][nextCol]) continue;

            dfs(nextRow, nextCol, k, block, count + 1);
            visit[nextRow][nextCol] = false;                    // backTracking
        }
    }

    private static boolean outOfRange(int row, int col){
        return row < 0 || row >= R || col < 0 || col >= C;
    }
}
