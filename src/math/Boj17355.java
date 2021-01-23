package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17355번: Messi An-Gimossi
 *
 * @see https://www.acmicpc.net/problem/17355
 *
 */
public class Boj17355 {

    private static final int MOD = 1_000_000_007;
    private static final int SIZE = 10_000_001;

    private static ArrayList<Integer> primes = new ArrayList<>();
    private static int[][] fraction = new int[2][SIZE];

    private static boolean[] prime = new boolean[10_000_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        eratosthenesSieve();

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            primeFactor(Math.abs(t - h), 0);
            primeFactor(Math.abs(t), 1);
        }

        System.out.println(fractionForm());
    }

    /**
     * remove duplicated value & make fraction form
     *
     * @return
     */
    private static String fractionForm() {
        for(int i = 0; i < SIZE; i++) {
            if(fraction[0][i] > fraction[1][i]) {
                fraction[0][i] -= fraction[1][i];
                fraction[1][i] = 0;
            }
            else {
                fraction[1][i] -= fraction[0][i];
                fraction[0][i] = 0;
            }
        }

        long head = 1;
        long tail = 1;

        for(int i = 0; i < SIZE; i++) {
            if(fraction[0][i] != 0) head = modulation(head, pow(i, fraction[0][i]));
            if(fraction[1][i] != 0) tail = modulation(tail, pow(i, fraction[1][i]));
        }

        return head + " " + tail;
    }

    private static long pow(long value, int current) {
        if(current == 1) return value;
        long result = 1;

        while (current-- > 0) {
            result = modulation(result, value);
        }

        return result;
    }

    private static long modulation(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    /**
     * find prime factors & convert
     *
     * @param index
     * @param flag
     */
    private static void primeFactor(int index, int flag) {
        if(index == 1 || prime[index]) {
            fraction[flag][index]++;
            return;
        }

        for(int p: primes) {
            if(index == 1) break;

            if(prime[index]) {
                fraction[flag][index]++;
                return;
            }

            while(index % p == 0) {
                index /= p;
                fraction[flag][p]++;
            }
        }
    }

    /**
     * find primes
     *
     */
    private static void eratosthenesSieve() {
        int loop = (int) Math.sqrt(SIZE) + 1;
        Arrays.fill(prime, true);

        prime[0] = prime[1] = false;

        for(int i = 2; i < loop; i++) {
            if (!prime[i]) continue;

            for(int j = i + i; j < SIZE; j += i) {
                prime[j] = false;
            }
        }

        for(int i = 2; i < prime.length; i++) {
            if (prime[i]) primes.add(i);
        }
    }
}
