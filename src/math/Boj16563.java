package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16563번: 어려운 소인수분해
 *
 * @see https://www.acmicpc.net/problem/16563/
 *
 */
public class Boj16563 {
    private static boolean[] prime = new boolean[5_000_001];
    private static ArrayList<Integer> primes = new ArrayList<>();

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        eratosthenes();                 // make prime

        int[] component = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            component[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(split(N, component));
    }

    private static void eratosthenes() {
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        int loop = (int) Math.sqrt(prime.length);

        for(int i = 2; i <= loop; i++){
            if(!prime[i]) continue;

            for(int j = i + i; j < prime.length; j += i){
                prime[j] = false;
            }
        }

        for(int i = 0; i < prime.length; i++){
            if (prime[i]) primes.add(i);
        }
    }

    private static String split(int n, int[] comp) {
        StringBuilder sb = new StringBuilder();

        int[] loops = new int[n];
        for(int i = 0; i < n; i++){
            loops[i] = (int) (Math.sqrt(comp[i])) + 1;
        }

        int idx = 0;
        for(int loop: loops) {
            int target = comp[idx++];

            if(prime[target]){
                sb.append(target).append(NEW_LINE);
                continue;
            }

            boolean flag = false;

            for (int p : primes) {
                if(p > loop) break;                 // if prime greater than sqrt(K)

                while(target % p == 0){             // find prime factor
                    sb.append(p).append(SPACE);
                    target /= p;

                    if(prime[target]){
                        sb.append(target).append(NEW_LINE);
                        flag = true;
                        break;
                    }
                }

                if(flag) break;
            }
        }

        return sb.toString();
    }
}
