import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Boj23260 {

    private static final int MOD = 1_000_000_007;
    private static final int SIZE = 1_000_000;

    private static int primes;
    private static int compositions;
    private static boolean[] prime = new boolean[SIZE + 1];
    private static Map<Long, Integer> dp = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        eratosThenes();
        // 소수 갯수(p) p_C_k-1 * 합성 수 갯수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());

            if(prime[number]) primes++;
            else compositions++;
        }

        System.out.println(calculation(K));
    }

    private static int calculation(int k) {
        if(primes + 1 < k) return 0;
        if(compositions == 0) return combination(primes, k);

        return MODULATION_MUL.apply(combination(primes, k - 1), compositions);
    }

    private static int combination(int n, int r) {
        int head = 1;
        for(int i = 0; i < r; i++) {
            head = MODULATION_MUL.apply(head, n--);
        }

        int tail = 1;
        while(r > 0) {
            tail = MODULATION_MUL.apply(tail, r--);
        }

        fastPow(tail, MOD - 2, MOD);
        return 0;
    }

    private static int fastPow(int base, int exp, int mod) {
        int result = 1;

        for (; exp > 0; exp >>= 1, base = (base * base) % mod) {
            if ((exp & 1) != 1) continue;
            result = (result * base) % mod;
        }

        return result;
    }

    private static void eratosThenes() {                // 최대 78,499
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

    private static final BiFunction<Integer, Integer, Integer> MODULATION_MUL = (x, y) -> ((x % MOD) * (y % MOD)) % MOD;
    private static final BiFunction<Integer, Integer, Integer> MODULATION_SUM = (x, y) -> ((x % MOD) + (y % MOD)) % MOD;
}
