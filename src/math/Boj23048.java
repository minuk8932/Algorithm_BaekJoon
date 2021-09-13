package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 23048번: 자연수 색칠하기
 *
 * @see https://www.acmicpc.net/problem/23048
 *
 */
public class Boj23048 {

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static int[] factors;
    private static int size = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSE.apply(br.readLine());

        eratosThenesSieve(N);
        System.out.println(printer());
    }

    /**
     *
     * Eratos thenes sieve
     *
     * coloring
     *
     * @param n
     */
    private static void eratosThenesSieve(int n) {
        boolean[] primes = new boolean[n + 1];

        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;

        factors = ARRY.apply(n + 1);
        factors[1] = size;

        for(int i = 2; i * i <= n; i++) {
            if(!primes[i]) continue;
            factors[i] = ++size;

            for(int j = i + i; j <= n; j += i) {
                primes[j] = false;
                factors[j] = size;
            }
        }

        for(int i = 0; i < primes.length; i++) {
            if(!primes[i] || factors[i] != 0) continue;
            factors[i] = ++size;
        }
    }

    private static String printer() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append(NEW_LINE);

        for(int i = 1; i < factors.length; i++) {
            sb.append(factors[i]).append(SPACE);
        }

        return sb.toString();
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, int[]> ARRY = int[]::new;

}
