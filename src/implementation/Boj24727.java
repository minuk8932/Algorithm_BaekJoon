package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24727번: 인지융~
 *
 * @see https://www.acmicpc.net/problem/24727
 *
 */
public class Boj24727{

    private static final String NEW_LINE = "\n";
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static int N;

    private static int[][] map;
    private static boolean flag = true;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        placed(C, E);
        System.out.println(printer());
    }

    private static void placed(int com, int eng) {
        map = new int[N][N];

        com = fillingOne(com, 0, N, 0, -1);
        if (com > 0) {
            fillingOne(com, N, (N << 1) - 1, -1, N - 1);
        }

        eng = fillingTwo(eng, (N << 1) - 1, N, N - 1, -1);
        if (eng > 0) {
            fillingTwo(eng, N - 1, 0, -1, 0);
        }
    }

    private static String printer() {
        if(!flag) return "-1";

        StringBuilder sb = new StringBuilder();
        sb.append(1).append(NEW_LINE);

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                sb.append(map[row][col]);
            }
            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static int fillingTwo(int eng, int outS, int outE, int inS, int inE) {

        for(int data = outS; data >= outE; data--) {
            for(int i = (inS == -1 ? data: inS); i >= (inE == -1 ? data - N + 1: inE); i--) {
                if (eng == 0) break;

                int j = data - i;
                map[i][j] = 2;
                flag &= validator(i, j, map);
                eng--;
            }
        }

        return eng;
    }

    private static int fillingOne(int com, int outS, int outE, int inS, int inE) {
        for (int data = outS; data < outE; data++) {
            for(int i = (inS == -1 ? data - N + 1: inS); i <= (inE == -1 ? data: inE); i++) {
                if (com == 0) break;

                int j = data - i;
                map[i][j] = 1;
                com--;
            }
        }

        return com;
    }

    private static boolean validator(int row, int col, int[][] map) {
        if (map[row][col] == 1) return false;

        for(final int[] DIRECTION: DIRECTIONS) {
            int nextRow = row + DIRECTION[ROW];
            int nextCol = col + DIRECTION[COL];
            if (outOfRange(nextRow, nextCol)) continue;
            if (map[nextRow][nextCol] == 1) return false;
        }

        return true;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}