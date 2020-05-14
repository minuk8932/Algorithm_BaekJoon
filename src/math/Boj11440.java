package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 11440번: 피보나치 수의 제곱의 합
 *
 * @see https://www.acmicpc.net/problem/11440/
 *
 */
public class Boj11440 {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        System.out.println((fibonacci(n) * fibonacci(n + 1)) % MOD);    // pow sum
    }

    private static long fibonacci(long n) {
        long[][] X = {{1, 1}, {1, 0}};
        long[][] E = {{1, 0}, {0, 1}};

        while (n > 0) {
            if (n % 2 == 1) E = cartesian(E, X);                            // make fibonacci with Matrix
            X = cartesian(X, X);
            n /= 2;
        }

        return E[0][1] % MOD;
    }

    private static long[][] cartesian(long[][] A, long[][] B) {
        long[][] result = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] = ((result[i][j] % MOD) + (A[i][k] % MOD) * (B[k][j] % MOD)) % MOD;
                }
            }
        }

        return result;
    }
}
