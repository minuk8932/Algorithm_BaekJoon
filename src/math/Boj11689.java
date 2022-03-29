package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 11689번: GCD(n, k)
 *
 * @see https://www.acmicpc.net/problem/11689
 *
 */
public class Boj11689 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        System.out.println(eulerPhi(N));
    }

    private static long eulerPhi(long n) {
        long answer = n;

        for (long i = 2L; i * i <= n; i++) {
            if (n % i != 0) continue;
            answer -= answer / i;

            while (n % i == 0){
                n /= i;
            }
        }

        return answer - (n > 1 ? answer / n: 0);
    }
}
