package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author exponential-e
 * 백준 20946번: 합성인수분해
 *
 * @see https://www.acmicpc.net/problem/20946
 *
 */
public class Boj20946 {

    private static final String SPACE = " ";
    private static ArrayList<Long> primes = new ArrayList<>();
    private static boolean[] prime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        eratosthenesSieve();
        System.out.println(finder(N));
    }

    private static void eratosthenesSieve() {
        int size = 1_001;
        prime = new boolean[1_000_001];
        Arrays.fill(prime, true);

        prime[0] = prime[1] = false;
        for(int i = 2; i < prime.length; i++) {
            if(!prime[i]) continue;

            for(int j = i + i; j < size; j += i) {
                prime[j] = false;
            }
        }

        for(int i = 2; i < prime.length; i++) {
            if (!prime[i]) continue;
            primes.add((long) i);
        }
    }

    /**
     *
     * Finder
     *
     * line 76 ~ 83: find primes & make composite number
     *
     * @param n
     * @return
     */
    private static String finder(long n) {
        ArrayList<Long> list = new ArrayList<>();
        long value = n;

        for(long p: primes) {
            while(value % p == 0) {
                value /= p;
                list.add(p);
            }
        }

        if(value != 1) list.add(value);

        int size = list.size();
        if(size == 1) return "-1";

        ArrayList<Long> result = new ArrayList<>();

        for(int i = 0; i < size; i += 2) {
            if(size % 2 == 1 && i == size - 3){
                result.add(list.get(i) * list.get(i + 1) * list.get(i + 2));
                break;
            }

            result.add(list.get(i) * list.get(i + 1));
        }

        StringBuilder sb = new StringBuilder();
        Collections.sort(result);

        for(long r: result) {
            sb.append(r).append(SPACE);
        }

        return sb.toString();
    }
}
