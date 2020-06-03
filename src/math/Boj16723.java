package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 16723번: 영원이는 ZOAC과 영원하고 싶다.
 *
 * @see https://www.acmicpc.net/problem/16723/
 *
 */
public class Boj16723 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        System.out.println(participants(N));
    }

    private static long participants(long n) {
        long result = 2 * n;

        for (long i = 1; n / 2 > 0; i++) {
            n /= 2;
            result += (1 << i) * n;         // pow * n size
        }

        return result;
    }
}
