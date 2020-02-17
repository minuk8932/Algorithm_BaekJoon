package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12266번: Sudoku Checker (Small)
 *
 * @see https://www.acmicpc.net/problem/12266/
 *
 */
public class Boj12266 {
    private static final String CASE = "Case #";
    private static final String Y = ": Yes\n";
    private static final String N = ": No\n";

    private static int[][] sudoku;
    private static int pow, n;

    private static class Point{
        int row;
        int col;

        public  Point(int row ,int col){
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            n = Integer.parseInt(br.readLine());
            pow = n * n;

            sudoku = new int[pow][pow];

            for(int i = 0; i < pow; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int j = 0; j < pow; j++){
                    sudoku[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(CASE).append(t).append(validation() ? Y: N);
        }

        System.out.println(sb.toString());
    }

    private static boolean validation(){
        for(int row = 0; row < pow; row += n) {
            for (int col = 0; col < pow; col += n) {
                if(square(new Point(row, col))) continue;           // check square
                return false;
            }
        }

        for(int i = 0; i < pow; i++){
            if(diagonal(i, true) && diagonal(i, false)) continue;       // check crossed
            return false;
        }

        return true;
    }

    private static boolean square(Point start){
        boolean[] used = new boolean[9];

        for(int row = start.row; row < start.row + n; row++){
            for(int col = start.col; col < start.col + n; col++){
                int idx = sudoku[row][col] - 1;

                if(idx > 8 || used[idx]) return false;
                used[idx] = true;
            }
        }

        return true;
    }

    private static boolean diagonal(int start, boolean flag){
        boolean[] used = new boolean[9];

        for(int i = 0; i < pow; i++){
            int idx = 0;

            if(flag) {
                idx = sudoku[start][i] - 1;
                if (idx > 8 || used[idx]) return false;
                used[idx] = true;
            }
            else{
                idx = sudoku[i][start] - 1;
                if (idx > 8 || used[idx]) return false;
                used[idx] = true;
            }
        }

        return true;
    }
}
