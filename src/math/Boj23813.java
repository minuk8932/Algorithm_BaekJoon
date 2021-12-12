package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 23813번: 회전
 *
 * @see https://www.acmicpc.net/problem/23813
 *
 */
public class Boj23813 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        System.out.println(valueToSum(N));
    }

    private static long valueToSum(long n) {
        long count = 0;
        long total = 0;

        while(n > 0) {
            total += n % 10L;
            n /= 10L;
            count++;
        }

        long sum = 0;
        long ten = 1L;
        while(count-- > 0) {
            sum += (ten * total);
            ten *= 10L;
        }

        return sum;
    }
}
