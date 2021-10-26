package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author exponential-e
 * 백준 4355번: 서로소
 *
 * @see https://www.acmicpc.net/problem/4355
 *
 */
public class Boj4355 {

    private static final String NEW_LINE = "\n";

    private static Map<Integer, Integer> primes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            int result = 1;
            primes = new HashMap<>();
            euler_sPhi(n);

            for (Map.Entry<Integer, Integer> entry: primes.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();

                result *= (Math.pow(key, value) - Math.pow(key, value - 1));
            }

            sb.append(n == 1 ? 0: result).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Euler's Phi function
     *
     * @param n
     */
    private static void euler_sPhi(int n) {
        int factor = 2;

        while (n > 1) {
            if(n % factor != 0) {
                factor++;
                continue;
            }

            primes.merge(factor, 1, Integer::sum);
            n /= factor;
        }
    }
}
