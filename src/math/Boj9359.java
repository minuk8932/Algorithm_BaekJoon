package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 9359번: 서로소
 *
 * @see https://www.acmicpc.net/problem/9359
 *
 */
public class Boj9359 {

    private static List<Long> primes;
    private static boolean[] prime;
    private static Map<Long, Integer> mapper;

    private static final String CASE = "Case #";
    private static final String COLON = ": ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        eratosThenesSieve();
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());
            long N = Long.parseLong(st.nextToken());

            sb.append(CASE).append(t).append(COLON).append(inExclude(A, B, N)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Eratos Thenes sieve
     *
     */
    private static void eratosThenesSieve() {
        prime = new boolean[100_001];

        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        primes = new ArrayList<>();

        for(int i = 2; i < prime.length; i++){
            if(!prime[i]) continue;
            primes.add((long) i);

            for(int j = i + i; j < prime.length; j += i){
                prime[j] = false;
            }
        }
    }

    private static long inExclude(long a, long b, long n) {
        List<Long> division = new ArrayList<>();
        mapper = new HashMap<>();

        while(n > 1){
            boolean flag = true;

            for(long p : primes){
                if(n % p != 0) continue;
                n /= p;
                flag = false;

                mapper.put(p, 1);
                break;
            }

            if(flag) break;
        }

        if(n != 1) mapper.put(n, 1);
        for(Long key: mapper.keySet()){
            division.add(key);
        }

        long ans = 0;
        int size = division.size();
        long bit = 1L << size;

        for(int i = 1; i < bit; i++){
            long count = 0, sum = 1;

            for(int j = 0; j < size; j++){
                if((i & (1 << j)) == 0) continue;

                count++;
                sum *= division.get(j);
            }

            long before = (a - 1) / sum + 1;
            long after = b / sum;
            if(before > after) continue;

            long interval = after - before + 1;
            ans += ((count & 1) > 0 ? 1L: -1L) * interval;
        }

        return b - a + 1 - ans;
    }
}
