import common.Point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Boj23074 {

    private static final int SIZE = 500_001;
    private static final boolean[] PRIMES = new boolean[SIZE];
    private static final int[] FACTORS = new int[SIZE];
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static int[] colors;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        colors = new int[N + 1];

        eratosthenesSieve();
        System.out.println(primeFactorsColoring(N));
    }
    
    private static String primeFactorsColoring(int n) {
        Map<Integer, Integer> fixed = new HashMap<>();
        colors[1] = 1;

        for(int number = 2; number <= n; number++) {
            if(PRIMES[number]) {
                colors[number] = 1;
                FACTORS[number]++;
                fixed.put(number, 1);
                continue;
            }

            Map<Integer, Integer> primes = factorization(number);
            int color = 0;

            for(Map.Entry<Integer, Integer> entry: primes.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                if(key == 1) continue;

                if (FACTORS[key] != 0){
                    fixed.merge(key, 1, Integer::sum);
                    color = Math.max(color, fixed.get(key));
                    fixed.put(key, color);
                }

                FACTORS[key] = Math.max(value, FACTORS[key]);
            }

            colors[number] = color;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(n >> 1).append(NEW_LINE);
        for(int i = 1; i <= n; i++) {
            sb.append(colors[i]).append(SPACE);
        }

        return sb.toString();
    }

    private static Map<Integer, Integer> factorization(int num) {
        Map<Integer, Integer> count = new HashMap<>();

        for(int p = 2; p * p <= num; p++) {
            int loop = num;
            while (loop % p == 0) {
                count.merge(p, 1, Integer::sum);
                int div = loop / p;
                if(PRIMES[div]) count.merge(div, 1, Integer::sum);

                loop /= p;
            }
        }

        return count;
    }

    private static void eratosthenesSieve() {
        Arrays.fill(PRIMES, true);
        PRIMES[0] = PRIMES[1] = false;

        for(int i = 2; i * i < PRIMES.length; i++) {
            if(!PRIMES[i]) continue;
            for(int j = i + i; j < PRIMES.length; j += i) {
                PRIMES[j] = false;
            }
        }
    }
}