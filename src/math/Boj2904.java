package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2904번: 수학은 너무 쉬워
 *
 * @see https://www.acmicpc.net/problem/2904/
 *
 */
public class Boj2904 {
    private static ArrayList<Integer> primes = new ArrayList<>();
    private static boolean[] prime = new boolean[1_000_001];
    private static HashMap<Integer, Integer> count = new HashMap<>();

    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        eratosthenes();

        int[] value = new int[N];
        int size = primes.size();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            value[i] = Integer.parseInt(st.nextToken());
            int loop = value[i];

            if(prime[loop]) {
                adder(loop);
                continue;
            }

            int idx = 0;

            while(idx < size && loop > 0) {                         // find prime factor and make count
                int current = primes.get(idx++);

                while(loop % current == 0){
                    adder(current);
                    loop /= current;
                }
            }
        }

        System.out.println(finding(N, value));
    }

    private static void adder (int p) {
        if(count.containsKey(p)) count.put(p, count.get(p) + 1);
        else count.put(p, 1);
    }

    private static void eratosthenes() {
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        int loop = 1_001;

        for(int i = 2; i < loop; i++) {
            if(!prime[i]) continue;

            for(int j = i + i; j < prime.length; j += i) {
                prime[j] = false;
            }
        }

        for(int i = 0; i < prime.length; i++) {
            if(prime[i]) primes.add(i);
        }
    }

    private static String finding(int n, int[] value) {
        HashMap<Integer, Integer> p = new HashMap<>();
        int gcd = 1;

        for(int key: count.keySet()) {                          // divide factors
            int dup = count.get(key) / n;
            if(dup == 0) continue;

            if(p.containsKey(key)) p.put(key, p.get(key) + dup);
            else p.put(key, dup);

            gcd *= Math.pow(key, dup);
        }

        int result = 0;
        for(int i = 0; i < n; i++) {
            for(int key: p.keySet()) {
                if(key == 1) continue;

                int cnt = 0;
                int loop = value[i];

                while(loop % key == 0) {
                    cnt++;
                    loop /= key;
                }

                if(cnt < p.get(key)) result += p.get(key) - cnt;    // calculate count make to gcd
            }
        }

        StringBuilder sb = new StringBuilder();
        return sb.append(gcd).append(SPACE).append(result).toString();
    }
}
