package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 13728번: 행렬식과 GCD
 *
 * @see https://www.acmicpc.net/problem/13728/
 *
 */
public class Boj13728 {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(matrixGCD(N));
    }

    private static long matrixGCD(int n) {
        if(n == 1) return 1;

        long[] fibo = new long[n + 2];
        fibo[1] = 1;

        for(int i = 2; i <= n + 1; i++) {
            fibo[i] = modulation(fibo[i - 1], fibo[i - 2]);                 // this determinant has characteristic, such as fibonacci sequence
        }

        long result = 0;
        for(int i = 1; i <= n; i++) {
            result = modulation(result, fibo[gcd(i + 1, n + 1)]);    // gcd(fibonacci(a), fibonacci(b)) == fibonacci(gcd(a, b))
        }

        return result;
    }

    private static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    private static long modulation(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }
}
