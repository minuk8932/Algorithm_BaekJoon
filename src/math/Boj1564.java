package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 1564번: 팩토리얼 5
 *
 * @see https://www.amicpc.net/problem/1564
 *
 */
public class Boj1564 {
    private static final long MOD = 1_000_000_000_000L;
    private static final long CIPHER = 100_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(String.format("%05d", factorial(N)));
    }

    private static long factorial(long n) {
        long result = 1;

        for(int i = 1; i <= n; i++) {
            result *= i;

            while(result % 10 == 0) result /= 10;           // remove suffix zeroes
            result %= MOD;
        }

        return result % CIPHER;
    }
}
