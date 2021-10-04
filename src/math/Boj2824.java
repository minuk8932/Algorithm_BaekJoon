package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2824번: 최대공약수
 *
 * @see https://www.acmicpc.net/problem/2824/
 *
 */
public class Boj2824 {
    private static HashMap<Integer, Integer> A = new HashMap<>();
    private static HashMap<Integer, Integer> B = new HashMap<>();

    private static boolean compressed;
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sieve(Integer.parseInt(br.readLine()), br.readLine(), A);
        sieve(Integer.parseInt(br.readLine()), br.readLine(), B);

        long result = GCD();
        System.out.println(compressed ? String.format("%09d", result): result);
    }

    private static long GCD(){
        long result = 1;

        for (int key : A.keySet()) {
            if (!B.containsKey(key)) continue;

            int loop = Math.min(A.get(key), B.get(key));
            for (int i = 0; i < loop; i++) {
                result = result * key;                      // make gcd

                if (result >= INF) {
                    compressed = true;
                    result %= INF;
                }
            }
        }

        return result;
    }

    private static void sieve (int N, String input, HashMap<Integer, Integer> primes) {
        StringTokenizer st = new StringTokenizer(input);

        for (int i = 0; i < N; i++) {
            int current = Integer.parseInt(st.nextToken());

            for(int p = 2; p * p <= current; p++) {
                int count = 0;

                while (current % p == 0) {
                    current /= p;
                    count++;
                }

                if (primes.containsKey(p)) primes.put(p, primes.get(p) + count);    // prime factors
                else primes.put(p, count);
            }

            if (current > 1) {                                                      // current prime by itself
                if (primes.containsKey(current)) primes.put(current, primes.get(current) + 1);
                else primes.put(current, 1);
            }
        }
    }
}
