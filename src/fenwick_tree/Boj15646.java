package fenwick_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15646번: 농부 후안은 바리스타 입니다.
 *
 * @see https://www.acmicpc.net/problem/15646
 *
 */
public class Boj15646 {
    private static long[][] tree;

    private static int N, M;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        tree = new long[N + 2][M + 2];
        StringBuilder sb = new StringBuilder();

        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(cmd == 1){
                int x1 = Integer.parseInt(st.nextToken()) + 1;
                int y1 = Integer.parseInt(st.nextToken()) + 1;
                int d = Integer.parseInt(st.nextToken());

                update(x, y, d);                // add value
                update(x1, y1, d);
                update(x, y1, -d);              // except duplicated
                update(x1, y, -d);
            }
            else{
                sb.append(sum(x, y)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void update(int x, int y, int value) {
        for(int row = x; row <= N; row += row & -row) {
            for(int col = y; col <= M; col += col & -col) {
                tree[row][col] += value;
            }
        }
    }

    private static long sum(int x, int y) {
        long result = 0;

        for(int row = x; row > 0; row -= row & -row) {          // total sum
            for(int col = y; col > 0; col -= col & -col) {
                result += tree[row][col];
            }
        }

        return result;
    }
}
