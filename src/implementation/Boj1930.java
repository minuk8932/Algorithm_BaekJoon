package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1930번: 정사면체
 *
 * @see https://www.acmicpc.net/problem/1930/
 *
 */
public class Boj1930 {
    private static final String NEW_LINE = "\n";
    private static final int[][] CASES = {{1, 2, 3}, {2, 0, 3}, {1, 3, 0}, {0, 2, 1}};      // correct cases

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] src = new int[4];
            for(int i = 0; i < 4; i++){
                src[i] = Integer.parseInt(st.nextToken());
            }

            int[] snk = new int[4];
            for(int i = 0; i < 4; i++){
                snk[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(isSame(src, snk)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int isSame(int[] src, int[] snk){
        int[][] saved = new int[12][4];                 // save all cases

        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 3; j++) {
                int idx = (i * 3 + j);
                saved[idx][0] = snk[i];

                int loop = 3;
                int t = j, k = 1;

                while(loop-- > 0) {
                    saved[idx][k++] = snk[CASES[i][t]]; // make correct permutation
                    t = (t + 1) % 3;
                }
            }
        }

        int flag = 0;

        for (int i = 0; i < 12; i++) {
            if (isFake(saved[i], src)) continue;
            return flag ^ 1;
        }

        return flag;
    }

    private static boolean isFake(int[] a, int[] b){
        return a[0] != b[0] || a[1] != b[1] || a[2] != b[2] || a[3] != b[3];
    }
}
