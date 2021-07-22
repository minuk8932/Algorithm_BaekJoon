package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 14256번: SSR
 *
 * @see https://www.acmicpc.net/problem/14256
 *
 */
public class Boj14256 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        System.out.println(SSR(N, M));
    }

    /**
     *
     * SSR
     *
     * line 41 ~ 42: find odd count(prime factors) contains
     *                  and multiply all odd counts ---> called p
     *                  then, represented value i = p * pow(X)
     *                  so p <= i <= m, p <= p * pow(X) <= m
     *                 therefore, have to find 1 <= X <= sqrt(m / p);
     *
     * @param n
     * @param m
     * @return
     */
    private static int SSR(int n, int m) {
        int count = 0;

        for(int i = 1; i <= n; i++) {
            int factor = eratosThenesSieve(i);
            count += (int) Math.sqrt(m / factor);
        }

        return count;
    }

    private static int eratosThenesSieve(int len) {
        int result = 1;

        for (int prime = 2; prime * prime <= len; prime++) {
            int count = 0;

            while (len % prime == 0) {
                count++;
                len /= prime;
            }

            if (count % 2 == 1) result *= prime;
        }

        return result * len;
    }
}
