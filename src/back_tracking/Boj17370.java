package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 17370번: 육각형 우리 속의 개미
 *
 * @see https://www.acmicpc.net/problem/17370/
 *
 */
public class Boj17370 {
    private static boolean[][] visit = new boolean[100][100];
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {-1, 1}, {1, 1}, {-1, -1}, {1, -1}};
    private static final int ROW = 0, COL = 1;

    private static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        visit[50][50] = true;
        recursion(49, 50, N, 0);
        System.out.println(result);
    }

    private static void recursion (int x, int y, int count, int dir) {
        if (count == 0){
            if (visit[x][y]) result++;              // cycle
            return;
        }

        if(visit[x][y]) return;
        visit[x][y] = true;

        int[] dirs = make(dir);                     // find next direction

        for (int d: dirs){
            int nx = x + DIRECTIONS[d][ROW];
            int ny = y + DIRECTIONS[d][COL];

            recursion(nx, ny, count - 1, d);
        }

        visit[x][y] = false;
    }

    private static int[] make(int d) {
        if(d <= 1) return new int[] {2 + d, (4 + d) % 6};
        else return new int[] {d % 2, d % 2 == 0 ? d + 1: d - 1};
    }
}
