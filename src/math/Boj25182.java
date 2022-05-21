package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 15182번: 청정 수열
 *
 * @see https://www.acmicpc.net.problem/15182
 *
 */
public class Boj25182 {

    private static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        System.out.println(sum(N) + " " + count(N));
    }

    private static long count(long n) {
        long facto = 1L;

        for(long i = n; i > 0; i--) {
            facto *= i;
            facto %= MOD;
        }

        return (facto * facto) % MOD;
    }

    private static long sum(long n) {
        long sum = 0;

        for(long i = n; i > 0; i--) {
            long value = ((i + 1) % MOD * i) % MOD;
            value *= i;
            sum += value % MOD;
            sum %= MOD;
        }

        return sum;
    }
}
