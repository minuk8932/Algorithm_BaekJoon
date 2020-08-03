package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5095번: Matrix Powers
 *
 * @see https://www.acmicpc.net/problem/5095
 *
 */
public class Boj5095 {
    private static HashMap<Integer, int[][]> dp;
    private static int[][] matrix;
    private static int N, M;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            if(N + M + P == 0) break;
            dp = new HashMap<>();

            matrix = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] result = recursion(P);
            sb.append(print(result)).append(NEW_LINE);
        }

        System.out.print(sb.toString());
    }

    private static int[][] recursion(int pow) {
        if(pow == 1) return matrix;
        if(dp.containsKey(pow)) return dp.get(pow);

        int[][] result;
        int half = pow / 2;             // size divide
        int[][] halfM = dp.containsKey(half) ? dp.get(half): recursion(pow / 2);

        if(pow % 2 == 0) result = cartesianProduct(halfM, halfM);
        else result = cartesianProduct(cartesianProduct(halfM, halfM), matrix);

        dp.put(pow, result);            // conquer
        return dp.get(pow);
    }

    private static String print(int[][] res) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(res[i][j]).append(SPACE);
            }
            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static int[][] cartesianProduct(int[][] src1, int[][] src2) {
        int[][] snk = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    snk[i][j] = ((snk[i][j] % M) + (modulation(src1[i][k], src2[k][j]))) % M;
                }
            }
        }

        return snk;
    }

    private static int modulation(int src1, int src2) {
        return ((src1 % M) * (src2 % M)) % M;
    }
}
