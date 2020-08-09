package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15712번: 등비수열
 *
 * @see https://www.acmicpc.net/problem/15712
 *
 */
public class Boj15712 {
    private static HashMap<Integer, Long> dp = new HashMap<>();
    private static long mod;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long r = Long.parseLong(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        mod = Long.parseLong(st.nextToken());

        System.out.println((a % mod) * (recursion(n - 1, r) % mod) % mod);
    }

    private static long modulation(long a, long b){
        long sum = 1;

        while (b > 0) {
            if (b % 2 != 0) sum = (sum % mod) * (a % mod) % mod;
            a = (a % mod) * (a % mod) % mod;
            b /= 2;
        }

        return sum;
    }

    private static long recursion(int n, long r) {          // tied 2n+1 or 2n, and calculation
        if (dp.containsKey(n)) return dp.get(n);

        if (n == 0) return 1;
        if (n == 1) return (r + 1) % mod;

        long result;
        int half = n / 2;

        if (n % 2 == 0){
            result = (recursion(half - 1, r) % mod * (modulation(r, half) + 1) % mod + modulation(r, n) % mod) % mod;
        }
        else {
            result = recursion(half, r) % mod * (modulation(r, half + 1) + 1) % mod;
        }

        dp.put(n, result);
        return result;
    }
}
