package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * @author exponential-e
 * 백준 12850번: 본대 산책2
 *
 * @see https://www.acmicpc.net/problem/12850/
 *
 */
public class Boj12850 {
    private static final int MOD = 1_000_000_007;
    private static HashMap<Integer, long[][]> visit = new HashMap<>();

    private static long[][] campus = {
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 0, 1},
            {0, 0, 0, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int D = Integer.parseInt(br.readLine());

        campus = recursion(D, campus);
        System.out.println(campus[0][0]);
    }

    private static long[][] recursion(int d, long[][] arr) {
        if(d == 1) return arr;

        if(visit.containsKey(d)) return visit.get(d);       // memoization

        int half = d / 2;
        long[][][] arrN = new long[2][8][8];

        if(d % 2 == 0){                                     // even
            arrN[0] = recursion(half, arr);
            visit.put(half, arrN[0]);

            return cartesianProduct(arrN[0], arrN[0]);
        }
        else{                                               // odd
            arrN[0] = recursion(half, arr);
            arrN[1] = recursion(half + 1, arr);
            visit.put(half, arrN[0]);
            visit.put(half + 1, arrN[1]);

            return cartesianProduct(arrN[0], arrN[1]);
        }
    }

    private static long[][] cartesianProduct(long[][] src, long[][] snk){       // matrix product
        long[][] arr = new long[8][8];

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                long value = 0;

                for(int k = 0; k < 8; k++) {
                    value = modulation(value, src[i][k], snk[k][j]);
                }

                arr[i][j] = value;
            }
        }

        return arr;
    }

    private static long modulation(long v, long s, long t) {
        return ((v % MOD) + ((s % MOD) * (t % MOD)) % MOD) % MOD;
    }
}
