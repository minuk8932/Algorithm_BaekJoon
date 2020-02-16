package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9727번: Mini Sudoku X
 *
 * @see https://www.acmicpc.net/problem/9727/
 *
 */
public class Boj9727 {
    private static final String CASE = "Case#";
    private static final String COLON = ": ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            int[][] sudoku = new int[6][6];

            for(int i = 0; i < 6; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 6; j++){
                    sudoku[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(CASE).append(t).append(COLON).append(check(sudoku) ? 1: 0).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static boolean check(int[][] arr){
        for(int i = 0; i < 5; i+= 2){
            if(!square(arr, i, 0) || !square(arr, i, 3)) return false;
        }

        return diagonal(arr, 0, 0, 1) && diagonal(arr, 0, 5, -1);
    }

    private static boolean square(int[][] arr, int row, int col){
        boolean[] visit = new boolean[6];

        int start = row * 6 + col;
        int under = (row + 1) * 6 + col;
        for(int i = start; i < start + 3; i++){         // check square
            int r = i / 6;
            int c = i % 6;

            int ur = under / 6;
            int uc = under % 6;

            if(visit[arr[r][c] - 1] || visit[arr[ur][uc] - 1]) return false;
            visit[arr[r][c] - 1] = true;
            visit[arr[ur][uc] - 1] = true;

            under++;
        }

        return true;
    }

    private static boolean diagonal(int[][] arr, int row, int col, int adder){
        boolean[] visit = new boolean[6];

        for(int i = 0; i < 6; i++){                     // check diagonal
            if(visit[arr[row][col] - 1]) return false;
            visit[arr[row][col] - 1] = true;

            row++;
            col += adder;
        }

        return true;
    }
}
