package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 7806번: GCD!
 *
 * @see https://www.acmicpc.net/problem/7806/
 *
 */
public class Boj7806 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        String input;

        while((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            long n = Math.max(Long.parseLong(st.nextToken()), 1);
            long k = Long.parseLong(st.nextToken());

            sb.append(getGCD(n, k)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static long getGCD(long n, long k) {
        if(n >= k) return k;
        long result = 1;
        long len = (long) (Math.sqrt(k) + 1);

        for (long i = 2; i < len; i++) {
            long[] factors = new long[2];

            while (k % i == 0) {                     // k factor counts
                factors[0]++;
                k /= i;
            }

            if (factors[0] == 0) continue;

            for (long j = i; j <= n; j *= i) {      // n factor counts
                factors[1] += n / j;
            }

            for (long j = 0; j < Math.min(factors[0], factors[1]); j++){    //  make gcd
                result *= i;
            }
        }

        if (k > 1 && k <= n) result *= k;
        return result;
    }
}
