package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 15717번: 떡 파이어
 *
 * @see https://www.acmicpc.net/problem/15717
 *
 */
public class Boj15717 {
    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        System.out.println(repeat(N));
    }

    private static long repeat(long n) {
        if(n == 0) return 1;

        long result = 1;
        long pow = 2;
        n--;

        while(n > 0) {
            if (n % 2 == 1) result = modulation(result, pow);       // make pows
            pow = modulation(pow, pow);

            n /= 2;
        }

        return result;
    }

    private static long modulation(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }
}
