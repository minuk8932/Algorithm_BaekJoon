package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9213번: 꽤 좋은 수
 *
 * @see https://www.acmicpc.net/problem/9213/
 *
 */
public class Boj9213 {
    private static int[] perfector = new int[1_000_001];
    private static boolean[] prime = new boolean[1_000_001];
    private static ArrayList<Integer> primes = new ArrayList<>();

    private static final String T = "Test ";
    private static final String C = ": ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        init();

        int t = 1;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a + b + c == 0) break;

            sb.append(T).append(t++).append(C).append(count(a, b, c)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init() {
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        int loop = (int) (Math.sqrt(prime.length)) + 1;

        for(int i = 2; i < loop; i++) {
            if(!prime[i]) continue;

            for(int j = i + i; j < prime.length; j += i) {
                prime[j] = false;
            }
        }

        for(int i = 0; i < prime.length; i++){
            if(prime[i]) primes.add(i);
        }

        for(int i = 2; i < perfector.length; i++) {
            perfector[i] = 1;
            int limit = (int) Math.sqrt(i) + 1;
            if(prime[i]) continue;

            for(int p = 2; p < limit; p++) {                // make factor's sum
                if(i % p != 0) continue;

                int div = i / p;
                perfector[i] += p;
                if(div == p) continue;

                perfector[i] += div;
            }
        }
    }

    private static int count(int from, int to, int diff) {
        int count = 0;

        for(int i = from; i <= to; i++){            // find number
            int target = i - perfector[i];

            if(diff == 0) {
                if(target == 0) count++;
            }
            else {
                if(target < -diff) continue;
                if(target <= diff) count++;
            }
        }

        return count;
    }
}
