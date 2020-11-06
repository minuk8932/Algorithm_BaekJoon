package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14731번: 謎紛芥索紀 (Large)
 *
 * @see https://www.acmicpc.net/problem/14731
 *
 */
public class Boj14731 {
    private static final long MOD = 1_000_000_007L;
    private static HashMap<Integer, Long> dp = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long result = 0;

        dp.put(0, 1L);
        dp.put(1, 2L);

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long C = Long.parseLong(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            if(K == 0) continue;                        // constant exception

            result = ((result % MOD) + (modulation(modulation(C, K), mathPow(K - 1)) % MOD)) % MOD;
        }

        System.out.println(result);
    }

    private static long mathPow(int k) {
        if(dp.containsKey(k)) return dp.get(k);

        int half = k / 2;
        int mod = k % 2;

        dp.put(half, mathPow(half));
        dp.put(half + mod, mathPow(half + mod));
        return modulation(dp.get(half), dp.get(half + mod));        // divide & conquer
    }

    private static long modulation(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }
}
