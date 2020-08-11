package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * @author exponential-e
 * 백준 7677번: Fibonacci
 *
 * @see https://www.acmicpc.net/problem/7677
 *
 */
public class Boj7677 {
    private static final long[][] ORIGIN = {{1, 1}, {1, 0}};
    private static HashMap<Integer, long[][]> dp = new HashMap<>();

    private static final String NEW_LINE = "\n";
    private static final long MOD = 10_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == -1) break;

            dp.put(0, new long[][]{{0, 0}, {0, 0}});
            long[][] result = recursion(N);

            sb.append(result[1][0]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static long[][] recursion(int n) {
        if(n == 1) return ORIGIN;
        if(dp.containsKey(n)) return dp.get(n);

        long[][] result;
        long[][] halfM = recursion(n / 2);
        int half = n / 2;

        dp.put(half, halfM);

        if(n % 2 == 0) {
            result = cartesianProduct(halfM, halfM);        // make matrix product
        }
        else {
            long[][] add = recursion(half + 1);
            dp.put(half + 1, add);

            result = cartesianProduct(halfM, add);
        }

        dp.put(n, result);
        return dp.get(n);
    }

    private static long[][] cartesianProduct(long[][] src, long[][] snk) {
        long[][] res = new long[2][2];

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                for(int k = 0; k < 2; k++) {
                    res[i][j] = ((res[i][j] % MOD) + ((src[i][k] % MOD) * (snk[k][j] % MOD)) % MOD) % MOD;
                }
            }
        }

        return res;
    }
}
