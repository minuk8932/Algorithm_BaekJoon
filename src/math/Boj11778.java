package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11778번: 피보나치 수와 최대 공약수
 *
 * @see https://www.acmicpc.net/problem/11778/
 *
 */
public class Boj11778 {
    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        System.out.println(fibonacci(gcd(a, b)));           // fibonacci(gcd)
    }

    private static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    private static long fibonacci(long n) {
        long[][] X = {{1, 1}, {1, 0}};
        long[][] E = {{1, 0}, {0, 1}};

        while (n != 0) {
            if (n % 2 == 1) E = cartesian(E, X);
            X = cartesian(X, X);
            n /= 2;
        }

        return E[0][1] % MOD;
    }

    private static long[][] cartesian(long[][] A, long[][] B) {                 // matrix product
        long[][] result = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] = (result[i][j] % MOD) + ((A[i][k] % MOD) * (B[k][j] % MOD)) % MOD;
                    result[i][j] %= MOD;
                }
            }
        }

        return result;
    }
}
