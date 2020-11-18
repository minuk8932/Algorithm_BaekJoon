package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20118번: 호반우가 길을 건너간 이유
 *
 * @see https://www.acmicpc.net/problem/20118
 *
 */
public class Boj20118 {
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static int count;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        crossRoad(N, M);
        System.out.println(count);
        System.out.print(sb.toString());
    }

    private static void crossRoad(int n, int m) {
        int row = 0, col = 0;
        int nr = samePairs(n, m) ? 1: 0;

        pathBuilder(row, col, nr, 1);
        row = nr; col = 1;

        while(true) {                                   // make path absolutely zero xor
            if(row == n - 1 && col == m - 1) return;

            int nRow = row;
            int nCol = col + 1;
            int nnRow = row;
            int nnCol = col + 2;

            if(nCol >= m) {
                nCol = col;
                nnCol = col;
                nRow += 1;
                nnRow += 2;
            }
            else {
                if(nnCol + 1 >= m) {
                    nnRow += 1;
                    nnCol -= 1;
                }
            }

            pathBuilder(nRow, nCol, nnRow, nnCol);
            row = nnRow;
            col = nnCol;
        }
    }

    private static boolean samePairs(int n, int m) {
        return (n % 2 == 0 && m % 2 == 0) || (n % 2 == 1 && m % 2 == 1);
    }

    private static void pathBuilder(int row, int col, int nrow, int ncol) {
        for(int i = 0; i < 2; i++) {
            sb.append(row).append(SPACE).append(col).append(NEW_LINE);
            sb.append(nrow).append(SPACE).append(ncol).append(NEW_LINE);
        }

        count += 4;
    }
}
