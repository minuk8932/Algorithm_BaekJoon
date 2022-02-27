package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author exponential-e
 * 백준 16400번: 소수 화폐
 *
 * @see https://www.acmicpc.net/problem/16400
 *
 */
public class Boj16400 {

    private static final int MOD = 123_456_789;

    private static List<Integer> primes = new ArrayList<>();
    private static int[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        eratosthenesSieve(N);

        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        System.out.println(bottomUp(N));
    }

    private static int bottomUp(int n) {
        dp = new int[n + 1];
        dp[0] = 1;

        int size = primes.size();
        for(int i = 0; i < size; i++) {
            for(int j = primes.get(i); j <= n; j++) {
                dp[j] = dp[j] + dp[j - primes.get(i)] % MOD;
                dp[j] %= MOD;
            }
        }

        return dp[n];
    }

    private static void eratosthenesSieve(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);

        prime[0] = prime[1] = false;

        for(int i = 2; i * i < prime.length; i++) {
            if(!prime[i]) continue;

            for(int j = i + i; j < prime.length; j += i) {
                prime[j] = false;
            }
        }

        for(int i = 0; i <= n; i++) {
            if(!prime[i]) continue;
            primes.add(i);
        }
    }
}
