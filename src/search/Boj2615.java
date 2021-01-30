package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2615번: 오목
 *
 * @see https://www.acmicpc.net/problem/2615
 *
 */
public class Boj2615 {

    private static int[][] board;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    private static final int ROW = 0, COL = 1;

    private static boolean[][] visit = new boolean[19][19];


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[19][19];

        for(int i = 0; i < board.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(omok());
    }

    private static String omok() {
        StringBuilder sb = new StringBuilder();

        for(int j = 0; j < board.length; j++) {
            for(int i = 0; i < board[j].length; i++) {
                if(visit[i][j] || board[i][j] == 0) continue;
                visit[i][j] = true;

                if(isValid(i, j))
                    return sb.append(board[i][j]).append(NEW_LINE).append(i + 1).append(SPACE).append(j + 1).toString();
            }
        }

        return "0";
    }

    private static boolean isValid(int i, int j) {

        for(int d = 0; d < 8; d += 2) {
            int count = 1 + searcher(d, i, j, board[i][j]) + searcher(d + 1, i, j, board[i][j]);
            if(count == 5) return count == 5;
        }

        return false;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= 19 || col < 0 || col >= 19;
    }

    private static int searcher(int idx, int i, int j, int target){
        int count = 0;
        int row = i;
        int col = j;

        row += DIRECTIONS[idx][ROW];
        col += DIRECTIONS[idx][COL];

        while(!outOfRange(row, col) && target == board[row][col]) {
            row += DIRECTIONS[idx][ROW];
            col += DIRECTIONS[idx][COL];

            count++;
        }

        return count;
    }
}
