import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

public class Boj23260 {

    private static final int MOD = 1_000_000_007;
    private static final int SIZE = 1_000_000;

    private static int primes;
    private static long compositions;
    private static boolean[] prime = new boolean[SIZE + 1];

    private static long[] factorial = new long[SIZE + 1];
    private static long[] inverse = new long[SIZE + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        eratosThenes();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());

            if(prime[number]) primes++;
            else compositions++;
        }

        System.out.println(calculation(K));
    }

    private static long calculation(int k) {
        if(primes + 1 < k) return 0;
        if(compositions == 0) return combination(primes, k);

        return MODULATION.apply(combination(primes, k - 1), compositions);
    }

    private static long combination(int n, int r) {
        makeFacto();
        getInverse();

        return MODULATION.apply(MODULATION.apply(inverse[n - r], inverse[r]), factorial[n]);
    }

    private static void makeFacto() {
        factorial[0] = 1;

        for (int i = 1; i <= SIZE; i++) {
            factorial[i] = MODULATION.apply(factorial[i - 1], (long) i);
        }
    }

    private static void getInverse() {
        inverse[SIZE] = pow(factorial[SIZE], MOD - 2);

        for (int i = SIZE - 1; 0 <= i; i--) {
            inverse[i] = MODULATION.apply(inverse[i + 1], 1L + i);
        }
    }

    private static long pow (long a, long p) {
        if (p == 0) return 1;
        if (p % 2 == 1) return MODULATION.apply(pow(a, p - 1), a);

        long half = pow(a, p >> 1) % MOD;
        return MODULATION.apply(half, half);
    }

    private static void eratosThenes() {
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for (int i = 2; i * i < prime.length; i++) {
            if(!prime[i]) continue;

            for (int j = i + i; j < prime.length; j += i) {
                prime[j] = false;
            }
        }

        prime[1] = true;
    }

    private static final BiFunction<Long, Long, Long> MODULATION = (x, y) -> ((x % MOD) * (y % MOD)) % MOD;
}
