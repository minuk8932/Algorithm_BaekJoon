package math.modular_inverse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

/**
 *
 * @author exponential-e
 * 백준 16134번: 조합
 *
 * @see https://www.acmicpc.net/problem/16134
 *
 */
public class Boj16134 {

    private static final int MOD = 1_000_000_007;
    private static final int MAX = 1_000_000;

    private static long[] factorial = new long[MAX + 1];
    private static long[] inverse = new long[MAX + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // get combination by fermat little theorem
        makeFacto();
        getInverse();

        System.out.println(MODULATION.apply(MODULATION.apply(inverse[N - R], inverse[R]), factorial[N]));
    }

    private static void makeFacto() {
        factorial[0] = 1;

        for (int i = 1; i <= MAX; i++) {
            factorial[i] = MODULATION.apply(factorial[i - 1], (long) i);
        }
    }

    private static void getInverse() {
        inverse[MAX] = pow(factorial[MAX], MOD - 2);

        for (int i = MAX - 1; 0 <= i; i--) {
            inverse[i] = MODULATION.apply(inverse[i + 1], 1L + i);
        }
    }

    private static long pow (long a, long p) {
        if (p == 0) return 1;
        if (p % 2 == 1) return MODULATION.apply(pow(a, p - 1), a);

        long half = pow(a, p >> 1) % MOD;
        return MODULATION.apply(half, half);
    }

    private static final BiFunction<Long, Long, Long> MODULATION = (a, b) -> ((a % MOD) * (b % MOD)) % MOD;
}
