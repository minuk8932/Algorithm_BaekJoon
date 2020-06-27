package fenwick_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11658번: 구간 합 구하기 3
 *
 * @see https://www.acmicpc.net/problem/11658/
 *
 */
public class Boj11658 {
    private static int[][] tree;

    private static int N;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        tree = new int[N + 1][N + 1];
        int[][] input = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= N; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                update(i, j, input[i][j]);
            }
        }

        StringBuilder sb = new StringBuilder();

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(w == 0){
                int c = Integer.parseInt(st.nextToken());

                update(x, y, c - input[x][y]);               // update with fenwick tree
                input[x][y] = c;
            }
            else{
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());

                int result = 0;

                for (int row = x; row <= x1; row++) {               // make section sum
                    result += sum(row, y1) - sum(row, y - 1);
                }

                sb.append(result).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void update(int row, int col, int value) {
        while (col <= N) {
            tree[row][col] += value;
            col += (col & -col);
        }
    }

    private static int sum(int row, int col) {
        int subsum = 0;

        while (col > 0) {
            subsum += tree[row][col];
            col -= (col & -col);
        }

        return subsum;
    }
}
