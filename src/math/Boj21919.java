package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 21919번: 소수 최소 공배수
 *
 * @see https://www.acmicpc.net/problem/21919
 *
 */
public class Boj21919 {

    private static boolean[] primes = new boolean[1_000_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        eratosthenesSieve();
        System.out.println(LCM(arr));
    }

    private static long LCM (long[] arr) {
        boolean[] used = new boolean[primes.length];
        long result = 1L;

        for(long a: arr) {
            int integer = (int) a;

            if(!primes[integer]) continue;
            if(used[integer]) continue;
            used[integer] = true;

            result *= a;
        }

        return result == 1 ? -1: result;
    }

    private static void eratosthenesSieve() {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;

        for(int i = 2; i * i < primes.length; i++) {
            if (!primes[i]) continue;

            for(int j = i + i; j < primes.length; j += i) {
                primes[j] = false;
            }
        }
    }
}
