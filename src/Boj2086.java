import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2086 {
    private static final long MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long aSum = (fibonacci(a) + (fibonacci(a - 1)));
        long bSum = (fibonacci(b + 1) + (fibonacci(b)));

        System.out.println(a == b ? bSum - 1: (bSum < aSum ? bSum + MOD - aSum: bSum - aSum) % MOD);
    }

    private static long fibonacci(long n) {
        long[][] X = {{1, 1}, {1, 0}};
        long[][] E = {{1, 0}, {0, 1}};

        while (n > 0) {
            if (n % 2 == 1) E = cartesian(E, X);
            X = cartesian(X, X);
            n /= 2;
        }

        return E[0][1];
    }

    private static long[][] cartesian(long[][] A, long[][] B) {
        long[][] result = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] = ((result[i][j] % MOD) + (A[i][k] % MOD) * (B[k][j] % MOD)) % MOD;
                }
            }
        }

        return result;
    }
}
