package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3908번: 서로 다른 소수의 합
 *
 * @see https://www.acmicpc.net/problem/3908/
 *
 */
public class Boj3908 {
    private static final String NEW_LINE = "\n";
    private static int INF = 1_121;

    private static int[][] dp = new int[INF][15];
    private static boolean[] prime = new boolean[INF];
    private static ArrayList<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        eratosthenesSieve();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            sb.append(dp[n][k]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void eratosthenesSieve() {
        Arrays.fill(prime, true);

        prime[0] = prime[1] = false;
        int loop = (int) Math.sqrt(prime.length) + 1;

        for(int i = 2; i < loop; i++) {
            if(!prime[i]) continue;

            for(int j = i + i; j < prime.length; j += i) {
                prime[j] = false;
            }
        }

        for(int i = 2; i < prime.length; i++) {
            if(prime[i]) primes.add(i);
        }

        initiator();
    }

    private static void initiator() {
        dp[0][0] = 1;

        int size = primes.size();

        for(int i = 0; i < size; i++) {                                 // with i th prime
            for(int j = prime.length - 1; j >= primes.get(i); j--) {    // make sum before zero
                for(int k = 1; k < 15; k++) {                           // i total k primes
                    dp[j][k] += dp[j - primes.get(i)][k - 1];
                }
            }
        }
    }
}
