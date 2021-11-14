package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

/**
 *
 * @author exponential-e
 * 백준 11402번: 이항 계수 4
 *
 * @see https://www.acmicpc.net/problem/11402
 *
 */
public class Boj11402 {

    private static long[] factorial;

    private static final int SIZE = 2_001;
    private static long mod;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        mod = M;

        init();
        System.out.println(process(N, K, M));
    }

    private static long process(long n, long k, long m) {
        if(k == 0) return 1 % m;

        long result = 1L;

        while (n > 0 || k > 0) {
            long modN = n % m;
            long modK = k % m;

            if(modN < modK) {
                result = 0;
                break;
            }

            result = MODULATION_MUL.apply(result, MODULATION_MUL.apply(factorial[(int) modN]
                    , MODULATION_MUL.apply(pow(factorial[(int) (modN - modK)], m - 2)
                            , pow(factorial[(int) modK], m - 2))));

            n /= m;
            k /= m;
        }

        return result;
    }

    /**
     *
     * Lucas' Theorem
     *
     * @param x
     * @param y
     * @return
     */
    private static long pow(long x, long y) {
        if (y == 1) return x;

        long powShift = pow(x, y >> 1L);

        if(y % 2 == 0) return MODULATION_MUL.apply(powShift, powShift);
        return MODULATION_MUL.apply(x, MODULATION_MUL.apply(powShift, powShift));
    }

    private static void init() {
        factorial = new long[SIZE];
        Arrays.fill(factorial, 1L);

        for(int i = 2; i < SIZE; i++) {
            factorial[i] = MODULATION_MUL.apply(factorial[i - 1], (long) i);
        }
    }

    private static final BiFunction<Long, Long, Long> MODULATION_MUL = (x, y) -> ((x % mod) * (y % mod)) % mod;
}
