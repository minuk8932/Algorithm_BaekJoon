package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author exponential-e
 * 백준 24039번: 2021은 무엇이 특별할까?
 *
 * @see https://www.acmicpc.net/problem/24039
 *
 */
public class Boj24039 {

    private static List<Integer> primes = new ArrayList<>();
    private static List<Integer> special = new ArrayList<>();

    private static boolean[] prime = new boolean[200];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        eratosThenesSieve();
        makeSpecial();

        System.out.println(getSpecial(N));
    }

    private static int getSpecial(int target) {

        for(int spec: special) {
            if(target >= spec) continue;
            return spec;
        }

        return 0;
    }

    private static void makeSpecial() {
        int size = primes.size();

        for(int i = 1; i < size; i++) {
            special.add(primes.get(i - 1) * primes.get(i));
        }
    }

    private static void eratosThenesSieve() {
        Arrays.fill(prime, true);

        prime[0] = prime[1] = false;
        for(int i = 2; i * i < prime.length; i++) {
            if (!prime[i]) continue;

            for(int j = i + i; j < prime.length; j += i) {
                prime[j] = false;
            }
        }

        for(int i = 0; i < prime.length; i++) {
            if(!prime[i]) continue;
            primes.add(i);
        }
    }
}
