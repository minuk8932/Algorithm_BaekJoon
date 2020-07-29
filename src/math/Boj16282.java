package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 16282번: Black Chain
 *
 * @see https://www.acmicpc.net/problem/16282
 *
 */
public class Boj16282 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        System.out.println(cutting(n));
    }

    private static int cutting(long n) {
        int count = 1;
        long size = 1;

        while (size < n) {                      // check one-to-one
            size = count;
            long separate = count + 1;

            for (long i = 0; i <= count; i++) {
                size += separate;
                separate = separate * 2;
            }

            count++;
        }

        return count - 1;
    }
}
